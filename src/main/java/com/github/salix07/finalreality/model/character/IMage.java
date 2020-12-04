package com.github.salix07.finalreality.model.character;

/**
 * This represents a mage from the game.
 * Mages have mana.
 *
 * @author Sebastián Salinas Rodriguez.
 */
public interface IMage {
    /**
     * Returns this Mage's mana.
     */
    int getMana();

    /**
     * Set this Mage's mana to the parameter passed.
     */
    void setMana(int mana);
}
