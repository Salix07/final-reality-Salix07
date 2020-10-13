package com.github.salix07.finalreality.model.character.player;

import com.github.salix07.finalreality.model.character.AbstractPlayerCharacter;
import com.github.salix07.finalreality.model.character.ICharacter;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a Engineer of the game.
 *
 * @author Sebastián Salinas Rodriguez.
 */

public class Engineer extends AbstractPlayerCharacter {

    /**
     * Creates a new Engineer.
     *
     * @param name
     *     the Engineer's name
     * @param healthPoints
     *     the health points of this Engineer
     * @param defense
     *     the defense of this Engineer
     * @param turnsQueue
     *     the queue with the characters waiting for their turn
     */
    public Engineer(@NotNull String name, int healthPoints, final int defense,
                    @NotNull BlockingQueue<ICharacter> turnsQueue) {
        super(name, healthPoints, defense, turnsQueue);
    }
    // Engineer specialization:

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Engineer)) {
            return false;
        }
        final Engineer that = (Engineer) o;
        return getName().equals(that.getName()) && getDefense() == that.getDefense();
    }

    @Override
    public int hashCode() {
        return Objects.hash(Engineer.class, getName(), getDefense());
    }
}