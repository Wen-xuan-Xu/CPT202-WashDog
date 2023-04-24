package com.cpt202.group7.service;

import com.cpt202.group7.entity.Groomer;
import com.cpt202.group7.mapper.GroomerMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GroomerServiceTest {

    @Mock
    private GroomerMapper groomerMapper;

    @InjectMocks
    private GroomerService groomerService;

    private Groomer groomer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        groomer = new Groomer();
        groomer.setGroomerId(1);
        groomer.setName("John");
        groomer.setGender("male");
        groomer.setAge(25);
        groomer.setGroomerStarLevelPriceCoefficientId(1);
        groomer.setSelfIntroduction("I'm a professional groomer.");
        groomer.setWorkStartTime(Time.valueOf("09:00:00"));
        groomer.setWorkEndTime(Time.valueOf("18:00:00"));

    }

    @Test
    void getGroomerList() {
        List<Groomer> expectedGroomerList = new ArrayList<>();
        expectedGroomerList.add(groomer);

        when(groomerMapper.getGroomerList()).thenReturn(expectedGroomerList);

        List<Groomer> actualGroomerList = groomerService.getGroomerList();

        assertEquals(expectedGroomerList, actualGroomerList);

    }

    @Test
    void deleteGroomer() {
        groomerService.deleteGroomer(1);

        verify(groomerMapper, times(1)).deleteGroomer(1);

    }

    @Test
    void getGroomer() {
        when(groomerMapper.getGroomer(1)).thenReturn(groomer);

        Groomer actualGroomer = groomerService.getGroomer(1);

        assertEquals(groomer, actualGroomer);

    }

    @Test
    void updateGroomer() {
        groomerService.updateGroomer(groomer);

        verify(groomerMapper, times(1)).updateGroomer(groomer, 1);
    }

    @Test
    void insert() {
        groomerService.insert(groomer);

        verify(groomerMapper, times(1)).insertGroomer(groomer);
    }


}