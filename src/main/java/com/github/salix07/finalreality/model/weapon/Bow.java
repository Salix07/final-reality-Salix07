package com.github.salix07.finalreality.model.weapon;

import com.github.salix07.finalreality.model.character.player.Engineer;
import com.github.salix07.finalreality.model.character.player.Thief;

import java.util.Objects;
/**
 * A class that holds all the information of a Bow.
 *
 * @author Sebastián Salinas Rodriguez.
 */
public class Bow extends AbstractWeapon {
    /**
     * Creates a new Bow
     *
     * @param name
     *     the bow's name
     * @param damage
     *     the bow's damage
     * @param  weight
     *     the bow's weight
     */
    public Bow(final String name, final int damage, final int weight) {
        super(name, damage, weight);
    }

    // Bow specialization:

    /**
     * The bow will be equipped on a Engineer.
     */
    @Override
    public void equipToEngineer(Engineer engineer) {
        engineer.setEquippedWeapon(this);
    }

    /**
     * The bow will be equipped on a Thief.
     */
    @Override
    public void equipToThief(Thief thief) {
        thief.setEquippedWeapon(this);
    }

    /**
     * toString method, returns String representing the class of this weapon
     */
    @Override
    public String toString() {return "Bow";}

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Bow)) {
            return false;
        }
        final Bow weapon = (Bow) o;
        return getName().equals(weapon.getName()) && getDamage() == weapon.getDamage() &&
                getWeight() == weapon.getWeight();
    }

    @Override
    public int hashCode() {
        return Objects.hash(Bow.class, getName(), getDamage(), getWeight());
    }
}
