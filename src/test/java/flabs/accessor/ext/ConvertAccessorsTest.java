package flabs.accessor.ext;


import flabs.test.common.model.amount.Amount;
import flabs.test.common.model.amount.Currency;
import org.junit.Test;

import static flabs.accessor.ext.NavAccessors.$;
import static flabs.test.common.model.amount.AmountFields.amount;
import static flabs.test.common.model.amount.AmountFields.crcy;
import static flabs.test.common.model.amount.Currency.newCurrency;
import static flabs.test.common.model.amount.CurrencyFilelds.crcyCd;
import static org.junit.Assert.assertEquals;

public class ConvertAccessorsTest {
    @Test
    public void testAsEnumAccessor() {
        final Amount a = Amount.newAmount()
                .with(amount, 10.0)
                .with(crcy, newCurrency("1", "usd"))
                .build();


       /* final TdhPositionBalance pb = newPositionBalance()
                .with(mtmAmount, newAmount()
                        .with(currency, newCurrency()
                                .with(currencyCode, "usd")
                                .with(currencyId, "1")))
                .build();*/
        String code = $(crcy, crcyCd).get(a);
        assertEquals("usd", code);
    }
}
