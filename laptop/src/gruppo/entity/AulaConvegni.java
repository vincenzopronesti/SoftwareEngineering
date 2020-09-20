package gruppo.entity;

public class AulaConvegni extends Aula {


    public AulaConvegni(int id, int numPosti, boolean proiettore, boolean microfono, boolean lavagna, boolean lavagnaInterattiva, boolean preseCorrente, boolean ethernet) {
        super(id, numPosti, proiettore, microfono, lavagna, lavagnaInterattiva, preseCorrente, ethernet);
    }

    public AulaConvegni() {
        super();
    }

    public AulaConvegni(int id) {
        super(id);
    }
}
