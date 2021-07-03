package com.github.salistito.finalreality.controller.phases;

import com.github.salistito.finalreality.model.weapon.IWeapon;
import com.github.salistito.finalreality.model.character.ICharacter;
import com.github.salistito.finalreality.model.character.player.IPlayerCharacter;

/**
 * PlayerSelectingActionPhase, the phase where the player can decide what action do
 *
 * @author Sebastián Salinas
 */
public class PlayerSelectingActionPhase extends Phase {

    /**
     * We are on PlayerSelectingActionPhase, so when the player do his action
     * his turn end, then we can change to WaitingForTurnPhase
     */
    @Override
    public void toWaitingForTurnPhase() {
        changePhase(new WaitingForTurnPhase());
    }

    /**
     * We are on PlayerSelectingActionPhase, so the player could make an action like kill
     * the last enemy, then the player wins and we can change to PlayerWinPhase
     */
    @Override
    public void toPlayerWinPhase() {
        changePhase(new PlayerWinPhase());
    }

    /**
     * We are on PlayerSelectingActionPhase, then this function returns true
     */
    @Override
    public boolean isPlayerSelectingActionPhase() {return true;}

    /**
     * We are on PlayerSelectingActionPhase, the player can attack
     */
    @Override
    public void tryToEquipPlayerCharacter(IPlayerCharacter playerCharacter, IWeapon weapon) {
        controller.equipPlayerCharacter(playerCharacter, weapon);
    }

    /**
     * We are on PlayerSelecting Action Phase, the player can change his characters weapons
     */
    @Override
    public void tryToAttackCharacter(ICharacter attackerCharacter, ICharacter attackedCharacter) {
        controller.attackCharacter(attackerCharacter, attackedCharacter);
    }

    /**
     * Returns the name of the phase
     */
    @Override
    public String getName() {return "Player Selecting Action Phase";}
}
