package com.github.salix07.finalreality.model.character;

import com.github.salix07.finalreality.model.weapon.IWeapon;

/**
 * This represents a player character from the game.
 * A player character can equip weapons.
 *
 * @author Sebastián Salinas Rodriguez.
 */

public interface IPlayerCharacterEquipped {

    /**
     * Equips a weapon to the character.
     */
    void equip(IWeapon weapon);

    /**
     * Return this character's equipped weapon.
     */
    IWeapon getEquippedWeapon();
}
