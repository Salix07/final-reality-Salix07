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
        controller = new GameController(1, 1);

        controller.createKnight("Jarvan", 10, 5);
        controller.createEnemy("Troll", 10, 5, 15, 20);

        controller.createAxe("Storm Breaker", 15, 5);
        controller.createSword("Infinity Edge", 10,5);

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
        // The game start at StartGamePhase()
        assertTrue(controller.getCurrentPhase().isStartGamePhase());
        assertFalse(controller.getCurrentPhase().isWaitingForTurnPhase());
        assertFalse(controller.getCurrentPhase().isTurnPhase());
        assertFalse(controller.getCurrentPhase().isSelectingActionPhase());
        assertFalse(controller.getCurrentPhase().isGameOverPhase());

        // We can´t begin a turn, or attack
        controller.tryToBeginTurn();
        turnCharacter = controller.getActiveICharacter();
        assertNull(turnCharacter);

        assertTrue(troll.isAlive());
        assertEquals(10, troll.getHealthPoints());
        controller.tryToAttackCharacter(jarvan, troll);
        assertTrue(troll.isAlive());
        assertEquals(10, troll.getHealthPoints());

        controller.tryToStartGame();
        Thread.sleep(5000);

        // Now the game is ended and the game is at GameOverPhase()
        assertFalse(controller.getCurrentPhase().isStartGamePhase());
        assertFalse(controller.getCurrentPhase().isWaitingForTurnPhase());
        assertFalse(controller.getCurrentPhase().isTurnPhase());
        assertFalse(controller.getCurrentPhase().isSelectingActionPhase());
        assertTrue(controller.getCurrentPhase().isGameOverPhase());

        // We can´t create IPlayerCharacters or enemies
        assertEquals(1, controller.getPlayerCharacters().size());
        controller.createBlackMage("Morgana", 10, 5, 5);
        controller.createEngineer("Feto InJeniero", 10, 5);
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
        assertEquals(stormBreaker, controller.getWeaponFrom(jarvan));
        assertTrue(controller.isWeaponInInventory(infinityEdge.getName()));
        controller.tryToEquipPlayerCharacter(jarvan, infinityEdge);
        assertEquals(stormBreaker, controller.getWeaponFrom(jarvan));
        assertTrue(controller.isWeaponInInventory(infinityEdge.getName()));

        // We can´t begin the game
        assertFalse(controller.getCurrentPhase().isStartGamePhase());
        assertTrue(controller.getCurrentPhase().isGameOverPhase());
        controller.tryToStartGame();
        assertFalse(controller.getCurrentPhase().isStartGamePhase());
        assertTrue(controller.getCurrentPhase().isGameOverPhase());
    }
}
