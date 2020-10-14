package com.github.salix07.finalreality.model.character;

import com.github.salix07.finalreality.model.character.player.Thief;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class containing the common tests for all the enemies of the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Sebastián Salinas Rodriguez.
 * @see Enemy
 */

class EnemyTest {

  protected BlockingQueue<ICharacter> turns;
  protected List<Enemy> testEnemies;

  private static final String ENEMY_NAME = "Goblin";
  private static int HEALTHPOINTS = 10;
  private static final int DEFENSE = 10;
  private static final int DAMAGE = 10;
  private static final int WEIGHT = 10;

  private Enemy testEnemy;

  /**
   * Basic set up for the enemies test
   */
  protected void basicSetUp() {
    turns = new LinkedBlockingQueue<>();
    testEnemies = new ArrayList<>();
  }

  /**
   * Function that is responsible for checking the correct construction and equalities between enemies
   */
  protected void checkConstruction(final Enemy expectedEnemy,
          final Enemy testEqualEnemy,
          final Enemy sameClassDifferentName,
          final Enemy sameClassDifferentHP,
          final Enemy sameClassDifferentDefense,
          final Enemy sameClassDifferentDamage,
          final Enemy sameClassDifferentWeight,
          final ICharacter differentClassCharacter) {

    assertEquals(expectedEnemy, testEqualEnemy);
    assertEquals(expectedEnemy.hashCode(), testEqualEnemy.hashCode());

    assertTrue(testEqualEnemy.equals(expectedEnemy));
    assertFalse(testEqualEnemy.equals(sameClassDifferentName));
    assertTrue(testEqualEnemy.equals(sameClassDifferentHP));
    assertFalse(testEqualEnemy.equals(sameClassDifferentDefense));
    assertFalse(testEqualEnemy.equals(sameClassDifferentDamage));
    assertFalse(testEqualEnemy.equals(sameClassDifferentWeight));
    assertFalse(testEqualEnemy.equals(differentClassCharacter));
  }

  /**
   * Checks that the enemy waits the appropriate amount of time for it's turn.
   */
  @Test
  void waitTurnTest() {

    Assertions.assertTrue(turns.isEmpty());
    testEnemies.get(0).waitTurn();
    try {
      // Thread.sleep is not accurate so this values may be changed to adjust the
      // acceptable error margin.
      // We're testing that the character waits approximately 1 second.
      Thread.sleep(900);
      Assertions.assertEquals(0, turns.size());
      Thread.sleep(200);
      Assertions.assertEquals(1, turns.size());
      Assertions.assertEquals(testEnemies.get(0), turns.peek());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * Start the set up for the test
   */
  @BeforeEach
  void setUp() {
    basicSetUp();
    testEnemy = new Enemy(ENEMY_NAME, 10, 10, 10, 10, turns);
    testEnemies.add(testEnemy);
  }

  /**
   * Test that check the correct construction and equalities between enemies
   */
  @Test
  void constructorTest() {
    var expectedEnemy = new Enemy(ENEMY_NAME, HEALTHPOINTS, DEFENSE, DAMAGE, WEIGHT, turns);
    var anotherEnemy1 = new Enemy("Spider", HEALTHPOINTS, DEFENSE, DAMAGE, WEIGHT, turns);
    var anotherEnemy2 = new Enemy(ENEMY_NAME, 12, DEFENSE, DAMAGE, WEIGHT, turns);
    var anotherEnemy3 = new Enemy(ENEMY_NAME, HEALTHPOINTS, 12, DAMAGE, WEIGHT, turns);
    var anotherEnemy4 = new Enemy(ENEMY_NAME, HEALTHPOINTS, DEFENSE, 12, WEIGHT, turns);
    var anotherEnemy5 = new Enemy(ENEMY_NAME, HEALTHPOINTS, DEFENSE, DAMAGE, 12, turns);
    var expectedThief = new Thief(ENEMY_NAME, HEALTHPOINTS, DEFENSE, turns);

    for (var enemy :
        testEnemies) {
      checkConstruction(expectedEnemy, enemy, anotherEnemy1, anotherEnemy2, anotherEnemy3, anotherEnemy4, anotherEnemy5, expectedThief);
    }
  }

  /**
   * Test for the getters
   */
  @Test
  void gettersTest() {
    assertEquals(ENEMY_NAME, testEnemy.getName());
    assertEquals(DEFENSE, testEnemy.getDefense());
    assertEquals(HEALTHPOINTS, testEnemy.getHealthPoints());
  }

  /**
   * Test for the setters
   */
  @Test
  void settersTest() {
    testEnemy.setHealthPoints(5);
    assertEquals(5, testEnemy.getHealthPoints());
  }
}