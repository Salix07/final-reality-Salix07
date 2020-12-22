package com.github.salix07.finalreality.controller.phases;

public class SelectingActionPhase extends Phase {
    @Override
    public void toWaitingForTurnPhase() {
        System.out.println("Fase de esperar el turno");
        changePhase(new WaitingForTurnPhase());
    }

    @Override
    public boolean isSelectingActionPhase() {return true;}
}
