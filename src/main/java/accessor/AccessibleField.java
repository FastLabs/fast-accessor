package accessor;


import functional.Gettable;
import functional.Nameable;
import functional.Settable;

public interface AccessibleField<T, P> extends Gettable<T, P>, Settable<T, P>, Nameable {

}
