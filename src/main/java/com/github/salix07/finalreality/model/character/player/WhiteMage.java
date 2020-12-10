package com.github.salix07.finalreality.model.character.player;

import com.github.salix07.finalreality.model.character.ICharacter;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

import com.github.salix07.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a White Mage of the game.
 *
 * @author Sebastián Salinas Rodriguez.
 */

public class WhiteMage extends AbstractMage {

    /**
     * Creates a new White Mage.
     *
     * @param name
     *     the White Mage's name
     * @param healthPoints
     *     the health points of this White Mage
     * @param defense
     *     the defense of this White Mage
     * @param mana
     *     the mana of this White Mage
     * @param turnsQueue
     *     the queue with the characters waiting for their turn
     */
    public WhiteMage(@NotNull String name, int healthPoints, final int defense, int mana,
                     @NotNull BlockingQueue<ICharacter> turnsQueue) {
        super(name, healthPoints, defense, mana, turnsQueue);
    }
    // White Mage specialization:

    /**
     * Equips a weapon to the WhiteMage using Double Dispatch.
     */
    @Override
    public void equip(IWeapon weapon) {
        weapon.equipToWhiteMage(this);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WhiteMage)) {
            return false;
        }
        final WhiteMage that = (WhiteMage) o;
        return getName().equals(that.getName()) && getDefense() == that.getDefense();
    }

    @Override
    public int hashCode() {
        return Objects.hash(WhiteMage.class, getName(), getDefense());
    }
}
