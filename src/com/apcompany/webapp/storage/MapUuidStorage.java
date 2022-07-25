package com.apcompany.webapp.storage;

import com.apcompany.webapp.model.Resume;

import java.util.*;

// TODO implement
// TODO create new MapStorage with search key not uuid
public class MapUuidStorage  extends AbstractStorage {

    protected Map<String, Resume> storage = new LinkedHashMap<>();

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
        return storage.get((String) key);
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
    public List<Resume> getAllSorted(){
        return new ArrayList<>(storage.values());
    }

    @Override
    public int size() {
        return storage.size();
    }
}
