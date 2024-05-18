package com.example.enjoytripfinal.domain.place.entity;

import lombok.Getter;

public enum Category {
    TRAVEL("관광지"),
    RESTAURANT("음식점"),
    ACCOMODATION("숙박");

    @Getter
    private final String korean;

    Category(String korean) {
        this.korean = korean;
    }


    public static Category toCategory(String korean) {
        return Category.valueOf(korean);
    }
}
