package com.github.salix07.finalreality.controller.eventHandler;

import com.github.salix07.finalreality.controller.GameController;
import com.github.salix07.finalreality.model.character.ICharacter;
import com.github.salix07.finalreality.model.character.player.IPlayerCharacter;

import java.beans.PropertyChangeEvent;

/**
 * ICharacter's add to queue handler.
 * The ICharacterAddToQueueHandler class work as a subscriber of ICharacter's added to queue events
 *
 * @author Sebastián Salinas
 */
public class ICharacterAddToQueueHandler implements IHandler { // Implementa la interfaz de Handlers
    private final GameController controller;

    /**
     * Constructor of ICharacterAddToQueueHandler.
     * Association between the real subscriber a.k.a ICharacter's add to queue handler, and the game controller
     */
    public ICharacterAddToQueueHandler(GameController controller) {
        this.controller = controller;
    }

    /**
     * Override del método de la interfaz PropertyChangeListener.
     * Es llamado cuando se notifica de un cambio y recibe el evento que sufrió la modificación.
     * (En este caso lo que hace es llamar al método addNewTurn() del controller)
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        controller.tryToTakeTurn();
    }
}
