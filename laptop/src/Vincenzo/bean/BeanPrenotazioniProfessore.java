package Vincenzo.bean;

import gruppo.entity.PrenotazioneConferenza;
import gruppo.entity.PrenotazioneDidattica;

import java.util.List;

public class BeanPrenotazioniProfessore {
    private List<PrenotazioneDidattica> preEs;
    private List<PrenotazioneConferenza> preConf;
    private boolean annoAccademicoValido;
    
    public BeanPrenotazioniProfessore() {
        
    }
    
    public BeanPrenotazioniProfessore(List<PrenotazioneDidattica> preEs, 
            List<PrenotazioneConferenza> preConf, 
            boolean annoAccademicoValido) {
        this.preEs = preEs;
        this.preConf = preConf;
        this.annoAccademicoValido = annoAccademicoValido;
    }
    
    public List<PrenotazioneDidattica> getPreEs() {
        return preEs;
    }

    public void setPreEs(List<PrenotazioneDidattica> preEs) {
        this.preEs = preEs;
    }

    public List<PrenotazioneConferenza> getPreConf() {
        return preConf;
    }

    public void setPreConf(List<PrenotazioneConferenza> preConf) {
        this.preConf = preConf;
    }

    public boolean isAnnoAccademicoValido() {
        return annoAccademicoValido;
    }

    public void setAnnoAccademicoValido(boolean annoAccademicoValido) {
        this.annoAccademicoValido = annoAccademicoValido;
    }
}
