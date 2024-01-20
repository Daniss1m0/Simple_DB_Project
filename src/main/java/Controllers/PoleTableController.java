package Controllers;

import DBConnection.Const;
import DBConnection.DataBaseConnection;
import TableContentClass.Pole;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class PoleTableController {

    @FXML
    private Button PotwiedzButton;

    @FXML
    private Button backButton;

    @FXML
    private TableView<Pole> Table;

    /**
     * TableColumns
     */
    @FXML
    private TableColumn<Pole, Integer> Id;

    @FXML
    private TableColumn<Pole, Double> Powierzchnia;

    @FXML
    private TableColumn<Pole, Integer> Nasiona;

    @FXML
    private TableColumn<Pole, Integer> Klasa;

    @FXML
    private TableColumn<Pole, Integer> Nasiona_Id;
    @FXML
    private TableColumn<Pole, Integer> Siew;
    @FXML
    private TableColumn<Pole, Integer> Zbiory;

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
    private CheckBox SiewBox;
    @FXML
    private CheckBox ZbioryBox;
    @FXML
    void initialize() {
        DataBaseConnection dbConnection = new DataBaseConnection();
        Const polecenie = new Const();
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
            try (Connection connection = dbConnection.connect(); Statement statement = connection.createStatement()) {
            String query = "{CALL pustaProcedura};";

            Id.setCellValueFactory(new PropertyValueFactory<>("id"));
            Powierzchnia.setCellValueFactory(new PropertyValueFactory<>("powierzchnia"));
            Nasiona_Id.setCellValueFactory(new PropertyValueFactory<>("nasionaId"));
            Nasiona.setCellValueFactory(new PropertyValueFactory<>("nazwaNasiona"));
            Klasa.setCellValueFactory(new PropertyValueFactory<>("klasa"));
            Siew.setCellValueFactory(new PropertyValueFactory<>("siew"));
            Zbiory.setCellValueFactory(new PropertyValueFactory<>("zbiory"));

            int w_id = -1;
            double w_powierzchnia = 0.0;
            int w_nasiona_id = -1;
            String w_nasiona = "";
            int w_klasa = -1;
            LocalDate w_siew = null;
            LocalDate w_zbiory = null;

            Boolean flag_id = IdBox.isSelected();
            Boolean flag_powierzchnia = PowierzchniaBox.isSelected();
            Boolean flag_nasiona = NasionaBox.isSelected();
            Boolean flag_nasiona_id = Nasiona_IdBox.isSelected();
            Boolean flag_klasa = KlasaBox.isSelected();
            Boolean flag_siew = SiewBox.isSelected();
            Boolean flag_zbiory = ZbioryBox.isSelected();

            query = polecenie.selectPole(flag_id, flag_powierzchnia, flag_nasiona_id, flag_nasiona, flag_klasa, flag_siew, flag_zbiory);
            System.out.println(query);

                ResultSet resultSet = statement.executeQuery(query);

                ObservableList<Pole> dane = FXCollections.observableArrayList();

                if(resultSet.isBeforeFirst())


                while(resultSet.next()) {
                    w_id = -1;
                    w_powierzchnia = -1;
                    w_nasiona_id = -1;
                    w_nasiona = "";
                    w_klasa = -1;
                    w_siew = null;
                    w_zbiory = null;

                    if(flag_id)
                        w_id = resultSet.getInt("id");
                    if(flag_powierzchnia)
                        w_powierzchnia = resultSet.getDouble("powierzchnia");
                    if(flag_nasiona)
                        w_nasiona = resultSet.getString("nasiona");
                    if(flag_nasiona_id)
                        w_nasiona_id = resultSet.getInt("nasiona_id");
                    if(flag_klasa)
                        w_klasa = resultSet.getInt("klasa");
                    if(flag_siew)
                        w_siew = resultSet.getDate("siew").toLocalDate();
                    if(flag_zbiory)
                        w_zbiory = resultSet.getDate("zbiory").toLocalDate();
                    Pole pole = new Pole(w_id,w_powierzchnia,w_nasiona_id,w_nasiona,w_klasa,w_siew,w_zbiory);

                    System.out.println("ID: "+w_id);
                    System.out.println("Powierzchnia: "+w_powierzchnia);
                    System.out.println("Nasiona_id: "+w_nasiona_id);
                    System.out.println("Nasiona: "+ w_nasiona);
                    System.out.println("Klasa: "+w_klasa);
                    System.out.println("Siew: "+w_siew);
                    System.out.println("Zbiory: "+w_zbiory);

                    dane.add(pole);
                }

                Id.setVisible(flag_id);
                Powierzchnia.setVisible(flag_powierzchnia);
                Nasiona_Id.setVisible(flag_nasiona_id);
                Nasiona.setVisible(flag_nasiona);
                Klasa.setVisible(flag_klasa);
                Siew.setVisible(flag_siew);
                Zbiory.setVisible(flag_zbiory);

                Table.setItems(dane);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });


    }

}
