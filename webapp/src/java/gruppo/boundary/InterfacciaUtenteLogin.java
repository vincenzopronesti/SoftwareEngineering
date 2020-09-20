package gruppo.boundary;

import Andrea.boundary.InterfVerificaDisponibilita;
import Andrea.boundary.InterfacciaVisualizzaPrenAttTot;
import Livio.boundary.InterfacciaSegreteriaAperturaAnnoAccademico;
import Livio.boundary.InterfacciaSegreteriaModificaAnnoAccademico;
import Livio.boundary.InterfacciaUtenteVisualizzaPrenotazioniAttive;
import Vincenzo.boundary.InterfacciaProfessoreVisualizzazioneStorico;
import Vincenzo.boundary.InterfacciaSegretarioDefinizioneSessione;
import Vincenzo.boundary.InterfacciaSegretarioVisualizzazioneStorico;
import gruppo.controller.GestoreLogin;

public class InterfacciaUtenteLogin {
    String username;
    String paswoord;
    String type;
    boolean isLog;
    
    public InterfacciaUtenteLogin() {}

    public InterfacciaUtenteLogin(String username, String paswoord) {
        this.username = username;
        this.paswoord = paswoord;
    }


    public boolean loggin() {
        if ((this.type = GestoreLogin.getIstance().login(this.username, this.paswoord)) != null) {
            this.isLog = true;

            return true;
        } else
            return false;
    }

    public void loggout() {
        this.isLog = false;
        this.username = null;
        this.paswoord = null;
        this.type=null;
    }
    //TODO ANDYSNAKE UC [ID10]
    public InterfVerificaDisponibilita getInterfacciaVerificaDisponibilita() throws Exception {
        //TODO CI CONFORMIAMO TUTTI A DARE ALLA MASTER BOUNDARY 1! dipendenza alle boundary personali..?
        if (this.isLog == false  ) {
            System.err.println("errore di credenziali...");
            throw new Exception();
        }
        return new InterfVerificaDisponibilita();
    }
    //TODO ANDYSNAKE UC [ID11]
    public InterfacciaVisualizzaPrenAttTot getInterfacciaVisPrenAttTot() throws Exception {
        if (this.isLog == false || !type.equals("Segretario")) {
            System.err.println("errore di credenziali...");
            throw new Exception();
        }
        return new InterfacciaVisualizzaPrenAttTot();
    }
    public InterfacciaPrenotazioneConferenzeProfessore getInterfacciaProf() throws Exception {
        //todo controllo che non dovrebbe essere necessario
        if (this.isLog == false || !type.equals("Professore")) {
            System.err.println("error");
            throw new Exception();
        }
        return new InterfacciaPrenotazioneConferenzeProfessore(this.username);
    }
    public InterfacciaUtenteVisualizzaPrenotazioniAttive getInterfacciaUtenteViusualizzaPrenotazioniAttive() throws Exception {
        if (this.isLog == false  ) {
            System.err.println("error");
            throw new Exception();
        }
        return new InterfacciaUtenteVisualizzaPrenotazioniAttive(this.username);
    }
    public InterfacciaSegreteriaAperturaAnnoAccademico getInterfacciaSegreteriaAperturaAnnoAccademico() throws Exception {
        if (this.isLog == false || !type.equals("Segretario") ) {
            System.err.println("error");
            throw new Exception();
        }
        return new InterfacciaSegreteriaAperturaAnnoAccademico();
    }
    public InterfacciaSegreteriaModificaAnnoAccademico getInterfacciaSegreteriaModificaAnnoAccademico() throws Exception {
        if (this.isLog == false || !type.equals("Segretario") ) {
            System.err.println("error");
            throw new Exception();
        }
        return new InterfacciaSegreteriaModificaAnnoAccademico();
    }

    public InterfacciaProfessoreVisualizzazioneStorico getInterfacciaProfessoreVisualizzazioneStorico() throws Exception {
        if (this.isLog == false || !type.equals("Professore") ) {
            System.err.println("error");
            throw new Exception();
        }
        return new InterfacciaProfessoreVisualizzazioneStorico(this.username);
    }
    
    public InterfacciaSegretarioVisualizzazioneStorico getInterfacciaSegretarioVisualizzazioneStorico() throws Exception {
        if (this.isLog == false || !type.equals("Segretario") ) {
            System.err.println("error");
            throw new Exception();
        }
        return new InterfacciaSegretarioVisualizzazioneStorico(this.username);
    }
    
    public InterfacciaSegretarioDefinizioneSessione getInterfacciaSegretarioDefinizioneSessione() throws Exception {
        if (this.isLog == false || !type.equals("Segretario") ) {
            System.err.println("error");
            throw new Exception();
        }
        return new InterfacciaSegretarioDefinizioneSessione(this.username);
    }

    public void setUsername(String username) {
    	this.username = username;
    }
    
    public void setPassword(String password) {
    	this.paswoord = password;
    }
    
    public String getUsername() {
        return username;
    }

    public String getType() {
        return type;
    }

//ognuno aggiungera la get per la boundary del propio uc nel proprio package


}