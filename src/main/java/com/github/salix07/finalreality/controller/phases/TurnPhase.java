package com.github.salix07.finalreality.controller.phases;

public class TurnPhase extends Phase {
    @Override
    public void toPlayerSelectingActionPhase() {
        System.out.println("Cambio a fase de jugador seleccionando accion");
        changePhase(new PlayerSelectingActionPhase());
    }

    @Override
    public void toEnemyActionPhase() {
        System.out.println("Cambio a fase de accion enemiga");
        changePhase(new EnemyActionPhase());
    }

    @Override
    public boolean isTurnPhase() {return true;}

    @Override
    public void tryToBeginTurn() {controller.beginTurn();}

    @Override
    public String getName() { return "Turn Phase";}
}