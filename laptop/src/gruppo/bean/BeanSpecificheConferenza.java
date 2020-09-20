package gruppo.bean;

import gruppo.entity.FasciaOraria;

import java.time.LocalDate;


public class BeanSpecificheConferenza implements Cloneable {
    private String titoloConferenza;
    private LocalDate dataInizio;
    private LocalDate dataFine;
    //private enum FasciaOraria fasciaoraria;
    private FasciaOraria FasciaOraria; //TODO scrivere come enum, 4 fascie orarie con interi secondo ordine
    private String usernameProf;


    public BeanSpecificheConferenza(String titoloConferenza, LocalDate dataInizio, LocalDate dataFine, FasciaOraria FasciaOraria, String usernameProf) throws Exception {
        this.setTitoloConferenza(titoloConferenza);
        this.setDataInizio(dataInizio);
        this.setDataFine(dataFine);
        this.setFasciaOraria(FasciaOraria);
        this.setUsernameProf(usernameProf);
        //settati attributi
    }

    public String getTitoloConferenza() {
        return titoloConferenza;
    }

    public void setTitoloConferenza(String titoloConferenza) {
        this.titoloConferenza = titoloConferenza;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDate dataFine) {
        this.dataFine = dataFine;
    }

    public FasciaOraria getFasciaOraria() {
        return FasciaOraria;
    }

    public void setFasciaOraria(FasciaOraria FasciaOraria) throws Exception {
        this.FasciaOraria = FasciaOraria;
    }

    public BeanSpecificheConferenza getClone() throws Exception {
        return (BeanSpecificheConferenza) (this.clone());
        //TODO work?
    }

    public String getUsernameProf() {
        return usernameProf;
    }

    public void setUsernameProf(String usernameProf) {
        this.usernameProf = usernameProf;
    }

    @Override
    public String toString() {
        return "BeanSpecificheConferenza{" +
                "titoloConferenza='" + titoloConferenza +
                ", dataInizio=" + dataInizio +
                ", dataFine=" + dataFine +
                ", FasciaOraria=" + FasciaOraria +
                ", usernameProf='" + usernameProf +
                '}';
    }
}




