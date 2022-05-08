package com.apcompany.webapp.storage;

import com.apcompany.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void saveInStorage(int index, Resume r) {
        System.arraycopy(storage, Math.abs(index) - 1, storage, Math.abs(index), size);
        storage[Math.abs(index) - 1] = r;
        size++;
    }

    @Override
    protected void deleteInFromStorage(int index) {
        if (index == 0) {
            size--;
            System.arraycopy(storage, 1, storage, 0, size);
        } else {
            System.arraycopy(storage, 0, storage,
                    0, index - 1);
            System.arraycopy(storage, index + 1,
                    storage, index,
                    size);
            size--;
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}