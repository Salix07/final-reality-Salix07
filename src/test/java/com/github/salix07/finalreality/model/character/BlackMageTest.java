package com.github.salix07.finalreality.model.character;


import com.github.salix07.finalreality.model.character.player.BlackMage;
import com.github.salix07.finalreality.model.character.player.Engineer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

/**
 * Class containing the tests for the Black Mages.
 *
 * @author Sebastián Salinas Rodriguez.
 */
public class BlackMageTest extends AbstractPlayerCharacterTest{

    private static final String BLACKMAGE_NAME = "Morgana";
    private static int HEALTHPOINTS = 300;
    private static final int DEFENSE = 100;
    private static int MANA = 150;

    private BlackMage testBlackMage;

    /**
     * Start the set up for the test.
     */
    @BeforeEach
    void setUp() {
        basicSetUp();
        testBlackMage = new BlackMage(BLACKMAGE_NAME, HEALTHPOINTS, DEFENSE, MANA, turns);
        testPlayerCharacters.add(testBlackMage);
    }

    /**
     * Test that check the correct construction and equalities between Black Mages.
     */
    @Test
    void contructorTest() {
        var expectedBlackMage = new BlackMage(BLACKMAGE_NAME, HEALTHPOINTS, DEFENSE, MANA, turns);
        var anotherBlackMage1 = new BlackMage("Veigar", HEALTHPOINTS, DEFENSE, MANA, turns);
        var anotherBlackMage2 = new BlackMage(BLACKMAGE_NAME, 250, DEFENSE, MANA, turns);
        var anotherBlackMage3 = new BlackMage(BLACKMAGE_NAME, HEALTHPOINTS, 50, MANA, turns);
        var anotherBlackMage4 = new BlackMage(BLACKMAGE_NAME, HEALTHPOINTS, DEFENSE, 120, turns);
        var expectedEngineer = new Engineer(BLACKMAGE_NAME, HEALTHPOINTS, DEFENSE, turns);

        for (var character :
                testPlayerCharacters) {
            checkConstruction(expectedBlackMage, character, anotherBlackMage1, anotherBlackMage2, anotherBlackMage3, expectedEngineer);
            assertTrue(character.equals(anotherBlackMage4));
        }
    }

    /**
     * Test for the getters.
     */
    @Test
    void gettersTest() {
        assertEquals(BLACKMAGE_NAME, testBlackMage.getName());
        assertEquals(DEFENSE, testBlackMage.getDefense());
        assertEquals(HEALTHPOINTS, testBlackMage.getHealthPoints());
        assertEquals(MANA, testBlackMage.getMana());
    }

    /**
     * Test for the setters.
     */
    @Test
    void settersTest() {
        testBlackMage.setMana(75);
        testBlackMage.setHealthPoints(200);
        assertEquals(75, testBlackMage.getMana());
        assertEquals(200, testBlackMage.getHealthPoints());
    }
}
