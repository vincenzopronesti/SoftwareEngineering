package Andrea.boundary;

import Andrea.controller.GestoreVisualizzaPrenAttTot;
import gruppo.bean.BeanCaratteristicheAula;
import gruppo.entity.Prenotazione;

import java.util.Iterator;
import java.util.List;

public class InterfacciaVisualizzaPrenAttTot {

    private GestoreVisualizzaPrenAttTot controllerUC;

    public InterfacciaVisualizzaPrenAttTot(){
        this.controllerUC=new GestoreVisualizzaPrenAttTot();
    }
    private List<Prenotazione> visualizzaPrenotazioniSuTipoDiEvento(String tipoEvento){
        //TODO tipoEvento ControlloSintattico...
        //tipoEvento..={CONFERENZA,SEDUTADILAUREA,TESTDIINGRESSO,ESAME}
          controllerUC.ricercaSuTipoEvento(tipoEvento);
          System.out.println("tornato alla boundary");
          return controllerUC.getPrenotazioniComplessiveTipoEvento();

    }

    private List<Prenotazione> visualizzaPrenotazioniSuTipoDiAula(BeanCaratteristicheAula beanCarAula) throws Exception {
        controllerUC.ricercaSuTipodiAula(beanCarAula);
        System.out.println("returned to boundary");
        return controllerUC.getPrenotazioniComplessiveTipoAula();

    }
    public String visualizzaPrenotazioniSuEventoEAula(BeanCaratteristicheAula bean, String tipoEvento) throws Exception {
        //todo unico punto di accesso di gui...
        if (bean==null && tipoEvento.equals("")){
            //CASO INPUT FORNITI SCORRETTI.... ESCO E STAMPO SU stderr
            System.err.println("invalido input per boundary UC 11");
            return "ERRORE NEL INPUT";
        }
        // in base a quale dei 2 input è not null discrimino quale metodo invocare...
        //trovo prenotazioni su SOLO evento/car aule... o in else finale entrambi :)
        if(tipoEvento.equals("")){
            List<Prenotazione> out= this.visualizzaPrenotazioniSuTipoDiAula(bean);
            return this.generaRisposta(out);
        }
        else if(bean==null){
            List<Prenotazione> out= this.visualizzaPrenotazioniSuTipoDiEvento(tipoEvento);
            return this.generaRisposta(out);
        }
        else {
            List<Prenotazione> risposta;
            controllerUC.ricercaSuTipodiAula(bean);
            controllerUC.ricercaSuTipoEvento(tipoEvento);
            risposta = controllerUC.intersezione();
            return this.generaRisposta(risposta);

        }
    }
    private String generaRisposta(List<Prenotazione> prenotazioni){
        /*
        genera stringa rispsota in base a liste di prenotazioni
         */
        if (prenotazioni.size()==0)
            return "RISULTATO VUOTO...";
        String risposta=new String();
        Iterator<Prenotazione> i = prenotazioni.iterator();
        while(i.hasNext())
        {
            Prenotazione p =i.next();
            risposta+=p.toString()+"\n\n";
        }
        System.out.println(risposta+"RISPOSTA GENERATA \n\n");
        return  risposta;
    }

}
