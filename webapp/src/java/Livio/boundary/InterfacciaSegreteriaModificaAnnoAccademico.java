package Livio.boundary;

import Livio.Controller.GestoreModificaAnnoAccademico;
import Livio.bean.BeanInfoAnnoAccademico;
import Livio.bean.BeanRispostaAnnoAccademico;

import java.time.LocalDate;
import java.time.Year;

public class InterfacciaSegreteriaModificaAnnoAccademico {
    private GestoreModificaAnnoAccademico gestoreModificaAnnoAccademico;

    public InterfacciaSegreteriaModificaAnnoAccademico() {

    }

    public BeanRispostaAnnoAccademico modificaAnnoAccademico(Year y, LocalDate dinizio, LocalDate dfine) {
        BeanInfoAnnoAccademico infoAnnoAccademico = new BeanInfoAnnoAccademico(y, dinizio, dfine);
        this.gestoreModificaAnnoAccademico = new GestoreModificaAnnoAccademico(infoAnnoAccademico);
        if(this.controlloDati(infoAnnoAccademico)) {
            return new BeanRispostaAnnoAccademico("IMPOSSIBILE MODIFICARE ANNO ACCADEMICO DEL PASSATO");
        }
        return this.gestoreModificaAnnoAccademico.modificaAnnoAccademico(infoAnnoAccademico);
    }

    private boolean controlloDati(BeanInfoAnnoAccademico infoAnnoAccademico) {
        if(infoAnnoAccademico.getAnno().isBefore(Year.now())) {
            return true;
        }
        return false;
    }


}
