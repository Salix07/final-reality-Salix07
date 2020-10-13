package com.github.salix07.finalreality.model.weapon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BowTest {

    private static final String BOW_NAME = "Test Bow";
    private static final int DAMAGE = 15;
    private static final int SPEED = 10;

    private Bow testBow;

    @BeforeEach
    void setUp() {
        testBow = new Bow(BOW_NAME, DAMAGE, SPEED);
    }

    @Test
    void constructorTest() {
        var expectedBow = new Bow(BOW_NAME, DAMAGE, SPEED);
        var anotherBow1 = new Bow("Last Whisper", DAMAGE, SPEED);
        var anotherBow2 = new Bow(BOW_NAME, 10, SPEED);
        var anotherBow3 = new Bow(BOW_NAME, DAMAGE, 15);
        var expectedKnife = new Knife(BOW_NAME, DAMAGE, SPEED);

        assertEquals(expectedBow, testBow);
        assertEquals(expectedBow.hashCode(), testBow.hashCode());

        assertTrue(expectedBow.equals(expectedBow));
        assertFalse(expectedBow.equals(anotherBow1));
        assertFalse(expectedBow.equals(anotherBow2));
        assertFalse(expectedBow.equals(anotherBow3));
        assertFalse(expectedBow.equals(expectedKnife));
    }

    @Test
    void gettersTest() {
        assertEquals(BOW_NAME, testBow.getName());
        assertEquals(DAMAGE, testBow.getDamage());
        assertEquals(SPEED, testBow.getWeight());
    }
}
