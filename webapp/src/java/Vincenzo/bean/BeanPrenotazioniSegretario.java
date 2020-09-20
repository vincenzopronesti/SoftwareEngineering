package Vincenzo.bean;

import gruppo.entity.PrenotazioneSedutaLaurea;
import gruppo.entity.PrenotazioneTestDiIngresso;
import java.util.List;

public class BeanPrenotazioniSegretario {
    private List<PrenotazioneSedutaLaurea> preSed;
    private List<PrenotazioneTestDiIngresso> preTest;
    private boolean annoAccademicoValido;
    
    public BeanPrenotazioniSegretario(List<PrenotazioneSedutaLaurea> preSed, 
            List<PrenotazioneTestDiIngresso> preTest, 
            boolean annoAccademicoValido) {
        this.preSed = preSed;
        this.preTest = preTest;
        this.annoAccademicoValido = annoAccademicoValido;
    }

    public List<PrenotazioneSedutaLaurea> getPreSed() {
        return preSed;
    }

    public void setPreSed(List<PrenotazioneSedutaLaurea> preSed) {
        this.preSed = preSed;
    }

    public List<PrenotazioneTestDiIngresso> getPreTest() {
        return preTest;
    }

    public void setPreTest(List<PrenotazioneTestDiIngresso> preTest) {
        this.preTest = preTest;
    }    

    public boolean isAnnoAccademicoValido() {
        return annoAccademicoValido;
    }

    public void setAnnoAccademicoValido(boolean annoAccademicoValido) {
        this.annoAccademicoValido = annoAccademicoValido;
    }
}
