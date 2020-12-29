package com.github.salix07.finalreality.model.character.player;

import com.github.salix07.finalreality.model.character.ICharacter;
import com.github.salix07.finalreality.model.weapon.IWeapon;

/**
 * This represents a player character from the game.
 * A player character can equip weapons.
 *
 * @author Sebastián Salinas Rodriguez.
 */
public interface IPlayerCharacter extends ICharacter {

    /**
     * Equips a weapon to the player character using Double Dispatch.
     */
    void equip(IWeapon weapon);

    /**
     * Return this player character's equipped weapon.
     */
    IWeapon getEquippedWeapon();

    /**
     * Set this player character's weapon to the passed parameter.
     */
    void setEquippedWeapon(IWeapon weapon);
}
