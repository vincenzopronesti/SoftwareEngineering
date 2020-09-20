package gruppo.entity;

import java.time.LocalDate;
import java.time.Year;

public class AnnoAccademico {
        private Year anno1;  //anno1 si riferisce al primo anno1( es: 2017/2018) anno1 = 2017
        private Year anno2;
        private LocalDate dataInizio;
        private LocalDate dataFine;

    public AnnoAccademico(Year anno1, LocalDate dataInizio, LocalDate dataFine) {
        this.setAnno1(anno1);
        this.setAnno2(anno1.plusYears(1));
        this.setDataInizio(dataInizio);
        this.setDataFine(dataFine);
    }
    
    public AnnoAccademico(Year anno1) {
        this.anno1 = anno1;
        this.anno2 = anno1.plusYears(1);
    }
    
    @Override
    public String toString() {
        return
                "anno1=" + getAnno1() + "-" + getAnno2() +
                ", dataInizio=" + getDataInizio() +
                ", dataFine=" + getDataFine() + "\n";
    }

    public String toStringAnno() {
        return ""+this.anno1 +"-" +this.anno2;
    }

    public Year getAnno1() {
        return anno1;
    }

    public void setAnno1(Year anno1) {
        this.anno1 = anno1;
    }

    public Year getAnno2() {
        return anno2;
    }

    public void setAnno2(Year anno2) {
        this.anno2 = anno2;
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
}
