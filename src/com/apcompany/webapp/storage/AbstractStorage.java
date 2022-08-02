package com.apcompany.webapp.storage;

import com.apcompany.webapp.exception.ExistStorageException;
import com.apcompany.webapp.exception.NotExistStorageException;
import com.apcompany.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage<SK> implements Storage {

    private static final Comparator<Resume> RESUME_FULL_NAME_COMPARATOR = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);

    public Resume get(String uuid) {
        SK key = getExistingSearchKey(uuid);
        return doGet(key);
    }

    public void update(Resume r) {
        SK key = getExistingSearchKey(r.getUuid());
        doUpdate(key, r);
    }

    public void save(Resume r) {
        SK key = getNotExistingSearchKey(r.getUuid());
        doSave(key, r);
    }

    public void delete(String uuid) {
        SK key = getExistingSearchKey(uuid);
        doDelete(key);
    }

    public List<Resume> getAllSorted() {
        List<Resume> resumes = doCopyAll();
        resumes.sort(RESUME_FULL_NAME_COMPARATOR);
        return resumes;
    }

    protected SK getExistingSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    protected SK getNotExistingSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract SK getSearchKey(String uuid);

    protected abstract boolean isExist(SK uuid);

    protected abstract void doDelete(SK key);

    protected abstract void doSave(SK key, Resume r);

    protected abstract Resume doGet(SK key);

    protected abstract void doUpdate(SK key, Resume r);

    protected abstract List<Resume> doCopyAll();
}
