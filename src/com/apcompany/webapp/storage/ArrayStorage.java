package com.apcompany.webapp.storage;

import com.apcompany.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final int STORAGE_LIMIT = 1000;
    private Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size;

    private int searchElementIndex(String uuid) {
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

        int elementIndex = searchElementIndex(r.getUuid());

        if (elementIndex != -1) {
            storage[elementIndex] = r;
            return;
        } else {
            System.out.println("ERROR: resume " + r + " is not present in storage");
        }

    }

    public void save(Resume r) {
        int elementIndex = searchElementIndex(r.getUuid());

        if (size == STORAGE_LIMIT) {
            System.out.println("ERROR: storage size of " + STORAGE_LIMIT + " is reached");
            return;
        } else if (elementIndex != -1) {
            System.out.println("ERROR: resume with uuid + " + r.getUuid() + "is already present");
        } else {
            storage[size] = r;
            size++;
        }
    }

    public Resume get(String uuid) {
        int elementIndex = searchElementIndex(uuid);

        if (elementIndex != -1) {
            return storage[elementIndex];
        }

        System.out.println("ERROR: resume " + uuid + " is not present in storage");
        return null;
    }

    public void delete(String uuid) {
        int elementIndex = searchElementIndex(uuid);

        if (elementIndex != -1) {
            for (int i = elementIndex; i < size - 1; i++) {
                storage[i] = storage[i + 1];
            }
            size--;
            return;
        }

        System.out.println("ERROR: cannot delete  message with uuid " + uuid + " . No message found");
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {

        Resume[] resumes = Arrays.copyOfRange(storage, 0, size);

        return resumes;
    }

    public int size() {
        return size;
    }
}
