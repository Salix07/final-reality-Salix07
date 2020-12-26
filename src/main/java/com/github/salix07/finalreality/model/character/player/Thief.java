package com.github.salix07.finalreality.model.character.player;

import com.github.salix07.finalreality.model.character.ICharacter;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

import com.github.salix07.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a Thief of the game.
 *
 * @author Sebastián Salinas Rodriguez.
 */

public class Thief  extends AbstractPlayerCharacter {

    /**
     * Creates a new Thief
     *
     * @param name
     *     the Thief's name
     * @param healthPoints
     *     the health points of this Thief
     * @param defense
     *     the defense of this Thief
     * @param turnsQueue
     *     the queue with the characters waiting for their turn
     */
    public Thief(@NotNull String name, int healthPoints, final int defense,
                 @NotNull BlockingQueue<ICharacter> turnsQueue) {
        super(name, healthPoints, defense, turnsQueue);
    }
    // Thief specialization:

    /**
     * Equips a weapon to the Thief using Double Dispatch.
     */
    @Override
    public void equip(IWeapon weapon) {
        weapon.equipToThief(this);
    }

    /**
     * toString method, returns String representing this Character
     */
    @Override
    public String toString() {return "Thief";}

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Thief)) {
            return false;
        }
        final Thief that = (Thief) o;
        return getName().equals(that.getName()) && getDefense() == that.getDefense();
    }

    @Override
    public int hashCode() {
        return Objects.hash(Thief.class, getName(), getDefense());
    }
}
