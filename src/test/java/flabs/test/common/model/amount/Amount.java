package flabs.test.common.model.amount;


import flabs.accessor.Accessor;
import flabs.builder.AbstractBuilder;

public class Amount {

    private Currency currency;
    private Number amount;

    public static Builder newAmount() {
        return new Builder(new Amount());
    }

    public Amount() {
    }

    public Currency getCurrency() {
        return currency;
    }

    void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Number getAmount() {
        return amount;
    }

    void setAmount(Number amount) {
        this.amount = amount;
    }

    public static class Builder extends AbstractBuilder<Amount, Builder> {

        public Builder(Amount template, Accessor<Amount, ?>... mandatory) {
            super(template, mandatory);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    @Override
    public String toString() {
        return "Amount{" +
                "currency=" + currency +
                ", amount=" + amount +
                '}';
    }
}
