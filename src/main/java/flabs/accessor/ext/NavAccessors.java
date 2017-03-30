package flabs.accessor.ext;


import flabs.accessor.AccessibleField;
import flabs.accessor.PipeAccessor;

public class NavAccessors {
    public static <T> PipeAccessor<T,String> from(final AccessibleField<T, ?> first) {
        return new PipeAccessor<>(first);
    }

    public static <T, P, X> PipeAccessor<T, X> $(final AccessibleField<T, P> first, AccessibleField<P, X> second ) {
        return new PipeAccessor<>(first, second);
    }

    public static <T, P, X, Y> PipeAccessor<T, Y> $(final AccessibleField<T, P> first, AccessibleField<P, X> second, AccessibleField<X, Y>  third) {
        return new PipeAccessor<>(new PipeAccessor<>(first, second), third);
    }

    public static <T, P, X, Y, Z> PipeAccessor<T, Z> $(final AccessibleField<T, P> first, AccessibleField<P, X> second, AccessibleField<X, Y>  third, AccessibleField<Y, Z>  fourth ) {
        final PipeAccessor<T, X> p1 = new PipeAccessor<>(first, second);
        final PipeAccessor<X, Z> p2 = new PipeAccessor<>(third, fourth);
        return new PipeAccessor<>(p1, p2);
    }
}
