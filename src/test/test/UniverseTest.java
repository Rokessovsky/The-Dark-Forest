package test;

import model.Civilization;
import model.Universe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class UniverseTest {
    private Universe galaxy;
    private ArrayList<Civilization> civilizations;
    private Civilization earth;
    private Civilization threeBody;

    @BeforeEach
    public void setup() {
        galaxy = new Universe("galaxy",10);

        earth = new Civilization("earth",2,2,2,galaxy);
        threeBody = new Civilization("Three Body",10,10,10,galaxy);

    }

    @Test
    public void testSettersAndGetters() {
        civilizations = new ArrayList<>();
        civilizations.add(earth);
        civilizations.add(threeBody);

        galaxy.setCivilizations(civilizations);
        assertEquals(2,galaxy.getCivilizations().size());

        galaxy.setDimension(5);
        assertEquals(5,galaxy.getDimension());

        galaxy.setName("we");
        assertEquals("we",galaxy.getName());
    }

    @Test
    public void testAddCivilizationNoDuplicate() {
        assertTrue(galaxy.addCivilization(earth));
        galaxy.addCivilization(earth);
        assertEquals(1,galaxy.getCivilizations().size());

        assertTrue(galaxy.addCivilization(threeBody));
        galaxy.addCivilization(threeBody);
        assertEquals(2,galaxy.getCivilizations().size());
    }

    @Test
    public void testAddCivilizationDuplicate() {
        assertTrue(galaxy.addCivilization(earth));
        galaxy.addCivilization(earth);
        assertEquals(1,galaxy.getCivilizations().size());


        assertFalse(galaxy.addCivilization(earth));
        galaxy.addCivilization(earth);
        assertEquals(1,galaxy.getCivilizations().size());
    }

    @Test
    public void testDestroyCivilizationInSet() {

        galaxy.addCivilization(earth);
        galaxy.addCivilization(threeBody);

        assertTrue(galaxy.destroyCivilization(earth));
        galaxy.destroyCivilization(earth);

        assertEquals(1,galaxy.getCivilizations().size());
        assertEquals(threeBody,galaxy.getCivilizations().get(0));
    }

    @Test
    public void testDestroyCivilizationNotInSet() {
        galaxy.addCivilization(earth);

        assertFalse(galaxy.destroyCivilization(threeBody));
        galaxy.destroyCivilization(threeBody);
        assertEquals(1,galaxy.getCivilizations().size());
    }

    @Test
    public void testDimensionalityReduction() {
        galaxy.dimensionalityReduction();

        assertEquals(9,galaxy.getDimension());
    }











}
