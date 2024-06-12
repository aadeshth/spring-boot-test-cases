package com.springboot.crudoperation.mapper;

import com.springboot.crudoperation.entity.ClassRoom;
import com.springboot.crudoperation.entity.School;
import com.springboot.crudoperation.model.ClassRoomDto;
import com.springboot.crudoperation.model.SchoolDto;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SchoolMapper {

    public static List<SchoolDto> mapToListSchoolDto(List<School> schools)
    {
        return schools.stream()
                .map(school ->
                        SchoolDto.builder().classRooms(convertClassRoomListToDtoList(school.getClassRoomList())).name(school.getName()).id(school.getId()).address(school.getAddress()).dressCodeColors(Collections.singletonList(school.getDressCodeColors())).createdBy(school.getCreatedBy()).updatedBy(school.getUpdatedBy()).createdDate(school.getCreatedDate()).updatedDate(school.getUpdatedDate()).build())
                .collect(Collectors.toList());
    }

    public static SchoolDto mapToSchoolDto(School school)
    {
        return SchoolDto.builder().name(school.getName()).id(school.getId()).address(school.getAddress()).dressCodeColors(Collections.singletonList(school.getDressCodeColors())).createdBy(school.getCreatedBy()).updatedBy(school.getUpdatedBy()).createdDate(school.getCreatedDate()).updatedDate(school.getUpdatedDate()).classRooms(convertClassRoomListToDtoList(school.getClassRoomList())).build();
    }

    public static School mapToSchool(SchoolDto schoolDto, String user) {
        School school = new School();
        school.setName(schoolDto.getName());
        school.setAddress(schoolDto.getAddress());
        school.setDressCodeColors(String.valueOf(schoolDto.getDressCodeColors()));
        school.setCreatedBy(user);
        school.setCreatedDate(Date.valueOf(LocalDate.now()));
        school.setClassRoomList(convertClassRoomDtoToEntity(schoolDto.getClassRooms()));
        return school;
    }

    private static List<ClassRoom> convertClassRoomDtoToEntity(List<ClassRoomDto> aDto) {
        return aDto.stream()
                .map(dto -> ClassRoom.builder().floor(dto.getFloor()).grade(dto.getGrade()).strength(dto.getStrength()).name(dto.getName()).build())
                .collect(Collectors.toList());
    }
    private static List<ClassRoomDto> convertClassRoomListToDtoList(List<ClassRoom> classRooms) {
        return classRooms.stream()
                .map(classRoom -> ClassRoomDto.builder().floor(classRoom.getFloor()).grade(classRoom.getGrade()).strength(classRoom.getStrength()).name(classRoom.getName()).build())
                .collect(Collectors.toList());
    }
}
