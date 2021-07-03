package com.github.salistito.finalreality.controller.phases;

/**
 * PlayerWinPhase, the phase where the winner team is the player's one
 *
 * @author Sebastián Salinas
 */
public class PlayerWinPhase extends Phase {

    /**
     * We are on PlayerWinPhase, then this function returns true
     */
    @Override
    public boolean isPlayerWinPhase() {return true;}

    /**
     * Returns the name of the phase
     */
    @Override
    public String getName() {return "Player Win Phase";}
}

