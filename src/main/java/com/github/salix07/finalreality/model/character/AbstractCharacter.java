package com.github.salix07.finalreality.model.character;

import com.github.salix07.finalreality.model.character.player.CharacterClass;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import org.jetbrains.annotations.NotNull;

/**
 * An abstract class that holds the common behaviour of all the characters in the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author <Sebastián Salinas Rodriguez>
 */
public abstract class AbstractCharacter implements ICharacter {

  protected final String name;
  protected final BlockingQueue<ICharacter> turnsQueue;
  protected final CharacterClass characterClass;
  protected ScheduledExecutorService scheduledExecutor;

  protected AbstractCharacter(
          @NotNull String name,
          @NotNull BlockingQueue<ICharacter> turnsQueue,
          CharacterClass characterClass) {
    this.name = name;
    this.turnsQueue = turnsQueue;
    this.characterClass = characterClass;
  }

  /**
   * Adds this character to the turns queue.
   */
  protected void addToQueue() {
    turnsQueue.add(this);
    scheduledExecutor.shutdown();
  }

  /**
   * Returns this character's name.
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Returns this character's class.
   */
  @Override
  public CharacterClass getCharacterClass() {
    return characterClass;
  }
}
