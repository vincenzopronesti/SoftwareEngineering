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
public class GestoreDefinizioneSessioneControlloDateTest {
    private final boolean expected;
    private final String annoAccademico;
    private final LocalDate dataInizioSessione;
    private final LocalDate dataFineSessione;
    private final TipoSessione tipologia;
    
    @Parameters
    public static Collection<Object[]> getTestParameters() {
        return Arrays.asList(new Object[][] {
            // anno accademico non esistente
            {false, "2025-2026", LocalDate.of(2025, Month.OCTOBER, 1), LocalDate.of(2026, Month.MARCH, 1), TipoSessione.valueOf("estiva")},
            
            // anno accademico esistente e sessione corretta
            {true, "2016-2017", LocalDate.of(2016, Month.OCTOBER, 15), LocalDate.of(2017, Month.MARCH, 1), TipoSessione.valueOf("estiva")},
            
            // inizio della sessione dopo la fine
            {false, "2016-2017", LocalDate.of(2016, Month.DECEMBER, 1), LocalDate.of(2016, Month.OCTOBER, 15), TipoSessione.valueOf("estiva")},
            
            // sessione prima dell'anno accademico
            {false, "2016-2017", LocalDate.of(2014, Month.MARCH, 1), LocalDate.of(2014, Month.MARCH, 15), TipoSessione.valueOf("estiva")},
            
            // sessione dopo la fine dell'anno accademico
            {false, "2016-2017", LocalDate.of(2018, Month.MARCH, 1), LocalDate.of(2018, Month.APRIL, 1), TipoSessione.valueOf("estiva")},
        
            // sessione che inizia prima ma finisce dopo l'inizio dell'anno accademico
            {false, "2016-2017", LocalDate.of(2016, Month.SEPTEMBER, 1), LocalDate.of(2016, Month.DECEMBER, 1), TipoSessione.valueOf("estiva")},
            
            // sessione che inizia nell'anno accademico ma finisce oltre
            {false, "2016-2017", LocalDate.of(2018, Month.JANUARY, 1), LocalDate.of(2018, Month.APRIL, 1), TipoSessione.valueOf("estiva")},
            
            // sessione che si sovrappone alla data d'inizio dell'anno accademico (non si richiede che esistano lezioni prima della sessione d'esame)
            {true, "2016-2017", LocalDate.of(2016, Month.OCTOBER, 3), LocalDate.of(2018, Month.FEBRUARY, 1), TipoSessione.valueOf("estiva")},
            
            // sessione che si sovrappone alla data di fine dell'anno accademico
            {true, "2016-2017", LocalDate.of(2018, Month.JANUARY, 1), LocalDate.of(2018, Month.FEBRUARY, 28), TipoSessione.valueOf("estiva")}
        });
    }
    
    public GestoreDefinizioneSessioneControlloDateTest(boolean expected, 
            String annoAccademico, LocalDate dataInizioSessione, 
            LocalDate dataFineSessione, TipoSessione tipologia) {
        this.expected = expected;
        this.annoAccademico = annoAccademico;
        this.dataInizioSessione = dataInizioSessione;
        this.dataFineSessione = dataFineSessione;
        this.tipologia = tipologia;
        
    }
    
    /**
     * il metodo controllaDateSessione prende come parametro un oggetto di 
     * tipo BeanSessione
     * 
     * controlla che l'anno accademico inserito esista 
     * controlla che l'inizio della sessione sia prima della fine 
     * controlla che la fine della sessione non sia oltre la fine dell'anno accademico 
     * controlla che l'inizio della sessione non sia prima dell'inizio dell'anno accademico
     */
    @Test
    public void testControllaDateSessioneSessione() {

        BeanSessione beanSessione = new BeanSessione(annoAccademico, 
                dataInizioSessione, dataFineSessione, tipologia);
        
        InterfacciaSegretarioDefinizioneSessione segretario = new InterfacciaSegretarioDefinizioneSessione("s");
        GestoreDefinizioneSessione gestore = 
                new GestoreDefinizioneSessione(segretario);
        
        boolean res = gestore.controllaDateSessione(beanSessione);
        assertEquals("errore nella definizione", res, expected);
    }
}