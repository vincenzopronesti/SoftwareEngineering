package gruppo.bean;


import java.time.LocalDate;
import java.util.List;

public class BeanRispostaAffermativa extends BeanRisposta {
    private List<Integer> idAule;
    //private int idPrenotazioni;

    //NB liste di LocalDate e id aule devono essere correlate per indice

    public BeanRispostaAffermativa(List<Integer> idAule, boolean flag, List<LocalDate> date) {
        super(date, flag);
        this.idAule = idAule;


    }

    public String toString() {
        Object[] date = getDate().toArray();
        Object[] idAule = this.idAule.toArray();
        String outputString = new String();
        outputString += "Risposta affermativa !!\n\n";
        for (int i = 0; i < idAule.length; i++) {
            outputString += "prenotata aula:\t" + String.valueOf((int) idAule[i]) + "\tin data\t" + date[i].toString()+"\n\n";
        }
        outputString+="\n\n";
        //generated string for affermative response
        //aula a <--> data assegnazione aula a
        return outputString;
    }


}
