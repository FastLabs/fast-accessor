package flabs.accessor;


import flabs.test.common.model.amount.Amount;
import flabs.test.common.model.party.Party;
import org.junit.Test;

import static flabs.test.common.model.amount.Amount.newAmount;
import static flabs.test.common.model.amount.Amount.update;
import static flabs.test.common.model.amount.AmountFields.amount;
import static flabs.test.common.model.amount.AmountFields.crcy;
import static flabs.test.common.model.amount.Currency.newCurrency;
import static flabs.test.common.model.amount.CurrencyFilelds.crcyCd;
import static flabs.test.common.model.party.Party.newParty;
import static flabs.test.common.model.party.PartyFields.partyTotalTurnover;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class PipeAccessorTest {

    @Test
    public void test() {
        Amount subjectAmount = newAmount()
                .$(amount, 10.0)
                .$(crcy, newCurrency()
                        .$(crcyCd, "USD"))
                .build();

        final PipeAccessor<Amount, String> x = new PipeAccessor<Amount, String>(crcy).take(crcyCd);
        assertEquals("USD", x.get(subjectAmount));
    }

    @Test
    public void testPosition() {
        final Amount subjectAmount = newAmount()
                .$(amount, 10.0)
                .$(crcy, newCurrency()
                        .$(crcyCd, "USD"))
                .build();

        final Party positionBalance = newParty()
                .$(partyTotalTurnover, subjectAmount).build();
        PipeAccessor<Party, String> accessor = new PipeAccessor<Party, String>(partyTotalTurnover)
                .take(crcy)
                .take(crcyCd);
        assertEquals("USD", accessor.get(positionBalance));
    }

    @Test
    public void testDefaultValueFactory() {
        final Amount amnt = newAmount()
                .$(amount, 10.0)
                .build();

        final PipeAccessor<Amount, String> x = new PipeAccessor<Amount, String>(crcy).take(crcyCd);
        assertNull(x.get(amnt));
        final Amount nullAmount = null;
        assertNull(x.get(nullAmount));
        x.set(amnt, "EUR");

        assertNotNull(amnt.getCurrency());
        assertEquals("EUR", amnt.getCurrency().getCode());
    }

    @Test
    public void testSetter() {
        Amount subjectAmount = newAmount()
                .$(amount, 10.0)
                .$(crcy, newCurrency()
                        .$(crcyCd, "USD"))
                .build();

        PipeAccessor<Amount, String> x = new PipeAccessor<Amount, String>(crcy).take(crcyCd);
        x.set(subjectAmount, "EUR");
        assertEquals("EUR", subjectAmount.getCurrency().getCode());

        PipeAccessor<Amount, Number> y = new PipeAccessor<Amount, Number>(amount);
        y.set(subjectAmount, 20.0);
        assertEquals(20.0, subjectAmount.getAmount());
    }

    @Test
    public void testShorter() {
        Amount subjectAmount = newAmount()
                .$(amount, 10.0)
                .$(crcy, newCurrency()
                        .$(crcyCd, "USD"))
                .build();

        final Amount amnt = update(subjectAmount)
                .$(amount, 11)
                .build();

        assertEquals(11, amnt.getAmount());

    }
}
