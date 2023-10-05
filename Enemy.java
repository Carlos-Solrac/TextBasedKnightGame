/**
 * Cuellar Benitez, Carlos 
 * COP-3252
 * Assignment 5
 * 02/25/2023
 */
import java.util.ArrayList;
import java.util.Random;
import java.util.List;

 public abstract class Enemy {
    // enum type Weapontype and Amourtype will hold all possible weapons for enemies. Not every weapon is available for each enemy.
    enum Weapontype{
        Long_sword, Battle_Axe, Spear, WarHammer, Staff, Club, Dagger, Mace
    }
    
    enum Armourtype{
        Chainmail, Steel_Plate, Leather, Robes, Bare
    }

    // Arrays of weapons and armour to be used to randomly assign enemies weapons based on weapons they can use.
    private static Weapontype[] possibleWeapons = {Weapontype.Long_sword, Weapontype.Battle_Axe, Weapontype.Spear, Weapontype.WarHammer, Weapontype.Staff, Weapontype.Club, Weapontype.Dagger};
    private static Armourtype[] possibleArmours = {Armourtype.Chainmail, Armourtype.Steel_Plate, Armourtype.Leather,Armourtype.Robes, Armourtype.Bare};

    // class private variables, the name is just the type of monster, 
    // maxhealth is separate from actual health for the fighting, and then we have the variables to hold the weapons and armours.

    private String name;
    private int maxHealth;
    private int currentHealth;
    private String enemyType;
    private Weapontype enemyWeapon;
    private Armourtype enemyArmour;
    private static Random rand = new Random();


    // Default constructor for my enemy 

    public Enemy(String name, int maxHealth, int currentHealth, Weapontype enemyWeapon, Armourtype enemyArmour, String type) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.currentHealth = Math.min(maxHealth, currentHealth);
        this.enemyWeapon = enemyWeapon;
        this.enemyArmour = enemyArmour;
        this.enemyType = type;
    }   

    // Accessor functions
     public String getName(){
        return this.name;
     }

     public int getMaxHealth(){
        return this.maxHealth;
     }

     public int getCurrentHealth(){
        return this.currentHealth;
     }

     public Weapontype getEnemyWeapon(){
        return this.enemyWeapon;
     }
     public Armourtype getEnemyArmour(){
        return this.enemyArmour;
     }

     public String getEnemyType(){
        return this.enemyType;
     }

    // Mutator functions

    public void setName(String nm){
        this.name = nm;
    }

    public void setMaxHealth(int num){
        this.maxHealth = num;
    }

    public void setCurrentHealth(int num) {
      try{
        if (num < 0) {
          throw new IllegalArgumentException(this.getName() + " has reached their health limit. This was a deadly blow!");
      }
      this.currentHealth = Math.min(this.maxHealth, num);
      } catch ( IllegalArgumentException e)
      {
        this.setCurrentHealth(0);
      }
        
    }

    public void setEnemyWeapon(Weapontype wep){
        this.enemyWeapon = wep;
    }

    public void setEnemyArmour(Armourtype arm){
        this.enemyArmour = arm;
    }

    public void setEnemyType(String type){
        this.enemyType = type;
    }


    // Class methods 

    public static List<Enemy> getRandomEnemy(int numEnemies){
        //Create a list to return the created enemies.
        List<Enemy> enemiesList = new ArrayList<>();
        int orcCounter = 1;
        int shamanCounter = 1;
        int banditCounter = 1;

        /*  return a random enemy by picking a random list index using our desired number of enemies
         and then using a switch to create a new instance of said enemy. This will allow for different
         for different enemy configurations even if they are the same type.
        */
        for (int i=0; i < numEnemies; i++){
            int randomIndex = rand.nextInt(numEnemies);
            switch(randomIndex){
                case 0:
                    enemiesList.add(new Orc("Orc"+ orcCounter)); 
                    orcCounter++;
                    break;
                case 1:
                  enemiesList.add(new Shaman("Shaman" +shamanCounter)); 
                  shamanCounter++;
                  break;

                case 2:
                  enemiesList.add(new Bandit("Bandit" +banditCounter)); 
                  banditCounter++;
                  break;
                default:
                  enemiesList.add(new Orc("Orc" + orcCounter));
                  orcCounter++;
                  break;           
            }
        }
        return enemiesList;
    }


    // method for enemies to take damage. Uses a custom exception.
    public int takeDamage(int damage)  {
      try{
          if (damage < 0) {
            throw new InvalidDamageException("Damage cannot be negative, setting to 0.");
          }
          setCurrentHealth(getCurrentHealth() - damage);
          return damage;

        } catch (InvalidDamageException e){
          System.out.println("Invalid damage value: " + e.getMessage());
          return 0;
        }
    } 
    
    // Abstract Methods

    //method for enemies to fight.
    public abstract int toFight(knight theKnight, Enemy enemyList);    

    // Meant to return a random weapon type for our enemy. Ideally, only weapons the enemy can use will be possibilities.
    public abstract  Weapontype getRandomWeapon(); 

    // Meant to return a random armour type for our enemy. Ideally, only armours the enemy can use will be possibilities.
    public abstract  Armourtype getRandomArmour(); 

}



