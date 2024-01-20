package Controllers;

import DBConnection.Const;
import DBConnection.DataBaseConnection;
import TableContentClass.Hodowla;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HodowlaTableController {

    @FXML
    private Button PotwiedzButton;

    @FXML
    private Button backButton;

    @FXML
    private Button OdznaczButton;

    @FXML
    private ToggleGroup mytoggleGroup;

    @FXML
    private RadioButton sumaRadio;

    @FXML
    private RadioButton avgRadio;

    @FXML
    private TableView<Hodowla> Table;

    /**
     * TableColumns
     */
    @FXML
    private TableColumn<Hodowla, Integer> Id_hodowla;

    @FXML
    private TableColumn<Hodowla, Integer> Id_gospodarstwo;

    @FXML
    private TableColumn<Hodowla, Integer> Id_zwierzat;

    @FXML
    private TableColumn<Hodowla, Integer> Ilosc;

    @FXML
    private TableColumn<Hodowla, String> Gatunek;


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
    private CheckBox GatunekBox;


    @FXML
    void initialize() {
        DataBaseConnection dbConnection = new DataBaseConnection();
        Const polecenie = new Const();
        mytoggleGroup = new ToggleGroup();
        sumaRadio.setToggleGroup(mytoggleGroup);
        avgRadio.setToggleGroup(mytoggleGroup);
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
        PotwiedzButton.setOnAction(event -> {
            try(Connection connection = dbConnection.connect(); Statement statement = connection.createStatement())
            {
                Id_hodowla.setCellValueFactory(new PropertyValueFactory<>("idHodowla"));
                Id_gospodarstwo.setCellValueFactory(new PropertyValueFactory<>("idGospodarstwo"));
                Id_zwierzat.setCellValueFactory(new PropertyValueFactory<>("idZwierzat"));
                Ilosc.setCellValueFactory(new PropertyValueFactory<>("ilosc"));
                Gatunek.setCellValueFactory(new PropertyValueFactory<>("gatunek"));

                Integer w_hodowla;
                Integer w_gospodarstwo;
                Integer w_zwierze;
                Integer w_ilosc;
                String w_gatunek;

                String query = "{CALL pustaProcedura};";

                Boolean flag_hodowla = Id_hodowlaBox.isSelected();
                Boolean flag_gospodarstwo = Id_gospodarstwoBox.isSelected();
                Boolean flag_zwierze = Id_zwierzatBox.isSelected();
                Boolean flag_ilosc = IloscBox.isSelected();
                Boolean flag_gatunek = GatunekBox.isSelected();

                System.out.println("AAAAA "+flag_zwierze);


                Toggle toggle = mytoggleGroup.getSelectedToggle();

                query = polecenie.selectHodowla(flag_hodowla,flag_gospodarstwo,flag_zwierze,flag_ilosc,flag_gatunek, toggle);

                System.out.println(query);

                ResultSet resultSet = statement.executeQuery(query);
                ObservableList<Hodowla> dane = FXCollections.observableArrayList();
                while(resultSet.next())
                {
                    w_hodowla = flag_hodowla ? resultSet.getInt("ID_HODOWLA") : -1;
                    w_gospodarstwo = flag_gospodarstwo ? resultSet.getInt("ID_GOSPODARSTWO") : -1;
                    w_zwierze = flag_zwierze ? resultSet.getInt("ID_ZWIERZAT") : -1;
                    w_ilosc = flag_ilosc ? resultSet.getInt("ILOSC") : -1;
                    w_gatunek = flag_gatunek ? resultSet.getString("gatunek") : ""; // Upewnij się, że używasz aliasu 'gatunek', jeśli taki został użyty w zapytaniu SQL

                    Hodowla hodowla = new Hodowla(w_hodowla,w_gospodarstwo,w_zwierze,w_ilosc,w_gatunek);
                    dane.add(hodowla);
                }
                Id_hodowla.setVisible(flag_hodowla);
                Id_gospodarstwo.setVisible(flag_gospodarstwo);
                Id_zwierzat.setVisible(flag_zwierze);
                Ilosc.setVisible(flag_ilosc);
                Gatunek.setVisible(flag_gatunek);

                Table.setItems(dane);
                Table.refresh();
            } catch (SQLException e)
            {
                throw new RuntimeException(e);
            }
        });

        OdznaczButton.setOnAction(event -> {
            mytoggleGroup.selectToggle(null);
        });

    }

}
