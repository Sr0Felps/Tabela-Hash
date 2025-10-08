package main.java.com.aula.hash;

@FunctionalInterface
public interface IntKeyExtractor<T> {
    int getKey(T value);
}