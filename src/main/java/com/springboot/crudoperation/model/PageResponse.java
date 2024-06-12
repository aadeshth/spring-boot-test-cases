package com.springboot.crudoperation.model;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse<T> {
    Integer statusCode;
    String message;

    List<Object> listData;

    int pageNo;
    int pageSize;

    int totalPages;

    boolean isLast;

    long totalRecords;
}
