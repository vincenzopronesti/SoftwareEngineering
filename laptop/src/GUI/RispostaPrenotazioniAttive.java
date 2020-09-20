package GUI;

import Livio.bean.BeanRispostaPrenotazioniAttive;
import Livio.boundary.InterfacciaUtenteVisualizzaPrenotazioniAttive;

import gruppo.bean.BeanRisposta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class RispostaPrenotazioniAttive {



    @FXML // fx:id="outputArea"
    private TextArea outputArea; // Value injected by FXMLLoader

    @FXML // fx:id="loggout"
    private Button loggout; // Value injected by FXMLLoader

    @FXML // fx:id="menu"
    private Button menu; // Value injected by FXMLLoader

    @FXML
    void gotoMenu(ActionEvent event) throws Exception {
        ViewSwap.getIstance().swap(event, ViewSwap.MENU);
    }

    @FXML
    void loggout(ActionEvent event) throws Exception {
        LogController.interfacciaUtenteLogin.loggout();
        ViewSwap.getIstance().swap(event, ViewSwap.LOGGIN);
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws Exception {

        this.outputArea.setText(this.displayRisposta());


    }

    public String displayRisposta() throws Exception {
        InterfacciaUtenteVisualizzaPrenotazioniAttive interfacciaUtente =
                LogController.interfacciaUtenteLogin.getInterfacciaUtenteViusualizzaPrenotazioniAttive();
        BeanRispostaPrenotazioniAttive rispostaPrenotazioniAttive = interfacciaUtente.visualizzaPrenotazioniAttive();
        return rispostaPrenotazioniAttive.getRisposta();

    }
}