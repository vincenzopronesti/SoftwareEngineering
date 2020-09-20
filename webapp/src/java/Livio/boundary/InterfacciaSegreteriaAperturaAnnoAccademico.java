package Livio.boundary;
import Livio.Controller.GestoreAperturaAnnoAccademico;
import Livio.bean.*;

import java.time.LocalDate;
import java.time.Year;

public class InterfacciaSegreteriaAperturaAnnoAccademico {
    private GestoreAperturaAnnoAccademico gestoreAperturaAnnoAccademico;

    public InterfacciaSegreteriaAperturaAnnoAccademico() {
        this.gestoreAperturaAnnoAccademico = new GestoreAperturaAnnoAccademico();
    }

    public BeanRispostaAnnoAccademico aperturaAnnoAccademico(Year year, LocalDate dataInizio, LocalDate dataFine) throws Exception {
        BeanInfoAnnoAccademico beanInfoAnnoAccademico = new BeanInfoAnnoAccademico(year, dataInizio, dataFine);
        if (this.controlloDati(beanInfoAnnoAccademico)) {
            return new BeanRispostaAnnoAccademico("ANNO ACCADEMICO NON VALIDO");
        }
        return this.gestoreAperturaAnnoAccademico.apriAnnoAccademico(beanInfoAnnoAccademico);
    }

    private boolean controlloDati(BeanInfoAnnoAccademico infoAnnoAccademico) {
        if (infoAnnoAccademico.getAnno().isBefore(Year.now())) {
            return true;
        }
        return false;
    }
}
