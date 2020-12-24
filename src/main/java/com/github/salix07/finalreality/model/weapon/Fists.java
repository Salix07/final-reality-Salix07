package com.github.salix07.finalreality.model.weapon;

import com.github.salix07.finalreality.model.character.player.*;

/**
 * A class that holds all the information of Fists (Null Object Patter for weapons).
 *
 * @author Sebastián Salinas Rodriguez.
 */
public class Fists extends AbstractWeapon {
    private static Fists fistsUniqueInstance;
    private Fists() {
        super("Fists", 0, 0);
    }

    public static Fists getFistsUniqueInstance() {
        if(fistsUniqueInstance == null) {
            fistsUniqueInstance = new Fists();
        }
        return fistsUniqueInstance;
    }

    /**
     * The Fists "can be equipped" on all IPlayerCharacters.
     */
    @Override
    public void equipToBlackMage(BlackMage blackMage) {
        blackMage.setEquippedWeapon(this);
    }
    @Override
    public void equipToEngineer(Engineer engineer) {
        engineer.setEquippedWeapon(this);
    }
    @Override
    public void equipToKnight(Knight knight) {
        knight.setEquippedWeapon(this);
    }
    @Override
    public void equipToThief(Thief thief) { thief.setEquippedWeapon(this); }
    @Override
    public void equipToWhiteMage(WhiteMage whiteMage) {
        whiteMage.setEquippedWeapon(this);
    }

}
