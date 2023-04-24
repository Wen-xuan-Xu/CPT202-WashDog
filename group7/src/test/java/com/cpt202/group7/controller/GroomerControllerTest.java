package com.cpt202.group7.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.allOf;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.cpt202.group7.entity.Groomer;
import com.cpt202.group7.service.GroomerService;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


class GroomerControllerTest {


    @Mock
    GroomerService groomerService;

    @InjectMocks
    GroomerController groomerController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(groomerController).build();
    }


    @Test
    void showGroomers() throws Exception {
        List<Groomer> groomers = new ArrayList<>();
        groomers.add(new Groomer());
        when(groomerService.getGroomerList()).thenReturn(groomers);

        mockMvc.perform(get("/admin/groomer/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("/groomer/GroomerList"))
                .andExpect(model().attribute("groomer", hasSize(1)));

    }

    @Test
    void deleteGroomer() throws Exception {
        mockMvc.perform(post("/admin/groomer/delete").param("groomerId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin/groomer/list"));

        verify(groomerService, times(1)).deleteGroomer(anyInt());

    }

    @Test
    void showgroomerDetails() throws Exception {
        Groomer groomer = new Groomer();
        when(groomerService.getGroomer(anyInt())).thenReturn(groomer);

        mockMvc.perform(get("/admin/groomer/detail").param("groomerId", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("/groomer/updateGroomer"))
                .andExpect(model().attributeExists("groomer", "groomerid"));

    }

    @Test
    void updateGroomerDetails() throws Exception {
        mockMvc.perform(get("/admin/groomer/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("/groomer/addGroomer"))
                .andExpect(model().attributeExists("groomer"));
    }



    @Test
    void showAddGroomerPage() throws Exception {
        mockMvc.perform(get("/admin/groomer/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("/groomer/addGroomer"))
                .andExpect(model().attributeExists("groomer"));

    }

    @Test
    void insertGroomer() throws Exception{
        Groomer groomer = new Groomer();
        groomer.setName("Test Groomer");
        groomer.setGender("Male");
        groomer.setAge(30);

        doNothing().when(groomerService).insert(any(Groomer.class));

        mockMvc.perform(post("/admin/groomer/insert")
                        .param("name", groomer.getName())
                        .param("gender", groomer.getGender())
                        .param("age", groomer.getAge().toString()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/groomer/list"));

        verify(groomerService, times(1)).insert(any(Groomer.class));

    }
}