package com.github.salistito.finalreality.model.controller;

import com.github.salistito.finalreality.controller.GameController;
import com.github.salistito.finalreality.model.character.Enemy;
import com.github.salistito.finalreality.model.character.player.IMage;
import com.github.salistito.finalreality.model.character.player.IPlayerCharacter;
import com.github.salistito.finalreality.model.weapon.IWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.HashMap;

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
    IPlayerCharacter playerCharacter2;
    Enemy enemy0;
    Enemy enemy1;

    IWeapon weapon0;
    IWeapon weapon1;
    IWeapon weapon2;

    HashMap<String, Integer> charactersPositionsByName;
    HashMap<String, Integer> enemyPositionsByName;

    ArrayList<Integer> alivePlayerCharactersIndex;
    ArrayList<Integer> aliveEnemiesIndex;

    /**
     * Basic set up for getters test
     */
    @BeforeEach
    void setUp() {
        controller = new GameController(3, 2,3);

        controller.createEnemy("FastGoblin", 10, 5, 15, 3);
        controller.createEnemy("NormalGoblin", 10, 5, 15, 5);
        controller.createBlackMage("Morgana", 10, 5, 5);
        controller.createKnight("Jarvan", 10, 5);
        controller.createThief("Pinera",10,5);

        controller.createStaff("VoidStaff", 15, 15, 10);
        controller.createAxe("Storm Breaker", 15, 12);

        playerCharacter0 = controller.getPlayerCharacter(0);
        weapon0 = controller.selectWeaponFromInventory("VoidStaff");
        controller.equipPlayerCharacter(playerCharacter0, weapon0);

        playerCharacter1 = controller.getPlayerCharacter(1);
        weapon1 = controller.selectWeaponFromInventory("Storm Breaker");
        controller.equipPlayerCharacter(playerCharacter1, weapon1);

        playerCharacter2 = controller.getPlayerCharacter(2);

        enemy0 = controller.getEnemy(0);
        enemy1 = controller.getEnemy(1);

        charactersPositionsByName = controller.getCharactersPositionsByName();
        enemyPositionsByName = controller.getEnemyPositionsByName();

        alivePlayerCharactersIndex = controller.getAlivePlayerCharactersIndex();
        aliveEnemiesIndex = controller.getAliveEnemiesIndex();
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
        assertEquals("Pinera", controller.getNameFrom(playerCharacter2));

        assertEquals(10, controller.getHealthPointsFrom(enemy0));
        assertEquals(10, controller.getHealthPointsFrom(enemy1));
        assertEquals(10, controller.getHealthPointsFrom(playerCharacter0));
        assertEquals(10, controller.getHealthPointsFrom(playerCharacter1));
        assertEquals(10, controller.getHealthPointsFrom(playerCharacter2));

        assertEquals(5, controller.getDefenseFrom(enemy0));
        assertEquals(5, controller.getDefenseFrom(enemy1));
        assertEquals(5, controller.getDefenseFrom(playerCharacter0));
        assertEquals(5, controller.getDefenseFrom(playerCharacter1));
        assertEquals(5, controller.getDefenseFrom(playerCharacter2));

        assertEquals(15, controller.getAttackDamageFrom(enemy0));
        assertEquals(15, controller.getAttackDamageFrom(enemy1));
        assertEquals(15, controller.getAttackDamageFrom(playerCharacter0));
        assertEquals(15, controller.getAttackDamageFrom(playerCharacter1));
        assertEquals(0, controller.getAttackDamageFrom(playerCharacter2));

        assertEquals(weapon0, controller.getWeaponFrom(playerCharacter0));
        assertEquals(weapon1, controller.getWeaponFrom(playerCharacter1));
        assertNull(controller.getWeaponFrom(playerCharacter2));

        assertEquals(15, controller.getWeaponDamageOff(weapon0));
        assertEquals(15, controller.getWeaponDamageOff(weapon1));

        assertEquals(10, controller.getWeaponWeightOff(weapon0));
        assertEquals(12, controller.getWeaponWeightOff(weapon1));

        assertEquals(3, controller.getWeightFrom(enemy0));
        assertEquals(5, controller.getWeightFrom(enemy1));

        assertEquals(5, controller.getManaFrom((IMage) playerCharacter0));

        assertEquals(Integer.valueOf(1), enemyPositionsByName.get("FastGoblin"));
        assertEquals(Integer.valueOf(2), enemyPositionsByName.get("NormalGoblin"));
        assertEquals(Integer.valueOf(1), charactersPositionsByName.get("Morgana"));
        assertEquals(Integer.valueOf(2), charactersPositionsByName.get("Jarvan"));
        assertEquals(Integer.valueOf(3), charactersPositionsByName.get("Pinera"));

        assertEquals(Integer.valueOf(0), aliveEnemiesIndex.get(0));
        assertEquals(Integer.valueOf(1), aliveEnemiesIndex.get(1));
        assertEquals(Integer.valueOf(0), alivePlayerCharactersIndex.get(0));
        assertEquals(Integer.valueOf(1), alivePlayerCharactersIndex.get(1));
        assertEquals(Integer.valueOf(2), alivePlayerCharactersIndex.get(2));

        assertEquals("FastGoblin", controller.getEnemyNameByIndex(0));
        assertEquals("NormalGoblin", controller.getEnemyNameByIndex(1));
        assertEquals("Morgana", controller.getPlayerCharacterNameByIndex(0));
        assertEquals("Jarvan", controller.getPlayerCharacterNameByIndex(1));
        assertEquals("Pinera", controller.getPlayerCharacterNameByIndex(2));

        assertEquals(10, controller.getEnemyHealthByIndex(0));
        assertEquals(10, controller.getEnemyHealthByIndex(1));
        assertEquals(10, controller.getPlayerCharacterHealthByIndex(0));
        assertEquals(10, controller.getPlayerCharacterHealthByIndex(1));
        assertEquals(10, controller.getPlayerCharacterHealthByIndex(2));

        assertEquals(5, controller.getEnemyDefenseByIndex(0));
        assertEquals(5, controller.getEnemyDefenseByIndex(1));
        assertEquals(5, controller.getPlayerCharacterDefenseByIndex(0));
        assertEquals(5, controller.getPlayerCharacterDefenseByIndex(1));
        assertEquals(5, controller.getPlayerCharacterDefenseByIndex(2));

        assertEquals(15, controller.getEnemyAttackByIndex(0));
        assertEquals(15, controller.getEnemyAttackByIndex(1));
        assertEquals(15, controller.getPlayerCharacterAttackByIndex(0));
        assertEquals(15, controller.getPlayerCharacterAttackByIndex(1));
        assertEquals(0, controller.getPlayerCharacterAttackByIndex(2));

        assertEquals(10, controller.getWeightOffEquippedWeaponFrom(playerCharacter0));
        assertEquals(12, controller.getWeightOffEquippedWeaponFrom(playerCharacter1));

        assertEquals(weapon0.getName(), controller.getNameOffEquippedWeaponFrom(playerCharacter0));
        assertEquals(weapon1.getName(), controller.getNameOffEquippedWeaponFrom(playerCharacter1));
        assertEquals("No weapon Equipped!", controller.getNameOffEquippedWeaponFrom(playerCharacter2));

        assertEquals(weapon0.getName(), controller.getNameOffEquippedWeaponByIndex(0));
        assertEquals(weapon1.getName(), controller.getNameOffEquippedWeaponByIndex(1));
        assertEquals("No weapon Equipped!", controller.getNameOffEquippedWeaponByIndex(2));

        assertEquals(10, controller.getWeightOffEquippedWeaponByIndex(0));
        assertEquals(12, controller.getWeightOffEquippedWeaponByIndex(1));
        assertEquals(3, controller.getEnemyWeightByIndex(0));
        assertEquals(5, controller.getEnemyWeightByIndex(1));

        assertEquals(playerCharacter0.toString(), controller.getCharacterClassFrom(playerCharacter0));
        assertEquals(playerCharacter1.toString(), controller.getCharacterClassFrom(playerCharacter1));
        assertEquals(playerCharacter2.toString(), controller.getCharacterClassFrom(playerCharacter2));

        assertEquals(playerCharacter0.toString(), controller.getCharacterClassByIndex(0));
        assertEquals(playerCharacter1.toString(), controller.getCharacterClassByIndex(1));
        assertEquals(playerCharacter2.toString(), controller.getCharacterClassByIndex(2));

        assertEquals(enemy0.toString(), controller.getEnemyClassByIndex(0));
        assertEquals(enemy1.toString(), controller.getEnemyClassByIndex(1));

        assertEquals(weapon0.toString(), controller.getWeaponClass(weapon0));
        assertEquals(weapon1.toString(), controller.getWeaponClass(weapon1));

        assertEquals(30, controller.getPartyHealthPoints());
        assertEquals(15, controller.getPartyDefense());
        assertEquals(30, controller.getPartyAttackDamage());

        assertFalse(controller.partyIsEquipped());

        controller.createKnife("Drakktar", 5,1);
        weapon2 = controller.selectWeaponFromInventory("Drakktar");
        controller.equipPlayerCharacter(playerCharacter2, weapon2);

        assertTrue(controller.partyIsEquipped());
        assertEquals(30, controller.getPartyHealthPoints());
        assertEquals(15, controller.getPartyDefense());
        assertEquals(35, controller.getPartyAttackDamage());
        assertEquals(23, controller.getPartyWeight());

    }
}