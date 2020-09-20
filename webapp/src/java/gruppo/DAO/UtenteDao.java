package gruppo.DAO;

import DB.DBAcess;
import gruppo.DAO.AnnoAccademicoDao;
import gruppo.entity.*;

import java.sql.*;

public class UtenteDao {

    private static String PASS = DBAcess.DBPASSW;
    private static String USER = DBAcess.DBOWNER;
    private static String DB_URL = DBAcess.DB_URL;
    public static Utente findUtente(String username, String password) {
        // STEP 1: dichiarazioni
        Statement stmt = null;
        Connection conn = null;
        Utente utente = null;
        try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName("org.postgresql.Driver");

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4: creazione ed esecuzione della query
            stmt = conn.createStatement();
            String sql = "SELECT  name, username, password, type FROM utente where username = '"
                    + username + "' AND password = '" + password + "' ;";
            ResultSet rs = stmt.executeQuery(sql);

            if (!rs.next()) { // rs is empty

                return null;
            }
            /*boolean moreThanOne = rs.first() && rs.next();
            assert !moreThanOne; // per abilitare le asserzioni, avviare la JVM con il parametro -ea
            // (Run Configurations -> <configurazione utilizzata per l'avvio del server> -> Arguments -> VM Arguments).
            // N.B. Le asserzioni andrebbero usate solo per testVerDisponibilita e debug, non per codice in produzione

            // riposizionamento del cursore*/
            //rs.first();

            // lettura delle colonne "by name"
            String nome = rs.getString("name");  // non se perhcè mastrofini riprendeva username, password se già lo sappiamo
            String uname = rs.getString("username");
            String pword = rs.getString("password");
            String type1 = rs.getString("type");

            //assert (usernameLoaded.equals(username));
            if (rs.next()) {
                System.out.println("Exception"); //si deve lanciare un eccezzione perchè non possono esserci due con lo stesso username e password
            }

            //uso il polimorfismo perchè utenteDao potrebbe ritornare un professore o un segretario in base a type
            if (type1.equals("Professore")) {

                utente = new Professore(nome, uname, pword);
            } else {
                utente = new Segretario(nome, uname, pword);
            }


            // STEP 6: Clean-up dell'ambiente
            rs.close();
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

        return utente;
    }
}