// Sub-classes of enemy superclass.
// Still need to figure out a way to randomly assign the correct type of weapon/armour of each enemy type.
 class Orc extends Enemy {

    // List of possible weapons/armor for Orcs
    private static Weapontype[] weapons = {Weapontype.Club, Weapontype.Spear, Weapontype.Dagger};
    private static Armourtype[] armour = {  Armourtype.Leather, Armourtype.Steel_Plate, Armourtype.Chainmail};
    //Constructor

    public Orc( String name ) {
        super( name, 100, 100, null, null, "Orc");
        setEnemyWeapon(getRandomWeapon());
        setEnemyArmour(getRandomArmour());
    }

    //Method to get a random weapon for orcs
    @Override
    public  Weapontype getRandomWeapon(){
        Random rand = new Random();
        int index = rand.nextInt(weapons.length);
        return weapons[index];
    }

    //Method to get a random armour for orcs
     @Override
    public  Armourtype getRandomArmour(){
        Random rand = new Random();
        int index = rand.nextInt(armour.length);
        return armour[index];
    }

    //Method to fight
    @Override
    public int toFight(knight theKnight, Enemy theEnemy){ int baseDamage = 0;

      // Weapon damage modifier.
   
       switch (theEnemy.getEnemyWeapon()) {
         case Club:
           baseDamage = 12;
           break;
         case Dagger:
           baseDamage = 10;
           break;
         case Spear:
           baseDamage = 15;
           break;
         default:
          baseDamage = 5;
           break;  
       }
   
       // Race damage modifier.
   
       int raceDamageModifier = 2;
  
       // Armor damage adjustments.
   
       int armorDamageReduction = 0;
       switch (theKnight.Getknightarmour()) {
         case Chainmail:
           armorDamageReduction = 2;
           break;
         case Steel_Plate:
           armorDamageReduction = 4;
           break;
         case Leather:
           armorDamageReduction = 1;
           break;  
         default:
           armorDamageReduction = 0;
           break;   
       }
   
     int totalDamage = baseDamage - armorDamageReduction + raceDamageModifier;
     return totalDamage; 
    }



    //Method to override the toString() method.
    @Override
public String toString() {
    return String.format("%s (%d/%d) wielding %s and wearing %s", 
        getName(), getCurrentHealth(), getMaxHealth(), getEnemyWeapon(), getEnemyArmour());
}
 }


class Shaman extends Enemy{
    // List of possible weapons/armor for Shamans
    private static Weapontype[] weapons = {Weapontype.Staff, Weapontype.Mace, Weapontype.Dagger};
    private static Armourtype[] armour =  {Armourtype.Steel_Plate, Armourtype.Leather, Armourtype.Robes};

    //Constructor
    public Shaman(String name) {
        super(name, 60, 60, null, null,"Shaman");
        setEnemyWeapon(getRandomWeapon());
        setEnemyArmour(getRandomArmour());
    }


