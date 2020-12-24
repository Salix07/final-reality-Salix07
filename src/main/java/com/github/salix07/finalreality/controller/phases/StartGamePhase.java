package com.github.salix07.finalreality.controller.phases;

import com.github.salix07.finalreality.model.character.player.IPlayerCharacter;
import com.github.salix07.finalreality.model.weapon.IWeapon;

public class StartGamePhase extends Phase {
    @Override
    public void toWaitingForTurnPhase() {
        System.out.println("Cambio a fase de esperar turno");
        changePhase(new WaitingForTurnPhase());
    }

    @Override
    public boolean isStartGamePhase() {return true;}

    @Override
    public void tryToStartGame() {controller.startGame();}

    @Override
    public void tryToEquipPlayerCharacter(IPlayerCharacter playerCharacter, IWeapon weapon) {
        controller.equipPlayerCharacter(playerCharacter, weapon);
    }

    @Override
    public String getName() { return "Start Game Phase";}
}
