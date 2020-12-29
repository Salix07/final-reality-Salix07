package com.github.salix07.finalreality.model.controller;

import com.github.salix07.finalreality.controller.GameController;
import com.github.salix07.finalreality.model.character.Enemy;
import com.github.salix07.finalreality.model.character.ICharacter;
import com.github.salix07.finalreality.model.character.player.IPlayerCharacter;
import com.github.salix07.finalreality.model.weapon.IWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WrongPhaseTest {
    GameController controller;

    IPlayerCharacter jarvan;
    Enemy troll;
    ICharacter turnCharacter;

    IWeapon stormBreaker;
    IWeapon infinityEdge;
    IWeapon nullWeapon;

    /**
     * Basic set up
     */
    @BeforeEach
    void setUp() {
        controller = new GameController(1, 1,10);

        controller.createKnight("Jarvan", 10, 5);
        controller.createEnemy("Troll", 10, 5, 15, 20);

        controller.createAxe("Storm Breaker", 15, 5);
        controller.createSword("Infinity Edge", 15,5);

        jarvan = controller.getPlayerCharacter(0);
        stormBreaker = controller.selectWeaponFromInventory("Storm Breaker");
        controller.tryToEquipPlayerCharacter(jarvan, stormBreaker);
        infinityEdge = controller.selectWeaponFromInventory("Infinity Edge");

        troll = controller.getEnemy(0);
    }

    /**
     * testing methods on wrong phases
     */
    @Test
    void wrongPhaseTest() throws InterruptedException {
        // The controller start at StartGamePhase
        assertTrue(controller.getCurrentPhase().isStartGamePhase());
        assertFalse(controller.getCurrentPhase().isWaitingForTurnPhase());
        assertFalse(controller.getCurrentPhase().isTurnPhase());
        assertFalse(controller.getCurrentPhase().isPlayerSelectingActionPhase());
        assertFalse(controller.getCurrentPhase().isEnemyActionPhase());
        assertFalse(controller.getCurrentPhase().isPlayerWinPhase());
        assertFalse(controller.getCurrentPhase().isEnemyWinPhase());

        assertEquals("Start Game Phase", controller.getCurrentPhaseName());
        assertNotEquals("Waiting For Turn Phase", controller.getCurrentPhaseName());
        assertNotEquals("Turn Phase", controller.getCurrentPhaseName());
        assertNotEquals("Player Selecting Action Phase", controller.getCurrentPhaseName());
        assertNotEquals("Enemy Action Phase", controller.getCurrentPhaseName());
        assertNotEquals("Player Win Phase", controller.getCurrentPhaseName());
        assertNotEquals("Enemy Win Phase", controller.getCurrentPhaseName());

        assertFalse(controller.isPlayerSelectingActionPhase());
        assertFalse(controller.isEnemyActionPhase());
        assertFalse(controller.isPlayerWinPhase());
        assertFalse(controller.isEnemyWinPhase());



        // We can´t begin a turn, or attack
        controller.tryToBeginTurn();
        turnCharacter = controller.getActiveICharacter();
        assertNull(turnCharacter);

        assertTrue(troll.isAlive());
        assertEquals(10, troll.getHealthPoints());
        controller.tryToAttackCharacter(jarvan, troll);
        assertTrue(troll.isAlive());
        assertEquals(10, troll.getHealthPoints());

        // We are at StartGamePhase, then we can try to start the game
        controller.tryToStartGame();
        Thread.sleep(5000);

        // Now we are at PlayerSelectingActionPhase
        assertFalse(controller.getCurrentPhase().isStartGamePhase());
        assertFalse(controller.getCurrentPhase().isWaitingForTurnPhase());
        assertFalse(controller.getCurrentPhase().isTurnPhase());
        assertTrue(controller.getCurrentPhase().isPlayerSelectingActionPhase());
        assertFalse(controller.getCurrentPhase().isEnemyActionPhase());
        assertFalse(controller.getCurrentPhase().isPlayerWinPhase());
        assertFalse(controller.getCurrentPhase().isEnemyWinPhase());

        assertNotEquals("Start Game Phase", controller.getCurrentPhaseName());
        assertNotEquals("Waiting For Turn Phase", controller.getCurrentPhaseName());
        assertNotEquals("Turn Phase", controller.getCurrentPhaseName());
        assertEquals("Player Selecting Action Phase", controller.getCurrentPhaseName());
        assertNotEquals("Enemy Action Phase", controller.getCurrentPhaseName());
        assertNotEquals("Player Win Phase", controller.getCurrentPhaseName());
        assertNotEquals("Enemy Win Phase", controller.getCurrentPhaseName());

        assertTrue(controller.isPlayerSelectingActionPhase());
        assertFalse(controller.isEnemyActionPhase());
        assertFalse(controller.isPlayerWinPhase());
        assertFalse(controller.isEnemyWinPhase());

        // We can change weapons
        controller.tryToEquipPlayerCharacter(jarvan, infinityEdge);

        // And keep on PlayerSelectingActionPhase
        assertFalse(controller.getCurrentPhase().isStartGamePhase());
        assertFalse(controller.getCurrentPhase().isWaitingForTurnPhase());
        assertFalse(controller.getCurrentPhase().isTurnPhase());
        assertTrue(controller.getCurrentPhase().isPlayerSelectingActionPhase());
        assertFalse(controller.getCurrentPhase().isEnemyActionPhase());
        assertFalse(controller.getCurrentPhase().isPlayerWinPhase());
        assertFalse(controller.getCurrentPhase().isEnemyWinPhase());

        assertNotEquals("Start Game Phase", controller.getCurrentPhaseName());
        assertNotEquals("Waiting For Turn Phase", controller.getCurrentPhaseName());
        assertNotEquals("Turn Phase", controller.getCurrentPhaseName());
        assertEquals("Player Selecting Action Phase", controller.getCurrentPhaseName());
        assertNotEquals("Enemy Action Phase", controller.getCurrentPhaseName());
        assertNotEquals("Player Win Phase", controller.getCurrentPhaseName());
        assertNotEquals("Enemy Win Phase", controller.getCurrentPhaseName());

        assertTrue(controller.isPlayerSelectingActionPhase());
        assertFalse(controller.isEnemyActionPhase());
        assertFalse(controller.isPlayerWinPhase());
        assertFalse(controller.isEnemyWinPhase());

        // We can attack
        controller.tryToAttackCharacter(jarvan, troll);

        // Now the game is ended and the game is at PlayerWinPhase()
        assertFalse(controller.getCurrentPhase().isStartGamePhase());
        assertFalse(controller.getCurrentPhase().isWaitingForTurnPhase());
        assertFalse(controller.getCurrentPhase().isTurnPhase());
        assertFalse(controller.getCurrentPhase().isPlayerSelectingActionPhase());
        assertFalse(controller.getCurrentPhase().isEnemyActionPhase());
        assertTrue(controller.getCurrentPhase().isPlayerWinPhase());
        assertFalse(controller.getCurrentPhase().isEnemyWinPhase());

        assertNotEquals("Start Game Phase", controller.getCurrentPhaseName());
        assertNotEquals("Waiting For Turn Phase", controller.getCurrentPhaseName());
        assertNotEquals("Turn Phase", controller.getCurrentPhaseName());
        assertNotEquals("Player Selecting Action Phase", controller.getCurrentPhaseName());
        assertNotEquals("Enemy Action Phase", controller.getCurrentPhaseName());
        assertEquals("Player Win Phase", controller.getCurrentPhaseName());
        assertNotEquals("Enemy Win Phase", controller.getCurrentPhaseName());

        assertFalse(controller.isPlayerSelectingActionPhase());
        assertFalse(controller.isEnemyActionPhase());
        assertTrue(controller.isPlayerWinPhase());
        assertFalse(controller.isEnemyWinPhase());

        // We can´t create IPlayerCharacters or enemies
        assertEquals(1, controller.getPlayerCharacters().size());
        controller.createBlackMage("Morgana", 10, 5, 5);
        controller.createEngineer("Feto Ingeniero", 10, 5);
        controller.createKnight("Darius", 10, 5);
        controller.createThief("Sebastián Piñera", 10, 5);
        controller.createWhiteMage("Lux", 10, 5, 5);
        assertEquals(1, controller.getPlayerCharacters().size());

        assertEquals(1, controller.getEnemies().size());
        controller.createEnemy("Goblin", 10, 5, 15, 5);
        assertEquals(1, controller.getEnemies().size());

        // We can't create Weapons
        controller.createAxe("Black Cleaver", 15,5);
        controller.createBow("Last Whisper",15,5);
        controller.createKnife("Duskblade of Draktharr",15,5);
        controller.createStaff("Void Staff",15,15,5);
        controller.createSword("The Bloodthirster",15,5);

        nullWeapon = controller.selectWeaponFromInventory("Black Cleaver");
        assertNull(nullWeapon);

        nullWeapon = controller.selectWeaponFromInventory("Last Whisper");
        assertNull(nullWeapon);

        nullWeapon = controller.selectWeaponFromInventory("Duskblade of Draktharr");
        assertNull(nullWeapon);

        nullWeapon = controller.selectWeaponFromInventory("Void Staff");
        assertNull(nullWeapon);

        nullWeapon = controller.selectWeaponFromInventory("The Bloodthirster");
        assertNull(nullWeapon);

        // We can't equip a character
        assertEquals(infinityEdge, controller.getWeaponFrom(jarvan));
        assertTrue(controller.isWeaponInInventory(stormBreaker.getName()));
        controller.tryToEquipPlayerCharacter(jarvan, stormBreaker);
        assertEquals(infinityEdge, controller.getWeaponFrom(jarvan));
        assertTrue(controller.isWeaponInInventory(stormBreaker.getName()));

        // We can´t begin the game
        assertFalse(controller.getCurrentPhase().isStartGamePhase());
        assertTrue(controller.getCurrentPhase().isPlayerWinPhase());
        assertFalse(controller.getCurrentPhase().isEnemyWinPhase());

        assertTrue(controller.isPlayerWinPhase());
        assertFalse(controller.isEnemyWinPhase());

        controller.tryToStartGame();
        assertFalse(controller.getCurrentPhase().isStartGamePhase());
        assertTrue(controller.getCurrentPhase().isPlayerWinPhase());
        assertFalse(controller.getCurrentPhase().isEnemyWinPhase());

        assertTrue(controller.isPlayerWinPhase());
        assertFalse(controller.isEnemyWinPhase());
    }
}
