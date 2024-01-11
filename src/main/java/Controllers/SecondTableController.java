package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class SecondTableController {

    @FXML
    private Button PotwiedzButton;

    @FXML
    private Button backButton;

    @FXML
    private TableView<?> Table;
    /**
     * TableColumns
     */
    @FXML
    private TableColumn<?, ?> ID;

    @FXML
    private TableColumn<?, ?> Gatunek;
    /**
     * CheckBoxes
     */
    @FXML
    private CheckBox IDBox;

    @FXML
    private CheckBox GatunekBox;

    @FXML
    void initialize() {
        backButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/menu.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);

                Stage stage = (Stage) backButton.getScene().getWindow();

                stage.setScene(scene);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

}
