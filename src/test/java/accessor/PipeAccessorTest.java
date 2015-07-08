package accessor;


import com.db.treasury.tch.core.beans.domain.Amount;
import com.db.treasury.tch.core.beans.domain.positionbalance.TdhPositionBalance;
import org.junit.Test;

import static com.db.treasury.tch.core.beans.domain.Amount.Fields.amount;
import static com.db.treasury.tch.core.beans.domain.Amount.Fields.currency;
import static com.db.treasury.tch.core.beans.domain.Currency.Fields.currencyCode;
import static com.db.treasury.tch.core.beans.domain.Currency.newCurrency;
import static com.db.treasury.tch.core.beans.domain.positionbalance.TdhPositionBalance.Fields.ifrsValuation;
import static com.db.treasury.tch.core.beans.domain.positionbalance.TdhPositionBalance.newPositionBalance;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PipeAccessorTest {

    @Test
    public void test() {
        Amount subjectAmount = Amount.newAmount()
                .with(amount, 10.0)
                .with(currency, newCurrency()
                        .with(currencyCode, "USD"))
                .build();

        final PipeAccessor<Amount, String> x = new PipeAccessor<Amount, String>(currency).take(currencyCode);
        assertEquals("USD", x.get(subjectAmount));
    }

    @Test
    public void testPosition() {
        final Amount subjectAmount = Amount.newAmount()
                .with(amount, 10.0)
                .with(currency, newCurrency()
                        .with(currencyCode, "USD"))
                .build();

        final TdhPositionBalance positionBalance = newPositionBalance()
                .with(ifrsValuation, subjectAmount).build();
        PipeAccessor<TdhPositionBalance, String> accessor = new PipeAccessor<TdhPositionBalance, String>(ifrsValuation)
                .take(currency)
                .take(currencyCode);
        assertEquals("USD", accessor.get(positionBalance));
    }

    @Test
    public void testNull() {
        final Amount subjectAmount = Amount.newAmount()
                .with(amount, 10.0)
                .build();

        final PipeAccessor<Amount, String> x = new PipeAccessor<Amount, String>(currency).take(currencyCode);
        assertNull(x.get(subjectAmount));
        final Amount nullAmount = null;
        assertNull(x.get(nullAmount));        
        x.set(subjectAmount,"EUR");
        assertNull(subjectAmount.getCurrency());
    }
    
    @Test
    public void testSetter(){
    	Amount subjectAmount = Amount.newAmount()
                .with(amount, 10.0)
                .with(currency, newCurrency()
                        .with(currencyCode, "USD"))
                .build();

        PipeAccessor<Amount, String> x = new PipeAccessor<Amount, String>(currency).take(currencyCode);
        x.set(subjectAmount,"EUR");
        assertEquals("EUR",subjectAmount.getCurrency().getCurrencyCode());
        
        PipeAccessor<Amount, Number> y = new PipeAccessor<Amount, Number>(amount);
        y.set(subjectAmount, 20.0);
        assertEquals(20.0,subjectAmount.getAmount());
    }
}
