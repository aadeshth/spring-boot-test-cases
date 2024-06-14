package com.springboot.crudoperation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.springboot.crudoperation.model.SchoolDto;
import com.springboot.crudoperation.service.SchoolService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(value = SchoolController.class)
public class SchoolControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private SchoolService schoolService;

    @Test
    public void getSchoolDetailsPathTest() throws Exception {
        SchoolDto schoolDto = SchoolDto.builder().id(1L).build();
        Mockito.when(schoolService.findSchoolById(1)).thenReturn(schoolDto);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/school/1").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().getStatus());
        assertEquals(200,result.getResponse().getStatus());
        String exp = "{\"statusCode\":200,\"message\":\"Fetched Successfully\",\"data\":{\"id\":1,\"name\":null,\"address\":null,\"dressCodeColors\":null,\"createdDate\":null,\"createdBy\":null,\"updatedDate\":null,\"updatedBy\":null,\"classRooms\":null},\"listData\":null}";
        assertEquals(exp,result.getResponse().getContentAsString());

    }
    @Test
    public void getSchoolDetailsPathTest2() throws Exception {
        SchoolDto schoolDto = SchoolDto.builder().id(1L).build();
        when(schoolService.findSchoolById(1)).thenReturn(schoolDto);
       mockMvc.perform(MockMvcRequestBuilders
               .get("/school/1")
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists())
               .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value(1));
    }

    @Test
    public void createSchoolTest() throws Exception {
            SchoolDto schoolDto = SchoolDto.builder().id(1L).build();
        when(schoolService.saveSchool(any())).thenReturn(schoolDto);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/school").content(objectToJsonString(SchoolDto.builder().name("Test case").address("Test").build()))
                .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").isNumber());
    }
    String objectToJsonString(SchoolDto schoolDto) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(schoolDto);
    }

}
