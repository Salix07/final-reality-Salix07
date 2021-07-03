package com.github.salistito.finalreality.model.weapon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class containing the tests for the Bows.
 *
 * @author Sebastián Salinas Rodriguez.
 */
public class BowTest {

    private static final String BOW_NAME = "Test Bow";
    private static final int DAMAGE = 15;
    private static final int SPEED = 10;

    private Bow testBow;

    /**
     * Start the set up for the test.
     */
    @BeforeEach
    void setUp() {
        testBow = new Bow(BOW_NAME, DAMAGE, SPEED);
    }

    /**
     * Test that check the correct construction and equalities between Bows.
     */
    @Test
    void constructorTest() {
        var expectedBow = new Bow(BOW_NAME, DAMAGE, SPEED);
        var anotherBow1 = new Bow("Last Whisper", DAMAGE, SPEED);
        var anotherBow2 = new Bow(BOW_NAME, 10, SPEED);
        var anotherBow3 = new Bow(BOW_NAME, DAMAGE, 15);
        var expectedKnife = new Knife(BOW_NAME, DAMAGE, SPEED);

        assertEquals(expectedBow, testBow);
        assertEquals(expectedBow.hashCode(), testBow.hashCode());

        assertEquals(testBow, testBow);
        assertEquals(testBow.hashCode(),testBow.hashCode());

        assertEquals(expectedBow, testBow);
        assertNotEquals(anotherBow1, testBow);
        assertNotEquals(anotherBow2, testBow);
        assertNotEquals(anotherBow3, testBow);
        assertNotEquals(expectedKnife, testBow);
    }

    /**
     * Test for the getters.
     */
    @Test
    void gettersTest() {
        assertEquals(BOW_NAME, testBow.getName());
        assertEquals(DAMAGE, testBow.getDamage());
        assertEquals(SPEED, testBow.getWeight());
    }

    @Test
    void toStringTest() {
        String expectedString = "Bow";
        assertEquals(expectedString, testBow.toString());
    }
}
