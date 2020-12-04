package com.github.salix07.finalreality.model.controller;

import com.github.salix07.finalreality.controller.GameController;
import com.github.salix07.finalreality.model.character.Enemy;
import com.github.salix07.finalreality.model.character.IMage;
import com.github.salix07.finalreality.model.character.IPlayerCharacter;
import com.github.salix07.finalreality.model.weapon.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class containing the common tests for the game that the controller handles
 *
 * @author Sebastián Salinas Rodriguez.
 *
 */
public class GameControllerTest {
    GameController controller;

    ArrayList<IPlayerCharacter> playerCharacters;
    ArrayList<Enemy> enemies;

    IPlayerCharacter playerCharacter0;
    IPlayerCharacter playerCharacter1;
    Enemy enemy0;
    Enemy enemy1;

    IWeapon weapon0;
    IWeapon weapon1;

    /**
     * Basic set up for games test
     */
    @BeforeEach
    void setUp() {
        controller = new GameController(2,2);

        controller.createBlackMage("Morgana", 10, 5, 5);
        controller.createKnight("Jarvan",10, 5);
        controller.createEnemy("Goblin0", 10, 5, 15, 5);
        controller.createEnemy("Goblin1", 10, 5,15,5);

        controller.createStaff("VoidStaff",15,15,5);
        controller.createAxe("Storm Breaker", 15,5);

        playerCharacters = controller.getPlayerCharacters();
        enemies = controller.getEnemies();

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
        assertEquals("Morgana", controller.getNameFrom(playerCharacter0));
        assertEquals("Jarvan", controller.getNameFrom(playerCharacter1));

        assertEquals(10, controller.getHealthPointsFrom(playerCharacter0));
        assertEquals(10, controller.getHealthPointsFrom(playerCharacter1));

        assertEquals(5, controller.getDefenseFrom(playerCharacter0));
        assertEquals(5, controller.getDefenseFrom(playerCharacter1));

        assertEquals(15, controller.getAttackDamageFrom(playerCharacter0));
        assertEquals(15, controller.getAttackDamageFrom(playerCharacter1));
        assertEquals(15, controller.getAttackDamageFrom(enemy0));
        assertEquals(15, controller.getAttackDamageFrom(enemy1));

        assertEquals(5, controller.getManaFrom((IMage) playerCharacter0));

        assertEquals(weapon0, controller.getWeaponFrom(playerCharacter0));
        assertEquals(weapon1, controller.getWeaponFrom(playerCharacter1));

        assertEquals(5, controller.getWeightFrom(enemy0));
        assertEquals(5, controller.getWeightFrom(enemy1));
    }

    /**
     * test for the game in which a player wins
     */
    @Test
    void playerWinGame() {
        controller.beginTurn();

        assertTrue(playerCharacter0.isAlive());
        assertTrue(playerCharacter1.isAlive());
        assertTrue(enemy0.isAlive());
        assertTrue(enemy1.isAlive());
        assertFalse(controller.playerWin());
        assertFalse(controller.enemyWin());

        assertEquals(10, controller.getHealthPointsFrom(playerCharacter0));
        controller.attackCharacter(playerCharacter0);
        assertEquals(0, controller.getHealthPointsFrom(playerCharacter0));

        assertFalse(playerCharacter0.isAlive());
        assertTrue(playerCharacter1.isAlive());
        assertTrue(enemy0.isAlive());
        assertTrue(enemy1.isAlive());
        assertFalse(controller.playerWin());
        assertFalse(controller.enemyWin());

        assertEquals(10, controller.getHealthPointsFrom(enemy0));
        controller.attackCharacter(enemy0);
        assertEquals(0, controller.getHealthPointsFrom(enemy0));

        assertFalse(playerCharacter0.isAlive());
        assertTrue(playerCharacter1.isAlive());
        assertFalse(enemy0.isAlive());
        assertTrue(enemy1.isAlive());
        assertFalse(controller.playerWin());
        assertFalse(controller.enemyWin());

        assertEquals(10, controller.getHealthPointsFrom(enemy1));
        controller.attackCharacter(enemy1);
        controller.attackCharacter(enemy1);
        assertEquals(0, controller.getHealthPointsFrom(enemy1));

        assertFalse(playerCharacter0.isAlive());
        assertTrue(playerCharacter1.isAlive());
        assertFalse(enemy0.isAlive());
        assertFalse(enemy1.isAlive());
        assertTrue(controller.playerWin());
        assertFalse(controller.enemyWin());
    }

    /**
     * test for the game in which a enemy wins
     */
    @Test
    void enemyWinGame() {
        controller.beginTurn();

        assertTrue(playerCharacter0.isAlive());
        assertTrue(playerCharacter1.isAlive());
        assertTrue(enemy0.isAlive());
        assertTrue(enemy1.isAlive());
        assertFalse(controller.playerWin());
        assertFalse(controller.enemyWin());

        assertEquals(10, controller.getHealthPointsFrom(enemy0));
        controller.attackCharacter(enemy0);
        assertEquals(0, controller.getHealthPointsFrom(enemy0));

        assertTrue(playerCharacter0.isAlive());
        assertTrue(playerCharacter1.isAlive());
        assertFalse(enemy0.isAlive());
        assertTrue(enemy1.isAlive());
        assertFalse(controller.playerWin());
        assertFalse(controller.enemyWin());

        assertEquals(10, controller.getHealthPointsFrom(playerCharacter0));
        controller.attackCharacter(playerCharacter0);
        assertEquals(0, controller.getHealthPointsFrom(playerCharacter0));

        assertFalse(playerCharacter0.isAlive());
        assertTrue(playerCharacter1.isAlive());
        assertFalse(enemy0.isAlive());
        assertTrue(enemy1.isAlive());
        assertFalse(controller.playerWin());
        assertFalse(controller.enemyWin());

        assertEquals(10, controller.getHealthPointsFrom(playerCharacter1));
        controller.attackCharacter(playerCharacter1);
        controller.attackCharacter(playerCharacter1);
        assertEquals(0, controller.getHealthPointsFrom(playerCharacter1));

        assertFalse(playerCharacter0.isAlive());
        assertFalse(playerCharacter1.isAlive());
        assertFalse(enemy0.isAlive());
        assertTrue(enemy1.isAlive());
        assertFalse(controller.playerWin());
        assertTrue(controller.enemyWin());
    }
}
