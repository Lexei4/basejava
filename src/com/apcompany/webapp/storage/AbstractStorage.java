package com.apcompany.webapp.storage;

import com.apcompany.webapp.exception.ExistStorageException;
import com.apcompany.webapp.exception.NotExistStorageException;
import com.apcompany.webapp.exception.StorageException;
import com.apcompany.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected static final int STORAGE_LIMIT = 10000;
    protected int size = 0;

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        Object key = getSearchKey(uuid);
        if ((Integer) key < 0) {
            throw new NotExistStorageException(uuid);
        }
        return doGet(key);
    }

    public void update(Resume r) {
        Object key = getSearchKey(r.getUuid());
        if ((Integer) key < 0) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            doUpdate((Integer) key, r);
        }
    }

    public void save(Resume r) {
        Object key = getSearchKey(r.getUuid());
        if ((Integer) key >= 0) {
            throw new ExistStorageException(r.getUuid());
        } else if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            doSave((Integer) key, r);
            size++;
        }
    }

    public void delete(String uuid) {
        Object key = getSearchKey(uuid);
        if ((Integer) key < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            doDelete(key);
        }
    }

    protected abstract Object getSearchKey(String uuid);

    protected abstract void doDelete(Object index);

    protected abstract void doSave(int index, Resume r);

    protected abstract Resume doGet(Object key);

    protected abstract void doUpdate(int index, Resume r);
}
