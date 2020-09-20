package gruppo.entity;

import java.time.LocalDate;

public class PrenotazioneTestDiIngresso extends Prenotazione {
    private AnnoAccademico aa;
    public PrenotazioneTestDiIngresso(){}

    public PrenotazioneTestDiIngresso(AnnoAccademico aa, int idAula,
                                      LocalDate data, String username, gruppo.entity.FasciaOraria fasciaOraria) {
        super(idAula, data, username, fasciaOraria);
        this.aa = aa;
    }

    @Override
    public String toString() {
        return "Prenotazione test d'ingresso\n" +
                "\tAnno accademico: " + this.getAA().toStringAnno() + "\n" + 
                "\tId Aula: " + this.getIdAula() + "\n" + 
                "\tData: " + this.getData() + "\n" + 
                "\tId Segretario: " + this.getUsername() + "\n" +
                "\tFascia oraria: " + this.getFasciaOraria() + "\n";
    }

    public AnnoAccademico getAA() {
        return aa;
    }

    public void setAA(AnnoAccademico aa) {
        this.aa = aa;
    }
}
