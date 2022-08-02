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

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
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
