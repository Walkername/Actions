package act.controller;

import act.card.ActionCard;
import act.model.Model;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Controller {

    private final Model model = new Model();

    @FXML
    private VBox mainPanel;

    @FXML
    private FlowPane cardPanel;

    @FXML
    private void addCard() {
        ActionCard newCard = new ActionCard(
                "New Card",
                0,
                ""
        );
        model.addCard(newCard);

        HBox cardElement = buildCardElement(newCard);
        cardPanel.getChildren().add(cardElement);
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
        mainPanel.getChildren().clear();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);

        int k = 1;
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 7; j++) {
                if (k == 32) {
                    break;
                }
                VBox calendarCell = new VBox();
                calendarCell.setAlignment(Pos.CENTER);
                calendarCell.setMinSize(50.0, 50.0);
                calendarCell.setMaxSize(50.0, 50.0);
                calendarCell.setStyle("-fx-border-color: black;");
                calendarCell.getChildren().add(new Text(String.valueOf(k)));

                grid.add(calendarCell, j, i);
                k++;
            }
        }

        VBox.setVgrow(grid, Priority.ALWAYS);
        HBox.setHgrow(grid, Priority.ALWAYS);
        grid.setStyle("-fx-border-color: black;");
        mainPanel.getChildren().add(grid);
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
            model.removeCard(card);
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
