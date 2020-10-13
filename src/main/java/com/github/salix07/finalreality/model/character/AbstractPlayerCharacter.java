package com.github.salix07.finalreality.model.character;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.github.salix07.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

/**
 * An abstract class that holds the common behaviour of all the player characters in the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Sebastián Salinas Rodriguez.
 */
public abstract class AbstractPlayerCharacter implements ICharacter, IPlayerCharacterEquipped {

  protected final String name;
  protected int healthPoints;
  protected final int defense;
  protected final BlockingQueue<ICharacter> turnsQueue;
  protected IWeapon equippedWeapon;

  private ScheduledExecutorService scheduledExecutor;

  /**
   * Constructor in common with all the player characters of the game.
   *
   * @param name
   *     the player character's name
   * @param healthPoints
   *     the player character's health points
   * @param defense
   *     the player character's defense
   * @param turnsQueue
   *     the queue with all the characters waiting for their turn
   */
  protected AbstractPlayerCharacter(@NotNull String name, int healthPoints, int defense,
                                    @NotNull BlockingQueue<ICharacter> turnsQueue) {
    this.name = name;
    this.healthPoints = healthPoints;
    this.defense = defense;
    this.turnsQueue = turnsQueue;
    this.equippedWeapon = null;
  }

  /**
   * Returns this character's name.
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Returns this character's health points.
   */
  @Override
  public int getHealthPoints() { return healthPoints; }

  /**
   * Set this character's health points to the passed parameter.
   */
  @Override
  public void setHealthPoints(int healthPoints) {
    this.healthPoints = healthPoints;
  }

  /**
   * Returns this character's defense.
   */
  @Override
  public int getDefense() { return defense; }

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
            .schedule(this::addToQueue, equippedWeapon.getWeight() / 10, TimeUnit.SECONDS);
  }

  /**
   * Equips a weapon to the character.
   */
  @Override
  public void equip(IWeapon weapon) { equippedWeapon = weapon; }

  /**
   * Return this character's equipped weapon.
   */
  @Override
  public IWeapon getEquippedWeapon() { return equippedWeapon; }
}
