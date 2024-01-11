package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SettingsController {

    @FXML
    private Button backButton;
    // ------------------------------------------
    // ------------------------------------------
    @FXML
    private ChoiceBox<?> Typ_operacji1;

    @FXML
    private Spinner<?> ID1;

    @FXML
    private Spinner<?> Pracownik;

    @FXML
    private Spinner<?> Pracownicy_Id;

    @FXML
    private DatePicker Data_start;

    @FXML
    private DatePicker Data_koniec;

    @FXML
    private TextField Typ;

    @FXML
    private Button Potwierdz1Button;
    // ------------------------------------------
    // ------------------------------------------
    @FXML
    private ChoiceBox<?> Typ_operacji2;

    @FXML
    private Spinner<?> ID2;

    @FXML
    private TextField Gatunek;

    @FXML
    private Button Potwierdz2Button;

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
