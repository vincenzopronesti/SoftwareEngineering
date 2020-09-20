package gruppo.entity;

import gruppo.bean.BeanSpecificheConferenza;

public class Factory {


    public Prenotazione createPrenotazioni(int tipo, int idAula, BeanSpecificheConferenza beanConf) {
        //0->DIDATTICA 1-> CONFERENZA;2->  Esame;3->Test di Ingresso; 4->Seduta di Laurea
        Prenotazione p = null;
        // if (tipo==0)
        //p=this.createPrenotazioniDidattica();
        if (tipo == 1) {
            p = this.createPrenotazioneConferenza(idAula, beanConf);
            if (p == null) {
                System.out.println("allert DEBUG");
        }

        } else
            System.out.println("tipo errato in factori!!");
        //    throw Exception;
        //tipo errato
        return p;
    }

    /*public PrenotazioneDidattica createPrenotazioniDidattica(){
        // scrivere prenotazione didattica
    }*/
    public PrenotazioneConferenza createPrenotazioneConferenza(int idAula, BeanSpecificheConferenza bean) {
        return new PrenotazioneConferenza(idAula, bean.getDataInizio(), bean.getFasciaOraria(), bean.getTitoloConferenza());

    }
 }
