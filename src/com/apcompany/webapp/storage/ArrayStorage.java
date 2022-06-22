package com.apcompany.webapp.storage;

import com.apcompany.webapp.exception.StorageException;
import com.apcompany.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected boolean isExist(String uuid){
        if (getSearchKey(uuid) == -1) {
            return false;
        } else
            return true;
    }

    @Override
    protected void doSave(Object key, Resume r) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        }
        storage[size] = r;
        size++;
    }

    @Override
    protected void doDelete(Object index) {
        storage[(Integer)index] = storage[size - 1];
        storage[size - 1] = null;
        size--;
    }

}
