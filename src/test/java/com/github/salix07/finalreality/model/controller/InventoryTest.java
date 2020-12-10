package com.github.salix07.finalreality.model.controller;


import com.github.salix07.finalreality.controller.GameController;
import com.github.salix07.finalreality.model.character.player.IPlayerCharacter;
import com.github.salix07.finalreality.model.weapon.IWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class containing the common tests for the inventory of the game.
 *
 * @author Sebastián Salinas Rodriguez.
 *
 */
public class InventoryTest {
    GameController controller;

    IPlayerCharacter playerCharacter0;

    IWeapon weapon0;
    IWeapon weapon1;
    IWeapon weapon2;
    IWeapon weapon3;
    IWeapon weapon4;

    /**
     * Basic set up for the inventory test
     */
    @BeforeEach
    void setUp() {
        controller = new GameController(1, 0);

        controller.createKnight("Jarvan", 30, 5);
        playerCharacter0 = controller.getPlayerCharacter(0);
    }

    @Test
    void inventoryTest() {
        // At the beginning the inventory is empty
        assertTrue(controller.isInventoryEmpty());
        // Weapons creation
        controller.createAxe("StormBreaker", 15,7);
        controller.createBow("Last Whisper", 10, 5);
        controller.createStaff("VoidStaff",1,10,5);
        controller.createSword("Infinity Edge",12,6);
        controller.createKnife("Duskblade of Draktharr", 5, 3);
        // Now the inventory is not empty
        assertFalse(controller.isInventoryEmpty());

        // The IPlayerCharacters starts with no weapon
        assertNull(controller.getWeaponFrom(playerCharacter0));

        // Try to equip a wrong weapon to a IPlayerCharacter without equipped weapon
        // (the wrong weapon must be in the inventory after trying to equip the character)
        weapon0 = controller.selectWeaponFromInventory("VoidStaff");
        controller.equipPlayerCharacter(playerCharacter0, weapon0);
        assertTrue(controller.isWeaponInInventory(weapon0.getName()));
        assertNull(controller.getWeaponFrom(playerCharacter0));

        // Equip a weapon to a IPlayerCharacter without equipped weapon
        // (the weapon is removed from the inventory)
        weapon1 = controller.selectWeaponFromInventory("Infinity Edge");
        controller.equipPlayerCharacter(playerCharacter0, weapon1);
        assertFalse(controller.isWeaponInInventory(weapon1.getName()));
        assertEquals(weapon1, controller.getWeaponFrom(playerCharacter0));

        // Try to equip a wrong weapon to a IPlayerCharacter with a equipped weapon
        // (the wrong weapon must be in the inventory after trying to equip the character)
        weapon2 = controller.selectWeaponFromInventory("Last Whisper");
        controller.equipPlayerCharacter(playerCharacter0, weapon2);
        assertTrue(controller.isWeaponInInventory(weapon2.getName()));
        assertEquals(weapon1, controller.getWeaponFrom(playerCharacter0));

        // Equip a weapon to a IPlayerCharacter with a equipped weapon -> Weapon swap
        // (the weapon is removed from the inventory and the old weapon from the IPlayerCharacter is stored in the inventory)
        weapon3 = controller.selectWeaponFromInventory("StormBreaker");
        controller.equipPlayerCharacter(playerCharacter0, weapon3);
        assertFalse(controller.isWeaponInInventory(weapon3.getName()));
        assertEquals(weapon3, controller.getWeaponFrom(playerCharacter0));

        //check if a weapon that was not selected is still in inventory
        weapon4 = controller.selectWeaponFromInventory("Duskblade of Draktharr");
        assertTrue(controller.isWeaponInInventory(weapon4.getName()));
    }
}