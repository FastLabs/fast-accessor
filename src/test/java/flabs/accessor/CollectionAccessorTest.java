package flabs.accessor;


import flabs.test.common.model.party.Party;
import org.junit.Test;

import static flabs.accessor.CollectionAccessor.add;
import static flabs.accessor.TransformAccessors.$;
import static flabs.test.common.model.party.Party.newParty;
import static flabs.test.common.model.party.PartyFields.partyClassifications;
import static org.junit.Assert.assertEquals;

public class CollectionAccessorTest {

    @Test
    public  void testAddAccessor() {
        final Party p = newParty()
                .$($(partyClassifications, add()), "abc")
                .$($(partyClassifications, add()), "cde")
                .build();
        assertEquals("[abc, cde]", p.getClassfications().toString());
    }
}
