package Livio.Controller;

import Livio.bean.BeanInfoAnnoAccademico;
import gruppo.DAO.AnnoAccademicoDao;
import gruppo.entity.AnnoAccademico;
import Livio.bean.BeanRispostaAnnoAccademico;

import java.time.LocalDate;
import java.time.Year;

public class GestoreAperturaAnnoAccademico {
    private AnnoAccademico annoAccademico;


    public GestoreAperturaAnnoAccademico() {
    }

    public BeanRispostaAnnoAccademico apriAnnoAccademico(BeanInfoAnnoAccademico infoAnnoAccademico) throws Exception {

        String mex = this.controlloDati(infoAnnoAccademico);
        if(mex != null) {
            return new BeanRispostaAnnoAccademico(mex);
        }


        this.annoAccademico =  new AnnoAccademico(infoAnnoAccademico.getAnno(), infoAnnoAccademico.getDataInizio(),
                infoAnnoAccademico.getDataFine());
        AnnoAccademicoDao annoAccademicoDao = AnnoAccademicoDao.getIstance();
        if(annoAccademicoDao.queryEsistenzaAnnoAccademico(this.annoAccademico)) {
            this.persistiAnnoAccademico();
            return new BeanRispostaAnnoAccademico("CREAZIONE ANDATA A BUON FINE");
        }
        else {
            return new BeanRispostaAnnoAccademico("ANNO ACCADEMICO GIA' DEFINITO");
        }


    }

    private String controlloDati(BeanInfoAnnoAccademico infoAnnoAccademico) throws Exception {
        try {
            LocalDate dataInizio = infoAnnoAccademico.getDataInizio();
            Year anno = infoAnnoAccademico.getAnno();
            LocalDate dateFine = infoAnnoAccademico.getDataFine();
            if (dataInizio.getYear() == anno.getValue() && dateFine.getYear() == anno.plusYears(2).getValue() && dataInizio.getMonthValue() == 10
                    && dateFine.getMonthValue() < 5)
                return null;
            else
                return "DATE SCELTE NON VALIDE PER  UN ANNO ACCADEMICO: DEVE ESSERE APERTO AL MESE DI OTTOBRE " +
                        "DELL'ANNO " + anno + " E CHIUSO ENTRO IL MESE DI APRILE DELL'ANNO " + anno.plusYears(2);
        }
        catch (Exception e) {
            e.printStackTrace(); //forse si può gestire meglio
            return "notNull";
        }
    }

    private void persistiAnnoAccademico() {
        AnnoAccademicoDao annoAccademicoDao = AnnoAccademicoDao.getIstance();
        annoAccademicoDao.persisti(this.annoAccademico);

    }
}
