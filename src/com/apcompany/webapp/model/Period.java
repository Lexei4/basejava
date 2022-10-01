package com.apcompany.webapp.model;

import java.util.Objects;

public class Period {
    private  final String dateFrom;
    private final String  dateTo;
    private final String title;
    private final String description;

    public Period(String dateFrom, String dateTo, String title, String description) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.title = title;
        this.description = description;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Period)) return false;
        Period period = (Period) o;
        return Objects.equals(dateFrom, period.dateFrom) && Objects.equals(dateTo, period.dateTo) && Objects.equals(title, period.title) && Objects.equals(description, period.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateFrom, dateTo, title, description);
    }
}
