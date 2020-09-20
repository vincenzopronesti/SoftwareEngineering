
package gruppo.controller;
import gruppo.DAO.AulaDao;
import gruppo.DAO.PrenotazioneDao;
import gruppo.bean.*;
import gruppo.boundary.InterfacciaPrenotazioneConferenzeProfessore;
import gruppo.entity.Aula;
import gruppo.entity.AulaConvegni;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class GestoreRichieste {
    //conterra la lista di LocalDate non buone per la prenotazione
    public int durataEvento; // x ora è public
    private InterfacciaPrenotazioneConferenzeProfessore prof;
    private List<GestorePrenotazioneConferenza> gestorePrenotazioni;
    private boolean persist = true;
    //diventa false se una prenotazione è null...
    private List<LocalDate> LocalDateNonDisponibili;
    private LocalDate dataInizio;
    private List<Thread> threads;
    //lista di threads
    //vedi run di GestorePrenotazioniCOnferenza per info...

    public GestoreRichieste(InterfacciaPrenotazioneConferenzeProfessore boundary) {
        this.prof = boundary;
        this.gestorePrenotazioni = new ArrayList<GestorePrenotazioneConferenza>();
        this.LocalDateNonDisponibili = new ArrayList<>();
        this.threads = new ArrayList<Thread>();
    }

    public BeanRisposta gestioneRichieste(BeanCaratteristicheAula beanCaratteristiche, BeanSpecificheConferenza beanInfoTemporali) throws Exception {
        //separare richieste su piu giorni analizzando
        //versione con caratteristiche
        BeanRisposta risposta = null; // output di questo metodo
        this.dataInizio = beanInfoTemporali.getDataInizio();
        Period intevallo = Period.between(beanInfoTemporali.getDataInizio(), beanInfoTemporali.getDataFine());//TODO check durata in classe LocalDate
        this.durataEvento = intevallo.getDays();
        System.out.println(durataEvento); //todo debug
        //System.out.println("accedo al db per verificare quali aule ci sono con quelle caratteristiche");
        List<Aula> auleTmp = this.ricercaAulaConCaratteristiche(beanCaratteristiche );
        System.out.println("trovate aule...");
        if (auleTmp.isEmpty())
            return new BeanRispostaNegativa(true, false);
        //non ci sono aule con le caratteristiche richieste :(
        //try {
        //auleTmp=aule temporanee aderenti alle caratteristiche fisiche inserite
        try {
            PrenotazioneDao.getLock().lock();
            System.out.println("taked lock..." + Thread.currentThread().getId());
        } catch (Exception e) {
            System.out.println("error in taking lock...");
            e.printStackTrace();
            PrenotazioneDao.getLock().unlock();
            throw new Exception();
        }
        for (int i = 0; i <= this.durataEvento; i++) {
            BeanSpecificheConferenza beanTemp = null;

            beanTemp = beanInfoTemporali.getClone();

            beanTemp.setDataFine(beanInfoTemporali.getDataInizio().plusDays(i));
            beanTemp.setDataInizio(beanInfoTemporali.getDataInizio().plusDays(i));
            //gruppo.bean temp rappresenta una suddivisione della richiesta su un giorno solo
            //--

            //lancio eccezione... deve terminare tutto dato e lascio lock
                 /*
                lock preso prima di controllare ogni giorno x prenotazione su piu giorni
                le singole prenotazioni su singolo giorno opereranno in maniera concorrente
                se un altro GestoreRichieste venisse aperto da un altra gruppo.boundary facendo lock dovrebbe aspettare
                che questa istanza di gestore richieste(e quindi tutti le richieste su singolo giorno) termini
                NB LASCIO IL LOCK QUI E PROPAGO ECCEZIONE DATO CHE NON POSSO CONTINUARE
                TODO invocante richiesta prenotazione dovrebbe gestire eccezione e rieffetuare richiesta
                todo nb2 qui niente deadlock e ci sara sempre 1! chiamante quindi non lo facciamo :)
                 */


            GestorePrenotazioneConferenza gestorePrentTemp = new GestorePrenotazioneConferenza(true, beanTemp);
            gestorePrenotazioni.add(gestorePrentTemp);
            gestorePrentTemp.setAule(auleTmp);
            Thread t = new Thread(gestorePrentTemp);
            this.threads.add(t);
            t.start();

            //versione sequenziale
            //gestorePrentTemp.verificaAulaConCaratteristiche(beanTemp);

            //la chiamata comportera il set del attributo   prenotazione...
            //alla fine delle richiste su tutti i giorni va controllato tale attributo
            //anche se una richiesta di prenotazione fallisce il for deve finire per dare info dettagliate alla gruppo.boundary

        }
        this.joinPrenotazioni();
        risposta = this.creaRisposta();
        PrenotazioneDao.getLock().unlock();
        System.out.println("leaved lock..." + Thread.currentThread().getId());
            /*rilascio lock dopo generazione risposta
                fino a prima di crea risposta è necessario
                che nessuno acceda a prenotazioni di aule
                dato che vedendo la stessa disponibilita a una prenotazione
                ci sarebbe un incoerenza nella logica
             */

        return risposta;

    }

    public BeanRisposta gestioneRichieste(BeanSpecificheConferenza beanInfo,
                                          BeanIdAula beanId) {
        //versione con id
        this.dataInizio = beanInfo.getDataInizio();
        Period intevallo = Period.between(beanInfo.getDataInizio(), beanInfo.getDataFine());//TODO check durata in classe LocalDate
        this.durataEvento = intevallo.getDays();
        // verifica esistenza aula
        AulaConvegni aulaTmp = this.ricercaAulaConId(beanId);
        if (aulaTmp == null)
            return new BeanRispostaNegativa(true, false);
        List<Aula > aule = new ArrayList<>();
        aule.add(aulaTmp);
        try {PrenotazioneDao.getLock().lock();}
        catch (Exception e){
            PrenotazioneDao.getLock().unlock();
            e.printStackTrace();
            System.err.println("errore in acquisizione lock accesso al db\n...in prenotazione con id");
            System.exit(1);
        }
        for (int i = 0; i <= this.durataEvento; i++) {
            BeanSpecificheConferenza beanTemp = null;
            try {
                beanTemp = beanInfo.getClone();
            } catch (Exception e) {
                e.printStackTrace();
            }
            beanTemp.setDataFine(beanInfo.getDataInizio().plusDays(i));
            beanTemp.setDataInizio(beanInfo.getDataInizio().plusDays(i));
            GestorePrenotazioneConferenza gestorePrenTemp =
                    new GestorePrenotazioneConferenza();
            gestorePrenotazioni.add(gestorePrenTemp);
            gestorePrenTemp.setAule(aule);
            gestorePrenTemp.verificaDisponibilitaId(beanTemp);
        }
        this.joinPrenotazioniId();
        BeanRisposta risposta= this.creaRisposta();
        PrenotazioneDao.getLock().unlock();
        //rilascio il lock
        return risposta;
    }

    private void joinPrenotazioniId() {
        Iterator<GestorePrenotazioneConferenza> i = this.gestorePrenotazioni.iterator();
        int j = 0;
        while (i.hasNext()) {
            if (i.next().getPrenotazione() == null) {
                LocalDate dataNonDisponibile = (this.dataInizio.plusDays(j));
                this.LocalDateNonDisponibili.add(dataNonDisponibile);
                this.persist = false;
                //non verra effetuata alcuna persistenza dato che esiste una data non disponibile
                ++j;
            }
        }
        if (persist) {
            i = this.gestorePrenotazioni.iterator();

            while (i.hasNext()) {
                GestorePrenotazioneConferenza k = i.next();
                k.persisti(prof.getUsernameProf());

                //manda in persistenza le singole prenotazioni su piu giorni
            }
        }
    }


    private BeanRisposta creaRisposta() {
        System.out.println("creazioneRisposta...\t da thread" + Thread.currentThread().getId());
        BeanRisposta beanRisposta;
        List<Integer> idAule = new ArrayList<>();
        List<LocalDate> LocalDate = new ArrayList<>();

        if (this.persist) {
            Iterator<GestorePrenotazioneConferenza> i = this.gestorePrenotazioni.iterator();
            while (i.hasNext()) {
                GestorePrenotazioneConferenza p = i.next();
                idAule.add(p.getPrenotazione().getIdAula());
                LocalDate.add(p.getPrenotazione().getData());
            }
            beanRisposta = new BeanRispostaAffermativa(idAule, true, LocalDate);


        } else {
            beanRisposta = new BeanRispostaNegativa(this.LocalDateNonDisponibili, false);
        }
        return beanRisposta;
    }

    private void joinPrenotazioni() {
        System.out.println("joining prenotazioni...");
        Iterator<Thread> th = this.threads.iterator();
        while (th.hasNext()) {

            try {
                th.next().join();
            } catch (Exception e) {
                e.printStackTrace();
            }
            /*
            join all threads=>aspetta che tutti abbiano finito
            per poi gestire logica per Prenotazione complessiva su tutti i giorni
             */
        }
        //qui tutti i thread avranno finito il loro lavoro
        Iterator<GestorePrenotazioneConferenza> i = this.gestorePrenotazioni.iterator();
        int j = 0;
        while (i.hasNext()) {

            GestorePrenotazioneConferenza controllerPren = i.next();
            if (controllerPren.getPrenotazione() == null) {

                LocalDate dataNonDisponibile = (this.dataInizio.plusDays(j));
                this.LocalDateNonDisponibili.add(dataNonDisponibile);
                this.persist = false;
                //non verra effetuata alcuna persistenza dato che esiste una data non disponibile
                ++j;
            }
        } //TODO ? NECESSARIA
        if (persist) {
            System.out.println("iniziata fase di persistenza...");
            //TODO ? se persist non è stato settato a false=> tutti giorni richiesti ok => persistiamoli tutti
            i = this.gestorePrenotazioni.iterator();

            while (i.hasNext()) {
                GestorePrenotazioneConferenza k = i.next();
                k.persisti(prof.getUsernameProf());

                //manda in persistenza le singole prenotazioni su piu giorni

            }


        }

    }

    // controlla se l'aula con id specificato esiste,
    // se non esiste ritorna null
    private AulaConvegni ricercaAulaConId(BeanIdAula beanIdAula) {
        return AulaDao.getIstance().queryConId(beanIdAula.getIdAula());
    }


    private List<Aula> ricercaAulaConCaratteristiche(BeanCaratteristicheAula beanCarAula) {
        return AulaDao.getIstance().queryConCaratteristiche(beanCarAula);


    }

}
