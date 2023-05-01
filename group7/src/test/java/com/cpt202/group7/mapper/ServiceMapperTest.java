package com.cpt202.group7.mapper;

import com.cpt202.group7.entity.Service;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
@Rollback
class ServiceMapperTest {
    @Autowired
    private ServiceMapper serviceMapper;


    @Test
    void getService() {
        Service serviceTest = serviceMapper.getService(1);
        assertNotNull(serviceTest);
        assertThat(serviceTest.getName()).isEqualTo("Basic Grooming");
    }


    @Test
    void getServiceList() {
        List<Service> serviceListShouldGet = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            serviceListShouldGet.add(serviceMapper.getService(i + 1));
        }
        assertThat(serviceListShouldGet).isEqualTo(serviceMapper.getServiceList());
        assertThat(serviceListShouldGet.size()).isEqualTo(15);
    }


    @Test
    void updateService() {
        Service service = serviceMapper.getService(1);
        assertNotNull(service);
        service.setName("Updated Service");
        service.setPrice(20.0);
        serviceMapper.updateService(service, service.getServiceId());
        Service updatedService = serviceMapper.getService(1);
        assertThat(updatedService.getName()).isEqualTo("Updated Service");
        assertThat(updatedService.getPrice()).isEqualTo(20.0);

    }

    @Test
    void insertService() {
        Service service = new Service();
        service.setName("Test Service");
        service.setPrice(10.0);
        service.setDuration(60);
        service.setBriefIntroduction("This is a test service.");
        service.setDetailIntroduction("This is a test service, only for testing purposes.");
        int frontLength = serviceMapper.getServiceList().size();
        serviceMapper.insertService(service);
        int afterLength = serviceMapper.getServiceList().size();
        assertThat(afterLength - frontLength).isEqualTo(1);
    }

    @Test
    void deleteService() {
        serviceMapper.deleteService(1);
        assertThat(serviceMapper.getService(1)).isNull();
    }


//    @Test
//    void getServicesByPetTypeID() {
//        List<Service> services = serviceMapper.getServicesByPetTypeID(1);
//
//            assertThat(services.get(0)).isEqualTo(new Service(
//                    1,
//                    "Basic Grooming",
//                    30.0,
//                    60,
//                    "Essential grooming for pets",
//                    "This essential grooming service includes a bath, " +
//                            "brush, and nail trim. It is suitable for all pets, " +
//                            "especially those with short hair or low-maintenance coats"));
//    }
}