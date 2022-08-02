package com.apcompany.webapp.storage;

import com.apcompany.webapp.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage<Resume> {

    protected Map<String, Resume> storage = new LinkedHashMap<>();

    private static final Comparator<Resume> RESUME_FULL_NAME_COMPARATOR = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);

    @Override
    protected Resume getSearchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected boolean isExist(Resume resume) {
        return resume != null;
    }

    @Override
    protected void doDelete(Resume key) {
        storage.remove(key.getUuid());
    }

    @Override
    protected void doSave(Resume key, Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected Resume doGet(Resume key) {
        return key;
    }

    @Override
    protected void doUpdate(Resume key, Resume r) {
        storage.replace(r.getUuid(), r);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public List<Resume> doCopyAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public int size() {
        return storage.size();
    }
}