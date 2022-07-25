package com.apcompany.webapp.storage;

import com.apcompany.webapp.exception.StorageException;
import org.junit.Ignore;
import org.junit.Test;

public class MapFullNameStorageTest extends AbstractArrayStorageTest {
    public MapFullNameStorageTest() {
        super(new MapFullNameStorage ());
    }

    @Ignore
    @Test(expected = StorageException.class)
    public void saveOverflow() {
    }
}