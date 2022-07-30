package com.apcompany.webapp.storage;

import com.apcompany.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

    private static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getUuid);

    @Override
    protected void doSave(Object key, Resume r) {
        int position = -(Integer) key - 1;
        System.arraycopy(storage, position, storage, position + 1, size - position);
        storage[position] = r;
    }

    @Override
    protected void doDelete(Object index) {
        System.arraycopy(storage, (Integer) index + 1, storage, (Integer) index, size - (Integer) index - 1);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid, "dummy");
        return Arrays.binarySearch(storage, 0, size, searchKey, RESUME_COMPARATOR);
    }
}