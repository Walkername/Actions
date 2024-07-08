package act.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Controller {

    @FXML
    private FlowPane cardPanel;

    @FXML
    public void addCard(ActionEvent event) {
        Stage cardStage = new Stage();

        Text nameText = new Text("Card name");
        TextField nameField = new TextField();

        Text priorityText = new Text("Card priority");
        TextField priorityField = new TextField();

        Text contentText = new Text("Card content");
        TextArea contentArea = new TextArea();
        Button saveButton = new Button("Save");
        saveButton.setOnAction(event1 -> {
            VBox cardImage = new VBox();
            cardImage.setMinSize(50.0, 50.0);
            cardImage.setMaxSize(50.0, 50.0);
            cardImage.setStyle("-fx-border-color: black;");
            cardImage.setAlignment(Pos.CENTER);
            Text cardImageText = new Text(nameField.getText());
            cardImage.getChildren().add(cardImageText);

            cardPanel.getChildren().add(cardImage);
            cardStage.close();
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
        cardStage.setScene(scene);
        cardStage.setTitle("Card Creating Window");
        cardStage.show();
    }
}
