package Andrea.controller;

import gruppo.DAO.*;
import gruppo.bean.*;
import gruppo.entity.*;
import Andrea.bean.*;
import gruppo.entity.Aula;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GestoreVerificaDisponibilita {
    private List<Aula> AuleDisponibili = new ArrayList<>();

    //private List<Aula> aule

    public BeanDiRispostaVerificaDisponibilita verificaDisponibilitaConCaratteristiche
            (BeanCaratteristicheAula beanCarAula, BeanDataFasciaOraria beanDate) {

        LocalDate data=beanDate.getData();
        FasciaOraria fasciaOraria=beanDate.getFasciaOraria();


        List<Aula > aule = this.ricercaAulaConCaratteristiche(beanCarAula);
        if (aule.isEmpty()) {
            System.out.println("non ci sono aule con le  caratteristiche indicate");
            return new BeanDiRispostaVerificaDisponibilita(true, false, null);
            //generata risposta corrispondente a <<non ci sono aule con quelle caratteristiche>>
        }
        PrenotazioneDao prenotazioneDao=PrenotazioneDao.getIstance();
        PrenotazioneDao.getLock().lock();
        //PRENDO LOCK PER ACCESSI AL DB PRENOTAZIONI SOLO PER COERENZA CON UC GRUPPO(PREVEDE LOCK PER CONCORRENZA DI GESTORE PRENOTAZIONI....
        Iterator<Aula > i = aule.iterator();
        while (i.hasNext()) {
            Aula aula = i.next();
            //TODO GUARDA PRENOTAZIONEDAO ULTIMO METODO
            //old
            //Prenotazione prenotazioneTmp = new Prenotazione(aula.getId(),data,null,fasciaOraria);
            //prenotazione temporanea per effettuare query al db (se esiste gia tale prenotazione...
            //new version...vedo se gia una prenotazione su l aula su cui itero tramite campi chiave di tabella Prenotazione in DB
            //todo necessaria modifica a prenotazioneDAO
            if(prenotazioneDao.queryEsistenzaPrenotazione(aula.getId(),data,fasciaOraria)) {
                AuleDisponibili.add(aula); //se non esista gia tale prenotazione==>aula è disponibile in tale data/fasciaOraria xD
            }
        }
        PrenotazioneDao.getLock().unlock();
        //todo rilascio il lock dopo che ho preso conoscenza di tutte le disponibilita e ho terminato gli accessi al db
        List<Integer> idAuleDisponibili= new ArrayList<>();
        Iterator<Aula> it= this.AuleDisponibili.iterator();
        while(it.hasNext()){
            Aula a=it.next();
            idAuleDisponibili.add(a.getId());
            //prendo solo id aule da ritornare in bean di risposta x UC
        }
        return new BeanDiRispostaVerificaDisponibilita(false,false, idAuleDisponibili);


    }

    private List<Aula> ricercaAulaConCaratteristiche(BeanCaratteristicheAula beanCarAula ) {
        return AulaDao.getIstance().queryConCaratteristiche(beanCarAula);

    }
}
