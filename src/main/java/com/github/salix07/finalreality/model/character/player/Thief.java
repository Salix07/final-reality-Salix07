package com.github.salix07.finalreality.model.character.player;

import com.github.salix07.finalreality.model.character.ICharacter;
import java.util.concurrent.BlockingQueue;
import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a Thief of the game.
 *
 * @author Sebastián Salinas Rodriguez
 */

public class Thief  extends PlayerCharacter {
    /**
     * Creates a new Thief
     *
     * @param name
     *     the Thief's name
     * @param turnsQueue
     *     the queue with the characters waiting for their turn
     */
    public Thief(@NotNull String name,
                  @NotNull BlockingQueue<ICharacter> turnsQueue) {
        super(name, turnsQueue, CharacterClass.THIEF);
    }
    // Especialización del ladron
}
