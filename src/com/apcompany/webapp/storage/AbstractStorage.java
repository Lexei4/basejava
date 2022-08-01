package com.apcompany.webapp.storage;

import com.apcompany.webapp.exception.ExistStorageException;
import com.apcompany.webapp.exception.NotExistStorageException;
import com.apcompany.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    private static final Comparator<Resume> RESUME_FULL_NAME_COMPARATOR = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);

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

    public List<Resume> getAllSorted() {
        List<Resume> resumes = doCopyAll();
        resumes.sort(RESUME_FULL_NAME_COMPARATOR);
        return resumes;
    }

    protected Object getExistingSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    protected Object getNotExistingSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract Object getSearchKey(String uuid);

    protected abstract boolean isExist(Object uuid);

    protected abstract void doDelete(Object key);

    protected abstract void doSave(Object key, Resume r);

    protected abstract Resume doGet(Object key);

    protected abstract void doUpdate(Object key, Resume r);

    protected abstract List<Resume> doCopyAll();
}
