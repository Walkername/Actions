package act;

import act.controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("main.fxml")));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Actions");
            stage.setWidth(800);
            stage.setHeight(500);
            stage.setMinWidth(500);
            stage.setMinHeight(200);
            stage.show();

            stage.setOnCloseRequest(event -> {
                event.consume();
                logout(stage, loader);
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void logout(Stage stage, FXMLLoader loader) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to logout!");
        alert.setContentText("Are you sure you want to exit?");

        if (alert.showAndWait().orElse(null) == ButtonType.OK) {
            Controller controller = loader.getController();
            controller.saveData();
            stage.close();
        }
    }
}