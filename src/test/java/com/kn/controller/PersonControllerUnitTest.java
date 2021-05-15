package com.kn.controller;

import com.kn.model.entity.Person;
import com.kn.service.PersonService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService service;

    private static Page<Person> page;
    private static Integer pageSize = 10;
    private static Integer totalElements = 15;
    private static String name = "person";
    private static String url = "http://url.com";

    @BeforeAll
    public static void beforeClass() {

        List<Person> people = new ArrayList<>();
        for(int i = 1; i <= 10; i++) {
            Person person = new Person();
            person.setId(i);
            person.setName(name);
            person.setPhotoUrl(url);
            people.add(person);
        }

        page = new PageImpl<>(people, PageRequest.of(0, pageSize), totalElements);

    }

    @BeforeEach
    public void before() {

        doReturn(page).when(service).find(ArgumentMatchers.anyString(),
                ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt());

    }

    @Test
    public void shouldAcceptNoParams() throws Exception {

        mockMvc.perform(get("/v1/person/find"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(service).find(ArgumentMatchers.eq(""),
                ArgumentMatchers.isNull(), ArgumentMatchers.isNull());

    }

    @Test
    public void shouldAcceptOnlyFilter() throws Exception {

        this.mockMvc.perform(get("/v1/person/find")
                .param("name", "person"))
                .andExpect(status().isOk());

        verify(service).find(ArgumentMatchers.eq("person"),
                ArgumentMatchers.isNull(), ArgumentMatchers.isNull());

    }

    @Test
    public void shouldAcceptOnlyPaging() throws Exception {

        this.mockMvc.perform(get("/v1/person/find")
                .param("pageNumber", "1")
                .param("pageSize", pageSize.toString()))
                .andExpect(status().isOk());

        verify(service, only()).find(ArgumentMatchers.eq(""),
                ArgumentMatchers.eq(1), ArgumentMatchers.eq(pageSize));

    }

    @Test
    public void shouldAcceptAllParams() throws Exception {

        this.mockMvc.perform(get("/v1/person/find")
                .param("name", "person")
                .param("pageNumber", "1")
                .param("pageSize", pageSize.toString()))
                .andExpect(status().isOk());

        verify(service, only()).find(ArgumentMatchers.eq("person"),
                ArgumentMatchers.eq(1), ArgumentMatchers.eq(pageSize));

    }

}