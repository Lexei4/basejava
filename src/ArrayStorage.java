/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i <= this.size(); i++) {
            storage[i] = null;
        }
    }

    void save(Resume r) {
        if (this.size() == 0) {
            storage[0] = r;
            return;
        }
        int i = 0;
        while (storage[i] != null) i++;
        storage[i] = r;
    }

    Resume get(String uuid) {
        for (int i = 0; i < this.size(); i++) {
            if (storage[i].toString().equals(uuid)) return storage[i];
        }
        /**
         * to avoid NullPointerException -  not to return "null"
         */
        return new Resume();
    }

    void delete(String uuid) {
        for (int i = 0; i < this.size(); i++) {
            if (storage[i].toString().equals(uuid)) {
                do {
                    storage[i] = storage[i + 1];
                    i++;
                } while (storage[i] != null);
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {

        Resume[] arrayToGet = new Resume[this.size()];

        int i = 0;
        while (storage[i] != null) {
            arrayToGet[i] = storage[i];
            i++;
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
