package gruppo.DAO;

import DB.DBAcess;
import Vincenzo.bean.BeanAnnoAccademico;
import Vincenzo.bean.BeanUtente;
import gruppo.entity.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrenotazioneDao {
    private static final PrenotazioneDao prenotazioneDao = new PrenotazioneDao();
    private static final Lock lock = new ReentrantLock(); //TODO preso alla lettura lasciato dopo la persistenza

    private static String PASS = DBAcess.DBPASSW;
    private static String USER = DBAcess.DBOWNER;
    private static String DB_URL = DBAcess.DB_URL;

    private PrenotazioneDao(){} //costruttore privato per impedire creazione di altre istanze.. singleton pattern...
    //true  se classe è stata istanziata

    /*
        private PrenotazioneDao(){
            //qui inizializzati campi lock e istanza
            System.out.println("debug...PrenotazioneDaoConstructor invokated");

            lock=new ReentrantLock();
            istanciated = true;
            //singleton constructor
        }
        public static synchronized PrenotazioneDao getIstance() {
            if (istanciated == false || prenotazioneDao==null) {
                new PrenotazioneDao();

                                }
            return prenotazioneDao;
            //end singleton pattern :)

            //classe singleton
        }
        public static synchronized Lock getLock(){
            if (istanciated==false){
                new PrenotazioneDao();

            }
            return lock;
        }
    */
    public static PrenotazioneDao getIstance() {
        return prenotazioneDao;
    }

    public static Lock getLock() {
        return lock;
    }

    public boolean queryEsistenzaPrenotazioneConferenza(Prenotazione prenotazione) {       //jdbc
        // STEP 1: dichiarazioni
        Statement stmt = null;
        Connection conn = null;

        try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName("org.postgresql.Driver");

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            String sql = "SELECT * FROM prenotazione WHERE idaula = " + prenotazione.getIdAula() + " AND data = '" +
                    prenotazione.getData() + "' AND FasciaOraria = '" + prenotazione.getFasciaOraria() + "';";
            ResultSet resultSet = stmt.executeQuery(sql);
            if (resultSet.next()) { // se non è vuoto
                return false;
            }
            // STEP 6: Clean-up dell'ambiente
            resultSet.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // Errore durante l'apertura della connessione

            se.printStackTrace();
        } catch (Exception e) {
            // Errore nel loading del driver

            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return true;
    }
    public void persistiPrenotazioneConferenza(PrenotazioneConferenza prenotazione) {

        // STEP 1: dichiarazioni
        Statement stmt = null;
        Connection conn = null;

        try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName("org.postgresql.Driver");

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "insert into prenotazione(idaula, data, fasciaoraria, username) " +
                    "values(" + prenotazione.getIdAula() + ",'" + prenotazione.getData() + "','" + prenotazione.getFasciaOraria() +
                    "','" + prenotazione.getUsername() + "')";
            String sql2 = "insert into prenotazioneconferenza(idaula, data, fasciaoraria, titoloevento) " +
                    "values(" + prenotazione.getIdAula() + ",'" + prenotazione.getData() + "','" + prenotazione.getFasciaOraria()
                    + "','" + prenotazione.getTitoloConferenza()+"')";
            System.out.println("sql persist queries\n"+sql+"\n"+sql2);
            stmt.executeUpdate(sql);
            stmt.executeUpdate(sql2);
            // STEP 6: Clean-up dell'ambiente

            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // Errore durante l'apertura della connessione

            se.printStackTrace();
        } catch (Exception e) {
            // Errore nel loading del driver

            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
        //TODO pubblic mod...:'(
        //QUERY ESISTENZA CON PARAMETRI ESPLICITI DI PRENOTAZIONE PER MANTERE ABSTRACT
     public boolean queryEsistenzaPrenotazione(int idAula,
              LocalDate data,FasciaOraria fasciaOraria) {
         // STEP 1: dichiarazioni
         Statement stmt = null;
         Connection conn = null;

         try {
             // STEP 2: loading dinamico del driver mysql
             Class.forName("org.postgresql.Driver");

             // STEP 3: apertura connessione
             conn = DriverManager.getConnection(DB_URL, USER, PASS);
             stmt = conn.createStatement();

             String sql = "SELECT * FROM prenotazione WHERE idaula = " + idAula + " AND data = '" +
                     data + "' AND FasciaOraria = '" + fasciaOraria + "';";
             ResultSet resultSet = stmt.executeQuery(sql);
             if (resultSet.next()) { // se non è vuoto
                 return false;
             }
             // STEP 6: Clean-up dell'ambiente
             resultSet.close();
             stmt.close();
             conn.close();
         } catch (SQLException se) {
             // Errore durante l'apertura della connessione

             se.printStackTrace();
         } catch (Exception e) {
             // Errore nel loading del driver

             e.printStackTrace();
         } finally {
             try {
                 if (stmt != null)
                     stmt.close();
             } catch (SQLException se2) {
             }
             try {
                 if (conn != null)
                     conn.close();
             } catch (SQLException se) {
                 se.printStackTrace();
             }
         }

         return true;
     }

    //anndysnake UC
    public List<Prenotazione> queryPrenotazioneDaTipo(String tipoDiEvento) {
        //eseguito in boundary controllo sintattico
        //tipoevento in         //tipoEvento..={CONFERENZA,SEDUTADILAUREA,TESTDIINGRESSO,ESAME}
        String tab=new String();
        if(tipoDiEvento.equals("CONFERENZA"))
            tab="prenotazioneconferenza";
        if(tipoDiEvento.equals("SEDUTADILAUREA"))
            tab="prenotazionisedutedilaurea";
        if(tipoDiEvento.equals("TESTDIINGRESSO"))
            tab="prenotazionitestdingresso";
        if(tipoDiEvento.equals("ESAME"))
            tab="prenotazionedidattica";
        //sopra ho ottenuto in tab tabella in db corrispondente a tipodievento

        List<Prenotazione> prenotazioni= new ArrayList<>();
        // STEP 1: dichiarazioni
        Statement stmt = null;
        Connection conn = null;

        try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName("org.postgresql.Driver");

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            String sql;
            sql = "SELECT * FROM ";
            sql+= tab +" AS q JOIN prenotazione AS p ON (q.idaula=p.idaula and q.data=p.data and q.fasciaoraria=p.fasciaoraria)"+
                    " WHERE p.data >= '"+LocalDate.now()+"'";

            System.out.println("debug sql query string \n"+sql);
            ResultSet resultSet = stmt.executeQuery(sql);
            /*
            scorrendo le righe della tabella del risultato
            produco istanze di tipo inserito di prenotazione da ritornare
             */
            if (tipoDiEvento.equals("CONFERENZA")){
            while (resultSet.next()) { //finche ci sono righe da leggere
                //tmp dAula, LocalDate data, FasciaOraria FasciaOraria, String titoloConferenza
                prenotazioni.add( new PrenotazioneConferenza( resultSet.getInt("idaula"),
                                        resultSet.getDate("data").toLocalDate(),
                                        FasciaOraria.valueOf(resultSet.getString("fasciaoraria")),
                                        resultSet.getString("titoloevento")));
                //aggiunta a lista di prenotazioni di output prenotazione specifica ottenuta da riga di DB
            }}
            else if (tipoDiEvento.equals("SEDUTADILAUREA")){
                while (resultSet.next()) { //finche ci sono righe da leggere
                    //tmp dAula, LocalDate data, FasciaOraria FasciaOraria, String titoloConferenza
                    prenotazioni.add( new PrenotazioneSedutaLaurea( new AnnoAccademico(Year.parse(resultSet.getString("annoaccademico").substring(0,4))),
                            resultSet.getInt("numeroseduta"),
                            resultSet.getInt("idaula"),
                            resultSet.getDate("data").toLocalDate(),
                            resultSet.getString("username"),
                            FasciaOraria.valueOf(resultSet.getString("fasciaoraria"))
                            ));
                    //aggiunta a lista di prenotazioni di output prenotazione specifica ottenuta da riga di DB

                }}
            else if (tipoDiEvento.equals("TESTDIINGRESSO")){
                while (resultSet.next()) { //finche ci sono righe da legger
                    // tmp (String aa, int numeroSeduta, int idAula,LocalDate data, String username,fasciaOraria)
                    prenotazioni.add( new PrenotazioneTestDiIngresso( new AnnoAccademico(Year.parse(resultSet.getString("annoaccademico").substring(0,4))),
                            resultSet.getInt("idaula"),
                            resultSet.getDate("data").toLocalDate(),
                            resultSet.getString("username"),
                            FasciaOraria.valueOf(resultSet.getString("fasciaoraria"))));
                    //aggiunta a lista di prenotazioni di output prenotazione specifica ottenuta da riga di DB

                }}
            else if (tipoDiEvento.equals("ESAME")){
                while (resultSet.next()) { //finche ci sono righe da leggere
                    //tmp idAula data idProf fascia nomeCorso, String tipoSeduta, LocalDate dataInizioAppello
                    prenotazioni.add( new PrenotazioneDidattica( resultSet.getInt("idaula"),
                            resultSet.getDate("data").toLocalDate(),
                            resultSet.getString("username"),
                            FasciaOraria.valueOf(resultSet.getString("fasciaoraria")),
                            resultSet.getString("nomecorso"),
                            resultSet.getString("tiposeduta")
//                            resultSet.getDate("datainizioappello").toLocalDate()
                            //annoaccademico???
                            //aggiunta a lista di prenotazioni di output prenotazione specifica ottenuta da riga di DB

                    ));
                }}

                // sono state aggiunte alla lista di prenotazioni oggetti prenotazioni specifici ottenuti da query
            // STEP 6: Clean-up dell'ambiente
            resultSet.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // Errore durante l'apertura della connessione

            se.printStackTrace();
        } catch (Exception e) {
            // Errore nel loading del driver

            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }


        return prenotazioni;
    }
    //ANDYSNAKE UC
    public List<Prenotazione> queryPrenotazioniAula(int idAula) {
        /*
        ritorna lista di prenotazioni di oggetti Prenotazione***
        associati alle aule con id specificato
        e prenotate in data >= ora

        NB discriminante tabelle(assunto vincolo NOT NULL almeno il logica controllo sintattico boundary/grafica....)
                prenotazionedidattica--> nomecorso
                prenotazioneconferenza-->titoloevento
                prenotazionesedutadilaurea-->numeroseduta
                prenotazionetestdingresso---> :(
         */
        List<Prenotazione> prenotazioni = new ArrayList<>();
        // STEP 1: dichiarazioni
        Statement stmt = null;
        Connection conn = null;
        //DISCRIMINA
        try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName("org.postgresql.Driver");

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            String sql = " SELECT *\nFROM prenotazione as q "
                    + "\nLEFT JOIN prenotazionisedutedilaurea AS pl "+ "ON q.idaula=pl.idaula and q.data=pl.data and q.fasciaoraria=pl.fasciaoraria "
                    + "\nLEFT JOIN prenotazionedidattica AS pd "+ "ON q.idaula=pd.idaula and q.data=pd.data and q.fasciaoraria=pd.fasciaoraria "
                    + "\nLEFT JOIN prenotazioneconferenza AS pc "+ "ON q.idaula=pc.idaula and q.data=pc.data and q.fasciaoraria=pc.fasciaoraria "
                    + "\nLEFT JOIN prenotazionitestdingresso AS pt "+ "ON q.idaula=pt.idaula and q.data=pt.data and q.fasciaoraria=pt.fasciaoraria "
                    +"\nWHERE q.idaula = " + idAula + " AND q.data >= ' "+LocalDate.now() +"'" ;
            System.out.println("debug string sql prenotazioneDaInfoAula...\n"+sql);
            ResultSet resultSet = stmt.executeQuery(sql);
             /*
             eseguo query ricerca prenotazioni idaula su maxy tab da join tutte possibili tabelle...
             identifichero prenotazione (di una riga generica di maxy tab) a quale tabella appartiene controllando
             valore discriminante di ogni tabella != null
             il primo not null ottenuto mi indica a quale tabella la prenotazione appartiene
                    ==> costruiro entity corrispondente guardando valore colonne specifiche sottotabella di appartenza prenotazione
              TODO assumo che prenotazione puo essere di 1! tipo
              vedi valori discriminanti in cima...
              */
             while (resultSet.next()) { //finche ci sono righe
                 int id = resultSet.getInt("idaula");
                 assert (id == idAula);
                 LocalDate data = resultSet.getDate("data").toLocalDate();
                 FasciaOraria fasciaOraria = FasciaOraria.valueOf(resultSet.getString("fasciaoraria"));

                 if (resultSet.getString("titoloevento") != null)
                     //tmpidAula, LocalDate data, FasciaOraria FasciaOraria, String titoloConferenza
                     prenotazioni.add(new PrenotazioneConferenza(id, data, fasciaOraria,
                             resultSet.getString("titoloevento")));


                 else if (resultSet.getString("nomecorso") != null)
                     prenotazioni.add(new PrenotazioneDidattica(
                             id,data,resultSet.getString("username"),fasciaOraria,
                             resultSet.getString("nomecorso"),
                             resultSet.getString("tiposeduta")
                             //resultSet.getDate("datainizioappello").toLocalDate()
                             ));

                 else if (resultSet.getInt("numeroseduta") != 0) //NB 0 default value for sql null value sql java doc..
                     prenotazioni.add(new PrenotazioneSedutaLaurea(
                             new AnnoAccademico(Year.parse(resultSet.getString("annoaccademico").substring(0,4))),
                             resultSet.getInt("numeroseduta"),
                             id,data,resultSet.getString("username"),fasciaOraria));


                 else {
                     AnnoAccademico aa= new AnnoAccademico(Year.parse(String.valueOf(data.getYear())));
                     prenotazioni.add(new PrenotazioneTestDiIngresso(aa,id,data,
                                    resultSet.getString("username"),fasciaOraria));
                 }
                 //tabellaApparteneza="prenotazionetestdingresso"; //unico a non avere discriminante...
                 // ora so prenotazione di riga i-esima a quale tabella appartiene...==> la aggiungo alla lista di prenotazioni in output


             }

            // STEP 6: Clean-up dell'ambiente
            resultSet.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // Errore durante l'apertura della connessione

            se.printStackTrace();
        } catch (Exception e) {
            // Errore nel loading del driver

            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return prenotazioni;
    }

    //aggiunto livio
    public List<Prenotazione> queryPrenotazioneAttive(String user) {
        List<Prenotazione> prenotazioni = new ArrayList<>();
        prenotazioni.addAll(this.queryPrenotazioneConferenzeAttive(user));
        prenotazioni.addAll(this.queryPrenotazioniEsamiAttive(user));
        prenotazioni.addAll(this.queryPrenotazioniSeduteDiLaureaAttive(user));
        prenotazioni.addAll(this.queryPrenotazioniTestDiIngressoAttive(user));
        //TODO agiungere gli altri tipi di prneotazioni
        return prenotazioni;
    }

    private List<PrenotazioneConferenza> queryPrenotazioneConferenzeAttive(String user) {
        Statement stmt = null;
        Connection conn = null;
        List<PrenotazioneConferenza> p = new ArrayList<>();
        try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName("org.postgresql.Driver");

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            LocalDate dataOggi = LocalDate.now();
            String sql = "SELECT p.idAula, username, p.data, p.fasciaoraria, titoloEvento FROM prenotazione p, prenotazioneconferenza pc WHERE p.data >= '" + dataOggi + "' AND " +
                    " username = '" + user + "' AND p.idAula = pc.idAula AND p.data = pc.data AND p.fasciaoraria = pc.fasciaoraria ;";


            PrenotazioneConferenza prenotazioni;
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                prenotazioni = new PrenotazioneConferenza();

                prenotazioni.setUsername(rs.getString("username"));
                prenotazioni.setIdAula(rs.getInt("idAula"));
                prenotazioni.setData(rs.getDate("data").toLocalDate());

                prenotazioni.setFasciaOraria(FasciaOraria.valueOf(rs.getString("fasciaoraria")));

                prenotazioni.setTitoloConferenza(rs.getString("titoloevento"));
                p.add(prenotazioni);

            }
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // Errore durante l'apertura della connessione

            se.printStackTrace();
        } catch (Exception e) {
            // Errore nel loading del driver

            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return p;

    }

    private List<PrenotazioneDidattica> queryPrenotazioniEsamiAttive(String user) {
        Statement stmt = null;
        Connection conn = null;
        List<PrenotazioneDidattica> p = new ArrayList<>();
        try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName("org.postgresql.Driver");

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            LocalDate dataOggi = LocalDate.now();
            String sql = "SELECT p.idAula, username, p.data, p.fasciaoraria, nomecorso, tiposeduta FROM prenotazione p, prenotazionedidattica pc WHERE p.data >= '" + dataOggi + "' AND" +
                    " username = '" + user + "' AND p.idAula = pc.idAula AND p.data = pc.data AND p.fasciaoraria = pc.fasciaoraria;";
            PrenotazioneDidattica prenotazioni;
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                prenotazioni = new PrenotazioneDidattica();
                prenotazioni.setUsername(rs.getString("username"));
                prenotazioni.setIdAula(rs.getInt("idAula"));
                prenotazioni.setData(rs.getDate("data").toLocalDate());
                prenotazioni.setFasciaOraria(FasciaOraria.valueOf(rs.getString("fasciaoraria")));
                prenotazioni.setNomeCorso(rs.getString("nomecorso"));
                prenotazioni.setTipoSeduta(rs.getString("tiposeduta"));
                p.add(prenotazioni);
            }
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // Errore durante l'apertura della connessione

            se.printStackTrace();
        } catch (Exception e) {
            // Errore nel loading del driver

            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return p;

    }
    private List<PrenotazioneSedutaLaurea> queryPrenotazioniSeduteDiLaureaAttive(String user) {
        Statement stmt = null;
        Connection conn = null;
        List<PrenotazioneSedutaLaurea> p = new ArrayList<>();
        try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName("org.postgresql.Driver");

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            LocalDate dataOggi = LocalDate.now();
            String sql = "SELECT p.idAula, username, p.data, p.fasciaoraria, numeroseduta, annoaccademico FROM prenotazione p, prenotazionisedutedilaurea pc WHERE p.data >= '" + dataOggi + "' AND" +
                    " username = '" + user + "' AND p.idAula = pc.idAula AND p.data = pc.data AND p.fasciaoraria = pc.fasciaoraria;";
            PrenotazioneSedutaLaurea prenotazioni;
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                prenotazioni = new PrenotazioneSedutaLaurea();
                prenotazioni.setUsername(rs.getString("username"));
                prenotazioni.setIdAula(rs.getInt("idAula"));
                prenotazioni.setData(rs.getDate("data").toLocalDate());
                prenotazioni.setFasciaOraria(FasciaOraria.valueOf(rs.getString("fasciaoraria")));
                prenotazioni.setNumeroSeduta(rs.getInt("numeroseduta"));
                prenotazioni.setAA(new AnnoAccademico(Year.parse(rs.getString("annoaccademico").substring(0,4))));
                p.add(prenotazioni);
            }
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // Errore durante l'apertura della connessione

            se.printStackTrace();
        } catch (Exception e) {
            // Errore nel loading del driver

            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return p;

    }

    private List<PrenotazioneTestDiIngresso> queryPrenotazioniTestDiIngressoAttive(String user) {
        Statement stmt = null;
        Connection conn = null;
        List<PrenotazioneTestDiIngresso> p = new ArrayList<>();
        try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName("org.postgresql.Driver");

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            LocalDate dataOggi = LocalDate.now();
            String sql = "SELECT p.idAula, username, p.data, p.fasciaoraria, annoaccademico FROM prenotazione p, prenotazionitestdingresso 			pc WHERE p.data >= '" + dataOggi + "' AND" +
                    " username = '" + user + "' AND p.idAula = pc.idAula AND p.data = pc.data AND p.fasciaoraria = pc.fasciaoraria;";
            PrenotazioneTestDiIngresso prenotazioni;
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                prenotazioni = new PrenotazioneTestDiIngresso();
                prenotazioni.setUsername(rs.getString("username"));
                prenotazioni.setIdAula(rs.getInt("idAula"));
                prenotazioni.setData(rs.getDate("data").toLocalDate());
                prenotazioni.setFasciaOraria(FasciaOraria.valueOf(rs.getString("fasciaoraria")));
		        prenotazioni.setAA(new AnnoAccademico(Year.parse(rs.getString("annoaccademico").substring(0,4))));
                p.add(prenotazioni);
            }
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // Errore durante l'apertura della connessione

            se.printStackTrace();
        } catch (Exception e) {
            // Errore nel loading del driver

            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return p;

    }
    //VINCENZO


    /**
     * utilizzato per il caso d'uso della visualizzazione dello storico
     * delle prenotazioni
     * @return lista delle prenotazioni per conferenze effettuate da un 
     * determinato professore
     */
    public List<PrenotazioneConferenza> queryPrenotazioneConferenza(BeanUtente utente, BeanAnnoAccademico beanAA) {
        List<PrenotazioneConferenza> listPreConf = new ArrayList<>();
        String username = utente.getUsername();
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "select * from prenotazione join "
                    + "prenotazioneconferenza on ("
                    + "prenotazione.idaula = prenotazioneconferenza.idaula and "
                    + "prenotazione.data = prenotazioneconferenza.data and "
                    + "prenotazione.fasciaoraria = prenotazioneconferenza.fasciaoraria) "
                    + "where username = '" + username + "'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int idAula = rs.getInt("idaula");
                LocalDate data = rs.getObject("data", LocalDate.class);
                FasciaOraria fasciaOraria = FasciaOraria.valueOf(rs.getString("fasciaoraria"));
                String titoloConferenza = rs.getString("titoloevento");
                if ((data.getYear() == Integer.parseInt(beanAA.getAnno().substring(0, 4))) ||
                        (data.getYear() == Integer.parseInt(beanAA.getAnno().substring(5)))) {
                    PrenotazioneConferenza preConf = new PrenotazioneConferenza(idAula, data, fasciaOraria, titoloConferenza);
                    listPreConf.add(preConf);
                }
            }
        } catch (SQLException e) {
            System.err.println("Errore durante l'apertura della connessione");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Errore nel loading del driver");
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listPreConf;
    }

    /**
     * utilizzato per il caso d'uso della visualizzazione dello storico
     * delle prenotazioni
     * @return lista delle prenotazioni per esami effettuate da un 
     * determinato professore
     */
    public List<PrenotazioneDidattica> queryPrenotazioneEsame(BeanUtente beanUtente, BeanAnnoAccademico beanAA) {
        List<PrenotazioneDidattica> listPreEs = new ArrayList<>();
        String username = beanUtente.getUsername();
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "select * from prenotazione join "
                    + "prenotazionedidattica on ("
                    + "prenotazione.idaula = prenotazionedidattica.idaula and "
                    + "prenotazione.data = prenotazionedidattica.data and "
                    + "prenotazione.fasciaoraria = prenotazionedidattica.fasciaoraria) "
                    + "where annoaccademico = '" + beanAA.getAnno()
                    + "' and username = '" + username + "'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int idAula = rs.getInt("idaula");
                LocalDate data = rs.getObject("data", LocalDate.class);
                FasciaOraria fasciaOraria = FasciaOraria.valueOf(rs.getString("fasciaoraria"));
                String tipoSeduta = rs.getString("tiposeduta");
                LocalDate dataInizioAppello = rs.getObject("datainizioappello", LocalDate.class);
                String nomeCorso = rs.getString("nomecorso");
                PrenotazioneDidattica preEs = new PrenotazioneDidattica(idAula, data, username, fasciaOraria, nomeCorso,tipoSeduta, dataInizioAppello);
                listPreEs.add(preEs);
            }
        } catch (SQLException e) {
            System.err.println("Errore durante l'apertura della connessione");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Errore nel loading del driver");
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listPreEs;
    }

    /**
     * utilizzato per il caso d'uso della visualizzazione dello storico
     * delle prenotazioni
     * @return lista delle prenotazioni per sedute di laurea effettuate da un 
     * determinato segretario
     */
    public List<PrenotazioneSedutaLaurea> queryPrenotazioneSedutaLaurea(BeanUtente utente, BeanAnnoAccademico beanAA) {
        List<PrenotazioneSedutaLaurea> listPreSed = new ArrayList<>();
        String username = utente.getUsername();
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "select * from prenotazione join "
                    + "prenotazionisedutedilaurea on ("
                    + "prenotazione.idaula = prenotazionisedutedilaurea.idaula and "
                    + "prenotazione.data = prenotazionisedutedilaurea.data and "
                    + "prenotazione.fasciaoraria = prenotazionisedutedilaurea.fasciaoraria) "
                    + "where annoaccademico = '" + beanAA.getAnno()
                    + "' and username = '" + username + "'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                AnnoAccademico annoA = new AnnoAccademico(Year.parse(rs.getString("annoaccademico").substring(0, 4)));
                int numeroSeduta = rs.getInt("numeroseduta");
                int idAula = rs.getInt("idaula");
                LocalDate data = rs.getObject("data", LocalDate.class);
                FasciaOraria fasciaOraria = FasciaOraria.valueOf(rs.getString("fasciaoraria"));
                PrenotazioneSedutaLaurea preSed = new PrenotazioneSedutaLaurea(annoA,
                        numeroSeduta, idAula, data, username, fasciaOraria);
                System.out.println("adding: " + preSed);
                listPreSed.add(preSed);
            }
        } catch (SQLException e) {
            System.err.println("Errore durante l'apertura della connessione");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Errore nel loading del driver");
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listPreSed;
    }

    /**
     * utilizzato per il caso d'uso della visualizzazione dello storico
     * delle prenotazioni
     * @return lista delle prenotazioni per test di ingresso effettuate da un 
     * determinato segretario
     */
    public List<PrenotazioneTestDiIngresso> queryPrenotazioneTestIngresso(BeanUtente utente, BeanAnnoAccademico beanAA) {
        List<PrenotazioneTestDiIngresso> listPreTest = new ArrayList<>();
        String username = utente.getUsername();
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "select * from prenotazione join "
                    + "prenotazionitestdingresso on ("
                    + "prenotazione.idaula = prenotazionitestdingresso.idaula and "
                    + "prenotazione.data = prenotazionitestdingresso.data and "
                    + "prenotazione.fasciaoraria = prenotazionitestdingresso.fasciaoraria) "
                    + "where annoaccademico = '" + beanAA.getAnno()
                    + "' and username = '" + username + "'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                AnnoAccademico annoA = new AnnoAccademico(Year.parse(rs.getString("annoaccademico").substring(0, 4)));
                int idAula = rs.getInt("idaula");
                LocalDate data = rs.getObject("data", LocalDate.class);
                FasciaOraria fasciaOraria = FasciaOraria.valueOf(rs.getString("fasciaoraria"));
                PrenotazioneTestDiIngresso preTest = new PrenotazioneTestDiIngresso(annoA,
                        idAula, data, username, fasciaOraria);
                listPreTest.add(preTest);
            }
        } catch (SQLException e) {
            System.err.println("Errore durante l'apertura della connessione");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Errore nel loading del driver");
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listPreTest;
    }
    
    }


