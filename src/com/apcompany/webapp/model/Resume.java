package com.apcompany.webapp.model;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume {

    // Unique identifier
    private final String uuid;

    private final String fullName;

    private final Map<ContactType, String> contacts;

    private final Map<SectionType, AbstractSection> sections;


    public Resume(String uuid, String fullName) {
        this.uuid = Objects.requireNonNull(uuid,"uuid must not be null");
        this.fullName = Objects.requireNonNull(fullName,"fullName must not be null");
    }

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(),fullName);
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Resume)) return false;
        Resume resume = (Resume) o;
        return getUuid().equals(resume.getUuid()) && getFullName().equals(resume.getFullName());
    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + fullName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return uuid + '(' + fullName + ')';
    }
}
