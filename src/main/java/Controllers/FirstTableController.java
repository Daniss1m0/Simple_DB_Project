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

public class FirstTableController {

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
    private TableColumn<?, ?> Typ;

    @FXML
    private TableColumn<?, ?> Data_start;

    @FXML
    private TableColumn<?, ?> Data_koniec;

    @FXML
    private TableColumn<?, ?> Pracownik;

    @FXML
    private TableColumn<?, ?> Pracownicy_Id;
    /**
     * CheckBoxes
     */
    @FXML
    private CheckBox IDBox;

    @FXML
    private CheckBox TypBox;

    @FXML
    private CheckBox Data_startBox;

    @FXML
    private CheckBox Data_koniecBox;

    @FXML
    private CheckBox PracownikBox;

    @FXML
    private CheckBox Pracownicy_IdBox;

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
