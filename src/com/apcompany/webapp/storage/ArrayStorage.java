package com.apcompany.webapp.storage;

import com.apcompany.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void doSave(Integer key, Resume r) {
        storage[size] = r;
    }

    @Override
    protected void doDelete(Integer index) {
        storage[index] = storage[size - 1];
        storage[size - 1] = null;
        size--;
    }

}
