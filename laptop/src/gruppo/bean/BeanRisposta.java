package gruppo.bean;
//TODO risposte avnno messe più chiare e specificare meglio gli errori sintattici.
//TODO tutte queste cose limite mese vanno aggiunte nelglossario del progettore

import java.time.LocalDate;
import java.util.List;

public abstract class BeanRisposta {
    private List<LocalDate> date;
    private boolean isValid;

    public BeanRisposta() {
    }

    public BeanRisposta(boolean isValid) {
        this.isValid = isValid;
    }

    public BeanRisposta(List<LocalDate> date, boolean flag) {
        this.setDate(date);
        this.setValid(flag);
    }


    public List<LocalDate> getDate() {
        return this.date;
    }

    public void setDate(List<LocalDate> date) {
        this.date = date;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }


}
