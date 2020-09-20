package GUI;

import Andrea.boundary.InterfacciaVisualizzaPrenAttTot;
import gruppo.bean.BeanCaratteristicheAula;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class InputVisualizzaPrenotazioniTotA  {
    @FXML
    private AnchorPane prenotazioniSuTipoAula;
    @FXML
    private AnchorPane prenotazioniSuTipoEvento;
    @FXML
    private CheckBox triggerPrenotazioniTipoAula;
    @FXML
    private CheckBox triggerPrenotazioniTipoEvento;
    @FXML
    private Button ok;
    @FXML
    private CheckBox proiettoreFlag;
    @FXML
    private CheckBox micFlag;
    @FXML
    private CheckBox lavagnaFlag;
    @FXML
    private CheckBox lavagnaInterFlag;
    @FXML
    private CheckBox preseFlag;
    @FXML
    private CheckBox ethernetFlag;

    @FXML
    private TextField numeroPosti;

    @FXML
    ComboBox<String> tipoEvento;
    @FXML
    TextArea negativeTextArea;
    @FXML
    Button backToMenu;
    @FXML
    Label rispostaNegativa;

    @FXML
    protected void setTriggerPrenotazioniTipoAula(javafx.event.ActionEvent event){
        if(this.prenotazioniSuTipoAula.isDisable())
            this.prenotazioniSuTipoAula.setDisable(false);
        else
            this.prenotazioniSuTipoAula.setDisable(true);
    }
    @FXML
    protected void setTriggerPrenotazioniTipoEvento(javafx.event.ActionEvent event){
        if(this.prenotazioniSuTipoEvento.isDisable())
            this.prenotazioniSuTipoEvento.setDisable(false);
        else
            this.prenotazioniSuTipoEvento.setDisable(true);
    }
    private BeanCaratteristicheAula beanCaratteristicheAulaFill(){

        String numeroPosti = this.numeroPosti.getText();

        int numPosti = Integer.parseInt(this.numeroPosti.getText());
        boolean proiettoreFlag = this.proiettoreFlag.isSelected();
        boolean micFlag = this.micFlag.isSelected();
        boolean lavagnaFlag = this.lavagnaFlag.isSelected();
        boolean lavagnaInterFlag = this.lavagnaInterFlag.isSelected();
        boolean preseFlag = this.preseFlag.isSelected();
        boolean ethernetFlag = this.ethernetFlag.isSelected();

        BeanCaratteristicheAula beanCaratteristicheAula =
                new BeanCaratteristicheAula(numPosti, proiettoreFlag, micFlag, lavagnaFlag, lavagnaInterFlag, preseFlag, ethernetFlag);
        System.out.println("bean caratteristiceh aule inserito..\n"+beanCaratteristicheAula);
        return beanCaratteristicheAula;

    }
    @FXML
    protected void startSearch (javafx.event.ActionEvent event) throws Exception {
        //TODO ADD CONTROLLO SINTATTICO LOGICA....
        System.out.println("search start...");
        System.out.println("trigger status...\n search su tipo Aula:"+this.triggerPrenotazioniTipoAula.isSelected());
        System.out.println("trigger status...\n search su tipo Evento:"+this.triggerPrenotazioniTipoEvento.isSelected());
        System.out.println("combobox selected....\t:"+this.tipoEvento.getSelectionModel().getSelectedItem());
        InterfacciaVisualizzaPrenAttTot bounadryUC11 = LogController.interfacciaUtenteLogin.getInterfacciaVisPrenAttTot();

        BeanCaratteristicheAula beanCaratteristicheAula=null;
        String tipoEvento="";

        //settati valori iniziali per logica controllo / discriminazione metodo da invocare in boundary...
        //vedi boundary invocata...
        if ( this.triggerPrenotazioniTipoAula.isSelected())
            beanCaratteristicheAula= this.beanCaratteristicheAulaFill();
        if(this.triggerPrenotazioniTipoEvento.isSelected() )
            tipoEvento=this.tipoEvento.getSelectionModel().getSelectedItem();
        //ora ho input per boundary... lei capira ....
        String risposta= bounadryUC11.visualizzaPrenotazioniSuEventoEAula(beanCaratteristicheAula,tipoEvento);
        RispostaController nextScreenController = new RispostaController(risposta);
          // inizializzato il controller con la stringa di risposta
        ViewSwap.getIstance().swap(event,ViewSwap.RISPOSTA,nextScreenController);

    }
    @FXML
    protected void backToMenu(javafx.event.ActionEvent event) throws Exception {
        ViewSwap.getIstance().swap(event,ViewSwap.MENU);
    }




    public void initialize() {
        this.tipoEvento.setItems(FXCollections.observableArrayList( "CONFERENZA","SEDUTADILAUREA","TESTDIINGRESSO","ESAME"));

    }
    public void initialize(String s){
        this.negativeTextArea.setText(s);   //initialize text area
    }
}
