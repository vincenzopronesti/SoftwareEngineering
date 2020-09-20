package Vincenzo.controller;

import Vincenzo.bean.BeanAnnoAccademico;
import Vincenzo.bean.BeanPrenotazioniProfessore;
import Vincenzo.bean.BeanPrenotazioniSegretario;
import Vincenzo.bean.BeanUtente;
import Vincenzo.boundary.InterfacciaProfessoreVisualizzazioneStorico;
import Vincenzo.boundary.InterfacciaSegretarioVisualizzazioneStorico;
import gruppo.DAO.AnnoAccademicoDao;
import gruppo.DAO.PrenotazioneDao;
import gruppo.entity.AnnoAccademico;
import gruppo.entity.PrenotazioneConferenza;
import gruppo.entity.PrenotazioneDidattica;
import gruppo.entity.PrenotazioneSedutaLaurea;
import gruppo.entity.PrenotazioneTestDiIngresso;
import java.time.Year;

import java.util.List;

public class GestoreVisualizzazioneStorico {
    private InterfacciaProfessoreVisualizzazioneStorico professore;
    private InterfacciaSegretarioVisualizzazioneStorico segretario;
    
    public GestoreVisualizzazioneStorico(InterfacciaProfessoreVisualizzazioneStorico professore) {
        this.professore = professore;
        this.segretario = null;
    }
    
    public GestoreVisualizzazioneStorico(InterfacciaSegretarioVisualizzazioneStorico segretario) {
        this.segretario = segretario;  
        this.professore = null;
    }
    
    public BeanPrenotazioniProfessore storicoPrenotazioniProfessore(BeanAnnoAccademico beanAA) {
        AnnoAccademicoDao annoAccademicoDao = AnnoAccademicoDao.getIstance();
        List<PrenotazioneDidattica> preDid = null;
        List<PrenotazioneConferenza> preConf = null;
        String anno = beanAA.getAnno().substring(0, 4);
        AnnoAccademico annoAccademico = new AnnoAccademico(Year.parse(anno));
        boolean annoAccademicoValido;
        if (!annoAccademicoDao.queryEsistenzaAnnoAccademico(annoAccademico)) {
            PrenotazioneDao prenotazioneDao = PrenotazioneDao.getIstance();
            BeanUtente beanUtente = new BeanUtente(this.professore.getUsernameProf(), "Professore");
            preDid = prenotazioneDao.queryPrenotazioneEsame(beanUtente, beanAA);
            preConf = prenotazioneDao.queryPrenotazioneConferenza(beanUtente, beanAA);           
            annoAccademicoValido = true;
        } else {
            annoAccademicoValido = false;
        }
        return new BeanPrenotazioniProfessore(preDid, preConf, annoAccademicoValido);
    }
    
    public BeanPrenotazioniSegretario storicoPrenotazioniSegretario(BeanAnnoAccademico beanAA) {
        AnnoAccademicoDao annoAccademicoDao = AnnoAccademicoDao.getIstance();
        List<PrenotazioneSedutaLaurea> preSed = null;
        List<PrenotazioneTestDiIngresso> preTest = null;
        String anno = beanAA.getAnno().substring(0, 4);
        AnnoAccademico annoAccademico = new AnnoAccademico(Year.parse(anno));
        boolean annoAccademicoValido;
        if (!annoAccademicoDao.queryEsistenzaAnnoAccademico(annoAccademico)) {
            PrenotazioneDao prenotazioneDao = PrenotazioneDao.getIstance();
            BeanUtente beanUtente = new BeanUtente(this.segretario.getUsernameSegretario(), "Segretario");
            preSed = prenotazioneDao.queryPrenotazioneSedutaLaurea(beanUtente, beanAA);
            preTest = prenotazioneDao.queryPrenotazioneTestIngresso(beanUtente, beanAA);           
            annoAccademicoValido = true;
        } else {
            annoAccademicoValido = false;
        }
        return new BeanPrenotazioniSegretario(preSed, preTest, annoAccademicoValido);
    }

}
