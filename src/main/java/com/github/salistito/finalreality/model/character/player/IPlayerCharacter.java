package com.github.salistito.finalreality.model.character.player;

import com.github.salistito.finalreality.model.weapon.IWeapon;
import com.github.salistito.finalreality.model.character.ICharacter;

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
