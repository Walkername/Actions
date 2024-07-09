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
        Stage cardSettingsStage = new Stage();

        Text nameText = new Text("Card name");
        TextField nameField = new TextField();

        Text priorityText = new Text("Card priority");
        TextField priorityField = new TextField();

        Text contentText = new Text("Card content");
        TextArea contentArea = new TextArea();
        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> {
            ActionCard newCard = new ActionCard(
                    nameField.getText(),
                    Integer.parseInt(priorityField.getText()),
                    contentArea.getText()
            );
            cardsList.add(newCard);

            HBox cardElement = buildCardElement(nameField.getText(), newCard);
            cardPanel.getChildren().add(cardElement);
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

    private HBox buildCardElement(String cardName, ActionCard card) {
        VBox cardImage = new VBox();
        cardImage.setMinSize(50.0, 50.0);
        cardImage.setMaxSize(50.0, 50.0);
        cardImage.setStyle("-fx-border-color: black;");
        cardImage.setAlignment(Pos.CENTER);

        Text cardImageText = new Text(cardName);
        cardImage.getChildren().add(cardImageText);

        CheckBox todoCheckBox = new CheckBox();

        HBox cardElement = new HBox();
        cardElement.setAlignment(Pos.CENTER);
        cardElement.setSpacing(5);
        cardElement.getChildren().addAll(todoCheckBox, cardImage);

        todoCheckBox.setOnAction(event -> {
            cardPanel.getChildren().remove(cardElement);
            cardsList.remove(card);
        });

        return cardElement;
    }
}
