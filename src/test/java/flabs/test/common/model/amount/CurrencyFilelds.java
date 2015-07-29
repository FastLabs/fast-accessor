package flabs.test.common.model.amount;


import flabs.accessor.Accessor;
import flabs.accessor.Accessors;

public class CurrencyFilelds {
    public static final Accessor<Currency, String> crcyId = Accessors.<Currency>asString()
            .withName("Id")
            .withSetter(Currency::setId)
            .withGetter(Currency::getId)
            .end();
    public static final Accessor<Currency, String> crcyCd = Accessors.<Currency>asString()
            .withName("Code")
            .withSetter(Currency::setCode)
            .withGetter(Currency::getCode)
            .end();
}
