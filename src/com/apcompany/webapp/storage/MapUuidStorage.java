package com.apcompany.webapp.storage;

import com.apcompany.webapp.model.Resume;

import java.util.*;

// TODO implement
// TODO create new MapStorage with search key not uuid
public class MapUuidStorage  extends AbstractStorage<String> {

    protected Map<String, Resume> storage = new LinkedHashMap<>();

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(String uuid) {
        return storage.containsKey(uuid);
    }

    @Override
    protected void doDelete(String key) {
        storage.remove(key);
    }

    @Override
    protected void doSave(String key, Resume r) {
        storage.put(key, r);
    }

    @Override
    protected Resume doGet(String key) {
        return storage.get(key);
    }

    @Override
    protected void doUpdate(String key, Resume r) {
        storage.replace(key, r);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public List<Resume> doCopyAll(){
        return new ArrayList<>(storage.values());
    }

    @Override
    public int size() {
        return storage.size();
    }
}
