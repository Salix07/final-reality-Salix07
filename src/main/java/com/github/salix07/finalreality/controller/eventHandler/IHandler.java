package com.github.salix07.finalreality.controller.eventHandler;

import java.beans.PropertyChangeListener;

/**
 * Interface off handlers for our game.
 * The classes that extends this interface works as a subscriber of character´s events
 * @author Sebastián Salinas
 */

public interface IHandler extends PropertyChangeListener { // Implementa la interfaz que se suscribe a los eventos
}
