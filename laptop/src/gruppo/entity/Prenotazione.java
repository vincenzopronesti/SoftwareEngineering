package gruppo.entity;


import java.time.LocalDate;
import java.util.Objects;

public abstract class Prenotazione {
    private int idAula;
    private LocalDate data;
    private String username;
    private FasciaOraria FasciaOraria;
    //fascia oraria 0<-->3

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Prenotazione)) return false;
        Prenotazione that = (Prenotazione) o;
        return idAula == that.idAula &&
                Objects.equals(data, that.data) &&
                Objects.equals(username, that.username) &&
                FasciaOraria == that.FasciaOraria;
    }
    //todo IDE generated....needed??
    @Override
    public int hashCode() {

        return Objects.hash(idAula, data, username, FasciaOraria);
    }//...p
    public Prenotazione(){};
    public Prenotazione(int idAula, LocalDate data, String username, FasciaOraria fascia) {
        this.setIdAula(idAula);
        this.setData(data);
        this.setUsername(username);
        this.setFasciaOraria(fascia);
    }

    public Prenotazione(int idAula, LocalDate data, FasciaOraria FasciaOraria) {
        this.idAula = idAula;
        this.data = data;
        this.FasciaOraria = FasciaOraria;
        this.username = null;
    }


    public int getIdAula() {
        return idAula;
    }

    public void setIdAula(int idAula) {
        this.idAula = idAula;
    }

    public LocalDate getData() {
        return this.data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public FasciaOraria getFasciaOraria() {
        return FasciaOraria;
    }

    public void setFasciaOraria(FasciaOraria FasciaOraria) {
        this.FasciaOraria = FasciaOraria;
    }

   /* public Prenotazione(int idAula, LocalDate data,  FasciaOraria fascia) {
        //costruttore per Oggetti prenotazione senza id (non ancora in strato di persistenza
        this.idAula = idAula;
        this.data = data;
        this.idPrenotazione = -1;
        this.FasciaOraria = fascia;
    } */
    public abstract String toString();
}

