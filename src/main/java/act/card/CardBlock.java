package act.card;

import java.io.Serializable;

public class CardBlock implements Serializable {

    private String name;

    public CardBlock(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
