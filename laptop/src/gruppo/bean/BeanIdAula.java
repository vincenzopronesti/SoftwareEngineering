package gruppo.bean;

public class BeanIdAula {
    private int idAula;

    public BeanIdAula(int idAula) {
        this.idAula = idAula;
    }

    public int getIdAula() {
        return idAula;
    }

    public void setIdAula(int idAula) {
        this.idAula = idAula;
    }

    @Override
    public String toString() {
        return "idAula = " + this.getIdAula();
    }
}
