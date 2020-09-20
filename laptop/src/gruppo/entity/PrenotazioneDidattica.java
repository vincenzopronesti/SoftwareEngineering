package gruppo.entity;

import java.time.LocalDate;

public class PrenotazioneDidattica extends Prenotazione {
    private String nomeCorso;
    private String tipoSeduta;
    private LocalDate dataInizioAppello;

    public PrenotazioneDidattica(){}
    public PrenotazioneDidattica(int idAula, LocalDate data, String idProf, FasciaOraria fascia, String nomeCorso, String tipoSeduta, LocalDate dataInizioAppello) {
        super(idAula, data, idProf, fascia);
        this.setNomeCorso(nomeCorso);
        this.setTipoSeduta(tipoSeduta);

        this.dataInizioAppello = dataInizioAppello;

    }
    public PrenotazioneDidattica(int idAula, LocalDate data, String idProf, FasciaOraria fascia, String nomeCorso, String tipoSeduta ) {
        super(idAula, data, idProf, fascia);
        this.setNomeCorso(nomeCorso);
        this.setTipoSeduta(tipoSeduta);



    }

    public String getNomeCorso() {
        return nomeCorso;
    }

    public void setNomeCorso(String nomeCorso) {
        this.nomeCorso = nomeCorso;
    }

    public String getTipoSeduta() {
        return tipoSeduta;
    }

    public void setTipoSeduta(String tipoSeduta) {
        this.tipoSeduta = tipoSeduta;
    }

    public LocalDate getDataInizioAppello() {
        return dataInizioAppello;
    }

    public void setDataInizioAppello(LocalDate dataInizioAppello) {
        this.dataInizioAppello = dataInizioAppello;
    }


    @Override
    public String toString() {
        return "Prenotazione esame:\n" +
                "\tId Aula: " + this.getIdAula() + "\n" +
                "\tData: " + this.getData() + "\n" +
                "\tId Professore: " + this.getUsername() + "\n" +
                "\tFascia oraria: " + this.getFasciaOraria() + "\n" +
                "\tNome corso: " + this.getNomeCorso() + "\n" +
                "\tTipo seduta: " + this.getTipoSeduta() + "\n\n";
    }

}
