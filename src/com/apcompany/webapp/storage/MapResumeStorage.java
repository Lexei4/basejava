package com.apcompany.webapp.storage;

import com.apcompany.webapp.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage {

    protected Map<String, Resume> storage = new LinkedHashMap<>();

    private static final Comparator<Resume> RESUME_FULL_NAME_COMPARATOR = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);

    @Override
    protected Object getSearchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected boolean isExist(Object resume) {
        return resume != null;
    }

    @Override
    protected void doDelete(Object key) {
        Resume resumeToDelete = (Resume) key;
        storage.remove(resumeToDelete.getUuid());
    }

    @Override
    protected void doSave(Object key, Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected Resume doGet(Object key) {
        return (Resume) key;
    }

    @Override
    protected void doUpdate(Object key, Resume r) {
        storage.replace(r.getUuid(), r);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public List<Resume> doCopyAll() {
        SortedSet<Resume> keys = new TreeSet<>(RESUME_FULL_NAME_COMPARATOR);
        keys.addAll(storage.values());
        return new ArrayList<>(storage.values());
    }

    @Override
    public int size() {
        return storage.size();
    }
}