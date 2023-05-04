package com.cpt202.group7.service;


import com.cpt202.group7.entity.Service;

import com.cpt202.group7.mapper.AppointmentMapper;
import com.cpt202.group7.mapper.CommentMapper;
import com.cpt202.group7.mapper.OrderMapper;
import com.cpt202.group7.mapper.ServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@org.springframework.stereotype.Service
public class ServiceService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private AppointmentMapper appointmentMapper;
    @Autowired
    private ServiceMapper serviceMapper;
    @Autowired
    private CommentMapper commentMapper;


    @Transactional
    public List<Service> getServiceList(){
        return serviceMapper.getServiceList();
    }


    public void deleteService(Integer serviceId){
       serviceMapper.deleteService(serviceId);
    }


    public Service getService(Integer serviceId){
        return serviceMapper.getService(serviceId);
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



    public Service getServiceById(Integer serviceId) {
        return serviceMapper.getService(serviceId);
    }
    public List<Service> getRandomAllowedServices() {
        return serviceMapper.getRandomAllowedServices();
    }


}
