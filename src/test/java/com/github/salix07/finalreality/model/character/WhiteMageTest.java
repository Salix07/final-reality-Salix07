package com.github.salix07.finalreality.model.character;

import com.github.salix07.finalreality.model.character.player.BlackMage;
import com.github.salix07.finalreality.model.character.player.WhiteMage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

/**
 * Class containing the tests for the White Mages.
 *
 * @author Sebastián Salinas Rodriguez.
 */
public class WhiteMageTest extends AbstractPlayerCharacterTest{

    private static final String WhiteMAGE_NAME = "Lux";
    private static final int DEFENSE = 100;

    private WhiteMage testWhiteMage;

    /**
     * Start the set up for the test.
     */
    @BeforeEach
    void setUp() {
        basicSetUp();
        testWhiteMage = new WhiteMage(WhiteMAGE_NAME, 300, DEFENSE, 150, turns);
        testPlayerCharacters.add(testWhiteMage);
    }

    /**
     * Test that check the correct construction and equalities between White Mages.
     */
    @Test
    void contructorTest() {
        var expectedWhiteMage = new WhiteMage(WhiteMAGE_NAME, 300, DEFENSE, 150, turns);
        var anotherWhiteMage1 = new WhiteMage("Ahri", 300, DEFENSE, 150, turns);
        var anotherWhiteMage2 = new WhiteMage(WhiteMAGE_NAME, 250, DEFENSE, 150, turns);
        var anotherWhiteMage3 = new WhiteMage(WhiteMAGE_NAME, 300, 50, 150, turns);
        var anotherWhiteMage4 = new WhiteMage(WhiteMAGE_NAME, 300, DEFENSE, 120, turns);
        var expectedBlackMage = new BlackMage(WhiteMAGE_NAME, 300, DEFENSE, 150, turns);

        for (var character :
                testPlayerCharacters) {
            checkConstruction(expectedWhiteMage, character, anotherWhiteMage1, anotherWhiteMage2, anotherWhiteMage3, expectedBlackMage);
            assertEquals(anotherWhiteMage4, character);
        }
    }

    /**
     * Test for the getters.
     */
    @Test
    void gettersTest() {
        assertEquals(WhiteMAGE_NAME, testWhiteMage.getName());
        assertEquals(DEFENSE, testWhiteMage.getDefense());
        assertEquals(300, testWhiteMage.getHealthPoints());
        assertEquals(150, testWhiteMage.getMana());
    }

    /**
     * Test for the setters.
     */
    @Test
    void settersTest() {
        testWhiteMage.setMana(75);
        testWhiteMage.setHealthPoints(200);
        assertEquals(75, testWhiteMage.getMana());
        assertEquals(200, testWhiteMage.getHealthPoints());
    }

    /**
     * Check that this WhiteMage starts without weapon and can equip a staff
     */
    @Test
    void equipWeaponTest() {
        for (var character :
                testPlayerCharacters) {
            assertNull(character.getEquippedWeapon());
            character.equip(testBow);
            assertNull(character.getEquippedWeapon());
            character.equip(testStaff);
            assertEquals(testStaff, character.getEquippedWeapon());
        }
        // If the whiteMage is dead, we can´t equip a weapon
        Enemy bigBoss = new Enemy("Big Boss", 500,DEFENSE,400,10, turns);
        WhiteMage deadWhiteMage = new WhiteMage(WhiteMAGE_NAME, 300, DEFENSE, 150, turns);
        bigBoss.attack(deadWhiteMage);
        deadWhiteMage.equip(testStaff);
        assertNull(deadWhiteMage.getEquippedWeapon());
    }

    /**
     * Check that this WhiteMage's attack is 0 when no weapon is equipped
     * and when a weapon is equipped the attack is the weapon damage
     */
    @Test
    void getWeaponAttack() {
        for (var character :
                testPlayerCharacters) {
            assertEquals(0, character.getAttackDamage());
            character.equip(testBow);
            assertEquals(0, character.getAttackDamage());
            character.equip(testStaff);
            assertEquals(testStaff.getDamage(), character.getAttackDamage());
        }
    }

    @Test
    void toStringTest() {
        String expectedString = "WhiteMage";
        assertEquals(expectedString, testWhiteMage.toString());
    }
}
