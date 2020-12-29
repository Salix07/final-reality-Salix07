package com.github.salix07.finalreality.model.controller;

import com.github.salix07.finalreality.controller.GameController;
import com.github.salix07.finalreality.model.character.Enemy;
import com.github.salix07.finalreality.model.character.ICharacter;
import com.github.salix07.finalreality.model.character.player.IPlayerCharacter;
import com.github.salix07.finalreality.model.weapon.IWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BigTeamFightTest {
    GameController controller;

    IPlayerCharacter morgana;
    IPlayerCharacter fetoIngeniero;
    IPlayerCharacter jarvan;

    Enemy redGolem;
    Enemy elderDrake;
    Enemy baronNashor;

    ICharacter turnCharacter;

    IWeapon voidStaff;
    IWeapon lastWhisper;
    IWeapon stormBreaker;

    /**
     * Basic set up
     */
    @BeforeEach
    void setUp() {
        controller = new GameController(3, 3,3);

        controller.createBlackMage("Morgana", 10, 5, 5);
        controller.createEngineer("Feto Ingeniero", 10, 5);
        controller.createKnight("Jarvan", 10, 5);

        controller.createEnemy("Red Golem", 31, 5, 15, 10);
        controller.createEnemy("Elder Drake", 31, 5, 15, 10);
        controller.createEnemy("Baron Nashor", 31, 5, 15, 10);

        controller.createStaff("Void Staff",15,15,5);
        controller.createBow("Last Whisper",15,5);
        controller.createAxe("Storm Breaker", 15,5);

        morgana = controller.getPlayerCharacter(0);
        voidStaff = controller.selectWeaponFromInventory("Void Staff");
        controller.tryToEquipPlayerCharacter(morgana, voidStaff);

        fetoIngeniero = controller.getPlayerCharacter(1);
        lastWhisper = controller.selectWeaponFromInventory("Last Whisper");
        controller.tryToEquipPlayerCharacter(fetoIngeniero, lastWhisper);

        jarvan = controller.getPlayerCharacter(2);
        stormBreaker = controller.selectWeaponFromInventory("Storm Breaker");
        controller.tryToEquipPlayerCharacter(jarvan, stormBreaker);

        redGolem = controller.getEnemy(0);
        elderDrake = controller.getEnemy(1);
        baronNashor = controller.getEnemy(2);
    }

    /**
     * test for a team fight
     */
    @RepeatedTest(1)
    void teamFightTest() throws InterruptedException {

        assertTrue(morgana.isAlive());
        assertTrue(fetoIngeniero.isAlive());
        assertTrue(jarvan.isAlive());

        assertTrue(redGolem.isAlive());
        assertTrue(elderDrake.isAlive());
        assertTrue(baronNashor.isAlive());

        assertFalse(controller.playerWin());
        assertFalse(controller.enemyWin());

        controller.tryToStartGame();
        Thread.sleep(5000);

        for(int i=0; i<3; i++) {
            turnCharacter = controller.getActiveICharacter();
            controller.tryToAttackCharacter(turnCharacter, baronNashor);
        }

        assertFalse(morgana.isAlive());
        assertFalse(fetoIngeniero.isAlive());
        assertFalse(jarvan.isAlive());

        assertTrue(redGolem.isAlive());
        assertTrue(elderDrake.isAlive());
        assertTrue(baronNashor.isAlive());

        assertFalse(controller.playerWin());
        assertTrue(controller.enemyWin());
    }
}
