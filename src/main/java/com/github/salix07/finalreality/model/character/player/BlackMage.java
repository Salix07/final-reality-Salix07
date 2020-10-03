package com.github.salix07.finalreality.model.character.player;

import com.github.salix07.finalreality.model.character.ICharacter;
import java.util.concurrent.BlockingQueue;
import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a Black Mage of the game.
 *
 * @author Sebastián Salinas Rodriguez
 */

public class BlackMage extends PlayerCharacter {

    private final int mana;

    /**
     * Creates a new Black Mage.
     *
     * @param name
     *     the Black Mage's name
     * @param mana
     *     the mana of this Black Mage
     * @param turnsQueue
     *     the queue with the characters waiting for their turn
     */
    public BlackMage(@NotNull String name, final int mana,
                     @NotNull BlockingQueue<ICharacter> turnsQueue) {
        super(name, turnsQueue, CharacterClass.BLACK_MAGE);
        this.mana = mana;
    }
    // Especialización del mago negro
}

