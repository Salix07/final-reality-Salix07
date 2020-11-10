package com.github.salix07.finalreality.model.character;

import com.github.salix07.finalreality.model.character.player.*;
import com.github.salix07.finalreality.model.weapon.Staff;
import com.github.salix07.finalreality.model.weapon.Sword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Abstract class containing the attack testing for all the types of characters.
 *
 * @author Sebastián Salinas Rodriguez.
 */
public class AbstractCharacterCombatTest {

    protected BlockingQueue<ICharacter> turns;

    private static final String DEAD_BLACKMAGE_NAME = "Morgana";
    private static final String KNIGHT_NAME = "Jarvan";
    private static final int DEFENSE = 100;

    private static final String TANKY_ENEMY_NAME = "Tanky Goblin";
    private static final String NORMAL_ENEMY_NAME = "Normal Goblin";
    private static final String WEAK_ENEMY_NAME = "Weak Goblin";
    private static final String DEAD_ENEMY_NAME = "Dead Goblin";
    private static final int TANKY_ENEMY_DEFENSE = 50;
    private static final int ENEMY_DEFENSE = 5;
    private static final int ENEMY_DAMAGE = 10;
    private static final int ENEMY_WEIGHT = 10;


    private BlackMage testDeadBlackMage;
    private Knight testKnight;

    private Enemy testTankyEnemy;
    private Enemy testNormalEnemy;
    private Enemy testWeakEnemy;
    private Enemy testDeadEnemy;

    /**
     * Start the set up for the test.
     */
    @BeforeEach
    void setUp() {
        testDeadBlackMage = new BlackMage(DEAD_BLACKMAGE_NAME, 0, DEFENSE, 150, turns);
        testKnight = new Knight(KNIGHT_NAME, 300, DEFENSE, turns);

        testTankyEnemy = new Enemy(TANKY_ENEMY_NAME, 300, TANKY_ENEMY_DEFENSE, ENEMY_DAMAGE, ENEMY_WEIGHT, turns);
        testNormalEnemy = new Enemy(NORMAL_ENEMY_NAME, 100, ENEMY_DEFENSE, ENEMY_DAMAGE, ENEMY_WEIGHT, turns);
        testWeakEnemy = new Enemy(WEAK_ENEMY_NAME, 5, ENEMY_DEFENSE, ENEMY_DAMAGE, ENEMY_WEIGHT, turns);
        testDeadEnemy = new Enemy(DEAD_ENEMY_NAME, 0, ENEMY_DEFENSE, ENEMY_DAMAGE, ENEMY_WEIGHT, turns);

        Staff testStaff = new Staff("testStaff", 5, 10, 7);
        Sword testSword = new Sword("TestSword", 15, 10);
        testDeadBlackMage.equip(testStaff);
        testKnight.equip(testSword);

        testDeadBlackMage.isAlive = false;
        testDeadEnemy.isAlive = false;
    }

    /**
     * Checks that the combat (attacks) between the characters is correct
     */
    @Test
    void Combat() {
        // These characters start the combat alive
        assertTrue(testKnight.isAlive());
        assertTrue(testTankyEnemy.isAlive());
        assertTrue(testNormalEnemy.isAlive());
        assertTrue(testWeakEnemy.isAlive());
        // These characters start the combat dead
        assertFalse(testDeadBlackMage.isAlive());
        assertFalse(testDeadEnemy.isAlive());

        // The knight attacks an enemy with a lot of defense
        testKnight.attack(testTankyEnemy);
        // The enemy with a lot of defense doesn't get damaged and still alive
        assertEquals(300, testTankyEnemy.getHealthPoints());
        assertTrue(testTankyEnemy.isAlive());

        // The knight attacks a normal enemy
        testKnight.attack(testNormalEnemy);
        // The normal enemy get damaged and still alive
        assertEquals(90, testNormalEnemy.getHealthPoints());
        assertTrue(testNormalEnemy.isAlive());

        // The knight attacks a weak enemy (low health points)
        testKnight.attack(testWeakEnemy);
        // The weak enemy get damaged and die
        assertEquals(-5, testWeakEnemy.getHealthPoints());
        assertFalse(testWeakEnemy.isAlive());

        // The knight attacks a dead enemy
        testKnight.attack(testDeadEnemy);
        // the method does nothing
        assertEquals(0, testDeadEnemy.getHealthPoints());
        assertFalse(testWeakEnemy.isAlive());

        // The dead black mage attacks an arbitrary alive enemy
        testDeadBlackMage.attack(testTankyEnemy);
        // the method does nothing
        assertEquals(300, testTankyEnemy.getHealthPoints());
        assertTrue(testTankyEnemy.isAlive());

        // The dead black mage attacks a dead enemy
        testDeadBlackMage.attack(testDeadEnemy);
        // the method does nothing
        assertEquals(0, testDeadEnemy.getHealthPoints());
        assertFalse(testDeadEnemy.isAlive());
    }


}
