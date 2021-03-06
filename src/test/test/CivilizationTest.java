package test;

import model.Civilization;
import model.Universe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.exceptions.OutOfResourcesException;

import static org.junit.jupiter.api.Assertions.*;

class CivilizationTest {
    private Civilization earth;
    private Universe solar;

    @BeforeEach
    public void setup() {
        solar = new Universe("solar",10);
        earth = new Civilization("earth", 0, 0, 0, solar);

    }

    @Test
    public void testSettersAndGetters() {
        earth.setResources(2);
        assertEquals(2,earth.getResources());

        earth.setTechnology(2);
        assertEquals(2,earth.getTechnology());

        earth.setSociety(2);
        assertEquals(2,earth.getSociety());

        earth.setCulture(2);
        assertEquals(2,earth.getCulture());

        earth.setIsExposed(true);
        assertTrue(earth.getIsExposed());

        earth.setIsHarmful(false);
        assertFalse(earth.getIsHarmful());



        earth.setDevelopmentLimits(5);
        assertEquals(5,earth.getDevelopmentLimits());

        earth.setName("Human");
        assertEquals("Human",earth.getName());

        earth.setDimension(10);
        assertEquals(10,earth.getDimension());

        earth.setDarkForestKnownCulture(5);
        assertEquals(5,earth.getDarkForestKnownCulture());

        earth.setMatureSociety(10);
        assertEquals(10,earth.getMatureSociety());

        earth.setTechLevel1(1);
        assertEquals(1,earth.getTechLevel1());

        earth.setTechLevel2(3);
        assertEquals(3,earth.getTechLevel2());

        earth.setTechLevel3(5);
        assertEquals(5,earth.getTechLevel3());

        Universe galaxy = new Universe("galaxy",8);
        earth.setUniverse(galaxy);
        assertEquals(galaxy,earth.getUniverse());

        earth.setRoundNumber(28);
        assertEquals(28,earth.getRoundNumber());

    }


    @Test
    public void testAddRoundNumbersOnce() {
        assertEquals(1,earth.getRoundNumber());

        earth.addRoundNumber();
        assertEquals(2,earth.getRoundNumber());
    }

    @Test
    public void testAddRoundNumbersMultiples() {
        assertEquals(1,earth.getRoundNumber());

        for(int i = 0;i<10;i++) {
            earth.addRoundNumber();
        }
        assertEquals(11,earth.getRoundNumber());

    }

    @Test
    public void testAddDevelopmentPoints() {
        assertEquals(0, earth.getResources());

        earth.addResources(5);
        assertEquals(5, earth.getResources());
    }

    @Test
    public void testAddTechnologyHasDPHasNotFull() {
        try {
            assertEquals(0, earth.getTechnology());

            earth.addResources(5);
            earth.civilDevelopment(2,0,0);

            assertEquals(3, earth.getResources());
            assertEquals(2, earth.getTechnology());
        } catch (OutOfResourcesException e) {
            fail("Exceptions is not expected");
        }
    }

    @Test
    public void testAddTechnologyHasNoDP() {
        try {
            earth.civilDevelopment(2,0,0);
            fail("Exception is expected");
        } catch (OutOfResourcesException e) {
            //expected
        }
    }

    @Test
    public void testAddTechnologyFull() {
        earth.addResources(120);
        try {
            earth.civilDevelopment(100,0,0);

            assertEquals(100, earth.getTechnology());
            assertEquals(20, earth.getResources());

            earth.civilDevelopment(10,0,0);
            assertEquals(100,earth.getTechnology());
        } catch (OutOfResourcesException e) {
            fail("Exception is not expected");
        }
    }

    @Test
    public void testAddTechnologyNoDPAndFull() {
        earth.addResources(100);
        try {
            earth.civilDevelopment(100, 0, 0);

            assertEquals(100, earth.getTechnology());
            assertEquals(0, earth.getResources());

            earth.civilDevelopment(10,0,0);
            fail("Exception is expected!!");
        } catch (OutOfResourcesException e) {
            //expected
        }
    }

    @Test
    public void testAddSocietyHasDPHasNotFull() {
        try {
            assertEquals(0, earth.getSociety());

            earth.addResources(5);
            earth.civilDevelopment(0,2,0);

            assertEquals(3, earth.getResources());
            assertEquals(2, earth.getSociety());
        } catch (OutOfResourcesException e) {
            fail("Exceptions is not expected");
        }
    }

