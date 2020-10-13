package com.github.salix07.finalreality.model.weapon;

import java.util.Objects;
/**
 * A class that holds all the information of a staff.
 *
 * @author Sebastián Salinas Rodriguez.
 */
public class Staff extends AbstractWeapon {
    private final int magicDamage;
    /**
     * Creates a new Staff
     *
     * @param name
     *     the staff's name
     * @param damage
     *     the staff's damage
     * @param magicDamage
     *     the staff´s magic damage
     * @param  weight
     *     the staff's weight
     */
    public Staff(final String name, final int damage, final int magicDamage, final int weight) {
        super(name, damage, weight);
        this.magicDamage = magicDamage;
    }
    // Staff specialization:

    /**
     * Returns this staff's magic damage.
     */
    public int getMagicDamage() { return magicDamage; }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Staff)) {
            return false;
        }
        final Staff weapon = (Staff) o;
        return getName().equals(weapon.getName()) && getDamage() == weapon.getDamage() &&
                getMagicDamage() == weapon.getMagicDamage() && getWeight() == weapon.getWeight();
    }

    @Override
    public int hashCode() {
        return Objects.hash(Staff.class, getName(), getDamage(), getMagicDamage(), getWeight());
    }
}
