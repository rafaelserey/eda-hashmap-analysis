package data_structures.hashstructures;
import java.util.ArrayList;

/**
 * This class implements a hash table using linear probing to resolve collisions.
 * Linear probing is a collision resolution method where each slot in the hash table is checked in a sequential manner
 * until an empty slot is found.
 *
 * <p>
 * The class allows for storing key-value pairs, where both the key and value are generic types.
 * The key must be of a type that implements the Comparable interface to ensure that the keys can be compared for sorting.
 * </p>
 *
 * <p>
 * This implementation supports basic operations such as:
 * <ul>
 *     <li><b>put(Key key, Value value)</b>: Adds a key-value pair to the hash table. If the key already exists, its value is updated.</li>
 *     <li><b>get(Key key)</b>: Retrieves the value associated with the given key.</li>
 *     <li><b>contains(Key key)</b>: Checks if the hash table contains a given key.</li>
 *     <li><b>size()</b>: Returns the number of key-value pairs in the hash table.</li>
 *     <li><b>keys()</b>: Returns an iterable collection of keys stored in the hash table.</li>
 * </ul>
 * </p>
 *
 * @see <a href="https://en.wikipedia.org/wiki/Linear_probing">Linear Probing Hash Table</a>
 *
 * @param <Key> the type of keys maintained by this map
 * @param <Value> the type of mapped values
 */

public class LinearProbingHashMap<Key extends Comparable<Key>, Value> extends Map<Key, Value> {
    private float loadFactor;
    private int hsize; // size of the hash table
    private Key[] keys; // array to store keys
    private Value[] values; // array to store values
    private int size; // number of elements in the hash table

    // Default constructor initializes the table with a default size of 16
    public LinearProbingHashMap(float loadFactor) {
        this(16, loadFactor);
    }

    @SuppressWarnings("unchecked")
    // Constructor to initialize the hash table with a specified size
    public LinearProbingHashMap(int capacity, float loadFactor) {
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

        if ((float) size / hsize > loadFactor) {
            resize(2 * hsize);
        }

        int keyIndex = hash(key, hsize);
        for (; keys[keyIndex] != null; keyIndex = increment(keyIndex)) {
            if (key.equals(keys[keyIndex])) {
                values[keyIndex] = value;
                return true;
            }
        }

        keys[keyIndex] = key;
        values[keyIndex] = value;
        size++;
        return true;
    }

    @Override
    public Value get(Key key) {
        if (key == null) {
            return null;
        }

        for (int i = hash(key, hsize); keys[i] != null; i = increment(i)) {
            if (key.equals(keys[i])) {
                return values[i];
            }
        }

        return null;
    }

    @Override
    public boolean contains(Key key) {
        return get(key) != null;
    }

    @Override
    int size() {
        return size;
    }

    @Override
    Iterable<Key> keys() {
        ArrayList<Key> listOfKeys = new ArrayList<>(size);
        for (int i = 0; i < hsize; i++) {
            if (keys[i] != null) {
                listOfKeys.add(keys[i]);
            }
        }

        listOfKeys.sort(Comparable::compareTo);
        return listOfKeys;
    }

    private int increment(int i) {
        return (i + 1) % hsize;
    }

    private void resize(int newSize) {
        LinearProbingHashMap<Key, Value> tmp = new LinearProbingHashMap<>(newSize, loadFactor);
        for (int i = 0; i < hsize; i++) {
            if (keys[i] != null) {
                tmp.put(keys[i], values[i]);
            }
        }

        this.keys = tmp.keys;
        this.values = tmp.values;
        this.hsize = newSize;
    }
}
