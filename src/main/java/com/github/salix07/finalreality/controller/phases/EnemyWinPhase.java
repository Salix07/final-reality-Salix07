package com.github.salix07.finalreality.controller.phases;

/**
 * EnemyWinPhase, the phase where the winner team is the enemy
 *
 * @author Sebastián Salinas
 */
public class EnemyWinPhase extends Phase {

    /**
     * We are on EnemyWinPhase, then this function returns true
     */
    @Override
    public boolean isEnemyWinPhase() {return true;}

    /**
     * Returns the name of the phase
     */
    @Override
    public String getName() {return "Enemy Win Phase";}
}
