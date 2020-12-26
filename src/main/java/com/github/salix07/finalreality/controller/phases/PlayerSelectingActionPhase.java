package com.github.salix07.finalreality.controller.phases;

import com.github.salix07.finalreality.model.character.ICharacter;
import com.github.salix07.finalreality.model.character.player.IPlayerCharacter;
import com.github.salix07.finalreality.model.weapon.IWeapon;

public class PlayerSelectingActionPhase extends Phase {
    @Override
    public void toWaitingForTurnPhase() {
        System.out.println("Fase de esperar el turno");
        changePhase(new WaitingForTurnPhase());
    }

    @Override
    public void toGameOverPhase() {
        System.out.println("Fase final del juego");
        changePhase(new GameOverPhase());
    }

    @Override
    public boolean isPlayerSelectingActionPhase() {return true;}

    @Override
    public void tryToEquipPlayerCharacter(IPlayerCharacter playerCharacter, IWeapon weapon) {
        controller.equipPlayerCharacter(playerCharacter, weapon);
    }

    @Override
    public void tryToAttackCharacter(ICharacter attackerCharacter, ICharacter attackedCharacter) {
        controller.attackCharacter(attackerCharacter, attackedCharacter);
    }

    @Override
    public String getName() { return "Player Selecting Action Phase";}
}
