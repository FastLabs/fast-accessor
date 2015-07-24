package flabs.test.common.model.country;


import flabs.accessor.Accessor;
import flabs.accessor.Accessors;

public class CountryFields {
    public static final Accessor<Country, String> countryId = Accessors.<Country>asString()
            .withName("Position Balance ID")
            .withSetter(Country::setId)
            .withGetter(Country::getId)
            .end();
}
