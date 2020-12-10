package com.github.salix07.finalreality.controller.eventHandler;

import com.github.salix07.finalreality.controller.GameController;

import java.beans.PropertyChangeEvent;

/**
 * PlayerCharacter's add to queue handler.
 * The PlayerCharacterAddToQueueHandler class work as a subscriber of PlayerCharacter's added to queue events
 *
 * @author Sebastián Salinas
 */
public class PlayerCharacterAddToQueueHandler implements IHandler { // Implementa la interfaz de Handlers
    private final GameController controller;

    /**
     * Constructor of PlayerCharacterAddToQueueHandler.
     * Association between the real subscriber a.k.a PlayerCharacter's add to queue handler, and the game controller
     */
    public PlayerCharacterAddToQueueHandler(GameController controller) {
        this.controller = controller;
    }

    /**
     * Override del método de la interfaz PropertyChangeListener.
     * Es llamado cuando se notifica de un cambio y recibe el evento que sufrió la modificación.
     * (En este caso lo que hace es llamar al método addNewTurn() del controller)
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        int turnType = 1; // 1 represents playerCharacter´s turns
        controller.addNewTurn(turnType);
    }
}
