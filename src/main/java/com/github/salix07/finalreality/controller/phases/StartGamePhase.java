package com.github.salix07.finalreality.controller.phases;

public class StartGamePhase extends Phase {
    @Override
    public void toWaitingForTurnPhase() {
        System.out.println("Cambio a fase de esperar turno");
        changePhase(new WaitingForTurnPhase());
    }

    @Override
    public boolean isStartGamePhase() {return true;}
}
