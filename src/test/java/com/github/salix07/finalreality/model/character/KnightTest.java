package com.github.salix07.finalreality.model.character;

import com.github.salix07.finalreality.model.character.player.Knight;
import com.github.salix07.finalreality.model.character.player.Thief;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

/**
 * Class containing the tests for the Knights.
 *
 * @author Sebastián Salinas Rodriguez.
 */
public class KnightTest extends AbstractPlayerCharacterTest{

    private static final String KNIGHT_NAME = "Jarvan";
    private static final int DEFENSE = 100;

    private Knight testKnight;

    /**
     * Start the set up for the test.
     */
    @BeforeEach
    void setUp() {
        basicSetUp();
        testKnight = new Knight(KNIGHT_NAME, 300, DEFENSE, turns);
        testPlayerCharacters.add(testKnight);
    }

    /**
     * Test that check the correct construction and equalities between Knights.
     */
    @Test
    void contructorTest() {
        var expectedKnight = new Knight(KNIGHT_NAME, 300, DEFENSE, turns);
        var anotherKnight1 = new Knight("Darius", 300, DEFENSE, turns);
        var anotherKnight2 = new Knight(KNIGHT_NAME, 250, DEFENSE, turns);
        var anotherKnight3 = new Knight(KNIGHT_NAME, 300, 50, turns);
        var expectedThief = new Thief(KNIGHT_NAME, 300, DEFENSE, turns);

        for (var character :
                testPlayerCharacters) {
            checkConstruction(expectedKnight, character, anotherKnight1, anotherKnight2, anotherKnight3, expectedThief);
        }
    }

    /**
     * Test for the getters.
     */
    @Test
    void gettersTest() {
        assertEquals(KNIGHT_NAME, testKnight.getName());
        assertEquals(DEFENSE, testKnight.getDefense());
        assertEquals(300, testKnight.getHealthPoints());
    }

    /**
     * Test for the setters.
     */
    @Test
    void settersTest() {
        testKnight.setHealthPoints(200);
        assertEquals(200, testKnight.getHealthPoints());
    }

    /**
     * Check that this Knight starts without weapon and can equip an axe, a knife and a sword
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
            character.equip(testKnife);
            assertEquals(testKnife, character.getEquippedWeapon());
            character.equip(testSword);
            assertEquals(testSword, character.getEquippedWeapon());
        }
        // If the knight is dead, we can´t equip a weapon
        Enemy bigBoss = new Enemy("Big Boss", 500,DEFENSE,400,10, turns);
        Knight deadKnight = new Knight(KNIGHT_NAME, 300, DEFENSE, turns);
        bigBoss.attack(deadKnight);
        deadKnight.equip(testSword);
        assertNull(deadKnight.getEquippedWeapon());
    }

    /**
     * Check that this Knight's attack is 0 when no weapon is equipped
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
            character.equip(testKnife);
            assertEquals(testKnife.getDamage(), character.getAttackDamage());
            character.equip(testSword);
            assertEquals(testSword.getDamage(), character.getAttackDamage());
        }
    }
}
