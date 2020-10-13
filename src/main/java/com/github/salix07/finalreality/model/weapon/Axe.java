package com.github.salix07.finalreality.model.weapon;

import java.util.Objects;
/**
 * A class that holds all the information of a axe.
 *
 * @author Sebastián Salinas Rodriguez.
 */
public class Axe extends AbstractWeapon {
    /**
     * Creates a new Axe
     *
     * @param name
     *     the axe's name
     * @param damage
     *     the axe's damage
     * @param  weight
     *     the axe's weight
     */
    public Axe(final String name, final int damage, final int weight) {
        super(name, damage, weight);
    }

    // Axe specialization:

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Axe)) {
            return false;
        }
        final Axe weapon = (Axe) o;
        return getName().equals(weapon.getName()) && getDamage() == weapon.getDamage() &&
                getWeight() == weapon.getWeight();
    }

    @Override
    public int hashCode() {
        return Objects.hash(Axe.class, getName(), getDamage(), getWeight());
    }
}
