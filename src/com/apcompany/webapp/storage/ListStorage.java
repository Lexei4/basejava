package com.apcompany.webapp.storage;

import com.apcompany.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    protected List<Resume> storage = new ArrayList<>();

    @Override
    protected Object getSearchKey(String uuid) {
        return getIndex(uuid);
    }

    @Override
    protected void doDelete(Object index) {
        int indexToRemove = (Integer) index;
        storage.remove(indexToRemove);
        size--;
    }

    @Override
    protected Resume doGet(Object key) {
        return storage.get((Integer) key);
    }

    @Override
    protected void doUpdate(int index, Resume r) {
        storage.set(index, r);
    }

    @Override
    protected void doSave(int index, Resume r) {
        doSave(r);
    }

    protected void doSave(Resume r) {
        storage.add(r);
    }

    protected int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return storage.indexOf(searchKey);
    }

    public Resume[] getAll() {
        return storage.toArray(new Resume[storage.size()]);
    }

    public void clear() {
        storage.clear();
        size = 0;
    }
}
