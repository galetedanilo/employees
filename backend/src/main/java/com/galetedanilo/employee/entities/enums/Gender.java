package com.galetedanilo.employee.entities.enums;

public enum Gender {

    FEMALE("FEMALE"), MALE("MALE");

    private final String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }
}
