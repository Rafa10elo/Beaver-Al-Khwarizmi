package Model;

public interface KeyExtractor <T, K extends Comparable<K>> {
    K getKey(T object);
}
