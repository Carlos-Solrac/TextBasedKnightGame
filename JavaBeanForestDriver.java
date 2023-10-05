/**
 * Cuellar Benitez, Carlos 
 * COP-3252
 * Assignment 5
 * 02/25/2023
 */
import java.util.Scanner;
import java.util.List;
import java.util.Random;

public class JavaBeanForestDriver {

    
    public static void main(String[] args) {
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);
        // flag for  the while loop to play the game.
        boolean playAgain = true;

        System.out.println("Welcome to Java Bean Forest!" + "\nEnemies lurk in these woods. Fight your way through!");

        do {
            String input = "";
            input = isValidString(sc);
            int numEnemies;
            // Set the length of the line separator
            int lineLength = 50; 
            // Create a string of length lineLength with "-"
            String lineSeparator = String.format("%0" + lineLength + "d", 0).replace("0", "-"); 
            
            // ask the user to name the knight.
            knight myKnight = new knight(input);
            // ask the user to choose a weapon.
            selectWeapon(myKnight, sc);

            // display knight object contents.
            System.out.println("Here is your knight: " + myKnight.toString() );

            // obtain value for number of enemies to fight.
            System.out.println("Sir " + myKnight.GetknightName() + " enters the deep into the dark forest. There is no sound, but that of his own making. As he travels deeper he notices shadows moving in front." + 
            " How many enemies do you see? (1-3)");
            numEnemies = sc.nextInt();

            List<Enemy> enemylist = Enemy.getRandomEnemy(numEnemies);
            for (int i = 0; i < enemylist.size(); i++) {
                System.out.printf("A %s equipped with a %s and wearing %s.\n", enemylist.get(i).getName(), enemylist.get(i).getEnemyWeapon(), enemylist.get(i).getEnemyArmour());
            }

            System.out.println(lineSeparator); 
            
            // Main fight loop, a random enemy is picked each time and the knight attacks them and they atack in return.
            while (enemylist.size() > 0 && myKnight.GetknightHitPoints() > 0) {
                // knight attacks one of the enemies.
                int randomIndex = rand.nextInt(enemylist.size());
                Enemy currentEnemy = enemylist.get(randomIndex);
                int knightAttack = myKnight.toFight(myKnight, currentEnemy);
                System.out.printf("\nSir %s attacks %s and does %d damage!\n", myKnight.GetknightName(), currentEnemy.getName(), knightAttack);
                currentEnemy.takeDamage(knightAttack);
                
                // Check to ensure enemies are "alive" before fighting them and taking damage.
                // enemy attacks
                if (currentEnemy.getCurrentHealth() > 0) {
                    int enemyAttack = currentEnemy.toFight(myKnight, currentEnemy);
                    System.out.printf("%s attacks %s and does %d damage\n", currentEnemy.getName(), myKnight.GetknightName(), enemyAttack);
                    myKnight.takeDamage(enemyAttack);
                }

                // remove enemies who have been defeated.
                if (currentEnemy.getCurrentHealth() <= 0) {
                    System.out.printf("%s has been defeated!\n", currentEnemy.getName());
                    enemylist.remove(randomIndex);
                }

                 // Wait for user input before continuing to the next iteration.
                System.out.println("Press any key to continue...");
                sc.nextLine();
                System.out.println(lineSeparator); 

            }
            
            System.out.println("\nThe battle is over.");
            if( myKnight.GetknightHitPoints() < 0 ) {System.out.printf("Sir %s has been defeated.\n", myKnight.GetknightName());}
            else {System.out.printf("Sir %s has defeated the enemies and can continue further into the forest.\n", myKnight.GetknightName()); }

            // Ask the user if they would like to play again, only valid inputs are Y or N. Anything else loops to ask again.
            playAgain = playAgain(sc);
        } while (playAgain);
    }
    /*
     * playAgain() : The goal is to take input from the user to see if they would liek to play again.
     * Only a Y or a N are valid inputs. The prompt will be asked continuously until a valid input is entered.
     */

     public static boolean playAgain(Scanner sc){
        String option = " ";
        option = option.toUpperCase();
        while ( !option.equals("N") && !option.equals("Y")) {
            System.out.printf("Would you like to play again? (Y|N)\n");
            option = sc.next().toUpperCase();
        }
        return option.equals("Y");
     }

    /*
     * String validation function. Accepts only non-empty strings.
     */
    public static String isValidString(Scanner sc) {
        String input = " ";
        while (true) {
            System.out.print("Enter the name of your knight: ");
            try {
                input = sc.next();
                if (input.length() > 0) {
                    break;
                }
            } catch (Exception ex) {
                System.out.println("Invalid option. Please enter a non-blank name.");
                sc.next();
            }
        }
        return input;
    }


    /*
     * Weapon selection function so that the user can choose the knights weapon.
     */
        
    public static void selectWeapon(knight myKnight, Scanner sc) {
        int weaponOption;
        while (true) {
            System.out.print("Now select your weapon! (Choose number)\n 1) Long Sword\n 2) Battle Axe\n 3) Spear\n 4) Warhammer\n Your choice my liege?: ");
            try {
                weaponOption = sc.nextInt();
                if (weaponOption >= 1 && weaponOption <= 4) {
                    break;
                }    
            } catch (Exception ex) {
                System.out.println("Invalid option. Please choose with a number between 1 and 4.");
                sc.next();
            }    
        }
        switch (weaponOption) {
            case 1:
                myKnight.SetknightWeapon(knight.Weapontype.Long_sword);
                break;
            case 2:
                myKnight.SetknightWeapon(knight.Weapontype.Battle_Axe);
                break;
            case 3:
                myKnight.SetknightWeapon(knight.Weapontype.Spear);
                break;
            case 4:
                myKnight.SetknightWeapon(knight.Weapontype.WarHammer);
                break;
        }
    }
    

}
