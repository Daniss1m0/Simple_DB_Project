package ClientFiles;

import DBConnection.DataBaseHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/gui/menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Simple_DB_Project");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        DataBaseHandler db = new DataBaseHandler();
        launch();

    }
}