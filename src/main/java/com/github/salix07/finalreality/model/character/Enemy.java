package com.github.salix07.finalreality.model.character;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a single enemy of the game.
 *
 * @author Ignacio Slater Muñoz
 * @author Sebastián Salinas Rodriguez.
 */
public class Enemy implements ICharacter {

  protected final String name;
  protected int healthPoints;
  protected final int defense;
  protected final int damage;
  protected final int weight;
  protected final BlockingQueue<ICharacter> turnsQueue;

  private ScheduledExecutorService scheduledExecutor;

  /**
   * Creates a new enemy with a name, a weight and the queue with the characters ready to play.
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
    this.name = name;
    this.healthPoints = healthPoints;
    this.defense = defense;
    this.damage = damage;
    this.weight = weight;
    this.turnsQueue = turnsQueue;
  }

  /**
   * Returns this enemy's name.
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Returns this character's health points.
   */
  @Override
  public int getHealthPoints() { return  healthPoints;}

  /**
   * Set this character's health points to the parameter passed.
   */
  @Override
  public void setHealthPoints(int healthPoints) {
    this.healthPoints = healthPoints;
  }

  /**
   * Returns this character's defense.
   */
  @Override
  public int getDefense() { return defense;}

  /**
   * Returns the damage of this enemy.
   */
  public int getDamage() { return  damage; }

  /**
   * Returns the weight of this enemy.
   */
  public int getWeight() { return weight; }

  /**
   * Adds this character to the turns queue.
   */
  private void addToQueue() {
    turnsQueue.add(this);
    scheduledExecutor.shutdown();
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
    return Objects.hash(getName(), getDefense(), getDamage(), getWeight());
  }
}
