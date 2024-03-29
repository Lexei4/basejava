package com.apcompany.webapp;

import com.apcompany.webapp.model.Resume;
import com.apcompany.webapp.storage.SortedArrayStorage;
import com.apcompany.webapp.storage.Storage;

/**
 * Test for your ArrayStorage implementation
 */
public class MainTestArrayStorage {
    static final Storage ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume("uuid1");
        Resume r2 = new Resume("uuid2");
        Resume r3 = new Resume("uuid3");
        Resume r4 = new Resume("dummyUpdate");

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);

//
        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
//        System.out.println("Size: " + ARRAY_STORAGE.size());
//
//        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));
//
//        ARRAY_STORAGE.update(r3);
//        System.out.println("dummy UPDATE result: ");
//        ARRAY_STORAGE.update(r4);
        printAll();
        ARRAY_STORAGE.delete(r1.getUuid());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());

        for (int i =0; i < 10010; i++){
            Resume r_loop = new Resume("120" + i);
            ARRAY_STORAGE.save(r_loop);
        }
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAllSorted()) {
            System.out.println(r);
        }
    }
}
