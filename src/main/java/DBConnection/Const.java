package DBConnection;

import TableContentClass.Akcja;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;

public class Const {
    private String sqlQuery;
    public Const()
    {
        sqlQuery = "SELECT * FROM GOSPODARSTWO";
    }

    public String selectAllFrom(String table)
    {
        sqlQuery = "SELECT * FROM "+table;
        return sqlQuery;
    }

    public String selectFrom(String column, String table)
    {
        sqlQuery = "SELECT "+column+" FROM "+table;
        return sqlQuery;
    }

    public String selectAkcja(Boolean id, Boolean typ, Boolean data_start, Boolean data_koniec, Boolean pracownik, Boolean p_id) {
        List<String> columns = new ArrayList<>();

        if(id && typ && data_start && data_koniec && pracownik)
            return "SELECT * FROM AKCJA";

        if (id) {
            columns.add("ID");
        }
        if (typ) {
            columns.add("typ");
        }
        if (data_start) {
            columns.add("Data_Start");
        }
        if (data_koniec) {
            columns.add("Data_Koniec");
        }
        if (pracownik) {
            columns.add("Pracownik");
        }

        if (columns.isEmpty()) {
            return "SELECT * FROM AKCJA"; // lub zwróć null, jeśli chcesz obsłużyć brak zaznaczonych kolumn inaczej
        }

        String selectQuery = "SELECT " + String.join(", ", columns) + " FROM AKCJA";
        return selectQuery;
    }

    public String selectZwierzeta(Boolean id, Boolean gatunek)
    {
        if(id && gatunek)
            return "SELECT * FROM ZWIERZETA";
        else if(!id && !gatunek)
            return "SELECT * FROM ZWIERZETA";

        List<String> columns = new ArrayList<>();
        if(id)
        {
            columns.add("ID_ZWIERZETA");
        }
        if(gatunek)
        {
            columns.add("Gatunek");
        }

        return "SELECT " + String.join(", ", columns) + " FROM ZWIERZETA";
    }
    public ObservableList<Akcja> pobierzAkcje() {
        ObservableList<Akcja> akcje = FXCollections.observableArrayList();
        // Tutaj skonfiguruj swoje dane połączeniowe
        String url = "jdbc:oracle:thin:@twoj_host:port:dbname";
        String user = "c##nazwa";
        String password = "haslo";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            CallableStatement stmt = conn.prepareCall("{call PobierzAkcje(?)}");

            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.execute();

            ResultSet rs = (ResultSet) stmt.getObject(1);

            while (rs.next()) {
                akcje.add(new Akcja(rs.getInt("ID"), rs.getString("TYP"), rs.getDate("DATA_START").toLocalDate(), rs.getDate("DATA_KONIEC").toLocalDate(), rs.getInt("PRACOWNIK")));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return akcje;
    }

    public String insertIntoAkcja(String typ, LocalDate dataStart, LocalDate dataKoniec, int pracownik) {
        return "{ call DodajDoAkcji('" + typ + "', DATE '" + dataStart + "', DATE '" + dataKoniec + "', " + pracownik + ") }";
    }
    public String insertIntoAkcja(Integer id, String typ, LocalDate dataStart, LocalDate dataKoniec, int pracownik) {
        return "{ call DodajDoAkcjiId(" + id + ", '" + typ + "', DATE '" + dataStart + "', DATE '" + dataKoniec + "', " + pracownik + ") }";
    }

    public String updateAkcja(Integer id, String typ, LocalDate dataStart, LocalDate dataKoniec, int pracownik) {
        return "{ call update_Akcja(" + id + ", '" + typ + "', DATE '" + dataStart + "', DATE '" + dataKoniec + "', " + pracownik + ") }";
    }

    public String deleteFromAkcja(Integer id) {
        return "{ call deleteFromAkcja(" + id + ") }";
    }

    public String insertIntoZwierze(Integer id, String gatunek) {
        return "{ call DODAJDOZWIERZAT(" + id + ", '" + gatunek + "') }";
    }
    public String insertIntoZwierze(String gatunek) {
        return "{ call DODAJDOZWIERZATID('" + gatunek + "') }";
    }
    public String updateZwierze(Integer id, String gatunek) {
        return "{ call UPDATE_ZWIERZETA(" + id + ", '" + gatunek + "') }";
    }
    public String deleteFromZwierze(Integer id) {
        return "{ call deleteFromZwierzeta(" + id + ") }";
    }


}
