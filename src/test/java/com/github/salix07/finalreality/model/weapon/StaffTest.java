package com.github.salix07.finalreality.model.weapon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StaffTest {

    private static final String STAFF_NAME = "Test Staff";
    private static final int DAMAGE = 15;
    private static final int MAGIC_DAMAGE = 20;
    private static final int SPEED = 10;

    private Staff testStaff;

    @BeforeEach
    void setUp() {
        testStaff = new Staff(STAFF_NAME, DAMAGE, MAGIC_DAMAGE, SPEED);
    }

    @Test
    void constructorTest() {
        var expectedStaff = new Staff(STAFF_NAME, DAMAGE, MAGIC_DAMAGE, SPEED);
        var anotherStaff1 = new Staff("VoidStaff", DAMAGE, MAGIC_DAMAGE, SPEED);
        var anotherStaff2 = new Staff(STAFF_NAME, 20, MAGIC_DAMAGE, SPEED);
        var anotherStaff3 = new Staff(STAFF_NAME, DAMAGE, 10, SPEED);
        var anotherStaff4 = new Staff(STAFF_NAME, DAMAGE, MAGIC_DAMAGE, 15);
        var expectedSword = new Sword(STAFF_NAME, DAMAGE, SPEED);

        assertEquals(expectedStaff, testStaff);
        assertEquals(expectedStaff.hashCode(), testStaff.hashCode());

        assertTrue(expectedStaff.equals(expectedStaff));
        assertFalse(expectedStaff.equals(anotherStaff1));
        assertFalse(expectedStaff.equals(anotherStaff2));
        assertFalse(expectedStaff.equals(anotherStaff3));
        assertFalse(expectedStaff.equals(anotherStaff4));
        assertFalse(expectedStaff.equals(expectedSword));
    }

    @Test
    void gettersTest() {
        assertEquals(STAFF_NAME, testStaff.getName());
        assertEquals(DAMAGE, testStaff.getDamage());
        assertEquals(MAGIC_DAMAGE, testStaff.getMagicDamage());
        assertEquals(SPEED, testStaff.getWeight());
    }
}
