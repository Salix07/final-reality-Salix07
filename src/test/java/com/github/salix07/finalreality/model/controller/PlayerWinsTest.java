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
 * Class containing the test for the game in which player wins
 *
 * @author Sebastián Salinas Rodriguez.
 *
 */
public class PlayerWinsTest {
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

        controller.createBlackMage("Morgana", 10, 5, 5);
        controller.createKnight("Jarvan", 10, 5);
        controller.createEnemy("Goblin", 10, 5, 15, 10);
        controller.createEnemy("Troll", 10, 5, 15, 20);

        controller.createStaff("VoidStaff", 15, 15, 5);
        controller.createAxe("Storm Breaker", 15, 15);

        morgana = controller.getPlayerCharacter(0);
        voidStaff = controller.selectWeaponFromInventory("VoidStaff");
        controller.equipPlayerCharacter(morgana, voidStaff);

        jarvan = controller.getPlayerCharacter(1);
        stormBreaker = controller.selectWeaponFromInventory("Storm Breaker");
        controller.equipPlayerCharacter(jarvan, stormBreaker);

        goblin = controller.getEnemy(0);
        troll = controller.getEnemy(1);
    }

    /**
     * test to check the right order of turns
     */
    /*
    @Test
    void rightOrderTurns() throws InterruptedException {

        turnCharacter = controller.getActiveICharacter();
        assertNull(turnCharacter);

        controller.beginGame();
        Thread.sleep(5000);

        turnCharacter = controller.getActiveICharacter();
        assertEquals(morgana, turnCharacter);
        controller.endTurn(turnCharacter);

        turnCharacter = controller.getActiveICharacter();
        assertEquals(goblin, turnCharacter);
        controller.endTurn(turnCharacter);

        turnCharacter = controller.getActiveICharacter();
        assertEquals(jarvan, turnCharacter);
        controller.endTurn(turnCharacter);

        turnCharacter = controller.getActiveICharacter();
        assertEquals(troll, turnCharacter);
        controller.endTurn(turnCharacter);

        turnCharacter = controller.getActiveICharacter();
        assertEquals(morgana, turnCharacter);
    }
    */

    /**
     * test for the game in which a player wins
     */
    @Test
    void playerWinGame() throws InterruptedException {

        assertTrue(morgana.isAlive());
        assertTrue(jarvan.isAlive());
        assertTrue(goblin.isAlive());
        assertTrue(troll.isAlive());
        assertFalse(controller.playerWin());
        assertFalse(controller.enemyWin());

        controller.beginGame();
        Thread.sleep(5000);

        assertFalse(goblin.isAlive());
        assertFalse(troll.isAlive());
        assertTrue(controller.playerWin());
        assertFalse(controller.enemyWin());
    }
}