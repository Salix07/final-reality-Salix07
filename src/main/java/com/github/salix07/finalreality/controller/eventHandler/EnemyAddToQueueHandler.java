package com.github.salix07.finalreality.controller.eventHandler;

import com.github.salix07.finalreality.controller.GameController;

import java.beans.PropertyChangeEvent;

/**
 * Enemy's add to queue handler.
 * The EnemyAddToQueueHandler class work as a subscriber of Enemy's added to queue events
 *
 * @author Sebastián Salinas
 */
public class EnemyAddToQueueHandler implements IHandler { // Implementa la interfaz de Handlers
    private final GameController controller;

    /**
     * Constructor of EnemyAddToQueueHandler.
     * Association between the real subscriber a.k.a Enemy's add to queue handler, and the game controller
     */
    public EnemyAddToQueueHandler(GameController controller) {
        this.controller = controller;
    }

    /**
     * Override del método de la interfaz PropertyChangeListener.
     * Es llamado cuando se notifica de un cambio y recibe el evento que sufrió la modificación.
     * (En este caso lo que hace es llamar al método addNewTurn() del controller)
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        int turnType = 0; // 0 represents enemy´s turns
        controller.addNewTurn(turnType);
    }
}
