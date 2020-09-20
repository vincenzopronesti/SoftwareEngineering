package gruppo.controller;

import Andrea.javaFX.UtenteDao;
import gruppo.entity.Utente;

public class GestoreLogin {

    private static GestoreLogin istance;

    public static synchronized GestoreLogin getIstance() {
        if (istance == null) {
            istance = new GestoreLogin();
        }
        return istance;
    }

    public String login(String username, String password) {

        Utente utente = UtenteDao.findUtente(username, password);
        if (utente == null) {
            return null;
        } else {
            return utente.getType();
        }

    }

}




