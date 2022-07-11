package com.apcompany.webapp.storage;

import com.apcompany.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    protected List<Resume> storage = new ArrayList<>();

    @Override
    protected void doDelete(Object index) {
        int indexToRemove = (Integer) index;
        storage.remove(indexToRemove);
    }

    @Override
    protected Resume doGet(Object key) {
        return storage.get((Integer) key);
    }

    @Override
    protected void doUpdate(Object key, Resume r) {
        storage.set((Integer) key, r);
    }

    @Override
    protected void doSave(Object key, Resume r) {
        storage.add(r);
    }

    protected Object getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return storage.indexOf(searchKey);
    }

    @Override
    protected boolean isExist(String uuid) {
        if (Integer.parseInt(getSearchKey(uuid).toString()) == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Resume[] getAll() {
        return storage.toArray(new Resume[storage.size()]);
    }

    @Override
    public int size() {
        return storage.size();
    }

    public void clear() {
        storage.clear();
    }
}
