package com.github.salix07.finalreality.controller.eventHandler;

import com.github.salix07.finalreality.controller.GameController;

import java.beans.PropertyChangeEvent;

/**
 * ICharacter's add to queue handler.
 * The ICharacterAddToQueueHandler class work as a subscriber of ICharacter's added to queue events
 *
 * @author Sebastián Salinas
 */
public class ICharacterAddToQueueHandler implements IHandler { // Implements the IHandler interface
    private final GameController controller;

    /**
     * Constructor of ICharacterAddToQueueHandler.
     * Association between the real subscriber a.k.a ICharacter's add to queue handler, and the game controller
     */
    public ICharacterAddToQueueHandler(GameController controller) {
        this.controller = controller;
    }

    /**
     * Override of the PropertyChangeListener interface method.
     * It is called when a change is notified, receives the event that suffered the modification.
     * (In this case what it does is call the controller's tryToTakeTurn method)
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        controller.tryToTakeTurn();
    }
}
