package DBConnection;

import TableContentClass.Akcja;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.scene.control.Toggle;
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

    public String insertIntoPole(int powierzchnia, int nasionaId, int klasa) {
        return "{ call DodajDoPole(" + powierzchnia + ", " + nasionaId + ", " + klasa + ") }";
    }

    public String insertIntoPoleWithId(int id, int powierzchnia, int nasionaId, int klasa) {
        return "{ call DodajDoPoleId(" + id + ", " + powierzchnia + ", " + nasionaId + ", " + klasa + ") }";
    }

    public String updatePole(int id, int powierzchnia, int nasionaId, int klasa) {
        return "{ call UpdatePole(" + id + ", " + powierzchnia + ", " + nasionaId + ", " + klasa + ") }";
    }

    public String deleteFromPole(int id) {
        return "{ call DeleteFromPole(" + id + ") }";
    }

    public String selectPole(Boolean flag_id, Boolean flag_powierzchnia, Boolean flag_nasiona_id, Boolean flag_nasiona, Boolean flag_klasa, Boolean flag_siew, Boolean flag_zbiory) {
        List<String> columns = new ArrayList<>();
        String joinClause = "";

        if(flag_id) {
            columns.add("p.ID");
        }
        if (flag_powierzchnia) {
            columns.add("p.POWIERZCHNIA");
        }
        if (flag_nasiona_id) {
            columns.add("p.NASIONA_ID");
        }
        if (flag_nasiona) {
            columns.add("n.NAZWA as nasiona"); // Alias 'nazwaNasiona' for clarity
            joinClause = " INNER JOIN NASIONA n ON p.NASIONA_ID = n.ID";
        }
        if (flag_klasa) {
            columns.add("p.KLASA");
        }
        if (flag_siew) {
            columns.add("n.SIEW as siew"); // Alias 'siew' for clarity
            // Make sure the join clause is included when siew is selected
            if (joinClause.isEmpty()) {
                joinClause = " INNER JOIN NASIONA n ON p.NASIONA_ID = n.ID";
            }
        }
        if (flag_zbiory) {
            columns.add("n.ZBIORY as zbiory"); // Alias 'zbiory' for clarity
            // Make sure the join clause is included when zbiory is selected
            if (joinClause.isEmpty()) {
                joinClause = " INNER JOIN NASIONA n ON p.NASIONA_ID = n.ID";
            }
        }
        if (columns.isEmpty()) {
            return "SELECT p.*, n.NAZWA as nazwaNasiona, n.SIEW as siew, n.ZBIORY as zbiory FROM POLE p" + joinClause; // Return all columns if no flags are set
        }

        String selectQuery = "SELECT " + String.join(", ", columns) + " FROM POLE p" + joinClause;
        return selectQuery;
    }

    public String selectHodowla(Boolean flag_id_hodowla, Boolean flag_id_gospodarstwo, Boolean flag_id_zwierzat, Boolean flag_ilosc, Boolean flag_gatunek, Toggle toggle) {
        List<String> columns = new ArrayList<>();
        String joinClause = "";
        String groupByClause = "";
        List<String> nonAggregatedColumns = new ArrayList<>(); // Initialize nonAggregatedColumns

        if (flag_id_hodowla) {
            columns.add("h.ID_HODOWLA");
        }
        if (flag_id_gospodarstwo) {
            columns.add("h.ID_GOSPODARSTWO");
        }
        if (flag_id_zwierzat) {
            columns.add("h.ID_ZWIERZAT");
        }
        if (flag_ilosc) {
            columns.add("h.ILOSC");
        }

        if (flag_gatunek) {
            joinClause = " FULL OUTER JOIN ZWIERZETA z ON h.ID_ZWIERZAT = z.ID_ZWIERZETA";
            columns.add("z.GATUNEK as gatunek");
        }

        // Handle the selected toggle option (SUM or AVG)
        if (toggle != null && toggle.getUserData() != null) {
            String aggregateFunction = toggle.getUserData().toString().equals("sumaRadio") ? "SUM" : "AVG";
            String iloscColumn = columns.contains("h.ILOSC") ? "h.ILOSC" : "0"; // Handle the case when ILOSC is not selected
            columns.remove("h.ILOSC");
            columns.add(aggregateFunction + "(" + iloscColumn + ") as ilosc");

            // Add the non-aggregated columns to the list
            nonAggregatedColumns = columns.stream()
                    .filter(col -> !col.startsWith("SUM") && !col.startsWith("AVG"))
                    .collect(Collectors.toList());
        }

        // Build the SELECT query
        String selectQuery = "SELECT " + String.join(", ", columns) + " FROM HODOWLA h" + joinClause;

        // Build the GROUP BY clause if there are non-aggregated columns
        if (!nonAggregatedColumns.isEmpty()) {
            groupByClause = " GROUP BY " + nonAggregatedColumns.stream()
                    .map(column -> {
                        // Map the alias to the original column name if it's an alias
                        if (column.endsWith(" as gatunek")) {
                            return column.replace(" as gatunek", "");
                        } else {
                            return column;
                        }
                    })
                    .collect(Collectors.joining(", "));
        }

        // Add the GROUP BY clause to the SELECT query with a space before it
        if (!groupByClause.isEmpty()) {
            selectQuery += groupByClause;
        }

        // Ensure there is a semicolon at the end of the SQL query


        return selectQuery;
    }

}
