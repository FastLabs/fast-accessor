package flabs.test.common.model.amount;


import flabs.builder.AbstractBuilder;

public class Currency {
    private String id;
    private String code;

    private Currency(String id, String currencyCode) {
        this.id = id;
        this.code = currencyCode;
    }

    public static Builder newCurrency() {
        return new Builder(new Currency());
    }

    public static Builder newCurrency(String id, String code) {
        return new Builder(new Currency(id, code));
    }


    public Currency() {
    }

    public String getId() {
        return id;
    }

    void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    void setCode(String currencyCode) {
        this.code = currencyCode;
    }

    public static class Builder extends AbstractBuilder<Currency, Builder> {

        protected Builder(Currency template) {
            super(template);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}

