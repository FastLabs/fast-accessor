package flabs.test.common.model;


import flabs.accessor.AccessibleField;
import flabs.accessor.Accessors;
import flabs.functional.Gettable;
import flabs.test.common.model.amount.Amount;

public class ShortcutAccessors {

    /**
     * Resolve the currency id from accessible fields. Will return the currency from the first field where it is found.
     *
     * @param accessors Accessible fields list to obtain the currency because can be obtained from different fields
     */
    @SafeVarargs
    public static <T> AccessibleField<T, String> currencyId(final AccessibleField<T, Amount>... accessors) {
        return Accessors.<T>asString().withGetter(new Gettable<T, String>() {
            @Override
            public String get(T target) {
                if (accessors != null && target != null) {
                    for (AccessibleField<T, Amount> accessor : accessors) {
                        final Amount amount = accessor.get(target);
                        if (amount != null && amount.getCurrency() != null) {
                            return amount.getCurrency().getId();
                        }
                    }
                }
                return null;
            }
        }).end();
    }

    /**
     * Resolve the amount value from accessible fields, normally fields of amount type.
     *
     * @param accessor Accessible field to obtain the amount value
     */
    public static <T> AccessibleField<T, Number> amountValue(final AccessibleField<T, Amount> accessor) {
        return Accessors.<T>asNumber().withGetter(new Gettable<T, Number>() {
            @Override
            public Number get(T target) {
                if (accessor != null && target != null) {
                    final Amount amount = accessor.get(target);
                    if (amount != null) {
                        return amount.getAmount();
                    }
                }
                return null;
            }
        }).end();
    }
}
