package com.github.salix07.finalreality.controller;

import com.github.salix07.finalreality.model.weapon.IWeapon;

import java.util.HashMap;

/**
 * Player's inventory.
 * The inventory save the IPlayerCharacters weapons
 *
 * @author Sebastián Salinas
 */
public class Inventory {
    private HashMap<String, IWeapon> inventory;
    public Inventory() {
        inventory = new HashMap<>(); // Inventory initialization as a hashmap
    }
    /**
     * Returns a boolean value depending if the inventory is empty or not
     */
    public boolean isInventoryEmpty() { return inventory.isEmpty(); }

    /**
     * Puts an IWeapon in inventory, where the key is the weapon's name and the value is the weapon itself
     */
    public void putWeaponInInventory(IWeapon weapon) {
        inventory.put(weapon.getName(), weapon);
    }

    /**
     * Returns a boolean value depending if the weapon is in the inventory or not
     */
    public boolean isWeaponInInventory(String weaponName) {return inventory.containsKey(weaponName);}

    /**
     * Returns a reference of the weapon saved in the inventory
     */
    public IWeapon selectWeaponFromInventory(String weaponName) { return inventory.get(weaponName);}

    /**
     * Removes and returns the weapon saved in the inventory
     */
    public IWeapon removeWeaponFromInventory(String weaponName) { return inventory.remove(weaponName); }
}
