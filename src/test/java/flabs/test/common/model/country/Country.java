package flabs.test.common.model.country;


import flabs.accessor.Accessor;
import flabs.builder.AbstractBuilder;

public class Country {
    private String id;
    private String code;

    public Country(String id, String code) {
        this.id = id;
        this.code = code;
    }

    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    void setId(String id) {
        this.id = id;
    }

    void setCode(String code) {
        this.code = code;
    }

    public static class Builder extends AbstractBuilder<Country, Builder> {

        protected Builder(Country template, Accessor<Country, ?>... mandatory) {
            super(template, mandatory);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }
}
