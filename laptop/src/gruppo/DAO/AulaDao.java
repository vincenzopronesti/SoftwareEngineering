package gruppo.DAO;


import DB.DBAcess;
import gruppo.bean.BeanCaratteristicheAula;
import gruppo.entity.Aula;
import gruppo.entity.AulaConvegni;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AulaDao {
    private static AulaDao aulaDao;
    private static AnnoAccademicoDao istance;
    private static String PASS = DBAcess.DBPASSW;
    private static String USER = DBAcess.DBOWNER;
    private static String DB_URL = DBAcess.DB_URL;
    //attributi per jdbc
    public static synchronized AulaDao getIstance() {
        if (aulaDao == null)
            return new AulaDao();
        else
            return aulaDao;
        //end singleton pattern :)

        //classe singleto
    }
/*
    public List<AulaConvegni> queryConCaratteristiche(BeanCaratteristicheAula beanCarAula) {
        // STEP 1: dichiarazioni
        Statement stmt = null;
        Connection conn = null;
        List<AulaConvegni> aule = new ArrayList<>();

        try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName("org.postgresql.Driver");

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql = "SELECT * FROM aula WHERE numero_posti >= " + beanCarAula.getNumPosti();
            if (beanCarAula.isLavagna())
                sql = sql + " AND lavagna =" + beanCarAula.isLavagna();
            if (beanCarAula.isMicrofono())
                sql = sql + " AND microfono =" + beanCarAula.isMicrofono();
            if (beanCarAula.isProiettore())
                sql = sql + " AND proiettore =" + beanCarAula.isProiettore();
            if (beanCarAula.isEthernet())
                sql = sql + " AND ethernet =" + beanCarAula.isEthernet();
            if (beanCarAula.isLavagnaInterattiva())
                sql = sql + " AND lavagnainterattiva =" + beanCarAula.isLavagnaInterattiva();
            if (beanCarAula.isPreseCorrente())
                sql = sql + " AND presecorrente =" + beanCarAula.isPreseCorrente();
            //se sono vere le inserisco nell'interogazzione
            //sql = sql +";";
            System.out.println(sql);
            stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(sql);
            while (result.next()) {
                AulaConvegni a = new AulaConvegni();
                a.setId(result.getInt("id"));
                a.setNumPosti(result.getInt("numero_posti"));
                a.setEthernet(result.getBoolean("ethernet"));
                a.setLavagna(result.getBoolean("lavagna"));
                a.setLavagnaInterattiva(result.getBoolean("lavagnaInterattiva"));
                a.setMicrofono(result.getBoolean("microfono"));
                a.setPreseCorrente(result.getBoolean("preseCorrente"));
                a.setProiettore(result.getBoolean("proiettore"));
                aule.add(a);
            }
            System.out.println(aule.size());
            // STEP 6: Clean-up dell'ambiente
            result.close();
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
        return aule;

    }

    public AulaConvegni queryConId(int id) {
        // STEP 1: dichiarazioni
        Statement stmt = null;
        Connection conn = null;
        AulaConvegni a = new AulaConvegni();

        try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName("org.postgresql.Driver");

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "SELECT * FROM aula WHERE id = " + id + ";";
            stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(sql);


            if (result.next()) {

                a.setId(result.getInt("id"));
                a.setNumPosti(result.getInt("numero_posti"));
                a.setEthernet(result.getBoolean("ethernet"));
                a.setLavagna(result.getBoolean("lavagna"));
                a.setLavagnaInterattiva(result.getBoolean("lavagnaInterattiva"));
                a.setMicrofono(result.getBoolean("microfono"));
                a.setPreseCorrente(result.getBoolean("preseCorrente"));
                a.setProiettore(result.getBoolean("proiettore"));

            } else {
                a = null;
            }
            if (result.next()) {
                //errore
            }
            result.close();
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
        return a;

    }
 */
    //todo andysnake add... ritorna lista di aule, se specificato il tipo in bean solo aule di un tipo...:)
    public List<Aula> queryConCaratteristiche(BeanCaratteristicheAula beanCarAula) {
        // STEP 1: dichiarazioni
        Statement stmt = null;
        Connection conn = null;
        List<Aula> aule = new ArrayList<>();

        try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName("org.postgresql.Driver");

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql = "SELECT * FROM aula WHERE numero_posti >= " + beanCarAula.getNumPosti();
            if (beanCarAula.isLavagna())
                sql = sql + " AND lavagna =" + beanCarAula.isLavagna();
            if (beanCarAula.isMicrofono())
                sql = sql + " AND microfono =" + beanCarAula.isMicrofono();
            if (beanCarAula.isProiettore())
                sql = sql + " AND proiettore =" + beanCarAula.isProiettore();
            if (beanCarAula.isEthernet())
                sql = sql + " AND ethernet =" + beanCarAula.isEthernet();
            if (beanCarAula.isLavagnaInterattiva())
                sql = sql + " AND lavagnainterattiva =" + beanCarAula.isLavagnaInterattiva();
            if (beanCarAula.isPreseCorrente())
                sql = sql + " AND presecorrente =" + beanCarAula.isPreseCorrente();
            if(beanCarAula.getType()!=null){
                sql=sql+"AND tipo="+beanCarAula.getType();
            }
            //se sono vere le inserisco nell'interogazzione
            //sql = sql +";";
            System.out.println(sql);
            stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(sql);
            while (result.next()) {
                AulaConvegni a = new AulaConvegni();
                a.setId(result.getInt("id"));
                a.setNumPosti(result.getInt("numero_posti"));
                a.setEthernet(result.getBoolean("ethernet"));
                a.setLavagna(result.getBoolean("lavagna"));
                a.setLavagnaInterattiva(result.getBoolean("lavagnaInterattiva"));
                a.setMicrofono(result.getBoolean("microfono"));
                a.setPreseCorrente(result.getBoolean("preseCorrente"));
                a.setProiettore(result.getBoolean("proiettore"));
                aule.add(a);
            }
            System.out.println(aule.size());
            // STEP 6: Clean-up dell'ambiente
            result.close();
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
        return aule;

    }

    public AulaConvegni queryConId(int id) {
        // STEP 1: dichiarazioni
        Statement stmt = null;
        Connection conn = null;
        AulaConvegni a = new AulaConvegni();

        try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName("org.postgresql.Driver");

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "SELECT * FROM aula WHERE id = " + id + ";";
            stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(sql);


            if (result.next()) {

                a.setId(result.getInt("id"));
                a.setNumPosti(result.getInt("numero_posti"));
                a.setEthernet(result.getBoolean("ethernet"));
                a.setLavagna(result.getBoolean("lavagna"));
                a.setLavagnaInterattiva(result.getBoolean("lavagnaInterattiva"));
                a.setMicrofono(result.getBoolean("microfono"));
                a.setPreseCorrente(result.getBoolean("preseCorrente"));
                a.setProiettore(result.getBoolean("proiettore"));

            } else {
                a = null;
            }
            if (result.next()) {
                //errore
            }
            result.close();
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
        return a;

    }
}