    @Test
    public void testAddSocietyHasNoDP() {
        try {
            earth.civilDevelopment(0,2,0);
            fail("Exception is expected");
        } catch (OutOfResourcesException e) {
            //expected
        }
    }

    @Test
    public void testAddSocietyFull() {
        earth.addResources(120);
        try {
            earth.civilDevelopment(0,100,0);

            assertEquals(100, earth.getSociety());
            assertEquals(20, earth.getResources());

            earth.civilDevelopment(0,10,0);
            assertEquals(100,earth.getSociety());
        } catch (OutOfResourcesException e) {
            fail("Exception is not expected");
        }
    }

    @Test
    public void testAddSocietyNoDPAndFull() {

        earth.addResources(100);
        try {
            earth.civilDevelopment(0, 100, 0);

            assertEquals(100, earth.getSociety());
            assertEquals(0, earth.getResources());

            earth.civilDevelopment(0,100,0);
            fail("Exception is expected!!");
        } catch (OutOfResourcesException e) {
            //expected
        }
    }

    @Test
    public void testAddCultureHasDPHasNotFull() {
        try {
            assertEquals(0, earth.getCulture());

            earth.addResources(5);
            earth.civilDevelopment(0,0,2);

            assertEquals(3, earth.getResources());
            assertEquals(2, earth.getCulture());
        } catch (OutOfResourcesException e) {
            fail("Exceptions is not expected");
        }
    }

    @Test
    public void testAddCultureHasNoDP() {
        try {
            earth.civilDevelopment(0,0,2);
            fail("Exception is expected");
        } catch (OutOfResourcesException e) {
            //expected
        }
    }

    @Test
    public void testAddCultureFull() {
        earth.addResources(120);
        try {
            earth.civilDevelopment(0,0,100);

            assertEquals(100, earth.getCulture());
            assertEquals(20, earth.getResources());

            earth.civilDevelopment(0,0,10);
            assertEquals(100,earth.getCulture());
        } catch (OutOfResourcesException e) {
            fail("Exception is not expected");
        }
    }

    @Test
    public void testAddCultureNoDPAndFull() {
        earth.addResources(100);
        try {
            earth.civilDevelopment(0, 0, 100);

            assertEquals(100, earth.getCulture());
            assertEquals(0, earth.getResources());

            earth.civilDevelopment(0,0,100);
            fail("Exception is expected!!");
        } catch (OutOfResourcesException e) {
            //expected
        }
    }

    @Test
    public void testExpose() {
        assertFalse(earth.getIsExposed());

        earth.expose();

        assertTrue(earth.getIsExposed());
    }


    @Test
    public void testMakeSafeStatement(){
        assertTrue(earth.getIsHarmful());

        earth.makeSafeStatement();

        assertFalse(earth.getIsHarmful());
    }

    @Test
    public void testEquals() {
        assertFalse(earth.equals(null));
        assertFalse(earth.equals(solar));

        Civilization testCv = new Civilization("earth",0,0,0,solar);
        assertTrue(earth.equals(testCv));

        Civilization testCv2 = new Civilization("earth",1,0,0,solar);
        assertFalse(earth.equals(testCv2));

        Civilization testCv3 = new Civilization("earth",0,1,0,solar);
        assertFalse(earth.equals(testCv3));

        Civilization testCv4 = new Civilization("earth",0,0,1,solar);
        assertFalse(earth.equals(testCv4));

        Civilization testCv5 = new Civilization("eart",0,0,0,solar);
        assertFalse(earth.equals(testCv5));

        Universe testUni = new Universe("s",3);
        Civilization testCv6 = new Civilization("earth",0,0,0,testUni);
        assertFalse(earth.equals(testCv6));

        Civilization testCv7 = new Civilization("earth",0,0,0,solar);
        testCv7.setRoundNumber(7);
        earth.setRoundNumber(2);
        assertFalse(earth.equals(testCv7));

        Civilization testCv8 = new Civilization("earth",0,0,0,solar);
        testCv8.setResources(122);
        earth.setResources(12);
        assertFalse(earth.equals(testCv8));

        Civilization testCv9 = new Civilization("earth",0,0,0,solar);
        testCv9.setDimension(5);
        earth.setDimension(10);
        assertFalse(earth.equals(testCv9));





    }





}

