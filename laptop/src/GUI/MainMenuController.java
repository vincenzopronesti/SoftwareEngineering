package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class MainMenuController {
    //logged to acces this controller
    @FXML
    Button PrenotazioneConId;
    @FXML
    Button PrenotazioneConCaratteristiche;
    @FXML
    Button loggout;
    @FXML
    Button visualizzaPrenotazioniAttive;
    @FXML
    Text text;
    //andysnake buttons
    @FXML
    Button visualizzaDisponibilita;
    @FXML
    Button visualizzaPrenotazioniAttiveTot;

    @FXML
    Button definizioneSessione;

    @FXML
    Button storicoPrenotazioni;

    @FXML
    protected void loggout(ActionEvent event) throws Exception {
        //todo destroy possible boundaries
        //...
        //swap view (fxml)
        LogController.interfacciaUtenteLogin.loggout();
        ViewSwap.getIstance().swap(event, ViewSwap.LOGGIN);
    }

    @FXML
    protected void PrenotazioneConId(ActionEvent event) throws Exception {
        //todo GOTO PRENOTAZIONE CON ID CONTROLLER.... SUBTIPE OF SPECIFICACARAULACONTROLLER?
        if (LogController.interfacciaUtenteLogin.getType().equals("Segretario")) {
            text.setText("AZIONE NON CONSENTITA AL SEGRETARIO");
        }
        else{
            ViewSwap.getIstance().swap(event, ViewSwap.INPUTID);

        }
    }


    @FXML
    protected void prenotazioneConCaratteristiche(ActionEvent event) throws Exception {
        //goto another controller
        //todo states to take - send ?
        if (LogController.interfacciaUtenteLogin.getType().equals("Segretario")) {
            this.text.setText("AZIONE NON CONSENTITA AL SEGRETARIO");
        } else {

            ViewSwap.getIstance().swap(event, ViewSwap.INPUTCONFERENZA );
        }
    }
    @FXML
    protected void visualizzaDisponibilita(ActionEvent event) throws Exception {
        if (LogController.interfacciaUtenteLogin.getType()==null) {
            this.text.setText("AZIONE NON CONSENTITA AL SEGRETARIO");
        } else
            ViewSwap.getIstance().swap(event, ViewSwap.VERIFICADISPONIBILITA);
    }

    @FXML
    protected void visualizzaPrenotazioniAttiveToT(ActionEvent event) throws Exception {
        if (!LogController.interfacciaUtenteLogin.getType().equals("Segretario")) {
            this.text.setText("AZIONE NON CONSENTITA AL PROFESSORE");
        } else
            ViewSwap.getIstance().swap(event, ViewSwap.VISUALIZZAPRENATTIVETOT);
    }

    @FXML
    protected void visualizzaPrenotazioniAttive(ActionEvent event) throws Exception {
        //controllo credenziali ?
        ViewSwap.getIstance().swap(event, ViewSwap.RISPOSTAPRENOTAZIONEATTIVE);
    }

    @FXML
    protected void apriUnAnnoAccademico(ActionEvent event) throws Exception {
        if (LogController.interfacciaUtenteLogin.getType().equals("Professore")) {
            this.text.setText("AZIONE NON CONSENTITA AD UN PROFESSORE");
        } else {
            ViewSwap.getIstance().swap(event, ViewSwap.APERTUREANNOACCADEMICO);
        }
    }
    @FXML
    protected void visualizzaStorico(ActionEvent event) throws Exception {
        if (LogController.interfacciaUtenteLogin.getType().equals("Professore"))
            ViewSwap.getIstance().swap(event, ViewSwap.STORICOPRENOTAZIONIPROF);
        else
            ViewSwap.getIstance().swap(event, ViewSwap.STORICOPRENOTAZIONISEGR);
    }

    @FXML
    protected void definisciSessione(ActionEvent event) throws Exception {
        if (LogController.interfacciaUtenteLogin.getType().equals("Professore")) {
            this.text.setText("AZIONE NON CONSENTITA AD UN PROFESSORE");
        } else {
            ViewSwap.getIstance().swap(event, ViewSwap.DEFINIZIONESESSIONE);
        }
    }


}
