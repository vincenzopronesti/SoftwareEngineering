package Vincenzo.boundary;

import Vincenzo.bean.BeanAnnoAccademico;
import Vincenzo.bean.BeanPrenotazioniProfessore;
import Vincenzo.controller.GestoreVisualizzazioneStorico;
import gruppo.DAO.AnnoAccademicoDao;
import gruppo.entity.AnnoAccademico;
import gruppo.entity.PrenotazioneConferenza;
import gruppo.entity.PrenotazioneDidattica;

import java.time.Year;
import java.util.List;

public class InterfacciaProfessoreVisualizzazioneStorico {
    private GestoreVisualizzazioneStorico controllerStorico;
    private String usernameProf;
    
    public InterfacciaProfessoreVisualizzazioneStorico(String usernameProf) {
            this.usernameProf=usernameProf;
    }

    public BeanPrenotazioniProfessore storicoPrenotazioni(BeanAnnoAccademico beanAA) {
        this.controllerStorico = new GestoreVisualizzazioneStorico(this);
        BeanPrenotazioniProfessore beanPrenotazioniProfessore = 
                this.controllerStorico.storicoPrenotazioniProfessore(beanAA);
        return beanPrenotazioniProfessore;
    }
    
    public String getUsernameProf() {
        return usernameProf;
    }

    public void setUsernameProf(String usernameProf) {
        this.usernameProf = usernameProf;
    }
}
