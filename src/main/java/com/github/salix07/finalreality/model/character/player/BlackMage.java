package com.github.salix07.finalreality.model.character.player;

import com.github.salix07.finalreality.model.character.AbstractPlayerCharacter;
import com.github.salix07.finalreality.model.character.ICharacter;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a Black Mage of the game.
 *
 * @author Sebastián Salinas Rodriguez.
 */

public class BlackMage extends AbstractPlayerCharacter {

    private int mana;

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
        super(name, healthPoints, defense, turnsQueue);
        this.mana = mana;
    }
    // Black Mage specialization:

    /**
     * Returns this character's mana.
     */
    public int getMana() { return mana;}

    /**
     * Set this character's mana to the parameter passed.
     */
    public void setMana(int mana) {
        this.mana = mana;
    }

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

