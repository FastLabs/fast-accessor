package flabs.functional;

public class Functions {
    
    public static <P> Function <P, P> identity() {
        return new Function<P, P>() {
            @Override
            public P apply(P subject) {
                return subject;
            }
        };
    }
}
