package GUI;

import Vincenzo.bean.BeanAnnoAccademico;
import Vincenzo.bean.BeanPrenotazioniSegretario;
import Vincenzo.boundary.InterfacciaSegretarioVisualizzazioneStorico;
import gruppo.boundary.InterfacciaUtenteLogin;
import gruppo.entity.PrenotazioneSedutaLaurea;
import gruppo.entity.PrenotazioneTestDiIngresso;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

import java.time.Year;
import java.util.Iterator;
import java.util.List;

public class RicercaPrenotazioniSegrController {
    @FXML
    Button cerca;
    @FXML
    ComboBox<String> inputAA;
    @FXML
    TextArea areaRisposta;
    @FXML
    Button menu;
    @FXML
    Button logoutButton;
    protected static InterfacciaUtenteLogin interfacciaUtenteLogin;
    
    @FXML
    // javafx.event.
    public void queryToBoundary(ActionEvent event) throws Exception {
        Year y;
        if(this.inputAA.getValue() == null)
            y = Year.now();  //anno iniziale di default
        else
            y = Year.parse(this.inputAA.getValue().substring(0,4));
        BeanAnnoAccademico beanAA;
        String annoA = "" + y + "-" + y.plusYears(1);
        beanAA = new BeanAnnoAccademico(annoA);
        InterfacciaSegretarioVisualizzazioneStorico boundaryInterfacciaSegretario = new
                InterfacciaSegretarioVisualizzazioneStorico(LogController.interfacciaUtenteLogin.getUsername());
System.out.println("starting with seg: " + LogController.interfacciaUtenteLogin.getUsername());
                //LogController.interfacciaUtenteLogin.getInterfacciaSegretario();
        //BoundaryTaskWrap boundaryTaskWrap = new BoundaryTaskWrap(
        //        boundaryInterfacciaSegretario, beanAA);
        BeanPrenotazioniSegretario risposta = boundaryInterfacciaSegretario.storicoPrenotazioni(beanAA);
        this.areaRisposta.setText(this.stampaPrenotazioni(risposta));
    }
    
    private String stampaPrenotazioni(BeanPrenotazioniSegretario risposta) {
        String res;
        if (risposta.isAnnoAccademicoValido()) {
            res = "Prenotazioni sedute di laurea\n";
            List<PrenotazioneSedutaLaurea> listPreSed = risposta.getPreSed();
            List<PrenotazioneTestDiIngresso> listPreTest = risposta.getPreTest();
            if (listPreSed.isEmpty()) {
                res += ">> Non ci sono prenotazioni per sedute di laurea\n\n";
            } else {
                Iterator<PrenotazioneSedutaLaurea> i = listPreSed.iterator();
                while (i.hasNext()) {
                    PrenotazioneSedutaLaurea pSL = i.next();
                    res += ">> " + pSL + "\n";
                }
                res += "\n";
            }
            res += "Prenotazioni per test di ingresso\n";
            if (listPreTest.isEmpty()) {
                res += ">> Non ci sono prenotazioni per test di ingresso\n\n";
            } else {
                Iterator<PrenotazioneTestDiIngresso> i = listPreTest.iterator();
                while (i.hasNext()) {
                    PrenotazioneTestDiIngresso pTI = i.next();
                    res += ">> " + pTI + "\n";
                }
                res += "\n";
            }
        } else {
            res = "Errore: Anno accademico inserito non valido\n";
        }
        return res;
    }
    
    @FXML
    public void backToMenu(ActionEvent event) throws Exception {
        //ritorna al menu
        ViewSwap.getIstance().swap(event,ViewSwap.MENU);
    }
    
    @FXML
    protected void logout(ActionEvent event) throws Exception {
        //todo destroy possible boundaries
        //...
        //swap view (fxml)
        LogController.interfacciaUtenteLogin.loggout();
        ViewSwap.getIstance().swap(event,ViewSwap.LOGGIN);
    }
    
    @FXML
    void initialize() {
        ObservableList<String> items = FXCollections.observableArrayList();
        Year y = Year.now();
        int i;
        for(i= 0; i < 10; i++) {
            items.add(y.minusYears(2).toString()+"-"+y.minusYears(1).toString());
            y = y.plusYears(1);
        }
        this.inputAA.setItems(items);
    }
}
