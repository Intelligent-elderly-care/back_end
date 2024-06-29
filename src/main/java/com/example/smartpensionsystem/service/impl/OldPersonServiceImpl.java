package com.example.smartpensionsystem.service.impl;

import com.example.smartpensionsystem.entity.OldPerson;
import com.example.smartpensionsystem.mapper.OldPersonMapper;
import com.example.smartpensionsystem.service.OldPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OldPersonServiceImpl implements OldPersonService {
    @Autowired
    private OldPersonMapper oldPersonMapper;

    @Override
    public OldPerson getOldPersonById(int id) {
        return oldPersonMapper.getOldPersonById(id);
    }

    @Override
    public OldPerson getOldPersonByIdCard(String idCard) {
        return oldPersonMapper.getOldPersonByIdCard(idCard);
    }

    @Override
    public List<OldPerson> allOldPersons() {
        return oldPersonMapper.allOldPersons();
    }

    @Override
    public void addOldPerson(OldPerson oldPerson) {
        oldPersonMapper.addOldPerson(oldPerson);
    }

    @Override
    public void deleteOldPersonById(int id) {
        oldPersonMapper.deletePerson(id);
    }

    @Override
    public void updateOldPerson(OldPerson oldPerson) {
        oldPersonMapper.updateOldPerson(oldPerson);
    }
}
