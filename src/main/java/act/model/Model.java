package act.model;

import act.card.ActionCard;
import act.card.CardBlock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Model {

    private final Map<CardBlock, List<ActionCard>> cardsMap = new HashMap<>();

    public void addCard(CardBlock cardBlock, ActionCard newCard) {
        cardsMap.computeIfAbsent(cardBlock, k -> new ArrayList<>());
        cardsMap.get(cardBlock).add(newCard);
    }

    public void removeCard(CardBlock cardBlock, ActionCard card) {
        cardsMap.get(cardBlock).remove(card);
    }

    public void deleteAllCards() {
        cardsMap.clear();
    }
}
