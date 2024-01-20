package TableContentClass;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleObjectProperty;
import java.time.LocalDate;

public class Pole {

    private SimpleIntegerProperty id;
    private SimpleDoubleProperty powierzchnia;
    private SimpleIntegerProperty nasionaId;
    private SimpleStringProperty nazwaNasiona;
    private SimpleIntegerProperty klasa;
    private SimpleObjectProperty<LocalDate> siew;
    private SimpleObjectProperty<LocalDate> zbiory;

    public Pole(int id, double powierzchnia, int nasionaId, String nazwaNasiona, int klasa, LocalDate siew, LocalDate zbiory) {
        this.id = new SimpleIntegerProperty(id);
        this.powierzchnia = new SimpleDoubleProperty(powierzchnia);
        this.nasionaId = new SimpleIntegerProperty(nasionaId);
        this.nazwaNasiona = new SimpleStringProperty(nazwaNasiona);
        this.klasa = new SimpleIntegerProperty(klasa);
        this.siew = new SimpleObjectProperty<>(siew);
        this.zbiory = new SimpleObjectProperty<>(zbiory);
    }

    // Gettery
    public int getId() {
        return id.get();
    }

    public double getPowierzchnia() {
        return powierzchnia.get();
    }

    public int getNasionaId() {
        return nasionaId.get();
    }

    public String getNazwaNasiona() {
        return nazwaNasiona.get();
    }

    public int getKlasa() {
        return klasa.get();
    }

    public LocalDate getSiew() {
        return siew.get();
    }

    public LocalDate getZbiory() {
        return zbiory.get();
    }

    // Property metody
    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public SimpleDoubleProperty powierzchniaProperty() {
        return powierzchnia;
    }

    public SimpleIntegerProperty nasionaIdProperty() {
        return nasionaId;
    }

    public SimpleStringProperty nazwaNasionaProperty() {
        return nazwaNasiona;
    }

    public SimpleIntegerProperty klasaProperty() {
        return klasa;
    }

    public SimpleObjectProperty<LocalDate> siewProperty() {
        return siew;
    }

    public SimpleObjectProperty<LocalDate> zbioryProperty() {
        return zbiory;
    }

    // Settery
    public void setId(int id) {
        this.id.set(id);
    }

    public void setPowierzchnia(double powierzchnia) {
        this.powierzchnia.set(powierzchnia);
    }

    public void setNasionaId(int nasionaId) {
        this.nasionaId.set(nasionaId);
    }

    public void setNazwaNasiona(String nazwaNasiona) {
        this.nazwaNasiona.set(nazwaNasiona);
    }

    public void setKlasa(int klasa) {
        this.klasa.set(klasa);
    }

    public void setSiew(LocalDate siew) {
        this.siew.set(siew);
    }

    public void setZbiory(LocalDate zbiory) {
        this.zbiory.set(zbiory);
    }

    // Dodatkowe metody, jeśli są potrzebne...

}