package com.github.salix07.finalreality.controller;

import java.beans.PropertyChangeListener;

/**
 * Character´s death handler.
 * The CharacterDeathHandler class work as a subscriber of character´s death events
 *
 * @author Sebastián Salinas
 */

public interface IDeathHandler extends PropertyChangeListener { // Implementa la interfaz que se suscribe a los eventos
}
