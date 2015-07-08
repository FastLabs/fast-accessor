package accessor.utils;


import accessor.AccessibleField;

import static org.apache.commons.lang.StringUtils.isNotBlank;

public class AccessorHashcoder<T> {

    private final AccessibleField<T, ?>[] accessors;

    public AccessorHashcoder(AccessibleField<T, ?> ... accessors) {
        this.accessors = accessors;
    }

    public int evaluateHash(T entity) {
        final StringBuilder accumulated = new StringBuilder();
        if(accessors != null) {
            for(AccessibleField accessor: accessors){
                final Object value = accessor.get(entity);
                if(value != null  ) {
                    if(isNotBlank(value.toString())) {
                        accumulated.append(value.toString());
                    }
                }
            }
        }
        return accumulated.toString().hashCode();
    }    
}
