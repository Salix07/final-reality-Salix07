package com.github.salix07.finalreality.model.weapon;

/**
 * This represents a weapon from the game.
 *
 * @author Sebastián Salinas Rodriguez.
 */
public interface IWeapon {

    /**
     * Returns this weapon's name.
     */
    String getName();

    /**
     * Returns this weapon's damage.
     */
    int getDamage();

    /**
     * Returns this weapon's weight.
     */
    int getWeight();
}
