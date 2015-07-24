package flabs.accessor.util;



import flabs.accessor.AccessibleField;
import flabs.accessor.ProxyAccessor;

import java.sql.Timestamp;
import java.util.Date;




public class ToTimestampAccessor<T> extends ProxyAccessor<T, Date, Timestamp> {
    public ToTimestampAccessor(AccessibleField<T, Date> accessor) {
        super(accessor);
    }

    @Override
    public Timestamp get(T target) {
        final Date simpleDate = accessor.get(target);
        if (simpleDate != null) {
            return new Timestamp(simpleDate.getTime());
        }
        return null;
    }

    @Override
    public void set(T destination, Timestamp value) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public Timestamp defaultValue() {
        throw new UnsupportedOperationException("not yet implemented");
    }
}
