package com.cpt202.group7.service;

import com.cpt202.group7.entity.Groomer;
import com.cpt202.group7.entity.Pet;
import com.cpt202.group7.mapper.GroomerMapper;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
public class GroomerService {
    @Autowired
    private GroomerMapper groomerMapper;

    @Transactional
    public List<Groomer> getGroomerList(){
        return groomerMapper.getGroomerList();
    }

    public void deleteGroomer(Integer groomerId){
        groomerMapper.deleteGroomer(groomerId);
    }

    public Groomer getGroomer(Integer groomerId){
        return groomerMapper.getGroomer(groomerId);
    }

    public void updateGroomer(Groomer groomer){
        groomerMapper.updateGroomer(groomer, groomer.getGroomerId());
    }


    public void insert(Groomer groomer) {
        groomerMapper.insertGroomer(groomer);
    }

    public List<Groomer> getGroomersByServiceID(Integer serviceID) {
        return groomerMapper.getGroomersByServiceID(serviceID);
    }

    public List<Groomer> getGroomerListByTheDate(Timestamp passInStartTime, Integer petTypeID) {
        return groomerMapper.getGroomerListByTheDate(passInStartTime, petTypeID);
    }
}
