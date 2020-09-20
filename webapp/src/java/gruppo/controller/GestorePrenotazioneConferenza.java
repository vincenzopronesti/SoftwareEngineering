package gruppo.controller;

import gruppo.DAO.PrenotazioneDao;
import gruppo.bean.BeanSpecificheConferenza;
import gruppo.entity.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GestorePrenotazioneConferenza implements Runnable {
    private PrenotazioneConferenza prenotazione;
    private List<Aula > aule; //manterra riferimenti a aule con caratteristiche (NON TEMPORALI ) o cambiato in conferenza perchè tanto il caso d'uso parla di conferenze
    private boolean tipoPrenotazione;
    //true=>PrenotazioneConCaratteristiche
    //false=>PrenotazioneConId
    private BeanSpecificheConferenza beanSpecificheConferenza;

    public GestorePrenotazioneConferenza(){}
    //costruttore
    public GestorePrenotazioneConferenza(boolean tipoPrenotazione, BeanSpecificheConferenza beanSpecConf) {
        this.tipoPrenotazione = tipoPrenotazione;
        this.aule = new ArrayList<Aula>();
        this.beanSpecificheConferenza = beanSpecConf;
    }

    //public Aula ricercaAulaConId() { // paramtero id

    //}

    public void persisti(String usernameProf) {
        //critico che qui il lock sia preso dal chiamante todo assertion...
        //fa persistere in db l'ogg prenotaizone
        PrenotazioneDao prenotazioneDao = PrenotazioneDao.getIstance();

        this.getPrenotazione().setUsername(usernameProf);
        prenotazioneDao.persistiPrenotazioneConferenza(this.getPrenotazione());
    }
    /*
    public Boolean ricercaAulaConCaratteristiche(BeanCaratteristicheAula beanCarAula, BeanSpecificheConferenza beanSpeConf) {
        aule = AulaDao.getIstance().queryConCaratteristiche(beanCarAula);
        if (!aule.isEmpty()) { //se caratteristiche richiesta hanno prodotto risultati parziali
             this.verificaAulaConCaratteristiche(beanSpeConf);
            //è possibile gestire prenotazioni su piu giorni e piu aule iterando per ogni giorno
            //verificaAUleconCaratteristiche (ottenendo
            return true;
        }
        return false;

    }
    //OLD VERSION... le aule sono le stesse per tutte le richieste su piu giorni
    //vengono ricavate dal GestoreRichieste
    */

    //private boolean verificaDisponibilitaId() {

    public void setAule(List<Aula> aule) {
        this.aule = aule;
    }//setta le aule di cui controllare la disponibilita

    protected void verificaAulaConCaratteristiche(BeanSpecificheConferenza beanSpecificheConferenza) {
        Iterator<Aula > I = this.aule.iterator();
        while (I.hasNext()) {
            Aula aula = I.next();
            PrenotazioneConferenza prenotazione;
            Factory factory = new Factory();
            prenotazione = (PrenotazioneConferenza) factory.createPrenotazioni(1, aula.getId(), beanSpecificheConferenza);
            //TODO DEBUG FACTORY
            // gruppo.controller per PrenotazioneAulaConferenza
            //ma funzionale anche con prenotazione didattica vista la unica differenza di un attributo
            PrenotazioneDao prenotazioneDao = PrenotazioneDao.getIstance();

            if (prenotazioneDao.queryEsistenzaPrenotazioneConferenza(prenotazione)) {
                this.setPrenotazione(prenotazione);
                //prenotazione ok-->setta come attributo
                //chiamante la prendera successivamente
                break;
            }
        }


    }

    public void verificaDisponibilitaId(BeanSpecificheConferenza beanInfo) {
        if (aule.get(0) == null) {
            System.out.println("non si e' specificato per quale aula "
                    + "controllare la disponibilita'");
            return;
        } else {
            Factory factory = new Factory();
            PrenotazioneConferenza prenotazione = (PrenotazioneConferenza) factory.
                    createPrenotazioni(1, aule.get(0).getId(), beanInfo);
            PrenotazioneDao prenotazioneDao = PrenotazioneDao.getIstance();

            if ( prenotazioneDao.queryEsistenzaPrenotazioneConferenza(prenotazione)) {
                this.setPrenotazione(prenotazione);
            }

            return;
        }
    }

    public PrenotazioneConferenza getPrenotazione() {
        return prenotazione;
    }

    public void setPrenotazione(PrenotazioneConferenza prenotazione) {
        this.prenotazione = prenotazione;
    }

    public void run() {
        /*
        un thread per ogni richiesta spezzata su un singolo giorno
         */
        System.out.println("inizio lavoro thread..." + Thread.currentThread().getId());
        if (this.tipoPrenotazione = true) {
            //caso prenotazione Con caratteristiche
            this.verificaAulaConCaratteristiche(this.beanSpecificheConferenza);
        } else {
            this.verificaDisponibilitaId(beanSpecificheConferenza);
        }

        System.out.println("ended thread work...." + Thread.currentThread().getId());

    } //lanciato per ogni giorno spezzato.
}
