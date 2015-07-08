package builder;

import com.db.treasury.tch.core.beans.exception.TdhException;
import com.db.treasury.tch.core.beans.functional.Either;
import com.db.treasury.tch.core.utils.accessor.Accessor;

public interface Converter<T> {
    Either<TdhException.Builder, T> convert(T originalValue); 
    TdhException.Builder customizeException(TdhException.Builder ex, Accessor<?, T> accessor, String fieldName);
}
