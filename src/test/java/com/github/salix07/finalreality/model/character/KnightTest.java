package com.github.salix07.finalreality.model.character;

import com.github.salix07.finalreality.model.character.player.Knight;
import com.github.salix07.finalreality.model.character.player.Thief;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

/**
 * Class containing the tests for the Knights.
 *
 * @author Sebastián Salinas Rodriguez.
 */
public class KnightTest extends AbstractPlayerCharacterTest{

    private static final String KNIGHT_NAME = "Garen";
    private static int HEALTHPOINTS = 300;
    private static final int DEFENSE = 100;

    private Knight testKnight;

    /**
     * Start the set up for the test.
     */
    @BeforeEach
    void setUp() {
        basicSetUp();
        testKnight = new Knight(KNIGHT_NAME, HEALTHPOINTS, DEFENSE, turns);
        testPlayerCharacters.add(testKnight);
    }

    /**
     * Test that check the correct construction and equalities between Knights.
     */
    @Test
    void contructorTest() {
        var expectedKnight = new Knight(KNIGHT_NAME, HEALTHPOINTS, DEFENSE, turns);
        var anotherKnight1 = new Knight("Darius", HEALTHPOINTS, DEFENSE, turns);
        var anotherKnight2 = new Knight(KNIGHT_NAME, 250, DEFENSE, turns);
        var anotherKnight3 = new Knight(KNIGHT_NAME, HEALTHPOINTS, 50, turns);
        var expectedThief = new Thief(KNIGHT_NAME, HEALTHPOINTS, DEFENSE, turns);

        for (var character :
                testPlayerCharacters) {
            checkConstruction(expectedKnight, character, anotherKnight1, anotherKnight2, anotherKnight3, expectedThief);
        }
    }

    /**
     * Test for the getters.
     */
    @Test
    void gettersTest() {
        assertEquals(KNIGHT_NAME, testKnight.getName());
        assertEquals(DEFENSE, testKnight.getDefense());
        assertEquals(HEALTHPOINTS, testKnight.getHealthPoints());
    }

    /**
     * Test for the setters.
     */
    @Test
    void settersTest() {
        testKnight.setHealthPoints(200);
        assertEquals(200, testKnight.getHealthPoints());
    }
}
