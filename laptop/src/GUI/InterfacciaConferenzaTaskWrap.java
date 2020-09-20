package GUI;

import gruppo.boundary.InterfacciaPrenotazioneConferenzeProfessore;
import gruppo.bean.BeanCaratteristicheAula;
import gruppo.bean.BeanRisposta;
import gruppo.bean.BeanSpecificheConferenza;
import javafx.concurrent.Task;

public class InterfacciaConferenzaTaskWrap extends Task<BeanRisposta> {
    private InterfacciaPrenotazioneConferenzeProfessore boundary;
    private BeanCaratteristicheAula beanCar;
    private BeanSpecificheConferenza beanSpecAula;


    public InterfacciaConferenzaTaskWrap(InterfacciaPrenotazioneConferenzeProfessore boundary, BeanSpecificheConferenza
            beanSpec, BeanCaratteristicheAula beanCar) {
        this.boundary = boundary;
        this.beanSpecAula = beanSpec;
        this.beanCar = beanCar;
    }

    @Override
    protected BeanRisposta call() throws Exception {
        return boundary.prenotazioneConCaratteristiche(beanSpecAula, beanCar);

    }
}
