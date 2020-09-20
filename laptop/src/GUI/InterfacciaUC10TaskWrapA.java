package GUI;

import Andrea.bean.BeanDataFasciaOraria;
import Andrea.bean.BeanDiRispostaVerificaDisponibilita;
import Andrea.boundary.InterfVerificaDisponibilita;
import gruppo.bean.BeanCaratteristicheAula;
import javafx.concurrent.Task;
//UC 10 ANDREA
public class InterfacciaUC10TaskWrapA extends Task<BeanDiRispostaVerificaDisponibilita> {
    private InterfVerificaDisponibilita boundary;
    private BeanDataFasciaOraria beanDataFasciaOraria;
    private BeanCaratteristicheAula beanCaratteristicheAula;
    //attributi per wrappare Interfaccia utente... specificatamente questa versione
    //per uc andysnake... visualizza disponibilita aule
        //NB SIA COSTRUTTORE CHE CALL SONO SCRITTI PER UC AS
    public InterfacciaUC10TaskWrapA(InterfVerificaDisponibilita boundary, BeanDataFasciaOraria beanDataFasciaOraria, BeanCaratteristicheAula beanCaratteristicheAula) {

        this.boundary = boundary;
        this.beanDataFasciaOraria = beanDataFasciaOraria;
        this.beanCaratteristicheAula = beanCaratteristicheAula;
    }

    @Override
    protected BeanDiRispostaVerificaDisponibilita call() throws Exception {
        System.out.println("background thread per verifica disponibilita partito..\tid:"+Thread.currentThread().getId());
        BeanDiRispostaVerificaDisponibilita risposta = this.boundary.VerificaDisponibilita (this.beanCaratteristicheAula,
                                                                this.beanDataFasciaOraria);
        System.out.println("background thread per verifica disponibilita ha terminato");

        return risposta;

    }
}
