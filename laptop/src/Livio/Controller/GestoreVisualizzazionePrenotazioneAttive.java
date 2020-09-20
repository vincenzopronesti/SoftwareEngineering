package Livio.Controller;
import gruppo.DAO.*;


import gruppo.entity.Prenotazione;

import Livio.bean.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GestoreVisualizzazionePrenotazioneAttive {
    private List<Prenotazione> prenotazioniAttive;

    public GestoreVisualizzazionePrenotazioneAttive(String user) throws Exception {

        this.visualizzaPrenotazioneAttive(user);
    }

    private void visualizzaPrenotazioneAttive(String user) throws Exception {
        PrenotazioneDao prenotazioneDao = PrenotazioneDao.getIstance();
        try {
            PrenotazioneDao.getLock().lock();
            this.prenotazioniAttive = prenotazioneDao.queryPrenotazioneAttive(user);
            PrenotazioneDao.getLock().unlock();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public BeanRispostaPrenotazioniAttive ottieniRisposta() {
        String risposta;
        if(this.prenotazioniAttive.isEmpty()) {
             risposta = "NON HA PRENOTAZIONI ATTIVE";
            return new BeanRispostaPrenotazioniAttive(risposta);
        }

        Iterator<Prenotazione> i = this.prenotazioniAttive.iterator();
        risposta = new String();
        while(i.hasNext()) {
            risposta = risposta + i.next().toString()+ "\n";
        }
        return new BeanRispostaPrenotazioniAttive(risposta);
    }
}
