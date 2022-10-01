package com.apcompany.webapp.model;

import java.util.List;
import java.util.Objects;

public class CompanySection extends AbstractSection{
    private  final List <Company> companies;

    public CompanySection(List<Company> companies) {
        this.companies = companies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompanySection)) return false;
        CompanySection that = (CompanySection) o;
        return Objects.equals(companies, that.companies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companies);
    }
}
