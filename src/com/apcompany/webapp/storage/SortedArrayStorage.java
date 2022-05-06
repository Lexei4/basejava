package com.apcompany.webapp.storage;

import com.apcompany.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            System.out.println("Resume " + r.getUuid() + " already exist");
        } else if (size >= STORAGE_LIMIT) {
            System.out.println("Storage overflow");
        } else {
            System.arraycopy(storage, Math.abs(index) - 1, storage, Math.abs(index), size);
            storage[Math.abs(index) - 1] = r;
            size++;
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("ERROR: cannot delete  message with uuid " + uuid + " . No message found");
        } else if (index == 0) {
            size--;
            System.arraycopy(storage, 1, storage, 0, size);
            System.out.println("Size is: " + size);
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