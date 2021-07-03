package com.github.salistito.finalreality.controller.phases;

import com.github.salistito.finalreality.controller.GameController;
import com.github.salistito.finalreality.model.weapon.IWeapon;
import com.github.salistito.finalreality.model.character.ICharacter;
import com.github.salistito.finalreality.model.character.player.IPlayerCharacter;

/**
 * This represents the phases of the game.
 * We use state pattern to represent this phases.
 *
 * @author Sebastián Salinas
 */
public class Phase {
    protected GameController controller;

    // Association with the game controller
    public void setGameController(GameController controller) {
        this.controller = controller;
    }

    // Function than change the actual phase to another
    protected void changePhase(Phase phase) {
        controller.setPhase(phase);
    }

    // Functions than can change tha actual phase to the phase that the name of the function says
    public void toWaitingForTurnPhase() {}
    public void toTurnPhase() {}
    public void toPlayerSelectingActionPhase() {}
    public void toEnemyActionPhase() {}
    public void toPlayerWinPhase() {}
    public void toEnemyWinPhase() {}

    // Functions that returns a boolean value depending on the game phase
    public boolean isStartGamePhase() {return false;}
    public boolean isWaitingForTurnPhase() {return false;}
    public boolean isTurnPhase() {return  false;}
    public boolean isPlayerSelectingActionPhase() {return false;}
    public boolean isEnemyActionPhase() {return false;}
    public boolean isPlayerWinPhase() {return false;}
    public boolean isEnemyWinPhase() {return false;}

    // Functions than do something only if the game is in the correct phase
    public void tryToStartGame() { }
    public void tryToBeginTurn() {}
    public void tryToEquipPlayerCharacter(IPlayerCharacter playerCharacter, IWeapon weapon) { }
    public void tryToAttackCharacter(ICharacter attackerCharacter, ICharacter attackedCharacter) { }

    /**
     * Returns the name of the phase
     */
    public String getName() {return "";}
}
