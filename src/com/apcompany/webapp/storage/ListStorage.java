package com.apcompany.webapp.storage;

import com.apcompany.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    protected List<Resume> storage = new ArrayList<>();

//    @Override
//    protected Object getSearchKey(String uuid) {
//        return getIndex(uuid);
//    }


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
        doSave(r);
    }

    protected void doSave(Resume r) {
        storage.add(r);
    }

    protected int getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return storage.indexOf(searchKey);
    }

    @Override
    protected boolean isExist(String uuid) {
        if (getSearchKey(uuid) == -1) {
            return false;
        } else
            return true;
    }

    public Resume[] getAll() {
        return storage.toArray(new Resume[storage.size()]);
    }

    @Override
    public int size() {
        return 0;
    }

    public void clear() {
        storage.clear();
    }
}
