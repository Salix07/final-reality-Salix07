package com.github.salix07.finalreality.controller;

import com.github.salix07.finalreality.controller.eventHandler.*;
import com.github.salix07.finalreality.controller.phases.StartGamePhase;
import com.github.salix07.finalreality.controller.phases.Phase;
import com.github.salix07.finalreality.model.character.Enemy;
import com.github.salix07.finalreality.model.character.ICharacter;
import com.github.salix07.finalreality.model.character.player.IMage;
import com.github.salix07.finalreality.model.character.player.IPlayerCharacter;
import com.github.salix07.finalreality.model.character.player.*;
import com.github.salix07.finalreality.model.weapon.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Controller of the game.
 * The controller manages all the input received from de game's GUI.
 *
 * @author Sebastián Salinas
 * @version 1.0
 * @since 1.0
 */
public class GameController {
    /* Observer Pattern */
    // Handlers for different events
    private final PlayerCharacterDeathHandler playerCharacterDeathHandler;
    private final EnemyDeathHandler enemyDeathHandler;
    private final PlayerCharacterAddToQueueHandler playerCharacterAddToQueueHandler;
    private final EnemyAddToQueueHandler enemyAddToQueueHandler;

    private BlockingQueue<ICharacter> turns; // Turns BlockingQueue
    private BlockingQueue<Integer> typeOfTurn; // Queue with turns type (IPlayerCharacter´s turn == 1 Enemy´s turn == 0)

    private ArrayList<IPlayerCharacter> playerCharacters; // Player's party
    private ArrayList<Enemy> enemies; // Enemy's party
    private Inventory inventory; // Inventory from the game

    private final int numberOfPlayerCharacters; // Cantidad fija de playerCharacters (tamaño exacto de la party)
    private final int maximumOfEnemies; // Cantidad máxima de enemigos (tamaño máximo de la party de enemigos)
    private int playerCharactersAlive; // Cantidad de playerCharacters que están vivos
    private int enemiesAlive; // Cantidad de enemigos que están vivos

    private Phase phase; // Phase of the game
    private final Random random; // Instance of random class (pick random target for enemy´s attack)
    private int turnType; // Integer off the turn type (IPlayerCharacter´s turn == 1 Enemy´s turn == 0)
    private Boolean turnTaken; // Boolean value for the turn state
    private Boolean canTakeTurn;
    private ICharacter activeICharacter; // Active ICharacter from the turns BlockingQueue

    /**
     * Creates the controller for a new game.
     *
     * @param numberOfPlayerCharacters the number of PlayerCharacters for this game
     * @param maximumOfEnemies         the maximum number of Enemies for this game
     */
    public GameController(int numberOfPlayerCharacters, int maximumOfEnemies) {
        // Association between the real subscriber a.k.a handlers, and the game controller
        // (If there exist more than one type of notification we can have another handler -> a handler for each notification)
        playerCharacterDeathHandler = new PlayerCharacterDeathHandler(this);
        enemyDeathHandler = new EnemyDeathHandler(this);
        playerCharacterAddToQueueHandler = new PlayerCharacterAddToQueueHandler(this);
        enemyAddToQueueHandler = new EnemyAddToQueueHandler(this);

        turns = new LinkedBlockingQueue<>(); // Turns BlockingQueue
        typeOfTurn = new LinkedBlockingQueue<>(); // Queue with the turns type (IPlayerCharacter's turns or Enemy's turns)

        playerCharacters = new ArrayList<>(numberOfPlayerCharacters); // Player's party
        enemies = new ArrayList<>(maximumOfEnemies); // Enemy's party
        inventory = new Inventory(); // Inventory from the game

        this.numberOfPlayerCharacters = numberOfPlayerCharacters; // Cantidad fija de playerCharacters (tamaño exacto de la party)
        this.maximumOfEnemies = maximumOfEnemies; // Cantidad máxima de enemigos (tamaño máximo de la party de enemigos)
        playerCharactersAlive = 0; // Cantidad de playerCharacters que están vivos
        enemiesAlive = 0; // Cantidad de enemigos que están vivos

        setPhase(new StartGamePhase()); // The Game start at begin game phase
        random = new Random(); //instance of random class (pick random target for enemy´s attack)
        turnTaken = false; // No turn is taken yet
        canTakeTurn = true; // Available to take a turn
    }

