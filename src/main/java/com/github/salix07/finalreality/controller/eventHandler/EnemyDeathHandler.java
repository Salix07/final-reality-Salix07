package com.github.salix07.finalreality.controller.eventHandler;

import com.github.salix07.finalreality.controller.GameController;
import com.github.salix07.finalreality.model.character.Enemy;

import java.beans.PropertyChangeEvent;

/**
 * Enemy´s death handler.
 * The EnemyDeathHandler class work as a subscriber of Enemy´s death events
 *
 * @author Sebastián Salinas
 */
public class EnemyDeathHandler implements IHandler { // Implementa la interfaz de Handlers
    private final GameController controller;

    /**
     * Constructor of EnemyDeathHandler.
     * Association between the real subscriber a.k.a Enemy's death handler, and the game controller
     */
    public EnemyDeathHandler(GameController controller) {
        this.controller = controller;
    }

    /**
     * Override del método de la interfaz PropertyChangeListener.
     * Es llamado cuando se notifica de un cambio y recibe el evento que sufrió la modificación.
     * (En este caso lo que hace es llamar al método onEnemyDeath del controller)
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        controller.onEnemyDeath();
    }
}
