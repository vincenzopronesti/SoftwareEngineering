package Vincenzo.controller;

import Vincenzo.bean.BeanSessione;
import Vincenzo.boundary.InterfacciaSegretarioDefinizioneSessione;
import Vincenzo.controller.GestoreDefinizioneSessione;
import gruppo.entity.TipoSessione;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class)
public class GestoreDefinizioneSessioneGestioneDefinizioneTest {
    private final boolean expected;
    private final String annoAccademico;
    private final LocalDate dataInizioSessione;
    private final LocalDate dataFineSessione;
    private final TipoSessione tipologia;
    
    @Parameters
    public static Collection<Object[]> getTestParameters() {
        return Arrays.asList(new Object[][] {
            // la sessione e' precedente all'anno accademico in cui la si vorrebbe inserire
            {false, "2016-2017", LocalDate.of(2016, Month.MARCH, 1), LocalDate.of(2016, Month.MARCH, 31), TipoSessione.valueOf("estiva")},
            
            // la sessione e' successiva all'anno accademico in cui la si vorrebbe inserire
            {false, "2016-2017", LocalDate.of(2018, Month.MARCH, 1), LocalDate.of(2018, Month.MARCH, 31), TipoSessione.valueOf("estiva")},
            
            // definizione corretta di una sessione per i controlli successivi
            // alla seconda esecuzione, il test non dovrebbe essere superato
            {true, "2016-2017", LocalDate.of(2017, Month.MARCH, 1), LocalDate.of(2017, Month.MARCH, 31), TipoSessione.valueOf("estiva")},
            // e' uguale alla precedente ma questa non dovrebbe essere accettata dato che non possono esistere due sessioni identiche
            {false, "2016-2017", LocalDate.of(2017, Month.MARCH, 1), LocalDate.of(2017, Month.MARCH, 31), TipoSessione.valueOf("estiva")},
            
            //sovrapposizione generica interna alla sessione esistente
            {false, "2016-2017", LocalDate.of(2017, Month.MARCH, 5), LocalDate.of(2017, Month.MARCH, 20), TipoSessione.valueOf("estiva")},
            
            //sovrapposizione che contiene la data di inizio della sessione esistene
            {false, "2016-2017", LocalDate.of(2017, Month.FEBRUARY, 15), LocalDate.of(2017, Month.MARCH, 20), TipoSessione.valueOf("estiva")},
            
            //sovrapposizione che contiene la data di fine della sessione esistente
            {false, "2016-2017", LocalDate.of(2017, Month.MARCH, 25), LocalDate.of(2017, Month.APRIL, 20), TipoSessione.valueOf("estiva")},
            
            //sovrapposizione generica esterna alla sessione esistente
            {false, "2016-2017", LocalDate.of(2017, Month.FEBRUARY, 15), LocalDate.of(2017, Month.MAY, 20), TipoSessione.valueOf("estiva")},
            
            //sovrapposizione solo sul primo giorno della sessione definita correttamente
            {false, "2016-2017", LocalDate.of(2017, Month.FEBRUARY, 20), LocalDate.of(2017, Month.MARCH, 1), TipoSessione.valueOf("estiva")},
                        
            //sovrapposizione solo sull'ultimo giorno della sessione definita correttamente
            {false, "2016-2017", LocalDate.of(2017, Month.MARCH, 31), LocalDate.of(2017, Month.APRIL, 10), TipoSessione.valueOf("estiva")}
                        
        });
    }
    
    public GestoreDefinizioneSessioneGestioneDefinizioneTest(boolean expected, 
            String annoAccademico, LocalDate dataInizioSessione, 
            LocalDate dataFineSessione, TipoSessione tipologia) {
        this.expected = expected;
        this.annoAccademico = annoAccademico;
        this.dataInizioSessione = dataInizioSessione;
        this.dataFineSessione = dataFineSessione;
        this.tipologia = tipologia;
        
    }
    
    /**
     * il metodo gestioneDefinizioneSessione prende in input un 
     * oggetto di tipo BeanSessionechiama prima il metodo 
     * controllaDateSessione (test sopra) e prima di inserire la sessione nel DB 
     * fa dei controlli aggiuntivi per assicurarsi che non ci siano sovrapposizioni con 
     * altre sessioni precedentemente definite 
     */
    @Test
    public void testGestioneDefinizioneSessione() {
        BeanSessione beanSessione = new BeanSessione(annoAccademico, 
                dataInizioSessione, dataFineSessione, tipologia);
        InterfacciaSegretarioDefinizioneSessione segretario = new InterfacciaSegretarioDefinizioneSessione("s");
        GestoreDefinizioneSessione gestore = 
                new GestoreDefinizioneSessione(segretario);
        
        boolean res = gestore.gestioneDefinizioneSessione(beanSessione);
        assertEquals("errore", res, expected);
    }
}
