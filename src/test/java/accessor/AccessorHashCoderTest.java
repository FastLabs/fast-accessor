package accessor;


import accessor.utils.AccessorHashcoder;
import com.db.treasury.tch.core.beans.domain.positionbalance.TdhPositionBalance;
import org.junit.Test;

import static com.db.treasury.tch.core.beans.domain.positionbalance.TdhPositionBalance.Fields.positionBalanceId;
import static com.db.treasury.tch.core.beans.domain.positionbalance.TdhPositionBalance.Fields.positionSnapshot;
import static com.db.treasury.tch.core.beans.domain.positionbalance.TdhPositionBalance.newPositionBalance;
import static com.db.treasury.tch.core.beans.domain.snapshot.TdhSnapshot.Fields.snapshotId;
import static com.db.treasury.tch.core.beans.domain.snapshot.TdhSnapshot.newSnapshot;
import static com.db.treasury.tch.core.utils.accessor.TransformAccessors.from;
import static org.junit.Assert.assertEquals;

public class AccessorHashCoderTest {

    @Test
    public void testHashCoder() {
        final AccessorHashcoder<TdhPositionBalance> hashcoder = new AccessorHashcoder<>(positionBalanceId);
        final TdhPositionBalance position = newPositionBalance()
                .with(positionBalanceId, "1").build();

        final String against = "1";
        int resulted = hashcoder.evaluateHash(position);
        assertEquals(against.hashCode(), resulted);
    }

    @Test
    public void testMultipleFields() {
        final AccessorHashcoder<TdhPositionBalance> hashcoder = new AccessorHashcoder<>(positionBalanceId, from(positionSnapshot).take(snapshotId));
        final TdhPositionBalance position = newPositionBalance()
                .with(positionBalanceId, "1")
                .with(positionSnapshot, newSnapshot()
                        .with(snapshotId, "2"))
                .build();

        final String against = "12";
        int resulted = hashcoder.evaluateHash(position);
        assertEquals(against.hashCode(), resulted);
    }
}
