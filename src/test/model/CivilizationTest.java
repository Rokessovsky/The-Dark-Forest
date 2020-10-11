package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CivilizationTest {
    private Civilization earth;
    private Universe solar;

    @BeforeEach
    public void setup() {
        solar = new Universe(10);
        earth = new Civilization("earth", 0, 0, 0, solar);
    }

    @Test
    public void testSettersAndGetters() {
        earth.setDevelopmentPoints(2);
        assertEquals(2,earth.getDevelopmentPoints());

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

        earth.setHasStarFleet(true);
        assertTrue(earth.getHasStarFleet());

        earth.setHasSophons(true);
        assertTrue(earth.getHasSophons());

        earth.setDarkForestPrinciple(true);
        assertTrue(earth.getHasDarkForestPrinciple());

        earth.setLightSpeedTravel(true);
        assertTrue(earth.getHasLightSpeedTravel());

        earth.setDevelopmentLimits(5);
        assertEquals(5,earth.getDevelopmentLimits());

        earth.setName("Human");
        assertEquals("Human",earth.getName());

    }

    @Test
    public void testAddDevelopmentPoints() {
        assertEquals(0, earth.getDevelopmentPoints());

        earth.addDevelopmentPoints(5);
        assertEquals(5, earth.getDevelopmentPoints());
    }

    @Test
    public void testAddTechnologyHasDPHasNotFull() {
        assertEquals(0, earth.getTechnology());

        earth.addDevelopmentPoints(5);
        earth.addTechnology();
        earth.addTechnology();


        assertEquals(3, earth.getDevelopmentPoints());
        assertEquals(2, earth.getTechnology());
    }

    @Test
    public void testAddTechnologyHasNoDP() {
        assertFalse(earth.addTechnology());
    }

    @Test
    public void testAddTechnologyFull() {
        earth.addDevelopmentPoints(100);
        for (int count = 1; count <= 100; count++) {
            earth.addTechnology();
        }

        assertEquals(100, earth.getTechnology());
        assertEquals(0, earth.getDevelopmentPoints());

        assertFalse(earth.addTechnology());
    }

    @Test
    public void testAddSocietyHasDPHasNotFull() {
        assertEquals(0, earth.getSociety());

        earth.addDevelopmentPoints(5);
        earth.addSociety();
        earth.addSociety();


        assertEquals(3, earth.getDevelopmentPoints());
        assertEquals(2, earth.getSociety());
    }

    @Test
    public void testAddSocietyHasNoDP() {
        assertFalse(earth.addSociety());
    }

    @Test
    public void testAddSocietyFull() {
        earth.addDevelopmentPoints(100);
        for (int count = 1; count <= 100; count++) {
            earth.addSociety();
        }

        assertEquals(100, earth.getSociety());
        assertEquals(0, earth.getDevelopmentPoints());

        assertFalse(earth.addSociety());
    }

    @Test
    public void testAddCultureHasDPHasNotFull() {
        assertEquals(0, earth.getCulture());

        earth.addDevelopmentPoints(5);
        earth.addCulture();
        earth.addCulture();


        assertEquals(3, earth.getDevelopmentPoints());
        assertEquals(2, earth.getCulture());
    }

    @Test
    public void testAddCultureHasNoDP() {
        assertFalse(earth.addCulture());
    }

    @Test
    public void testAddCultureFull() {
        earth.addDevelopmentPoints(100);
        for (int count = 1; count <= 100; count++) {
            earth.addCulture();
        }

        assertEquals(100, earth.getCulture());
        assertEquals(0, earth.getDevelopmentPoints());

        assertFalse(earth.addCulture());
    }

    @Test
    public void testExpose() {
        assertFalse(earth.getIsExposed());

        earth.expose();

        assertTrue(earth.getIsExposed());
    }

    @Test
    public void testObtainDarkForestPrinciple() {
        assertFalse(earth.getHasDarkForestPrinciple());

        earth.obtainDarkForestPrinciple();

        assertTrue(earth.getHasDarkForestPrinciple());
    }

    @Test
    public void testMakeSafeStatement(){
        assertTrue(earth.getIsHarmful());

        earth.makeSafeStatement();

        assertFalse(earth.getIsHarmful());
    }

    @Test
    public void testObtainLightSpeedTravel(){
        assertFalse(earth.getHasLightSpeedTravel());

        earth.obtainLightSpeedTravel();

        assertTrue(earth.getHasLightSpeedTravel());

    }



}

