package test;

import model.Universe;
import org.junit.jupiter.api.Test;
import persistence.UniverseReader;



import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class UniverseReaderTest extends JsonTest {

    @Test
    void testUniverseReaderNonExistentFile() {
        UniverseReader reader = new UniverseReader("./data/noSuchFile.json");
        try{
            Universe uni = reader.read();
            fail("IOException expected");

        } catch (IOException e) {
            //expected
        }
    }

    @Test
    void testUniverseReaderEmptyUniverse() {
        UniverseReader reader = new UniverseReader("./data/testUniverseWriterEmptyUniverse");
        try{
            Universe uni = reader.read();
            assertEquals(10,uni.getDimension());
            assertEquals(0,uni.getCivilizations().size());
        } catch (IOException e) {
            fail("Couldn't read the file");
        }
    }

    @Test
    void testUniverseReaderGeneral() {
        UniverseReader reader = new UniverseReader("./data/testUniverseWriterGeneral");
        try {
            Universe uni = reader.read();
            assertEquals(10,uni.getDimension());
            assertEquals(2,uni.getCivilizations().size());
            checkCivilization("cv1",5,4,3,88,uni.getCivilizations().get(0));
            checkCivilization("cv2",10,3,9,11,uni.getCivilizations().get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
