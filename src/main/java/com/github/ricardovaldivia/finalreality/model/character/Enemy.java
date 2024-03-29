package com.github.ricardovaldivia.finalreality.model.character;

import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.github.ricardovaldivia.finalreality.controller.handlers.IHandler;
import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a single enemy of the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Ricardo Valdivia Orellana.
 */
public class Enemy extends AbstractCharacter {

  private final int weight;
  private final int attack;
  private final PropertyChangeSupport enemyDieNotification = new PropertyChangeSupport(
      this);

  /**
   * Creates a new enemy with a name, a weight and the queue with the characters ready to
   * play.
   */
  public Enemy(@NotNull final String name, final int weight,
      @NotNull final BlockingQueue<ICharacter> turnsQueue,int maxHealth , int defense, int attack) {
    super(turnsQueue, name, maxHealth, defense);
    this.weight = weight;
    this.attack = attack;
  }

  /**
   * Returns the weight of this enemy.
   */
  public int getWeight() {
    return weight;
  }

  /**
   * Returns the attack of this enemy.
   */
  public int getAttack() {
    return attack;
  }

  @Override
  public void waitTurn() {
    scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    scheduledExecutor.schedule(this::addToQueue, this.getWeight() / 10, TimeUnit.SECONDS);
  }

  @Override
  public void attack(ICharacter character) {
    character.attackByEnemy(this);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Enemy)) {
      return false;
    }
    final Enemy enemy = (Enemy) o;
    return getWeight() == enemy.getWeight() &&
          getDefense() == enemy.getDefense() &&
          getMaxHealth() == enemy.getMaxHealth() &&
          getAttack() == enemy.getAttack();
  }

  @Override
  public int hashCode() {
    return Objects.hash(getWeight(), getMaxHealth(), getAttack(), getDefense());
  }


  @Override
  public HashMap<String, String> getCurrentInfo() {
   var info =  super.getCurrentInfo();
   info.put("Attack", String.valueOf(this.getAttack()));
   info.put("Weight", String.valueOf(this.getWeight()));
   info.put("Character Class", "Enemy");
   return info;
  }

  @Override
  public void setCurrentHealth(int newHealth) {
    super.setCurrentHealth(newHealth);
    if (newHealth <= 0){
      enemyDieNotification.firePropertyChange("ENEMY_DEATH", null, this);
    }
  }

  /**
   * adds a enemyListener to this enemy, to handle his death.
   */
  public void addEnemyListener(final IHandler enemyDeathHandler) {
    enemyDieNotification.addPropertyChangeListener(enemyDeathHandler);
  }

  @Override
  public String toString() {
    return super.toString() +", Weight: "+ getWeight()+", Attack: "+ getAttack();
  }
}


