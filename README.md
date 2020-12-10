Final Reality
=============

![http://creativecommons.org/licenses/by/4.0/](https://i.creativecommons.org/l/by/4.0/88x31.png)

This work is licensed under a 
[Creative Commons Attribution 4.0 International License](http://creativecommons.org/licenses/by/4.0/)

Context
-------

This project's goal is to create a (simplified) clone of _Final Fantasy_'s combat, a game developed
by [_Square Enix_](https://www.square-enix.com)
Broadly speaking for the combat the player has a group of characters to control and a group of 
enemies controlled by the computer.

---

Usage
-------

In this Second homework, the application is not yet executable. 
However, the logic of the playerCharacter, enemies, weapons, some methods 
and interactions between them are implemented. Also, the base of the game controller was implemented. 
The controller will be an intermediary between the user and the objects of the model, whose purpose 
will be to control all the messages, manipulating and redirecting those that are necessary.

It is also possible to perform a test on these classes to prove that what is implemented works.
To run these tests we must download the repository, access the Test directory located in src
and run the tests of all the classes that we want to test.

---

Supuestos Realizados
-------

Durante la realización de este proyecto se tomaron algunos supuestos que influyeron en la implementación del programa. 
Primero que todo, se eliminaron las enumeraciones de CharacterClass y WeaponType ya que aparte de no respetar
algunos principios SOLID estas enumeraciones no serían útiles al momento de necesitar una futura especialización 
de Characters o de Weapons.

Por otro lado, como en la descripción del proyecto no se hace alusión a hechizos o armas que bajen la defensa ó otros atributos
que no sean la vida de los personajes o armas se consideró que los atributos nombre, defensa y clase de cada playerCharacter se mantendría 
constante durante el juego, y en consiguiente se definieron como finales. 
Al igual que los atributos nombre, defensa, ataque, peso de cada Enemy, y los atributos nombre, ataque 
(también el daño mágico en el caso del bastón), peso y tipo de cada Weapon.

Esto nos deja en que los unicos atributos modificables durante el juego serán la vida de todos los personajes 
(y él mana en el caso de los magos).

Al momento de implementar las armas y a que tipos de personajes del jugador se podían equipar se decidió que el ladrón
pueda equiparse espadas, arcos y cuchillos, y no espadas, arcos y bastones. Esto último por que hace más sentido
que un ladrón pueda portar un cuchillo y no un bastón mágico.

Con respecto al controlador del juego, me pareció que era buena idea dejar que el usuario decidiera el tamaño de su party
y un rango máximo de enemigos. Esto respeta lo que dice el enunciado ya que si bien la cantidad de personajes del jugador
la decide el usuario, esta cantidad es fija durante esa instancia de juego y se asegura que sea exactamente esa antes de
dar inicio al mismo. 
Por el lado de los enemigos también se respeta lo dicho en el enunciado, ya que si bien el rango máximo de enemigos la
decide el usuario, esta cantidad termina siendo aleatoria y depende de cada instancia de juego, siempre siendo menor o
igual al rango máximo de enemigos especificados.

Cabe mencionar que para el inventario del jugador se tomaron algunos supuestos bastante fuertes,
si se intenta equipar un arma a un personaje que no puede llevarla, se decidió que la arma siga disponible en el inventario
si se intenta equipar un arma y efectivamente el personaje puede llevarla, la arma se remueve del inventario de forma
que solo pueda ser utilizada por un personaje a la vez. Por último si un personaje ya tiene equipada un arma y 
se intenta equipar otra arma del inventario lo que sucede es un intercambio de armas, dejando
la que tenía equipada el personaje en el inventario y equipándole la arma seleccionada 

Por último, los diagramas UML entregados en el resumen no contienen todas los métodos, constructores y parámetros
que contienen las clases, esto es debido a que los diagramas quedaban excesivamente grandes y al momento de colocarlos
en el archivo pdf no se veían ni se entendían. Por lo cual se colocaron algunos diagramas UML de referencia en el resumen
y los diagramas completos están subidos en este mismo repositorio en el directorio Diagrams/T1.

---

Explicación del Funcionamiento y Lógica del Programa
-------

Como se mencionó un poco más arriba, en este proyecto se analizó la jerarquía de clases que fueron entregadas en el código base.
Donde si se encontraban errores y/o violaciones de los principios SOLID se debía modificar la jerarquía e realizar una nueva implementación.

Es por esto que la lógica del programa es un poco diferente a la presentada en el código base. Primero, se creó una clase abstracta
llamada AbstractCharacter, la cual representa a todos los personajes (los controlados por el jugador y los enemigos)
e implementan la interfaz llamada ICharacter, que  contiene métodos generales para ambos tipos de personajes.
Luego existe una especialización dentro de los personajes, AbstractPlayerCharacter, que representa
a todos los personajes que el jugador puede utilizar (magos, ingenieros, ladrones y caballeros). Esta clase es abstracta e implementa
los métodos de la interfaz ya que son común para todos. Esta clase abstracta también implementa otra interfaz llamada IPlayerCharacterEquipped.
Esta interfaz será profundizada en el futuro, pero básicamente contiene los métodos que equipan un arma a los PlayerCharacter, y otro 
método que entrega la arma que está siendo equipada por un PlayerCharacter. Para finalizar con los PlayerCharacter, se crearon subclases
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

Por otro lado, en el paquete llamado controller se encuentra el controlador del juego, el inventario del jugador y algunos handlers
que manejan distintos eventos del juego.
El controlador se encarga de ser un intermediario entre el usuario y el modelo del programa, así que tiene la capacidad de
crear personajes del jugador, enemigos, armas, saber sus atributos, manejar el inventario del jugador, equipar armas, realizar
ataques, manejar la lógica de turnos, entre otras cosas.
La clase inventario solo agrupa los métodos de este mismo, y cabe mencionar que está en una clase separada al controlador,
solamente por que aportaba mayor claridad y legibilidad al código, y como se dijo más arriba 
todo el funcionamiento del inventario está implementado solamente en el controlador del juego.
Por último los handlers manejan los eventos que ocurren durante el juego, principalmente notifican cuando
ocurre una muerte de un personaje o cuando estos se añaden a la cola de turnos.

Finalmente para comprobar si todas estas clases y métodos funcionan se crearon test para cada uno de ellos. Se logró obtener un coverage
del 100% tantos en las lineas como en las ramas. Para los test de los PlayerCharacter se hizo una clase AbstractPlayerCharacter, que contiene
algunos test que son en común para todos los PlayerCharacter, además se crearon test especializados para cada PlayerCharacter, las cuales
heredan de esta clase abstracta.

En el caso de los enemigos se creó un EnemyTest que prueba todos sus métodos. Y para las armas también se crearon test específicos para cada
una (AxeTest, BowTest, KnifeTest, StaffTest, SwordTest).

Y para el controlador del juego, el inventario del jugador y los handler de eventos simplemente se realizaron algunos test
que simulaban una instancia de juego (equipamiento de armas y combates a muerte a través del sistema de turnos),
donde se logró evaluar que la lógica estaba bien implementada.

---
