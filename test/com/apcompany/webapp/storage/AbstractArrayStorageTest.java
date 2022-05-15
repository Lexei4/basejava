package com.apcompany.webapp.storage;

import com.apcompany.webapp.exception.ExistStorageException;
import com.apcompany.webapp.exception.NotExistStorageException;
import com.apcompany.webapp.exception.StorageException;
import com.apcompany.webapp.model.Resume;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {

    private Storage storage;
    private static final Resume resume1 = new Resume("uuid1");
    private static final Resume resume2 = new Resume("uuid2");
    private static final Resume resume3 = new Resume("uuid3");
    private static final Resume UUID_NOT_EXIST = new Resume("dummy");

    private static final int STORAGE_LIMIT = AbstractArrayStorage.STORAGE_LIMIT;

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(resume1);
        storage.save(resume2);
        storage.save(resume3);
    }

    @After
    public void tearDown() {
        storage = null;
    }

    public void assertSize(int expectedValue) {
        Assert.assertEquals(expectedValue, storage.size());
    }

    public void assertGet(Resume resume) {
        Assert.assertEquals(resume, storage.get(resume.getUuid()));
    }

    @Test
    public void size() {
        assertSize(3);
    }

    @Test
    public void get() {
        assertGet(resume1);
        assertGet(resume2);
        assertGet(resume3);
    }

    @Test
    public void getAll() {
        Resume[] resumeArray = {resume1, resume2, resume3};
        Assert.assertArrayEquals(resumeArray, storage.getAll());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(UUID_NOT_EXIST.getUuid());
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void update() {
        storage.update(resume1);
        Assert.assertSame(resume1, storage.get(resume1.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(UUID_NOT_EXIST);
    }

    @Test
    public void save() {
        Resume testResume = new Resume("123");
        storage.save(testResume);
        assertGet(testResume);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(resume1);
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

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete("uuid1");
        storage.get("uuid1");
        assertSize(2);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExsst() {
        storage.delete(UUID_NOT_EXIST.getUuid());
    }

}