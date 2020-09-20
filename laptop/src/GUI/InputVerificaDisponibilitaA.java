package GUI;

import Andrea.bean.BeanDataFasciaOraria;
import Andrea.bean.BeanDiRispostaVerificaDisponibilita;
import Andrea.boundary.InterfVerificaDisponibilita;
import gruppo.bean.BeanCaratteristicheAula;
import gruppo.entity.FasciaOraria;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.Period;

//end gruppo imports...

//andysnake uc 10
public class InputVerificaDisponibilitaA {

    @FXML
    Button button;
    @FXML
    CheckBox proiettoreFlag;
    @FXML
    CheckBox micFlag;
    @FXML
    CheckBox lavagnaFlag;
    @FXML
    CheckBox lavagnaInterFlag;
    @FXML
    CheckBox preseFlag;
    @FXML
    CheckBox ethernetFlag;

    @FXML
    TextField numeroPosti;
    @FXML
    DatePicker data;

    @FXML
    ComboBox<FasciaOraria> fasciaOrariaComboBox;
    @FXML
    TextArea negativeTextArea;
    @FXML
    Button backToMenu;
    @FXML
    Label rispostaNegativa;
    //todo ---- fascia oraria wrap logic ....

    public void initialize(){
        fasciaOrariaComboBox.setItems( FXCollections.observableArrayList( FasciaOraria.values()));

    }
    @FXML
    public void verificaDisponibilita(javafx.event.ActionEvent event) throws Exception {
        BeanCaratteristicheAula beanCaratteristicheAula = this.wrapCarAulaFields(); //prese caratterstiche da grafica
        LocalDate data = this.data.getValue();
        FasciaOraria fasciaOraria;

        fasciaOraria = this.fasciaOrariaComboBox.getValue();
        BeanDataFasciaOraria beanDataFasciaOraria = new BeanDataFasciaOraria(fasciaOraria, data);
        BeanDiRispostaVerificaDisponibilita risposta;
        //unico controllo sintattico da grafica offerta è se data inserita è indietro nel tempo...
        if(Period.between(LocalDate.now(),beanDataFasciaOraria.getData()).getDays()<0){
            System.out.println("data inserita indietro nel tempo...errore :(");
            this.negativeTextArea.setText("errore sintattico..data inserita indietro nel tempo...");
            return;
        }
        //assumendo che logcontroller ha ottenuto correttamente un istanza di InterfacciaUtente
        InterfVerificaDisponibilita boundaryUCAS = LogController.interfacciaUtenteLogin.getInterfacciaVerificaDisponibilita();
        //NB controllo credenziali eseguito nel momento che ho ottenuto istanza di boundary (referenzia link sopra)
        InterfacciaUC10TaskWrapA task = new InterfacciaUC10TaskWrapA(boundaryUCAS, beanDataFasciaOraria, beanCaratteristicheAula);
        Thread t = new Thread(task);
        t.start();
        //ora il task è partito e eseguira metodo call secondo API javafxconcurrent
        risposta = task.get(); //SAFE block fino a quando task nn ha finito
        //TODO CHECK RISPOSTA NEGATIVA==> NON CI SONO AULE DI QUEL TIPO
        if (risposta.getNotAula()) {
            this.negativeTextArea.setText("NON CI SONO AULE CON LE CARATTERISTICHE INSERITE..");
            return; }
        Initializable rispostaController = new RispostaController(risposta);
        ViewSwap.getIstance().swap(event, ViewSwap.RISPOSTA, rispostaController);
        //swap to next screen con risposta affermativa (vedi to string di bean risposta verifica disponibilita

    }

    private BeanCaratteristicheAula wrapCarAulaFields() {
        //testVerDisponibilita
        //System.out.println(this.ethernetFlag.isSelected()+"preso checkbox stato ethernetFlag");
        //isSelected() ritorna boolean in base se è stato (non) triggerato il checkbox
        String numeroPosti = this.numeroPosti.getText();
        int numPosti = 1;             //default valude only for debug
        if (!numeroPosti.equals("")) { //todo error of invalid input data has to be genereted
            numPosti = Integer.parseInt(this.numeroPosti.getText());
        }
        boolean proiettoreFlag = this.proiettoreFlag.isSelected();
        boolean micFlag = this.micFlag.isSelected();
        boolean lavagnaFlag = this.lavagnaFlag.isSelected();
        boolean lavagnaInterFlag = this.lavagnaInterFlag.isSelected();
        boolean preseFlag = this.preseFlag.isSelected();
        boolean ethernetFlag = this.ethernetFlag.isSelected();

        BeanCaratteristicheAula beanCaratteristicheAula =
                new BeanCaratteristicheAula(numPosti, proiettoreFlag, micFlag, lavagnaFlag, lavagnaInterFlag, preseFlag, ethernetFlag);

        return beanCaratteristicheAula;

    }
    @FXML
    protected void backToMenu(javafx.event.ActionEvent e)   {
        try {
            ViewSwap.getIstance().swap(e,ViewSwap.MENU);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
