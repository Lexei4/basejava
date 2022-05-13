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

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @After
    public void tearDown() {
        storage = null;
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void get() {
        Assert.assertEquals(new Resume("uuid1"), storage.get(UUID_1));
        Assert.assertEquals(new Resume("uuid2"), storage.get(UUID_2));
        Assert.assertEquals(new Resume("uuid3"), storage.get(UUID_3));
    }

    @Test
    public void getAll() {
        Assert.assertArrayEquals(new Resume[]{
                new Resume("uuid1"),
                new Resume("uuid2"),
                new Resume("uuid3")
        }, storage.getAll());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void clear() {
        storage.clear();
        Resume[] all = storage.getAll();
        Assert.assertEquals(0, storage.size());
        Assert.assertEquals(0, all.length);
    }

    @Test
    public void update() {
        Resume resume = new Resume("uuid1");
        storage.update(resume);
        //ToDO: реализовать проверку обновленного резюме, когда будет больше одного поля
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.get("dummy");
    }

    @Test
    public void save() {
        Resume testResume = new Resume("123");
        storage.save(testResume);
        Assert.assertEquals(testResume, storage.get("123"));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(new Resume(UUID_1));
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        try {
            for (int i = 0; i < 5000; i++) {
                storage.save(new Resume("test_earlyExeption" + i));
            }
        } catch (final RuntimeException e) {
            Assert.fail("Early exception: " + e.getMessage());
        }
        for (int i = 0; i < 5000; i++) {
            storage.save(new Resume("test_overflow" + i));
        }
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete("uuid1");
        storage.get("uuid1");
    }
}