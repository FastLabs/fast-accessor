package flabs.test.common.model.party;


import flabs.accessor.Accessor;
import flabs.accessor.Accessors;
import flabs.test.common.model.amount.Amount;

import java.util.ArrayList;
import java.util.List;

public class PartyFields {

    public static final Accessor<Party, String> partyId = Accessors.<Party>asString()
            .withName("Id")
            .withSetter(Party::setId)
            .withGetter(Party::getId)
            .end();

    public static final Accessor<Party, Amount> partyTotalTurnover = Accessors.<Party, Amount>asObject()
            .withName("Total Turnover")
            .withSetter(Party::setTotalTurnover)
            .withGetter(Party::getTotalTurnover)
            .withDefault(Amount::new);

    public static final Accessor<Party, List<String>> partyClassifications = Accessors.<Party, String, List<String>>asList()
            .withName("Classifications")
            .withGetter(Party::getClassifications)
            .withSetter(Party::setClassifications)
            .withDefault(ArrayList<String>::new);
}
