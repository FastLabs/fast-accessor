package accessor;

import java.util.Date;

public class Accessors {
    
	public static <T> Accessor.Builder<T, String> asString() {
        return new Accessor.Builder<>();
    }
	
    public static <T> Accessor.Builder<T, Number> asNumber() {
        return new Accessor.Builder<>();
    }
    
    public static <T> Accessor.Builder<T, Integer> asInt() {
        return new Accessor.Builder<>();
    }
    
    public static <T> Accessor.Builder<T, Boolean> asBool() {
        return new Accessor.Builder<>();
    }

    public static <T, I extends Enum> Accessor.Builder<T, I> asEnum() {
        return new Accessor.Builder<>();
    }

    public static <T, I> Accessor.Builder<T, I> asObject() {
        return new Accessor.Builder<>();
    }

    public static <T> Accessor.Builder<T, Date> asDate() {
        return new Accessor.Builder<>();
    }
    
    public static <T> Accessor.Builder<T, Character> asCharacter() {
        return new Accessor.Builder<>();
    }
}