    /**
     * Function that set a phase in our game
     */
    public void setPhase(Phase phase) {
        this.phase = phase;
        phase.setGameController(this);
    }

    /**
     * @return the phase of the game
     */
    public Phase getPhase() {
        return this.phase;
    }


    // Manejar arreglos que contienen a los personajes del jugador y a los enemigos

    /**
     * add an IPlayerCharacter to the playerCharacters array if there is space
     */
    private void addToPlayerCharacters(IPlayerCharacter character) {
        if (playerCharacters.size() < numberOfPlayerCharacters) { // Si queda espacio en el arreglo
            playerCharacters.add(character); // Se añade al arreglo de playerCharacters
            playerCharactersAlive += 1; // Aumentamos la cantidad de playerCharacters que están vivos
            character.addSubscriberForDeath(playerCharacterDeathHandler);
            character.addSubscriberForAddToQueue(playerCharacterAddToQueueHandler);
        }
    }

    /**
     * add an Enemy to the enemies array if there is space
     */
    private void addToEnemies(Enemy enemy) {
        if (enemies.size() < maximumOfEnemies) { // Si queda espacio en el arreglo
            enemies.add(enemy); // Se añade al arreglo de enemies
            enemiesAlive += 1; // Aumentamos la cantidad de enemies que están vivos
            enemy.addSubscriberForDeath(enemyDeathHandler);
            enemy.addSubscriberForAddToQueue(enemyAddToQueueHandler);
        }
    }

    /**
     * Returns the playerCharacters array
     */
    public ArrayList<IPlayerCharacter> getPlayerCharacters() { // Retorna el arreglo de los personajes del jugador
        return playerCharacters;
    }

    /**
     * Returns the enemies array
     */
    public ArrayList<Enemy> getEnemies() { // Retorna el arreglo de los enemigos
        return enemies;
    }

    /**
     * Returns a IPlayerCharacter from the playerCharacters array
     */
    public IPlayerCharacter getPlayerCharacter(int index) { // Retorna un personajes del jugador según el indice del arreglo
        return playerCharacters.get(index);
    }

    /**
     * Returns a Enemy from the enemies array
     */
    public Enemy getEnemy(int index) { // Retorna un enemigo según el indice del arreglo
        return enemies.get(index);
    }


    // Crear y asignar objetos pertenecientes al modelo (personajes, enemigos, armas, etc.)

    /**
     * Creates a BlackMage and adds it to the turns queue and to the playerCharacters array from this game managed by the controller
     */
    public void createBlackMage(String name, int healthPoints, int defense, int mana) { // Crea BlackMage asociado a este Game
        if (phase.isStartGamePhase()) {
            BlackMage blackMage = new BlackMage(name, healthPoints, defense, mana, turns);
            addToPlayerCharacters(blackMage);
        }
    }

    /**
     * Creates a Engineer and adds it to the turns queue and to the playerCharacters array from this game managed by the controller
     */
    public void createEngineer(String name, int healthPoints, int defense) { // Crea Engineer asociado a este Game
        if (phase.isStartGamePhase()) {
            Engineer engineer = new Engineer(name, healthPoints, defense, turns);
            addToPlayerCharacters(engineer);
        }
    }

    /**
     * Creates a Knight and adds it to the turns queue and to the playerCharacters array from this game managed by the controller
     */
    public void createKnight(String name, int healthPoints, int defense) { // Crea Knight asociado a este Game
        if (phase.isStartGamePhase()) {
            Knight knight = new Knight(name, healthPoints, defense, turns);
            addToPlayerCharacters(knight);
        }
    }

    /**
     * Creates a Thief and adds it to the turns queue and to the playerCharacters array from this game managed by the controller
     */
    public void createThief(String name, int healthPoints, int defense) { // Crea Thief asociado a este Game
        if (phase.isStartGamePhase()) {
            Thief thief = new Thief(name, healthPoints, defense, turns);
            addToPlayerCharacters(thief);
        }
    }

