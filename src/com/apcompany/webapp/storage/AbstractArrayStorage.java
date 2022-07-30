package com.apcompany.webapp.storage;

import com.apcompany.webapp.exception.StorageException;
import com.apcompany.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage {

    private static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getFullName);

    protected static final int STORAGE_LIMIT = 10000;
    protected int size = 0;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<Resume> doCopyAll() {
        Resume[] resumeArray = Arrays.copyOfRange(storage, 0, size);
        List<Resume> sortedArrayList = new ArrayList<>(List.of(resumeArray));
        return sortedArrayList;
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
        return Integer.parseInt(uuid) >= 0;
    }
}
