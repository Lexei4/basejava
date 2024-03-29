package com.apcompany.webapp.model;

import java.util.List;
import java.util.Objects;

public class ListSection extends AbstractSection{
    private final List<String> content;

    public ListSection(List<String> content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ListSection)) return false;
        ListSection that = (ListSection) o;
        return Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }
}
