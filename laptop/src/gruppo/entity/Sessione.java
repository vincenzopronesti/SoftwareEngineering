package gruppo.entity;

import java.time.LocalDate;

public class Sessione {
    private LocalDate dataInizio;
    private LocalDate dataFine;
    private TipoSessione tipologia;
    private String annoAccademico;
    
    public Sessione(LocalDate dataInizio, LocalDate dataFine, 
            TipoSessione tipologia, String annoAccademico) {
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.tipologia = tipologia;
        this.annoAccademico = annoAccademico;
    }
    
    @Override
    public String toString() {
        return "Sessione:\n" + 
                "\tData inizio: " + this.getDataInizio() + "\n" + 
                "\tData fine: " + this.getDataFine() + "\n" + 
                "\tTipologia: " + this.getTipologia() + "\n" + 
                "\tAnno accademico: " + this.getAnnoAccademico() + "\n";
    }
    
    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDate dataFine) {
        this.dataFine = dataFine;
    }

    public TipoSessione getTipologia() {
        return tipologia;
    }

    public void setTipologia(TipoSessione tipologia) {
        this.tipologia = tipologia;
    }

    public String getAnnoAccademico() {
        return annoAccademico;
    }

    public void setAnnoAccademico(String anno) {
        this.annoAccademico = annoAccademico;
    }
}
