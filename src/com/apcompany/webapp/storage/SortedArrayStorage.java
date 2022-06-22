package com.apcompany.webapp.storage;

import com.apcompany.webapp.exception.StorageException;
import com.apcompany.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected boolean isExist(String uuid){
        if (getSearchKey(uuid) < 0) {
            return false;
        } else
            return true;
    }

    @Override
    protected void doSave(Object key, Resume r) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        }
        int position = -(Integer)key - 1;
        System.arraycopy(storage, position, storage, position + 1, size - position);
        storage[position] = r;
        size++;
    }

    @Override
    protected void doDelete(Object index) {
        System.arraycopy(storage, (Integer) index + 1, storage, (Integer) index, size - (Integer) index - 1);
    }

    @Override
    protected int getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

}