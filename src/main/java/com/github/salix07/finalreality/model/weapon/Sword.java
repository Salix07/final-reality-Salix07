package com.github.salix07.finalreality.model.weapon;

/**
 * A class that holds all the information of a sword.
 *
 * @author Sebastián Salinas Rodriguez
 */
public class Sword extends Weapon {

    public Sword(final String name, final int damage, final int weight) {
        super(name, damage, weight, WeaponType.SWORD);
    }
    // Especialización de la espada
}
