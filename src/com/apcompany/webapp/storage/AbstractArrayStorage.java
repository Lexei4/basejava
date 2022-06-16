package com.apcompany.webapp.storage;

import com.apcompany.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {

    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    @Override
    protected Object getSearchKey(String uuid) {
        return getIndex(uuid);
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    @Override
    protected Resume doGet(Object key) {
        return storage[(Integer) key];
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected void doUpdate(int key, Resume r) {
        storage[key] = r;
    }
}
