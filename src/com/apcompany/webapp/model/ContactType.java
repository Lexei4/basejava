package com.apcompany.webapp.model;

public enum ContactType {
    PHONENUMBER("Тел.:"),
    SKYPE("Skype:"),
    EMAIL("Почта.:"),
    LINKEDIN("Профиль LinkedIn"),
    GITHUB("Профиль GitHub"),
    STACKOVERFLOW("Профиль Stackoverflow");

    private String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
