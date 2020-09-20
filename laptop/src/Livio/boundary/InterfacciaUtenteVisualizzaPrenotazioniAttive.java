package Livio.boundary;

import Livio.Controller.GestoreVisualizzazionePrenotazioneAttive;
import Livio.bean.*;

public class InterfacciaUtenteVisualizzaPrenotazioniAttive {
    String username;
    boolean isLog;
    private GestoreVisualizzazionePrenotazioneAttive gestoreVisualizzazionePrenotazioneAttive;


    public InterfacciaUtenteVisualizzaPrenotazioniAttive(String username) throws Exception {
        this.username = username;
        gestoreVisualizzazionePrenotazioneAttive = new GestoreVisualizzazionePrenotazioneAttive(username);
    }


    public BeanRispostaPrenotazioniAttive visualizzaPrenotazioniAttive() throws Exception {
            //if(!this.isLog) {
              //  throw new Exception();
            //}

          //  this.gestoreVisualizzazionePrenotazioneAttive.visualizzaPrenotazioneAttive(this.username);
            return gestoreVisualizzazionePrenotazioneAttive.ottieniRisposta();

    }






}