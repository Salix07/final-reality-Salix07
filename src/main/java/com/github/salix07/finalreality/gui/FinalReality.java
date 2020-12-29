package com.github.salix07.finalreality.gui;

import com.github.salix07.finalreality.controller.GameController;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Main entry point for the application.
 * <p>
 * <Complete here with the details of the implemented application>
 *
 * @author Ignacio Slater Muñoz.
 * @author Sebastián Salinas.
 */
public class FinalReality extends Application {
  private Stage window;
  private Scene setControllerScene, mainMenuScene, playerCreatorScene, weaponCreatorScene;
  private Scene equipCharacterScene, combatScene, changeWeaponScene, playerWinScene, enemyWinScene;
  private boolean playingFinalMusic;
  private int partySize, enemyPartySize;
  private int weaponAttackDamage,weaponWeight;
  private String turnCharacterName;
  GameController controller;

  public static void main(String[] args) {
    launch(args);
  }

  /**
   * @param primaryStage
   *     the primary stage for this application, onto which the application scene can be set.
   *     Applications may create other stages, if needed, but they will not be primary stages.
   */
  @Override
  public void start(Stage primaryStage) throws FileNotFoundException {
    window = primaryStage;
    partySize = 0;
    enemyPartySize = 0;
    turnCharacterName = "";
    setControllerScene = setControllerScene();
    primaryStage.setScene(setControllerScene);
    primaryStage.setTitle("Final Reality");
    primaryStage.setResizable(false);
    primaryStage.show();
  }



