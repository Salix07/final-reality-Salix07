package com.github.salix07.finalreality.controller.phases;

import com.github.salix07.finalreality.controller.GameController;

public class Phase {
    protected GameController controller;

    public void setGameController(GameController controller) {
        this.controller = controller;
    }

    protected void changePhase(Phase phase) {
        controller.setPhase(phase);
    }

    public void toWaitingForTurnPhase() {}
    public void toTurnPhase() {}
    public void toSelectingActionPhase() {}
    public void toGameOverPhase() {
        changePhase(new GameOverPhase());
    }

    public boolean isStartGamePhase() {return false;}
    public boolean isWaitingForTurnPhase() {return false;}
    public boolean isTurnPhase() {return  false;}
    public boolean isSelectingActionPhase() {return false;}
    public boolean isGameOverPhase() {return false;}
}
