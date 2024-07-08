package act;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Actions");
            stage.setWidth(800);
            stage.setHeight(500);
            stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}