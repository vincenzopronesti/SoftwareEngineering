package Livio.Controller;
import Livio.bean.BeanInfoAnnoAccademico;
import Livio.bean.BeanRispostaAnnoAccademico;
import gruppo.DAO.AnnoAccademicoDao;
import gruppo.DAO.SessioneDao;

import gruppo.entity.AnnoAccademico;

import java.time.LocalDate;
import java.time.Year;

public class GestoreModificaAnnoAccademico {
    private AnnoAccademico annoAccademico;

    public GestoreModificaAnnoAccademico(BeanInfoAnnoAccademico infoAnnoAccademico) {
        this.annoAccademico = new AnnoAccademico(infoAnnoAccademico.getAnno(), infoAnnoAccademico.getDataInizio(),
                                                infoAnnoAccademico.getDataFine());
    }





    public BeanRispostaAnnoAccademico modificaAnnoAccademico(BeanInfoAnnoAccademico infoAnnoAccademico) {
        AnnoAccademicoDao annoAccademicoDao = AnnoAccademicoDao.getIstance();
        if(annoAccademicoDao.queryEsistenzaAnnoAccademico(this.annoAccademico)) {
            return new BeanRispostaAnnoAccademico("ANNO ACCADEMICO NON ESISTENTE");
        }
        String mex = this.controlloModifiche(infoAnnoAccademico);
        if(mex == null) {

            annoAccademicoDao.updateAnnoAccademico(this.annoAccademico);
            return new BeanRispostaAnnoAccademico("ANNO ACCADEMICO MODIFICATO CON SUCCESSO");
        }
        else {
            return new BeanRispostaAnnoAccademico(mex);
        }
    }

    private String controlloModifiche(BeanInfoAnnoAccademico infoAnnoAccademico) {
        try {
            LocalDate dataInizio = infoAnnoAccademico.getDataInizio();
            Year anno = infoAnnoAccademico.getAnno();
            LocalDate dateFine = infoAnnoAccademico.getDataFine();
            if (dataInizio.getYear() == anno.getValue() && dateFine.getYear() == anno.plusYears(2).getValue() && dataInizio.getMonthValue() == 10
                    && dateFine.getMonthValue() < 5)
                return this.controlloDateSessioni();
            else
                return "DATE SCELTE NON VALIDE PER  UN ANNO ACCADEMICO: DEVE INIZIARE AL MESE DI OTTOBRE " +
                        "DELL'ANNO " + anno + " E FINIRE ENTRO IL MESE DI APRILE DELL'ANNO " + anno.plusYears(2);

        } catch (Exception e) {
            e.printStackTrace(); //forse si può gestire meglio
            return "notNull";
        } finally {
            LocalDate dateFine = infoAnnoAccademico.getDataFine();
        }
    }

    private String controlloDateSessioni() {
        SessioneDao sessioneDao = SessioneDao.getInstance();
        if(sessioneDao.queryDateSessione(this.annoAccademico)) {
            return null;
        }
        else {
            String mex = "MODIFICA RIFIUTATA PERCHE' NON CONFORME ALLE DATE DELLE SESSIONI";
            return mex;
        }
    }


}
