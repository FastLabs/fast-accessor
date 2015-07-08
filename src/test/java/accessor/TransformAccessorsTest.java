package accessor;


import com.db.treasury.tch.core.beans.domain.positionbalance.TdhPositionBalance;
import org.junit.Test;

import static com.db.treasury.tch.core.beans.domain.Amount.Fields.currency;
import static com.db.treasury.tch.core.beans.domain.Amount.newAmount;
import static com.db.treasury.tch.core.beans.domain.Currency.Fields.currencyCode;
import static com.db.treasury.tch.core.beans.domain.Currency.Fields.currencyId;
import static com.db.treasury.tch.core.beans.domain.Currency.newCurrency;
import static com.db.treasury.tch.core.beans.domain.positionbalance.TdhPositionBalance.Fields.mtmAmount;
import static com.db.treasury.tch.core.beans.domain.positionbalance.TdhPositionBalance.newPositionBalance;
import static com.db.treasury.tch.core.utils.accessor.TransformAccessors.$;
import static org.junit.Assert.assertEquals;

public class TransformAccessorsTest {
    @Test
    public void testPipeAccessor() {
        final TdhPositionBalance pb = newPositionBalance()
                .with(mtmAmount, newAmount()
                        .with(currency, newCurrency()
                                .with(currencyCode, "usd")
                                .with(currencyId, "1")))
                .build();
        String code = $(mtmAmount, currency, currencyCode).get(pb);
        assertEquals("usd", code);
    }
}
