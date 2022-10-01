package com.apcompany.webapp.model;

import java.util.List;
import java.util.Objects;

public class Company {

    private final List <Period> periods;
    private final String companyName;
    private final String websiteName;

    public Company(List<Period> periods, String companyName, String websiteName) {
        this.periods = periods;
        this.companyName = companyName;
        this.websiteName = websiteName;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getWebsiteName() {
        return websiteName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company)) return false;
        Company company = (Company) o;
        return Objects.equals(getPeriods(), company.getPeriods()) && Objects.equals(getCompanyName(), company.getCompanyName()) && Objects.equals(getWebsiteName(), company.getWebsiteName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPeriods(), getCompanyName(), getWebsiteName());
    }
}
