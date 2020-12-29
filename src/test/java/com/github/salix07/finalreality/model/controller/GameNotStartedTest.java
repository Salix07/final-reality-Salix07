package com.github.salix07.finalreality.model.controller;

import com.github.salix07.finalreality.controller.GameController;
import com.github.salix07.finalreality.model.character.Enemy;
import com.github.salix07.finalreality.model.character.ICharacter;
import com.github.salix07.finalreality.model.character.player.IPlayerCharacter;
import com.github.salix07.finalreality.model.weapon.IWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameNotStartedTest {
    GameController controller;

    IPlayerCharacter morgana;
    IPlayerCharacter jarvan;
    Enemy goblin;
    Enemy troll;
    ICharacter turnCharacter;

    IWeapon voidStaff;
    IWeapon stormBreaker;
    IWeapon infinityEdge;

    /**
     * Basic set up for all the game test
     */
    @BeforeEach
    void setUp() {
        controller = new GameController(2, 2,3);

        controller.createBlackMage("Morgana", 10, 5, 5);
        controller.createEnemy("Goblin", 10, 5, 15, 75);
        controller.createEnemy("Troll", 10, 5, 15, 100);

        controller.createStaff("VoidStaff", 15, 15, 10);

        morgana = controller.getPlayerCharacter(0);
        voidStaff = controller.selectWeaponFromInventory("VoidStaff");
        controller.tryToEquipPlayerCharacter(morgana, voidStaff);

        goblin = controller.getEnemy(0);
        troll = controller.getEnemy(1);
    }

    @Test
    void gameNotStartedYet() throws InterruptedException {
        // Try to start the game with less playerCharacters than the value passed at the controller
        // In this case the game must not start so the activeICharacter
        // must be null and the phase mustn't change
        assertTrue(controller.getCurrentPhase().isStartGamePhase());
        assertEquals("Start Game Phase", controller.getCurrentPhaseName());
        assertEquals(controller.getCurrentPhaseName(), controller.getCurrentPhase().getName());

        controller.tryToStartGame();

        assertTrue(controller.getCurrentPhase().isStartGamePhase());
        assertEquals("Start Game Phase", controller.getCurrentPhaseName());
        assertEquals(controller.getCurrentPhaseName(), controller.getCurrentPhase().getName());

        turnCharacter = controller.getActiveICharacter();
        assertNull(turnCharacter);

        // Create the missing character
        controller.createKnight("Jarvan", 10, 5);
        controller.createAxe("Storm Breaker", 15, 15);
        controller.createSword("Infinity Edge",100,15);
        jarvan = controller.getPlayerCharacter(1);
        stormBreaker = controller.selectWeaponFromInventory("Storm Breaker");
        infinityEdge = controller.selectWeaponFromInventory("Infinity Edge");
        controller.tryToEquipPlayerCharacter(jarvan, stormBreaker);

        // The game now can start
        controller.tryToStartGame();
        Thread.sleep(5000);

        assertFalse(controller.getCurrentPhase().isStartGamePhase());
        assertTrue(controller.getCurrentPhase().isPlayerSelectingActionPhase());
        assertEquals("Player Selecting Action Phase", controller.getCurrentPhaseName());
        assertEquals(controller.getCurrentPhaseName(), controller.getCurrentPhase().getName());

        controller.tryToAttackCharacter(morgana, goblin);

        /*
        assertFalse(controller.getCurrentPhase().isStartGamePhase());
        assertTrue(controller.getCurrentPhase().isWaitingForTurnPhase());
        assertFalse(controller.getCurrentPhase().isTurnPhase());
        assertFalse(controller.getCurrentPhase().isPlayerSelectingActionPhase());
        assertFalse(controller.getCurrentPhase().isEnemyActionPhase());
        assertFalse(controller.getCurrentPhase().isPlayerWinPhase());
        assertFalse(controller.getCurrentPhase().isEnemyWinPhase());

        assertEquals("Waiting For Turn Phase", controller.getCurrentPhaseName());
        assertEquals(controller.getCurrentPhaseName(), controller.getCurrentPhase().getName());

        assertFalse(controller.getCurrentPhase().isStartGamePhase());
        assertFalse(controller.getCurrentPhase().isWaitingForTurnPhase());
        assertTrue(controller.getCurrentPhase().isTurnPhase());
        assertFalse(controller.getCurrentPhase().isPlayerSelectingActionPhase());
        assertFalse(controller.getCurrentPhase().isEnemyActionPhase());
        assertFalse(controller.getCurrentPhase().isPlayerWinPhase());
        assertFalse(controller.getCurrentPhase().isEnemyWinPhase());

        assertEquals("Turn Phase", controller.getCurrentPhaseName());
        assertEquals(controller.getCurrentPhaseName(), controller.getCurrentPhase().getName());

         */

        assertTrue(controller.getCurrentPhase().isPlayerSelectingActionPhase());
        assertEquals("Player Selecting Action Phase", controller.getCurrentPhaseName());
        assertEquals(controller.getCurrentPhaseName(), controller.getCurrentPhase().getName());

        controller.tryToEquipPlayerCharacter(jarvan, infinityEdge);

        assertTrue(controller.getCurrentPhase().isPlayerSelectingActionPhase());
        assertEquals("Player Selecting Action Phase", controller.getCurrentPhaseName());
        assertEquals(controller.getCurrentPhaseName(), controller.getCurrentPhase().getName());

        controller.tryToAttackCharacter(jarvan, troll);

        assertTrue(controller.getCurrentPhase().isPlayerWinPhase());
        assertEquals("Player Win Phase", controller.getCurrentPhaseName());
        assertEquals(controller.getCurrentPhaseName(), controller.getCurrentPhase().getName());
    }
}
