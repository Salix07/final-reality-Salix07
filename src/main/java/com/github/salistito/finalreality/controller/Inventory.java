package com.github.salistito.finalreality.controller;

import com.github.salistito.finalreality.model.weapon.IWeapon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Player's inventory.
 * The inventory store the IPlayerCharacter's weapons
 *
 * @author Sebastián Salinas
 */
public class Inventory {

    private final HashMap<String, IWeapon> inventory;
    private final int maxSize;

    /**
     * Constructor of the class Inventory, here the inventory is initialized as a Hashmap
     */
    public Inventory(int maxSize) {
        inventory = new HashMap<>(maxSize); // Inventory initialization as a hashmap
        this.maxSize = maxSize; // Max size of the inventory
    }

    /**
     * Returns the actual inventory size
     */
    public int getSize() {return inventory.size();}

    /**
     * Returns the inventory capacity (max size)
     */
    public int getMaxSize() {return maxSize;}


    /**
     * Returns a boolean value depending if the inventory is empty or not
     */
    public boolean isInventoryEmpty() { return inventory.isEmpty(); }

    /**
     * Returns a boolean value depending if the inventory is full or not
     */
    public boolean isInventoryFull() {return inventory.size() == maxSize;}

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

    /**
     * Returns the names of the weapons stored in the inventory
     */
    public ArrayList<String> getWeaponsName() {
        Set<String>  weaponsName = inventory.keySet();
        return new ArrayList<>(weaponsName);
    }
}
