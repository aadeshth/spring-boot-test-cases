package com.springboot.crudoperation.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity(name = "ClassRoom")
@Table(name = "ClassRoom")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ClassRoom extends BaseEntity {

    String name;

    int grade;

    int floor;

    int strength;

}
