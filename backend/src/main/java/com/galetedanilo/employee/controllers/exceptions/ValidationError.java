package com.galetedanilo.employee.controllers.exceptions;


import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{

    @Getter
    private List<FieldMessage> fieldMessageList = new ArrayList<>();

    public void addFieldMessage(String fieldName, String message) {
        fieldMessageList.add(new FieldMessage(fieldName, message));
    }
}
