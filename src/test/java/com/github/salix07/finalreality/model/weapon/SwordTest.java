package com.github.salix07.finalreality.model.weapon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SwordTest {

    private static final String SWORD_NAME = "Test Sword";
    private static final int DAMAGE = 15;
    private static final int SPEED = 10;

    private Sword testSword;

    @BeforeEach
    void setUp() {
        testSword = new Sword(SWORD_NAME, DAMAGE, SPEED);
    }

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

        assertTrue(testSword.equals(expectedSword));
        assertFalse(testSword.equals(anotherSword1));
        assertFalse(testSword.equals(anotherSword2));
        assertFalse(testSword.equals(anotherSword3));
        assertFalse(testSword.equals(expectedAxe));
    }

    @Test
    void gettersTest() {
        assertEquals(SWORD_NAME, testSword.getName());
        assertEquals(DAMAGE, testSword.getDamage());
        assertEquals(SPEED, testSword.getWeight());
    }
}
