package gruppo.entity;

public class Professore extends Utente {
    public Professore(String name, String username, String password) {
        super(name, username, password, "Professore"); // il tipo già è noto
    }
}
