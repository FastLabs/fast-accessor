package flabs.accessor.ext.sql;



import flabs.accessor.AccessibleField;
import flabs.accessor.Accessor;

import java.sql.Date;
import java.sql.Timestamp;


public class PersistenceAccessors {

    public static <T> AccessibleField<T, Date> asSqlDate(Accessor<T, java.util.Date> accessor) {
        return new ToSqlDateAccessor<>(accessor);
    }
    public static <T> AccessibleField<T, Timestamp> asTime(Accessor<T, java.util.Date> accessor) {
        return new ToTimestampAccessor<>(accessor);
    }
}
