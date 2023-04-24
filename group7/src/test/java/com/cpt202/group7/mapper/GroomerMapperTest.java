package com.cpt202.group7.mapper;

import com.cpt202.group7.entity.Groomer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
@Rollback

class GroomerMapperTest {

    @Autowired
    private GroomerMapper groomerMapper;

    @Test
    void getGroomerList() {
        List<Groomer> groomer = groomerMapper.getGroomerList();


    }

    @Test
    void getGroomer() {
        Groomer groomer = groomerMapper.getGroomer(1);

    }

    @Test
    void updateGroomer() {
    }

    @Test
    void insertGroomer() {
    }

    @Test
    void deleteGroomer() {
    }

    @Test
    void getGroomersByServiceID() {
    }

    @Test
    void getGroomerListByTheDate() {
    }
}