  private static void playSound(String audioFilePath) {
    try {
      Clip sound = AudioSystem.getClip();
      try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
              new File(audioFilePath))) {
        sound.open(audioInputStream);
        sound.start();
      }
    } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
      e.printStackTrace();
    }
  }

  private ImageView createBackGroundImage(String backGroundImagePath, double opacity) throws FileNotFoundException {
    Image backGroundImage = new Image(new FileInputStream(backGroundImagePath));
    ImageView backGround = new ImageView(backGroundImage);
    backGround.fitWidthProperty().bind(window.widthProperty());
    backGround.isPreserveRatio();
    backGround.setOpacity(opacity);
    backGround.setMouseTransparent(true);
    return backGround;
  }

  private Label createArialRoundedMtBoldLabel(String text, int fontSize) {
    Label label = new Label(text);
    label.setFont(new Font("Arial rounded Mt Bold", fontSize));
    return label;
  }

  private TextField createArialRoundedMtBoldTextField(String promptText, int fontSize, int prefWidth) {
    TextField textField = new TextField();
    textField.setPromptText(promptText);
    textField.setFont(new Font("Arial rounded Mt Bold", fontSize));
    if(prefWidth != 0) {
      textField.setPrefWidth(prefWidth);
    }
    return textField;
  }

  private Button createArialRoundedMtBoldButton(String text, int fontSize, int prefWidth) {
    Button button = new Button(text);
    button.setFont(new Font("Arial rounded Mt Bold", fontSize));
    button.setPrefWidth(prefWidth);
    return button;
  }



  private @NotNull Scene setControllerScene() throws FileNotFoundException {
    playingFinalMusic = false;

    ImageView backGround = createBackGroundImage(
            "src/main/resources/backGrounds/settingController.png",0.5);

    Label controllerTitleLabel = createArialRoundedMtBoldLabel(
            "Welcome to Final Reality!", 60);

    Label partySizeLabel = createArialRoundedMtBoldLabel(
            "Enter the number of characters you want in your party", 30);
    Label partyRangeLabel = createArialRoundedMtBoldLabel(
            "you can choose a number between 1 and 5",25);

    TextField partySizeText = createArialRoundedMtBoldTextField(
            "Recommended size: 3",25,300);

    Button enterPartySize = createArialRoundedMtBoldButton(
            "Enter party size",25,300);
    enterPartySize.setOnAction(actionEvent -> {
      playSound("src/main/resources/sounds/pokemonButton.wav");
      partySize = Integer.parseInt(partySizeText.getText());
      if(partySize > 5) {
        partySize = 5;
      }
      if(partySize < 1) {
        partySize = 1;
      }
      partySizeText.setText("");
      partySizeText.setPromptText("");
    });

    Label enemyPartySizeLabel = createArialRoundedMtBoldLabel(
            "Enter the number of enemies you want to fight",30);
    Label enemyPartyRangeLabel = createArialRoundedMtBoldLabel(
            "you can choose a number between 1 and 10 and we will randomly generate that amount of enemies",
            16);

    TextField enemyPartySizeText = createArialRoundedMtBoldTextField(
            "Recommended amount: 6",20,300);
    Button enterEnemyPartySize = createArialRoundedMtBoldButton(
            "Enter amount of enemies",20,300);
    enterEnemyPartySize.setOnAction(actionEvent -> {
      playSound("src/main/resources/sounds/pokemonButton.wav");
      enemyPartySize = Integer.parseInt(enemyPartySizeText.getText());
      if(enemyPartySize > 10) {
        enemyPartySize = 10;
      }
      if(enemyPartySize < 1) {
        enemyPartySize = 1;
      }
      enemyPartySizeText.setText("");
      enemyPartySizeText.setPromptText("");
    });

    Label authorLabel = createArialRoundedMtBoldLabel("Made by Sebastian Salinas",10);

    Button confirmButton = createArialRoundedMtBoldButton("Confirm",25,300);
    confirmButton.setVisible(false);
    confirmButton.setOnAction(actionEvent -> {
      playSound("src/main/resources/sounds/pokemonButton.wav");
      if(partySize != 0 && enemyPartySize != 0) {
        controller = new GameController(partySize, enemyPartySize, 2*partySize);
        // Maximum 3 weapons per character, so the player can have his team equipped and
        // 2 extra weapons for each one in the inventory
        try {
          mainMenuScene = mainMenuScene();
        } catch (FileNotFoundException e) {
          e.printStackTrace();
        }
        window.setScene(mainMenuScene);
      }
    });

    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(final long now) {
        if(partySize != 0 && enemyPartySize != 0) {
          confirmButton.setVisible(true);
        }
      }
    };
    timer.start();

    GridPane textPartySizePane = new GridPane();
    textPartySizePane.addRow(0, partySizeLabel);
    textPartySizePane.addRow(1, partyRangeLabel);

    GridPane partySizePane = new GridPane();
    partySizePane.setHgap(50);
    partySizePane.addColumn(0, enterPartySize);
    partySizePane.addColumn(1, partySizeText);

    GridPane textEnemyPartySizePane = new GridPane();
    textEnemyPartySizePane.addRow(0, enemyPartySizeLabel);
    textEnemyPartySizePane.addRow(1, enemyPartyRangeLabel);

    GridPane enemyPartySizePane = new GridPane();
    enemyPartySizePane.setHgap(50);
    enemyPartySizePane.addColumn(0, enterEnemyPartySize);
    enemyPartySizePane.addColumn(1, enemyPartySizeText);

    Button genericGameButton = createArialRoundedMtBoldButton("Generate generic game",10,130);
    genericGameButton.setOnAction(event -> {
      playSound("src/main/resources/sounds/pokemonButton.wav");
      playSound("src/main/resources/sounds/duelOfFates.wav");
      controller = new GameController(5,10,10);
      controller.createBlackMage("Morgana",350,20,10);
      controller.createEngineer("Yop",400,40);
      controller.createKnight("Jarvan",500,50);
      controller.createThief("Pinera",250,10);
      controller.createWhiteMage("Lux",300,15,10);
      controller.createAxe("Storm Breaker",200, controller.getRandomWeaponWeight(200));
      controller.createBow("Last Whisper",175, controller.getRandomWeaponWeight(175));
      controller.createKnife("Yummu",100,controller.getRandomWeaponWeight(100));
      controller.createStaff("Void Staff",150,10,controller.getRandomWeaponWeight(150));
      controller.createSword("Infinity Edge",250,controller.getRandomWeaponWeight(250));
      controller.tryToEquipPlayerCharacter(controller.getPlayerCharacter(0), controller.selectWeaponFromInventory("Yummu"));
      controller.tryToEquipPlayerCharacter(controller.getPlayerCharacter(1), controller.selectWeaponFromInventory("Storm Breaker"));
      controller.tryToEquipPlayerCharacter(controller.getPlayerCharacter(2), controller.selectWeaponFromInventory("Infinity Edge"));
      controller.tryToEquipPlayerCharacter(controller.getPlayerCharacter(3), controller.selectWeaponFromInventory("Last Whisper"));
      controller.tryToEquipPlayerCharacter(controller.getPlayerCharacter(4), controller.selectWeaponFromInventory("Void Staff"));
      controller.createAxe("Black Cleaver",180, controller.getRandomWeaponWeight(180));
      controller.createBow("Dominik's Remider",175, controller.getRandomWeaponWeight(175));
      controller.createKnife("Drakktar",120,controller.getRandomWeaponWeight(120));
      controller.createStaff("Rabadon",250,10,controller.getRandomWeaponWeight(250));
      controller.createSword("Bloodthirsty",230,controller.getRandomWeaponWeight(230));
      controller.createAxe("Tiamat",200, controller.getRandomWeaponWeight(200));
      controller.createBow("Legolas Bow",250, controller.getRandomWeaponWeight(250));
      controller.createKnife("Butter Knife",100,controller.getRandomWeaponWeight(100));
      controller.createStaff("Zhonya",200,10,controller.getRandomWeaponWeight(200));
      controller.createSword("Excalibur",250,controller.getRandomWeaponWeight(250));
      int partyHealthPoints = controller.getPartyHealthPoints();
      int partyDefense = controller.getPartyDefense();
      int partyAttackDamage = controller.getPartyAttackDamage();
      controller.createRandomEnemies(partyHealthPoints,partyDefense,partyAttackDamage);
      controller.tryToStartGame();
      try {
        combatScene = combatScene();
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
      window.setScene(combatScene);
    });

    VBox ayudanteVBox = new VBox();
    ayudanteVBox.getChildren().addAll(authorLabel, genericGameButton);

    GridPane bottomPane = new GridPane();
    bottomPane.setHgap(220);
    bottomPane.addColumn(0, ayudanteVBox);
    bottomPane.addColumn(1,confirmButton);

    GridPane controllerScenePane = new GridPane();
    controllerScenePane.setAlignment(Pos.TOP_CENTER);
    controllerScenePane.setVgap(15);
    //margins around the whole grid (top/right/bottom/left)
    controllerScenePane.setPadding(new Insets(10, 10, 10, 100));
    controllerScenePane.add(controllerTitleLabel,0,0,1,1);
    controllerScenePane.add(textPartySizePane,0,3,1,1);
    controllerScenePane.add(partySizePane,0,4,1,1);
    controllerScenePane.add(textEnemyPartySizePane,0,9,1,1);
    controllerScenePane.add(enemyPartySizePane,0,10,1,1);
    controllerScenePane.add(bottomPane,0,16,1,1);

    StackPane controllerSceneStackPane = new StackPane();
    controllerSceneStackPane.getChildren().add(backGround);
    controllerSceneStackPane.getChildren().add(controllerScenePane);

    // This sets the size of the Scene to be 1200px wide, 600px high
    return new Scene(controllerSceneStackPane, 1200, 600);
  }



  private @NotNull Scene mainMenuScene() throws FileNotFoundException {
    ImageView backGround = createBackGroundImage(
            "src/main/resources/backGrounds/summonersRiftMenu.png",0.5
    );

    Label phaseLabel = createArialRoundedMtBoldLabel(controller.getCurrentPhaseName(),30);

    Label playerCreatorLabel = createArialRoundedMtBoldLabel("", 30);
    Button playerCreatorButton = createArialRoundedMtBoldButton(
            "Go to Character Creator",18,300);
    playerCreatorButton.setOnAction(event -> {
      playSound("src/main/resources/sounds/pokemonButton.wav");
      try {
        playerCreatorScene = playerCreatorScene();
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
      window.setScene(playerCreatorScene);
    });

    Label weaponCreatorLabel = createArialRoundedMtBoldLabel(
            "Create weapons for your inventory",30);
    Button weaponCreatorButton = createArialRoundedMtBoldButton(
            "Go to Weapon Creator",20,300);
    weaponCreatorButton.setOnAction(event -> {
      playSound("src/main/resources/sounds/pokemonButton.wav");
      try {
        weaponCreatorScene = weaponCreatorScene();
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
      window.setScene(weaponCreatorScene);
    });

    Label equipLabel = createArialRoundedMtBoldLabel(
            "Now you can equip your characters!",30);
    equipLabel.setVisible(false);
    Button equipButton = createArialRoundedMtBoldButton(
            "Equip Characters",20,300);
    equipButton.setVisible(false);
    equipButton.setOnAction(event -> {
      playSound("src/main/resources/sounds/pokemonButton.wav");
      if(controller.getPlayerCharactersAlive() == partySize) {
        try {
          equipCharacterScene = equipCharacterScene();
        } catch (FileNotFoundException e) {
          e.printStackTrace();
        }
        window.setScene(equipCharacterScene);
      }
    });

    GridPane menuOptionsPane = new GridPane();
    menuOptionsPane.setHgap(20); //horizontal gap in pixels
    menuOptionsPane.setVgap(50); //vertical gap in pixels
    menuOptionsPane.addRow(0, playerCreatorLabel, playerCreatorButton);
    menuOptionsPane.addRow(1, weaponCreatorLabel, weaponCreatorButton);
    menuOptionsPane.addRow(2, equipLabel, equipButton);

    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(final long now) {
        int charactersRemaining = partySize - controller.getPlayerCharactersAlive();
        int charactersAlive = controller.getPlayerCharactersAlive();

        playerCreatorLabel.setText("You must create " + charactersRemaining + " characters more");

        if(charactersRemaining == 1) {
          playerCreatorLabel.setText("You must create " + charactersRemaining + " character more");
        }

        if(charactersRemaining == 0) {
          playerCreatorLabel.setText("Your party is already full");
          playerCreatorButton.setVisible(false);
        }

        if((partySize - charactersAlive) == 0) { // All characters are created
          equipLabel.setVisible(true);
          equipButton.setVisible(true);
        }

        if(controller.isInventoryFull()) {
          weaponCreatorLabel.setText("Your inventory is now full");
          weaponCreatorButton.setVisible(false);
        }

        if(!controller.isInventoryFull()) {
          weaponCreatorLabel.setText("Create weapons for your inventory");
          weaponCreatorButton.setVisible(true);
        }

      }
    };
    timer.start();

    GridPane menuPane = new GridPane();
    menuPane.setAlignment(Pos.TOP_CENTER);
    menuPane.setVgap(50);
    menuPane.setPadding(new Insets(10, 10, 10, 10)); //margins around the whole grid (top/right/bottom/left)
    menuPane.add(phaseLabel,0,0,1,1);
    menuPane.add(menuOptionsPane,0,3,1,1);

    StackPane menuSceneStackPane = new StackPane();
    menuSceneStackPane.getChildren().add(backGround);
    menuSceneStackPane.getChildren().add(menuPane);

    // This sets the size of the Scene to be 1200px wide, 600px high
    return new Scene(menuSceneStackPane, 1200, 600);
  }



  private @NotNull Scene playerCreatorScene() throws FileNotFoundException {
    ImageView backGround = createBackGroundImage(
            "src/main/resources/backGrounds/characters.jpg",0.35
    );

    Label playerCreatorLabel = createArialRoundedMtBoldLabel("Character Creator",30);

    GridPane playerCreatorLabelPane = new GridPane();
    //margins around the whole grid (top/right/bottom/left)
    playerCreatorLabelPane.setPadding(new Insets(10, 0, 0, 100));
    playerCreatorLabelPane.addRow(0,playerCreatorLabel);

    Label characterRemainingLabel = createArialRoundedMtBoldLabel("",18);
    Label phaseLabel = createArialRoundedMtBoldLabel(controller.getCurrentPhaseName(),20);
    GridPane blackMageGrid = characterGridPane("BlackMage");
    GridPane engineerGrid = characterGridPane("Engineer");
    GridPane knightGrid = characterGridPane("Knight");
    GridPane thiefGrid = characterGridPane("Thief");
    GridPane whiteMageGrid = characterGridPane("WhiteMage");
    Button mainMenuButton = createArialRoundedMtBoldButton(
            "Go back to main menu",18,300);
    mainMenuButton.setOnAction(event -> {
      playSound("src/main/resources/sounds/pokemonButton.wav");
      window.setScene(mainMenuScene);
    });

    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(final long now) {
        characterRemainingLabel.setText(
                "You have created " + controller.getPlayerCharactersAlive() + "/" + partySize + " characters"
        );
      }
    };
    timer.start();

    GridPane playerCreatorPane = new GridPane();
    playerCreatorPane.setHgap(250); //horizontal gap in pixels
    playerCreatorPane.setVgap(15); //vertical gap in pixels
    //margins around the whole grid (top/right/bottom/left)
    playerCreatorPane.setPadding(new Insets(45, 0, 0, 100));
    playerCreatorPane.addColumn(0, characterRemainingLabel);
    playerCreatorPane.addColumn(1, phaseLabel);
    playerCreatorPane.addRow(2, blackMageGrid);
    playerCreatorPane.addRow(2, engineerGrid);
    playerCreatorPane.addRow(3, knightGrid);
    playerCreatorPane.addRow(3, thiefGrid);
    playerCreatorPane.addRow(4, whiteMageGrid);
    playerCreatorPane.addRow(4, mainMenuButton);

    StackPane playerCreatorStackPane = new StackPane();
    playerCreatorStackPane.getChildren().add(backGround);
    playerCreatorStackPane.getChildren().add(playerCreatorLabelPane);
    playerCreatorStackPane.getChildren().add(playerCreatorPane);

    // This sets the size of the Scene to be 1200px wide, 600px high
    return new Scene(playerCreatorStackPane, 1200, 600);
  }



  private @NotNull Scene weaponCreatorScene() throws FileNotFoundException {
    ImageView backGround = createBackGroundImage(
            "src/main/resources/backGrounds/weapons.png",0.35
    );

    Label weaponsCreatorLabel = createArialRoundedMtBoldLabel("Weapons creator",30);

    GridPane weaponsCreatorLabelPane = new GridPane();
    //margins around the whole grid (top/right/bottom/left)
    weaponsCreatorLabelPane.setPadding(new Insets(10, 0, 0, 100));
    weaponsCreatorLabelPane.addRow(0,weaponsCreatorLabel);

    Label inventoryLabel = createArialRoundedMtBoldLabel("", 18);

    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(final long now) {
        int inventorySize = controller.getInventorySize();
        int inventoryMaxSize = controller.getInventoryMaxSize();
        inventoryLabel.setText(
                "The weapons you create will be stored in your inventory\n"
                + "Your inventory has " + inventorySize + "/" + inventoryMaxSize + " weapons");
      }
    };
    timer.start();

    Label phaseLabel = createArialRoundedMtBoldLabel(controller.getCurrentPhaseName(),20);
    GridPane axeGrid = weaponGridPane("Axe");
    GridPane bowGrid = weaponGridPane("Bow");
    GridPane knifeGrid = weaponGridPane("Knife");
    GridPane staffGrid = weaponGridPane("Staff");
    GridPane swordGrid = weaponGridPane("Sword");

    Button mainMenuButton = createArialRoundedMtBoldButton(
            "Go back to main menu",18,300);
    mainMenuButton.setOnAction(event -> {
      playSound("src/main/resources/sounds/pokemonButton.wav");
      window.setScene(mainMenuScene);
    });

    GridPane weaponCreatorPane = new GridPane();
    weaponCreatorPane.setHgap(150); //horizontal gap in pixels
    weaponCreatorPane.setVgap(15); //vertical gap in pixels
    //margins around the whole grid (top/right/bottom/left)
    weaponCreatorPane.setPadding(new Insets(45, 0, 0, 100));
    weaponCreatorPane.addColumn(0, inventoryLabel);
    weaponCreatorPane.addColumn(1, phaseLabel);
    weaponCreatorPane.addRow(2, axeGrid);
    weaponCreatorPane.addRow(2, bowGrid);
    weaponCreatorPane.addRow(3, knifeGrid);
    weaponCreatorPane.addRow(3, staffGrid);
    weaponCreatorPane.addRow(4, swordGrid);
    weaponCreatorPane.addRow(4, mainMenuButton);

    StackPane weaponCreatorStackPane = new StackPane();
    weaponCreatorStackPane.getChildren().add(backGround);
    weaponCreatorStackPane.getChildren().add(weaponsCreatorLabelPane);
    weaponCreatorStackPane.getChildren().add(weaponCreatorPane);

    // This sets the size of the Scene to be 1200px wide, 600px high
    return new Scene(weaponCreatorStackPane, 1200, 600);
  }



  private GridPane characterGridPane(String characterClass) {
    Label characterLabel = createArialRoundedMtBoldLabel(characterClass + "'s fields",18);

    Label nameLabel = createArialRoundedMtBoldLabel("Name: ",15);
    TextField name = createArialRoundedMtBoldTextField(
            characterClass + "'s name",13,0);

    Label healthPointsLabel = createArialRoundedMtBoldLabel("HealthPoints: ",15);
    TextField healthPoints = createArialRoundedMtBoldTextField(
            characterClass + "'s health", 13,0);

    Label defenseLabel = createArialRoundedMtBoldLabel("Defense: ",15);
    TextField defense = createArialRoundedMtBoldTextField(
            characterClass + "'s defense", 13,0);

    Button createCharacterButton = createArialRoundedMtBoldButton(
            "Create " + characterClass, 12,150);

    // Enlazar funcionalidad del botón
    switch (createCharacterButton.getText()) {
      case "Create BlackMage":
        createCharacterButton.setOnAction(actionEvent -> {
          controller.createBlackMage(
                  name.getText(),
                  Integer.parseInt(healthPoints.getText()),
                  Integer.parseInt(defense.getText()),
                  10
          );
          name.setText("");
          healthPoints.setText("");
          defense.setText("");
          playSound("src/main/resources/sounds/myPowerOver9000.wav");
        });
        break;

      case "Create Engineer":
        createCharacterButton.setOnAction(actionEvent -> {
          controller.createEngineer(
                  name.getText(),
                  Integer.parseInt(healthPoints.getText()),
                  Integer.parseInt(defense.getText())
          );
          name.setText("");
          healthPoints.setText("");
          defense.setText("");
          playSound("src/main/resources/sounds/soyIndustrial.wav");
        });
        break;

      case "Create Knight":
        createCharacterButton.setOnAction(actionEvent -> {
          controller.createKnight(
                  name.getText(),
                  Integer.parseInt(healthPoints.getText()),
                  Integer.parseInt(defense.getText())
          );
          name.setText("");
          healthPoints.setText("");
          defense.setText("");
          playSound("src/main/resources/sounds/forNarnia.wav");
        });
        break;

      case "Create Thief":
        createCharacterButton.setOnAction(actionEvent -> {
          controller.createThief(
                  name.getText(),
                  Integer.parseInt(healthPoints.getText()),
                  Integer.parseInt(defense.getText())
          );
          name.setText("");
          healthPoints.setText("");
          defense.setText("");
          playSound("src/main/resources/sounds/tiemposMejores.wav");
        });
        break;

      case "Create WhiteMage":
        createCharacterButton.setOnAction(actionEvent -> {
          controller.createWhiteMage(
                  name.getText(),
                  Integer.parseInt(healthPoints.getText()),
                  Integer.parseInt(defense.getText()),
                  10
          );
          name.setText("");
          healthPoints.setText("");
          defense.setText("");
          playSound("src/main/resources/sounds/itsLeviosa.wav");
        });
        break;
    }

    GridPane characterPane = new GridPane();
    characterPane.setHgap(5); //horizontal gap in pixels
    characterPane.setVgap(3); //vertical gap in pixels
    characterPane.addRow(0, characterLabel);
    characterPane.addRow(1, nameLabel, name);
    characterPane.addRow(2, healthPointsLabel, healthPoints);
    characterPane.addRow(3, defenseLabel, defense);
    characterPane.add(createCharacterButton, 1,4,1,1);

    return characterPane;
  }

  private GridPane weaponGridPane(String weaponClass) {
    Label weaponLabel = createArialRoundedMtBoldLabel(weaponClass + "'s fields",18);

    Label nameLabel = createArialRoundedMtBoldLabel("Name: ",15);
    TextField name = createArialRoundedMtBoldTextField(
            weaponClass + "'s name",13,0);

    Label damageLabel = createArialRoundedMtBoldLabel("Damage: ",15);
    TextField damage = createArialRoundedMtBoldTextField(
            weaponClass + "'s damage",13,0);

    Button createWeaponButton = createArialRoundedMtBoldButton(
            "Create " + weaponClass,15,150);

    // Enlazar funcionalidad del botón
    switch (createWeaponButton.getText()) {
      case "Create Axe":
        createWeaponButton.setOnAction(actionEvent -> {
          weaponAttackDamage = Integer.parseInt(damage.getText());
          weaponWeight = controller.getRandomWeaponWeight(weaponAttackDamage);
          controller.createAxe(
                  name.getText(),
                  weaponAttackDamage,
                  weaponWeight
          );
          name.setText("");
          damage.setText("");
          playSound("src/main/resources/sounds/anvilSound.wav");
        });
        break;

      case "Create Bow":
        createWeaponButton.setOnAction(actionEvent -> {
          weaponAttackDamage = Integer.parseInt(damage.getText());
          weaponWeight = controller.getRandomWeaponWeight(weaponAttackDamage);
          controller.createBow(
                  name.getText(),
                  weaponAttackDamage,
                  weaponWeight
          );
          name.setText("");
          damage.setText("");
          playSound("src/main/resources/sounds/anvilSound.wav");
        });
        break;

      case "Create Knife":
        createWeaponButton.setOnAction(actionEvent -> {
          weaponAttackDamage = Integer.parseInt(damage.getText());
          weaponWeight = controller.getRandomWeaponWeight(weaponAttackDamage);
          controller.createKnife(
                  name.getText(),
                  weaponAttackDamage,
                  weaponWeight
          );
          name.setText("");
          damage.setText("");
          playSound("src/main/resources/sounds/anvilSound.wav");
        });
        break;

      case "Create Staff":
        createWeaponButton.setOnAction(actionEvent -> {
          weaponAttackDamage = Integer.parseInt(damage.getText());
          weaponWeight = controller.getRandomWeaponWeight(weaponAttackDamage);
          controller.createStaff(
                  name.getText(),
                  weaponAttackDamage,
                  10,
                  weaponWeight
          );
          name.setText("");
          damage.setText("");
          playSound("src/main/resources/sounds/anvilSound.wav");
        });
        break;

      case "Create Sword":
        createWeaponButton.setOnAction(actionEvent -> {
          weaponAttackDamage = Integer.parseInt(damage.getText());
          weaponWeight = controller.getRandomWeaponWeight(weaponAttackDamage);
          controller.createSword(
                  name.getText(),
                  weaponAttackDamage,
                  weaponWeight
          );
          name.setText("");
          damage.setText("");
          playSound("src/main/resources/sounds/anvilSound.wav");
        });
        break;
    }

    GridPane characterPane = new GridPane();
    characterPane.setHgap(5); //horizontal gap in pixels
    characterPane.setVgap(3); //vertical gap in pixels
    characterPane.addRow(0, weaponLabel);
    characterPane.addRow(1, nameLabel, name);
    characterPane.addRow(2, damageLabel, damage);
    characterPane.add(createWeaponButton, 1,4,1,1);

    return characterPane;
  }



  private @NotNull Scene equipCharacterScene() throws FileNotFoundException {
    ImageView backGround = createBackGroundImage(
            "src/main/resources/backGrounds/summonersRiftEquip.png",0.5
    );

    Label characterTitle = createArialRoundedMtBoldLabel("Your Characters:",25);
    FlowPane characterFlowPane = characterFlowPaneForEquipCharacterScene();
    characterFlowPane.setAlignment(Pos.TOP_CENTER);
    VBox characterVbox = new VBox();
    characterVbox.setSpacing(10);
    characterVbox.getChildren().addAll(characterTitle, characterFlowPane);
    characterVbox.setAlignment(Pos.TOP_CENTER);

    Label equipTitleLabel = createArialRoundedMtBoldLabel(
            "You must equip all your\n" + "characters to start the game",30);

    Label characterLabel = createArialRoundedMtBoldLabel("Enter character's name",12);
    Label weaponLabel = createArialRoundedMtBoldLabel("Enter weapon's name",12);
    HBox equipInstruction = new HBox();
    equipInstruction.setSpacing(75);
    equipInstruction.getChildren().addAll(characterLabel, weaponLabel);

    TextField characterText = createArialRoundedMtBoldTextField(
            "Character's name",13,150);
    Label equipLabel = createArialRoundedMtBoldLabel(
            "equip",10);
    TextField weaponText = createArialRoundedMtBoldTextField(
            "Weapon's name",15,150);
    Button equipButton = createArialRoundedMtBoldButton("Equip",15,75);
    equipButton.setOnAction(event -> {
      int characterPosition = controller.getCharactersPositionsByName().get(characterText.getText()) - 1;
      String weaponName = weaponText.getText();
      controller.tryToEquipPlayerCharacter(controller.getPlayerCharacter(characterPosition), controller.selectWeaponFromInventory(weaponName));
      characterText.setText("");
      weaponText.setText("");
      playSound("src/main/resources/sounds/equipWeapon.wav");
      try {
        equipCharacterScene = equipCharacterScene();
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
      window.setScene(equipCharacterScene);
    });
    HBox equipTextFields = new HBox();
    equipTextFields.setSpacing(30); //horizontal gap in pixels
    equipTextFields.getChildren().addAll(characterText,equipLabel,weaponText,equipButton);

    VBox equipLayout = new VBox();
    equipLayout.getChildren().addAll(equipInstruction,equipTextFields);

    Label moreWeaponsLabel = createArialRoundedMtBoldLabel("You can create more Weapons",18);

    Button mainMenuButton = createArialRoundedMtBoldButton(
            "Go back to main menu",12,150);
    mainMenuButton.setOnAction(event -> {
      playSound("src/main/resources/sounds/pokemonButton.wav");
      window.setScene(mainMenuScene);
    });

    HBox moreWeaponsVbox = new HBox();
    moreWeaponsVbox.setSpacing(25);
    moreWeaponsVbox.getChildren().addAll(moreWeaponsLabel, mainMenuButton);

    Label startGameLabel = createArialRoundedMtBoldLabel("Now you can start the game!",20);
    startGameLabel.setVisible(false);

    Button startGameButton = createArialRoundedMtBoldButton("Start Game",15,150);
    startGameButton.setVisible(false);
    startGameButton.setOnAction(event -> {
      playSound("src/main/resources/sounds/pokemonButton.wav");
      playSound("src/main/resources/sounds/duelOfFates.wav");
      int partyHealthPoints = controller.getPartyHealthPoints();
      int partyDefense = controller.getPartyDefense();
      int partyAttackDamage = controller.getPartyAttackDamage();
      controller.createRandomEnemies(partyHealthPoints,partyDefense,partyAttackDamage);
      controller.tryToStartGame();
      try {
        combatScene = combatScene();
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
      window.setScene(combatScene);
    });

    HBox startGameVbox = new HBox();
    startGameVbox.setSpacing(15);
    startGameVbox.getChildren().addAll(startGameLabel,startGameButton);

    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(final long now) {
        if(controller.isInventoryFull()) {
          moreWeaponsLabel.setVisible(false);
          mainMenuButton.setVisible(false);
        }
        if(!controller.isInventoryFull()) {
          moreWeaponsLabel.setVisible(true);
          mainMenuButton.setVisible(true);
        }
        if(controller.partyIsEquipped()) {
          startGameLabel.setVisible(true);
          startGameButton.setVisible(true);
        }
      }
    };
    timer.start();

    GridPane equipGridPane = new GridPane();
    equipGridPane.setVgap(10); //vertical gap in pixels
    equipGridPane.add(equipTitleLabel,0,22,1,1);
    equipGridPane.add(equipLayout,0,24,1,1);
    equipGridPane.add(moreWeaponsVbox,0,26,1,1);
    equipGridPane.add(startGameVbox,0,30,1,1);

    Label weaponsTitle = createArialRoundedMtBoldLabel("Available weapons:",25);
    FlowPane weaponFlowPane = weaponFlowPaneForEquipCharacterScene();
    weaponFlowPane.setAlignment(Pos.TOP_CENTER);
    VBox weaponVbox = new VBox();
    weaponVbox.setSpacing(10);
    weaponVbox.getChildren().addAll(weaponsTitle, weaponFlowPane);
    weaponVbox.setAlignment(Pos.TOP_CENTER);

    HBox equipCharacter = new HBox();
    equipCharacter.setSpacing(30); //horizontal gap in pixels
    equipCharacter.setLayoutX(100);
    equipCharacter.getChildren().addAll(characterVbox, equipGridPane, weaponVbox);

    StackPane equipCharacterStackPane = new StackPane();
    equipCharacterStackPane.getChildren().addAll(backGround, equipCharacter);
    // This sets the size of the Scene to be 1200px wide, 600px high
    return new Scene(equipCharacterStackPane, 1200, 600);
  }

  private FlowPane characterFlowPaneForEquipCharacterScene() {
    FlowPane characterFlowPane = new FlowPane();
    characterFlowPane.setHgap(15);
    characterFlowPane.setVgap(15);

    VBox characterStats;

    for(int i=0; i<partySize; i++) {
      String className = controller.getCharacterClassByIndex(i);
      String name = controller.getPlayerCharacterNameByIndex(i);
      int healthPoints = controller.getPlayerCharacterHealthByIndex(i);
      int defense = controller.getPlayerCharacterDefenseByIndex(i);
      String weaponName = controller.getNameOffEquippedWeaponByIndex(i);
      characterStats = characterStatsForEquipCharacterScene(className, name, healthPoints, defense, weaponName);
      characterFlowPane.getChildren().add(characterStats);
    }
    return characterFlowPane;
  }

  private VBox characterStatsForEquipCharacterScene(String className, String name, int healthPoints, int defense, String weaponName) {
    VBox characterStats = new VBox();
    Label characterClassName = createArialRoundedMtBoldLabel("Class: " + className,15);
    Label characterName = createArialRoundedMtBoldLabel("Name: " + name,15);
    Label characterHealthPoints = createArialRoundedMtBoldLabel("HealthPoints: " + healthPoints,15);
    Label characterDefense = createArialRoundedMtBoldLabel("Defense: " + defense,15);
    Label characterWeaponName = createArialRoundedMtBoldLabel("Weapon: " + weaponName,15);
    characterStats.getChildren().addAll(characterClassName, characterName, characterHealthPoints, characterDefense, characterWeaponName);
    return characterStats;
  }

  private FlowPane weaponFlowPaneForEquipCharacterScene() {
    FlowPane weaponFlowPane = new FlowPane();
    weaponFlowPane.setHgap(15);
    weaponFlowPane.setVgap(15);

    VBox weaponStats;
    int numberOfWeapons = controller.getInventorySize();
    ArrayList<String> weaponsName = controller.getWeaponsName();
    for(int i=0; i<numberOfWeapons; i++) {
      String name = weaponsName.get(i);
      String className = controller.getWeaponClass(controller.selectWeaponFromInventory(name));
      int damage = controller.getWeaponDamageOff(controller.selectWeaponFromInventory(name));
      int weight = controller.getWeaponWeightOff(controller.selectWeaponFromInventory(name));
      weaponStats = weaponStatsForEquipCharacterScene(className, name, damage, weight);
      weaponFlowPane.getChildren().add(weaponStats);
    }
    return weaponFlowPane;
  }

  private VBox weaponStatsForEquipCharacterScene(String className, String name, int damage, int weight) {
    VBox weaponStats = new VBox();
    Label weaponClassName = createArialRoundedMtBoldLabel("Class: " + className,15);
    Label weaponName = createArialRoundedMtBoldLabel("Name: " + name,15);
    Label weaponDamage = createArialRoundedMtBoldLabel("Damage: " + damage,15);
    Label weaponWeight = createArialRoundedMtBoldLabel("Weight: " + weight,15);
    weaponStats.getChildren().addAll(weaponClassName, weaponName, weaponDamage, weaponWeight);
    return weaponStats;
  }


  private Scene combatScene() throws FileNotFoundException {
    ImageView backGround = createBackGroundImage(
            "src/main/resources/backGrounds/combatScene.png",0.5
    );

    Label characterTitle = createArialRoundedMtBoldLabel("Your Characters:",25);
    FlowPane characterFlowPane = characterFlowPaneCombatScene();
    characterFlowPane.setAlignment(Pos.TOP_CENTER);
    VBox characterVbox = new VBox();
    characterVbox.setSpacing(10);
    characterVbox.getChildren().addAll(characterTitle, characterFlowPane);
    characterVbox.setAlignment(Pos.TOP_CENTER);

    Label logLabel = createArialRoundedMtBoldLabel("Turn's log",30);
    VBox logVBox = new VBox();
    logVBox.setAlignment(Pos.TOP_CENTER);
    logVBox.getChildren().addAll(logLabel);
    ArrayList<String> turnsLog = controller.getTurnsLog();
    for(var log : turnsLog) {
      Label actionLabel = createArialRoundedMtBoldLabel(log,15);
      logVBox.getChildren().add(actionLabel);
    }

    Label turnLabel = createArialRoundedMtBoldLabel("",30);
    VBox turnVbox = new VBox();
    turnVbox.setAlignment(Pos.CENTER);
    turnVbox.getChildren().add(turnLabel);

    Label whatWillDoLabel = createArialRoundedMtBoldLabel("",25);

    Label attackText = createArialRoundedMtBoldLabel("Attack Enemy",22);

    Label targetText = createArialRoundedMtBoldLabel("Enter target's name",15);

    TextField attackField = createArialRoundedMtBoldTextField("Target's name",15,150);

    Button attackButton = createArialRoundedMtBoldButton("Attack",15,85);
    attackButton.setOnAction(event -> {
      int targetPosition = controller.getEnemyPositionsByName().get(attackField.getText()) - 1;
      controller.tryToAttackCharacter(controller.getActiveICharacter(), controller.getEnemy(targetPosition));
      attackField.setText("");
      playSound("src/main/resources/sounds/zombieHurt.wav");
      if(controller.isPlayerWinPhase()) { // Is Game Over Phase
        try {
          playerWinScene = playerWinScene();
        } catch (FileNotFoundException e) {
          e.printStackTrace();
        }
        window.setScene(playerWinScene);
      }
      else { // Is Not Game Over Phase
        try {
          combatScene = combatScene();
        } catch (FileNotFoundException e) {
          e.printStackTrace();
        }
        window.setScene(combatScene);
      }
    });

    HBox attackHBox = new HBox();
    attackHBox.setSpacing(30); //horizontal gap in pixels
    attackHBox.getChildren().addAll(attackField, attackButton);

    VBox attackInstruction = new VBox();
    attackInstruction.getChildren().addAll(targetText, attackHBox);

    VBox attackOption = new VBox();
    attackOption.getChildren().addAll(attackText, attackInstruction);

    Label changeWeaponText = createArialRoundedMtBoldLabel("Change weapon",19);

    Button changeWeaponButton = createArialRoundedMtBoldButton(
            "Go to Inventory",15,150);
    changeWeaponButton.setOnAction(actionEvent -> {
      if(controller.isPlayerSelectingActionPhase() && controller.isPlayerCharacterTurn(controller.getActiveICharacter())) {
        playSound("src/main/resources/sounds/pokemonButton.wav");
        try {
          changeWeaponScene = changeWeaponScene();
        } catch (FileNotFoundException e) {
          e.printStackTrace();
        }
        window.setScene(changeWeaponScene);
      }
    });

    VBox changeWeaponOption = new VBox();
    changeWeaponOption.getChildren().addAll(changeWeaponText, changeWeaponButton);

    VBox optionsHud = new VBox();
    optionsHud.setAlignment(Pos.CENTER);
    optionsHud.setSpacing(20);
    optionsHud.setVisible(false);
    optionsHud.getChildren().addAll(whatWillDoLabel, attackOption, changeWeaponOption);

    VBox combatHud = new VBox();
    combatHud.setAlignment(Pos.CENTER);
    combatHud.setSpacing(15);
    combatHud.getChildren().addAll(turnVbox, optionsHud);

    VBox hud = new VBox();
    hud.setAlignment(Pos.TOP_CENTER);
    hud.setSpacing(75);
    hud.getChildren().addAll(logVBox, combatHud);

    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(final long now) {
        optionsHud.setVisible(false);
        if(controller.getActiveICharacter() == null) {
          turnLabel.setText("Waiting for\n" + "characters cooldowns");
        }
        else { // A turn has start
          turnCharacterName = controller.getNameFrom(controller.getActiveICharacter());
          turnLabel.setText(turnCharacterName + "'s turn begin!");
          if (controller.isPlayerCharacterTurn(controller.getActiveICharacter())) { // PlayerCharacter's turn
            whatWillDoLabel.setText("What will " + turnCharacterName + " do?");
            optionsHud.setVisible(true);
          }
          if(!controller.isPlayerCharacterTurn(controller.getActiveICharacter())) { // Enemy's turn
            if (controller.isEnemyWinPhase()) { // Enemy Win Phase
              try {
                enemyWinScene = enemyWinScene();
              } catch (FileNotFoundException e) {
                e.printStackTrace();
              }
              window.setScene(enemyWinScene);
            }
            else { // Is Not Enemy Win Phase
              playSound("src/main/resources/sounds/steveHurt.wav");
              try {
                combatScene = combatScene();
              } catch (FileNotFoundException e) {
                e.printStackTrace();
              }
              window.setScene(combatScene);
            }
          }
        }
      }
    };
    timer.start();

    Label enemiesTitle = createArialRoundedMtBoldLabel("Enemies:",25);
    FlowPane enemiesFlowPane = enemiesFlowPaneForCombatScene();
    enemiesFlowPane.setAlignment(Pos.TOP_CENTER);
    VBox enemiesVbox = new VBox();
    enemiesVbox.setSpacing(10);
    enemiesVbox.getChildren().addAll(enemiesTitle, enemiesFlowPane);
    enemiesVbox.setAlignment(Pos.TOP_CENTER);

    HBox combatHBox = new HBox();
    combatHBox.setSpacing(30); //horizontal gap in pixels
    combatHBox.setLayoutX(100);
    combatHBox.getChildren().addAll(characterVbox, hud, enemiesVbox);

    StackPane equipCharacterStackPane = new StackPane();
    equipCharacterStackPane.getChildren().addAll(backGround, combatHBox);
    // This sets the size of the Scene to be 1200px wide, 600px high
    return new Scene(equipCharacterStackPane, 1200, 600);
  }

  private FlowPane characterFlowPaneCombatScene() {
    FlowPane characterFlowPane = new FlowPane();
    characterFlowPane.setHgap(15);
    characterFlowPane.setVgap(12);

    ArrayList<Integer> playerCharactersAliveIndex = controller.getAlivePlayerCharactersIndex();
    VBox characterStats;

    for(int i : playerCharactersAliveIndex) {
      String className = controller.getCharacterClassByIndex(i);
      String name = controller.getPlayerCharacterNameByIndex(i);
      int healthPoints = controller.getPlayerCharacterHealthByIndex(i);
      int defense = controller.getPlayerCharacterDefenseByIndex(i);
      String weaponName = controller.getNameOffEquippedWeaponByIndex(i);
      int attackDamage = controller.getPlayerCharacterAttackByIndex(i);
      int weight = controller.getWeightOffEquippedWeaponByIndex(i);
      characterStats = characterStatsForCombatScene(className, name, healthPoints, defense, weaponName,attackDamage,weight);
      characterFlowPane.getChildren().add(characterStats);
    }
    return characterFlowPane;
  }

  private VBox characterStatsForCombatScene(String className, String name, int healthPoints, int defense, String weaponName, int attackDamage, int weight) {
    VBox characterStats = new VBox();
    Label characterClassName = createArialRoundedMtBoldLabel("Class: " + className,15);
    Label characterName = createArialRoundedMtBoldLabel("Name: " + name,15);
    Label characterHealthPoints = createArialRoundedMtBoldLabel("HealthPoints: " + healthPoints,15);
    Label characterDefense = createArialRoundedMtBoldLabel("Defense: " + defense,15);
    Label characterWeaponName = createArialRoundedMtBoldLabel("Weapon: " + weaponName,15);
    Label characterAttackDamage = createArialRoundedMtBoldLabel("AttackDamage: " + attackDamage,15);
    Label characterWeight = createArialRoundedMtBoldLabel("Weight: " + weight,15);
    characterStats.getChildren().addAll(characterClassName, characterName, characterHealthPoints, characterDefense, characterWeaponName, characterAttackDamage, characterWeight);
    return characterStats;
  }

  private FlowPane enemiesFlowPaneForCombatScene() {
    FlowPane enemiesFlowPane = new FlowPane();
    enemiesFlowPane.setHgap(15);
    enemiesFlowPane.setVgap(12);

    ArrayList<Integer> enemiesAliveIndex = controller.getAliveEnemiesIndex();
    VBox enemiesStats;

    for(int i : enemiesAliveIndex) {
      String className = controller.getEnemyClassByIndex(i);
      String name = controller.getEnemyNameByIndex(i);
      int healthPoints = controller.getEnemyHealthByIndex(i);
      int defense = controller.getEnemyDefenseByIndex(i);
      int attackDamage = controller.getEnemyAttackByIndex(i);
      enemiesStats = enemyStatsForCombatScene(className, name, healthPoints, defense, attackDamage);
      enemiesFlowPane.getChildren().add(enemiesStats);
    }
    return enemiesFlowPane;
  }

  private VBox enemyStatsForCombatScene(String className, String name, int healthPoints, int defense, int attackDamage) {
    VBox enemyStats = new VBox();
    Label enemyClassName = createArialRoundedMtBoldLabel("Class: " + className,15);
    Label enemyName = createArialRoundedMtBoldLabel("Name: " + name,15);
    Label enemyHealthPoints = createArialRoundedMtBoldLabel("HealthPoints: " + healthPoints,15);
    Label enemyDefense = createArialRoundedMtBoldLabel("Defense: " + defense,15);
    Label enemyAttackDamage = createArialRoundedMtBoldLabel("AttackDamage: " + attackDamage,15);
    enemyStats.getChildren().addAll(enemyClassName, enemyName, enemyHealthPoints, enemyDefense, enemyAttackDamage);
    return enemyStats;
  }

  private Scene changeWeaponScene() throws FileNotFoundException {
    ImageView backGround = createBackGroundImage(
            "src/main/resources/backGrounds/summonersRiftEquip.png",0.5
    );

    Label characterTitle = createArialRoundedMtBoldLabel("Active Character:",30);
    FlowPane characterFlowPane = characterFlowPaneForChangeWeaponScene();
    characterFlowPane.setAlignment(Pos.TOP_CENTER);
    VBox characterVbox = new VBox();
    characterVbox.setSpacing(10);
    characterVbox.getChildren().addAll(characterTitle, characterFlowPane);
    characterVbox.setAlignment(Pos.TOP_CENTER);

    Label equipTitleLabel = createArialRoundedMtBoldLabel(
            "Enter the name of the\n" + "weapon you want to equip",25);

    TextField weaponText = createArialRoundedMtBoldTextField(
            "Weapon's name",15,150);
    Button equipButton = createArialRoundedMtBoldButton("Equip",15,75);
    equipButton.setOnAction(event -> {
      String weaponName = weaponText.getText();
      String activeCharacterName = controller.getNameFrom(controller.getActiveICharacter());
      HashMap<String, Integer> charactersPositionsByName = controller.getCharactersPositionsByName();
      int activeCharacterPosition = charactersPositionsByName.get(activeCharacterName)-1;
      controller.tryToEquipPlayerCharacter(controller.getPlayerCharacter(activeCharacterPosition), controller.selectWeaponFromInventory(weaponName));
      weaponText.setText("");
      playSound("src/main/resources/sounds/equipWeapon.wav");
      try {
        changeWeaponScene = changeWeaponScene();
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
      window.setScene(changeWeaponScene);
    });
    HBox equipTextFields = new HBox();
    equipTextFields.setSpacing(30); //horizontal gap in pixels
    equipTextFields.getChildren().addAll(weaponText,equipButton);

    VBox equipLayout = new VBox();
    equipLayout.getChildren().addAll(equipTextFields);

    Button combatButton = createArialRoundedMtBoldButton("Go back to Combat",12,150);
    combatButton.setOnAction(event -> {
      playSound("src/main/resources/sounds/pokemonButton.wav");
      try {
        combatScene = combatScene();
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
      window.setScene(combatScene);
    });

    GridPane equipGridPane = new GridPane();
    equipGridPane.setVgap(10); //vertical gap in pixels
    equipGridPane.add(equipTitleLabel,0,22,1,1);
    equipGridPane.add(equipLayout,0,24,1,1);
    equipGridPane.add(combatButton,0,26,1,1);

    Label weaponsTitle = createArialRoundedMtBoldLabel("Available weapons:",30);
    FlowPane weaponFlowPane = weaponFlowPaneForEquipCharacterScene();
    weaponFlowPane.setAlignment(Pos.TOP_CENTER);
    VBox weaponVbox = new VBox();
    weaponVbox.setSpacing(10);
    weaponVbox.getChildren().addAll(weaponsTitle, weaponFlowPane);
    weaponVbox.setAlignment(Pos.TOP_CENTER);

    HBox equipCharacter = new HBox();
    equipCharacter.setSpacing(30); //horizontal gap in pixels
    equipCharacter.setLayoutX(100);
    equipCharacter.getChildren().addAll(characterVbox, equipGridPane, weaponVbox);

    StackPane equipCharacterStackPane = new StackPane();
    equipCharacterStackPane.getChildren().addAll(backGround, equipCharacter);
    // This sets the size of the Scene to be 1200px wide, 600px high
    return new Scene(equipCharacterStackPane, 1200, 600);
  }

  private FlowPane characterFlowPaneForChangeWeaponScene() {
    FlowPane characterFlowPane = new FlowPane();
    characterFlowPane.setHgap(15);
    characterFlowPane.setVgap(15);

    VBox characterStats;

    HashMap<String, Integer> charactersPositionsByName = controller.getCharactersPositionsByName();
    String activeCharacterName = controller.getNameFrom(controller.getActiveICharacter());
    int activeCharacterPosition = charactersPositionsByName.get(activeCharacterName)-1;

    String className = controller.getCharacterClassFrom(controller.getPlayerCharacter(activeCharacterPosition));
    String name = controller.getNameFrom(controller.getPlayerCharacter(activeCharacterPosition));
    int healthPoints = controller.getHealthPointsFrom(controller.getPlayerCharacter(activeCharacterPosition));
    int defense = controller.getDefenseFrom(controller.getPlayerCharacter(activeCharacterPosition));
    String weaponName = controller.getNameOffEquippedWeaponFrom(controller.getPlayerCharacter(activeCharacterPosition));
    int attackDamage = controller.getAttackDamageFrom(controller.getPlayerCharacter(activeCharacterPosition));
    int weight = controller.getWeightOffEquippedWeaponFrom(controller.getPlayerCharacter(activeCharacterPosition));
    characterStats = characterStatsForChangeWeaponScene(className, name, healthPoints, defense, weaponName, attackDamage, weight);
    characterFlowPane.getChildren().add(characterStats);
    return characterFlowPane;
  }

  private VBox characterStatsForChangeWeaponScene(String className, String name, int healthPoints, int defense, String weaponName, int attackDamage, int weight) {
    VBox characterStats = new VBox();
    Label characterClassName = createArialRoundedMtBoldLabel("Class: " + className,20);
    Label characterName = createArialRoundedMtBoldLabel("Name: " + name,20);
    Label characterHealthPoints = createArialRoundedMtBoldLabel("HealthPoints: " + healthPoints,20);
    Label characterDefense = createArialRoundedMtBoldLabel("Defense: " + defense,20);
    Label characterWeaponName = createArialRoundedMtBoldLabel("Weapon: " + weaponName,20);
    Label characterAttackDamage = createArialRoundedMtBoldLabel("AttackDamage: " + attackDamage,20);
    Label characterWeight = createArialRoundedMtBoldLabel("Weight: " + weight,20);
    characterStats.getChildren().addAll(characterClassName, characterName, characterHealthPoints, characterDefense, characterWeaponName, characterAttackDamage, characterWeight);
    return characterStats;
  }

  private Scene playerWinScene() throws FileNotFoundException {
    controller = new GameController(0,0,0);

    if(!playingFinalMusic) {
      playingFinalMusic = true;
      playSound("src/main/resources/sounds/chileCampeon.wav");
    }

    ImageView backGround = createBackGroundImage(
            "src/main/resources/backGrounds/goodEnding.jpg",0.5
    );

    Label goodEndingLabel = createArialRoundedMtBoldLabel("Good Ending",100);
    Label youWinLabel = createArialRoundedMtBoldLabel("You Win :)",100);
    VBox goodEndingVBox = new VBox();
    goodEndingVBox.setAlignment(Pos.CENTER);
    goodEndingVBox.getChildren().addAll(goodEndingLabel, youWinLabel);

    Button playAgainButton = createArialRoundedMtBoldButton("Play Again",25,300);
    playAgainButton.setOnAction(event -> {
      playSound("src/main/resources/sounds/pokemonButton.wav");
      try {
        setControllerScene = setControllerScene();
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
      window.setScene(setControllerScene);
    });
    Button closeButton = createArialRoundedMtBoldButton("Close Game", 25,300);
    closeButton.setOnAction(event -> {
      playSound("src/main/resources/sounds/pokemonButton.wav");
      Platform.exit();
    });
    HBox buttonsHBox = new HBox();
    buttonsHBox.setAlignment(Pos.CENTER);
    buttonsHBox.setSpacing(75);
    buttonsHBox.getChildren().addAll(playAgainButton,closeButton);

    VBox gameOverVBox = new VBox();
    gameOverVBox.setAlignment(Pos.CENTER);
    gameOverVBox.setSpacing(50);
    gameOverVBox.getChildren().addAll(goodEndingVBox, buttonsHBox);

    StackPane goodEndingStackPane = new StackPane();
    goodEndingStackPane.getChildren().addAll(backGround, gameOverVBox);

    // This sets the size of the Scene to be 1200px wide, 600px high
    return new Scene(goodEndingStackPane, 1200, 600);
  }

  private Scene enemyWinScene() throws FileNotFoundException {
    controller = new GameController(0,0,0);

    if(!playingFinalMusic) {
      playingFinalMusic = true;
      playSound("src/main/resources/sounds/sadnessAndSorrow.wav");
    }

    ImageView backGround = createBackGroundImage(
            "src/main/resources/backGrounds/badEnding.jpg",0.5
    );

    Label badEndingLabel = createArialRoundedMtBoldLabel("Bad Ending",100);
    Label youLoseLabel = createArialRoundedMtBoldLabel("You Lose :(",100);
    VBox badEndingVBox = new VBox();
    badEndingVBox.setAlignment(Pos.CENTER);
    badEndingVBox.getChildren().addAll(badEndingLabel, youLoseLabel);

    Button playAgainButton = createArialRoundedMtBoldButton("Play Again",25,300);
    playAgainButton.setOnAction(event -> {
      playSound("src/main/resources/sounds/pokemonButton.wav");
      try {
        setControllerScene = setControllerScene();
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
      window.setScene(setControllerScene);
    });
    Button closeButton = createArialRoundedMtBoldButton("Close Game", 25,300);
    closeButton.setOnAction(event -> {
      playSound("src/main/resources/sounds/pokemonButton.wav");
      Platform.exit();
    });
    HBox buttonsHBox = new HBox();
    buttonsHBox.setAlignment(Pos.CENTER);
    buttonsHBox.setSpacing(75);
    buttonsHBox.getChildren().addAll(playAgainButton,closeButton);

    VBox gameOverVBox = new VBox();
    gameOverVBox.setAlignment(Pos.CENTER);
    gameOverVBox.setSpacing(50);
    gameOverVBox.getChildren().addAll(badEndingVBox, buttonsHBox);

    StackPane badEndingStackPane = new StackPane();
    badEndingStackPane.getChildren().addAll(backGround, gameOverVBox);

    // This sets the size of the Scene to be 1200px wide, 600px high
    return new Scene(badEndingStackPane, 1200, 600);
  }
}