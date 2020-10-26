package test;


import model.Civilization;
import model.Universe;
import org.junit.jupiter.api.Test;
import persistence.CivilizationReader;
import persistence.CivilizationWriter;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CivilizationWriterTest {

    @Test
    void testCivilizationWriterInvalidFile() {
        try{
            Universe universe = new Universe("solar",10);
            Civilization cv = new Civilization("earth",1,2,3,universe);
            CivilizationWriter writer = new CivilizationWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch(IOException e) {
            // pass
        }
    }

    @Test
    void testCivilizationWriterValid() {
         try{
             Universe universe = new Universe("solar",10);
             Civilization cv = new Civilization("earth",1,2,3,universe);
             cv.setResources(20);
             CivilizationWriter writer = new CivilizationWriter("./data/testCivilizationWriter");
             writer.open();
             writer.write(cv);
             writer.close();

             CivilizationReader reader = new CivilizationReader("./data/testCivilizationWriter");
             cv = reader.read();
             assertEquals("earth",cv.getName());
             assertEquals(1,cv.getTechnology());
             assertEquals(2,cv.getSociety());
             assertEquals(3,cv.getCulture());
             assertEquals(20,cv.getResources());
             assertEquals(universe,cv.getUniverse());


         } catch(IOException e) {
             fail("Exception should not have been thrown");
         }
    }

}
