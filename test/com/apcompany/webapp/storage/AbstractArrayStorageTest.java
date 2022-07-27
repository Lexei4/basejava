package com.apcompany.webapp.storage;

import com.apcompany.webapp.exception.StorageException;
import com.apcompany.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Test;

public class AbstractArrayStorageTest extends AbstractStorageTest{
    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        storage.clear();
        try {
            for (int i = 0; i < STORAGE_LIMIT; i++) {
                storage.save(new Resume("test_earlyExeption" + i));
            }
        } catch (final RuntimeException e) {
            Assert.fail("Early exception: " + e.getMessage());
        }
        storage.save(new Resume("test_overflow"));
    }
}
