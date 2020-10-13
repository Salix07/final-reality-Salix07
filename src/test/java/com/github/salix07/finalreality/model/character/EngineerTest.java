package com.github.salix07.finalreality.model.character;

import com.github.salix07.finalreality.model.character.player.Engineer;
import com.github.salix07.finalreality.model.character.player.Knight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

/**
 * Class containing the tests for the Engineers.
 *
 * @author Sebastián Salinas Rodriguez.
 */
public class EngineerTest extends AbstractPlayerCharacterTest{

    private static final String ENGINEER_NAME = "Feto Ingeniero";
    private static int HEALTHPOINTS = 300;
    private static final int DEFENSE = 100;

    private Engineer testEngineer;

    /**
     * Start the set up for the test.
     */
    @BeforeEach
    void setUp() {
        basicSetUp();
        testEngineer = new Engineer(ENGINEER_NAME, HEALTHPOINTS, DEFENSE, turns);
        testPlayerCharacters.add(testEngineer);
    }

    /**
     * Test that check the correct construction and equalities between Engineers.
     */
    @Test
    void contructorTest() {
        var expectedEngineer = new Engineer(ENGINEER_NAME, HEALTHPOINTS, DEFENSE, turns);
        var anotherEngineer1 = new Engineer("Yop", HEALTHPOINTS, DEFENSE, turns);
        var anotherEngineer2 = new Engineer(ENGINEER_NAME, 250, DEFENSE, turns);
        var anotherEngineer3 = new Engineer(ENGINEER_NAME, HEALTHPOINTS, 50, turns);
        var expectedKnight = new Knight(ENGINEER_NAME, HEALTHPOINTS, DEFENSE, turns);

        for (var character :
                testPlayerCharacters) {
            checkConstruction(expectedEngineer, character, anotherEngineer1, anotherEngineer2, anotherEngineer3, expectedKnight);
        }
    }

    /**
     * Test for the getters.
     */
    @Test
    void gettersTest() {
        assertEquals(ENGINEER_NAME, testEngineer.getName());
        assertEquals(DEFENSE, testEngineer.getDefense());
        assertEquals(HEALTHPOINTS, testEngineer.getHealthPoints());
    }

    /**
     * Test for the setters.
     */
    @Test
    void settersTest() {
        testEngineer.setHealthPoints(200);
        assertEquals(200, testEngineer.getHealthPoints());
    }
}
