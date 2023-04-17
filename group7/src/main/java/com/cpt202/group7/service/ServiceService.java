package com.cpt202.group7.service;


import com.cpt202.group7.entity.Service;
import com.cpt202.group7.entity.Groomer;
import com.cpt202.group7.entity.Service;

import com.cpt202.group7.mapper.ServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceService {
    @Autowired
    private ServiceMapper serviceMapper;


    @Transactional
    public List<Service> getServiceList(){
        List<Service>allService=serviceMapper.getServiceList();
        return allService;
    }


    public void deleteService(Integer serviceId){
       serviceMapper.deleteService(serviceId);
    }


    public Service getService(Integer serviceId){
        Service service = serviceMapper.getService(serviceId);
        return service;
    }


    public void updateService(Service service){
        serviceMapper.updateService(service, service.getServiceId());
    }


    public void insert(Service service){
        serviceMapper.insertService(service);
    }


    public List<Service> getServicesByPetTypeID(Integer petTypeID) {
        return serviceMapper.getServicesByPetTypeID(petTypeID);
    }
}
