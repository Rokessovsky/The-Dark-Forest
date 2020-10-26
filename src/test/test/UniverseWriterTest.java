package test;

import model.Civilization;
import model.Universe;
import org.junit.jupiter.api.Test;
import persistence.CivilizationWriter;
import persistence.UniverseReader;
import persistence.UniverseWriter;
import persistence.Writer;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class UniverseWriterTest extends JsonTest{

    @Test
    void testUniverseWriterInvalidFile() {
        try{
            Universe universe = new Universe("solar",10);
            UniverseWriter writer = new UniverseWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch(IOException e) {
            // pass
        }
    }

    @Test
    void testUniverseWriterEmptyUniverse() {
        try{
            Universe universe = new Universe("solar",10);
            UniverseWriter writer = new UniverseWriter("./data/testUniverseWriterEmptyUniverse");
            writer.open();
            writer.write(universe);
            writer.close();

            UniverseReader reader = new UniverseReader("./data/testUniverseWriterEmptyUniverse");
            universe = reader.read();
            assertEquals(10,universe.getDimension());
            assertEquals("solar",universe.getName());
            assertEquals(0,universe.getCivilizations().size());
        } catch(IOException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    void testUniverseWriterGeneral() {
        try{
            Universe universe = new Universe("solar",10);
            Civilization cv1 = new Civilization("cv1",5,4,3,universe);
            Civilization cv2 = new Civilization("cv2",10,3,9,universe);
            cv1.setResources(88);
            cv2.setResources(11);
            universe.addCivilization(cv1);
            universe.addCivilization(cv2);

            UniverseWriter writer = new UniverseWriter("./data/testUniverseWriterGeneral");
            writer.open();
            writer.write(universe);
            writer.close();

            UniverseReader reader = new UniverseReader("./data/testUniverseWriterGeneral");
            universe = reader.read();
            assertEquals(10,universe.getDimension());
            assertEquals("solar",universe.getName());
            assertEquals(2,universe.getCivilizations().size());
            checkCivilization("cv1",5,4,3,88,universe.getCivilizations().get(0));
            checkCivilization("cv2",10,3,9,11,universe.getCivilizations().get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}


