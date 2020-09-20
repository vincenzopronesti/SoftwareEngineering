package gruppo.bean;

import java.util.Random;

public class BeanCaratteristicheAula {
    private int numPosti;
    private boolean proiettore;
    private boolean microfono;
    private boolean lavagna;
    private boolean lavagnaInterattiva;
    private boolean preseCorrente;
    private boolean ethernet;
    private String type;

    public BeanCaratteristicheAula( ) { }

    public BeanCaratteristicheAula(int numPosti) {
        this.numPosti = numPosti;
        this.proiettore = false;
        this.microfono = false;
        this.lavagnaInterattiva = false;
        this.preseCorrente = false;
        this.ethernet = false;
    }

    public BeanCaratteristicheAula(int numPosti, boolean proiettore) {
        this.numPosti = numPosti;
        this.proiettore = proiettore;
        this.microfono = false;
        this.lavagna = false;
        this.lavagnaInterattiva = false;
        this.preseCorrente = false;
        this.ethernet = false;

    }

    public BeanCaratteristicheAula(int numPosti, boolean proiettore, boolean microfono) {
        this.numPosti = numPosti;
        this.proiettore = proiettore;
        this.microfono = microfono;
        this.lavagna = false;
        this.lavagnaInterattiva = false;
        this.preseCorrente = false;
        this.ethernet = false;
    }

    public BeanCaratteristicheAula(int numPosti, boolean proiettore, boolean microfono, boolean lavagna) {
        this.numPosti = numPosti;
        this.proiettore = proiettore;
        this.microfono = microfono;
        this.lavagna = lavagna;
        this.lavagnaInterattiva = false;
        this.preseCorrente = false;
        this.ethernet = false;
    }

    public BeanCaratteristicheAula(int numPosti, boolean proiettore, boolean microfono, boolean lavagna, boolean lavagnaInterattiva) {
        this.numPosti = numPosti;
        this.proiettore = proiettore;
        this.microfono = microfono;
        this.lavagna = lavagna;
        this.lavagnaInterattiva = lavagnaInterattiva;
        this.preseCorrente = false;
        this.ethernet = false;
    }
    //TODO andysnake add
    public BeanCaratteristicheAula(int numPosti, boolean proiettore, boolean microfono,
                                   boolean lavagna, boolean lavagnaInterattiva,String type) {
        this.numPosti = numPosti;
        this.proiettore = proiettore;
        this.microfono = microfono;
        this.lavagna = lavagna;
        this.lavagnaInterattiva = lavagnaInterattiva;
        this.preseCorrente = false;
        this.ethernet = false;
        if(!type.equals("DIDATTICA") && !type.equals("CONVEGNI") &&!type.equals("DIDATTICA-LABORATORIO")  ) {
            System.err.println("tipo aula inserito errato...");
            //todo exit o expetion throw...
        }
        this.type=type;
    }

    public BeanCaratteristicheAula(int numPosti, boolean proiettore, boolean microfono, boolean lavagna, boolean lavagnaInterattiva, boolean preseCorrente) {
        this.numPosti = numPosti;
        this.proiettore = proiettore;
        this.microfono = microfono;
        this.lavagna = lavagna;
        this.lavagnaInterattiva = lavagnaInterattiva;
        this.preseCorrente = preseCorrente;
        this.ethernet = false;
    }

    public BeanCaratteristicheAula(int numPosti, boolean proiettore, boolean microfono, boolean lavagna, boolean lavagnaInterattiva,
                                   boolean preseCorrente, boolean ethernet) {
        this.numPosti = numPosti;
        this.proiettore = proiettore;
        this.microfono = microfono;
        this.lavagna = lavagna;
        this.lavagnaInterattiva = lavagnaInterattiva;
        this.preseCorrente = preseCorrente;
        this.ethernet = ethernet;

    }

    public int getNumPosti() {
        return numPosti;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type=type;
    }

    public void setNumPosti(int numPosti) {
        this.numPosti = numPosti;
    }

    public boolean isProiettore() {
        return proiettore;
    }

    public void setProiettore(boolean proiettore) {
        this.proiettore = proiettore;
    }

    public boolean isMicrofono() {
        return microfono;
    }

    public void setMicrofono(boolean microfono) {
        this.microfono = microfono;
    }

    public boolean isLavagna() {
        return lavagna;
    }

    public void setLavagna(boolean lavagna) {
        this.lavagna = lavagna;
    }

    public boolean isLavagnaInterattiva() {
        return lavagnaInterattiva;
    }

    public void setLavagnaInterattiva(boolean lavagnaInterattiva) {
        this.lavagnaInterattiva = lavagnaInterattiva;
    }

    public boolean isPreseCorrente() {
        return preseCorrente;
    }

    public void setPreseCorrente(boolean preseCorrente) {
        this.preseCorrente = preseCorrente;
    }

    public boolean isEthernet() {
        return ethernet;
    }

    public void setEthernet(boolean ethernet) {
        this.ethernet = ethernet;
    }

    @Override
    public String toString() {
        return "BeanCaratteristicheAula{" +
                "numPosti=" + numPosti +
                ", proiettore=" + proiettore +
                ", microfono=" + microfono +
                ", lavagna=" + lavagna +
                ", lavagnaInterattiva=" + lavagnaInterattiva +
                ", preseCorrente=" + preseCorrente +
                ", ethernet=" + ethernet +
                '}';
    }
    //ANDYSNAKE random bean...
    public static BeanCaratteristicheAula randomBean(){
        Random r = new Random () ;
        int numPosti=r.nextInt();
        boolean proiettore=r.nextBoolean();
        boolean microfono=r.nextBoolean();
        boolean lavagna=r.nextBoolean();
        boolean lavagnaInterattiva=r.nextBoolean();
        boolean preseCorrente=r.nextBoolean();
        boolean ethernet=r.nextBoolean();
        return new BeanCaratteristicheAula(numPosti,proiettore,microfono,
                lavagna,lavagnaInterattiva,preseCorrente,ethernet);
        //return new random bean caratteristiche aula

    }
}
