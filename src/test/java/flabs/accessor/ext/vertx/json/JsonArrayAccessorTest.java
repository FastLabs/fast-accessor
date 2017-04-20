package flabs.accessor.ext.vertx.json;


import io.vertx.core.json.JsonArray;
import org.junit.Test;

import static flabs.accessor.ext.vertx.json.JsonArrayAccessor.first;
import static org.junit.Assert.assertEquals;

public class JsonArrayAccessorTest {

    @Test
    public void simpleFirstTest() {
        final JsonArray arr = new JsonArray()
                .add("1")
                .add("2");
        assertEquals("1", first().get(arr));
    }

    @Test
    public void simplePredicateTest() {
        final JsonArray arr = new JsonArray()
                .add("1")
                .add("2");

        assertEquals("2", first("2"::equals).get(arr));
    }
}
