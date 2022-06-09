package com.apcompany.webapp.storage;

import com.apcompany.webapp.exception.ExistStorageException;
import com.apcompany.webapp.exception.NotExistStorageException;
import com.apcompany.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    protected List<Resume> storage = new ArrayList<>();

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            throw new NotExistStorageException(uuid);
        }
        return storage.get(index);
    }

    public Resume[] getAll() {
        return storage.toArray(new Resume[storage.size()]);
    }

    public void clear() {
        storage.clear();
        size = 0;
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index == -1) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            storage.set(index, r);
        }
    }

    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(r.getUuid());
        } else {
            doSave(r);
            size++;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            doDelete(index);
            size--;
        }
    }

    protected int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return storage.indexOf(searchKey);
    }

    protected void doSave(Resume r) {
        storage.add(r);
    }

    protected void doDelete(int index) {
        storage.remove(index);
    }

}
