package Andrea.boundary;
/*
import gruppo.bean.*;
import gruppo.controller.*;
import gruppo.boundary.*;
import gruppo.entity.*;
import gruppo.DAO.*;
    //end gruppo imports...
import Andrea.boundary.*;
import Andrea.bean.*;
import Andrea.controller.*;
*/// import di TUTTO (intelliJ Refactor schifo)
import Andrea.bean.BeanDataFasciaOraria;
import Andrea.bean.BeanDiRispostaVerificaDisponibilita;
import Andrea.controller.GestoreVerificaDisponibilita;
import gruppo.bean.BeanCaratteristicheAula;

import java.time.LocalDate;

public class InterfVerificaDisponibilita {
    /*
    boundary per attore:Utente
    UC:verifica disponibilita
     */

    //reference -> controller uc
    private GestoreVerificaDisponibilita gestoreVerificaDisponibilita;

    //ANDYSNAKE UC
    public BeanDiRispostaVerificaDisponibilita
            VerificaDisponibilita(BeanCaratteristicheAula beanCarAula, BeanDataFasciaOraria beanTemp)   {
        if( this.controlloSintattico(beanTemp)==false)
            return new BeanDiRispostaVerificaDisponibilita(false,true,null);

        this.gestoreVerificaDisponibilita=new GestoreVerificaDisponibilita();
        return this.gestoreVerificaDisponibilita.verificaDisponibilitaConCaratteristiche(beanCarAula,beanTemp);


    }
    private boolean controlloSintattico (BeanDataFasciaOraria beanDataFasciaOraria) {
        if(beanDataFasciaOraria.getData().isBefore(LocalDate.now()))
            return false;
        return true;
    }
}