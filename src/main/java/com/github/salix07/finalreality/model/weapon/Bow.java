package com.github.salix07.finalreality.model.weapon;

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
