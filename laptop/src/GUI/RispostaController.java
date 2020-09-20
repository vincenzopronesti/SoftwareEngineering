package GUI;
//TODO ora qui non saranno piu stampate risposte negative

import Andrea.bean.BeanDiRispostaVerificaDisponibilita;
import gruppo.bean.BeanRisposta;
import gruppo.bean.BeanRispostaAffermativa;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/*
NOT CHECKED YET :(
 */
public class RispostaController implements Initializable {
    @FXML
    TextArea outputArea;
    @FXML
    Button loggout;
    @FXML
    Button Menu;

    BeanRisposta beanRisposta;
    //andysnake UC (next 3)
    private BeanDiRispostaVerificaDisponibilita beanDiRispostaVerificaDisponibilita;
    private String rispostaGenerica;
    public RispostaController(String s){this.rispostaGenerica=s;}  //costruttore   per display rispsota generica.... verra chiamata initialize(String s)
    public RispostaController(BeanDiRispostaVerificaDisponibilita rispostaDisponibilita)
    {
        this.beanDiRispostaVerificaDisponibilita = rispostaDisponibilita;
    }

    public RispostaController(BeanRisposta beanRisposta) {
        this.beanRisposta = beanRisposta;

        //setted gruppo.bean di risposta as state
        //data of gruppo.bean passed from another controller
        //this newly istance will be passed to ViewSwap to swap fxml(view) with a setted controller istance passed
        //donw by setController method

    }


    public void initialize() {
        //andysnake
        if(this.rispostaGenerica!=null){
            this.outputArea.setText(this.rispostaGenerica);
        }
        else
            this.outputArea.setText(this.displayBeanRisposta());
    }


    protected String displayBeanRisposta() {
        String risposta = null;
        //TODO NB è sufficiente controllare solo quale dei 2 possibili gruppo.bean di riposa è != null
        //logica di risposta negativa all interno di schermate precedenti a questa
        if (beanRisposta != null && beanRisposta.isValid()) {
            BeanRispostaAffermativa beanRispostaAffermativa = (BeanRispostaAffermativa) beanRisposta;
            //cast di risposta affermativa
            //this.outputArea.setText(beanRispostaAffermativa.toString());
            risposta = beanRispostaAffermativa.toString();
        } else if (beanDiRispostaVerificaDisponibilita != null && !beanDiRispostaVerificaDisponibilita.getNotAula()) {
            risposta = beanDiRispostaVerificaDisponibilita.toString();

        }
        return risposta;
    }

    @FXML
    protected void loggout(javafx.event.ActionEvent event) throws Exception {
        //todo boundary has to die ....
        //swapping view (fxml loggin)
        LogController.interfacciaUtenteLogin.loggout();
        ViewSwap.getIstance().swap(event, ViewSwap.LOGGIN);
    }

    @FXML
    protected void gotoMenu(javafx.event.ActionEvent event) throws Exception {
        //todo boundary has to die ....
        //swapping view (fxml loggin)
        ViewSwap.getIstance().swap(event, ViewSwap.MENU);
    }

}
