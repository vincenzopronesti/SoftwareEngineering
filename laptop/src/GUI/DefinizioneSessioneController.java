package GUI;

import Vincenzo.bean.BeanAnnoAccademico;
import Vincenzo.bean.BeanSessione;
import Vincenzo.boundary.InterfacciaSegretarioDefinizioneSessione;
import gruppo.entity.TipoSessione;
import gruppo.boundary.InterfacciaUtenteLogin;

import java.time.LocalDate;
import java.time.Year;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.text.Text;

public class DefinizioneSessioneController {
    private static InterfacciaUtenteLogin interfacciaUtenteLogin;
    
    @FXML
    private DatePicker dataInizioSessioneDatePicker;
    @FXML
    private DatePicker dataFineSessioneDatePicker;
    @FXML
    private ComboBox<String> tipoSessioneComboBox;
    @FXML
    private ComboBox<String> annoAccademicoComboBox;
    @FXML
    private Text areaRisposta;
    @FXML
    private Button okButton;
    @FXML
    private Button menuButton;
    @FXML
    private Button logoutButton;
    
    @FXML
    private BeanSessione wrapSessioneRichiesta() {
        LocalDate dataInizioSessione = dataInizioSessioneDatePicker.getValue();
        if (dataInizioSessione == null) {
            areaRisposta.setText("Inserire data inizio sessione\n");
            return null;
        }
        LocalDate dataFineSessione = dataFineSessioneDatePicker.getValue();
        if (dataFineSessione == null) {
            areaRisposta.setText("Inserire data fine sessione\n");
            return null;
        }
        String tipoSessioneString = tipoSessioneComboBox.getValue();
        if (tipoSessioneString == null) {
            areaRisposta.setText("Inserire tipo sessione\n");
            return null;
        }  
        TipoSessione tipoSessione = TipoSessione.valueOf(tipoSessioneString);     
        Year y;
        if(this.annoAccademicoComboBox.getValue() == null)
            y = Year.now();  //anno iniziale di default
        else
            y = Year.parse(this.annoAccademicoComboBox.getValue().substring(0,4));
        BeanAnnoAccademico beanAA;
        String annoA = "" + y + "-" + y.plusYears(1);
        BeanSessione beanSessioneProposta = new BeanSessione(annoA, 
                dataInizioSessione, dataFineSessione, tipoSessione);
        return beanSessioneProposta;
    }
    
    @FXML
    public void queryToBoundary(ActionEvent event) throws Exception {
        BeanSessione beanSessioneProposta = this.wrapSessioneRichiesta();
        if (beanSessioneProposta != null) {
            InterfacciaSegretarioDefinizioneSessione boundaryInterfacciaSegretario = new
                    InterfacciaSegretarioDefinizioneSessione(LogController.interfacciaUtenteLogin.getUsername());
            if (boundaryInterfacciaSegretario.definizioneSessione(beanSessioneProposta)) {
                areaRisposta.setText("Sessione prenotata correttamente\n" + beanSessioneProposta);
            } else {
                areaRisposta.setText("Impossibilile inserire la sessione richiesta");
            }
        }
    }
    
    @FXML
    public void backToMenu(ActionEvent event) throws Exception {
        ViewSwap.getIstance().swap(event, ViewSwap.MENU);
    }
    
    @FXML
    public void logout(ActionEvent event) throws Exception {
        LogController.interfacciaUtenteLogin.loggout();
        ViewSwap.getIstance().swap(event, ViewSwap.LOGGIN);
    }
    
    @FXML
    void initialize() {
        ObservableList<String> itemsTS = FXCollections.observableArrayList();
        itemsTS.add("estiva");
        itemsTS.add("invernale");
        itemsTS.add("autunnale");
        itemsTS.add("straordinaria");
        this.tipoSessioneComboBox.setItems(itemsTS);
        
        ObservableList<String> itemsAA = FXCollections.observableArrayList();
        Year y = Year.now();
        int i;
        for(i= 0; i < 10; i++) {
            itemsAA.add(y.minusYears(2).toString()+"-"+y.minusYears(1).toString());
            y = y.plusYears(1);
        }
        this.annoAccademicoComboBox.setItems(itemsAA);
/*
        LocalDate oggi = LocalDate.now();
        if (oggi.isAfter(LocalDate.of(oggi.getYear(), Month.MARCH, 1)) || 
                oggi.isEqual(LocalDate.of(oggi.getYear(), Month.MARCH, 1))) {
            this.annoAccademicoPrecedenteCheckBox.setSelected(false);
            this.annoAccademicoPrecedenteCheckBox.setDisable(true);
//            this.annoAccademicoPrecedenteCheckBox.setEnable(true);
//        } else {  
        }
*/
    }
}
