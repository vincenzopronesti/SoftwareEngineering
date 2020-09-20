package Vincenzo.bean;

public class BeanUtente {
    private String username;
    private String type;
    
    public BeanUtente(String username, String type) {
        if (!type.equals("Professore") && !type.equals("Segretario")) {
            System.out.println("errore nel costruttore di BeanUtente, il tipo "
                    + "deve essere 'Professore' oppure 'Segretario'");
        }
        this.username = username;
        this.type = type;
        
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
        this.type = type;
    }    
}
