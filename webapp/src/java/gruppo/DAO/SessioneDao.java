package gruppo.DAO;

import DB.DBAcess;
import Vincenzo.bean.BeanSessione;
import gruppo.entity.Sessione;
import gruppo.entity.TipoSessione;
import gruppo.entity.AnnoAccademico;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class SessioneDao {
    private static SessioneDao instance = null;

    private static String PASS = DBAcess.DBPASSW;
    private static String USER = DBAcess.DBOWNER;
    private static String DB_URL = DBAcess.DB_URL;
    public static synchronized SessioneDao getInstance() {
        if (instance == null)
            return new SessioneDao();
        else
            return instance;
    }
    
    public void persistiSessione(BeanSessione beanSessione) {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "insert into sessione(datainizio, datafine, " + 
                    "tipologia, anno) values('" + beanSessione.getDataInizioSessione() + "', '" + 
                    beanSessione.getDataFineSessione() + "', '" + beanSessione.getTipologia() + "', '" + 
                    beanSessione.getAnnoAccademico() + "')";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // Errore durante l'apertura della connessione
            se.printStackTrace();

            se.printStackTrace();
        } catch (Exception e) {
            // Errore nel loading del driver
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    //Aggiunta Livio
    public boolean queryDateSessione(AnnoAccademico annoAccademico) {
        Statement stmt = null;
        Connection conn = null;
        boolean isValid = false;
        try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName("org.postgresql.Driver");

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM sessione WHERE (datainizio < '" + annoAccademico.getDataInizio() + "' or " +
                    "datafine > '" +annoAccademico.getDataFine() +"') AND anno = '" + annoAccademico.toStringAnno() + "';";
            ResultSet resultSet = stmt.executeQuery(sql);
            if(resultSet.next()) {
                isValid = false;
            }
            else {
                isValid = true;
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
        return isValid;

    }
    
    /**
     * 
     * @return insieme di sessioni di un determinato anno accademico
     */
    public List<Sessione> querySessioniAnnoAccademico(BeanSessione beanSessioneProposta) {
        Connection conn = null;
        Statement stmt = null;
        List<Sessione> listSessione = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "select sessione.datainizio, sessione.datafine, " + 
                    "sessione.tipologia, sessione.anno, " + 
                    "annoaccademico.datainizio, annoaccademico.datafine " + 
                    "from sessione join annoaccademico on (" + 
                    "sessione.anno = annoaccademico.anno) where sessione.anno = '" + 
                    beanSessioneProposta.getAnnoAccademico() + "'";
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            String colDataInizioSessione = rsmd.getColumnName(1);
            String colDataFineSessione = rsmd.getColumnName(2);
            String colTipoSessione = rsmd.getColumnName(3);
            String colAnno = rsmd.getColumnName(4);
            String colDataInizioAnno = rsmd.getColumnName(5);
            String colDataFineAnno = rsmd.getColumnName(6);
            while (rs.next()) {
                LocalDate dataInizioSessione = rs.getObject(colDataInizioSessione, LocalDate.class);
                LocalDate dataFineSessione = rs.getObject(colDataFineSessione, LocalDate.class);
                TipoSessione tipologia = TipoSessione.valueOf(rs.getString(colTipoSessione));
                LocalDate dataInizioAnno = rs.getObject(colDataInizioAnno, LocalDate.class);
                LocalDate dataFineAnno = rs.getObject(colDataFineAnno, LocalDate.class);
                Year anno1 = Year.parse(rs.getString(colAnno).substring(0, 4));
                //Year anno2 = anno1.plusYears(1);
                AnnoAccademico annoAccademico = new AnnoAccademico(anno1, dataInizioAnno, dataFineAnno);
                Sessione sessioneEsistente = new Sessione(dataInizioSessione, 
                        dataFineSessione, tipologia, annoAccademico.toStringAnno());

                listSessione.add(sessioneEsistente);             
            }
    /*
                        String sql = "select * from sessione join annoaccademico on (" + 
                    "sessione.anno = annoaccademico.anno) where sessione.anno = '" + 
                    beanSessioneProposta.getAnnoAccademico() + "'";
     */
    /*
    String sql = "select sessione.datainizio as datainiziosessione, " + 
            "sessione.datafine as datafinesessione, tipologia, " + 
            "sessione.anno as annosessione, " + 
            "annoaccademico.datainizio as datainizioannoaccademico, " + 
            "annoaccademico.datafine as datafineaannoaccademico, " + 
            "annoaccademico.anno as anno " + 
            "from sessione join annoaccademico on (" + 
            "annosessione = anno) where annosessione= '" + 
            beanSessioneProposta.getAnnoAccademico() + "'";
    */
    /*
            while (rs.next()) {
                LocalDate dataInizioSessione = rs.getObject("sessione.datainizio", LocalDate.class);
                LocalDate dataFineSessione = rs.getObject("sessione.datafine", LocalDate.class);
                TipoSessione tipologia = TipoSessione.valueOf(rs.getString("tipologia"));
                LocalDate dataInizioAnno = rs.getObject("annoaccademico.dataInizio", LocalDate.class);
                LocalDate dataFineAnno = rs.getObject("annoaccademico.datafine", LocalDate.class);
                Year anno1 = Year.parse(rs.getString("sessione.anno").substring(0, 4));
                //Year anno2 = anno1.plusYears(1);
                AnnoAccademico annoAccademico = new AnnoAccademico(anno1, dataInizioAnno, dataFineAnno);
                Sessione sessioneEsistente = new Sessione(dataInizioSessione, 
                        dataFineSessione, tipologia, annoAccademico.toStringAnno());

                listSessione.add(sessioneEsistente);             
            }
     */     
        } catch (SQLException se) {
            // Errore durante l'apertura della connessione
            se.printStackTrace();

            se.printStackTrace();
        } catch (Exception e) {
            // Errore nel loading del driver
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return listSessione;
    }
}
