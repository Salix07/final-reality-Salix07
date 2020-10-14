package com.github.salix07.finalreality.model.character;

import com.github.salix07.finalreality.model.character.player.Thief;
import com.github.salix07.finalreality.model.character.player.WhiteMage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

/**
 * Class containing the tests for the Thiefs.
 *
 * @author Sebastián Salinas Rodriguez.
 */
public class ThiefTest extends AbstractPlayerCharacterTest{

    private static final String THIEF_NAME = "José Piñera Echenique";
    private static int HEALTHPOINTS = 300;
    private static final int DEFENSE = 100;

    private Thief testThief;

    /**
     * Start the set up for the test.
     */
    @BeforeEach
    void setUp() {
        basicSetUp();
        testThief = new Thief(THIEF_NAME, HEALTHPOINTS, DEFENSE, turns);
        testPlayerCharacters.add(testThief);
    }

    /**
     * Test that check the correct construction and equalities between Thiefs.
     */
    @Test
    void contructorTest() {
        var expectedThief = new Thief(THIEF_NAME, HEALTHPOINTS, DEFENSE, turns);
        var anotherThief1 = new Thief("Sebastán Piñera Echeñique", HEALTHPOINTS, DEFENSE, turns);
        var anotherThief2 = new Thief(THIEF_NAME, 250, DEFENSE, turns);
        var anotherThief3 = new Thief(THIEF_NAME, HEALTHPOINTS, 50, turns);
        var expectedWhiteMage = new WhiteMage(THIEF_NAME, HEALTHPOINTS, DEFENSE, 100, turns);

        for (var character :
                testPlayerCharacters) {
            checkConstruction(expectedThief, character, anotherThief1, anotherThief2, anotherThief3, expectedWhiteMage);
        }
    }

    /**
     * Test for the getters.
     */
    @Test
    void gettersTest() {
        assertEquals(THIEF_NAME, testThief.getName());
        assertEquals(DEFENSE, testThief.getDefense());
        assertEquals(HEALTHPOINTS, testThief.getHealthPoints());
    }

    /**
     * Test for the setters.
     */
    @Test
    void settersTest() {
        testThief.setHealthPoints(200);
        assertEquals(200, testThief.getHealthPoints());
    }
}
