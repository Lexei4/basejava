package com.apcompany.webapp.storage;

import com.apcompany.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {

    protected Map<Integer, Resume> storage = new HashMap();

    @Override
    protected int getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        for (Map.Entry entry : storage.entrySet()) {
            if (Objects.equals(searchKey, entry.getValue())) {
                return (Integer) entry.getKey();
            }
        }
        return -1;
    }

    @Override
    protected boolean isExist(String uuid) {
        return storage.containsValue(new Resume(uuid));
    }

    @Override
    protected void doDelete(Object key) {
        storage.remove(key);
    }

    @Override
    protected void doSave(Object key, Resume r) {
        if (!isExist(r.getUuid())) {
            for (Map.Entry entry : storage.entrySet()) {
                key = entry.getKey();
            }
            storage.put((Integer) key + 1, r);
        }
    }

    @Override
    protected Resume doGet(Object key) {
        return storage.get(key);
    }

    @Override
    protected void doUpdate(Object key, Resume r) {
        storage.put((Integer) key, r);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return 0;
    }
}
