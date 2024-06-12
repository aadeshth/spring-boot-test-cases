package com.springboot.crudoperation.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RequestDto<T>{
    T data;
    List<T> listData;
}
