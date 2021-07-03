package com.github.salistito.finalreality.model.weapon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class containing the tests for the Staffs.
 *
 * @author Sebastián Salinas Rodriguez.
 */
public class StaffTest {

    private static final String STAFF_NAME = "Test Staff";
    private static final int DAMAGE = 15;
    private static final int MAGIC_DAMAGE = 20;
    private static final int SPEED = 10;

    private Staff testStaff;

    /**
     * Start the set up for the test.
     */
    @BeforeEach
    void setUp() {
        testStaff = new Staff(STAFF_NAME, DAMAGE, MAGIC_DAMAGE, SPEED);
    }

    /**
     * Test that check the correct construction and equalities between Staffs.
     */
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

        assertEquals(testStaff, testStaff);
        assertEquals(testStaff.hashCode(), testStaff.hashCode());

        assertEquals(expectedStaff, testStaff);
        assertNotEquals(anotherStaff1, testStaff);
        assertNotEquals(anotherStaff2, testStaff);
        assertNotEquals(anotherStaff3, testStaff);
        assertNotEquals(anotherStaff4, testStaff);
        assertNotEquals(expectedSword, testStaff);
    }

    /**
     * Test for the getters.
     */
    @Test
    void gettersTest() {
        assertEquals(STAFF_NAME, testStaff.getName());
        assertEquals(DAMAGE, testStaff.getDamage());
        assertEquals(MAGIC_DAMAGE, testStaff.getMagicDamage());
        assertEquals(SPEED, testStaff.getWeight());
    }

    @Test
    void toStringTest() {
        String expectedString = "Staff";
        assertEquals(expectedString, testStaff.toString());
    }
}
