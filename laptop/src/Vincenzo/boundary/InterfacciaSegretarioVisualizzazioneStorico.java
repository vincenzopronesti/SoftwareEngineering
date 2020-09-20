package Vincenzo.boundary;

import Vincenzo.bean.BeanAnnoAccademico;
import Vincenzo.bean.BeanPrenotazioniProfessore;
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

public class InterfacciaSegretarioVisualizzazioneStorico {
    private GestoreVisualizzazioneStorico controllerStorico;
    private String usernameSegretario;
       
    public InterfacciaSegretarioVisualizzazioneStorico(String usernameSegretario) {
        this.usernameSegretario=usernameSegretario;
    }
  
    
    public BeanPrenotazioniSegretario storicoPrenotazioni(BeanAnnoAccademico beanAA) {
        this.controllerStorico = new GestoreVisualizzazioneStorico(this);
        BeanPrenotazioniSegretario beanPrenotazioniSegretario = 
                this.controllerStorico.storicoPrenotazioniSegretario(beanAA);
        return beanPrenotazioniSegretario;
    }
    
    public String getUsernameSegretario() {
        return usernameSegretario;
    }

    public void setUsernameSegretario(String usernameSegretario) {
        this.usernameSegretario = usernameSegretario;
    }
}
