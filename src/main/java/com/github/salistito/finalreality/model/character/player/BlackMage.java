package com.github.salistito.finalreality.model.character.player;

import com.github.salistito.finalreality.model.weapon.IWeapon;
import com.github.salistito.finalreality.model.character.ICharacter;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a Black Mage of the game.
 *
 * @author Sebastián Salinas Rodriguez.
 */
public class BlackMage extends AbstractMage {

    /**
     * Creates a new Black Mage.
     *
     * @param name
     *     the Black Mage's name
     * @param healthPoints
     *     the health points of this Black Mage
     * @param defense
     *     the defense of this Black Mage
     * @param mana
     *     the mana of this Black Mage
     * @param turnsQueue
     *     the queue with the characters waiting for their turn
     */
    public BlackMage(@NotNull String name, int healthPoints, final int defense, int mana,
                     @NotNull BlockingQueue<ICharacter> turnsQueue) {
        super(name, healthPoints, defense, mana, turnsQueue);
    }

    // Black Mage specialization:

    /**
     * Equips a weapon to the BlackMage using Double Dispatch.
     */
    @Override
    public void equip(IWeapon weapon) {
        weapon.equipToBlackMage(this);
    }

    /**
     * toString method, returns String representing this Character
     */
    @Override
    public String toString() {return "BlackMage";}

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BlackMage)) {
            return false;
        }
        final BlackMage that = (BlackMage) o;
        return getName().equals(that.getName()) && getDefense() == that.getDefense();
    }

    @Override
    public int hashCode() {
        return Objects.hash(BlackMage.class, getName(), getDefense());
    }
}

