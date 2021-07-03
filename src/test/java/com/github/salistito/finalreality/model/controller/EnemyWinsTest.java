package com.github.salistito.finalreality.model.controller;

import com.github.salistito.finalreality.controller.GameController;
import com.github.salistito.finalreality.model.character.Enemy;
import com.github.salistito.finalreality.model.character.player.IPlayerCharacter;
import com.github.salistito.finalreality.model.weapon.IWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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

    IWeapon voidStaff;
    IWeapon stormBreaker;

    ArrayList<Integer> alivePlayerCharactersIndex;

    /**
     * Basic set up for all the game test
     */
    @BeforeEach
    void setUp() {
        controller = new GameController(2, 2,2);

        controller.createEnemy("Goblin", 10, 5, 15, 10);
        controller.createEnemy("Troll", 10, 5, 15, 15);
        controller.createBlackMage("Morgana", 10, 5, 5);
        controller.createKnight("Jarvan", 10, 5);

        controller.createStaff("VoidStaff", 15, 15, 75);
        controller.createAxe("Storm Breaker", 15, 100);

        goblin = controller.getEnemy(0);
        troll = controller.getEnemy(1);

        morgana = controller.getPlayerCharacter(0);
        voidStaff = controller.selectWeaponFromInventory("VoidStaff");
        controller.tryToEquipPlayerCharacter(morgana, voidStaff);

        jarvan = controller.getPlayerCharacter(1);
        stormBreaker = controller.selectWeaponFromInventory("Storm Breaker");
        controller.tryToEquipPlayerCharacter(jarvan, stormBreaker);
    }

    /**
     * test for the game in which a enemy wins
     */
    @Test
    void enemyWinGame() throws InterruptedException {
        assertTrue(goblin.isAlive());
        assertTrue(troll.isAlive());
        assertTrue(jarvan.isAlive());
        assertTrue(morgana.isAlive());
        alivePlayerCharactersIndex = controller.getAlivePlayerCharactersIndex();
        assertEquals(2, alivePlayerCharactersIndex.size());
        assertEquals(Integer.valueOf(0), alivePlayerCharactersIndex.get(0));
        assertEquals(Integer.valueOf(1), alivePlayerCharactersIndex.get(1));
        assertTrue(controller.getCurrentPhase().isStartGamePhase());
        assertFalse(controller.getCurrentPhase().isPlayerSelectingActionPhase());
        assertFalse(controller.getCurrentPhase().isPlayerWinPhase());
        assertFalse(controller.getCurrentPhase().isEnemyWinPhase());
        assertEquals("Start Game Phase", controller.getCurrentPhaseName());
        assertNotEquals("Player Selecting Action Phase", controller.getCurrentPhaseName());
        assertNotEquals("Player Win Phase", controller.getCurrentPhaseName());
        assertNotEquals("Enemy Win Phase", controller.getCurrentPhaseName());
        assertEquals(controller.getCurrentPhaseName(), controller.getCurrentPhase().getName());
        assertFalse(controller.playerWin());
        assertFalse(controller.enemyWin());

        controller.tryToStartGame();
        Thread.sleep(5000);

        assertFalse(morgana.isAlive());
        assertFalse(jarvan.isAlive());
        assertTrue(goblin.isAlive());
        assertTrue(troll.isAlive());
        alivePlayerCharactersIndex = controller.getAlivePlayerCharactersIndex();
        assertEquals(0, alivePlayerCharactersIndex.size());
        assertFalse(controller.getCurrentPhase().isStartGamePhase());
        assertFalse(controller.getCurrentPhase().isPlayerSelectingActionPhase());
        assertFalse(controller.getCurrentPhase().isPlayerWinPhase());
        assertTrue(controller.getCurrentPhase().isEnemyWinPhase());
        assertNotEquals("Start Game Phase", controller.getCurrentPhaseName());
        assertNotEquals("Player Selecting Action Phase", controller.getCurrentPhaseName());
        assertNotEquals("Player Win Phase", controller.getCurrentPhaseName());
        assertEquals("Enemy Win Phase", controller.getCurrentPhaseName());
        assertEquals(controller.getCurrentPhaseName(), controller.getCurrentPhase().getName());
        assertFalse(controller.playerWin());
        assertTrue(controller.enemyWin());
    }
}
