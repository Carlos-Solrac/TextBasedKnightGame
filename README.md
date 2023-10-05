# Knight Fight Game

**Author:** Carlos Cuellar Benitez
**Date:** 02/25/2023


## About this Project

This Java project implements a text-based knight fighting game. It allows players to create knights and fight against different types of enemies, including Orcs, Shamans, and Bandits. The game features weapon and armor selection, random enemy generation, and turn-based combat.

## Implementation Details

- The project consists of several Java classes:
  - `KnightDriver`: The main class that handles user input and manages the game loop.  -- DEFUNCT, No longer in use. It was meant to be added together, but time ran out.
  - `Knight`: Represents the player's knight and contains attributes like name, hit points, weapon, and armor.
  - `Enemy`: An abstract class that serves as the base for enemy types like Orcs, Shamans, and Bandits.
  - `Orc`, `Shaman`, `Bandit`: Subclasses of `Enemy` that implement specific enemy behaviors and characteristics.
  - `InvalidDamageException`: A custom exception class used for handling invalid damage values.

- The game allows users to create their knights by selecting a name and a weapon. They can also choose to auto-generate an opponent or provide a name and weapon for the opponent.

- Each enemy type has different attributes and characteristics, including weapons and armor.

- Combat in the game is turn-based, and the outcome of battles depends on the player's knight's weapon, the enemy's type, and the enemy's armor.

## Usage

1. Compile the Java source files.
2. Run the `KnightDriver` class to start the game.
3. Follow the on-screen prompts to create your knight and start battles against randomly generated enemies.

## Credits

- This project was created by Carlos Cuellar Benitez.

