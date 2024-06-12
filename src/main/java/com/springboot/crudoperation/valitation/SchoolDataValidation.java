package com.springboot.crudoperation.valitation;

import com.springboot.crudoperation.exception.RequestValidationException;
import com.springboot.crudoperation.model.SchoolDto;

public class SchoolDataValidation {

    public static void requestValidation(SchoolDto schoolDto) {
        if(schoolDto.getName() == null)
            throw new RequestValidationException("Name should not be null");
    }
}
