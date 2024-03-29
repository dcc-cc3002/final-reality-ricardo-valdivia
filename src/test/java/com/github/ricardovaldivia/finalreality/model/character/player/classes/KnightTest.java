package com.github.ricardovaldivia.finalreality.model.character.player.classes;

import com.github.ricardovaldivia.finalreality.model.character.Enemy;
import com.github.ricardovaldivia.finalreality.model.character.player.AbstractPlayerCharacterTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class KnightTest extends AbstractPlayerCharacterTest {
  private static final String KNIGHT_NAME = "Adelbert";
  private int maxHealth;
  private int defense;
  private Knight testKnightCharacter;


  /**
   * Set the basics setup for each test in this class.
   */
  @BeforeEach
  void setUp() {
    var seed = new Random().nextInt();
    super.setUp(seed);
    maxHealth = r.nextInt(50);
    defense = r.nextInt(30);
    testKnightCharacter = new Knight(KNIGHT_NAME, turns, maxHealth, defense);
  }

  /**
   * Checks that the class' constructor and equals method works properly.
   */
  @RepeatedTest(500)
  void constructorTest() {
    checkConstruction(new Knight(KNIGHT_NAME, turns, maxHealth, defense),
        testKnightCharacter,
        new Knight("Potter", turns, r.nextInt(50), r.nextInt(30)),
        new Enemy(KNIGHT_NAME, r.nextInt(15), turns, r.nextInt(30), r.nextInt(50), r.nextInt(20)),
        new Knight("Dublin", turns, r.nextInt(50), r.nextInt(30)),
        new Knight(KNIGHT_NAME, turns, r.nextInt(50) + 51, defense),
        new Knight(KNIGHT_NAME, turns, maxHealth, r.nextInt(30) + 31));
  }

  /**
   * Checks that the class' equip method works properly.
   */
  @RepeatedTest(500)
  void equipTest() {
    assertFalse(testKnightCharacter.isEquipped());
    testKnightCharacter.equip(testWeapons.get(0));
    assertTrue(testKnightCharacter.isEquipped());
    testKnightCharacter.equip(testWeapons.get(1));
    assertFalse(testKnightCharacter.isEquipped());
    testKnightCharacter.equip(testWeapons.get(2));
    assertTrue(testKnightCharacter.isEquipped());
    testKnightCharacter.equip(testWeapons.get(3));
    assertFalse(testKnightCharacter.isEquipped());
    testKnightCharacter.equip(testWeapons.get(4));
    assertTrue(testKnightCharacter.isEquipped());
  }

  /**
   * Checks that the class' attack method works properly.
   */
  @RepeatedTest(500)
  void attackByEngineerTest() {
    var playerCharacterTest = new Thief("Thief", turns, r.nextInt(60), r.nextInt(30));
    testKnightCharacter.attack(playerCharacterTest);
    testKnightCharacter.equip(testWeapons.get(2));
    testKnightCharacter.attack(playerCharacterTest);
    testKnightCharacter.setCurrentHealth(0);
    testKnightCharacter.setAlive(false);
    testKnightCharacter.attack(playerCharacterTest);
    testKnightCharacter.unEquip();
    assertNull(testKnightCharacter.getEquippedWeapon());
  }
}