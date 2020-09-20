package gruppo.entity;

public abstract class Utente {
    private String name;
    private String username;
    private String password;
    private String type;

    public Utente(String name, String username, String password, String type) {
        if (!type.equals("Professore") && !type.equals("Segretario")) {
            System.out.println("error"); //type errato
        }
        this.name = name;
        this.username = username;
        this.password = password;
        this.type = type;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (!type.equals("Professore") && !type.equals("Segretario")) {
            System.out.println("error"); //type errato
        }
        this.type = type;
    }
}
