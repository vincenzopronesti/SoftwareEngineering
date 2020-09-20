package gruppo.entity;


import java.time.LocalDate;

public class PrenotazioneConferenza extends Prenotazione {

    private String titoloConferenza;
    public PrenotazioneConferenza(){};

    public PrenotazioneConferenza(int idAula, LocalDate data, FasciaOraria FasciaOraria, String titoloConferenza) {
        super(idAula, data, FasciaOraria);
        this.setTitoloConferenza(titoloConferenza);
    }

    public String getTitoloConferenza() {
        return titoloConferenza;
    }
    public void setTitoloConferenza(String titoloConferenza) {
        this.titoloConferenza = titoloConferenza;
    }

    @Override
    public String toString() {
        return "Prenotazione conferenza:\n" +
                "\tId Aula: " + this.getIdAula() + "\n" +
                "\tData: " + this.getData() + "\n" +
                "\tFascia oraria: " + this.getFasciaOraria() + "\n" +
                "\tTitolo conferenza: " + this.getTitoloConferenza() + "\n";
    }

  /* public PrenotazioneConferenza(int idAula, LocalDate data, FasciaOraria fascia, String titoloConferenza) {
    super (idAula,data, fascia);
    this.titoloConferenza=titoloConferenza;
    } */
}


