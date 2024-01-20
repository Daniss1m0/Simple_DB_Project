package Controllers;

import DBConnection.Const;
import DBConnection.DataBaseConnection;
import TableContentClass.Akcja;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class AkcjaTableController {

    @FXML
    private Button PotwiedzButton;

    @FXML
    private Button backButton;

    @FXML
    private TableView<Akcja> Table;

    /**
     * TableColumns
     */
    @FXML
    private TableColumn<Akcja, Number> ID;

    @FXML
    private TableColumn<Akcja, String> Typ;

    @FXML
    private TableColumn<Akcja, LocalDate> Data_start;

    @FXML
    private TableColumn<Akcja, LocalDate> Data_koniec;

    @FXML
    private TableColumn<Akcja, Number> Pracownik;

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

    private Const polecenie = new Const();

    @FXML
    void initialize() {
        DataBaseConnection dbConnection = new DataBaseConnection();
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
                ID.setCellValueFactory(new PropertyValueFactory<>("id"));
                Typ.setCellValueFactory(new PropertyValueFactory<>("typ"));
                Data_start.setCellValueFactory(new PropertyValueFactory<>("DataStart"));
                Data_koniec.setCellValueFactory(new PropertyValueFactory<>("DataKoniec"));
                Pracownik.setCellValueFactory(new PropertyValueFactory<>("Pracownik"));


                Boolean idFlag = IDBox.isSelected();
                Boolean typFlag = TypBox.isSelected();
                Boolean Data_startFlag = Data_startBox.isSelected();
                Boolean Data_koniecFlag = Data_koniecBox.isSelected();
                Boolean PracownikFlag = PracownikBox.isSelected();

                String query = polecenie.selectAkcja(idFlag, typFlag, Data_startFlag, Data_koniecFlag, PracownikFlag,false);
                ResultSet resultSet = statement.executeQuery(query);

                ObservableList<Akcja> dane = FXCollections.observableArrayList();
                while(resultSet.next())
                {
                    Integer id = -1;
                    String typ = null;
                    LocalDate data_start = null;
                    LocalDate data_koniec = null;
                    Integer pracownik = -1;

                    if(idFlag)
                        id = resultSet.getInt("id");
                    if(typFlag)
                        typ = resultSet.getString("Typ");
                    if(Data_startFlag)
                        data_start = resultSet.getDate("data_start").toLocalDate();
                    if(Data_koniecFlag)
                        data_koniec = resultSet.getDate("data_koniec").toLocalDate();
                    if(PracownikFlag)
                        pracownik = resultSet.getInt("pracownik");

                    Akcja akcja = new Akcja(id,typ,data_start,data_koniec,pracownik);

                    dane.add(akcja);
                }
                ID.setVisible(idFlag);
                Typ.setVisible(typFlag);
                Data_start.setVisible(Data_startFlag);
                Data_koniec.setVisible(Data_koniecFlag);
                Pracownik.setVisible(PracownikFlag);

                Table.setItems(dane);
                Table.refresh();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
