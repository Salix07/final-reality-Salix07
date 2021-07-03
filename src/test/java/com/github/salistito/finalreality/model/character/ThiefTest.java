package com.github.salistito.finalreality.model.character;

import com.github.salistito.finalreality.model.character.player.Thief;
import com.github.salistito.finalreality.model.character.player.WhiteMage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

/**
 * Class containing the tests for the Thiefs.
 *
 * @author Sebastián Salinas Rodriguez.
 */
public class ThiefTest extends AbstractPlayerCharacterTest{

    private static final String THIEF_NAME = "José Piñera Echeñique";
    private static final int DEFENSE = 100;

    private Thief testThief;

    /**
     * Start the set up for the test.
     */
    @BeforeEach
    void setUp() {
        basicSetUp();
        testThief = new Thief(THIEF_NAME, 300, DEFENSE, turns);
        testPlayerCharacters.add(testThief);
    }

    /**
     * Test that check the correct construction and equalities between Thiefs.
     */
    @Test
    void contructorTest() {
        var expectedThief = new Thief(THIEF_NAME, 300, DEFENSE, turns);
        var anotherThief1 = new Thief("Sebastán Piñera Echeñique", 300, DEFENSE, turns);
        var anotherThief2 = new Thief(THIEF_NAME, 250, DEFENSE, turns);
        var anotherThief3 = new Thief(THIEF_NAME, 300, 50, turns);
        var expectedWhiteMage = new WhiteMage(THIEF_NAME, 300, DEFENSE, 100, turns);

        for (var character :
                testPlayerCharacters) {
            checkConstruction(expectedThief, character, anotherThief1, anotherThief2, anotherThief3, expectedWhiteMage);
        }
    }

    /**
     * Test for the getters.
     */
    @Test
    void gettersTest() {
        assertEquals(THIEF_NAME, testThief.getName());
        assertEquals(DEFENSE, testThief.getDefense());
        assertEquals(300, testThief.getHealthPoints());
    }

    /**
     * Test for the setters.
     */
    @Test
    void settersTest() {
        testThief.setHealthPoints(200);
        assertEquals(200, testThief.getHealthPoints());
    }

    /**
     * Check that this Thief starts without weapon and can equip a bow, a knife and a sword
     */
    @Test
    void equipWeaponTest() {
        for (var character :
                testPlayerCharacters) {
            assertNull(character.getEquippedWeapon());
            character.equip(testStaff);
            assertNull(character.getEquippedWeapon());
            character.equip(testBow);
            assertEquals(testBow, character.getEquippedWeapon());
            character.equip(testKnife);
            assertEquals(testKnife, character.getEquippedWeapon());
            character.equip(testSword);
            assertEquals(testSword, character.getEquippedWeapon());
        }
        // If the thief is dead, we can´t equip a weapon
        Enemy bigBoss = new Enemy("Big Boss", 500,DEFENSE,400,10, turns);
        Thief deadThief = new Thief(THIEF_NAME, 300, DEFENSE, turns);
        bigBoss.attack(deadThief);
        deadThief.equip(testKnife);
        assertNull(deadThief.getEquippedWeapon());
    }

    /**
     * Check that this Thief's attack is 0 when no weapon is equipped
     * and when a weapon is equipped the attack is the weapon damage
     */
    @Test
    void getWeaponAttack() {
        for (var character :
                testPlayerCharacters) {
            assertEquals(0, character.getAttackDamage());
            character.equip(testStaff);
            assertEquals(0, character.getAttackDamage());
            character.equip(testBow);
            assertEquals(testBow.getDamage(), character.getAttackDamage());
            character.equip(testKnife);
            assertEquals(testKnife.getDamage(), character.getAttackDamage());
            character.equip(testSword);
            assertEquals(testSword.getDamage(), character.getAttackDamage());
        }
    }
    @Test
    void toStringTest() {
        String expectedString = "Thief";
        assertEquals(expectedString, testThief.toString());
    }
}
