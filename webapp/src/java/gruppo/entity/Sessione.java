package gruppo.entity;

import Vincenzo.bean.BeanSessione;
import gruppo.DAO.AnnoAccademicoDao;
import gruppo.DAO.SessioneDao;
import java.time.LocalDate;
import java.time.Year;
import java.util.Iterator;
import java.util.List;

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
    
    public boolean controllaDateSessione() {
        AnnoAccademicoDao annoAccademicoDao = AnnoAccademicoDao.getIstance();
        
        String aa = this.annoAccademico; //beanSessione.getAnnoAccademico();
        Year anno1 = Year.parse(aa.substring(0,4));
        AnnoAccademico annoAccademico = new AnnoAccademico(anno1);
        if (annoAccademicoDao.queryEsistenzaAnnoAccademico(annoAccademico))
            return false; // anno accademico non esistente
        if (this.dataFine.isBefore(this.dataInizio))
            return false; // la fine della sessione non puo' essere prima dell'inizio
//        if (beanSessione.getDataFineSessione().isBefore(beanSessione.getDataInizioSessione()))
//            return false; // la fine della sessione non puo' essere prima dell'inizio
        
        LocalDate dataInizioAnnoAccademico = annoAccademicoDao.queryDataInizioAnnoAccademico(this.annoAccademico);
        LocalDate dataFineAnnoAccademico = annoAccademicoDao.queryDataFineAnnoAccademico(this.annoAccademico);
        if (this.dataFine.isAfter(dataFineAnnoAccademico))
            return false; // la fine della sessione non puo' essere dopo la fine dell'anno accademico
        if (this.dataFine.isBefore(dataInizioAnnoAccademico))
            return false; // l'inizio della sessione non puo' essere prima dell'inizio dell'anno accademico
        return true;
    }
    
    public boolean sessioneDefinibile() {
        boolean inseribile = true;
        
        if (this.controllaDateSessione()) {
            SessioneDao sessioneDao = SessioneDao.getInstance();
            // solo perche altrimenti devo modificare la anche dao
            BeanSessione beanSessioneProposta = new BeanSessione(annoAccademico, dataInizio, 
             dataFine, tipologia);
            List<Sessione> sessioniAA = sessioneDao.querySessioniAnnoAccademico(beanSessioneProposta);
            Iterator<Sessione> i = sessioniAA.iterator();
            while (i.hasNext() && inseribile) {
                Sessione sessioneEsistente = i.next();
                if ((this.dataInizio.isBefore(sessioneEsistente.getDataInizio()) ||
                        this.dataInizio.isEqual(sessioneEsistente.getDataInizio()) )&&
                        (this.dataFine.isAfter(sessioneEsistente.getDataInizio()) || 
                        this.dataFine.isEqual(sessioneEsistente.getDataInizio()) ))
                    inseribile = false;// NO  la data di inizio di una sessione esistente e' compresa nell'intervallo che si vuole definire
                if ((this.dataInizio.isBefore(sessioneEsistente.getDataInizio()) ||
                        this.dataInizio.isEqual(sessioneEsistente.getDataInizio()) )&& 
                        (this.dataFine.isAfter(sessioneEsistente.getDataFine()) || 
                        this.dataFine.isEqual(sessioneEsistente.getDataFine()) ))
                    inseribile = false; // NO l'intervallo che si vuole definire ricopre interamente il periodo di un'altra sessione
                if ((this.dataInizio.isAfter(sessioneEsistente.getDataInizio()) || 
                        this.dataInizio.isEqual(sessioneEsistente.getDataInizio()) )&& 
                        (this.dataFine.isBefore(sessioneEsistente.getDataFine()) || 
                        this.dataFine.isEqual(sessioneEsistente.getDataFine()))) 
                    inseribile = false; // NO l'intervallo che si vuole definire e' gia' incluso in un'altra sessione
                if ((this.dataInizio.isBefore(sessioneEsistente.getDataFine()) || 
                        this.dataInizio.isEqual(sessioneEsistente.getDataFine()) )&& 
                        (this.dataFine.isAfter(sessioneEsistente.getDataFine()) ||
                        this.dataFine.isEqual(sessioneEsistente.getDataFine())) )
                    inseribile = false; // NO la data di fine di una sessione e' compresa nell' intervallo proposto
            }
        }
        return inseribile;
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
