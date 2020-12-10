package com.github.salix07.finalreality.model.controller;

import com.github.salix07.finalreality.controller.GameController;
import com.github.salix07.finalreality.model.character.Enemy;
import com.github.salix07.finalreality.model.character.ICharacter;
import com.github.salix07.finalreality.model.character.player.IPlayerCharacter;
import com.github.salix07.finalreality.model.weapon.IWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class containing the test for the game in which enemy wins
 *
 * @author Sebastián Salinas Rodriguez.
 *
 */
public class EnemyWinsTest {
    GameController controller;

    IPlayerCharacter morgana;
    IPlayerCharacter jarvan;
    Enemy goblin;
    Enemy troll;
    ICharacter turnCharacter;

    IWeapon voidStaff;
    IWeapon stormBreaker;

    /**
     * Basic set up for all the game test
     */
    @BeforeEach
    void setUp() {
        controller = new GameController(2, 2);

        controller.createEnemy("Goblin", 10, 5, 15, 5);
        controller.createEnemy("Troll", 10, 5, 15, 25);
        controller.createKnight("Jarvan", 10, 5);
        controller.createBlackMage("Morgana", 10, 5, 5);

        controller.createAxe("Storm Breaker", 15, 15);
        controller.createStaff("VoidStaff", 15, 15, 35);

        goblin = controller.getEnemy(0);
        troll = controller.getEnemy(1);

        jarvan = controller.getPlayerCharacter(0);
        stormBreaker = controller.selectWeaponFromInventory("Storm Breaker");
        controller.equipPlayerCharacter(jarvan, stormBreaker);

        morgana = controller.getPlayerCharacter(1);
        voidStaff = controller.selectWeaponFromInventory("VoidStaff");
        controller.equipPlayerCharacter(morgana, voidStaff);
    }

    /**
     * test to check the right order of turns
     */
    @Test
    void rightOrderTurns() throws InterruptedException {

        turnCharacter = controller.getActiveICharacter();
        assertNull(turnCharacter);

        controller.beginGame();
        Thread.sleep(5000);

        controller.beginTurn();
        turnCharacter = controller.getActiveICharacter();
        assertEquals(goblin, turnCharacter);
        controller.endTurn(turnCharacter);

        controller.beginTurn();
        turnCharacter = controller.getActiveICharacter();
        assertEquals(jarvan, turnCharacter);
        controller.endTurn(turnCharacter);

        controller.beginTurn();
        turnCharacter = controller.getActiveICharacter();
        assertEquals(troll, turnCharacter);
        controller.endTurn(turnCharacter);

        controller.beginTurn();
        turnCharacter = controller.getActiveICharacter();
        assertEquals(morgana, turnCharacter);
        controller.endTurn(turnCharacter);

        controller.beginTurn();
        turnCharacter = controller.getActiveICharacter();
        assertEquals(goblin, turnCharacter);
    }

    /**
     * test for the game in which a enemy wins
     */
    @Test
    void enemyWinGame() throws InterruptedException {

        controller.beginGame();
        Thread.sleep(3000);

        assertTrue(goblin.isAlive());
        assertTrue(troll.isAlive());
        assertTrue(jarvan.isAlive());
        assertTrue(morgana.isAlive());
        assertFalse(controller.playerWin());
        assertFalse(controller.enemyWin());

        controller.beginTurn();
        turnCharacter = controller.getActiveICharacter();
        assertEquals(goblin, turnCharacter);
        assertEquals(10, controller.getHealthPointsFrom(morgana));
        controller.attackCharacter(turnCharacter, morgana);
        assertEquals(0, controller.getHealthPointsFrom(morgana));

        assertTrue(goblin.isAlive());
        assertTrue(troll.isAlive());
        assertTrue(jarvan.isAlive());
        assertFalse(morgana.isAlive());
        assertFalse(controller.playerWin());
        assertFalse(controller.enemyWin());

        controller.beginTurn();
        turnCharacter = controller.getActiveICharacter();
        assertEquals(jarvan, turnCharacter);
        assertEquals(10, controller.getHealthPointsFrom(goblin));
        controller.attackCharacter(turnCharacter, goblin);
        assertEquals(0, controller.getHealthPointsFrom(goblin));

        assertFalse(goblin.isAlive());
        assertTrue(troll.isAlive());
        assertTrue(jarvan.isAlive());
        assertFalse(morgana.isAlive());
        assertFalse(controller.playerWin());
        assertFalse(controller.enemyWin());

        controller.beginTurn();
        turnCharacter = controller.getActiveICharacter();
        assertEquals(troll, turnCharacter);
        assertEquals(10, controller.getHealthPointsFrom(jarvan));
        controller.attackCharacter(turnCharacter, jarvan);
        assertEquals(0, controller.getHealthPointsFrom(jarvan));

        assertFalse(goblin.isAlive());
        assertTrue(troll.isAlive());
        assertFalse(jarvan.isAlive());
        assertFalse(morgana.isAlive());
        assertFalse(controller.playerWin());
        assertTrue(controller.enemyWin());
    }
}
