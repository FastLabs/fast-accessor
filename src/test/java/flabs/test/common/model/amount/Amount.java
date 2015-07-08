package flabs.test.common.model.amount;


public class Amount {

    private Currency currency;
    private Number amount;

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
}
