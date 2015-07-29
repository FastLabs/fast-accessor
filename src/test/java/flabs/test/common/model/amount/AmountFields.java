package flabs.test.common.model.amount;


import flabs.accessor.Accessor;
import flabs.accessor.Accessors;

public class AmountFields {

    public static final Accessor<Amount, Number> amount = Accessors.<Amount>asNumber()
            .withName("Amount")
            .withSetter(Amount::setAmount)
            .withGetter(Amount::getAmount)
            .end();

    public static final Accessor<Amount, Currency> crcy = Accessors.<Amount, Currency>asObject()
            .withName("Currency")
            .withSetter(Amount::setCurrency)
            .withGetter(Amount::getCurrency)
            .withDefault(Currency::new);
}
