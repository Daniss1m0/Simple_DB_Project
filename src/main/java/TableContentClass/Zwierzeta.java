package TableContentClass;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Zwierzeta {
    private SimpleIntegerProperty ID_ZWIERZETA;
    private SimpleStringProperty gatunek;

    public Zwierzeta(int idZwierzeta, String gatunek) {
        this.ID_ZWIERZETA = new SimpleIntegerProperty(idZwierzeta);
        this.gatunek = new SimpleStringProperty(gatunek);
    }

    // Gettery
    public int getID_ZWIERZETA() {
        return ID_ZWIERZETA.get();
    }

    public String getGatunek() {
        return gatunek.get();
    }

    // Property metody
    public SimpleIntegerProperty ID_ZWIERZETAProperty() {
        return ID_ZWIERZETA;
    }

    public SimpleStringProperty gatunekProperty() {
        return gatunek;
    }

    // Settery
    public void setID_ZWIERZETA(int id) {
        this.ID_ZWIERZETA.set(id);
    }

    public void setGatunek(String gatunek) {
        this.gatunek.set(gatunek);
    }
}
