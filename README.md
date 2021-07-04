<h1>Final-Reality</h1>

[![License: CC BY 4.0](https://img.shields.io/badge/License-CC%20BY%204.0-lightgrey.svg?style=flat)](http://creativecommons.org/licenses/by/4.0/)
[![Language](https://img.shields.io/badge/Language-Java-blue.svg?style=flat)](https://www.java.com/en/)
[![Use](https://img.shields.io/badge/Use-JavaFX-blue.svg?style=flat&)](https://openjfx.io)
[![Use](https://img.shields.io/badge/Built_with-Gradle-orange.svg?style=flat&)](https://openjfx.io)
[![Release](https://img.shields.io/badge/Release-v1.0-green.svg?style=flat)](https://github.com/salistito/Final-Reality)


<h2>Table of contents</h2>

- <a href="#context">Context</a>
- <a href="#instructions">Instructions</a>
    - <a href="#quick-start">Quick Start</a>
    - <a href="#gui">GUI Explanation and Game Details</a>
- <a href="#homework-details">Homerwork Details</a>
    - <a href="#assumptions">Assumptions</a>
    - <a href="#logic">Game Logic</a>
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
- Run the "FinalReality" class located at `src/main/java/com.github.salistito/finalreality/gui/FinalReality`
- Enjoy the game :)

<h3 id="gui">GUI Explanation and Game Details</h3>

After running the file a window will be displayed, it will show a welcome message and a couple of rules.
You will be asked to enter the amount of characters that will be part of your team and the number of enemies you want to face, you must fill the two fields and
click on the "Enter party size" and "Enter amount of enemies" buttons. Once these details have been entered, a confirmation button will appear,
if you click on this button the data will be confirmed and you will go to the main menu.

It should be noted that in the lower left corner there is a "Generate generic game" button, which will generate a generic combat
between 5 allied characters, equipped with their respective weapons, and 10 random enemies.

<img src="https://github.com/salistito/Final-Reality/blob/master/Preview/Game_Images/Screenshot_1.png" width="825" height="500"/>

Once in the main menu, 2 options will appear, the first one takes you to the character creation scene. 
Here you can create exactly the amount of characters you chose at the beginning, the characters you can create are:
- Black Mages
- Engineers
- Knights
- Thiefs
- White Mages

You must choose a name, health points and defense (in the lower right corner there is a text with the appropriate ranges of these attributes to have a well balanced game).
If you have already decided which characters to create and which statistics to give them, you must click on the respective creation buttons.
Once all your characters have been created, you must click on the "Back to main menu" button, which will return you to the main menu. 

<img src="https://github.com/salistito/Final-Reality/blob/master/Preview/Game_Images/Screenshot_2.png" width="825" height="500"/>
 
The second option on the main menu takes you to the weapon creation scene.
Here, in a similar way to the previous scene, you can create the weapons that you will use during the game, the available weapons are:
- Axes
- Bows
- Knifes
- Staffs
- Swords

You must choose a name and the damage they do (again, in the lower right corner there is a text with the proper range to have a well balanced game).
If you have already decided which weapons to create and which statistics to give them, you must click on the respective creation buttons.
Once all your weapons have been created, you must click on the "Back to main menu" button, which will return you to the main menu.  

<img src="https://github.com/salistito/Final-Reality/blob/master/Preview/Game_Images/Screenshot_3.png" width="825" height="500"/>

Only when you create exactly the number of characters you choose at the beginning, a third option will appear in the main menu.
This option takes you to the "equip weapon" scene, here you can equip the characters you created with the weapons
which you also decided to create. Don't worry if you haven't created the weapons yet, you can go back to the main menu and go
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
to the combat scene. Here you will see on the left side the characters of your team and their data,
on the right side the enemies and their data and in the center a history with the actions that occurred in the turns.
 
Every time it is the turn of a character of your team, a menu will appear with the actions you can perform.

The first action is to attack an enemy, if you want to make an attack you must enter the name of the enemy and
press the "attack" button. When a character or enemy dies, it disappears from the visual.
However, if the name is remembered it is possible to make an attack on a dead enemy, this attack will not have any effect
for which you will lose your turn in vain, in the case of enemies they will never attack a character that is dead. 

<img src="https://github.com/salistito/Final-Reality/blob/master/Preview/Game_Images/Screenshot_6.png" width="825" height="500"/>

The second action is to change the weapon that your character carries.
If you want to change the weapon, click on the "Go to inventory" button, this will take you to a scene where it will be shown
the character who owns the turn and the available weapons in your inventory.
The equipping of the new weapon is done in the same way as the equipping was done in the "equip weapon" scene.
Once you are satisfied with the selected weapon, you can return to combat with the "Go back to combat" button.
Your character's turn only ends when they make an attack and you can change weapons as many times as you want. 

<img src="https://github.com/salistito/Final-Reality/blob/master/Preview/Game_Images/Screenshot_7.png" width="825" height="500"/>
 
The combat will eventually end, you can win or lose, in both cases a scene will be shown according to what has happened.
There will be a "Play again" button that takes you to the first scene of the game so you can play again
choosing a new amount of characters and enemies, and also a "Close game" button that closes the game. 

<img src="https://github.com/salistito/Final-Reality/blob/master/Preview/Game_Images/Screenshot_8.png" width="825" height="500"/>


<h2 id="homework-details">Homework Details:</h2>

<h3 id="assumptions">Assumptions</h3>

During the execution of this project, some assumptions were made that influenced on the final implementation of the program.

- CharacterClass and WeaponType enumerations were removed, because them didn't respect some SOLID principles and these enumerations aren't so useful when you need a future specialization of Characters or Weapons.

- In the description of the project is no allusion to spells or weapons that reduced defense or other attributes than character's health points.
So, the attributes that will be kept constant throughout the game (defined as finals) will be:
    - name, defense and class of each playerCharacter
    - name, defense, attack, weight of each Enemy
    - name, attack, weight, (magic damage in the case of the staff) and class of each Weapon
    
- The only modifiable attributes during the game will be the character's health points (and the mana in the case of mages).
- A thief can equip swords, bows or knifes but not staffs (because it makes more sense that a thief can carry a knife and not a magic staff.)

- The game controller let the user decide the number of characters in the party, and how many enemies there will be.
This respects what the statement says because, although the number of player characters it's decided by the user, this amount is fixed during that game instance and it's ensured that it's exactly that before start the game. On the enemy side, what is said in the statement is also respected because, although the number of enemies is decided by the user, these enemies are generated with random attributes and they change in each game instance.

- It should be mentioned that some pretty strong assumptions were made for the player's inventory:
    - If an attempt was made to equip a weapon to a character who cannot carry it, the weapon would remain available in inventory.
    - If an attempt is made to equip a weapon and the character can carry it, the weapon is removed from inventory so that it can be used by only one character at a time. 
    - If a character already has a weapon equipped and an attempt is made to equip another weapon from the inventory, what happens is a weapons exchange, leaving the one that the character had equipped in the inventory and equipping him with the selected weapon
    - The UML diagrams don't contain all the methods, constructors or parameters that the classes contain, because the diagrams were excessively large and at the time of placing them, they were neither seen nor understood in the pdf file. Therefore, some reference UML diagrams were placed in the summary. And the complete diagrams are uploaded in this same repository in the directory Diagrams / T3. 


<h3 id="logic">Game Logic</h3>

Como se mencionó un poco más arriba, en este proyecto se analizó la jerarquía de clases que fueron entregadas en el código base.
Donde si se encontraban errores y/o violaciones de los principios SOLID se debía modificar la jerarquía e realizar una nueva implementación.

Es por esto que la lógica del programa es un poco diferente a la presentada en el código base. Primero, se creó una clase abstracta
llamada AbstractCharacter, la cual representa a todos los personajes (los controlados por el jugador y los enemigos)
e implementan la interfaz llamada ICharacter, que contiene métodos generales para ambos tipos de personajes.
Luego existe una especialización dentro de los personajes, AbstractPlayerCharacter, que representa
a todos los personajes que el jugador puede utilizar (magos, ingenieros, ladrones y caballeros). Esta clase es abstracta e implementa
los métodos de la interfaz ya que son común para todos. Esta clase abstracta también implementa otra interfaz llamada IPlayerCharacterEquipped.
Esta interfaz básicamente contiene los métodos que equipan un arma a los PlayerCharacter, y otro 
método que retorna el arma que está siendo equipada por un PlayerCharacter. Para finalizar con los PlayerCharacter, se crearon subclases
para todos los personajes que puede controlar el jugador, todas ellas heredan de AbstractPlayerCharacter y comparten sus funcionalidades
en común, además en el caso de los magos se especializó un poco más y se creó una interfaz IMage y una
clase abstracta abstractMage que la implementa y a la vez es subclase de AbstractPlayerCharacter. De momento
la única diferencia es el atributo mana, por lo cual IMage solo contiene los getters y setters de este atributo.

Otra especialización dentro de los personajes comunes son los enemigos, aquí se creó la clase Enemy, que implementa la interfaz ICharacter.
Esta clase Enemy contiene todo los comportamientos y atributos de los enemigos del juego.

En el caso de las armas se creó la interfaz IWeapon que contiene los métodos en común de todas las armas, se creó una clase
abstracta AbstractWeapon, que implementa esta interfaz y por ende los métodos en común de todas las armas, y se crearon subclases 
para todas las armas del juego (hacha, arco, cuchillo, bastón, espada) que implementan esta clase abstracta y especializan sus
métodos de "equiparse al jugador" (Se desambigua el tipo gracias al Double Dispatch) según sea el caso.

Por otro lado, en el paquete llamado controller se encuentra el controlador del juego, el inventario del jugador, algunos handlers
que manejan distintos eventos del juego y las fases que posee el juego como tal.
El controlador se encarga de ser un intermediario entre lo que ve el usuario y el modelo del programa, así que tiene la capacidad de
crear personajes del jugador, enemigos, armas, saber sus atributos, manejar el inventario del jugador, equipar armas, realizar
ataques, manejar la lógica de turnos, entre otras cosas.
La clase inventario solo agrupa los métodos de este mismo, y cabe mencionar que está en una clase separada al controlador,
solamente por que aportaba mayor claridad y legibilidad al código, y como se dijo más arriba 
todo el funcionamiento del inventario está implementado solamente en el controlador del juego.
Por otro lado los handlers manejan los eventos que ocurren durante el juego, principalmente notifican cuando
ocurre una muerte de un personaje o cuando estos se añaden a la cola de turnos.
Por último para representar las fases del juego se implementó un State Pattern. 
Se creó una clase Phase, esta clase agrupa los métodos en común de las fases y es la super clase de todas las fases del juego.
Las clases que representan las fases del juego son: StartGamePhase, WaitingForTurnPhase, TurnPhase, PlayerSelectingActionPhase,
EnemyActionPhase, PlayerWinPhase y EnemyWinPhase. En cada una de ellas se hacen override de algunos métodos de la clase Phase,
principalmente para explicitar las transiciones que se pueden hacer de una fase a otra.

Finalmente para comprobar si todas estas clases y métodos funcionan se crearon test para cada uno de ellos. Se logró obtener un coverage
del 95% en métodos, 94% en líneas y 97% en ramas. Para los test de los PlayerCharacter se hizo una clase AbstractPlayerCharacter, que contiene
algunos test que son en común para todos los PlayerCharacter, además se crearon test especializados para cada PlayerCharacter, las cuales
heredan de esta clase abstracta.

En el caso de los enemigos se creó un EnemyTest que prueba todos sus métodos. Y para las armas también se crearon test específicos para cada
una (AxeTest, BowTest, KnifeTest, StaffTest, SwordTest).

Para el controlador del juego, el inventario del jugador, los handler de eventos y las fases del juego simplemente se realizaron algunos test
que simulaban una instancia de juego (equipamiento de armas y combates a muerte a través del sistema de turnos),
donde se logró evaluar que la lógica estaba bien implementada.


<h2 id="credits">Credits</h2>

Por último me gustaría dar unos reconocimientos y agradecimientos por las imágenes y sonidos utilizados en la GUI del proyecto.
Agradezco encarecidamente a Riot Games ya que utilicé algunas imágenes de sus videos de Youtube, las cuales fueron retocadas
para dejarlas acorde a la temática del juego. A Game Freak y Nintendo por su característico sonido
al momento de presionar los botones en los nostálgicos juegos de Pokemon, el cual también fue utilizado en este juego.
A John Williams por su composición Duel Of Fates, la cual fue utilizada como música de fondo en el combate.
A Markus Persson y Mojang Studios por el sonido al momento de recibir daño los enemigos y personajes del jugador.
A Toshio Masuda por su composición Sadness and Sorrow, el octavo Soundtrack del álbum Naruto Original Soundtrack, ya que
es utilizada un pequeño fragmento de esta en la escena de derrota del juego. Y por último a Claudio Palma
por el inolvidable relato que realizo al comentar el penal de Alexis Sanchez que coronó a Chile como campeón de América en el 2015,
ya que se utiliza un fragmento de este relato en la escena de victoria del juego.


<h2 id="license">Copyright and License </h2>

![http://creativecommons.org/licenses/by/4.0/](https://i.creativecommons.org/l/by/4.0/88x31.png)

This work is licensed under a 
[Creative Commons Attribution 4.0 International License](http://creativecommons.org/licenses/by/4.0/)
