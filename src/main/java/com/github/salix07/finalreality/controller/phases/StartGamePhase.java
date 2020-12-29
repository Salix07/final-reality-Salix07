package com.github.salix07.finalreality.controller.phases;

import com.github.salix07.finalreality.model.character.player.IPlayerCharacter;
import com.github.salix07.finalreality.model.weapon.IWeapon;

/**
 * StartGamePhase, the first phase of the game, where the user is making the set up of the game.
 *
 * @author Sebastián Salinas
 */
public class StartGamePhase extends Phase {

    /**
     * We are on StartGamePhase, so when the game begins
     * we can change the StartGamePhase to the WaitingForTurnPhase
     */
    @Override
    public void toWaitingForTurnPhase() {
        changePhase(new WaitingForTurnPhase());
    }

    /**
     * We are on StartGamePhase, then this function returns true
     */
    @Override
    public boolean isStartGamePhase() {return true;}

    /**
     * We are on StartGamePhase, then we can start the game
     */
    @Override
    public void tryToStartGame() {controller.startGame();}

    /**
     * We are on StartGamePhase, then we can equip the characters
     */
    @Override
    public void tryToEquipPlayerCharacter(IPlayerCharacter playerCharacter, IWeapon weapon) {
        controller.equipPlayerCharacter(playerCharacter, weapon);
    }

    /**
     * Returns the name of the phase
     */
    @Override
    public String getName() {return "Start Game Phase";}
}
