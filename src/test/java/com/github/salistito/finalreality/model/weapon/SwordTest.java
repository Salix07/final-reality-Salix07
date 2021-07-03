package com.github.salistito.finalreality.model.weapon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class containing the tests for the Sword.
 *
 * @author Sebastián Salinas Rodriguez.
 */
public class SwordTest {

    private static final String SWORD_NAME = "Test Sword";
    private static final int DAMAGE = 15;
    private static final int SPEED = 10;

    private Sword testSword;

    /**
     * Start the set up for the test.
     */
    @BeforeEach
    void setUp() {
        testSword = new Sword(SWORD_NAME, DAMAGE, SPEED);
    }

    /**
     * Test that check the correct construction and equalities between Swords.
     */
    @Test
    void constructorTest() {
        var expectedSword = new Sword(SWORD_NAME, DAMAGE, SPEED);
        var anotherSword1 = new Sword("Infinity Edge", DAMAGE, SPEED);
        var anotherSword2 = new Sword(SWORD_NAME, 10, SPEED);
        var anotherSword3 = new Sword(SWORD_NAME, DAMAGE, 15);
        var expectedAxe = new Axe(SWORD_NAME, DAMAGE, SPEED);

        assertEquals(expectedSword, testSword);
        assertEquals(expectedSword.hashCode(), testSword.hashCode());

        assertEquals(testSword, testSword);
        assertEquals(testSword.hashCode(), testSword.hashCode());

        assertEquals(expectedSword, testSword);
        assertNotEquals(anotherSword1, testSword);
        assertNotEquals(anotherSword2, testSword);
        assertNotEquals(anotherSword3, testSword);
        assertNotEquals(expectedAxe, testSword);
    }

    /**
     * Test for the getters.
     */
    @Test
    void gettersTest() {
        assertEquals(SWORD_NAME, testSword.getName());
        assertEquals(DAMAGE, testSword.getDamage());
        assertEquals(SPEED, testSword.getWeight());
    }

    @Test
    void toStringTest() {
        String expectedString = "Sword";
        assertEquals(expectedString, testSword.toString());
    }
}
