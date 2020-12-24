package com.github.salix07.finalreality.controller.phases;

public class WaitingForTurnPhase extends Phase {
    @Override
    public void toTurnPhase() {
        System.out.println("Cambio a fase de turno");
        changePhase(new TurnPhase());
    }

    @Override
    public boolean isWaitingForTurnPhase() {return true;}

    @Override
    public String getName() { return "Waiting For Turn Phase";}
}
