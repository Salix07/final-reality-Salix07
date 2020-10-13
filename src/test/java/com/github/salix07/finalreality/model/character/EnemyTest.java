/*
package com.github.salix07.finalreality.model.character;

import com.github.salix07.finalreality.model.character.player.Thief;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EnemyTest extends AbstractCharacterTest {

  private static final String ENEMY_NAME = "Goblin";

  @BeforeEach
  void setUp() {
    basicSetUp();
    testCharacters.add(new Enemy(ENEMY_NAME, 10, 10, 10, 10, turns));
  }

  @Test
  void constructorTest() {
    checkConstruction(new Enemy(ENEMY_NAME, 10, 10, 10, 10, turns),
        testCharacters.get(0),
        new Enemy(ENEMY_NAME, 11, 11, 11, 11, turns),
        new Thief(ENEMY_NAME, 11, 11, turns));
  }
}
 */