package test;

import model.Civilization;
import org.junit.jupiter.api.Test;
import persistence.CivilizationReader;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CivilizationReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        CivilizationReader reader = new CivilizationReader("./data/noSuchFile.json");
        try{
            Civilization cv = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            //expected
        }
    }

    @Test
    void testCivilizationReader() {
        CivilizationReader reader = new CivilizationReader("./data/testCivilizationWriter");
        try{
            Civilization cv = reader.read();
            assertEquals("earth",cv.getName());
            assertEquals(1,cv.getTechnology());
            assertEquals(2,cv.getSociety());
            assertEquals(3,cv.getCulture());
            assertEquals(20,cv.getResources());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
