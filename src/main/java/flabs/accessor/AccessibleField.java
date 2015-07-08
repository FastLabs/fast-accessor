package flabs.accessor;


import flabs.functional.Gettable;
import flabs.functional.Nameable;
import flabs.functional.Settable;

public interface AccessibleField<T, P> extends Gettable<T, P>, Settable<T, P>, Nameable {

}
