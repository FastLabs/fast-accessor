package flabs.accessor.util;



import flabs.accessor.AccessibleField;
import flabs.accessor.ProxyAccessor;

import java.util.Date;




public class ToSqlDateAccessor<T> extends ProxyAccessor<T, Date, java.sql.Date> {
    public ToSqlDateAccessor(AccessibleField<T, Date> accessor) {
        super(accessor);
    }

    @Override
    public java.sql.Date get(T target) {
        final Date simpleDate = accessor.get(target);
        if (simpleDate != null) {
            return new java.sql.Date(simpleDate.getTime());
        }
        return null;
    }

    @Override
    public void set(T destination, java.sql.Date value) {
        if(value != null) accessor.set(destination, new java.util.Date(value.getTime()));
    }

    @Override
    public java.sql.Date defaultValue() {
        return new java.sql.Date(new Date().getTime());
    }
}
