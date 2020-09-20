package Livio.bean;

import java.time.LocalDate;
import java.time.Year;

public class BeanInfoAnnoAccademico {
    private LocalDate dataInizio;
    private LocalDate dataFine;
    private Year anno;
    
    public BeanInfoAnnoAccademico() {}

    public BeanInfoAnnoAccademico(Year anno, LocalDate dataInizio, LocalDate dataFine ) {
        this.setDataInizio(dataInizio);
        this.setDataFine(dataFine);
        this.setAnno(anno);
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

    public Year getAnno() {
        return anno;
    }

    public void setAnno(Year anno) {
        this.anno = anno;
    }
}
