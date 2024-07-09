package act.controller;

import act.model.ActionCard;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class Controller {

    List<ActionCard> cardsList = new ArrayList<>();

    @FXML
    private FlowPane cardPanel;

    @FXML
    private void addCard() {
        ActionCard newCard = new ActionCard(
                "New Card",
                0,
                ""
        );
        cardsList.add(newCard);

        HBox cardElement = buildCardElement(newCard);
        cardPanel.getChildren().add(cardElement);


    }

    private HBox buildCardElement(ActionCard card) {
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
            cardPanel.getChildren().remove(cardElement);
            cardsList.remove(card);
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
