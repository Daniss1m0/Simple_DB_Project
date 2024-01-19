package Controllers;

import DBConnection.Const;
import DBConnection.DataBaseConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class SettingsController {

    @FXML
    private Button backButton;
    // ------------------------------------------
    // ------------------------------------------
    @FXML
    private ChoiceBox<String> Typ_operacji1;

    @FXML
    private Spinner<Integer> ID1;

    @FXML
    private Spinner<Integer> Pracownik;

    @FXML
    private Spinner<Integer> Pracownicy_Id;

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
    private ChoiceBox<String> Typ_operacji2;

    @FXML
    private Spinner<Integer> ID2;

    @FXML
    private TextField Gatunek;

    @FXML
    private Button Potwierdz2Button;

    @FXML
    void initialize() {
        DataBaseConnection dbConnection = new DataBaseConnection();
        Const polecenie = new Const();
        Typ_operacji1.getItems().addAll("INSERT", "UPDATE", "DELETE");
        Typ_operacji2.getItems().addAll("INSERT", "UPDATE", "DELETE");
        Typ_operacji1.setValue("INSERT");
        Typ_operacji2.setValue("INSERT");
        SpinnerValueFactory<Integer> valueFactoryId = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,0);
        SpinnerValueFactory<Integer> valueFactoryId2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,0);
        SpinnerValueFactory<Integer> valueFactoryPracownik = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,0);
        ID1.setValueFactory(valueFactoryId);
        Pracownik.setValueFactory(valueFactoryPracownik);
        ID2.setValueFactory(valueFactoryId2);


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

        Potwierdz1Button.setOnAction(event -> {
            try(Connection connection = dbConnection.connect(); Statement statement = connection.createStatement())
            {
                Integer w_id = -1;
                String w_typ = "";
                LocalDate w_data_start = null;
                LocalDate w_data_koniec = null;
                Integer w_pracownik = -1;

                w_id = ID1.getValue();
                w_typ = Typ.getText();
                w_data_start = Data_start.getValue();
                w_data_koniec = Data_koniec.getValue();
                w_pracownik = Pracownik.getValue();

                String query = "{CALL pustaProcedura};";

                if(Typ_operacji1.getValue().equals(null))
                {
                    return;
                }
                else if(Typ_operacji1.getValue().equals("INSERT")) {

                    if(w_id == 0)
                    {
                        query = polecenie.insertIntoAkcja(w_typ, w_data_start, w_data_koniec, w_pracownik);
                    }
                    else {
                        query = polecenie.insertIntoAkcja(w_id, w_typ, w_data_start, w_data_koniec, w_pracownik);
                    }
                }
                else if(Typ_operacji1.getValue().equals("UPDATE"))
                {
                    query = polecenie.updateAkcja(w_id,w_typ,w_data_start,w_data_koniec,w_pracownik);
                }
                else if(Typ_operacji1.getValue().equals("DELETE"))
                {
                    query = polecenie.deleteFromAkcja(w_id);
                }

                try (CallableStatement statement1 = connection.prepareCall(query)) {
                    statement1.execute();
                }
                ID1.getValueFactory().setValue(1);
                Pracownik.getValueFactory().setValue(1);
                Typ.setText("");
                Data_start.setValue(null);
                Data_koniec.setValue(null);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        Potwierdz2Button.setOnAction(event -> {
            try(Connection connection = dbConnection.connect(); Statement statement = connection.createStatement())
            {
                Integer w_id = ID2.getValue();
                String w_gatunek = Gatunek.getText();
                String query = "{ call pustaProcedura }";

                    if(Typ_operacji2.getValue().equals("INSERT")) {
                        if (w_id == 0) {
                            query = polecenie.insertIntoZwierze(w_gatunek);
                        } else {
                            query = polecenie.insertIntoZwierze(w_id, w_gatunek);
                        }
                    } else if(Typ_operacji2.getValue().equals("UPDATE"))
                    {
                        query = polecenie.updateZwierze(w_id,w_gatunek);
                    }
                    else if(Typ_operacji2.getValue().equals("DELETE")) {
                        query = polecenie.deleteFromZwierze(w_id);
                    }

                System.out.println(query);

                try(CallableStatement statement2 = connection.prepareCall(query))
                {
                    statement2.execute();
                }
                ID2.getValueFactory().setValue(0);
                Gatunek.setText("");
            } catch (SQLException e2)
            {
                throw new RuntimeException(e2);
            }
        });

    }
}
