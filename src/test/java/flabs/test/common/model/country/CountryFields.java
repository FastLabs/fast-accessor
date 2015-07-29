package flabs.test.common.model.country;


import flabs.accessor.Accessor;
import flabs.accessor.Accessors;

public class CountryFields {
    public static final Accessor<Country, String> ctryId = Accessors.<Country>asString()
            .withName("Id")
            .withSetter(Country::setId)
            .withGetter(Country::getId)
            .end();

    public static final Accessor<Country, String> ctryCd = Accessors.<Country>asString()
            .withName("Code")
            .withSetter(Country::setCode)
            .withGetter(Country::getCode)
            .end();
}
