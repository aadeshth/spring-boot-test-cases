package com.springboot.crudoperation.controller;

import com.springboot.crudoperation.model.ResponseDto;
import com.springboot.crudoperation.model.SchoolDto;
import com.springboot.crudoperation.service.SchoolService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


@RestController
@Tag(name = "School Controller", description = "This is used to perform crud operation on school data")
@RequestMapping(value = "/school")
public class SchoolController {

    private final SchoolService schoolService;

    @Autowired
    public SchoolController(SchoolService schoolService)
    {
        this.schoolService = schoolService;
    }

    @Operation(summary = "Fetch the school details by school id.", description = "fetch the school details")
    @GetMapping(value = "/{schoolId}")
    public ResponseEntity<ResponseDto<Object>> getSchoolDetailsPath(@PathVariable int schoolId)
    {
        return new ResponseEntity<>(ResponseDto.builder().statusCode(HttpStatus.OK.value()).message("Fetched Successfully!").data(schoolService.findSchoolById(schoolId)).build(), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<ResponseDto<Object>>  createSchool(@RequestBody SchoolDto request)  {
        return new ResponseEntity<>(ResponseDto.builder().statusCode(HttpStatus.OK.value()).message("Created Successfully").data(schoolService.saveSchool(request)).build(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ResponseDto<Object>>  updateSchool(@RequestBody SchoolDto schoolDto) {
        return new ResponseEntity<>(ResponseDto.builder().statusCode(HttpStatus.OK.value()).message("Updated Successfully").data(schoolService.updateSchool(schoolDto)).build(), HttpStatus.OK);
    }
    @PatchMapping
    public ResponseEntity<ResponseDto<Object>> schoolUpdate(@RequestBody SchoolDto schoolDto) {
        return new ResponseEntity<>(ResponseDto.builder().statusCode(HttpStatus.OK.value()).message("Updated Successfully").data(schoolService.updateSchool(schoolDto)).build(), HttpStatus.OK);
    }
    @DeleteMapping(value = "/{schoolId}")
    public ResponseEntity<ResponseDto<Object>>  deleteSchool(@PathVariable int schoolId)
    {
        schoolService.deleteSchool(schoolId);
        return new ResponseEntity<>(ResponseDto.builder().statusCode(HttpStatus.OK.value()).message("Record Deleted!").build(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ResponseDto<Object>> searchSchools(@RequestParam(value = "searchText" , required = false) String searchText)
    {
        List<SchoolDto> list = schoolService.findSchoolBySearchText(searchText);
        return new ResponseEntity<>(ResponseDto.builder().statusCode(HttpStatus.OK.value()).message("Fetched Successfully!").listData(Collections.singletonList(list)).build(), HttpStatus.OK);
    }


    @GetMapping(value = "/all")
    public ResponseEntity<ResponseDto<Object>> getSchools(@RequestParam(value = "pageNo") int pageNo, @RequestParam(value = "pageSize") int pageSize, @RequestParam(value = "sortBy") String sortBy,  @RequestParam(value = "sortDir") String sortDir)
    {
        return new ResponseEntity<>(ResponseDto.builder().statusCode(HttpStatus.OK.value()).message("Fetched Successfully").data(schoolService.findAll(pageNo,pageSize,sortBy,sortDir)).build(), HttpStatus.OK);
    }

    @GetMapping(value = "/searchall")
    public ResponseEntity<ResponseDto<Object>>  getSchools(@RequestParam(value = "searchText" , required = false) String searchText,@RequestParam(value = "pageNo") int pageNo, @RequestParam(value = "pageSize") int pageSize, @RequestParam(value = "sortBy") String sortBy,  @RequestParam(value = "sortDir") String sortDir)
    {
        return new ResponseEntity<>(ResponseDto.builder().statusCode(HttpStatus.OK.value()).message("Fetched Successfully").data(schoolService.searchAll(searchText,pageNo,pageSize,sortBy,sortDir)).build(), HttpStatus.OK);
    }
}
