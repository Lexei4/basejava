package com.apcompany.webapp.storage;

public abstract class AbstractStorage implements Storage {
    protected int size = 0;

    public int size() {
        return size;
    }

    protected abstract int getIndex(String uuid);
    protected abstract void doDelete(int index);
}
