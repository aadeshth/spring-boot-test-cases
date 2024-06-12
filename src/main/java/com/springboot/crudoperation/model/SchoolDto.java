package com.springboot.crudoperation.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@Builder
public class SchoolDto {
    Long id;
    String name;
    String address;
    List<String> dressCodeColors;

    Date createdDate;

    String createdBy;
    Date updatedDate;

    String updatedBy;

    List<ClassRoomDto> classRooms;



}
