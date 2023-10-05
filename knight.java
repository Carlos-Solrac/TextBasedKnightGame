/**
 * Cuellar Benitez, Carlos 
 * COP-3252
 * Assignment 5
 * 02/25/2023
 */

public class knight
{
  /**
   * My main method
   */

   enum Weapontype{
    Long_sword,Battle_Axe,Spear,WarHammer
   }

   enum Armourtype{
    Chainmail,Steel_Plate,Leather
   }

  private String knightName;
  private int knightHitPoints;
  private Weapontype knightweapon;
  private Armourtype knightarmour;
  private String[] names ={"Arthur", "Lancelot","Galahad","Robin","Gawain"};

 /*
  * Constructors for knight class.
  */
  public knight(String knightName)
  {
    this.knightName = knightName;
    this.knightHitPoints=  200;
    this.knightweapon = null;
    this.knightarmour = Armourtype.values()[(int)(Math.random()*Armourtype.values().length)];;;
  }

  public knight()
  {
    this.knightName = names[(int)(Math.random()*names.length)];
    this.knightHitPoints= 50 + (int) (Math.random() * ((100-50)+1));
    this.knightweapon = Weapontype.values()[(int)(Math.random()*Weapontype.values().length)];;
    this.knightarmour = Armourtype.values()[(int)(Math.random()*Armourtype.values().length)];;
  }

  /*
   * Set methods for class.
   */
  public void SetknightName( String knightName)
  {
	this.knightName = knightName;
  }

  public void SetknightWeapon( Weapontype weapon)
  {
	this.knightweapon = weapon;
  }

  public void SetKnightHitPoints( int num)
  {
    try{
      if (num < 0){
        throw new IllegalArgumentException("Sir " + this.GetknightName() + " has been dealt a deadly blow.");
      }
      this.knightHitPoints = Math.min(this.knightHitPoints, num);
    } catch (IllegalArgumentException e)
      {
        System.out.println( e.getMessage());
        this.knightHitPoints = 0;
      }
  }

    /*
   * Get methods for class.
   */
  String GetknightName()
  {
	return this.knightName;
  }

  int GetknightHitPoints()
  {
	return this.knightHitPoints;
  }

 
 Weapontype Getknightweapon()
  {
	return this.knightweapon;
  }

  Armourtype Getknightarmour()
  {
	return this.knightarmour;
  }

  /*
   *  Class methods
   */

   public String toString()
   {
      return "Sir "+ knightName +"\nHealth: "+knightHitPoints+"\nWeapon: "+knightweapon+"\nArmour: "+knightarmour;
   }


   public int takeDamage (int damage) {
      try{
        if (damage < 0) {
        damage = 0;
        throw new InvalidDamageException("Damage cannot be negative, setting to 0.");
      }
      SetKnightHitPoints(GetknightHitPoints() - damage);
      return damage;

    } catch ( InvalidDamageException e)
    {
                System.out.println("Invalid damage value: " + e.getMessage());
          return 0;
    } 
   }  


/*
 * The tofight method is meant to take a single instance of our knight and an enemy. A damage
 * value is returned based one 1. the knights weapon, 2. the type of enemy, and 3. the armour
 * the enemy is wearing.
 */
   public int toFight(knight theKnight, Enemy theEnemy)
   {
    int baseDamage = 0;
    int knightBonusDamage = 20;

   // Weapon damage modifier.

    switch (Getknightweapon()) {
      case Long_sword:
        baseDamage = 12;
        break;
      case Battle_Axe:
        baseDamage = 14;
        break;
      case Spear:
        baseDamage = 15;
        break;
      case WarHammer:
        baseDamage = 13;
        break;
      default:
        baseDamage = 5;
        break;    
    }

    // Race damage modifier.

    int raceDamageModifier = 0;
    switch (theEnemy.getEnemyType()) {
      case "Orc":
        raceDamageModifier = -1;
        break;
      case "Shaman":
        raceDamageModifier = +2;
        break;
      case "Bandit":
        raceDamageModifier = +1;
        break;
      default:
        raceDamageModifier = 0;
        break;  
    }

    // Armor damage adjustments.

    int armorDamageReduction = 0;
    switch (theEnemy.getEnemyArmour()) {
      case Chainmail:
        armorDamageReduction = 2;
        break;
      case Steel_Plate:
        armorDamageReduction = 4;
        break;
      case Leather:
        armorDamageReduction = 1;
        break;
      case Robes:
        armorDamageReduction = 0;
        break;
        
      default:
        armorDamageReduction = 0;
        break;    
    }

  int totalDamage = baseDamage + knightBonusDamage - armorDamageReduction + raceDamageModifier;
  return totalDamage; 
  }
}
