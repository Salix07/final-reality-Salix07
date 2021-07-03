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

    IWeapon voidStaff;
    IWeapon stormBreaker;
    IWeapon infinityEdge;

    ArrayList<Integer> aliveEnemiesIndex;

    /**
     * Basic set up for all the game test
     */
    @BeforeEach
    void setUp() {
        controller = new GameController(2, 2,3);

        controller.createBlackMage("Morgana", 10, 5, 5);
        controller.createKnight("Jarvan", 10, 5);
        controller.createEnemy("Goblin", 10, 5, 15, 75);
        controller.createEnemy("Troll", 10, 5, 15, 100);

        controller.createStaff("VoidStaff", 15, 15, 10);
        controller.createAxe("Storm Breaker", 15, 15);
        controller.createSword("Infinity Edge",100,15);

        morgana = controller.getPlayerCharacter(0);
        voidStaff = controller.selectWeaponFromInventory("VoidStaff");
        controller.tryToEquipPlayerCharacter(morgana, voidStaff);

        jarvan = controller.getPlayerCharacter(1);
        stormBreaker = controller.selectWeaponFromInventory("Storm Breaker");
        controller.tryToEquipPlayerCharacter(jarvan, stormBreaker);

        infinityEdge = controller.selectWeaponFromInventory("Infinity Edge");

        goblin = controller.getEnemy(0);
        troll = controller.getEnemy(1);
    }

    /**
     * test for the game in which a player wins
     */
    @Test
    void playerWinGame() throws InterruptedException {
        assertTrue(morgana.isAlive());
        assertTrue(jarvan.isAlive());
        assertTrue(goblin.isAlive());
        assertTrue(troll.isAlive());
        aliveEnemiesIndex = controller.getAliveEnemiesIndex();
        assertEquals(2, aliveEnemiesIndex.size());
        assertEquals(Integer.valueOf(0), aliveEnemiesIndex.get(0));
        assertEquals(Integer.valueOf(1), aliveEnemiesIndex.get(1));
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

        assertTrue(morgana.isAlive());
        assertTrue(jarvan.isAlive());
        assertTrue(goblin.isAlive());
        assertTrue(troll.isAlive());
        aliveEnemiesIndex = controller.getAliveEnemiesIndex();
        assertEquals(2, aliveEnemiesIndex.size());
        assertEquals(Integer.valueOf(0), aliveEnemiesIndex.get(0));
        assertEquals(Integer.valueOf(1), aliveEnemiesIndex.get(1));
        assertFalse(controller.getCurrentPhase().isStartGamePhase());
        assertTrue(controller.getCurrentPhase().isPlayerSelectingActionPhase());
        assertFalse(controller.getCurrentPhase().isPlayerWinPhase());
        assertFalse(controller.getCurrentPhase().isEnemyWinPhase());
        assertNotEquals("Start Game Phase", controller.getCurrentPhaseName());
        assertEquals("Player Selecting Action Phase", controller.getCurrentPhaseName());
        assertNotEquals("Player Win Phase", controller.getCurrentPhaseName());
        assertNotEquals("Enemy Win Phase", controller.getCurrentPhaseName());
        assertEquals(controller.getCurrentPhaseName(), controller.getCurrentPhase().getName());
        assertFalse(controller.playerWin());
        assertFalse(controller.enemyWin());

        controller.tryToAttackCharacter(morgana, goblin);

        assertTrue(morgana.isAlive());
        assertTrue(jarvan.isAlive());
        assertFalse(goblin.isAlive());
        assertTrue(troll.isAlive());
        aliveEnemiesIndex = controller.getAliveEnemiesIndex();
        assertEquals(1, aliveEnemiesIndex.size());
        assertEquals(Integer.valueOf(1), aliveEnemiesIndex.get(0));
        assertFalse(controller.getCurrentPhase().isStartGamePhase());
        assertTrue(controller.getCurrentPhase().isPlayerSelectingActionPhase());
        assertFalse(controller.getCurrentPhase().isPlayerWinPhase());
        assertFalse(controller.getCurrentPhase().isEnemyWinPhase());
        assertNotEquals("Start Game Phase", controller.getCurrentPhaseName());
        assertEquals("Player Selecting Action Phase", controller.getCurrentPhaseName());
        assertNotEquals("Player Win Phase", controller.getCurrentPhaseName());
        assertNotEquals("Enemy Win Phase", controller.getCurrentPhaseName());
        assertEquals(controller.getCurrentPhaseName(), controller.getCurrentPhase().getName());
        assertFalse(controller.playerWin());
        assertFalse(controller.enemyWin());

        controller.tryToEquipPlayerCharacter(jarvan, infinityEdge);

        assertTrue(morgana.isAlive());
        assertTrue(jarvan.isAlive());
        assertFalse(goblin.isAlive());
        assertTrue(troll.isAlive());
        aliveEnemiesIndex = controller.getAliveEnemiesIndex();
        assertEquals(1, aliveEnemiesIndex.size());
        assertEquals(Integer.valueOf(1), aliveEnemiesIndex.get(0));
        assertFalse(controller.getCurrentPhase().isStartGamePhase());
        assertTrue(controller.getCurrentPhase().isPlayerSelectingActionPhase());
        assertFalse(controller.getCurrentPhase().isPlayerWinPhase());
        assertFalse(controller.getCurrentPhase().isEnemyWinPhase());
        assertNotEquals("Start Game Phase", controller.getCurrentPhaseName());
        assertEquals("Player Selecting Action Phase", controller.getCurrentPhaseName());
        assertNotEquals("Player Win Phase", controller.getCurrentPhaseName());
        assertNotEquals("Enemy Win Phase", controller.getCurrentPhaseName());
        assertEquals(controller.getCurrentPhaseName(), controller.getCurrentPhase().getName());
        assertFalse(controller.playerWin());
        assertFalse(controller.enemyWin());

        controller.tryToAttackCharacter(jarvan, troll);

        assertTrue(morgana.isAlive());
        assertTrue(jarvan.isAlive());
        assertFalse(goblin.isAlive());
        assertFalse(troll.isAlive());
        aliveEnemiesIndex = controller.getAliveEnemiesIndex();
        assertEquals(0, aliveEnemiesIndex.size());
        assertFalse(controller.getCurrentPhase().isStartGamePhase());
        assertFalse(controller.getCurrentPhase().isPlayerSelectingActionPhase());
        assertTrue(controller.getCurrentPhase().isPlayerWinPhase());
        assertFalse(controller.getCurrentPhase().isEnemyWinPhase());
        assertNotEquals("Start Game Phase", controller.getCurrentPhaseName());
        assertNotEquals("Player Selecting Action Phase", controller.getCurrentPhaseName());
        assertEquals("Player Win Phase", controller.getCurrentPhaseName());
        assertNotEquals("Enemy Win Phase", controller.getCurrentPhaseName());
        assertEquals(controller.getCurrentPhaseName(), controller.getCurrentPhase().getName());
        assertTrue(controller.playerWin());
        assertFalse(controller.enemyWin());
    }
}
