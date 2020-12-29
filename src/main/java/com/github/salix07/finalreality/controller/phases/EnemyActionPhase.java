package com.github.salix07.finalreality.controller.phases;

import com.github.salix07.finalreality.model.character.ICharacter;

/**
 * EnemyActionPhase, the phase where enemies do their actions (like attack).
 *
 * @author Sebastián Salinas
 */
public class EnemyActionPhase extends Phase {

    /**
     * We are on EnemyActionPhase, so when a enemy do his action
     * his turn end, then we can change to WaitingForTurnPhase
     */
    @Override
    public void toWaitingForTurnPhase() {
        changePhase(new WaitingForTurnPhase());
    }

    /**
     * We are on EnemyActionPhase, so a enemy could make an action like kill the last player character
     * then the enemy team win and we can change to EnemyWinPhase
     */
    @Override
    public void toEnemyWinPhase() {
        changePhase(new EnemyWinPhase());
    }

    /**
     * We are on EnemyActionPhase, then this function returns true
     */
    @Override
    public boolean isEnemyActionPhase() {return true;}

    /**
     * We are on EnemyActionPhase, enemies can attack
     */
    @Override
    public void tryToAttackCharacter(ICharacter attackerCharacter, ICharacter attackedCharacter) {
        controller.attackCharacter(attackerCharacter, attackedCharacter);
    }

    /**
     * Returns the name of the phase
     */
    @Override
    public String getName() {return "Enemy Action Phase";}
}
