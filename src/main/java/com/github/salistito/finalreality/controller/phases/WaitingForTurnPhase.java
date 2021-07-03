package com.github.salistito.finalreality.controller.phases;

/**
 * WaitingForTurnPhase,
 * It is the second phase of the game
 * or the phase between a end of turn and the beginning of a new one.
 *
 * @author Sebastián Salinas
 */
public class WaitingForTurnPhase extends Phase {

    /**
     * We are on WaitingForTurnPhase, so when a turn begin
     * we can change the WaitingForTurnPhase to the TurnPhase
     */
    @Override
    public void toTurnPhase() {
        changePhase(new TurnPhase());
    }

    /**
     * We are on WaitingForTurnPhase, then this function returns true
     */
    @Override
    public boolean isWaitingForTurnPhase() {return true;}

    /**
     * Returns the name of the phase
     */
    @Override
    public String getName() {return "Waiting For Turn Phase";}
}
