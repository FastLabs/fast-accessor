package flabs.test.common.model.party;


import flabs.builder.AbstractBuilder;
import flabs.test.common.model.amount.Amount;

import java.util.Collection;
import java.util.List;

public class Party {

    private String id;
    private Amount totalTurnover;
    private List<String> classfications;


    private Party() {
    }

    public List<String> getClassfications() {
        return classfications;
    }

    public void setClassfications(List<String> classif) {
        this.classfications = classif;
    }

    public static Builder newParty() {
        return new Builder(new Party());
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTotalTurnover(Amount totalTurnover) {
        this.totalTurnover = totalTurnover;
    }

    public String getId() {
        return id;
    }

    public Amount getTotalTurnover() {
        return totalTurnover;
    }



    public static class Builder extends AbstractBuilder<Party, Builder> {


        public Builder(Party template) {
            super(template);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }
}
