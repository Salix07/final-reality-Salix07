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

    /**
     * Basic set up for all the game test
     */
    @BeforeEach
    void setUp() {
        controller = new GameController(2, 2);

        controller.createBlackMage("Morgana", 10, 5, 5);
        controller.createEnemy("Goblin", 10, 5, 15, 10);
        controller.createEnemy("Troll", 10, 5, 15, 20);

        controller.createStaff("VoidStaff", 15, 15, 5);

        morgana = controller.getPlayerCharacter(0);
        voidStaff = controller.selectWeaponFromInventory("VoidStaff");
        controller.equipPlayerCharacter(morgana, voidStaff);

        goblin = controller.getEnemy(0);
        troll = controller.getEnemy(1);
    }

    @Test
    void gameNotStartedYet() throws InterruptedException {
        // Try to start the game with less playerCharacters than the value passed at the controller
        // In this case the game must not start so the activeICharacter must be null and the phase mustn't change
        assertTrue(controller.getPhase().isStartGamePhase());
        assertFalse(controller.getPhase().isWaitingForTurnPhase());
        assertFalse(controller.getPhase().isTurnPhase());
        assertFalse(controller.getPhase().isSelectingActionPhase());
        assertFalse(controller.getPhase().isGameOverPhase());

        controller.beginGame();

        assertTrue(controller.getPhase().isStartGamePhase());
        assertFalse(controller.getPhase().isWaitingForTurnPhase());
        assertFalse(controller.getPhase().isTurnPhase());
        assertFalse(controller.getPhase().isSelectingActionPhase());
        assertFalse(controller.getPhase().isGameOverPhase());

        turnCharacter = controller.getActiveICharacter();
        assertNull(turnCharacter);

        // Create the missing character
        controller.createKnight("Jarvan", 10, 5);
        controller.createAxe("Storm Breaker", 15, 15);
        jarvan = controller.getPlayerCharacter(1);
        stormBreaker = controller.selectWeaponFromInventory("Storm Breaker");
        controller.equipPlayerCharacter(jarvan, stormBreaker);

        // The game now can start
        controller.beginGame();
        Thread.sleep(5000);
        assertFalse(controller.getPhase().isStartGamePhase());
        assertFalse(controller.getPhase().isWaitingForTurnPhase());
        assertFalse(controller.getPhase().isTurnPhase());
        assertFalse(controller.getPhase().isSelectingActionPhase());
        assertTrue(controller.getPhase().isGameOverPhase());
    }
}
