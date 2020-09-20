package gruppo.entity;

import java.time.LocalDate;

public class PrenotazioneSedutaLaurea extends Prenotazione {

    private int numeroSeduta;
    private AnnoAccademico aa;
    public PrenotazioneSedutaLaurea(){};

    public PrenotazioneSedutaLaurea(AnnoAccademico aa, int numeroSeduta, int idAula,
            LocalDate data, String username, gruppo.entity.FasciaOraria fasciaOraria) {
        super(idAula, data, username, fasciaOraria);
        this.aa = aa;
        this.numeroSeduta = numeroSeduta;
    }
    
    @Override
    public String toString() {
        return "Prenotazione seduta di laurea\n" +
                "\tAnno Accademico : " + this.getAA().toStringAnno() + "\n" + 
                "\tNumero seduta: " + this.getNumeroSeduta() + "\n" + 
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

    public int getNumeroSeduta() {
        return numeroSeduta;
    }

    public void setNumeroSeduta(int numeroSeduta) {
        this.numeroSeduta = numeroSeduta;
    } 
}
