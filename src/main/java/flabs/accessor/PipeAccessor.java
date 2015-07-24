package flabs.accessor;

import java.util.ArrayList;
import java.util.List;


public class PipeAccessor<T, P> implements AccessibleField<T, P> {
    private List<AccessibleField<?, ?>> pipe = new ArrayList<>();
    private String name;
    public PipeAccessor(AccessibleField<T, ?> current) {
        this.pipe.add(current);
    }

    PipeAccessor(AccessibleField current, AccessibleField next) {
        this.pipe.add(current);
        this.pipe.add(next);
    }

    @Override
    public String getName() {
        if(name == null) {
            final StringBuilder result = new StringBuilder();
            for(AccessibleField<?, ?> accessor : pipe) {
                if(result.length() > 0) {
                    result.append(" ");
                }
                result.append(accessor.getName());
            }
            name = result.toString();
        }
        return name;
    }

    @Override
    public P get(final T target) {
        Object x = target;
        if (x != null) {
            for (AccessibleField field : pipe) {
                if (x == null) {
                    return null;
                }
                x = field.get(x);
            }
            return (P) x;
        }
        return null;
    }

    @Override
    public void set(T destination, P value) {
        Object x = destination;
        if (x != null) {
            if (pipe.size() == 1) {
                final AccessibleField f = pipe.get(0);
                f.set(destination, value);
            } else {
                final AccessibleField settableField = pipe.get(pipe.size() - 1);
                for (int i = 0; i < pipe.size() - 1; i++) {
                    AccessibleField field = pipe.get(i);
                    x = field.get(x);
                    if (x == null) {
                        x = field.defaultValue();
                        if (x == null) {
                            return;
                        } else {
                            field.set(destination, x);
                        }
                    }
                }
                settableField.set(x, value);
            }
        }
    }

    public AccessibleField<T, Number> asNumber() {
        return TransformAccessors.asNumber(this);
    }

    public List<AccessibleField<?,?>> getPipe(){
        return pipe;
    }

    public PipeAccessor<T, P> take(AccessibleField<?, ?> next) {    	
        pipe.add(next);
        return this;
    }

    @Override
    public P defaultValue() {
        return null;
    }
}