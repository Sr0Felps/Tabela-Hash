package packages.hash;

@FunctionalInterface
public interface IntKeyExtractor<T> {
    int getKey(T value);
}