package Andrea.bean;

import java.util.Iterator;
import java.util.List;

public class BeanDiRispostaVerificaDisponibilita {
    private Boolean  notAula; //non aule disponibili con quelle caratteristiche
    private  boolean erroreSintattico;
    //TODO errore sintattico non dovrebbe esserci con GUI ?
    private List<Integer> idAuleDisponibili;

    @Override
    public String toString() {
        if (idAuleDisponibili.isEmpty()) {
            return "non ci sono aule disponibili con le caratteristiche specificate";
        }
        String outString=  "disponibili\t" + idAuleDisponibili.size() +"aule...\n\n";

        outString+="gli id delle aule sono: \n";
        Iterator<Integer> i=idAuleDisponibili.iterator();
        while(i.hasNext()){
            outString+="=>>\t\t\t\t"+i.next().toString()+"\n";
        }
        return outString;
    }


    public BeanDiRispostaVerificaDisponibilita(Boolean notAula, boolean erroreSintattico, List<Integer> idAuleDisponibili) {
        this.notAula = notAula;
        this.erroreSintattico = erroreSintattico;
        this.idAuleDisponibili = idAuleDisponibili;
    }

    public Boolean getNotAula() {
        return notAula;
    }

    public boolean isErroreSintattico() {
        return erroreSintattico;
    }

    public List<Integer> getIdAuleDisponibili() {
        return idAuleDisponibili;
    }
}
