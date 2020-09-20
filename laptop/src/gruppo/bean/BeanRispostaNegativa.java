package gruppo.bean;

import java.time.LocalDate;
import java.util.List;

public class BeanRispostaNegativa extends BeanRisposta {
    private Boolean notAula; //non aule disponibili con quelle caratteristiche
    private boolean erroreSintattico;
    //TODO questi 2 flag sono settati a true se esiste un errore che non permette la prenotazione
    //todo aggiungere altri flag per ogni errore sintattico ??
    //  LocalDate sono la lista di LocalDate non disponibili per la prenotazione

    public BeanRispostaNegativa(List<LocalDate> date, boolean flag) {
        super(date, flag);
        this.notAula = false;


    }

    public BeanRispostaNegativa(Boolean notAula, boolean erroreSintattico) {
        super(false);
        this.notAula = notAula;
        this.erroreSintattico = erroreSintattico;
    }

    @Override
    public String toString() {
        if (this.notAula) {
            return "Non esistono aule con le caratteristiche da lei scelte\n";
        } else if (this.erroreSintattico) {
            return "errore sintattico\n";
        }
        Object[] date = getDate().toArray();
        String outputString = new String();
        outputString += "rispsota negativa \n aule gia prenotate in date:";
        for (int i = 0; i < date.length; i++) {
            outputString += "\t" + ((LocalDate) date[i]).toString();
        }
        return outputString;
    }
}
