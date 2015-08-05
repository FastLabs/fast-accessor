package flabs.accessor;


import static flabs.accessor.CollectionAccessor.add;
import static flabs.test.common.model.amount.CurrencyFilelds.crcyId;
import static flabs.test.common.model.party.PartyFields.partyClassifications;
import static org.junit.Assert.*;

import flabs.test.common.model.party.PartyFields;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static flabs.accessor.MapAccessor.map$;
import static flabs.accessor.TransformAccessors.$;
import static flabs.test.common.model.amount.AmountFields.crcy;
import static flabs.test.common.model.amount.CurrencyFilelds.crcyCd;

public class MapAccessorTest {

    @Test
    public void testSimpleAccess() {
        AccessibleField<Map, String> currencyCode = map$($(crcy, crcyCd)),
                currencyId = map$($(crcy, crcyId));
        final Map<String, ? super Object> amount = new HashMap<>();
        final Map<String, String> currency = new HashMap<>();
        amount.put("Currency", currency);
        currency.put("Id", "c1");
        currency.put("Code", "cd1");

        final String cCode = currencyCode.get(amount),
                cId = currencyId.get(amount);
        assertEquals("cd1", cCode);
        assertEquals("c1", cId);

        assertEquals("c1", map$(crcyId).get(currency));


    }

    @Test
    public void testMapSetters() {
        final Map<String, String> map1 = new HashMap<>();
        map$(crcyId).set(map1, "cid2");
        assertEquals("cid2", map1.get("Id"));
        //test the embedded map
        final Map<String, ?> map2 = new HashMap<>();
        map$($(crcy, crcyCd)).set(map2, "cd1");
        assertNotNull(map2.get("Currency"));
        assertEquals("cd1", map$($(crcy, crcyCd)).get(map2));
    }

    @Test
    public void testCollectionSetters() {
        final Map<String, ?> map = new HashMap<>();
        map$($(partyClassifications, add())).set(map, "class1");

        System.out.println(map);

    }


}
