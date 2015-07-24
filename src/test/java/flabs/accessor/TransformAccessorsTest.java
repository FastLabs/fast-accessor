package flabs.accessor;



import org.junit.Test;


import static flabs.accessor.TransformAccessors.$;
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
