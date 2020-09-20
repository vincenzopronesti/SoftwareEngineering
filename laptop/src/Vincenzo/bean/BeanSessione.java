package Vincenzo.bean;

import gruppo.entity.TipoSessione;
import gruppo.entity.AnnoAccademico;
import java.time.LocalDate;

public class BeanSessione {
    private String annoAccademico;
    private LocalDate dataInizioSessione;
    private LocalDate dataFineSessione;
    private TipoSessione tipologia;
     
    public BeanSessione(String annoAccademico, LocalDate dataInizioSessione, 
            LocalDate dataFineSessione, TipoSessione tipologia) {
        this.annoAccademico = annoAccademico;
        this.dataInizioSessione = dataInizioSessione;
        this.dataFineSessione = dataFineSessione;
        this.tipologia = tipologia;
    }
   
    @Override
    public String toString() {
        return "Sessione:\n" + 
                "\tData inizio: " + this.getDataInizioSessione() + "\n" + 
                "\tData fine: " + this.getDataFineSessione() + "\n" + 
                "\tTipologia: " + this.getTipologia() + "\n" + 
                "\tAnno accademico: " + this.getAnnoAccademico() + "\n";
    }

    public String getAnnoAccademico() {
        return annoAccademico;
    }

    public void setAnnoAccademico(String annoAccademico) {
        this.annoAccademico = annoAccademico;
    }

    public LocalDate getDataInizioSessione() {
        return dataInizioSessione;
    }

    public void setDataInizioSessione(LocalDate dataInizioSessione) {
        this.dataInizioSessione = dataInizioSessione;
    }

    public LocalDate getDataFineSessione() {
        return dataFineSessione;
    }

    public void setDataFineSessione(LocalDate dataFineSessione) {
        this.dataFineSessione = dataFineSessione;
    }   

    public TipoSessione getTipologia() {
        return tipologia;
    }

    public void setTipologia(TipoSessione tipologia) {
        this.tipologia = tipologia;
    }
}
