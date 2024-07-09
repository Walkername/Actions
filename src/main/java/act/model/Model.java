package act.model;

import act.card.ActionCard;

import java.util.ArrayList;
import java.util.List;

public class Model {

    private final List<ActionCard> cardsList = new ArrayList<>();

    public void addCard(ActionCard newCard) {
        cardsList.add(newCard);
    }

    public void removeCard(ActionCard card) {
        cardsList.remove(card);
    }

    public void deleteAllCards() {
        cardsList.clear();
    }


}
