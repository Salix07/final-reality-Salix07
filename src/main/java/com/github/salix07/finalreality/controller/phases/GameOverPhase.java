package com.github.salix07.finalreality.controller.phases;

public class GameOverPhase extends Phase {
    @Override
    public boolean isGameOverPhase() {return true; }

    @Override
    public String getName() { return "Game Over Phase";}
}
