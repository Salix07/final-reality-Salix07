package com.github.salix07.finalreality.controller.phases;

public class TurnPhase extends Phase {
    @Override
    public void toSelectingActionPhase() {
        System.out.println("Cambio a fase de seleccionar accion");
        changePhase(new SelectingActionPhase());
    }

    @Override
    public boolean isTurnPhase() {return true;}
}