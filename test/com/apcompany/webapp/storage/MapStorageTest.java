package com.apcompany.webapp.storage;

import com.apcompany.webapp.exception.StorageException;
import org.junit.Ignore;
import org.junit.Test;

public class MapStorageTest extends AbstractArrayStorageTest {
    public MapStorageTest() {
        super(new MapStorage());
    }

    @Ignore
    @Test(expected = StorageException.class)
    public void saveOverflow() {
    }
}