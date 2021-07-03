package com.github.salistito.finalreality.model.character;

import com.github.salistito.finalreality.model.character.ICharacter;
import com.github.salistito.finalreality.model.character.player.*;
import com.github.salistito.finalreality.model.weapon.*;

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
 * @author Sebastián Salinas Rodriguez.
 * @see IPlayerCharacter
 */

public abstract class AbstractPlayerCharacterTest {

  protected BlockingQueue<ICharacter> turns;
  protected List<IPlayerCharacter> testPlayerCharacters;
  protected Axe testAxe;
  protected Bow testBow;
  protected Knife testKnife;
  protected Staff testStaff;
  protected Sword testSword;



  /**
   * Basic set up for all the test of player characters
   */
  protected void basicSetUp() {
    turns = new LinkedBlockingQueue<>();
    testPlayerCharacters = new ArrayList<>();
    testAxe = new Axe("TestAxe",12,10);
    testBow = new Bow("TestBow", 12, 10);
    testKnife = new Knife("TestKnife",10,10);
    testStaff = new Staff("testStaff", 5, 10, 10);
    testSword = new Sword("TestSword", 15, 10);
  }

  /**
   * Function that is responsible for checking the correct construction and equalities between player characters
   */
  protected void checkConstruction(final IPlayerCharacter expectedCharacter,
      final IPlayerCharacter testEqualCharacter,
      final IPlayerCharacter sameClassDifferentName,
      final IPlayerCharacter sameClassDifferentHP,
      final IPlayerCharacter sameClassDifferentDefense,
      final IPlayerCharacter differentClassCharacter) {

    assertEquals(expectedCharacter, testEqualCharacter);
    assertEquals(expectedCharacter.hashCode(), testEqualCharacter.hashCode());

    assertEquals(testEqualCharacter, testEqualCharacter);
    assertEquals(expectedCharacter, testEqualCharacter);
    assertNotEquals(sameClassDifferentName, testEqualCharacter);
    assertEquals(sameClassDifferentHP, testEqualCharacter);
    assertNotEquals(sameClassDifferentDefense, testEqualCharacter);
    assertNotEquals(differentClassCharacter, testEqualCharacter);
  }

  /**
   * Function that calls the "equip" method of the player's characters depending on which character instance it is.
   * We do this to equip the appropriate weapon an do the wait turn test.
   */
  private void tryToEquip(IPlayerCharacter character) {

    if (character instanceof Engineer || character instanceof Thief) {
      character.equip(testBow);
    }
    if (character instanceof BlackMage || character instanceof WhiteMage) {
      character.equip(testStaff);
    }
    if (character instanceof Knight) {
      character.equip(testSword);
    }
  }

  /**
   * Checks that the player character waits the appropriate amount of time for it's turn.
   */
  @Test
  void waitTurnTest() {

    Assertions.assertTrue(turns.isEmpty());
    tryToEquip(testPlayerCharacters.get(0));
    testPlayerCharacters.get(0).waitTurn();
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
}
