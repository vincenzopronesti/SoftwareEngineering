package Vincenzo.boundary;

import Vincenzo.bean.BeanAnnoAccademico;
import Vincenzo.bean.BeanPrenotazioniSegretario;
import Vincenzo.bean.BeanSessione;
import Vincenzo.controller.GestoreDefinizioneSessione;
import Vincenzo.controller.GestoreVisualizzazioneStorico;
import gruppo.DAO.AnnoAccademicoDao;
import gruppo.entity.AnnoAccademico;
import gruppo.entity.PrenotazioneSedutaLaurea;
import gruppo.entity.PrenotazioneTestDiIngresso;
import java.time.Year;
import java.util.List;

public class InterfacciaSegretarioDefinizioneSessione {
    private String usernameSegretario;
    private GestoreDefinizioneSessione controllerDefinizioneSessione;
   
    public InterfacciaSegretarioDefinizioneSessione(String usernameSegretario) {
        this.usernameSegretario=usernameSegretario;
    }
  
    public boolean definizioneSessione(BeanSessione beanSessione) {
        boolean res;
        controllerDefinizioneSessione = new GestoreDefinizioneSessione(this);
        res = controllerDefinizioneSessione.gestioneDefinizioneSessione(beanSessione);
        return res;
    }
       
    public String getUsernameSegretario() {
        return usernameSegretario;
    }

    public void setUsernameSegretario(String usernameSegretario) {
        this.usernameSegretario = usernameSegretario;
    }
}
