package com.springboot.crudoperation.model;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto<T> {
    Integer statusCode;
    String message;

    T data;
    List<Object> listData;

}
