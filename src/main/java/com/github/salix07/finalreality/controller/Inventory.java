package com.github.salix07.finalreality.controller;

import com.github.salix07.finalreality.model.weapon.IWeapon;

import java.util.HashMap;

/**
 * Player's inventory.
 * The inventory save the IPlayerCharacter's weapons
 *
 * @author Sebastián Salinas
 */
public class Inventory {
    private HashMap<String, IWeapon> inventory;
    private int maxSize;

    /**
     * Constructor of the class Inventory, here the inventory is initialized as a Hashmap
     */
    public Inventory(int maxSize) {
        inventory = new HashMap<>(maxSize); // Inventory initialization as a hashmap
        this.maxSize = maxSize; // Max size of the inventory
    }

    /**
     * Returns a boolean value depending if the inventory is empty or not
     */
    public boolean isInventoryEmpty() { return inventory.isEmpty(); }

    /**
     * Puts an IWeapon in inventory, where the key is the weapon's name and the value is the weapon itself
     */
    public void putWeaponInInventory(IWeapon weapon) {
        if(inventory.size() < maxSize) {
            inventory.put(weapon.getName(), weapon);
        }
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
