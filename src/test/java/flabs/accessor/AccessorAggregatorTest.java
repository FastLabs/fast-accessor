package flabs.accessor;


import flabs.accessor.utils.AccessorAggregator;
import com.db.treasury.tch.core.beans.domain.positionbalance.TdhPositionBalance;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.db.treasury.tch.core.beans.domain.Amount.Fields.amount;
import static com.db.treasury.tch.core.beans.domain.Amount.newAmount;
import static com.db.treasury.tch.core.beans.domain.positionbalance.TdhPositionBalance.Fields.*;
import static com.db.treasury.tch.core.beans.domain.positionbalance.TdhPositionBalance.newPositionBalance;
import static com.db.treasury.tch.core.beans.domain.positionbalance.TdhPositionBalance.updatePositionBalance;
import static org.junit.Assert.assertEquals;

public class AccessorAggregatorTest {

    private List<TdhPositionBalance> positions () {
        final TdhPositionBalance p1 = newPositionBalance()
                .with(positionBalanceId, "1")
                .build(),
                p2 = newPositionBalance()
                        .with(positionBalanceId, "2")
                        .build(),
                p11 = newPositionBalance()
                        .with(positionBalanceId, "1").build();
        return asList(p1, p2, p11);
    }

    @Test
    public void testSimpleAggregation() {
        final AccessorAggregator<TdhPositionBalance, List<TdhPositionBalance>> aggregator = new AccessorAggregator<TdhPositionBalance, List<TdhPositionBalance>>(notionalAmount, positionBalanceId) {
            @Override
            public List<TdhPositionBalance> aggregated() {
                return unique;
            }
        };
        final List<TdhPositionBalance> p = positions();
        aggregator.aggregate(p.get(0), p.get(1));
        aggregator.aggregate(p.get(2));
        final List<TdhPositionBalance> accumulated = aggregator.aggregated();
        assertEquals(2, accumulated.size());

        updatePositionBalance(accumulated.get(0)).with(notionalAmount, newAmount().with(amount, 10));
        updatePositionBalance(accumulated.get(1)).with(notionalAmount, newAmount().with(amount, 11));

        aggregator.merge();

        assertEquals(10, p.get(0).getNotional().getAmount());
        assertEquals(11, p.get(1).getNotional().getAmount());
        assertEquals(10, p.get(2).getNotional().getAmount());

    }

    @Test
    public void testMultipleUpdates() {
        final Set<AccessibleField<TdhPositionBalance, ?>> updatedFields  = new HashSet<>();
        updatedFields.add(notionalAmount);
        updatedFields.add(ifrsValuation);

        final AccessorAggregator<TdhPositionBalance, List<TdhPositionBalance>> aggregator = new AccessorAggregator<TdhPositionBalance, List<TdhPositionBalance>>(updatedFields, positionBalanceId) {
            @Override
            public List<TdhPositionBalance> aggregated() {
                return unique;
            }
        };

        final List<TdhPositionBalance> p = positions();
        aggregator.aggregate(p.get(0), p.get(1));
        aggregator.aggregate(p.get(2));

        final List<TdhPositionBalance> accumulated = aggregator.aggregated();
        assertEquals(2, accumulated.size());

        updatePositionBalance(accumulated.get(0)).with(notionalAmount, newAmount().with(amount, 10));
        updatePositionBalance(accumulated.get(0)).with(ifrsValuation, newAmount().with(amount, 11));
        updatePositionBalance(accumulated.get(1)).with(notionalAmount, newAmount().with(amount, 12));
        updatePositionBalance(accumulated.get(1)).with(ifrsValuation, newAmount().with(amount, 13));

        aggregator.merge();

        assertEquals(10, p.get(0).getNotional().getAmount());
        assertEquals(11, p.get(0).getIfrsValuation().getAmount());
        assertEquals(12, p.get(1).getNotional().getAmount());
        assertEquals(13, p.get(1).getIfrsValuation().getAmount());
        assertEquals(10, p.get(2).getNotional().getAmount());
    }
}