    /**
     * Creates a WhiteMage and adds it to the turns queue and to the playerCharacters array from this game managed by the controller
     */
    public void createWhiteMage(String name, int healthPoints, int defense, int mana) { // Crea WhiteMage asociado a este Game
        if (phase.isStartGamePhase()) {
            WhiteMage whiteMage = new WhiteMage(name, healthPoints, defense, mana, turns);
            addToPlayerCharacters(whiteMage);
        }
    }

    /**
     * Creates a Enemy and adds it to the turns queue and to the enemies array from this game managed by the controller
     */
    public void createEnemy(String name, int healthPoints, int defense, int damage, int weight) { // Crea Enemy asociado a este Game
        if (phase.isStartGamePhase()) {
            Enemy enemy = new Enemy(name, healthPoints, defense, damage, weight, turns);
            addToEnemies(enemy);
        }
    }


    /**
     * Creates a Axe and adds it to inventory from this game managed by the controller
     */
    public void createAxe(String name, int damage, int weight) { // Crea Axe y se añade al inventario de este Game
        if (phase.isStartGamePhase()) {
            Axe axe = new Axe(name, damage, weight);
            putWeaponInInventory(axe);
        }
    }

    /**
     * Creates a Bow and adds it to inventory from this game managed by the controller
     */
    public void createBow(String name, int damage, int weight) { // Crea Bow y se añade al inventario de este Game
        if (phase.isStartGamePhase()) {
            Bow bow = new Bow(name, damage, weight);
            putWeaponInInventory(bow);
        }
    }

    /**
     * Creates a Knife and adds it to inventory from this game managed by the controller
     */
    public void createKnife(String name, int damage, int weight) { // Crea Knife y se añade al inventario de este Game
        if (phase.isStartGamePhase()) {
            Knife knife = new Knife(name, damage, weight);
            putWeaponInInventory(knife);
        }
    }

    /**
     * Creates a Staff and adds it to inventory from this game managed by the controller
     */
    public void createStaff(String name, int damage, int magicDamage, int weight) { // Crea Staff y se añade al inventario de este Game
        if (phase.isStartGamePhase()) {
            Staff staff = new Staff(name, damage, magicDamage, weight);
            putWeaponInInventory(staff);
        }
    }

    /**
     * Creates a Sword and adds it to inventory from this game managed by the controller
     */
    public void createSword(String name, int damage, int weight) { // Crea Sword y se añade al inventario de este Game
        if (phase.isStartGamePhase()) {
            Sword sword = new Sword(name, damage, weight);
            putWeaponInInventory(sword);
        }
    }


    // Saber cuáles son los personajes del jugador y cuáles son sus datos (vida, ataque, etc.)
    // Tener conocimiento de los enemigos y sus datos -> Getters
    public String getNameFrom(ICharacter character) {
        return character.getName();
    }
    public int getHealthPointsFrom(ICharacter character) {
        return character.getHealthPoints();
    }
    public int getDefenseFrom(ICharacter character) {
        return character.getDefense();
    }
    public int getAttackDamageFrom(ICharacter character) {
        return character.getAttackDamage();
    }
    public int getManaFrom(IMage mage) {return mage.getMana();}
    public IWeapon getWeaponFrom(IPlayerCharacter character) {return character.getEquippedWeapon();}
    public int getWeightFrom(Enemy enemy) {
        return enemy.getWeight();
    }


    // Manejar el inventario del jugador

    /**
     * Returns a boolean value depending if the inventory is empty or not
     */
    public boolean isInventoryEmpty() { return inventory.isInventoryEmpty(); }

    /**
     * Puts an IWeapon in inventory, where the key is the weapon's name and the value is the weapon itself
     */
    public void putWeaponInInventory(IWeapon weapon) {
        if (!(weapon == null)) {
            inventory.putWeaponInInventory(weapon);
        }
    }

    /**
     * Returns a boolean value depending if the weapon is in the inventory or not
     */
    public boolean isWeaponInInventory(String weaponName) {return inventory.isWeaponInInventory(weaponName);}

    /**
     * Returns a reference of the weapon saved in the inventory
     */
    public IWeapon selectWeaponFromInventory(String weaponName) { return inventory.selectWeaponFromInventory(weaponName); }

