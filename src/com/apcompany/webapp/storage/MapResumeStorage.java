package com.apcompany.webapp.storage;

import com.apcompany.webapp.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage {

    protected Map<String, Resume> storage = new LinkedHashMap<>();

    private static final Comparator<Resume> RESUME_FULL_NAME_COMPARATOR = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);

    @Override
    protected Object getSearchKey(String uuid) {
                if (storage.get(uuid) == null) {
                    return uuid;
                } else {
                    return storage.get(uuid);
                }
    }

    @Override
    protected boolean isExist(Object resume) {
        Resume newResume =  new Resume(resume.toString(), "dummy");
        return storage.containsKey(newResume.getUuid());
    }

    @Override
    protected void doDelete(Object key) {
        Resume newResume =  new Resume(key.toString(), "dummy");
        storage.remove(newResume.getUuid());
    }

    @Override
    protected void doSave(Object key, Resume r) {
        storage.put((String) key, r);
    }

    @Override
    protected Resume doGet(Object key) {
        return (Resume)key;
    }

    @Override
    protected void doUpdate(Object key, Resume r) {
        Resume newResume =  new Resume(key.toString(), "dummy");
        storage.replace(newResume.getUuid(), r);
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