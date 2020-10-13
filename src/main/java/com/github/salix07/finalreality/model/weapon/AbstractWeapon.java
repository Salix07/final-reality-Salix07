package com.github.salix07.finalreality.model.weapon;

/**
 * A class that holds all the information of a weapon.
 *
 * @author Ignacio Slater Muñoz.
 * @author Sebastián Salinas Rodriguez
 */
public abstract class AbstractWeapon implements IWeapon {

  protected final String name;
  protected final int damage;
  protected final int weight;

  /**
   * Creates a weapon with a name, a base damage, speed.
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
}
