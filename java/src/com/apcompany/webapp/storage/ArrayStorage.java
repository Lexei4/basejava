package com.apcompany.webapp.storage;

import com.apcompany.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final int STORAGE_LIMIT = 1000;
    private final Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size;

    private int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        int index = findIndex(r.getUuid());
        if (index != -1) {
            storage[index] = r;
        } else {
            System.out.println("ERROR: resume " + r + " is not present in storage");
        }
    }

    public void save(Resume r) {
        int index = findIndex(r.getUuid());
        if (size == STORAGE_LIMIT) {
            System.out.println("ERROR: storage size of " + STORAGE_LIMIT + " is reached");
        } else if (index != -1) {
            System.out.println("ERROR: resume with uuid + " + r.getUuid() + "is already present");
        } else {
            storage[size] = r;
            size++;
        }
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index == -1) {
            System.out.println("ERROR: resume " + uuid + " is not present in storage");
            return null;
        }
        return storage[index];
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index != -1) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
            return;
        }
        System.out.println("ERROR: cannot delete  message with uuid " + uuid + " . No message found");
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }
}
