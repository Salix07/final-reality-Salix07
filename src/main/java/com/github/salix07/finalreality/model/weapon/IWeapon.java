package com.github.salix07.finalreality.model.weapon;

import com.github.salix07.finalreality.model.character.player.*;

/**
 * This represents a weapon from the game.
 *
 * @author Sebastián Salinas Rodriguez.
 */
public interface IWeapon {

    /**
     * Returns this weapon's name.
     */
    String getName();

    /**
     * Returns this weapon's damage.
     */
    int getDamage();

    /**
     * Returns this weapon's weight.
     */
    int getWeight();

    // All IWeapons will have a method to be equipped on a PlayerCharacter.
    // This methods will be call by the PlayerCharacter's equip method.
    // So only if the PlayerCharacter can be equipped with this IWeapon
    // the method will equip the PlayerCharacter.
    // Otherwise these methods will do nothing.

    /**
     * All IWeapons will have a method to be equipped on a BlackMage.
     */
    void equipToBlackMage(BlackMage blackMage);

    /**
     * All IWeapons will have a method to be equipped on a Engineer.
     */
    void equipToEngineer(Engineer engineer);

    /**
     * All IWeapons will have a method to be equipped on a Knight.
     */
    void equipToKnight(Knight knight);

    /**
     * All IWeapons will have a method to be equipped on a Thief.
     */
    void equipToThief(Thief thief);

    /**
     * All IWeapons will have a method to be equipped on a WhiteMage.
     */
    void equipToWhiteMage(WhiteMage whiteMage);
}
