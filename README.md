<h1>Final-Reality</h1>

[![License: CC BY 4.0](https://img.shields.io/badge/License-CC%20BY%204.0-lightgrey.svg?style=flat)](http://creativecommons.org/licenses/by/4.0/)
[![Language](https://img.shields.io/badge/Language-Java-blue.svg?style=flat)](https://www.java.com/en/)
[![Use](https://img.shields.io/badge/Use-JavaFX-blue.svg?style=flat&)](https://openjfx.io)
[![Release](https://img.shields.io/badge/Release-v1.0-green.svg?style=flat)](https://github.com/salistito/Final-Reality)


<h2>Table of contents</h2>

- <a href="#context">Context</a>
- <a href="#requirements-usage">Requirements and Usage (Quick Start)</a>
- <a href="#game-details">Game Details:</a>
    - <a href="#assumptions">Assumptions</a>
    - <a href="#logic">Game Logic</a>
- <a href="#credits">Credits</a>
- <a href="#license">Copyright and License</a>

<h2 id="context">Context</h2>

This project's goal is to create a (simplified) clone of _Final Fantasy_'s combat, a game developed
by [_Square Enix_](https://www.square-enix.com)
Broadly speaking for the combat the player has a group of characters to control and a group of 
enemies controlled by the computer.


<h2 id="requirements-usage">Requirements and Usage (Quick Start)</h2>

Para poder disfrutar de este juego primero debes descargar el código y abrir la carpeta descargada como un nuevo proyecto de
IntelliJ, una vez cargado el proyecto debes hacer click derecho en la clase FinalReality ubicada en la ruta
src/main/java/com.github.salix07/finalreality/gui y seleccionar la opción "run", esto abrirá una ventana y se mostrará la GUI del juego.

Entrando más en detalle, la ventana mostrará un mensaje de bienvenida y un par de reglas, se te pedirá que ingreses la cantidad
de personajes que formarán parte de tu equipo y la cantidad de enemigos que quieres enfrentar, debes rellenar los dos campos y
hacer click en los botones "Enter party size" y "Enter amount of enemies", una vez ingresados estos datos aparecerá un botón
de confirmación con el texto "Confirm", si se hace click en este botón se confirmarán los datos y se pasará al menú principal.
Cabe destacar que en la esquina inferior izquierda hay un botón "Generate generic game", que generara una combate genérico
entre 5 personajes aliados, equipados con sus respectivas armas, y 10 enemigos aleatorios.
 
Ya estando en el menú principal aparecerán 2 opciones, la primera te traslada a la escena de creación de personajes, en ella
podrás crear exactamente la cantidad de personajes que escogiste al inicio, los personajes que puedes crear son
Magos oscuros, Ingenieros, Caballeros, Ladrones y Magos blancos, debes elegirles un nombre, puntos de vida y defensa 
(en la esquina inferior derecha hay un texto con los rangos adecuados de estos atributos para tener un juego bien balanceado).
Si ya decidiste que personajes crear y que estadísticas darles debes hacer click en los botones de creación respectivos.
Una vez creados todos tus personajes debes hacer click en el botón "Back to main menu", que te devolverá al menú principal.
 
La segunda opción del menú principal te traslada a la escena de creación de armas.
Aquí de manera similar a la escena anterior puedes crear las armas que utilizaras durante el juego, las armas disponibles son
Hachas, Arcos, Cuchillos, Bastones mágicos y Espadas, debes elegirles un nombre y el daño que realizan
(nuevamente, de manera similar, en la esquina inferior derecha hay un texto con el rango adecuado para tener un juego bien balanceado).
Si ya se decidió que armas crear se debe hacer click en los botones de creación respectivos.
Y nuevamente está disponible un botón con el texto "Back to main menu", que te devuelve al menú principal.
 
Solo una vez creada la cantidad de personajes que se ingresó aparecerá una tercera opción en el menú principal,
esta opción te lleva a la escena de equipamiento de armas, en ella puedes equipar a los personajes que creaste con las armas
que también decidiste crear, pero no te preocupes si todavía no creas las armas ya que puedes volver al menu principal e ir
a la escena de creación de armas y crearlas o continuar creando unas nuevas, siempre respetando la capacidad máxima de
tu inventario (en este caso está configurado para soportar 2 armas por personaje creado, por lo cual si creas armas y
equipas a tus personajes es posible volver a llenar el inventario con las armas faltantes). Cabe destacar que para equipar
una arma a un personaje se debe ingresar el nombre del personaje en la caja de texto correspondiente, el nombre del arma
en la caja de texto correspondiente y apretar el botón "equip". Cabe recalcar que los personajes pueden equiparse ciertas
armas o no dependiendo de su clase, a continuación haré un breve resumen:

Mago Oscuro: Puede equiparse un Cuchillo y un Bastón
Ingeniero: Puede equiparse un Hacha y un Arco
Caballero: Puede equiparse un Hacha, un Cuchillo y una Espada
Ladrón: Puede equiparse un Arco un Cuchillo y una Espada
Mago Blanco: Puede equiparse un Bastón

 
Una vez equipados todos los personajes aparecerá un botón con el texto "Start game", que comenzará el combate y te trasladará
a la escena de combate. En ella podrás ver al lado izquierdo los personajes de tu equipo y sus datos,
al lado derecho los enemigos y sus datos y al centro un historial con las acciones ocurridas en los turnos.
 
Cada vez que sea el turno de un personaje de tu equipo aparecerá un menú con las acciones que puedes realizar.
La primera acción es la de atacar a un enemigo, si quieres realizar un ataque debes ingresar el nombre del enemigo y
apretar el botón "attack". Cabe destacar que cuando un personaje o enemigo muere este desaparece de las visual.
Sin embargo si se recuerda el nombre es posible realizar un ataque a un enemigo muerto, este ataque no hará ningún efecto
por lo cual perderás tu turno en vano, en el caso de los enemigos estos nunca atacarán a un personaje que esté muerto.
La segunda acción es para cambiar el arma que porta tu personaje,
si quieres cambiarla debes hacer click en el botón "Go to inventory", esto te llevará a una escena en que se mostrará
el personaje que es dueño del turno y las armas disponibles que hay en tu inventario.
El equipamiento de la nueva arma se realiza de la misma manera que se hizo el equipamiento en la escena de equipamiento
de personajes. Una vez conforme con el arma seleccionada se puede volver al combate con el botón "Go back to combat".
El turno de tu personaje solo termina cuando realizar un ataque y puedes cambiar de armas cuantas veces quieras.
 
El combate terminará eventualmente y puedes ganar o perder, en ambos casos se mostrará una escena acorde a lo que haya
ocurrido, en ella habrá un botón "Play again" que te traslada a la primera escena del juego para que puedas jugar de nuevo
eligiendo una nueva cantidad de personajes y enemigos, para posteriormente cambiar sus estadísticas si quieres.
Y también un botón "Close game" que cierra el juego.


<h2 id="game-details">Game Details:</h2>

<h3 id="assumptions">Assumptions</h3>

Durante la realización de este proyecto se tomaron algunos supuestos que influyeron en la implementación del programa. 
Primero que todo, se eliminaron las enumeraciones CharacterClass y WeaponType ya que aparte de no respetar
algunos principios SOLID estas enumeraciones no son tan útiles al momento de necesitar una futura especialización 
de Characters o de Weapons.

Por otro lado, como en la descripción del proyecto no se hace alusión a hechizos o armas que bajen la defensa ó otros atributos
que no sean la vida de los personajes se consideró que los atributos nombre, defensa y la clase de cada playerCharacter se mantendrá
constante durante el juego, y en consiguiente se definieron como finales. 
Al igual que los atributos nombre, defensa, ataque, peso de cada Enemy, y los atributos nombre, ataque, peso
(también el daño mágico en el caso del bastón) y clase de cada Weapon.

Esto nos deja en que los unicos atributos modificables durante el juego serán la vida de todos los personajes 
(y él mana en el caso de los magos).

Al momento de implementar las armas y a que tipos de personajes del jugador se podían equipar se decidió que el ladrón
pueda equiparse espadas, arcos y cuchillos, y no espadas, arcos y bastones. Esto último por que hace más sentido
que un ladrón pueda portar un cuchillo y no un bastón mágico.

Con respecto al controlador del juego, me pareció que era buena idea dejar que el usuario decidiera con la cantidad de personajes
que quisiera jugar, y la cantidad de enemigos que quisiera enfrentar.
Esto respeta lo que dice el enunciado ya que si bien la cantidad de personajes del jugador
la decide el usuario, esta cantidad es fija durante esa instancia de juego y se asegura que sea exactamente esa antes de
dar inicio al mismo. 
Por el lado de los enemigos también se respeta lo dicho en el enunciado, ya que si bien la cantidad de enemigos la
decide el usuario, estos enemigos terminan siendo generados con atributos aleatorios y varían en cada instancia de juego.

Cabe mencionar que para el inventario del jugador se tomaron algunos supuestos bastante fuertes.
Primero, si se intenta equipar un arma a un personaje que no puede llevarla, se decidió que el arma siga disponible en el inventario.
Si se intenta equipar un arma y efectivamente el personaje puede llevarla, el arma se remueve del inventario de forma
que solo pueda ser utilizada por un personaje a la vez. Por último si un personaje ya tiene equipada un arma y 
se intenta equipar otra arma del inventario lo que sucede es un intercambio de armas, dejando
la que tenía equipada el personaje en el inventario y equipándole el arma seleccionada 

Por último, los diagramas UML entregados en el resumen no contienen todas los métodos, constructores y parámetros
que contienen las clases, esto es debido a que los diagramas quedaban excesivamente grandes y al momento de colocarlos
en el archivo pdf no se veían ni se entendían. Por lo cual se colocaron algunos diagramas UML de referencia en el resumen
y los diagramas completos están subidos en este mismo repositorio en el directorio Diagrams/T3.



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
