package test;

import model.Civilization;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkCivilization(String name, int tech, int society, int culture, int resources, Civilization cv) {
        assertEquals(name,cv.getName());
        assertEquals(tech,cv.getTechnology());
        assertEquals(society,cv.getSociety());
        assertEquals(culture,cv.getCulture());
        assertEquals(resources,cv.getResources());

    }
}
