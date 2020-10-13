package com.github.salix07.finalreality.model.weapon;

import java.util.Objects;
/**
 * A class that holds all the information of a Knife.
 *
 * @author Sebastián Salinas Rodriguez.
 */
public class Knife extends AbstractWeapon {
    /**
     * Creates a new Knife
     *
     * @param name
     *     the knife's name
     * @param damage
     *     the knife's damage
     * @param  weight
     *     the knife's weight
     */
    public Knife(final String name, final int damage, final int weight) {
        super(name, damage, weight);
    }

    // Knife specialization:

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Knife)) {
            return false;
        }
        final Knife weapon = (Knife) o;
        return getName().equals(weapon.getName()) && getDamage() == weapon.getDamage() &&
                getWeight() == weapon.getWeight();
    }

    @Override
    public int hashCode() {
        return Objects.hash(Knife.class, getName(), getDamage(), getWeight());
    }
}
