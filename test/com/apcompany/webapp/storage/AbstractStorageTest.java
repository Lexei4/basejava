package com.apcompany.webapp.storage;

import com.apcompany.webapp.exception.ExistStorageException;
import com.apcompany.webapp.exception.NotExistStorageException;
import com.apcompany.webapp.model.Resume;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractStorageTest {

    protected Storage storage;
    private static final Resume RESUME_1 = new Resume("uuid1","name1");
    private static final Resume RESUME_2 = new Resume("uuid2", "name2");
    private static final Resume RESUME_3 = new Resume("uuid3","name3");
    private static final Resume RESUME_NOT_EXIST = new Resume("dummy");

    public static final int STORAGE_LIMIT = AbstractArrayStorage.STORAGE_LIMIT;

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
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
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test
    public void getAllSorted() {
        Resume[] resumeArray = {RESUME_1, RESUME_2, RESUME_3};
        Assert.assertArrayEquals(resumeArray, storage.getAllSorted().toArray());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(RESUME_NOT_EXIST.getUuid());
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void update() {
        storage.update(RESUME_1);
        Assert.assertSame(RESUME_1, storage.get(RESUME_1.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(RESUME_NOT_EXIST);
    }

    @Test
    public void save() {
        Resume testResume = new Resume("123", "dummy");
        storage.save(testResume);
        assertGet(testResume);
        assertSize(4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete("uuid1");
        storage.get("uuid1");
        assertSize(2);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(RESUME_NOT_EXIST.getUuid());
    }

}