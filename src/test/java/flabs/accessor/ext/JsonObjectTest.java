package flabs.accessor.ext;


import flabs.accessor.Accessor;
import io.vertx.core.json.JsonObject;
import org.junit.Test;

import static flabs.accessor.TransformAccessors.$;
import static flabs.accessor.ext.JsonAccessor.js$;
import static flabs.accessor.ext.JsonAccessor.newJson;

public class JsonObjectTest {

    Accessor<JsonObject, String> currency = js$("currency", () -> "");
    Accessor<JsonObject, Double> amount = js$("amount", () -> 0.0);
    Accessor<JsonObject, JsonObject> tradeAmount = js$("tradeAmount", JsonObject::new);



    @Test
    public void testIt() {
       final JsonObject x =  newJson()
               .with($(tradeAmount, currency), "usd")
               .with($(tradeAmount, amount), 0.0)
               .build();

        System.out.println(x);
    }
}
