package com.springboot.crudoperation.service;

import com.springboot.crudoperation.model.PageResponse;
import com.springboot.crudoperation.model.SchoolDto;

import java.util.List;


public interface SchoolService {

      SchoolDto saveSchool(SchoolDto schoolDto);
      SchoolDto updateSchool(SchoolDto schoolDto) ;

      SchoolDto findSchoolById(int schoolId);

      void deleteSchool(int schoolId);

      List<SchoolDto> findSchoolBySearchText(String searchText);

      PageResponse findAll(int pageNo, int pageSize, String sortBy, String sortDir);

      PageResponse searchAll(String searchText,int pageNo, int pageSize, String sortBy, String sortDir);
}
