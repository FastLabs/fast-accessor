package flabs.functional;

@FunctionalInterface
public interface Gettable <T, R> {
    R get(T target);
}
