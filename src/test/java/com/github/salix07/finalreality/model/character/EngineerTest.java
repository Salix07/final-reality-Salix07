package com.github.salix07.finalreality.model.character;

import com.github.salix07.finalreality.model.character.player.Engineer;
import com.github.salix07.finalreality.model.character.player.Knight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

/**
 * Class containing the tests for the Engineers.
 *
 * @author Sebastián Salinas Rodriguez.
 */
public class EngineerTest extends AbstractPlayerCharacterTest{

    private static final String ENGINEER_NAME = "Feto Ingeniero";
    private static final int DEFENSE = 100;

    private Engineer testEngineer;

    /**
     * Start the set up for the test.
     */
    @BeforeEach
    void setUp() {
        basicSetUp();
        testEngineer = new Engineer(ENGINEER_NAME, 300, DEFENSE, turns);
        testPlayerCharacters.add(testEngineer);
    }

    /**
     * Test that check the correct construction and equalities between Engineers.
     */
    @Test
    void contructorTest() {
        var expectedEngineer = new Engineer(ENGINEER_NAME, 300, DEFENSE, turns);
        var anotherEngineer1 = new Engineer("Yop", 300, DEFENSE, turns);
        var anotherEngineer2 = new Engineer(ENGINEER_NAME, 250, DEFENSE, turns);
        var anotherEngineer3 = new Engineer(ENGINEER_NAME, 300, 50, turns);
        var expectedKnight = new Knight(ENGINEER_NAME, 300, DEFENSE, turns);

        for (var character :
                testPlayerCharacters) {
            checkConstruction(expectedEngineer, character, anotherEngineer1, anotherEngineer2, anotherEngineer3, expectedKnight);
        }
    }

    /**
     * Test for the getters.
     */
    @Test
    void gettersTest() {
        assertEquals(ENGINEER_NAME, testEngineer.getName());
        assertEquals(DEFENSE, testEngineer.getDefense());
        assertEquals(300, testEngineer.getHealthPoints());
    }

    /**
     * Test for the setters.
     */
    @Test
    void settersTest() {
        testEngineer.setHealthPoints(200);
        assertEquals(200, testEngineer.getHealthPoints());
    }

    /**
     * Check that this Engineer starts without weapon and can equip an axe, and a bow
     */
    @Test
    void equipWeaponTest() {
        for (var character :
                testPlayerCharacters) {
            assertNull(character.getEquippedWeapon());
            character.equip(testStaff);
            assertNull(character.getEquippedWeapon());
            character.equip(testAxe);
            assertEquals(testAxe, character.getEquippedWeapon());
            character.equip(testBow);
            assertEquals(testBow, character.getEquippedWeapon());
        }
    }

    /**
     * Check that this Engineer's attack is 0 when no weapon is equipped
     * and when a weapon is equipped the attack is the weapon damage
     */
    @Test
    void getWeaponAttack() {
        for (var character :
                testPlayerCharacters) {
            assertEquals(0, character.getAttackDamage());
            character.equip(testStaff);
            assertEquals(0, character.getAttackDamage());
            character.equip(testAxe);
            assertEquals(testAxe.getDamage(), character.getAttackDamage());
            character.equip(testBow);
            assertEquals(testBow.getDamage(), character.getAttackDamage());
        }
    }
}
