package com.example.smartpensionsystem.controller;

import com.example.smartpensionsystem.entity.SysUser;
import com.example.smartpensionsystem.service.SysUserService;
import com.example.smartpensionsystem.utils.JwtUtil;
import com.example.smartpensionsystem.utils.Md5Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
public class SysUserControllerTests {

    private MockMvc mockMvc;

    @Mock
    private SysUserService sysUserService;

    @Mock
    private StringRedisTemplate stringRedisTemplate;

    @Mock
    private ValueOperations<String, String> valueOperations;

    @InjectMocks
    private SysUserController sysUserController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(sysUserController).build();

        when(stringRedisTemplate.opsForValue()).thenReturn(valueOperations);
    }

    @Test
    public void testRegister() throws Exception {
        when(sysUserService.findByUserName("newuser")).thenReturn(null);
        doNothing().when(sysUserService).register("newuser", "password123");

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"newuser\", \"password\": \"password123\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andDo(print());

        when(sysUserService.findByUserName("existinguser")).thenReturn(new SysUser());
        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"existinguser\", \"password\": \"password123\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1))
                .andExpect(jsonPath("$.message").value("用户名已被占用"))
                .andDo(print());
    }

    @Test
    public void testLogin() throws Exception {
        SysUser user = new SysUser();
        user.setId(1);
        user.setUsername("testuser");
        user.setPassword(Md5Util.getMD5String("password123"));

        when(sysUserService.findByUserName("testuser")).thenReturn(user);

        String token = JwtUtil.genToken(new HashMap<String, Object>() {{
            put("id", user.getId());
            put("username", user.getUsername());
        }});

        doNothing().when(valueOperations).set(eq(token), eq(token), eq(1L), eq(TimeUnit.HOURS));

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"testuser\", \"password\": \"password123\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.data").isString())
                .andDo(print());

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"testuser\", \"password\": \"wrongpassword\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1))
                .andExpect(jsonPath("$.message").value("用户名或密码错误"))
                .andDo(print());
    }

    @Test
    public void testUpdatePsw() throws Exception {
        SysUser user = new SysUser();
        user.setUsername("testuser");
        user.setPassword(Md5Util.getMD5String("oldpassword"));

        when(sysUserService.findByUserName("testuser")).thenReturn(user);
        doNothing().when(sysUserService).updatePwd("testuser", "newpassword");

        mockMvc.perform(patch("/user/testuser/password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"old_pwd\": \"oldpassword\", \"new_pwd\": \"newpassword\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andDo(print());

        mockMvc.perform(patch("/user/testuser/password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"old_pwd\": \"wrongpassword\", \"new_pwd\": \"newpassword\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1))
                .andExpect(jsonPath("$.message").value("原密码错误"))
                .andDo(print());
    }

    @Test
    public void testFindByUsername() throws Exception {
        SysUser user = new SysUser();
        user.setUsername("testuser");

        when(sysUserService.findByUserName("testuser")).thenReturn(user);

        mockMvc.perform(get("/user/testuser"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.data.username").value("testuser"))
                .andDo(print());

        when(sysUserService.findByUserName("nonexistentuser")).thenReturn(null);

        mockMvc.perform(get("/user/nonexistentuser"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1))
                .andExpect(jsonPath("$.message").value("用户不存在"))
                .andDo(print());
    }

    @Test
    public void testUpdateSysUserInfo() throws Exception {
        doNothing().when(sysUserService).updateInfo(any(SysUser.class));

        mockMvc.perform(put("/user/testuser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"real_name\": \"Test User\", \"sex\": \"Male\", \"email\": \"testuser@example.com\", \"phone\": \"1234567890\", \"description\": \"A test user\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andDo(print());
    }
}
