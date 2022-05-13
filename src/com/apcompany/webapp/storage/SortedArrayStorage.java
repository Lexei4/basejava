package com.apcompany.webapp.storage;

import com.apcompany.webapp.exception.StorageException;
import com.apcompany.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void doSave(int index, Resume r) {
        int position = Math.abs(index) - 1;
        try {
            System.arraycopy(storage, position, storage, position + 1, size);
        }
        catch (ArrayIndexOutOfBoundsException e){
            throw new StorageException (e.getMessage(),r.getUuid());
        }
        storage[position] = r;
    }

    @Override
    protected void doDelete(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}