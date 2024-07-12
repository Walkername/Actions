package act.model;

import act.card.ActionCard;
import act.card.CardBlock;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Model {

    private Map<CardBlock, List<ActionCard>> cardsMap = new HashMap<>();

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

    public Map<CardBlock, List<ActionCard>> getCardsMap() {
        return this.cardsMap;
    }

    public void saveData() {
        try {
            FileOutputStream fileStream = new FileOutputStream("src/main/resources/data.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileStream);
            out.writeObject(cardsMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void loadData() {
        try {
            FileInputStream fileStream = new FileInputStream("src/main/resources/data.ser");
            ObjectInputStream in = new ObjectInputStream(fileStream);
            cardsMap = (Map<CardBlock, List<ActionCard>>) in.readObject();
        } catch (Exception ignored) {

        }
    }
}
