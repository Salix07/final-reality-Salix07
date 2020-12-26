package com.github.salix07.finalreality.model.character;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a single enemy of the game.
 *
 * @author Sebastián Salinas Rodriguez.
 */
public class Enemy extends AbstractCharacter {

  private final int damage;
  private final int weight;

  /**
   * Creates a new enemy with a name, health points, defense, damage, weight and the queue with the characters ready to play.
   *
   * @param name
   *     the enemy's name
   * @param healthPoints
   *     the enemy's health points
   * @param  defense
   *     the enemy's defense
   * @param damage
   *     the enemy's damage
   * @param weight
   *     the enemy's weight
   * @param turnsQueue
   *     the queue with all the characters waiting for their turn
   */
  public Enemy(@NotNull final String name, int healthPoints, final int defense, final int damage,
               final int weight, @NotNull final BlockingQueue<ICharacter> turnsQueue) {
    super(name, healthPoints, defense, turnsQueue);
    this.damage = damage;
    this.weight = weight;
  }

  /**
   * Returns the damage of this enemy.
   */
  public int getDamage() { return  this.damage; }

  /**
   * Returns the weight of this enemy.
   */
  public int getWeight() { return this.weight; }

  /**
   * Returns this enemy's attack damage.
   */
  @Override
  public int getAttackDamage() {
    if (!this.isAlive()) {
      return 0;
    }
    else {
      return this.getDamage();
    }
  }

  /**
   * Sets a scheduled executor to make this character (thread) wait for {@code speed / 10}
   * seconds before adding the character to the queue.
   */
  @Override
  public void waitTurn() {
    scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    scheduledExecutor
            .schedule(this::addToQueue, this.getWeight() / 10, TimeUnit.SECONDS);
  }

  /**
   * @return int value that represents this character class, so with this int we can know what kind of turn it is
   * if the value is 1, it is a playerCharacter and the turn will be playerCharacter's one
   * if the value is 0, it is an enemy and the turn will be enemy's one
   */
  @Override
  public int turnType() {
    return 0; // int that represents this character class
  }

  /**
   * toString method, returns String representing the enemy class
   */
  @Override
  public String toString() {return "Enemy";}

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Enemy)) {
      return false;
    }
    final Enemy enemy = (Enemy) o;
    return ((getName().equals(enemy.getName())) && (getDefense() == enemy.getDefense())
            && (getDamage() == enemy.getDamage()) && (getWeight() == enemy.getWeight()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(Enemy.class, getName(), getDefense(), getDamage(), getWeight());
  }
}
