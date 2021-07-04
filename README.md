<h1>Final-Reality</h1>

[![License: CC BY 4.0](https://img.shields.io/badge/License-CC%20BY%204.0-lightgrey.svg?style=flat)](http://creativecommons.org/licenses/by/4.0/)
[![Language](https://img.shields.io/badge/Language-Java-blue.svg?style=flat)](https://www.java.com/en/)
[![Use](https://img.shields.io/badge/Use-JavaFX-blue.svg?style=flat&)](https://openjfx.io)
[![Built](https://img.shields.io/badge/Built_with-Gradle-orange.svg?style=flat&)](https://gradle.org)
[![Release](https://img.shields.io/badge/Release-v1.0-green.svg?style=flat)](https://github.com/salistito/Final-Reality)


<h2>Table of contents</h2>

- <a href="#context">Context</a>
- <a href="#instructions">Instructions</a>
    - <a href="#quick-start">Quick Start</a>
    - <a href="#gui">Game Details and GUI Explanation</a>
- <a href="#homework-details">Homerwork Details</a>
    - <a href="#assumptions">Assumptions</a>
    - <a href="#logic">Game Logic</a>
    - <a href="#testing">Testing</a>
- <a href="#credits">Credits</a>
- <a href="#license">Copyright and License</a>

<h2 id="context">Context</h2>

This project's goal is to create a (simplified) clone of _Final Fantasy_'s combat, a game developed
by [_Square Enix_](https://www.square-enix.com). <br>
Broadly speaking for the combat the player has a group of characters to control and a group of 
enemies controlled by the computer.


<h2 id="instructions">Instructions</h2>

<h3 id="quick-start">Quick Start</h3>

- Download the repository
- Open your favorite IDE that supports Gradle and open the repository as a new project
- A Gradle build script will be found, select the option "Load Gradle Project", this will import the gradle build 
- Run the FinalReality.java file located at `src/main/java/com.github.salistito/finalreality/gui/FinalReality.java`
- Enjoy the game :)

<h3 id="gui">Game Details and GUI Explanation</h3>

After running the file a window will be displayed, it will show a welcome message and a couple of rules.
You will be asked to enter the amount of characters that will be part of your team and the number of enemies you want to face, you must fill the two fields and
click on the "Enter party size" and "Enter amount of enemies" buttons. Once these details have been entered, a confirmation button will appear,
if you click on this button the data will be confirmed and you will go to the main menu.

In the lower left corner there is a "Generate generic game" button, which will generate a generic combat
between 5 allied characters, equipped with their respective weapons, and 10 random enemies.

<img src="https://github.com/salistito/Final-Reality/blob/master/Preview/Game_Images/Screenshot_1.png" width="825" height="500"/>

Once in the main menu, 2 options will appear, the first one takes you to the character creation scene. 
Here you can create exactly the amount of characters you chose at the beginning, the characters you can create are:

- Black Mages
- Engineers
- Knights
- Thieves
- White Mages

You must choose a name, health points and defense (in the lower right corner there is a text with the appropriate ranges of these attributes to have a well balanced game).
If you have already decided which characters to create and which statistics to give them you must click on the respective creation buttons.
Once all your characters have been created you must click on the "Back to main menu" button, which will return you to the main menu. 

<img src="https://github.com/salistito/Final-Reality/blob/master/Preview/Game_Images/Screenshot_2.png" width="825" height="500"/>
 
The second option on the main menu takes you to the weapon creation scene.
Here, in a similar way to the previous scene, you can create the weapons that you will use during the game, the available weapons are:

- Axes
- Bows
- Knifes
- Staffs
- Swords

You must choose a name and the damage they do (again, in the lower right corner there is a text with the proper range to have a well balanced game).
If you have already decided which weapons to create and which statistics to give them you must click on the respective creation buttons.
Once all your weapons have been created you must click on the "Back to main menu" button, which will return you to the main menu.  

<img src="https://github.com/salistito/Final-Reality/blob/master/Preview/Game_Images/Screenshot_3.png" width="825" height="500"/>

Only when you create exactly the number of characters you choose at the beginning, a third option will appear in the main menu.
This option takes you to the "equip weapon" scene, here you can equip the characters you created with the weapons
which you also decided to create.

Don't worry if you haven't created the weapons yet, you can go back to the main menu and go
to the weapon creation scene and create them or continue creating new ones, always respecting the maximum capacity of
your inventory (in this case it is configured to support 2 weapons per character created, so if you create weapons and
equip your characters it is possible to refill the inventory with the missing weapons).

To equip a weapon to a character you must enter the name of the character and the name of the weapon
in the corresponding text box and press the button "equip". Characters can equip certain
weapons depending on their class:

- Black Mage: can equip a knife and a staff
- Engineer: can equip an Axe and a Bow
- Knight: can equip an Axe, a Knife and a Sword
- Thief: can equip a Bow, a Knife and a Sword
- White Mage: Can equip a Staff 

<img src="https://github.com/salistito/Final-Reality/blob/master/Preview/Game_Images/Screenshot_5.png" width="825" height="500"/>
 
Once all characters are equipped, a "Start game" button will appear. The button will start the fight and take you
to the combat scene. Here you will see on the left side the characters of your team,
on the right side the enemies and in the center a history with the actions that occurred in the turns.
 
Every time it's the turn of a character of your team, a menu will appear with the actions you can perform.

The first action is to attack an enemy, if you want to make an attack you must enter the name of the enemy and
press the "attack" button. When a character or enemy dies, it disappears from the visual.
However, if the name is remembered, it's possible to make an attack on a dead enemy, this attack will not have any effect
and you will lose your turn in vain. Enemies will never attack a character that is dead. 

<img src="https://github.com/salistito/Final-Reality/blob/master/Preview/Game_Images/Screenshot_6.png" width="825" height="500"/>

The second action is to change the weapon that your character carries.
If you want to change the weapon, click on the "Go to inventory" button, this will take you to a scene where it will be shown
the character who owns the turn and the available weapons in your inventory.

The equipping of the new weapon is done in the same way as the equipping was done in the "equip weapon" scene.
Once you are satisfied with the selected weapon, you can return to combat with the "Go back to combat" button.
Your character's turn only ends when he make an attack, so you can change weapons as many times as you want. 

<img src="https://github.com/salistito/Final-Reality/blob/master/Preview/Game_Images/Screenshot_7.png" width="825" height="500"/>
 
The combat will eventually end, you can win or lose, in both cases a scene will be shown according to what has happened.
There will be a "Play again" button that takes you to the first scene of the game so you can play again
choosing a new amount of characters and enemies, and also a "Close game" button that closes the game. 

<img src="https://github.com/salistito/Final-Reality/blob/master/Preview/Game_Images/Screenshot_8.png" width="825" height="500"/>


<h2 id="homework-details">Homework Details:</h2>

<h3 id="assumptions">Assumptions</h3>

During the execution of this project, some assumptions were made that influenced on the final implementation of the program.

- CharacterClass and WeaponType enumerations were removed, because them didn't respect some SOLID principles and these enumerations aren't useful when you need a future specialization of Characters or Weapons.

- In the project description is no allusion to spells or weapons that reduced defense or other attributes than character's health points.
So, the attributes that will be kept constant throughout the game (defined as finals) will be:
    - name, defense and class of each PlayerCharacter
    - name, defense, attack, weight of each Enemy
    - name, attack, weight, (magic damage in the case of the staff) and class of each Weapon
    
- The only modifiable attributes during the game will be the character's health points (and the mana in the case of mages).


- A thief can equip swords, bows or knifes but not staffs (because it makes more sense that a thief can carry a knife and not a magic staff.)

- The game controller let the user decide the number of characters in the party, and how many enemies there will be.
This respects what the statement says because, although the number of player characters it's decided by the user, this amount is fixed during that game instance and it's ensured that it's exactly that before start the game. On the enemy side, what is said in the statement is also respected because, although the number of enemies is decided by the user, these enemies are generated with random attributes and they change in each game instance.

- Some pretty strong assumptions were made for the player's inventory:
    - If an attempt was made to equip a weapon to a character who cannot carry it, the weapon would remain available in inventory.
    - If an attempt is made to equip a weapon and the character can carry it, the weapon is removed from inventory so that it can be used by only one character at a time. 
    - If a character already has a weapon equipped and an attempt is made to equip another weapon from the inventory, what happens is a weapons exchange, leaving the one that the character had equipped in the inventory and equipping him with the selected weapon


- The UML diagrams don't contain all the methods, constructors or parameters that the classes contains, because the diagrams were excessively large and at the time of placing them, they were neither seen nor understood in the pdf file. Therefore, some reference UML diagrams were placed in the summary. And the complete diagrams are uploaded in this same repository in the directory Diagrams. 


<h3 id="logic">Game Logic</h3>

After analyze the hierarchy of classes that were delivered in the base scripts errors and violations of the SOLID principles were found.
So, the hierarchy was modified and some new implementations were developed.

<h4>Characters:</h4>

First of all, an abstract class called AbstractCharacter was created. This class represents all characters (those controlled by the player and enemies), and implements the ICharacter interface, which contains general methods for both types of characters.

Then, the AbstractPlayerCharacter class was created, which is a specialization of the characters and represents all the characters that the player can use
(mages, engineers, thieves and knights). This class is abstract and implements the interface methods.
This abstract class also implements another interface called IPlayerCharacterEquipped.
This interface basically contains the methods that can equip a weapon to the PlayerCharacter, and another
method that returns the weapon that is being equipped by a PlayerCharacter.

To finish with the PlayerCharacter, subclasses were created
for all the characters that the player can control, they all inherit from AbstractPlayerCharacter and share its functionalities
in common.

For mages, an interface (IMage) and a abstract class (abstractMage) were created.
abstractMage implements IMage, and is also a subclass of AbstractPlayerCharacter.

At the moment the only difference is the mana attribute, so IMage only contains the getters and setters of this attribute. 

Another common character specialization are the enemies, so a Enemy class was created.
This Enemy class implements the ICharacter interface and contains all the behaviors and attributes of the game's enemies. 


<h4>Weapons:</h4>

For weapons, the IWeapon interface was created, it contains the common methods for all weapons, and the AbstractWeapon class, which implements this interface.
Finally, subclasses were created for all weapons in the game (axe, bow, knife, staff, sword), them implement this abstract class and specialize their
methods of "equipping the player" (the type is disambiguated thanks to the Double Dispatch). 


<h4>Game Controller:</h4>

The package called controller contains the game controller, the player's inventory, some handlers
that handle different events of the game and the phases that the game has.

The controller is responsible for being an intermediary between what the user sees and the program model, so it has the ability to
create player characters, enemies, weapons, know their attributes, manage player inventory, equip weapons, perform
attacks, handling turn logic, etc.

The inventory class only groups its methods, it is in a separate class to the controller,
only because it provided greater clarity and readability to the code.

Handlers manage the events that happen during the game, they notify when
a character death occurs or when they are added to the turn queue.

Finally, to represent the phases of the game, a State Pattern was implemented.
A Phase class was created, this class groups the common methods of the phases and is the super class of all phases of the game.
The classes that represent the phases of the game are:
- StartGamePhase
- WaitingForTurnPhase
- TurnPhase
- PlayerSelectingActionPhase
- EnemyActionPhase
- PlayerWinPhase
- EnemyWinPhase.

In each of them, some methods of the Phase class are overridden, mainly to make explicit the transitions that can be made from one phase to another. 

<h3 id="testing">Testing</h3>

Finally, to check if all these classes and methods work, tests were created for each of them.
It was possible to obtain 95% coverage in methods, 94% in lines and 97% in branches.
For the PlayerCharacter tests, an AbstractPlayerCharacter class was made.
This class contains some tests that are in common for all PlayerCharacter.
Also, specialized tests were created for each PlayerCharacter, which inherit from this abstract class.

For the enemies, an EnemyTest was created. This class tests all their methods.
For the weapons, specific tests were also created for each ne (AxeTest, BowTest, KnifeTest, StaffTest, SwordTest).

For the game controller, player inventory, event handlers and game phases just a few tests were done.
This test simulated an instance of the game (equipping weapons and combat to the death through the turn system),
where it was possible to evaluate that the logic was well implemented. 


<h2 id="credits">Credits</h2>

Finally I would like to give thanks and credits for the images and sounds used in the project GUI.

- I am very grateful to Riot Games as I used some images from their Youtube videos, which were retouched
to leave them according to the game topic.

- Thanks to Game Freak and Nintendo for their distinctive sound at the time of pressing the buttons in the nostalgic Pokemon games, which was also used in this game.

- Thanks to John Williams for his composition Duel Of Fates, which was used as background music in the combat scene.

- Thanks to Markus Persson and Mojang Studios for the sound when enemies and player characters take damage.

- Thanks to Toshio Masuda for his composition Sadness and Sorrow, the eighth Soundtrack from the Naruto Original Soundtrack album, as a small fragment of it is used in the game's defeat scene.

- And finally to Claudio Palma, for the unforgettable phrases he made when commenting on Alexis Sanchez's penalty that crowned Chile as champion of America in 2015,
since a fragment of it is used in the game's victory scene. 


<h2 id="license">Copyright and License </h2>

![http://creativecommons.org/licenses/by/4.0/](https://i.creativecommons.org/l/by/4.0/88x31.png)

This work is licensed under a 
[Creative Commons Attribution 4.0 International License](http://creativecommons.org/licenses/by/4.0/)
