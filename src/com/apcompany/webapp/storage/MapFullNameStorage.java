package com.apcompany.webapp.storage;

import com.apcompany.webapp.model.Resume;

import java.util.*;

public class MapFullNameStorage extends AbstractStorage {

    protected Map<String, Resume> storage = new LinkedHashMap<>();

    private static final Comparator<Resume> RESUME_FULL_NAME_COMPARATOR = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);


    @Override
    protected Object getSearchKey(String fullName) {
        return fullName;
    }

    @Override
    protected boolean isExist(String fullName) {
        return storage.containsKey(fullName);
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
    public List<Resume> doCopyAll(){
        SortedSet<Resume> keys = new TreeSet<>(RESUME_FULL_NAME_COMPARATOR);
        keys.addAll(storage.values());
        return new ArrayList<>(storage.values());
    }

    @Override
    public int size() {
        return storage.size();
    }
}