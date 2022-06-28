package com.apcompany.webapp.storage;

import com.apcompany.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void doSave(Object key, Resume r) {
        storage[size] = r;
    }

    @Override
    protected void doDelete(Object index) {
        storage[(Integer) index] = storage[size - 1];
        storage[size - 1] = null;
        size--;
    }

}
