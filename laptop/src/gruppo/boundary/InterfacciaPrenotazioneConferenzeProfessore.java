package gruppo.boundary;

import gruppo.bean.*;
import gruppo.controller.GestoreRichieste;

import java.time.LocalDate;
import java.time.Period;

public class InterfacciaPrenotazioneConferenzeProfessore {

    private GestoreRichieste controllerPrenotazioni;
    //private GestoreLogin controllerLogin;
    private boolean isLog;
    private String usernameProf;

    public InterfacciaPrenotazioneConferenzeProfessore(String usernameProf) {

        this.isLog = true; //TODO si potrebbe levare
        //todo obsoletothis.controllerLogin = GestoreLogin.getIstance(); // va fatto nel costruttore?
        this.usernameProf = usernameProf;
    }


    private boolean controlloSintattico(BeanSpecificheConferenza beanSpecificheConferenza) {
        boolean returnFlag = false;
        LocalDate inizio = beanSpecificheConferenza.getDataInizio();
        LocalDate fine = beanSpecificheConferenza.getDataFine();
        if (Period.between(inizio, fine).getMonths() > 0) {
            System.err.println("DATA FINE OLTRE IL MASSIMO DA NOI SCELTO XD");
            returnFlag = true;
        }
        if (Period.between(inizio, LocalDate.now()).getDays() > 0) {
            System.err.println("data inizio scelta indietro nel tempo");
            returnFlag = true;

        }
        if (Period.between(inizio, fine).getDays() < 0) {
            System.err.println("data fine prima dell inizio");
            returnFlag = true;
        }
        return returnFlag;
    }

    /*public void controlloLogin(String nomeUtente, String password) {

        if(this.controllerLogin.login(nomeUtente, password, "Professore")) { // stiamo su interfaccia professore
            this.usernameProf = nomeUtente;
            this.isLog = true;
        }
        else {
            this.isLog = false;
            this.usernameProf = null;
        }
    }*/

    public BeanRisposta prenotazioneConId(BeanSpecificheConferenza beanInfo, BeanIdAula beanId) throws Exception {
        if (!this.isLog) {
            System.out.println("fare qualcose con l'interfacci grafica");
            return null;
        }
        if (this.controlloSintattico(beanInfo)) {
            return new BeanRispostaNegativa(false, true);
        }
        this.controllerPrenotazioni = new GestoreRichieste(this);
        return this.controllerPrenotazioni.gestioneRichieste(beanInfo, beanId);
    }


    public BeanRisposta prenotazioneConCaratteristiche(BeanSpecificheConferenza beanConf, BeanCaratteristicheAula beanCarAula) throws Exception {
        if (!this.isLog) {
            System.out.println("fare qualcose con l'interfacci grafica");
            return null;
        }
        //controllo vincolo 2 settimane su date
        if (this.controlloSintattico(beanConf)) {
            return new BeanRispostaNegativa(false, true);


        }
        this.controllerPrenotazioni = new GestoreRichieste(this);
        BeanRisposta beanRisposta = null;
        return this.controllerPrenotazioni.gestioneRichieste(beanCarAula, beanConf);
        //gestisci la risposta inoltrandola alla grafica etc... xD
        /*if(beanRisposta.isValid()) {
            return true;

        }
        else {
            return false;
        }*/


    }


    public String getUsernameProf() {
        return usernameProf;
    }

    public void setUsernameProf(String usernameProf) {
        this.usernameProf = usernameProf;
    }
}
