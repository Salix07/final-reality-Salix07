package com.github.salix07.finalreality.model.character.player;

import com.github.salix07.finalreality.model.character.ICharacter;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

import com.github.salix07.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a Knight of the game.
 *
 * @author Sebastián Salinas Rodriguez.
 */

public class Knight extends AbstractPlayerCharacter {

    /**
     * Creates a new Knight
     *
     * @param name
     *     the Knight's name
     * @param healthPoints
     *     the health points of this Knight
     * @param defense
     *     the defense of this Knight
     * @param turnsQueue
     *     the queue with the characters waiting for their turn
     */
    public Knight(@NotNull String name, int healthPoints, final int defense,
                  @NotNull BlockingQueue<ICharacter> turnsQueue) {
        super(name, healthPoints, defense, turnsQueue);
    }
    // Knight specialization:

    /**
     * Equips a weapon to the Knight using Double Dispatch.
     */
    @Override
    public void equip(IWeapon weapon) {
        weapon.equipToKnight(this);
    }

    /**
     * toString method, returns String representing this Character
     */
    @Override
    public String toString() {return "Knight";}

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Knight)) {
            return false;
        }
        final Knight that = (Knight) o;
        return getName().equals(that.getName()) && getDefense() == that.getDefense();
    }

    @Override
    public int hashCode() {
        return Objects.hash(Knight.class, getName(), getDefense());
    }
}
