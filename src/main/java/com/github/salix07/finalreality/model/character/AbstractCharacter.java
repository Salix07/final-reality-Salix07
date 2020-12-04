package com.github.salix07.finalreality.model.character;

import java.beans.PropertyChangeSupport;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ScheduledExecutorService;

import com.github.salix07.finalreality.controller.EnemyDeathHandler;
import com.github.salix07.finalreality.controller.PlayerCharacterDeathHandler;
import org.jetbrains.annotations.NotNull;

/**
 * An abstract class that holds the common behaviour of all the characters in the game.
 *
 * @author Sebastián Salinas Rodriguez.
 */
public abstract class AbstractCharacter implements ICharacter {
  private final PropertyChangeSupport characterDeathEvent;
  // El equivalente a Observable.
  // Pero en este caso es una propiedad o evento que puede cambiar en ICharacter.
  // A este evento le podemos agregar suscriptores (listeners) a los que se les informa cuando cambió

  private final String name;
  private int healthPoints;
  private final int defense;
  protected final BlockingQueue<ICharacter> turnsQueue;
  protected ScheduledExecutorService scheduledExecutor;
  protected boolean isAlive;

  /**
   * Constructor in common with all the characters of the game.
   *
   * @param name         the player character's name
   * @param healthPoints the player character's health points
   * @param defense      the player character's defense
   * @param turnsQueue   the queue with all the characters waiting for their turn
   */
  protected AbstractCharacter(@NotNull String name, int healthPoints, int defense, @NotNull BlockingQueue<ICharacter> turnsQueue) {
      characterDeathEvent = new PropertyChangeSupport(this); // Asociar evento de muerte a este personaje
      this.name = name;
      this.healthPoints = healthPoints;
      this.defense = defense;
      this.turnsQueue = turnsQueue;
      this.isAlive = true;
  }

  /**
   * Returns this character's name.
   */
  @Override
  public String getName() { return this.name; }

  /**
   * Returns this character's health points.
   */
  @Override
  public int getHealthPoints() {
        return this.healthPoints;
    }

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
  public int getDefense() {
        return this.defense;
    }

  /**
   * Returns a boolean value depending on the character condition
   * (true if the character is alive and false if the character is dead.)
   */
  @Override
  public boolean isAlive() {
      return this.isAlive;
  }

  /**
   * The character performs an attack
   */
  @Override
  public void attack(ICharacter character) {
      if (this.isAlive() && character.isAlive()) {
          character.attackedBy(this.getAttackDamage());
      }
  }

  /**
  * The character get damaged
  */
  @Override
  public void attackedBy(int damage) {
      int receivedDamage = damage - this.getDefense();
      int currentHealthPoints = this.getHealthPoints();

      if (receivedDamage < 0) { // There is no negative damage
          receivedDamage = 0; // set the dealt damage to 0
      }

      this.setHealthPoints(currentHealthPoints - receivedDamage); // the character is damaged with the received damage

      if(this.getHealthPoints() <= 0) { // If the new health points are less than or equal to 0
          this.isAlive = false;  // the character is dead
          characterDeathEvent.firePropertyChange("Character´s Death", null, this); // Notificar el cambio
      }
  }

    /**
     * Adds this character to the turns queue.
     */
    protected void addToQueue() {
        turnsQueue.add(this);
        scheduledExecutor.shutdown();
    }

    /**
     * Adds a subscriber to a PlayerCharacter's death event
     * (the subscriber is the PlayerCharacterDeathHandler)
     */
    public void addSubscriberForPlayerCharacter(PlayerCharacterDeathHandler playerCharacterDeathHandler) {
        characterDeathEvent.addPropertyChangeListener(playerCharacterDeathHandler);
    }

    /**
     * Adds a subscriber to a Enemy's death event
     * (the subscriber is the EnemyDeathHandler)
     */
    public void addSubscriberForEnemy(EnemyDeathHandler EnemyDeathHandler) {
        characterDeathEvent.addPropertyChangeListener(EnemyDeathHandler);
    }
}
