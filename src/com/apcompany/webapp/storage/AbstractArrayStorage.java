package com.apcompany.webapp.storage;

import com.apcompany.webapp.exception.StorageException;
import com.apcompany.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int STORAGE_LIMIT = 10000;
    protected int size = 0;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    @Override
    public int size() {
        return size;
    }



    @Override
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public void save(Resume r) {
        Object index = getNotExistingSearchKey(r.getUuid());
        if (size >= STORAGE_LIMIT) {
            System.out.println("Storage overflow");
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            doSave(index, r);
            size++;
        }
    }

    @Override
    protected Resume doGet(Object key) {
        return storage[(Integer) key];
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected void doUpdate(Object key, Resume r) {
        storage[(Integer) key] = r;
    }

    @Override
    protected boolean isExist(String uuid) {
        if (Integer.parseInt(getSearchKey(uuid).toString()) < 0) {
            return false;
        } else {
            return true;
        }
    }
}
