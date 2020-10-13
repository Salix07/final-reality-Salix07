package com.github.salix07.finalreality.model.character;

import com.github.salix07.finalreality.model.weapon.Axe;
import com.github.salix07.finalreality.model.weapon.IWeapon;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Abstract class containing the common tests for all the types of player characters.
 *
 * @author Ignacio Slater Muñoz.
 * @author Sebastián Salinas Rodriguez.
 * @see IPlayerCharacterEquipped
 */

public abstract class AbstractPlayerCharacterTest {

  protected BlockingQueue<ICharacter> turns;
  protected List<IPlayerCharacterEquipped> testPlayerCharacters;
  protected IWeapon testWeapon;


  /**
   * Basic set up for all the test of player characters
   */
  protected void basicSetUp() {
    turns = new LinkedBlockingQueue<>();
    testPlayerCharacters = new ArrayList<>();
    testWeapon = new Axe("Test", 15, 10);
  }

  /**
   * Function that is responsible for checking the correct construction and equalities between player characters
   */
  protected void checkConstruction(final IPlayerCharacterEquipped expectedCharacter,
      final IPlayerCharacterEquipped testEqualCharacter,
      final IPlayerCharacterEquipped sameClassDifferentName,
      final IPlayerCharacterEquipped sameClassDifferentHP,
      final IPlayerCharacterEquipped sameClassDifferentDefense,
      final IPlayerCharacterEquipped differentClassCharacter) {

    assertEquals(expectedCharacter, testEqualCharacter);
    assertEquals(expectedCharacter.hashCode(), testEqualCharacter.hashCode());

    assertTrue(testEqualCharacter.equals(expectedCharacter));
    assertFalse(testEqualCharacter.equals(sameClassDifferentName));
    assertTrue(testEqualCharacter.equals(sameClassDifferentHP));
    assertFalse(testEqualCharacter.equals(sameClassDifferentDefense));
    assertFalse(testEqualCharacter.equals(differentClassCharacter));
  }

  /**
   * Function that calls the "equip" method of the player characters
   */
  private void tryToEquip(IPlayerCharacterEquipped character) {
    character.equip(testWeapon);
  }

  /**
   * Checks that the player character waits the appropriate amount of time for it's turn.
   */
  @Test
  void waitTurnTest() {

    Assertions.assertTrue(turns.isEmpty());
    tryToEquip(testPlayerCharacters.get(0));
    ((ICharacter)testPlayerCharacters.get(0)).waitTurn();
    try {
      // Thread.sleep is not accurate so this values may be changed to adjust the
      // acceptable error margin.
      // We're testing that the character waits approximately 1 second.
      Thread.sleep(900);
      Assertions.assertEquals(0, turns.size());
      Thread.sleep(200);
      Assertions.assertEquals(1, turns.size());
      Assertions.assertEquals(testPlayerCharacters.get(0), turns.peek());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * Check that the player character starts without weapon and can equip weapons
   */
  @Test
  void equipWeaponTest() {
    for (var character :
            testPlayerCharacters) {
      assertNull(character.getEquippedWeapon());
      character.equip(testWeapon);
      assertEquals(testWeapon, character.getEquippedWeapon());
    }
  }
}
