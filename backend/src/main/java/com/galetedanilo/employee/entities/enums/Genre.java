package com.galetedanilo.employee.entities.enums;

public enum Genre {

    FEMALE("FEMALE"), MALE("MALE");

    private final String genre;

    Genre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }
}
