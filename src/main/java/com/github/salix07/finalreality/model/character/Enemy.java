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
 * @author Sebastián Salinas Rodriguez.
 */
public class Enemy extends AbstractCharacter {

  protected final int damage;
  protected final int weight;

  private ScheduledExecutorService scheduledExecutor;

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
  public int getDamage() { return  damage; }

  /**
   * Returns the weight of this enemy.
   */
  public int getWeight() { return weight; }

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
    return Objects.hash(Enemy.class, getName(), getDefense(), getDamage(), getWeight());
  }
}
