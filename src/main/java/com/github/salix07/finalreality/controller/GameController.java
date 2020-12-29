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
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Controller of the game.
 * The controller is the entity that is in charge of receiving and redirecting
 * the messages between the GUI and the model
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
    private final ICharacterAddToQueueHandler characterAddToQueueHandler;

    private final BlockingQueue<ICharacter> turns; // Turns BlockingQueue

    private final ArrayList<IPlayerCharacter> playerCharacters; // Player's party
    private final ArrayList<Enemy> enemies; // Enemy's party
    private final Inventory inventory; // Inventory from the game

    private final int numberOfPlayerCharacters; // Size of the player's party
    private final int numberOfEnemies; // Size of the enemy's party
    private int playerCharactersAlive; // Number of playerCharacters that are alive
    private int enemiesAlive; // Number of enemies that are alive

    // Hashmaps with the position of the characters by their names
    private final HashMap<String, Integer> charactersPositionsByName;
    private final HashMap<String, Integer> enemyPositionsByName;

    private Phase phase; // Phase of the game
    private final Random random; // Instance of random class (pick random target for enemy´s attack)
    private Boolean turnTaken; // Boolean value for the turn state
    private Boolean canTakeTurn; // Boolean value representing whether it is possible to take a turn
    private ICharacter activeICharacter; // Active ICharacter from the turns BlockingQueue
    private final ArrayList<String> turnsLog; // Logs with the attacks that occurred in turns

    /**
     * Creates the controller for a new game.
     *
     * @param numberOfPlayerCharacters the number of PlayerCharacters for this game
     * @param numberOfEnemies the number of Enemies for this game
     * @param inventoryMaxSize the maximum capacity of the inventory
     */
    public GameController(int numberOfPlayerCharacters, int numberOfEnemies, int inventoryMaxSize) {
        // Association between the real subscriber a.k.a handlers, and the game controller
        // (If there exist more than one type of notification we can have another handler -> a handler for each notification)
        playerCharacterDeathHandler = new PlayerCharacterDeathHandler(this);
        enemyDeathHandler = new EnemyDeathHandler(this);
        characterAddToQueueHandler = new ICharacterAddToQueueHandler(this);

        turns = new LinkedBlockingQueue<>(); // Turns BlockingQueue

        playerCharacters = new ArrayList<>(numberOfPlayerCharacters); // Player's party
        enemies = new ArrayList<>(numberOfEnemies); // Enemy's party
        inventory = new Inventory(inventoryMaxSize); // Inventory from the game

        this.numberOfPlayerCharacters = numberOfPlayerCharacters; // Size of the player's party
        this.numberOfEnemies = numberOfEnemies; // Size of the enemy's party
        playerCharactersAlive = 0; // At this moment, the number of playerCharacters that are alive is 0
        enemiesAlive = 0; // At this moment, the number of enemies that are alive is 0

        // Initialize the hashmaps with the position of the characters by their names
        charactersPositionsByName = new HashMap<>();
        enemyPositionsByName = new HashMap<>();

        setPhase(new StartGamePhase()); // The Game start at StartGamePhase
        random = new Random(); // Instance of random class (pick random target for enemy´s attack)
        turnTaken = false; // No turn is taken yet
        canTakeTurn = true; // Available to take a turn
        turnsLog = new ArrayList<>(10); // turnsLog with initialCapacity 10
    }



    /**
     * Function that set the phases in our game
     */
    public void setPhase(Phase phase) {
        this.phase = phase;
        phase.setGameController(this);
    }

    /**
     * @return the actual phase of the game
     */
    public Phase getCurrentPhase() {
        return this.phase;
    }

    /**
     * @return the name of the actual phase of the game
     */
    public String getCurrentPhaseName(){ return this.phase.getName();}

    /**
     * Functions that returns a boolean value depending on the phase than the game is
     */
    public boolean isPlayerSelectingActionPhase() {return this.phase.isPlayerSelectingActionPhase();}
    public boolean isEnemyActionPhase() {return this.phase.isEnemyActionPhase();}
    public boolean isPlayerWinPhase() {return this.phase.isPlayerWinPhase();}
    public boolean isEnemyWinPhase() {return this.phase.isEnemyWinPhase();}



    //Manage arrays containing playerCharacters and enemies

    /**
     * Returns the number of alive playerCharacters
     */
    public int getPlayerCharactersAlive() { return playerCharactersAlive; }

    /**
     * Returns the number of alive enemies
     */
    public int getEnemiesAlive() {return enemiesAlive;}

    /**
     * add an IPlayerCharacter to the playerCharacters array if there is space
     */
    private void addToPlayerCharacters(IPlayerCharacter character) {
        if (playerCharacters.size() < numberOfPlayerCharacters) { // If there is space
            playerCharacters.add(character); // Added to playerCharacters array
            playerCharactersAlive += 1; // We increased the number of playerCharacters that are alive
            charactersPositionsByName.put(getNameFrom(character), getPlayerCharactersAlive()); // add the position of this character
            character.addSubscriberForDeath(playerCharacterDeathHandler);
            character.addSubscriberForAddToQueue(characterAddToQueueHandler);
        }
    }

    /**
     * add an Enemy to the enemies array if there is space
     */
    private void addToEnemies(Enemy enemy) {
        if (enemies.size() < numberOfEnemies) { // If there is space
            enemies.add(enemy); // Added to enemies array
            enemiesAlive += 1; // We increased the number of enemies that are alive
            enemyPositionsByName.put(getNameFrom(enemy), getEnemiesAlive()); // add the position of this enemy
            enemy.addSubscriberForDeath(enemyDeathHandler);
            enemy.addSubscriberForAddToQueue(characterAddToQueueHandler);
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

    /**
     * Returns the hashmaps with the position of the PlayerCharacters by their names
     */
    public HashMap<String, Integer> getCharactersPositionsByName() {return charactersPositionsByName;}

    /**
     * Returns the hashmaps with the position of the enemies by their names
     */
    public HashMap<String, Integer> getEnemyPositionsByName() {return enemyPositionsByName;}

    /**
     * Returns the indices from the playerCharacters array of the playerCharacters that are alive
     */
    public ArrayList<Integer> getAlivePlayerCharactersIndex() {
        ArrayList<Integer> alivePlayerCharactersIndex = new ArrayList<>();
        for(int i=0; i<playerCharacters.size(); i++) {
            if(getPlayerCharacter(i).isAlive()) {
                alivePlayerCharactersIndex.add(i);
            }
        }
        return alivePlayerCharactersIndex;
    }

    /**
     * Returns the indices from the enemies array of the enemies that are alive
     */
    public ArrayList<Integer> getAliveEnemiesIndex() {
        ArrayList<Integer> aliveEnemiesIndex = new ArrayList<>();
        for(int i=0; i<enemies.size(); i++) {
            if(getEnemy(i).isAlive()) {
                aliveEnemiesIndex.add(i);
            }
        }
        return aliveEnemiesIndex;
    }



    //Create and assign objects belonging to the model (characters, enemies, weapons, etc.)

    /**
     * Creates a BlackMage and adds it to the turns queue
     * and to the playerCharacters array from this game managed by the controller
     */
    public void createBlackMage(String name, int healthPoints, int defense, int mana) {
        if (phase.isStartGamePhase()) {
            BlackMage blackMage = new BlackMage(name, healthPoints, defense, mana, turns);
            addToPlayerCharacters(blackMage);
        }
    }

    /**
     * Creates a Engineer and adds it to the turns queue
     * and to the playerCharacters array from this game managed by the controller
     */
    public void createEngineer(String name, int healthPoints, int defense) {
        if (phase.isStartGamePhase()) {
            Engineer engineer = new Engineer(name, healthPoints, defense, turns);
            addToPlayerCharacters(engineer);
        }
    }

    /**
     * Creates a Knight and adds it to the turns queue
     * and to the playerCharacters array from this game managed by the controller
     */
    public void createKnight(String name, int healthPoints, int defense) {
        if (phase.isStartGamePhase()) {
            Knight knight = new Knight(name, healthPoints, defense, turns);
            addToPlayerCharacters(knight);
        }
    }

    /**
     * Creates a Thief and adds it to the turns queue
     * and to the playerCharacters array from this game managed by the controller
     */
    public void createThief(String name, int healthPoints, int defense) {
        if (phase.isStartGamePhase()) {
            Thief thief = new Thief(name, healthPoints, defense, turns);
            addToPlayerCharacters(thief);
        }
    }

    /**
     * Creates a WhiteMage and adds it to the turns queue
     * and to the playerCharacters array from this game managed by the controller
     */
    public void createWhiteMage(String name, int healthPoints, int defense, int mana) {
        if (phase.isStartGamePhase()) {
            WhiteMage whiteMage = new WhiteMage(name, healthPoints, defense, mana, turns);
            addToPlayerCharacters(whiteMage);
        }
    }

    /**
     * Creates a Enemy and adds it to the turns queue
     * and to the enemies array from this game managed by the controller
     */
    public void createEnemy(String name, int healthPoints, int defense, int damage, int weight) {
        if (phase.isStartGamePhase()) {
            Enemy enemy = new Enemy(name, healthPoints, defense, damage, weight, turns);
            addToEnemies(enemy);
        }
    }


    /**
     * Creates a Axe and adds it to inventory from this game managed by the controller
     */
    public void createAxe(String name, int damage, int weight) {
        if (phase.isStartGamePhase()) {
            Axe axe = new Axe(name, damage, weight);
            putWeaponInInventory(axe);
        }
    }

    /**
     * Creates a Bow and adds it to inventory from this game managed by the controller
     */
    public void createBow(String name, int damage, int weight) {
        if (phase.isStartGamePhase()) {
            Bow bow = new Bow(name, damage, weight);
            putWeaponInInventory(bow);
        }
    }

    /**
     * Creates a Knife and adds it to inventory from this game managed by the controller
     */
    public void createKnife(String name, int damage, int weight) {
        if (phase.isStartGamePhase()) {
            Knife knife = new Knife(name, damage, weight);
            putWeaponInInventory(knife);
        }
    }

    /**
     * Creates a Staff and adds it to inventory from this game managed by the controller
     */
    public void createStaff(String name, int damage, int magicDamage, int weight) {
        if (phase.isStartGamePhase()) {
            Staff staff = new Staff(name, damage, magicDamage, weight);
            putWeaponInInventory(staff);
        }
    }

    /**
     * Creates a Sword and adds it to inventory from this game managed by the controller
     */
    public void createSword(String name, int damage, int weight) {
        if (phase.isStartGamePhase()) {
            Sword sword = new Sword(name, damage, weight);
            putWeaponInInventory(sword);
        }
    }



    // Know who characters are the players one and what their data is (life, attack, etc.)
    // Have knowledge of enemies and their data -> Getters
    public String getNameFrom(ICharacter character) { return character.getName(); }
    public int getHealthPointsFrom(ICharacter character) {
        return character.getHealthPoints();
    }
    public int getDefenseFrom(ICharacter character) {
        return character.getDefense();
    }
    public int getAttackDamageFrom(ICharacter character) {
        return character.getAttackDamage();
    }
    public IWeapon getWeaponFrom(IPlayerCharacter character) {return character.getEquippedWeapon();}
    public int getWeaponDamageOff(IWeapon weapon) {return weapon.getDamage();}
    public int getWeaponWeightOff(IWeapon weapon) {return weapon.getWeight();}
    public int getWeightFrom(Enemy enemy) {
        return enemy.getWeight();
    }
    public int getManaFrom(IMage mage) {return mage.getMana();}

    public String getPlayerCharacterNameByIndex(int index) { return getNameFrom(getPlayerCharacter(index)); }
    public String getEnemyNameByIndex(int index) { return getNameFrom(getEnemy(index)); }

    public int getPlayerCharacterHealthByIndex(int index) { return getHealthPointsFrom(getPlayerCharacter(index)); }
    public int getEnemyHealthByIndex(int index) { return getHealthPointsFrom(getEnemy(index)); }

    public int getPlayerCharacterDefenseByIndex(int index) { return getDefenseFrom(getPlayerCharacter(index)); }
    public int getEnemyDefenseByIndex(int index) { return getDefenseFrom(getEnemy(index)); }

    public int getPlayerCharacterAttackByIndex(int index) { return getAttackDamageFrom(getPlayerCharacter(index)); }
    public int getEnemyAttackByIndex(int index) { return getAttackDamageFrom(getEnemy(index)); }

    public int getWeightOffEquippedWeaponFrom(IPlayerCharacter character) { return getWeaponWeightOff(getWeaponFrom(character)); }
    public int getWeightOffEquippedWeaponByIndex(int index) { return getWeaponWeightOff(getWeaponFrom(getPlayerCharacter(index))); }
    public int getEnemyWeightByIndex(int index) { return getWeightFrom(getEnemy(index)); }

    public String getNameOffEquippedWeaponFrom(IPlayerCharacter character) {
        IWeapon weapon = getWeaponFrom(character);
        String weaponName = "No weapon Equipped!";
        if(weapon != null) {
            weaponName = weapon.getName();
        }
        return weaponName;
    }
    public String getNameOffEquippedWeaponByIndex(int index) {
        IWeapon weapon = getWeaponFrom(getPlayerCharacter(index));
        String weaponName = "No weapon Equipped!";
        if(weapon != null) {
            weaponName = weapon.getName();
        }
        return weaponName;
    }

    public String getCharacterClassFrom(ICharacter character) {return character.toString();}
    public String getCharacterClassByIndex(int index) { return getPlayerCharacter(index).toString(); }
    public String getEnemyClassByIndex(int index) { return getEnemy(index).toString(); }
    public String getWeaponClass(IWeapon weapon) {
        return weapon.toString();
    }


    public int getPartyHealthPoints() {
        int partyHealthPoints = 0;
        for(var character : getPlayerCharacters()) {
            partyHealthPoints += getHealthPointsFrom(character);
        }
        return partyHealthPoints;
    }

    public int getPartyDefense() {
        int partyDefense = 0;
        for(var character : getPlayerCharacters()) {
            partyDefense += getDefenseFrom(character);
        }
        return partyDefense;
    }

    public int getPartyAttackDamage() {
        int partyAttackDamage = 0;
        for(var character : getPlayerCharacters()) {
            partyAttackDamage += getAttackDamageFrom(character);
        }
        return partyAttackDamage;
    }

    public int getPartyWeight() {
        int partyWeight = 0;
        for(var character : getPlayerCharacters()) {
            partyWeight += getWeightOffEquippedWeaponFrom(character);
        }
        return partyWeight;
    }

    public boolean partyIsEquipped() {
        for(var character : getPlayerCharacters()) {
            if(character.getEquippedWeapon() == null) {
                return false;
            }
        }
        return true;
    }



    // Manage player inventory

    /**
     * Returns the actual inventory size
     */
    public int getInventorySize() {return inventory.getSize();}

    /**
     * Returns the inventory capacity (max size)
     */
    public int getInventoryMaxSize() {return inventory.getMaxSize();}

    /**
     * Returns a boolean value depending if the inventory is empty or not
     */
    public boolean isInventoryEmpty() { return inventory.isInventoryEmpty(); }

    /**
     * Returns a boolean value depending if the inventory is full or not
     */
    public boolean isInventoryFull() {return inventory.isInventoryFull();}

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

    /**
     * Returns the names of the weapons stored in the inventory
     */
    public ArrayList<String> getWeaponsName() {return inventory.getWeaponsName();}



    // Equip weapons to a PlayerCharacter

    /**
     * Function that try to equip a weapon to a IPlayerCharacter (Check if the game is in the correct phase)
     */
    public void tryToEquipPlayerCharacter(IPlayerCharacter playerCharacter, IWeapon weapon) {
        phase.tryToEquipPlayerCharacter(playerCharacter, weapon);
    }
    /**
     * Equips a weapon to a IPlayerCharacter
     */
    public void equipPlayerCharacter(IPlayerCharacter playerCharacter, IWeapon weapon) {
        IWeapon oldWeapon = playerCharacter.getEquippedWeapon(); // Old weapon from this IPlayerCharacter
        IWeapon inventoryWeapon = removeWeaponFromInventory(weapon.getName()); // Weapon to be equipped
        playerCharacter.equip(inventoryWeapon); // try to equip weapon
        IWeapon currentWeapon = playerCharacter.getEquippedWeapon(); // Current weapon from this IPlayerCharacter
        // If can't equip the weapon, the weapon that was attempted to equip is returned to the inventory
        if (currentWeapon == null) {
            putWeaponInInventory(weapon);
        }
        // If current weapon is still the old one, we return the weapon that was attempted to equip to inventory
        else if (!(currentWeapon.equals(weapon))) {
            putWeaponInInventory(weapon);
        }
        // Weapon swap
        // actualWeapon != null && actualWeapon == weapon ->
        //The weapon was equipped, we return the old weapon to inventory
        else {
            putWeaponInInventory(oldWeapon);
        }
    }


    // Game phases logic:

    /**
     * Function that try to start the game (Check if the game is in the correct phase)
     */
    public void tryToStartGame() { phase.tryToStartGame();}

    /**
     * Adds all the ICharacters to the turns queue
     */
    public void startGame() {
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

    /**
     * Method called every time a character is added to the turn queue.
     * Try to take a turn if the game is in the correct phase and it is possible to take it.
     */
    public void tryToTakeTurn() {
        if(phase.isWaitingForTurnPhase() && canTakeTurn) {
            canTakeTurn = false;
            phase.toTurnPhase();
            tryToBeginTurn();
        }
    }

    /**
     * Function that return a boolean value depending
     * if the character's turnType is from playerCharacters
     *
     * Remember, turnType returns a int value:
     * if the value is 1, it is a playerCharacter and the turn will be playerCharacter's one
     * if the value is 0, it is an enemy and the turn will be enemy's one
     */
    public boolean isPlayerCharacterTurn(ICharacter character) {return character.turnType() == 1;}

    /**
     * Function that try to begin a turn (Check if the game is in the correct phase)
     */
    public void tryToBeginTurn() {phase.tryToBeginTurn();}

    /**
     * Selects a ICharacter from the turn queue, starting his a turn
     */
    public void beginTurn() {
        if (!turnTaken) {
            turnTaken = true;
            activeICharacter = getActiveICharacter(); // Peek the first character in the queue
            if(activeICharacter.isAlive()) { // If the turns owner is alive
                addToTurnsLog(activeICharacter.getName() + " has begin his turn");
                if (isPlayerCharacterTurn(activeICharacter)) { // turnType == 1 -> PlayerCharacter's turn
                    phase.toPlayerSelectingActionPhase();
                } else { // turnType == 0 -> Enemy's turn, random attack a playerCharacter
                    phase.toEnemyActionPhase();
                    IPlayerCharacter randomAliveTarget = randomAlivePlayerCharacter();
                    tryToAttackCharacter(activeICharacter, randomAliveTarget);
                }
            }
            else { // the turns owner id dead
                endTurn(activeICharacter);
            }
        }
    }

    /**
     * Selects a random alive IPlayerCharacter from the playerCharacter array
     */
    public IPlayerCharacter randomAlivePlayerCharacter() {
        while (true) {
            int randomInt = random.nextInt(numberOfPlayerCharacters); // generate random values from 0-(numberOfPlayerCharacters-1)
            IPlayerCharacter randomAlivePlayerCharacter = getPlayerCharacter(randomInt);
            if(randomAlivePlayerCharacter.isAlive()) {
                return randomAlivePlayerCharacter;
            }
        }
    }

    /**
     * Returns the active ICharacter a.k.a the character who owns the turn
     */
    public ICharacter getActiveICharacter() {
        activeICharacter = turns.peek();
        return activeICharacter;
    }

    /**
     * Function that try to do an attack (Check if the game is in the correct phase)
     */
    public void tryToAttackCharacter(ICharacter attackerCharacter, ICharacter attackedCharacter) {
        phase.tryToAttackCharacter(attackerCharacter, attackedCharacter);
    }

    /**
     * Make one character attack another (A character can only attack on his turn)
     */
    public void attackCharacter(ICharacter attackerCharacter, ICharacter attackedCharacter) {
        addToTurnsLog(attackerCharacter.getName() + " attacked " + attackedCharacter.getName() + "!");
        int oldHealthPoints = attackedCharacter.getHealthPoints();
        attackerCharacter.attack(attackedCharacter); // Attack the target character
        int currentHealthPoints = attackedCharacter.getHealthPoints();
        addToTurnsLog("The attack did " + (oldHealthPoints-currentHealthPoints) + " damage!");
        // We end the turn of this character, eventually starting another
        endTurn(attackerCharacter);
    }

    /**
     * Function that end the turn of a character
     */
    public void endTurn(ICharacter character) {
        addToTurnsLog(character.getName() + " end his turn");
        canTakeTurn = true;
        turnTaken = false;
        turns.poll(); // The character is removed from the turns queue
        phase.toWaitingForTurnPhase();
        if (!turns.isEmpty()) { // If there are more characters in the turn queue
            tryToTakeTurn(); // We start a new turn
        }
        // A timer is started according to the weight of the character
        // When the time is up the character is added back to the queue
        character.waitTurn();
    }



    // Know immediately if the player won or lost the game.
    // A player wins when all enemies are knocked out
    // and lose if his characters are knocked out.

    /**
     * Check if the player won the game (check if the enemies that are alive are 0)
     */
    public boolean playerWin() {
        return enemiesAlive == 0;
    }

    /**
     * Check if the enemy won the game (check if the PlayerCharacters that are alive are 0)
     */
    public boolean enemyWin() {
        return playerCharactersAlive == 0;
    }


    /**
     * Method that is executed when the PlayerCharacterDeathHandler is notified about a change
     * (death of PlayerCharacter)
     */
    public void onPlayerCharacterDeath(IPlayerCharacter character) {
        playerCharactersAlive -= 1;
        turns.remove(character);
        addToTurnsLog(character.getName() + " died!");
        if (enemyWin()) {
            phase.toEnemyWinPhase();
        }
    }

    /**
     * Method that is executed when the EnemyDeathHandler is notified about a change (Enemy death)
     */
    public void onEnemyDeath(Enemy enemy) {
        enemiesAlive -= 1;
        turns.remove(enemy);
        addToTurnsLog(enemy.getName() + " died!");
        if (playerWin()) {
            phase.toPlayerWinPhase();
        }
    }

    /**
     * Returns a random weight for a enemy between 10 and 40
     */
    public int getRandomEnemyWeight() {return random.nextInt(40 -10) + 10;}
    /**
     * Returns a random weight for a PlayerCharacter's weapon
     */
    public int getRandomWeaponWeight(int weaponDamage) {
        return random.nextInt(30 -10) + 10 + (5*weaponDamage/100);
    }

    /**
     * Function that generate random enemies stats based on player's party power
     * and creates enemies with that stats
     */
    public void createRandomEnemies(int partyHealthPoints, int partyDefense, int partyAttackDamage) {
        int enemyHealthPoints = partyHealthPoints/numberOfEnemies;
        int enemyDefense = partyDefense/numberOfEnemies;
        int enemyAttackDamage = partyAttackDamage/numberOfEnemies;
        int enemyWeight = getRandomEnemyWeight();
        int deltaHealthPoints, deltaDefense, deltaAttackDamage;
        for(int i=0; i<numberOfEnemies; i++) {
            // To get a random number between a set range with min and max -> random.nextInt(max - min) + min;
            deltaHealthPoints = random.nextInt(15 - -5) - 5;
            deltaDefense = random.nextInt(5 - -5) - 5;
            deltaAttackDamage = random.nextInt(15 - -5) -5;
            enemyHealthPoints += deltaHealthPoints;
            enemyDefense += deltaDefense;
            enemyAttackDamage += deltaAttackDamage;
            if(enemyHealthPoints<0) {
                enemyHealthPoints=1;
            }
            if(enemyDefense<0) {
                enemyDefense=0;
            }
            if(enemyAttackDamage<0){
                enemyAttackDamage=1;
            }
            createEnemy(
                    "Enemy" + (i+1),
                    enemyHealthPoints,
                    enemyDefense,
                    enemyAttackDamage,
                    enemyWeight);
        }
    }

    /**
     * Function that add a string to the turns log
     */
    public void addToTurnsLog(String action) {
        if (turnsLog.size() >= 10) {
            turnsLog.remove(0);
        }
        turnsLog.add(action);
    }

    /**
     * Returns the turns log
     */
    public ArrayList<String> getTurnsLog() {return turnsLog;}
}
