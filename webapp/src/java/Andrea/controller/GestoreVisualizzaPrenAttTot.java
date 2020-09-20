package Andrea.controller;

import gruppo.DAO.AulaDao;
import gruppo.DAO.PrenotazioneDao;
import gruppo.bean.BeanCaratteristicheAula;
import gruppo.entity.Aula;
import gruppo.entity.Prenotazione;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GestoreVisualizzaPrenAttTot    {

    private List<Aula> aule=new ArrayList<>();
    private List<Prenotazione> prenotazioniComplessiveTipoEvento=new ArrayList<>();
    private List<Prenotazione> prenotazioniComplessiveTipoAula=new ArrayList<>();


    public void setAule(List<Aula> aule) {
        this.aule = aule;
    }

    public List<Prenotazione> getPrenotazioniComplessiveTipoEvento() {
        return prenotazioniComplessiveTipoEvento;
    }

    public List<Prenotazione> getPrenotazioniComplessiveTipoAula() {
        return prenotazioniComplessiveTipoAula;
    }

    public void ricercaSuTipoEvento(String tipoDiEvento) {

        PrenotazioneDao prenotazioneDao = PrenotazioneDao.getIstance();
        try {
            PrenotazioneDao.getLock().lock();
        }
        catch (Exception e){
            System.err.println("erroe in lock in ricercasutipo evento UC 11");
            PrenotazioneDao.getLock().unlock();

            //temporaneamente chiudo
            System.exit(1);
        }
        this.prenotazioniComplessiveTipoEvento = prenotazioneDao.queryPrenotazioneDaTipo(tipoDiEvento);
        PrenotazioneDao.getLock().unlock();
     }

    public void ricercaSuTipodiAula(BeanCaratteristicheAula beanCaratteristicheAula) throws Exception {
        this.aule = AulaDao.getIstance().queryConCaratteristiche(beanCaratteristicheAula);
        PrenotazioneDao prenotazioneDao= PrenotazioneDao.getIstance();
        try{
            PrenotazioneDao.getLock().lock();
        }
        catch (Exception e){
            PrenotazioneDao.getLock().unlock();
            e.printStackTrace();
            System.err.println("errore in acquisizione Lock...");
            throw new Exception();
        }
        Iterator<Aula> a = this.aule.iterator();
        while(a.hasNext()) {
            Aula aula= a.next();
            System.out.println("aulaid"+aula.getId());
            this.prenotazioniComplessiveTipoAula.addAll(prenotazioneDao.queryPrenotazioniAula(aula.getId()));
        }
        PrenotazioneDao.getLock().unlock();
        //rilascio il lock
    }
    public List<Prenotazione> intersezione (){
        Iterator<Prenotazione> i = this.prenotazioniComplessiveTipoAula.iterator();
        List<Prenotazione> out=new ArrayList<>();
        while (i.hasNext()){
            Prenotazione pTipoAula =i.next();
            for (int k=0;k< this.prenotazioniComplessiveTipoEvento.size();k++){
                if (pTipoAula.equals(this.prenotazioniComplessiveTipoEvento.get(k))){
                    out.add(pTipoAula);
                    break;
                    //aggiungo a risultato finale prenotazioni
                    // che rispettano le caratteristiche dell aula
                    //e che sono del tipo scelto intersezionando i 2 insiemi
                }
            }
        }
        return out;
        //intersezione eseguita
    }

}
