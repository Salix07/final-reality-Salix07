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
    private static final int DEFENSE = 100;

    private BlackMage testBlackMage;

    /**
     * Start the set up for the test.
     */
    @BeforeEach
    void setUp() {
        basicSetUp();
        testBlackMage = new BlackMage(BLACKMAGE_NAME, 300, DEFENSE, 150, turns);
        testPlayerCharacters.add(testBlackMage);
    }

    /**
     * Test that check the correct construction and equalities between Black Mages.
     */
    @Test
    void contructorTest() {
        var expectedBlackMage = new BlackMage(BLACKMAGE_NAME, 300, DEFENSE, 150, turns);
        var anotherBlackMage1 = new BlackMage("Veigar", 300, DEFENSE, 150, turns);
        var anotherBlackMage2 = new BlackMage(BLACKMAGE_NAME, 250, DEFENSE, 150, turns);
        var anotherBlackMage3 = new BlackMage(BLACKMAGE_NAME, 300, 50, 150, turns);
        var anotherBlackMage4 = new BlackMage(BLACKMAGE_NAME, 300, DEFENSE, 120, turns);
        var expectedEngineer = new Engineer(BLACKMAGE_NAME, 300, DEFENSE, turns);

        for (var character :
                testPlayerCharacters) {
            checkConstruction(expectedBlackMage, character, anotherBlackMage1, anotherBlackMage2, anotherBlackMage3, expectedEngineer);
            assertEquals(anotherBlackMage4, character);
        }
    }

    /**
     * Test for the getters.
     */
    @Test
    void gettersTest() {
        assertEquals(BLACKMAGE_NAME, testBlackMage.getName());
        assertEquals(DEFENSE, testBlackMage.getDefense());
        assertEquals(300, testBlackMage.getHealthPoints());
        assertEquals(150, testBlackMage.getMana());
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

    /**
     * Check that this BlackMage starts without weapon and can equip a knife and a staff
     */
    @Test
    void equipWeaponTest() {
        for (var character :
                testPlayerCharacters) {
            assertNull(character.getEquippedWeapon());
            character.equip(testBow);
            assertNull(character.getEquippedWeapon());
            character.equip(testKnife);
            assertEquals(testKnife, character.getEquippedWeapon());
            character.equip(testStaff);
            assertEquals(testStaff, character.getEquippedWeapon());
        }
    }

    /**
     * Check that this BlackMage's attack is 0 when no weapon is equipped
     * and when a weapon is equipped the attack is the weapon damage
     */
    @Test
    void getWeaponAttack() {
        for (var character :
                testPlayerCharacters) {
            assertEquals(0, character.getAttackDamage());
            character.equip(testBow);
            assertEquals(0, character.getAttackDamage());
            character.equip(testKnife);
            assertEquals(testKnife.getDamage(), character.getAttackDamage());
            character.equip(testStaff);
            assertEquals(testStaff.getDamage(), character.getAttackDamage());
        }
    }
}
