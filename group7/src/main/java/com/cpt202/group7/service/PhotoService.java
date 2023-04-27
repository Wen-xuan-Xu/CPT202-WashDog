package com.cpt202.group7.service;

import com.cpt202.group7.mapper.PhotoMapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoService {
    @Autowired
    private PhotoMapper photoMapper;


    public void updateGroomerPhoto(String photoPath,Integer groomerId){
        photoMapper.updateGroomerPhoto(photoPath,groomerId);
    }




    public void updatePetTypePhoto(String photoPath,Integer petTypeId){
        photoMapper.updatePetTypePhoto(photoPath, petTypeId);
    }



    public void updateServicePhoto(String photoPath,Integer ServiceId){
        photoMapper.updateServicePhoto(photoPath, ServiceId);
    }




    public void updateUserPhoto(String photoPath,Integer userId){
        photoMapper.updateUserPhoto(photoPath, userId);
    }
}
