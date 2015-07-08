package flabs.test.common.model.amount;


public class Currency {
    private String id;
    private String currencyCode;

    public Currency(String id, String currencyCode) {
        this.id = id;
        this.currencyCode = currencyCode;
    }

    public String getId() {
        return id;
    }

    void setId(String id) {
        this.id = id;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}

