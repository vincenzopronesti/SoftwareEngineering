package GUI;

import gruppo.bean.BeanIdAula;
import gruppo.bean.BeanRisposta;
import gruppo.bean.BeanSpecificheConferenza;
import gruppo.boundary.InterfacciaPrenotazioneConferenzeProfessore;
import gruppo.entity.FasciaOraria;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class InputIdAulaConferenza {
    @FXML
    TextField titoloConferenza;
    @FXML
    TextField idAulaText;
    @FXML
    DatePicker dataInizio;
    @FXML
    DatePicker dataFine;
    @FXML
    ComboBox<String> fasciaOrariaComboBox;
    @FXML
    Button okButton;
    @FXML
    Button backToMenuButton;
    @FXML
    TextArea negativeTextArea;
    @FXML
    Text datoMancanteText;
    
    @FXML
    public void backToMenu(ActionEvent event) throws Exception {
        //ritorna al menu
        ViewSwap.getIstance().swap(event,ViewSwap.MENU);
    }
    
    private BeanSpecificheConferenza wrapSpecifConferenza() throws Exception {
        //todo fasciaoraria
        //tmp FasciaOraria autoset to prima
        FasciaOraria fasciaOraria;
        if(this.fasciaOrariaComboBox.getValue() == null) {
            datoMancanteText.setText("Inserire la fascia oraria\n"); 
            return null;
        } else {
            fasciaOraria = FasciaOraria.valueOf(this.fasciaOrariaComboBox.getValue());
        }
        String titoloConf = this.titoloConferenza.getText();
        if (titoloConf.isEmpty()) {
            datoMancanteText.setText("Inserire il titolo della conferenza\n"); 
            return null;
        }
        LocalDate inizio= this.dataInizio.getValue();
        if (inizio == null) {
            datoMancanteText.setText("Inserire la data di inizio\n");
            return null;
        }
        LocalDate fine= this.dataFine.getValue();
        if (fine == null) {
            datoMancanteText.setText("Inserire la data di fine\n");
            return null;
        }
        BeanSpecificheConferenza beanSpConf= new BeanSpecificheConferenza (titoloConf, inizio,fine, fasciaOraria, GuiMain.profId);

        return beanSpConf;
    }
    
    private BeanIdAula wrapIdAulaField() {
        String idAulaString;
        idAulaString = this.idAulaText.getText();
        if (idAulaString.isEmpty()) {
            datoMancanteText.setText("Inserire l'id dell'aula\n");
            return null;
        }
        int idAula = Integer.parseInt(this.idAulaText.getText());
        BeanIdAula beanIdAula = new BeanIdAula(idAula);
        return beanIdAula;
        
    }
    
    @FXML
    public void queryToBoundary(ActionEvent event) throws Exception {
        //TODO POSSIBILE IN CASO DI RISPOSTA NEGATIVA RESTARE QUI E MODIFICARE CAMPI?
        //|--> io penso che logica di trovare modo di migliorare prenotazione va in altra classe/controller...

        //questo metodo prende caratteristiche richiesta per queries
        //e si collega con boundary

        System.out.println("buttonPressed");
        BeanIdAula beanIdAula= this.wrapIdAulaField();
        BeanSpecificheConferenza beanSpecificheConferenza= this.wrapSpecifConferenza();
        System.out.println("caratterestiche>"+beanIdAula+"\n\n"+beanSpecificheConferenza);

        if (beanSpecificheConferenza != null && beanIdAula != null) {
            InterfacciaPrenotazioneConferenzeProfessore boundaryUCGroup= LogController.interfacciaUtenteLogin.getInterfacciaProf();
            BeanRisposta risposta = boundaryUCGroup.prenotazioneConId(beanSpecificheConferenza, beanIdAula);
            System.out.println(risposta);
            if (risposta.isValid()==false){
                this.gestistiRispostaNegativa(risposta);
                return;
                //N.B. in caso di risposta negativa non si accede a rispsota controller
            }
            System.out.println("debug...bean di risposta"+risposta);
            RispostaController rispostaController = new RispostaController(risposta);
            //passato stato di bean di risposta a altro controller
            ViewSwap.getIstance().swap(event,ViewSwap.RISPOSTA, rispostaController);
            //swapped to RispostaController with state... inside instance under reference rispostaCOntroller
        }
    }

    private void gestistiRispostaNegativa(BeanRisposta risposta) {
        //TODO SAREBBE BELLO SE LABEL E TEXT AREA COMPARISSERO SOLO DA QUESTO METODO IN POI
        this.negativeTextArea.setText(risposta.toString());
    }

    @FXML
    void initialize() {
        ObservableList<String> items = FXCollections.observableArrayList();
        items.add("prima");
        items.add("seconda");
        items.add("terza");
        items.add("quarta");
        this.fasciaOrariaComboBox.setItems(items);
    }
}
