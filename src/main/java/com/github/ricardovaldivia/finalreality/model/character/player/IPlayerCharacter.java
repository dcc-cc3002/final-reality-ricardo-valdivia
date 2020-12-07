package com.github.ricardovaldivia.finalreality.model.character.player;

import com.github.ricardovaldivia.finalreality.model.character.ICharacter;
import com.github.ricardovaldivia.finalreality.model.weapon.IWeapon;

public interface IPlayerCharacter extends ICharacter {
  /**
   * Equip a weapon to a character.
   */
  void equip(IWeapon weapon);

  /**
   * Returns if the weapon is equipped or not.
   */
  boolean isEquipped();

  /**
   * Return the weapon equipped by a character
   */

  IWeapon getEquippedWeapon();

}