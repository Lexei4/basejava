/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size;

    void clear() {
        for (int i = 0; i <= size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume r) {
        for (int i = 0; i <= size; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                size++;
                return;
            }
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i <= size; i++) {
            if (size != 0 && storage[i].toString().equals(uuid)) return storage[i];
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i <= size; i++) {
            if (size != 0 && storage[i].toString().equals(uuid)) {
                for (; storage[i] != null; i++) {
                    storage[i] = storage[i + 1];

                }
                size--;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {

        Resume[] arrayToGet = new Resume[this.size()];

        for (int i = 0; storage[i] != null; i++) {
            arrayToGet[i] = storage[i];
        }

        return arrayToGet;
    }

    int size() {
        int counterOfResumes = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) counterOfResumes++;
        }

        return counterOfResumes;
    }
}
