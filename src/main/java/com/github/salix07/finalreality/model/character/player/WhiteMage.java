package com.github.salix07.finalreality.model.character.player;

import com.github.salix07.finalreality.model.character.ICharacter;
import java.util.concurrent.BlockingQueue;
import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a White Mage of the game.
 *
 * @author Sebastián Salinas Rodriguez
 */

public class WhiteMage extends PlayerCharacter {

    private final int mana;

    /**
     * Creates a new White Mage.
     *
     * @param name
     *     the White Mage's name
     * @param mana
     *     the mana of this White Mage
     * @param turnsQueue
     *     the queue with the characters waiting for their turn
     */
    public WhiteMage(@NotNull String name, final int mana,
                     @NotNull BlockingQueue<ICharacter> turnsQueue) {
        super(name, turnsQueue, CharacterClass.WHITE_MAGE);
        this.mana = mana;
    }
    // Especialización del mago blanco
}
