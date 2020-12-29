package com.github.salix07.finalreality.controller.eventHandler;

import com.github.salix07.finalreality.controller.GameController;
import com.github.salix07.finalreality.model.character.player.IPlayerCharacter;

import java.beans.PropertyChangeEvent;

/**
 * PlayerCharacter´s death handler.
 * The PlayerCharacterDeathHandler class work as a subscriber of PlayerCharacter´s death events
 *
 * @author Sebastián Salinas
 */
public class PlayerCharacterDeathHandler implements IHandler { // Implements the IHandler interface
    private final GameController controller;

    /**
     * Constructor of PlayerCharacterDeathHandler.
     * Association between the real subscriber a.k.a PlayerCharacter's death handler, and the game controller
     */
    public PlayerCharacterDeathHandler(GameController controller) {
        this.controller = controller;
    }

    /**
     * Override of the PropertyChangeListener interface method.
     * It is called when a change is notified, receives the event that suffered the modification.
     * (In this case what it does is call the controller's onPlayerCharacterDeath method)
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        controller.onPlayerCharacterDeath((IPlayerCharacter) evt.getNewValue());
    }
}
