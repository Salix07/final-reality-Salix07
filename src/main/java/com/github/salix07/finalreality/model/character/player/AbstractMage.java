package com.github.salix07.finalreality.model.character.player;

import com.github.salix07.finalreality.model.character.ICharacter;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.BlockingQueue;

/**
 * An abstract class that holds the common behaviour of all mages in the game.
 *
 * @author Sebastián Salinas Rodriguez.
 */
public abstract class AbstractMage extends AbstractPlayerCharacter implements IMage {

    private int mana;

    /**
     * Constructor in common with all the mages of the game.
     *
     * @param name
     *     the Mage's name
     * @param healthPoints
     *     the health points of this Mage
     * @param defense
     *     the defense of this Mage
     * @param mana
     *     the mana of this Mage
     * @param turnsQueue
     *     the queue with the characters waiting for their turn
     */
    public AbstractMage(@NotNull String name, int healthPoints, final int defense, int mana,
                     @NotNull BlockingQueue<ICharacter> turnsQueue) {
        super(name, healthPoints, defense, turnsQueue);
        this.mana = mana;
    }

    // Mage specialization:

    /**
     * Returns this mage's mana.
     */
    @Override
    public int getMana() { return this.mana;}

    /**
     * Set this mage's mana to the parameter passed.
     */
    @Override
    public void setMana(int mana) {
        this.mana = mana;
    }
}
