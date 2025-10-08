package packages.hash;

public class HashTableEntry<T> {
    public int key;
    public T value;
    public State state = State.EMPTY;
}