    /**
     * Removes and returns the weapon saved in the inventory
     */
    public IWeapon removeWeaponFromInventory(String weaponName) { return inventory.removeWeaponFromInventory(weaponName); }


    // Equipar armas a un IPlayerCharacter

    /**
     * Equips a weapon to a IPlayerCharacter
     */
    public void equipPlayerCharacter(IPlayerCharacter playerCharacter, IWeapon weapon) {
        if (phase.isStartGamePhase() || phase.isSelectingActionPhase()) {
            IWeapon oldWeapon = playerCharacter.getEquippedWeapon(); // Arma antigua que posee el IPlayerCharacter
            IWeapon inventoryWeapon = removeWeaponFromInventory(weapon.getName()); // Arma que se intentará equipar
            playerCharacter.equip(inventoryWeapon); // Intentamos equipar el arma
            IWeapon actualWeapon = playerCharacter.getEquippedWeapon(); // Arma actual que posee el IPlayerCharacter
            if (actualWeapon == null) { // Si no logramos equipar el arma, se devuelve el arma que se intentó equipar al inventario
                putWeaponInInventory(weapon);
            } else if (!(actualWeapon.equals(weapon))) { // Si arma actual sigue siendo la antigua, devolvemos el arma que se intentó equipar al inventario
                putWeaponInInventory(weapon);
            }
            // Swap de armas
            else { // actualWeapon != null && actualWeapon == weapon -> Se logró equipar el arma, devolvemos el arma antigua al inventario
                putWeaponInInventory(oldWeapon);
            }
        }
    }

    /**
     * Adds all the ICharacters to the turns queue
     */
    public void beginGame() {
        if(phase.isStartGamePhase()) {
            // Asegurar antes de iniciar el juego que la cantidad de playerCharacters
            // sea exactamente la indicada al inicializar el controller
            if (playerCharactersAlive == numberOfPlayerCharacters) {
                for (var playerCharacter : playerCharacters) {
                    playerCharacter.waitTurn();
                }
                for (var enemy : enemies) {
                    enemy.waitTurn();
                }

                phase.toWaitingForTurnPhase();
            }
        }
    }

    /**
     * Método que se llama cada vez que se añade un personaje a la cola de turnos.
     * Se encarga de rellenar una cola paralela a la cola de turnos con un valor entero
     * que representa un tipo de turno (IPlayerCharacter´s turn == 1 Enemy´s turn == 0)
     */
    public void addNewTurn(int turnType) {
        typeOfTurn.add(turnType); // Añadimos el tipo de turno a la cola paralela
        tryToTakeTurn();
    }

    public void tryToTakeTurn() {
        if(phase.isWaitingForTurnPhase() && canTakeTurn) {
            canTakeTurn = false;
            System.out.println("Trying to begin a turn");
            phase.toTurnPhase();
            beginTurn();
        }
        else {
            System.out.println("Can't take the turn, keep waiting");
        }
    }

    /**
     * Selecciona a un jugador de la cola de turnos, dando inicio a un turno
     */
    public void beginTurn() {
        if (phase.isTurnPhase() && !turnTaken) {
            System.out.println(turns);
            System.out.println(typeOfTurn);
            turnTaken = true;
            activeICharacter = turns.peek(); // Se toma al primer personaje en la cola
            turnType = typeOfTurn.peek();
            System.out.println("A new turn has started");
            System.out.println(activeICharacter.getName() + " has begin his turn");
            phase.toSelectingActionPhase();
            if (activeICharacter.isAlive()) {
                if (turnType == 0) { // Es turno de enemigo, se ataca aleatoriamente a un playerCharacter
                    IPlayerCharacter randomAliveTarget = rndAlivePlayerCharacter();
                    System.out.println(activeICharacter.getName() + " attacked " + randomAliveTarget.getName());
                    attackCharacter(activeICharacter, randomAliveTarget);
                }
                else  { // turnType == 1 -> Es turno de playerCharacter
                    Enemy randomAliveTarget = rndAliveEnemy();
                    System.out.println(activeICharacter.getName() + " attacked " + randomAliveTarget.getName());
                    attackCharacter(activeICharacter, randomAliveTarget);
                }
            }

            else {
                System.out.println(activeICharacter.getName() + " try to begin his turn, but he is dead");
                endTurn(activeICharacter);
            }
        }
    }

