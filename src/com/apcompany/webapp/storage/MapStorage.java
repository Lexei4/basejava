package com.apcompany.webapp.storage;

import com.apcompany.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {

    protected Map<String, Resume> storage = new HashMap();

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(String uuid) {
        return storage.containsKey(uuid);
    }

    @Override
    protected void doDelete(Object key) {
        storage.remove((String) key);
    }

    @Override
    protected void doSave(Object key, Resume r) {
        storage.put((String) key, r);
    }

    @Override
    protected Resume doGet(Object key) {
        return storage.get(key);
    }

    @Override
    protected void doUpdate(Object key, Resume r) {
        storage.put((String) key, r);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        Resume[] resumeArray = new Resume[storage.size()];
//        System.out.println(Arrays.toString(resumeArray));
        int i = 0;
        for (Map.Entry <String, Resume> entry: storage.entrySet())
        {
            resumeArray[i] = entry.getValue();
            i++;
        }
//        System.out.println(Arrays.toString(storage.entrySet().toArray()));
        return resumeArray;
    }

    @Override
    public int size() {
        return storage.size();
    }
}
