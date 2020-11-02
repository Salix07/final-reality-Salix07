package com.github.salix07.finalreality.model.weapon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AxeTest {

    private static final String AXE_NAME = "Test Axe";
    private static final int DAMAGE = 15;
    private static final int SPEED = 10;

    private Axe testAxe;

    @BeforeEach
    void setUp() {
        testAxe = new Axe(AXE_NAME, DAMAGE, SPEED);
    }

    @Test
    void constructorTest() {
        var expectedAxe = new Axe(AXE_NAME, DAMAGE, SPEED);
        var anotherAxe1 = new Axe("StormBreaker", DAMAGE, SPEED);
        var anotherAxe2 = new Axe(AXE_NAME, 10, SPEED);
        var anotherAxe3 = new Axe(AXE_NAME, DAMAGE, 15);
        var expectedBow = new Bow(AXE_NAME, DAMAGE, SPEED);

        assertEquals(expectedAxe, testAxe);
        assertEquals(expectedAxe.hashCode(), testAxe.hashCode());

        assertEquals(testAxe, testAxe);
        assertEquals(testAxe.hashCode(),testAxe.hashCode());

        assertEquals(expectedAxe, testAxe);
        assertNotEquals(anotherAxe1, testAxe);
        assertNotEquals(anotherAxe2, testAxe);
        assertNotEquals(anotherAxe3, testAxe);
        assertNotEquals(expectedBow, testAxe);
    }

    @Test
    void gettersTest() {
        assertEquals(AXE_NAME, testAxe.getName());
        assertEquals(DAMAGE, testAxe.getDamage());
        assertEquals(SPEED, testAxe.getWeight());
    }
}
