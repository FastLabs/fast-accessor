package accessor.utils;

import accessor.AccessibleField;
import com.db.treasury.tch.core.beans.functional.Aggregable;

import java.util.*;


public abstract class AccessorAggregator<I, O> implements Aggregable<I, O> {
    protected Map<Integer, List<I>> aggregated = new HashMap<>();
    protected  final Set<AccessibleField<I, ?>> updatedFields;
    protected List<I> unique = new ArrayList<>();
    protected AccessorHashcoder <I> hashCodder;

    public AccessorAggregator(AccessibleField<I, ?> updatedField, AccessibleField<I, ?>... keyFields) {
        this.updatedFields = new HashSet<>();
        this.updatedFields.add(updatedField);
        hashCodder = new AccessorHashcoder<>(keyFields);
    }

    public AccessorAggregator(Collection<AccessibleField<I, ?>> updateable, AccessibleField<I, ?>... keyFields) {
        this.updatedFields = new HashSet<>();
        this.updatedFields.addAll(updateable);
        hashCodder = new AccessorHashcoder<>(keyFields);
    }

    protected AccessorAggregator(Set<AccessibleField<I, ?>> updatedFields, AccessibleField<I, ?>... keyFields) {
        this.updatedFields = updatedFields;
        hashCodder = new AccessorHashcoder<>(keyFields);
    }

    public void aggregate(I ... elements) {
        for(I element: elements) {
            final Integer key = hashCodder.evaluateHash(element);
            List<I> existing = aggregated.get(key);
            if (existing == null) {
                existing = new ArrayList<>();//TODO: investigate the data to understand the size
                existing.add(element);
                unique.add(element);
                aggregated.put(key, existing);
            } else {
                existing.add(element);
            }
        }
    }
    
    public void aggregate(List<I> elements) {
        for(I element: elements) {
            aggregate(element);
        }
    }
    
    public void merge() {
        if(updatedFields != null) {
            for(I requested: unique) {
                final Integer key = hashCodder.evaluateHash(requested);
                final List<I> subjects = aggregated.get(key);
                if(subjects != null) {
                    apply(requested, subjects);
                }

            }
        }
    }
    @SuppressWarnings("unchecked")
    private void apply(I source, List<I> subjects) {
        for(AccessibleField updatedField: updatedFields) {
            final Object value = updatedField.get(source);
            for (I subject : subjects) {
                updatedField.set(subject, value);
            }
        }
    }

    public abstract O aggregated();
    

}
