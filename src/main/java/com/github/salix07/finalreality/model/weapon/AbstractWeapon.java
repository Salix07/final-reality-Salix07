package com.github.salix07.finalreality.model.weapon;

import com.github.salix07.finalreality.model.character.player.*;

/**
 * An abstract class that holds the common behaviour of all the weapons in the game.
 *
 * @author Sebastián Salinas Rodriguez
 */
public abstract class AbstractWeapon implements IWeapon {

  protected final String name;
  protected final int damage;
  protected final int weight;

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
    return name;
  }

  /**
   * Returns this weapon's damage.
   */
  public int getDamage() {
    return damage;
  }

  /**
   * Returns this weapon's weight.
   */
  public int getWeight() {
    return weight;
  }

  @Override
  public void equipToBlackMage(BlackMage blackMage) { }

  @Override
  public void equipToEngineer(Engineer engineer) { }

  @Override
  public void equipToKnight(Knight knight) { }

  @Override
  public void equipToThief(Thief thief) { }

  @Override
  public void equipToWhiteMage(WhiteMage whiteMage) { }


}
