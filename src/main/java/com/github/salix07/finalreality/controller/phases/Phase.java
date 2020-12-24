package com.github.salix07.finalreality.controller.phases;

import com.github.salix07.finalreality.controller.GameController;
import com.github.salix07.finalreality.model.character.ICharacter;
import com.github.salix07.finalreality.model.character.player.IPlayerCharacter;
import com.github.salix07.finalreality.model.weapon.IWeapon;

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
    public void toGameOverPhase() {}

    public boolean isStartGamePhase() {return false;}
    public boolean isWaitingForTurnPhase() {return false;}
    public boolean isTurnPhase() {return  false;}
    public boolean isSelectingActionPhase() {return false;}
    public boolean isGameOverPhase() {return false;}

    public void tryToStartGame() { }
    public void tryToEquipPlayerCharacter(IPlayerCharacter playerCharacter, IWeapon weapon) { }
    public void tryToBeginTurn() {}
    public void tryToAttackCharacter(ICharacter attackerCharacter, ICharacter attackedCharacter) { }

    public String getName() { return "";}
}
