package com.github.salix07.finalreality.model.weapon;

import com.github.salix07.finalreality.model.character.player.*;

/**
 * An abstract class that holds the common behaviour of all the weapons in the game.
 *
 * @author Sebastián Salinas Rodriguez
 */
public abstract class AbstractWeapon implements IWeapon {

  private final String name;
  private final int damage;
  private final int weight;

  /**
   * Constructor in common with all weapons of the game.
   * @param name
   *     the weapon's name
   * @param damage
   *     the weapon's health points
   * @param weight
   *     the weapon's defense
   */
  public AbstractWeapon(final String name, final int damage, final int weight) {
    this.name = name;
    this.damage = damage;
    this.weight = weight;
  }

  /**
   * Returns this weapon's name.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Returns this weapon's damage.
   */
  public int getDamage() {
    return this.damage;
  }

  /**
   * Returns this weapon's weight.
   */
  public int getWeight() {
    return this.weight;
  }

  // All IWeapons will have a method to be equipped on a PlayerCharacter.
  // This methods will be call by the PlayerCharacter's equip method.
  // So only if the PlayerCharacter can be equipped with this IWeapon
  // the method will equip the PlayerCharacter.
  // Otherwise these methods will do nothing.

  /**
   * All IWeapons will have a method to be equipped on a BlackMage.
   */
  @Override
  public void equipToBlackMage(BlackMage blackMage) { }

  /**
   * All IWeapons will have a method to be equipped on a Engineer.
   */
  @Override
  public void equipToEngineer(Engineer engineer) { }

  /**
   * All IWeapons will have a method to be equipped on a Knight.
   */
  @Override
  public void equipToKnight(Knight knight) { }

  /**
   * All IWeapons will have a method to be equipped on a Thief.
   */
  @Override
  public void equipToThief(Thief thief) { }

  /**
   * All IWeapons will have a method to be equipped on a WhiteMage.
   */
  @Override
  public void equipToWhiteMage(WhiteMage whiteMage) { }
}
