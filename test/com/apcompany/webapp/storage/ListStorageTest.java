package com.apcompany.webapp.storage;

import com.apcompany.webapp.exception.StorageException;
import org.junit.Ignore;
import org.junit.Test;

public class ListStorageTest  extends AbstractArrayStorageTest {
    public ListStorageTest() {
        super(new ListStorage());
    }

    @Ignore
    @Test(expected = StorageException.class)
    public void saveOverflow() {
    }
}