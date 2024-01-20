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

public class HodowlaTableController {

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
    private TableColumn<?, ?> Id_hodowla;

    @FXML
    private TableColumn<?, ?> Id_gospodarstwo;

    @FXML
    private TableColumn<?, ?> Id_zwierzat;

    @FXML
    private TableColumn<?, ?> Ilosc;

    @FXML
    private TableColumn<?, ?> Zwierzeta_Id;

    @FXML
    private TableColumn<?, ?> Gospodarstwo_Id;

    /**
     * CheckBoxes
     */
    @FXML
    private CheckBox Id_hodowlaBox;

    @FXML
    private CheckBox Id_gospodarstwoBox;

    @FXML
    private CheckBox Id_zwierzatBox;

    @FXML
    private CheckBox IloscBox;

    @FXML
    private CheckBox Zwierzeta_IdBox;

    @FXML
    private CheckBox Gospodarstwo_IdBox;

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
