package com.github.salix07.finalreality.controller.phases;

import com.github.salix07.finalreality.model.character.ICharacter;
import com.github.salix07.finalreality.model.character.player.IPlayerCharacter;
import com.github.salix07.finalreality.model.weapon.IWeapon;

public class EnemyActionPhase extends Phase {
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
    public boolean isEnemyActionPhase() {return true;}

    @Override
    public void tryToAttackCharacter(ICharacter attackerCharacter, ICharacter attackedCharacter) {
        controller.attackCharacter(attackerCharacter, attackedCharacter);
    }

    @Override
    public String getName() { return "Enemy Action Phase";}
}
