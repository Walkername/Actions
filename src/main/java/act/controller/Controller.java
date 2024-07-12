package act.controller;

import act.card.ActionCard;
import act.card.CardBlock;
import act.model.Model;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;
import java.util.Map;


public class Controller {

    private final Model model = new Model();

    private int blocksNumber = 0;

    @FXML
    private HBox cardPanel;

    @FXML
    public void initialize() {
        model.loadData();

        Map<CardBlock, List<ActionCard>> cardsMap = model.getCardsMap();

        for (CardBlock cardBlock : cardsMap.keySet()) {
            VBox block = buildBlockElement(cardBlock);
            for (ActionCard card : cardsMap.get(cardBlock)) {
                HBox cardElement = buildCardElement(block, cardBlock, card);
                block.getChildren().add(block.getChildren().size() - 1, cardElement);
            }
        }
    }

    public void saveData() {
        model.saveData();
    }

    private void addCard(CardBlock cardBlock, VBox block) {
        ActionCard newCard = new ActionCard(
                "New Card",
                0,
                ""
        );
        model.addCard(cardBlock, newCard);

        HBox cardElement = buildCardElement(block, cardBlock, newCard);
        block.getChildren().add(block.getChildren().size() - 1, cardElement);
    }

    private VBox buildBlockElement(CardBlock newBlock) {
        VBox block = new VBox();
        block.setSpacing(5);

        StackPane blockTitleBox = new StackPane();
        blockTitleBox.setStyle("-fx-border-color: black;");
        blockTitleBox.setMinSize(100.0, 50.0);
        blockTitleBox.setMaxSize(200.0, 100.0);
        blockTitleBox.setAlignment(Pos.CENTER);

        Label blockLabel = new Label("New Block " + blocksNumber);
        blocksNumber++;

        TextField blockLabelField = new TextField();
        blockLabelField.setVisible(false);
        blockLabelField.setAlignment(Pos.CENTER);

        blockLabel.setOnMouseClicked(event -> {
            blockLabelField.setText(blockLabel.getText());
            blockLabel.setVisible(false);
            blockLabelField.setVisible(true);
            blockLabelField.requestFocus();
            blockLabelField.selectAll();
        });

        blockLabelField.setOnAction(event -> {
            blockLabel.setText(blockLabelField.getText());
            blockLabelField.setVisible(false);
            blockLabel.setVisible(true);
        });

        blockLabelField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                blockLabel.setText(blockLabelField.getText());
                newBlock.setName(blockLabelField.getText());
                blockLabelField.setVisible(false);
                blockLabel.setVisible(true);
            }
        });

        blockTitleBox.getChildren().addAll(blockLabelField, blockLabel);

        block.getChildren().add(blockTitleBox);

        Button addCardButton = new Button("Add Card");
        addCardButton.setAlignment(Pos.CENTER);
        addCardButton.setOnAction(event -> addCard(newBlock, block));

        block.getChildren().add(addCardButton);

        cardPanel.getChildren().add(cardPanel.getChildren().size() - 1, block);

        return block;
    }

    @FXML
    private void addBlock() {
        CardBlock newBlock = new CardBlock("New Block");
        buildBlockElement(newBlock);
    }

    @FXML
    private void deleteAllCards() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("List clear");
        alert.setHeaderText("You're about to clear the ToDo List!");
        alert.setContentText("Are you sure you want to delete all your cards?");
        if (alert.showAndWait().orElse(null) == ButtonType.OK) {
            cardPanel.getChildren().clear();
            model.deleteAllCards();
        }
    }

    @FXML
    private void openCalendar() {

    }

    private HBox buildCardElement(VBox block, CardBlock cardBlock, ActionCard card) {
        VBox cardImage = new VBox();

        cardImage.setMinSize(100.0, 50.0);
        cardImage.setMaxSize(100.0, 50.0);
        cardImage.setStyle("-fx-border-color: black;");
        cardImage.setAlignment(Pos.CENTER);

        Text cardImageText = new Text(card.getPriority() + ": " + card.getName());
        cardImage.getChildren().add(cardImageText);

        CheckBox todoCheckBox = new CheckBox();

        HBox cardElement = new HBox();
        cardElement.setAlignment(Pos.CENTER);
        cardElement.setSpacing(5);
        cardElement.getChildren().addAll(todoCheckBox, cardImage);

        cardImage.setOnMouseClicked(event -> customizeCard(card, cardImageText));

        todoCheckBox.setOnAction(event -> {
            model.removeCard(cardBlock, card);
            block.getChildren().remove(cardElement);
        });

        return cardElement;
    }

    private void customizeCard(ActionCard card, Text cardImageText) {
        Stage cardSettingsStage = new Stage();

        Text nameText = new Text("Card name");
        TextField nameField = new TextField(card.getName());

        Text priorityText = new Text("Card priority");
        TextField priorityField = new TextField(String.valueOf(card.getPriority()));

        Text contentText = new Text("Card content");
        TextArea contentArea = new TextArea(card.getContent());
        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> {
            card.setName(nameField.getText());
            card.setPriority(Integer.parseInt(priorityField.getText()));
            card.setContent(contentArea.getText());
            cardImageText.setText(card.getPriority() + ": " + card.getName());
            cardSettingsStage.close();
        });

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(
                nameText,
                nameField,
                priorityText,
                priorityField,
                contentText,
                contentArea,
                saveButton
        );

        Scene scene = new Scene(vbox);
        cardSettingsStage.setScene(scene);
        cardSettingsStage.setTitle("Card Creating Window");
        cardSettingsStage.show();
    }
}
