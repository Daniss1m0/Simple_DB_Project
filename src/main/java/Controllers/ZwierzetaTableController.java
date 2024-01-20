package Controllers;

import DBConnection.Const;
import DBConnection.DataBaseConnection;
import TableContentClass.Zwierzeta;
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
import java.sql.*;

public class ZwierzetaTableController {

    @FXML
    private Button PotwiedzButton;

    @FXML
    private Button backButton;

    @FXML
    private TableView<Zwierzeta> Table;
    /**
     * TableColumns
     */
    @FXML
    private TableColumn<Zwierzeta, Number> ID;

    @FXML
    private TableColumn<Zwierzeta, String> Gatunek;
    /**
     * CheckBoxes
     */
    @FXML
    private CheckBox IDBox;

    @FXML
    private CheckBox GatunekBox;

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
            ID.setCellValueFactory(new PropertyValueFactory<>("ID_ZWIERZETA"));
            Gatunek.setCellValueFactory(new PropertyValueFactory<>("gatunek"));

            Boolean idFlag = IDBox.isSelected();
            Boolean gatunekFlag = GatunekBox.isSelected();

            String query = polecenie.selectZwierzeta(idFlag, gatunekFlag);

            String test = "SELECT id_zwierzeta FROM ZWIERZETA";
            System.out.println(query);
            ResultSet resultSet = statement.executeQuery(query);
            ResultSetMetaData rsmd = resultSet.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                System.out.println("Column Name from ResultSetMetaData: " + rsmd.getColumnName(i));
            }
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet columns = metaData.getColumns(null, null, "ZWIERZETA", null);
            while(columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                System.out.println(columnName); // Log the actual column names
            }

            ObservableList<Zwierzeta> dane = FXCollections.observableArrayList();
            while(resultSet.next())
            {
                Integer id = -1;
                String gatunek = null;

                if(idFlag)
                    id = resultSet.getInt("ID_ZWIERZETA");

                if(gatunekFlag)
                    gatunek = resultSet.getString("GATUNEK");

                Zwierzeta zwierzeta = new Zwierzeta(id,gatunek);
                dane.add(zwierzeta);
            }
            ID.setVisible(idFlag);
            Gatunek.setVisible(gatunekFlag);

            Table.setItems(dane);
            Table.refresh();

        } catch (SQLException e) {
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("Error Code: " + e.getErrorCode());
            System.out.println("Message: " + e.getMessage());
            e.printStackTrace();
        }
        });
    }

}
