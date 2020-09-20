package Vincenzo.controller;

import gruppo.DAO.SessioneDao;
import Vincenzo.bean.BeanSessione;
import Vincenzo.boundary.InterfacciaSegretarioDefinizioneSessione;
import gruppo.entity.Sessione;
import gruppo.DAO.AnnoAccademicoDao;
import gruppo.entity.AnnoAccademico;
import java.time.LocalDate;
import java.time.Year;
import java.util.Iterator;
import java.util.List;

public class GestoreDefinizioneSessione {
    private InterfacciaSegretarioDefinizioneSessione segretario;
    
    public GestoreDefinizioneSessione(InterfacciaSegretarioDefinizioneSessione segretario) {
        this.segretario = segretario;
    }
    
    /**
     * controlla che l'anno accademico inserito esista 
     * controlla che l'inizio della sessione sia prima della fine 
     * controlla che la fine della sessione non sia oltre la fine dell'anno accademico 
     * controlla che l'inizio della sessione non sia prima dell'inizio dell'anno accademico
     * @param beanSessione 
     */
//    public boolean controllaDateSessione(BeanSessione beanSessione) {
//        AnnoAccademicoDao annoAccademicoDao = AnnoAccademicoDao.getIstance();
//        
//        String aa = beanSessione.getAnnoAccademico();
//        Year anno1 = Year.parse(aa.substring(0,4));
//        AnnoAccademico annoAccademico = new AnnoAccademico(anno1);
//        if (annoAccademicoDao.queryEsistenzaAnnoAccademico(annoAccademico))
//            return false; // anno accademico non esistente
//        if (beanSessione.getDataFineSessione().isBefore(beanSessione.getDataInizioSessione()))
//            return false; // la fine della sessione non puo' essere prima dell'inizio
//        
//        LocalDate dataInizioAnnoAccademico = annoAccademicoDao.queryDataInizioAnnoAccademico(beanSessione.getAnnoAccademico());
//        LocalDate dataFineAnnoAccademico = annoAccademicoDao.queryDataFineAnnoAccademico(beanSessione.getAnnoAccademico());
//        if (beanSessione.getDataFineSessione().isAfter(dataFineAnnoAccademico))
//            return false; // la fine della sessione non puo' essere dopo la fine dell'anno accademico
//        if (beanSessione.getDataInizioSessione().isBefore(dataInizioAnnoAccademico))
//            return false; // l'inizio della sessione non puo' essere prima dell'inizio dell'anno accademico
//        return true;
//    }
    
    /**
     * @param beanSessioneProposta 
     */
    public boolean gestioneDefinizioneSessione(BeanSessione beanSessioneProposta) {
        Sessione sessioneDaDefinire = new Sessione(beanSessioneProposta.getDataInizioSessione(), 
                beanSessioneProposta.getDataFineSessione(), beanSessioneProposta.getTipologia(), beanSessioneProposta.getAnnoAccademico());
        
//        if (this.controllaDateSessione(beanSessioneProposta)) {
//            SessioneDao sessioneDao = SessioneDao.getInstance();
//            List<Sessione> sessioniAA = sessioneDao.querySessioniAnnoAccademico(beanSessioneProposta);
//            Iterator<Sessione> i = sessioniAA.iterator();
//            boolean persisti = true;
//            while (i.hasNext() && persisti) {
//                Sessione sessioneEsistente = i.next();
//                if ((beanSessioneProposta.getDataInizioSessione().isBefore(sessioneEsistente.getDataInizio()) ||
//                        beanSessioneProposta.getDataInizioSessione().isEqual(sessioneEsistente.getDataInizio()) )&&
//                        (beanSessioneProposta.getDataFineSessione().isAfter(sessioneEsistente.getDataInizio()) || 
//                        beanSessioneProposta.getDataFineSessione().isEqual(sessioneEsistente.getDataInizio()) ))
//                    persisti = false;// NO  la data di inizio di una sessione esistente e' compresa nell'intervallo che si vuole definire
//                if ((beanSessioneProposta.getDataInizioSessione().isBefore(sessioneEsistente.getDataInizio()) ||
//                        beanSessioneProposta.getDataInizioSessione().isEqual(sessioneEsistente.getDataInizio()) )&& 
//                        (beanSessioneProposta.getDataFineSessione().isAfter(sessioneEsistente.getDataFine()) || 
//                        beanSessioneProposta.getDataFineSessione().isEqual(sessioneEsistente.getDataFine()) ))
//                    persisti = false; // NO l'intervallo che si vuole definire ricopre interamente il periodo di un'altra sessione
//                if ((beanSessioneProposta.getDataInizioSessione().isAfter(sessioneEsistente.getDataInizio()) || 
//                        beanSessioneProposta.getDataInizioSessione().isEqual(sessioneEsistente.getDataInizio()) )&& 
//                        (beanSessioneProposta.getDataFineSessione().isBefore(sessioneEsistente.getDataFine()) || 
//                        beanSessioneProposta.getDataFineSessione().isEqual(sessioneEsistente.getDataFine()))) 
//                    persisti = false; // NO l'intervallo che si vuole definire e' gia' incluso in un'altra sessione
//                if ((beanSessioneProposta.getDataInizioSessione().isBefore(sessioneEsistente.getDataFine()) || 
//                        beanSessioneProposta.getDataInizioSessione().isEqual(sessioneEsistente.getDataFine()) )&& 
//                        (beanSessioneProposta.getDataFineSessione().isAfter(sessioneEsistente.getDataFine()) ||
//                        beanSessioneProposta.getDataFineSessione().isEqual(sessioneEsistente.getDataFine())) )
//                    persisti = false; // NO la data di fine di una sessione e' compresa nell' intervallo proposto
//            }
            
            SessioneDao sessioneDao = SessioneDao.getInstance();
            if (sessioneDaDefinire.sessioneDefinibile()) {
//            if (persisti) {
                sessioneDao.persistiSessione(beanSessioneProposta);
                return true;
            }
        return false;
    }
        
}
