package TableContentClass;

public class Hodowla {
    private int idHodowla;
    private int idGospodarstwo;
    private int idZwierzat;
    private int ilosc;
    private String gatunek;

    public Hodowla(int idHodowla, int idGospodarstwo, int idZwierzat, int ilosc, String gatunek) {
        this.idHodowla = idHodowla;
        this.idGospodarstwo = idGospodarstwo;
        this.idZwierzat = idZwierzat;
        this.ilosc = ilosc;
        this.gatunek = gatunek;
    }

    // Getters and setters for each field

    public int getIdHodowla() {
        return idHodowla;
    }

    public void setIdHodowla(int idHodowla) {
        this.idHodowla = idHodowla;
    }

    public int getIdGospodarstwo() {
        return idGospodarstwo;
    }

    public void setIdGospodarstwo(int idGospodarstwo) {
        this.idGospodarstwo = idGospodarstwo;
    }

    public int getIdZwierzat() {
        return idZwierzat;
    }

    public void setIdZwierzat(int idZwierzat) {
        this.idZwierzat = idZwierzat;
    }

    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }

    public String getGatunek() {
        return gatunek;
    }

    public void setGatunek(String gatunek) {
        this.gatunek = gatunek;
    }
}
