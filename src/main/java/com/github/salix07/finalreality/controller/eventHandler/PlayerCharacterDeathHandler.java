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
public class PlayerCharacterDeathHandler implements IHandler { // Implementa la interfaz de Handlers
    private final GameController controller;

    /**
     * Constructor of PlayerCharacterDeathHandler.
     * Association between the real subscriber a.k.a PlayerCharacter's death handler, and the game controller
     */
    public PlayerCharacterDeathHandler(GameController controller) {
        this.controller = controller;
    }

    /**
     * Override del método de la interfaz PropertyChangeListener.
     * Es llamado cuando se notifica de un cambio y recibe el evento que sufrió la modificación.
     * (En este caso lo que hace es llamar al método onPlayerCharacterDeath del controller)
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        controller.onPlayerCharacterDeath((IPlayerCharacter) evt.getNewValue());
    }
}
