package Andrea.bean;

import gruppo.entity.FasciaOraria;

import java.time.LocalDate;

public class BeanDataFasciaOraria {
    private FasciaOraria fasciaOraria;
    private LocalDate data;

    public BeanDataFasciaOraria(FasciaOraria fasciaOraria, LocalDate data) {
        this.fasciaOraria = fasciaOraria;
        this.data = data;
    }

    public void setFasciaOraria(FasciaOraria fasciaOraria) {
        this.fasciaOraria = fasciaOraria;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public FasciaOraria getFasciaOraria() {
        return fasciaOraria;
    }

    public LocalDate getData() {
        return data;
    }
}
