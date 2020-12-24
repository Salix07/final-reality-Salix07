package com.github.salix07.finalreality.model.controller;

import com.github.salix07.finalreality.controller.GameController;
import com.github.salix07.finalreality.model.character.Enemy;
import com.github.salix07.finalreality.model.character.ICharacter;
import com.github.salix07.finalreality.model.character.player.IPlayerCharacter;
import com.github.salix07.finalreality.model.weapon.IWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BigTeamFightTest {
    GameController controller;

    IPlayerCharacter morgana;
    IPlayerCharacter fetoIngeniero;
    IPlayerCharacter jarvan;
    IPlayerCharacter sebastianPinera;
    IPlayerCharacter lux;

    Enemy cannonMinion;
    Enemy blueGolem;
    Enemy redGolem;
    Enemy elderDrake;
    Enemy baronNashor;

    ICharacter turnCharacter;

    IWeapon draktharr;
    IWeapon stormBreaker;
    IWeapon infinityEdge;
    IWeapon lastWhisper;
    IWeapon voidStaff;

    /**
     * Basic set up
     */
    @BeforeEach
    void setUp() {
        controller = new GameController(5, 5);

        controller.createBlackMage("Morgana", 10, 5, 5);
        controller.createEngineer("Feto Ingeniero", 10, 5);
        controller.createKnight("Jarvan", 10, 5);
        controller.createThief("Sebastian Pinera", 10, 5);
        controller.createWhiteMage("Lux",10, 5, 5);

        controller.createEnemy("Cannon Minion", 10, 5, 15, 5);
        controller.createEnemy("Blue Golem", 10, 5, 15, 10);
        controller.createEnemy("Red Golem", 10, 5, 15, 15);
        controller.createEnemy("Elder Drake", 10, 5, 15, 15);
        controller.createEnemy("Baron Nashor", 10, 5, 15, 15);

        controller.createKnife("Duskblade of Draktharr",15,5);
        controller.createAxe("Storm Breaker", 15,5);
        controller.createSword("Infinity Edge", 15, 7);
        controller.createBow("Last Whisper",15,10);
        controller.createStaff("Void Staff",15,15,10);

        morgana = controller.getPlayerCharacter(0);
        draktharr = controller.selectWeaponFromInventory("Duskblade of Draktharr");
        controller.tryToEquipPlayerCharacter(morgana, draktharr);

        fetoIngeniero = controller.getPlayerCharacter(1);
        stormBreaker = controller.selectWeaponFromInventory("Storm Breaker");
        controller.tryToEquipPlayerCharacter(fetoIngeniero, stormBreaker);

        jarvan = controller.getPlayerCharacter(2);
        infinityEdge = controller.selectWeaponFromInventory("Infinity Edge");
        controller.tryToEquipPlayerCharacter(jarvan, infinityEdge);

        sebastianPinera = controller.getPlayerCharacter(3);
        lastWhisper = controller.selectWeaponFromInventory("Last Whisper");
        controller.tryToEquipPlayerCharacter(sebastianPinera, lastWhisper);

        lux = controller.getPlayerCharacter(4);
        voidStaff = controller.selectWeaponFromInventory("Void Staff");
        controller.tryToEquipPlayerCharacter(lux, voidStaff);

        cannonMinion = controller.getEnemy(0);
        blueGolem = controller.getEnemy(1);
        redGolem = controller.getEnemy(2);
        elderDrake = controller.getEnemy(3);
        baronNashor = controller.getEnemy(4);
    }

    /**
     * test for a team fight
     */
    @RepeatedTest(10)
    void teamFightTest() throws InterruptedException {

        assertTrue(morgana.isAlive());
        assertTrue(fetoIngeniero.isAlive());
        assertTrue(jarvan.isAlive());
        assertTrue(sebastianPinera.isAlive());
        assertTrue(lux.isAlive());

        assertTrue(cannonMinion.isAlive());
        assertTrue(blueGolem.isAlive());
        assertTrue(redGolem.isAlive());
        assertTrue(elderDrake.isAlive());
        assertTrue(baronNashor.isAlive());

        assertFalse(controller.playerWin());
        assertFalse(controller.enemyWin());

        controller.tryToStartGame();
        Thread.sleep(5000);

        assertFalse(cannonMinion.isAlive());
        assertFalse(blueGolem.isAlive());
        assertFalse(redGolem.isAlive());
        assertFalse(elderDrake.isAlive());
        assertFalse(baronNashor.isAlive());

        assertTrue(controller.playerWin());
        assertFalse(controller.enemyWin());
    }
}