    public IPlayerCharacter rndAlivePlayerCharacter() {
        // generate random values from 0-(numberOfPlayerCharacters-1)
        int randomInt = random.nextInt(numberOfPlayerCharacters);
        while (true) {
            IPlayerCharacter randomAlivePlayerCharacter = getPlayerCharacter(randomInt);
            if(randomAlivePlayerCharacter.isAlive()) {
                return randomAlivePlayerCharacter;
            }
            randomInt = (randomInt + 1)%numberOfPlayerCharacters;
        }
    }

    public Enemy rndAliveEnemy() {
        // generate random values from 0-(enemies.size-1)
        int randomInt = random.nextInt(enemies.size());
        while (true) {
            Enemy randomAliveEnemy = getEnemy(randomInt);
            if(randomAliveEnemy.isAlive()) {
                return randomAliveEnemy;
            }
            randomInt = (randomInt + 1)%enemies.size();
        }
    }

    /**
     * Retorna el ICharacter activo a.k.a el personaje que es dueño del turno
     */
    public ICharacter getActiveICharacter() {
        return activeICharacter;
    }

    /**
     * Hacer que un personaje ataque a otro (Un personaje solo puede atacar si es su turno)
     */
    public void attackCharacter(ICharacter attackerCharacter, ICharacter attackedCharacter) {
        if (phase.isSelectingActionPhase()) {
            attackerCharacter.attack(attackedCharacter); // Realizamos ataque al personaje objetivo
            System.out.println("PlayerCharacters Alive: " + playerCharactersAlive);
            System.out.println("Enemies Alive: " + enemiesAlive);
            System.out.println("Attacked Character Health: " + attackedCharacter.getHealthPoints());
            endTurn(attackerCharacter); // terminamos el turno de este personaje dando, eventualmente, inicio a otro
        }
    }

    public void endTurn(ICharacter character) {
        System.out.println(character.getName() + " end his turn");
        turnTaken = false;
        canTakeTurn = true;
        turns.poll(); // Se saca al personaje de la cola
        typeOfTurn.poll();
        phase.toWaitingForTurnPhase();
        System.out.println("turn is free and can begin a new one");
        if (!turns.isEmpty()) { // Si hay más personajes en la cola de turnos
            tryToTakeTurn(); // Le damos inicio a un nuevo turno
        }
        if (character.isAlive()) {
            character.waitTurn(); // Se inicia un temporizador acorde al peso para añadirse nuevamente a la cola
        }
    }

    
    // Saber inmediatamente si el jugador ganó o perdió el juego.
    // Un jugador gana cuando todos los enemigos quedan fuera de combate y pierde si sus personajes quedan fuera de combate.

    /**
     * Verifica si es que el jugador ganó el juego (ver si los enemigos que están vivos son 0)
     */
    public boolean playerWin() {
        return enemiesAlive == 0;
    }

    /**
     * Verifica si es que el enemigo ganó el juego (ver si los personajes del jugador que están vivos son 0)
     */
    public boolean enemyWin() {
        return playerCharactersAlive == 0;
    }

    /**
     * Método para dar fin al juego, de momento solo imprime "Game Over"
     */
    private void gameOver() {
        if (phase.isGameOverPhase()) {
            System.out.println("Game Over");
        }
    }

    /**
     * Método que se ejecuta cuando se notifica al PlayerCharacterDeathHandler sobre un cambio (muerte de PlayerCharacter)
     */
    public void onPlayerCharacterDeath(IPlayerCharacter playerCharacter) {
        playerCharactersAlive -= 1;
        if (enemyWin()) {
            phase.toGameOverPhase();
            gameOver();
        }
    }

    /**
     * Método que se ejecuta cuando se notifica al EnemyDeathHandler sobre un cambio (muerte de Enemy)
     */
    public void onEnemyDeath(Enemy enemy) {
        enemiesAlive -= 1;
        if (playerWin()) {
            phase.toGameOverPhase();
            gameOver();
        }
    }
}
