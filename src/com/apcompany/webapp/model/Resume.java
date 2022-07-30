package com.apcompany.webapp.model;

import java.util.Objects;
import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume {

    // Unique identifier
    private final String uuid;

    private final String fullName;

    public Resume(String uuid, String fullName) {
        this.uuid = Objects.requireNonNull(uuid,"uuid must not be null");
        this.fullName = Objects.requireNonNull(fullName,"fullName must not be null");
    }

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(),fullName);
    }
//
//    public Resume() {
//        this.uuid = UUID.randomUUID().toString();
//        fullName = "No_Name";
//    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        return uuid.equals(resume.uuid);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public String toString() {
        return uuid;
    }
}
