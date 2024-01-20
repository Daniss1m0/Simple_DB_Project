package Controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuController {

    @FXML
    private Button akcjaTableButton;

    @FXML
    private Button zwierzetaTableButton;

    @FXML
    private Button poleTableButton;

    @FXML
    private Button hodowlaTableButton;

    @FXML
    private Button settingsButton;

    @FXML
    private Button exitButton;

    @FXML
    void initialize() {
        akcjaTableButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/akcjaTable.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);

                Stage stage = (Stage) akcjaTableButton.getScene().getWindow();

                stage.setScene(scene);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        zwierzetaTableButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/zwierzetaTable.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);

                Stage stage = (Stage) zwierzetaTableButton.getScene().getWindow();

                stage.setScene(scene);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        poleTableButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/poleTable.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);

                Stage stage = (Stage) poleTableButton.getScene().getWindow();

                stage.setScene(scene);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        hodowlaTableButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/hodowlaTable.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);

                Stage stage = (Stage) hodowlaTableButton.getScene().getWindow();

                stage.setScene(scene);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        settingsButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/settings.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);

                Stage stage = (Stage) settingsButton.getScene().getWindow();

                stage.setScene(scene);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        exitButton.setOnAction(event -> {
            Stage stage = (Stage) exitButton.getScene().getWindow();
            stage.close();
        });
    }

}
