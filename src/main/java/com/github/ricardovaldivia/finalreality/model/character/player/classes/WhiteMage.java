package com.github.ricardovaldivia.finalreality.model.character.player.classes;

import com.github.ricardovaldivia.finalreality.model.character.ICharacter;
import com.github.ricardovaldivia.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;


/**
 * A class that holds all the information of a single White Mage of the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Ricardo Valdivia Orellana.
 */
public class WhiteMage extends AbstractMageCharacter {
  /**
   * Creates a new White Mage.
   *
   * @param name       the character's name
   * @param turnsQueue the queue with the characters waiting for their turn
   * @param maxMana  Max amount of mana of the WhiteMage
   */

  public WhiteMage(@NotNull String name, @NotNull BlockingQueue<ICharacter> turnsQueue, int maxHealth, int defense, int maxMana) {
    super(name, turnsQueue, maxHealth, defense, maxMana);
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName(), getMaxMana(), getMaxHealth(), getDefense());
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof WhiteMage)) {
      return false;
    }
    final WhiteMage that = (WhiteMage) o;
    return getName().equals(that.getName()) &&
        getDefense() == that.getDefense() &&
        getMaxHealth() == that.getMaxHealth() &&
        getMaxMana() == that.getMaxMana();
  }
  @Override
  public void equip(IWeapon weapon) {
    super.equip(weapon.equippedByWhiteMage(this));
  }

  @Override
  public void attack(ICharacter character) {
    if (this.isAlive()) {
      character.attackByWhiteMage(this);

    }
  }

  @Override
  public HashMap<String, String> getCurrentInfo() {
    var info = super.getCurrentInfo();
    info.put("Character Class","White Mage");
    return info;
  }

  @Override
  public String toString(){
    return super.toString() + ", "+ "White Mage";
  }
}
