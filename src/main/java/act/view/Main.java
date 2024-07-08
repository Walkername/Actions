package act.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        HBox root = new HBox();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Actions");
        stage.setWidth(800);
        stage.setHeight(500);
        stage.show();
    }
}