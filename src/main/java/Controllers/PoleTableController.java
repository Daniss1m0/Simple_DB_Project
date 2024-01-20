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

public class PoleTableController {

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
    private TableColumn<?, ?> Id;

    @FXML
    private TableColumn<?, ?> Powierzchnia;

    @FXML
    private TableColumn<?, ?> Nasiona;

    @FXML
    private TableColumn<?, ?> Klasa;

    @FXML
    private TableColumn<?, ?> Nasiona_Id;

    /**
     * CheckBoxes
     */
    @FXML
    private CheckBox IdBox;

    @FXML
    private CheckBox PowierzchniaBox;

    @FXML
    private CheckBox NasionaBox;

    @FXML
    private CheckBox KlasaBox;

    @FXML
    private CheckBox Nasiona_IdBox;

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
