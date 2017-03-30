package flabs.accessor;

import java.util.Collection;


public class CollectionAccessor {

    public static <C extends Collection<T>, T> AccessibleField<C, T> add() {
        return Accessors.<C, T>asObject()
                .withSetter(Collection::add)
                .end();
    }
}
