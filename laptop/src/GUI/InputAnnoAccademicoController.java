
package GUI;

import Livio.bean.BeanRispostaAnnoAccademico;
import Livio.boundary.InterfacciaSegreteriaAperturaAnnoAccademico;
import Livio.boundary.InterfacciaSegreteriaModificaAnnoAccademico;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.text.Text;

import java.time.Year;

public class InputAnnoAccademicoController {

    @FXML // fx:id="dataInizio"
    private DatePicker dataInizio; // Value injected by FXMLLoader

    @FXML // fx:id="dataFine"
    private DatePicker dataFine; // Value injected by FXMLLoader

    @FXML // fx:id="outputArea"
    private Text outputArea; // Value injected by FXMLLoader

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    void backToMenu(ActionEvent event) throws Exception {
        ViewSwap.getIstance().swap(event, ViewSwap.MENU);
    }

    @FXML
    void creaAnnoAccademico(ActionEvent event) throws  Exception {
        InterfacciaSegreteriaAperturaAnnoAccademico interfacciaSegreteria =
                LogController.interfacciaUtenteLogin.getInterfacciaSegreteriaAperturaAnnoAccademico();
        Year y = Year.now();
        if(this.comboBox.getValue() == null) {
            this.outputArea.setText("SELEZIONARE UN ANNO");
            return;
        }
        else
            y = Year.parse(this.comboBox.getValue().substring(0,4));
        if(this.dataInizio.getValue() == null || this.dataFine.getValue() == null) {
            this.outputArea.setText("SELEZIONARE ENTRAMBE LE DATE");
            return;
        }
        BeanRispostaAnnoAccademico risposta;
        risposta = interfacciaSegreteria.aperturaAnnoAccademico(y, this.dataInizio.getValue(), this.dataFine.getValue());
        this.outputArea.setText(risposta.getRisposta());
    }

    @FXML
    void modificaAnnoAccademico(ActionEvent event) throws Exception {
        InterfacciaSegreteriaModificaAnnoAccademico interfacciaSegreteria =
                LogController.interfacciaUtenteLogin.getInterfacciaSegreteriaModificaAnnoAccademico();
        Year y = Year.now();
        if(this.comboBox.getValue() == null) {
            this.outputArea.setText("SELEZIONARE UN ANNO");
            return;
        }
        else
            y = Year.parse(this.comboBox.getValue().substring(0,4));
        if(this.dataInizio.getValue() == null || this.dataFine.getValue() == null) {
            this.outputArea.setText("SELEZIONARE ENTRAMBE LE DATE");
            return;
        }
        BeanRispostaAnnoAccademico risposta;
        risposta = interfacciaSegreteria.modificaAnnoAccademico(y, this.dataInizio.getValue(), this.dataFine.getValue());
        this.outputArea.setText(risposta.getRisposta());


    }

    @FXML
    void logout(ActionEvent event) throws Exception {
        LogController.interfacciaUtenteLogin.loggout();
        ViewSwap.getIstance().swap(event, ViewSwap.LOGGIN);
    }

    @FXML
    void initialize() {
        ObservableList<String> items = FXCollections.observableArrayList();
        Year y = Year.now();
        int i; // si possono creare anni accademici per i prossimi cento anni!!!
        for(i= 0; i < 100; i++) {
            items.add(y.toString()+"-"+y.plusYears(1).toString());
            y = y.plusYears(1);
        }
        this.comboBox.setItems(items);
    }
}
