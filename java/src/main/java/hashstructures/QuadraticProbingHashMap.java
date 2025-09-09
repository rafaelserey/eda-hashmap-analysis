import java.util.ArrayList;

/**
 * This class implements a hash table using quadratic probing to resolve collisions.
 * Quadratic probing is a collision resolution method where slots are checked in a non-linear sequence
 * (h + 1^2, h + 2^2, h + 3^2, ...) until an empty slot is found.
 *
 * <p>
 * This implementation supports basic operations such as:
 * <ul>
 * <li><b>put(Key key, Value value)</b>: Adds a key-value pair to the hash table.</li>
 * <li><b>get(Key key)</b>: Retrieves the value associated with the given key.</li>
 * <li><b>delete(Key key)</b>: Removes the key and its associated value.</li>
 * <li><b>contains(Key key)</b>: Checks if the hash table contains a given key.</li>
 * </ul>
 * </p>
 *
 * @param <Key> the type of keys maintained by this map
 * @param <Value> the type of mapped values
 */
public class QuadraticProbingHashMap<Key extends Comparable<Key>, Value> extends Map<Key, Value> {
    private float loadFactor;
    private int hsize;
    private Key[] keys;
    private Value[] values;
    private int size;

    public QuadraticProbingHashMap(float loadFactor) {
        this(16, loadFactor);
    }

    @SuppressWarnings("unchecked")
    public QuadraticProbingHashMap(int capacity, float loadFactor) {
        this.hsize = capacity;
        this.loadFactor = loadFactor;
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Object[capacity];
        this.size = 0;
    }

    @Override
    public boolean put(Key key, Value value) {
        if (key == null) {
            return false;
        }

        if ((float) size / hsize >= loadFactor) {
            resize(2 * hsize);
        }

        int i = 0;
        int keyIndex;
        do {
            keyIndex = (int)(hash(key, hsize) + ((long)i*(i+1))/2) % hsize;
            if (keys[keyIndex] == null) {
                keys[keyIndex] = key;
                values[keyIndex] = value;
                size++;
                return true;
            }
            if (key.equals(keys[keyIndex])) {
                values[keyIndex] = value;
                return true;
            }
            i++;
        } while (i < hsize);

        return false;
    }

    @Override
    public Value get(Key key) {
        if (key == null) {
            return null;
        }

        int i = 0;
        int keyIndex;
        do {
            keyIndex = (int)(hash(key, hsize) + ((long)i*(i+1))/2) % hsize;
            if (keys[keyIndex] != null && key.equals(keys[keyIndex])) {
                return values[keyIndex]; 
            }
            i++;
        } while (keys[keyIndex] != null && i < hsize);

        return null; 
    }

    @Override
    public boolean contains(Key key) {
        return get(key) != null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterable<Key> keys() {
        ArrayList<Key> listOfKeys = new ArrayList<>(size);
        for (int i = 0; i < hsize; i++) {
            if (keys[i] != null) {
                listOfKeys.add(keys[i]);
            }
        }
        listOfKeys.sort(Comparable::compareTo);
        return listOfKeys;
    }

    private void resize(int newSize) {
        QuadraticProbingHashMap<Key, Value> tempMap = new QuadraticProbingHashMap<>(newSize, this.loadFactor);
        for (int i = 0; i < this.hsize; i++) {
            if (keys[i] != null) {
                tempMap.put(keys[i], values[i]);
            }
        }
        this.keys = tempMap.keys;
        this.values = tempMap.values;
        this.hsize = tempMap.hsize;
        this.size = tempMap.size;
    }
}