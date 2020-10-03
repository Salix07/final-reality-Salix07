package com.github.salix07.finalreality.model.character.player;

import com.github.salix07.finalreality.model.character.ICharacter;
import java.util.concurrent.BlockingQueue;
import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a Knight of the game.
 *
 * @author Sebastián Salinas Rodriguez
 */

public class Knight extends PlayerCharacter {
    /**
     * Creates a new Knight
     *
     * @param name
     *     the Knight's name
     * @param turnsQueue
     *     the queue with the characters waiting for their turn
     */
    public Knight(@NotNull String name,
                  @NotNull BlockingQueue<ICharacter> turnsQueue) {
        super(name, turnsQueue, CharacterClass.KNIGHT);
    }
    // Especialización del caballero
}
