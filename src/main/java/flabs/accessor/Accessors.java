package flabs.accessor;

import java.util.Collection;
import java.util.Date;

public interface Accessors {
    
	 static <T> Accessor.Builder<T, String> asString() {
        return new Accessor.Builder<>();
    }
	
    static <T> Accessor.Builder<T, Number> asNumber() {
        return new Accessor.Builder<>();
    }
    
    static <T> Accessor.Builder<T, Integer> asInt() {
        return new Accessor.Builder<>();
    }

    static <T, I,  C extends Collection<I>> Accessor.Builder<T, C > asList() {
        return new Accessor.Builder<T, C>().asCollection();
    }
    
     static <T> Accessor.Builder<T, Boolean> asBool() {
        return new Accessor.Builder<>();
    }

     static <T, I extends Enum> Accessor.Builder<T, I> asEnum() {
        return new Accessor.Builder<>();
    }

    static <T, I> Accessor.Builder<T, I> asObject() {
        return new Accessor.Builder<>();
    }

    static <T> Accessor.Builder<T, Date> asDate() {
        return new Accessor.Builder<>();
    }
    
    static <T> Accessor.Builder<T, Character> asCharacter() {
        return new Accessor.Builder<>();
    }
}
