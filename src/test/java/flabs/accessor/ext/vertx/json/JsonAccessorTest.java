package flabs.accessor.ext.vertx.json;


import flabs.accessor.Accessor;
import io.vertx.core.json.JsonObject;
import org.junit.Test;

import static flabs.accessor.ext.NavAccessors.$;
import static flabs.accessor.ext.vertx.json.JsonAccessor.js$;
import static flabs.accessor.ext.vertx.json.JsonAccessor.newJs;
import static org.junit.Assert.assertEquals;

public class JsonAccessorTest {

    private Accessor<JsonObject, String> currency = js$("currency", () -> "");
    private Accessor<JsonObject, Double> amount = js$("amount", () -> 0.0);
    private Accessor<JsonObject, JsonObject> tradeAmount = js$("tradeAmount", JsonObject::new);



    @Test
    public void testIt() {
       final JsonObject x =  newJs()
               .with($(tradeAmount, currency), "usd")
               .with($(tradeAmount, amount), 0.0)
               .build();
        assertEquals("usd", $(tradeAmount, currency).get(x));
    }
}
