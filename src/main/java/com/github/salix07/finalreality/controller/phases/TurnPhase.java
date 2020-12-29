package com.github.salix07.finalreality.controller.phases;

/**
 * TurnPhase, the phase where turns can start.
 *
 * @author Sebastián Salinas
 */
public class TurnPhase extends Phase {

    /**
     * We are on TurnPhase, so when a turn start it could be player's one
     * Then we can change to PlayerSelectingActionPhase
     */
    @Override
    public void toPlayerSelectingActionPhase() {
        changePhase(new PlayerSelectingActionPhase());
    }

    /**
     * We are on TurnPhase, so when a turn start it could be enemy's one
     * Then we can change to EnemyActionPhase
     */
    @Override
    public void toEnemyActionPhase() {
        changePhase(new EnemyActionPhase());
    }

    /**
     * We are on TurnPhase, so when a turn start the turn owner could be dead
     * Then we can change to WaitingForTurnPhase to wait for a new turn
     */
    @Override
    public void toWaitingForTurnPhase() {
        changePhase(new WaitingForTurnPhase());
    }

    /**
     * We are on TurnPhase, then this function returns true
     */
    @Override
    public boolean isTurnPhase() {return true;}

    /**
     * We are on TurnPhase, then we can begin a turn
     */
    @Override
    public void tryToBeginTurn() {controller.beginTurn();}

    /**
     * Returns the name of the phase
     */
    @Override
    public String getName() {return "Turn Phase";}
}