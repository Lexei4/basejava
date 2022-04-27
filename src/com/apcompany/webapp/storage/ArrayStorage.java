package com.apcompany.webapp.storage;

import com.apcompany.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size;

    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    public void update(Resume r) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(r.getUuid())) {
                storage[i] = r;
                return;
            }
        }
        System.out.println("ERROR: resume " + r + " is not present in storage");
    }

    public void save(Resume r) {
        if (size != 0) {
            for (int i = 0; i < size; i++) {
                if (storage[i].toString().equals(r.getUuid())) {
                    System.out.println("ERROR: resume with uuid + " + r.getUuid() + "is already present");
                    return;
                }
                storage[size] = r;
            }
        } else {
            storage[size] = r;
        }
        size++;
    }

    public Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                return storage[i];
            }
        }
        System.out.println("ERROR: no resume with uuid " + uuid + " found in storagew");
        return null;
    }

    public void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                for (; i < size - 1; i++) {
                    storage[i] = storage[i + 1];
                }
                size--;
                return;
            }
        }
        System.out.println("ERROR: cannot delete  message with uuid " + uuid + " . No message found");
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] resumes = new Resume[size];

        for (int i = 0; i < size; i++) {
            resumes[i] = storage[i];
        }

        return resumes;
    }

    public int size() {
        return size;
    }
}
