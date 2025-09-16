package data_structures.hashstructures;

public abstract class Map<Key, Value> {

    abstract boolean put(Key key, Value value);

    abstract Value get(Key key);

    abstract Iterable<Key> keys();

    abstract int size();

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public int hash(Key key, int size) {
        return (key.hashCode() & Integer.MAX_VALUE) % size;
    }
}
