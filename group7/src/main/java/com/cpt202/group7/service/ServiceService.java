package com.cpt202.group7.service;

import com.cpt202.group7.entity.service;
import com.cpt202.group7.mapper.ServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceService {
    @Autowired
    private ServiceMapper serviceMapper;


    @Transactional
    public List<service> getServiceList(){
        List<service>allService=serviceMapper.getServiceList();
        return allService;
    }


    public void deleteService(Integer serviceId){
       serviceMapper.deleteService(serviceId);
    }


    public service getService(Integer serviceId){
        service service = serviceMapper.getService(serviceId);
        return service;
    }

    public void updateService(service service){
        serviceMapper.updateService(service, service.getServiceId());
    }


    public void insert(service service){
        serviceMapper.insertService(service);
    }





    public List<service> getServicesByPetTypeID(Integer petTypeID) {
        return serviceMapper.getServicesByPetTypeID(petTypeID);
    }
}
