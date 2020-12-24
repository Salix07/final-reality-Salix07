package com.github.salix07.finalreality.model.character;

import com.github.salix07.finalreality.controller.eventHandler.IHandler;

/**
 * This represents a character from the game.
 * A character can be controlled by the player or by the CPU (an enemy).
 *
 * @author Sebastián Salinas Rodriguez.
 */
public interface ICharacter {

  /**
   * Returns this character's name.
   */
  String getName();

  /**
   * Returns this character's health points.
   */
  int getHealthPoints();

  /**
   * Set this character's health points to the passed parameter.
   */
  void setHealthPoints(int healthPoints);

  /**
   * Returns this character's defense.
   */
  int getDefense();

  /**
   * Returns a boolean value depending on the character condition.
   * (True if the character is alive and false if the character is dead.)
   */
  boolean isAlive();

  /**
   * Returns this character's attack damage.
   */
  int getAttackDamage();

  /**
   * The character performs an attack
   */
  void attack(ICharacter character);

  /**
   * The character get damaged
   */
  void attackedBy(int damage);

  /**
   * Sets a scheduled executor to make this character (thread) wait for {@code speed / 10}
   * seconds before adding the character to the queue.
   */
  void waitTurn();

  /**
   * Adds a subscriber to this character's death event
   * (the subscriber is the DeathHandler)
   */
  void addSubscriberForDeath(IHandler deathHandler);

  /**
   * Adds a subscriber to this character's add to queue event
   * (the subscriber is the addToQueueHandler)
   */
  void addSubscriberForAddToQueue(IHandler addToQueueHandler);

  /**
   * @return int value that represents this character class, so with this int we can know what kind of turn it is
   * if the value is 1, it is a playerCharacter and the turn will be playerCharacter's one
   * if the value is 0, it is an enemy and the turn will be enemy's one
   */
  int turnType();
}
