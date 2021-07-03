package com.github.salistito.finalreality.model.controller;

import com.github.salistito.finalreality.controller.GameController;
import com.github.salistito.finalreality.model.character.Enemy;
import com.github.salistito.finalreality.model.character.player.IPlayerCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Class containing the common tests for controller´s teams
 *
 * @author Sebastián Salinas Rodriguez.
 *
 */
public class ControllerPartyTest {
    GameController controller;

    ArrayList<IPlayerCharacter> playerCharacters;
    ArrayList<Enemy> enemies;

    IPlayerCharacter playerCharacter0;
    IPlayerCharacter playerCharacter1;
    IPlayerCharacter playerCharacter2;
    IPlayerCharacter playerCharacter3;
    IPlayerCharacter playerCharacter4;
    Enemy enemy;

    /**
     * Basic set up for the party test
     */
    @BeforeEach
    void setUp() {
        controller = new GameController(5,1,0);

        playerCharacters = controller.getPlayerCharacters();
        enemies = controller.getEnemies();
    }

    @Test
    void getICharacter() {
        // At the beginning the arrays are empty
        assertTrue(playerCharacters.isEmpty());
        assertTrue(enemies.isEmpty());
        // ICharacter creation
        controller.createBlackMage("Morgana", 30, 5, 30);
        controller.createEngineer("Feto Ingeniero", 30, 5);
        controller.createKnight("Jarvan", 30, 5);
        controller.createThief("José Piñera Echeñique", 30, 5);
        controller.createWhiteMage("Lux", 30, 5, 30);
        controller.createEnemy("Goblin", 30, 3, 10, 5);
        // Now the arrays are not empty
        assertFalse(playerCharacters.isEmpty());
        assertFalse(enemies.isEmpty());
        //And the arrays must be of a size congruent to the creations of ICharacters
        assertEquals(5, playerCharacters.size());
        assertEquals(1, enemies.size());

        // Intentar crear ICharacter y Enemy, sobre pasando el tamaño de playerCharacters y enemies
        controller.createBlackMage("Veigar", 30, 5, 30);
        controller.createEnemy("Tank Goblin", 50, 7, 7, 10);
        assertEquals(5, playerCharacters.size());
        assertEquals(1, enemies.size());

        // Check if the characters from the playerCharacters array are equals to the characters from the method getPlayerCharacter
        // and if the enemy from the enemies arrays is equal to the enemy from the method getEnemy
        playerCharacter0 = controller.getPlayerCharacter(0);
        playerCharacter1 = controller.getPlayerCharacter(1);
        playerCharacter2 = controller.getPlayerCharacter(2);
        playerCharacter3 = controller.getPlayerCharacter(3);
        playerCharacter4 = controller.getPlayerCharacter(4);
        enemy = controller.getEnemy(0);

        assertEquals(playerCharacters.get(0), playerCharacter0);
        assertEquals(playerCharacters.get(1), playerCharacter1);
        assertEquals(playerCharacters.get(2), playerCharacter2);
        assertEquals(playerCharacters.get(3), playerCharacter3);
        assertEquals(playerCharacters.get(4), playerCharacter4);
        assertEquals(enemies.get(0), enemy);
    }
}
