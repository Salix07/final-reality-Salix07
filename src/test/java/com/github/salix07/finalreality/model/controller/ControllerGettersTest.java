package com.github.salix07.finalreality.model.controller;

import com.github.salix07.finalreality.controller.GameController;
import com.github.salix07.finalreality.model.character.Enemy;
import com.github.salix07.finalreality.model.character.player.IMage;
import com.github.salix07.finalreality.model.character.player.IPlayerCharacter;
import com.github.salix07.finalreality.model.weapon.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

/**
 * Class containing the common tests for getters of the controller
 *
 * @author Sebastián Salinas Rodriguez.
 *
 */
public class ControllerGettersTest {
    GameController controller;

    IPlayerCharacter playerCharacter0;
    IPlayerCharacter playerCharacter1;
    Enemy enemy0;
    Enemy enemy1;

    IWeapon weapon0;
    IWeapon weapon1;

    /**
     * Basic set up for getters test
     */
    @BeforeEach
    void setUp() {
        controller = new GameController(2, 2);

        controller.createEnemy("FastGoblin", 10, 5, 15, 3);
        controller.createEnemy("NormalGoblin", 10, 5, 15, 5);
        controller.createBlackMage("Morgana", 10, 5, 5);
        controller.createKnight("Jarvan", 10, 5);

        controller.createStaff("VoidStaff", 15, 15, 10);
        controller.createAxe("Storm Breaker", 15, 12);

        playerCharacter0 = controller.getPlayerCharacter(0);
        weapon0 = controller.selectWeaponFromInventory("VoidStaff");
        controller.equipPlayerCharacter(playerCharacter0, weapon0);

        playerCharacter1 = controller.getPlayerCharacter(1);
        weapon1 = controller.selectWeaponFromInventory("Storm Breaker");
        controller.equipPlayerCharacter(playerCharacter1, weapon1);

        enemy0 = controller.getEnemy(0);
        enemy1 = controller.getEnemy(1);
    }

    /**
     * Test for the getters from the controller
     */
    @Test
    void controllerGetters() {
        assertEquals("FastGoblin", controller.getNameFrom(enemy0));
        assertEquals("NormalGoblin", controller.getNameFrom(enemy1));
        assertEquals("Morgana", controller.getNameFrom(playerCharacter0));
        assertEquals("Jarvan", controller.getNameFrom(playerCharacter1));

        assertEquals(10, controller.getHealthPointsFrom(enemy0));
        assertEquals(10, controller.getHealthPointsFrom(enemy1));
        assertEquals(10, controller.getHealthPointsFrom(playerCharacter0));
        assertEquals(10, controller.getHealthPointsFrom(playerCharacter1));

        assertEquals(5, controller.getDefenseFrom(enemy0));
        assertEquals(5, controller.getDefenseFrom(enemy1));
        assertEquals(5, controller.getDefenseFrom(playerCharacter0));
        assertEquals(5, controller.getDefenseFrom(playerCharacter1));

        assertEquals(15, controller.getAttackDamageFrom(enemy0));
        assertEquals(15, controller.getAttackDamageFrom(enemy1));
        assertEquals(15, controller.getAttackDamageFrom(playerCharacter0));
        assertEquals(15, controller.getAttackDamageFrom(playerCharacter1));

        assertEquals(5, controller.getManaFrom((IMage) playerCharacter0));

        assertEquals(3, controller.getWeightFrom(enemy0));
        assertEquals(5, controller.getWeightFrom(enemy1));
        assertEquals(weapon0, controller.getWeaponFrom(playerCharacter0));
        assertEquals(weapon1, controller.getWeaponFrom(playerCharacter1));
    }
}