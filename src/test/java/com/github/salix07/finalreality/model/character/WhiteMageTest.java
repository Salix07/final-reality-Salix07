package com.github.salix07.finalreality.model.character;

import com.github.salix07.finalreality.model.character.player.BlackMage;
import com.github.salix07.finalreality.model.character.player.WhiteMage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

/**
 * Class containing the tests for the White Mages.
 *
 * @author Sebastián Salinas Rodriguez.
 */
public class WhiteMageTest extends AbstractPlayerCharacterTest{

    private static final String WhiteMAGE_NAME = "Lux";
    private static int HEALTHPOINTS = 300;
    private static final int DEFENSE = 100;
    private static int MANA = 150;

    private WhiteMage testWhiteMage;

    /**
     * Start the set up for the test.
     */
    @BeforeEach
    void setUp() {
        basicSetUp();
        testWhiteMage = new WhiteMage(WhiteMAGE_NAME, HEALTHPOINTS, DEFENSE, MANA, turns);
        testPlayerCharacters.add(testWhiteMage);
    }

    /**
     * Test that check the correct construction and equalities between White Mages.
     */
    @Test
    void contructorTest() {
        var expectedWhiteMage = new WhiteMage(WhiteMAGE_NAME, HEALTHPOINTS, DEFENSE, MANA, turns);
        var anotherWhiteMage1 = new WhiteMage("Ahri", HEALTHPOINTS, DEFENSE, MANA, turns);
        var anotherWhiteMage2 = new WhiteMage(WhiteMAGE_NAME, 250, DEFENSE, MANA, turns);
        var anotherWhiteMage3 = new WhiteMage(WhiteMAGE_NAME, HEALTHPOINTS, 50, MANA, turns);
        var anotherWhiteMage4 = new WhiteMage(WhiteMAGE_NAME, HEALTHPOINTS, DEFENSE, 120, turns);
        var expectedBlackMage = new BlackMage(WhiteMAGE_NAME, HEALTHPOINTS, DEFENSE, MANA, turns);

        for (var character :
                testPlayerCharacters) {
            checkConstruction(expectedWhiteMage, character, anotherWhiteMage1, anotherWhiteMage2, anotherWhiteMage3, expectedBlackMage);
            assertTrue(character.equals(anotherWhiteMage4));
        }
    }

    /**
     * Test for the getters.
     */
    @Test
    void gettersTest() {
        assertEquals(WhiteMAGE_NAME, testWhiteMage.getName());
        assertEquals(DEFENSE, testWhiteMage.getDefense());
        assertEquals(HEALTHPOINTS, testWhiteMage.getHealthPoints());
        assertEquals(MANA, testWhiteMage.getMana());
    }

    /**
     * Test for the setters.
     */
    @Test
    void settersTest() {
        testWhiteMage.setMana(75);
        testWhiteMage.setHealthPoints(200);
        assertEquals(75, testWhiteMage.getMana());
        assertEquals(200, testWhiteMage.getHealthPoints());
    }
}
