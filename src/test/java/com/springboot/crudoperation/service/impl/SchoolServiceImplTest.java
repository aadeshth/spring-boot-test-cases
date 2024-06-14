package com.springboot.crudoperation.service.impl;

import com.springboot.crudoperation.entity.School;
import com.springboot.crudoperation.exception.DataNotFoundException;
import com.springboot.crudoperation.model.SchoolDto;
import com.springboot.crudoperation.repository.SchoolRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class SchoolServiceImplTest {

    @Mock
    SchoolRepository schoolRepository;

    @InjectMocks
    SchoolServiceImpl schoolService;

    @Test
    void saveSchool() {
        School school = new School(1L, "Test");
        when(schoolRepository.save(ArgumentMatchers.any(School.class))).thenReturn(school);
        SchoolDto schoolDto = schoolService.saveSchool(SchoolDto.builder().name("Test").build());
        assertEquals("Test",schoolDto.getName());
        assertEquals(1L,schoolDto.getId());
    }

    @Test
    void findSchoolByIdSuccessTest() {
        when(schoolRepository.findByIdAndIsDeleted(anyLong(),anyInt())).thenReturn(Optional.of(School.builder().id(1L).name("TEST").build()));
        SchoolDto schoolDto = schoolService.findSchoolById(1);
        assertEquals("TEST",schoolDto.getName());
        assertEquals(1L,schoolDto.getId());
    }
    @Test
    void findSchoolByIdFailedTest() {
        when(schoolRepository.findByIdAndIsDeleted(anyLong(),anyInt())).thenReturn(Optional.empty());
        try {
            schoolService.findSchoolById(1);
            assertTrue(true);
        }catch (DataNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}