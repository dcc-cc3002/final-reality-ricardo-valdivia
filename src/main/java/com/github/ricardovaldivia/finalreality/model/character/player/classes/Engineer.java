package com.github.ricardovaldivia.finalreality.model.character.player.classes;

import com.github.ricardovaldivia.finalreality.model.character.ICharacter;
import com.github.ricardovaldivia.finalreality.model.character.player.AbstractPlayerCharacter;
import com.github.ricardovaldivia.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

public class Engineer extends AbstractPlayerCharacter {
  /**
   * Creates a new character.
   *
   * @param name       the character's name
   * @param turnsQueue the queue with the characters waiting for their turn
   * @param defense    the character's defense
   */
  public Engineer(@NotNull String name, @NotNull BlockingQueue<ICharacter> turnsQueue, int maxHealth, int defense) {
    super(name, turnsQueue, maxHealth, defense);
  }


  @Override
  public int hashCode() {
    return Objects.hash(getName(), getMaxHealth(), getDefense());
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Engineer)) {
      return false;
    }
    final Engineer that = (Engineer) o;
    return getName().equals(that.getName()) &&
        getDefense() == that.getDefense() &&
        getMaxHealth() == that.getMaxHealth();
  }

  @Override
  public void equip(IWeapon weapon) {
    super.equip(weapon.equippedByEngineer(this));
  }

  @Override
  public void attack(ICharacter character) {
    if (this.isAlive()) {
      character.attackByEngineer(this);
    }
  }
}
