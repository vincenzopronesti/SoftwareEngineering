package gruppo.DAO;
import DB.DBAcess;
import gruppo.entity.AnnoAccademico;

import java.sql.*;
import java.time.LocalDate;

public class AnnoAccademicoDao {
    private static AnnoAccademicoDao istance;
    private static String PASS = DBAcess.DBPASSW;
    private static String USER = DBAcess.DBOWNER;
    private static String DB_URL = DBAcess.DB_URL;

    public static synchronized AnnoAccademicoDao getIstance() {
        if(istance == null)
            istance = new AnnoAccademicoDao();
        return istance;
    }
    
        public LocalDate queryDataInizioAnnoAccademico(String annoAccademico) {
        LocalDate dataInizio = null;
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "select datainizio from annoaccademico where anno = '" + 
                    annoAccademico + "'";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                dataInizio = rs.getObject("datainizio", LocalDate.class);
            }
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
        return dataInizio;
    }
    
        public LocalDate queryDataFineAnnoAccademico(String annoAccademico) {
        LocalDate dataFine = null;
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "select datafine from annoaccademico where anno = '" + 
                    annoAccademico + "'";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                dataFine = rs.getObject("dataFine", LocalDate.class);
            }
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
        return dataFine;
    }




    public boolean queryEsistenzaAnnoAccademico(AnnoAccademico annoAccademico) { // torna vero se non esiste
        Statement stmt = null;
        Connection conn = null;
        boolean risp = true;

        try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName("org.postgresql.Driver");

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM annoaccademico WHERE anno = '" + annoAccademico.toStringAnno() +"';";
            ResultSet resultSet = stmt.executeQuery(sql);
            if(resultSet.next()) {
                risp =  false;
            }
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
        return risp;

    }

    public void persisti(AnnoAccademico annoAccademico) {
        Statement stmt = null;
        Connection conn = null;


        try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName("org.postgresql.Driver");

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "insert into annoaccademico(anno, datainizio, datafine) " +
                    "values('"+annoAccademico.toStringAnno() +"', '"+ annoAccademico.getDataInizio() + "', '" +
                    annoAccademico.getDataFine()+"')";
            stmt.executeUpdate(sql);

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


    //aggiunto da livio
    public void updateAnnoAccademico(AnnoAccademico annoAccademico) {
        Statement stmt = null;
        Connection conn = null;


        try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName("org.postgresql.Driver");

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            System.out.println(annoAccademico);
            String sql = "update annoaccademico set  datainizio = '" +  annoAccademico.getDataInizio() + "', " +
                    "datafine = '" + annoAccademico.getDataFine() + "' WHERE anno = '" + annoAccademico.toStringAnno()+"';";
            stmt.executeUpdate(sql);
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




}