    //Method to get a random weapon for orcs
    @Override
    public  Weapontype getRandomWeapon(){
        Random rand = new Random();
        int index = rand.nextInt(weapons.length);
        return weapons[index];
    }

    //Method to get a random armour for orcs
        @Override
    public  Armourtype getRandomArmour(){
        Random rand = new Random();
        int index = rand.nextInt(armour.length);
        return armour[index];
    }

        //Method to fight
        @Override
        public int toFight(knight theKnight, Enemy theEnemy){ int baseDamage = 0;
    
          // Weapon damage modifier.
       
           switch (getEnemyWeapon()) {
             case Staff:
               baseDamage = 12;
               break;
             case Mace:
               baseDamage = 13;
               break;
             case Dagger:
               baseDamage = 10;
               break;
             default:
              baseDamage = 5;
              break;
           }
       
           // Race damage modifier.
       
           int raceDamageModifier = 1;
      
           // Armor damage adjustments.
       
           int armorDamageReduction = 0;
           switch (theKnight.Getknightarmour()) {
             case Chainmail:
               armorDamageReduction = -5;
               break;
             case Steel_Plate:
               armorDamageReduction = -7;
               break;
             case Leather:
               armorDamageReduction = 3;
               break;  
             default:
               armorDamageReduction = 0;
               break;    
           }
       
         int totalDamage = baseDamage - armorDamageReduction + raceDamageModifier;
         return totalDamage; 
        }

    
    //Method to override the toString() method.
    @Override
public String toString() {
    return String.format("%s (%d/%d) wielding %s and wearing %s", 
        getName(), getCurrentHealth(), getMaxHealth(), getEnemyWeapon(), getEnemyArmour());
}
}

class Bandit extends Enemy{
    // List of possible weapons/armor for Bandits
    private static Weapontype[] weapons = {Weapontype.Long_sword, Weapontype.Battle_Axe, Weapontype.Club ,Weapontype.Dagger};
    private static Armourtype[] armour =  {Armourtype.Chainmail, Armourtype.Leather, Armourtype.Robes, Armourtype.Steel_Plate};

    //Constructor
    public Bandit(String name) {
        super( name, 80, 80, null, null,"Bandit");
        setEnemyWeapon(getRandomWeapon());
        setEnemyArmour(getRandomArmour());
    }

    //Method to get a random weapon for orcs
    @Override
    public  Weapontype getRandomWeapon(){
        Random rand = new Random();
        int index = rand.nextInt(weapons.length);
        return weapons[index];
    }

    //Method to get a random armour for orcs
        @Override
    public  Armourtype getRandomArmour(){
        Random rand = new Random();
        int index = rand.nextInt(armour.length);
        return armour[index];
    }

        //Method to fight
        @Override
        public int toFight(knight theKnight, Enemy theEnemy){ 
          int baseDamage = 0;
    
          // Weapon damage modifier.
       
           switch (this.getEnemyWeapon()) {
             case Club:
               baseDamage = 12;
               break;
             case Dagger:
               baseDamage = 10;
               break;
             case Long_sword:
               baseDamage = 11;
               break;
             case Battle_Axe:
              baseDamage = 14;
              break;
             default:
              baseDamage = 5;
              break;    
           }
       
           // Race damage modifier.
       
           int raceDamageModifier = 0;
      
           // Armor damage adjustments.
       
           int armorDamageReduction = 0;
           switch (theKnight.Getknightarmour()) {
             case Chainmail:
               armorDamageReduction = 2;
               break;
             case Steel_Plate:
               armorDamageReduction = 4;
               break;
             case Leather:
               armorDamageReduction = 1;
               break;
             default:
               armorDamageReduction = 0;
               break;   
           }
       
         int totalDamage = baseDamage - armorDamageReduction + raceDamageModifier;
         return totalDamage; 
        }

    //Method to override the toString() method.
    @Override
public String toString() {
    return String.format("%s (%d/%d) wielding %s and wearing %s", 
        getName(), getCurrentHealth(), getMaxHealth(), getEnemyWeapon(), getEnemyArmour());
}

}


