package com.apcompany.webapp.storage;

import com.apcompany.webapp.exception.ExistStorageException;
import com.apcompany.webapp.exception.NotExistStorageException;
import com.apcompany.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    public Resume get(String uuid) {
        Object key = getExistingSearchKey(uuid);
        return doGet(key);
    }

    public void update(Resume r) {
        Object key = getExistingSearchKey(r.getUuid());
        doUpdate(key, r);
    }

    public void save(Resume r) {
        Object key = getNotExistingSearchKey(r.getUuid());
        doSave(key, r);
    }

    public void delete(String uuid) {
        Object key = getExistingSearchKey(uuid);
        doDelete(key);
    }

    protected Object getExistingSearchKey(String uuid) {
        if (!isExist(uuid)) {
            throw new NotExistStorageException(uuid);
        }
        return getSearchKey(uuid);
    }

    protected Object getNotExistingSearchKey(String uuid) {
        if (isExist(uuid)) {
            throw new ExistStorageException(uuid);
        }
        return getSearchKey(uuid);
    }

    protected abstract int getSearchKey(String uuid);

    protected abstract boolean isExist(String uuid);

    protected abstract void doDelete(Object key);

    protected abstract void doSave(Object key, Resume r);

    protected abstract Resume doGet(Object key);

    protected abstract void doUpdate(Object key, Resume r);
}
