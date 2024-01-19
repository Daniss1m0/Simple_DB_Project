package TableContentClass;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import java.time.LocalDate;
import java.util.Date;

public class Akcja {

        private SimpleIntegerProperty id;
        private SimpleStringProperty typ;
        private SimpleObjectProperty<LocalDate> dataStart;
        private SimpleObjectProperty<LocalDate> dataKoniec;
        private SimpleIntegerProperty pracownik;

        public Akcja(int id, String typ, LocalDate dataStart, LocalDate dataKoniec, int pracownik) {
            this.id = new SimpleIntegerProperty(id);
            this.typ = new SimpleStringProperty(typ);
            this.dataStart = new SimpleObjectProperty<>(dataStart);
            this.dataKoniec = new SimpleObjectProperty<>(dataKoniec);
            this.pracownik = new SimpleIntegerProperty(pracownik);
        }

        // Gettery
        public int getId() {
            return id.get();
        }

        public String getTyp() {
            return typ.get();
        }

        public LocalDate getDataStart() {
            return dataStart.get();
        }

        public LocalDate getDataKoniec() {
            return dataKoniec.get();
        }

        public int getPracownik() {
            return pracownik.get();
        }

        // Property metody
        public SimpleIntegerProperty idProperty() {
            return id;
        }

        public SimpleStringProperty typProperty() {
            return typ;
        }

        public SimpleObjectProperty<LocalDate> dataStartProperty() {
            return dataStart;
        }

        public SimpleObjectProperty<LocalDate> dataKoniecProperty() {
            return dataKoniec;
        }

        public SimpleIntegerProperty pracownikProperty() {
            return pracownik;
        }

        // Settery
        public void setId(int id) {
            this.id.set(id);
        }

        public void setTyp(String typ) {
            this.typ.set(typ);
        }

        public void setDataStart(LocalDate dataStart) {
            this.dataStart.set(dataStart);
        }

        public void setDataKoniec(LocalDate dataKoniec) {
            this.dataKoniec.set(dataKoniec);
        }

        public void setPracownik(int pracownik) {
            this.pracownik.set(pracownik);
        }

}