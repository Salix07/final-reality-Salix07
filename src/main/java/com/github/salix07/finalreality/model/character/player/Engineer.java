package com.github.salix07.finalreality.model.character.player;

import com.github.salix07.finalreality.model.character.ICharacter;
import java.util.concurrent.BlockingQueue;
import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a Engineer of the game.
 *
 * @author Sebastián Salinas Rodriguez
 */

public class Engineer extends PlayerCharacter {
    /**
     * Creates a new Engineer
     *
     * @param name
     *     the Engineer's name
     * @param turnsQueue
     *     the queue with the characters waiting for their turn
     */
    public Engineer(@NotNull String name,
                  @NotNull BlockingQueue<ICharacter> turnsQueue) {
        super(name, turnsQueue, CharacterClass.ENGINEER);
    }
    // Especialización del ingeniero
}
