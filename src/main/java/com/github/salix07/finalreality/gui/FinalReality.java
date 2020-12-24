package com.github.salix07.finalreality.gui;

import com.github.salix07.finalreality.controller.GameController;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
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
  private Scene mainMenuScene, playerCreatorScene, weaponCreatorScene, equipScene, combatScene;
  int partySize, enemyPartySize;
  private HashMap<Integer, String> charactersNameClass;
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
    charactersNameClass = new HashMap<>();
    Scene scene = setControllerScene();
    primaryStage.setScene(scene);
    primaryStage.setTitle("Final Reality");
    primaryStage.setResizable(false);
    primaryStage.show();
    playSound();
  }


  private static void playSound() {
    String audioFilePath = "mediaFiles/musicaAscensor.wav";
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



  private @NotNull Scene setControllerScene() throws FileNotFoundException {
    Image backGroundImage = new Image(new FileInputStream("mediaFiles/settingController.png"));
    ImageView backGround = new ImageView(backGroundImage);
    backGround.fitWidthProperty().bind(window.widthProperty());
    backGround.isPreserveRatio();
    backGround.setViewOrder(0.5);
    backGround.setOpacity(0.5);
    backGround.setMouseTransparent(true);

    Label controllerTitleLabel = new Label("Welcome to Final Reality!");
    controllerTitleLabel.setFont(new Font("Arial rounded Mt Bold", 60));

    Label partySizeLabel = new Label("Enter the number of characters you want in your party");
    Label partyRangeLabel = new Label("you can choose a number between 1 and 5");
    partySizeLabel.setFont(new Font("Arial rounded Mt Bold", 30));
    partyRangeLabel.setFont(new Font("Arial rounded Mt Bold", 25));

    TextField partySizeText = new TextField();
    partySizeText.setPromptText("Recommended size: 5");
    partySizeText.setFont(new Font("Arial rounded Mt Bold", 25));
    partySizeText.setPrefWidth(300);

    Button enterPartySize = new Button("Enter party size");
    enterPartySize.setFont(new Font("Arial rounded Mt Bold", 25));
    enterPartySize.setPrefWidth(300);
    enterPartySize.setOnAction(actionEevent -> {
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

    Label enemyPartySizeLabel = new Label("Enter the number of enemies you want to fight");
    Label enemyPartyRangeLabel = new Label("you can choose a number between 5 and 10 and we will randomly generate that amount of enemies");
    enemyPartySizeLabel.setFont(new Font("Arial rounded Mt Bold", 30));
    enemyPartyRangeLabel.setFont(new Font("Arial rounded Mt Bold", 16));

    TextField enemyPartySizeText = new TextField();
    enemyPartySizeText.setPromptText("Recommended amount: 10");
    enemyPartySizeText.setFont(new Font("Arial rounded Mt Bold", 20));
    enemyPartySizeText.setPrefWidth(300);

    Button enterEnemyPartySize = new Button("Enter amount of enemies");
    enterEnemyPartySize.setFont(new Font("Arial rounded Mt Bold", 20));
    enterEnemyPartySize.setPrefWidth(300);
    enterEnemyPartySize.setOnAction(actionEevent -> {
      enemyPartySize = Integer.parseInt(enemyPartySizeText.getText());
      if(enemyPartySize > 5) {
        enemyPartySize = 5;
      }
      if(enemyPartySize < 1) {
        enemyPartySize = 1;
      }
      enemyPartySizeText.setText("");
      enemyPartySizeText.setPromptText("");
    });

    Label authorLabel = new Label("Made by Sebastian Salinas");
    authorLabel.setFont(new Font("Arial rounded Mt Bold", 10));

    Button confirmButton = new Button("Confirm");
    confirmButton.setVisible(false);
    confirmButton.setFont(new Font("Arial rounded Mt Bold", 25));
    confirmButton.setPrefWidth(300);
    confirmButton.setOnAction(actionEvent -> {
      if(partySize != 0 && enemyPartySize != 0) {
        controller = new GameController(partySize, enemyPartySize);
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

    GridPane bottomPane = new GridPane();
    bottomPane.setHgap(200);
    bottomPane.addColumn(0,authorLabel);
    bottomPane.addColumn(1,confirmButton);

    GridPane controllerScenePane = new GridPane();
    controllerScenePane.setAlignment(Pos.TOP_CENTER);
    controllerScenePane.setVgap(15);
    controllerScenePane.setPadding(new Insets(10, 10, 10, 100)); //margins around the whole grid
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
    Image backGroundImage = new Image(new FileInputStream("mediaFiles/summonersRiftMenu.png"));
    ImageView backGround = new ImageView(backGroundImage);
    backGround.fitWidthProperty().bind(window.widthProperty());
    backGround.isPreserveRatio();
    backGround.setViewOrder(0.5);
    backGround.setOpacity(0.5);
    backGround.setMouseTransparent(true);

    Label phaseLabel = new Label(controller.getCurrentPhaseName());
    phaseLabel.setFont(new Font("Arial rounded Mt Bold", 30));

    Label playerCreatorLabel = new Label();
    playerCreatorLabel.setFont(new Font("Arial rounded Mt Bold", 30));
    Button playerCreatorButton = new Button("Go to PlayerCharacter creator");
    playerCreatorButton.setFont(new Font("Arial rounded Mt Bold", 18));
    playerCreatorButton.setPrefWidth(300);
    playerCreatorButton.setOnAction(event -> {
      try {
        playerCreatorScene = playerCreatorScene();
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
      window.setScene(playerCreatorScene);
    });

    Label weaponCreatorLabel = new Label("Create weapons for your inventory");
    weaponCreatorLabel.setFont(new Font("Arial rounded Mt Bold", 30));
    Button weaponCreatorButton = new Button("Go to Weapon creator");
    weaponCreatorButton.setFont(new Font("Arial rounded Mt Bold", 20));
    weaponCreatorButton.setPrefWidth(300);
    weaponCreatorButton.setOnAction(event -> {
      try {
        weaponCreatorScene = weaponCreatorScene();
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
      window.setScene(weaponCreatorScene);
    });

    Label equipLabel = new Label();
    equipLabel.setText("Now you can equip your characters!");
    equipLabel.setVisible(false);
    equipLabel.setFont(new Font("Arial rounded Mt Bold", 27));
    Button equipButton = new Button("Equip characters");
    equipButton.setVisible(false);
    equipButton.setFont(new Font("Arial rounded Mt Bold", 20));
    equipButton.setPrefWidth(300);
    equipButton.setOnAction(event -> {
      if(controller.getPlayerCharactersAlive() == controller.getNumberOfPlayerCharacters()) {
        equipScene = equipCharacterScene();
        window.setScene(equipScene);
      }
    });

    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(final long now) {
        playerCreatorLabel.setText("You must create " + (controller.getNumberOfPlayerCharacters() - controller.getPlayerCharactersAlive()) + " PlayerCharacters more");
        if((controller.getNumberOfPlayerCharacters() - controller.getPlayerCharactersAlive()) == 0) {
          equipLabel.setVisible(true);
          equipButton.setVisible(true);
        }
      }
    };
    timer.start();

    GridPane menuOptionsPane = new GridPane();
    menuOptionsPane.setHgap(20); //horizontal gap in pixels
    menuOptionsPane.setVgap(50); //vertical gap in pixels
    menuOptionsPane.addRow(0, playerCreatorLabel, playerCreatorButton);
    menuOptionsPane.addRow(1, weaponCreatorLabel, weaponCreatorButton);
    menuOptionsPane.addRow(2, equipLabel, equipButton);

    GridPane menuPane = new GridPane();
    menuPane.setAlignment(Pos.TOP_CENTER);
    menuPane.setVgap(50);
    menuPane.setPadding(new Insets(10, 10, 10, 10)); //margins around the whole grid
    menuPane.add(phaseLabel,0,0,1,1);
    menuPane.add(menuOptionsPane,0,3,1,1);

    StackPane menuSceneStackPane = new StackPane();
    menuSceneStackPane.getChildren().add(backGround);
    menuSceneStackPane.getChildren().add(menuPane);

    // This sets the size of the Scene to be 1200px wide, 600px high
    return new Scene(menuSceneStackPane, 1200, 600);
  }



  private @NotNull Scene playerCreatorScene() throws FileNotFoundException {
    Image backGroundImage = new Image(new FileInputStream("mediaFiles/characters.jpg"));
    ImageView backGround = new ImageView(backGroundImage);
    backGround.fitWidthProperty().bind(window.widthProperty());
    backGround.isPreserveRatio();
    backGround.setViewOrder(0.5);
    backGround.setOpacity(0.35);
    backGround.setMouseTransparent(true);

    Label playerCreatorLabel = new Label("Player Creator");
    playerCreatorLabel.setFont(new Font("Arial rounded Mt Bold", 30));

    GridPane playerCreatorLabelPane = new GridPane();
    playerCreatorLabelPane.setPadding(new Insets(10, 0, 0, 100)); //margins around the whole grid (top/right/bottom/left)
    playerCreatorLabelPane.addRow(0,playerCreatorLabel);

    Label characterRemainingLabel = new Label();
    characterRemainingLabel.setFont(new Font("Arial rounded Mt Bold", 18));
    Label phaseLabel = new Label(controller.getCurrentPhaseName());
    phaseLabel.setFont(new Font("Arial rounded Mt Bold", 18));
    GridPane blackMageGrid = characterGridPane("BlackMage");
    GridPane engineerGrid = characterGridPane("Engineer");
    GridPane knightGrid = characterGridPane("Knight");
    GridPane thiefGrid = characterGridPane("Thief");
    GridPane whitheMageGrid = characterGridPane("WhiteMage");
    Button mainMenuButton = new Button("Go back to main menu");
    mainMenuButton.setFont(new Font("Arial rounded Mt Bold", 18));
    mainMenuButton.setPrefWidth(300);
    mainMenuButton.setOnAction(event ->
            window.setScene(mainMenuScene));

    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(final long now) {
        characterRemainingLabel.setText("You have created " + controller.getPlayerCharactersAlive() + "/" + controller.getNumberOfPlayerCharacters() + " PlayerCharacters");
      }
    };
    timer.start();

    GridPane playerCreatorPane = new GridPane();
    playerCreatorPane.setHgap(250); //horizontal gap in pixels
    playerCreatorPane.setVgap(15); //vertical gap in pixels
    playerCreatorPane.setPadding(new Insets(45, 0, 0, 100)); //margins around the whole grid (top/right/bottom/left)
    playerCreatorPane.addColumn(0, characterRemainingLabel);
    playerCreatorPane.addColumn(1, phaseLabel);
    playerCreatorPane.addRow(2, blackMageGrid);
    playerCreatorPane.addRow(2, engineerGrid);
    playerCreatorPane.addRow(3, knightGrid);
    playerCreatorPane.addRow(3, thiefGrid);
    playerCreatorPane.addRow(4, whitheMageGrid);
    playerCreatorPane.addRow(4, mainMenuButton);

    StackPane playerCreatorStackPane = new StackPane();
    playerCreatorStackPane.getChildren().add(backGround);
    playerCreatorStackPane.getChildren().add(playerCreatorLabelPane);
    playerCreatorStackPane.getChildren().add(playerCreatorPane);

    // This sets the size of the Scene to be 1200px wide, 600px high
    return new Scene(playerCreatorStackPane, 1200, 600);
  }



  private @NotNull Scene weaponCreatorScene() throws FileNotFoundException {
    Image backGroundImage = new Image(new FileInputStream("mediaFiles/weapons.png"));
    ImageView backGround = new ImageView(backGroundImage);
    backGround.fitWidthProperty().bind(window.widthProperty());
    backGround.isPreserveRatio();
    backGround.setViewOrder(0.5);
    backGround.setOpacity(0.35);
    backGround.setMouseTransparent(true);

    Label weaponsCreatorLabel = new Label("Weapons creator");
    weaponsCreatorLabel.setFont(new Font("Arial rounded Mt Bold", 30));

    GridPane weaponsCreatorLabelPane = new GridPane();
    weaponsCreatorLabelPane.setPadding(new Insets(10, 0, 0, 100)); //margins around the whole grid (top/right/bottom/left)
    weaponsCreatorLabelPane.addRow(0,weaponsCreatorLabel);

    Label inventoryLabel = new Label("All the weapons you create will be stored in your inventory");
    inventoryLabel.setFont(new Font("Arial rounded Mt Bold", 18));
    Label phaseLabel = new Label(controller.getCurrentPhaseName());
    phaseLabel.setFont(new Font("Arial rounded Mt Bold", 18));
    GridPane axeGrid = weaponGridPane("Axe");
    GridPane bowrGrid = weaponGridPane("Bow");
    GridPane knifeGrid = weaponGridPane("Knife");
    GridPane staffGrid = weaponGridPane("Staff");
    GridPane swordGrid = weaponGridPane("Sword");
    Button mainMenuButton = new Button("Go back to main menu");
    mainMenuButton.setFont(new Font("Arial rounded Mt Bold", 18));
    mainMenuButton.setPrefWidth(300);
    mainMenuButton.setOnAction(event ->
            window.setScene(mainMenuScene));

    GridPane weaponCreatorPane = new GridPane();
    weaponCreatorPane.setHgap(150); //horizontal gap in pixels
    weaponCreatorPane.setVgap(15); //vertical gap in pixels
    weaponCreatorPane.setPadding(new Insets(45, 0, 0, 100)); //margins around the whole grid (top/right/bottom/left)
    weaponCreatorPane.addColumn(0, inventoryLabel);
    weaponCreatorPane.addColumn(1, phaseLabel);
    weaponCreatorPane.addRow(2, axeGrid);
    weaponCreatorPane.addRow(2, bowrGrid);
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
    Label characterLabel = new Label(characterClass + "'s fields");
    characterLabel.setFont(new Font("Arial rounded Mt Bold", 18));

    Label nameLabel = new Label("Name: ");
    nameLabel.setFont(new Font("Arial rounded Mt Bold", 15));
    TextField name = new TextField();
    name.setFont(new Font("Arial rounded Mt Bold", 13));
    name.setPromptText(characterClass + "'s name");

    Label healthPointsLabel = new Label("HealthPoints: ");
    healthPointsLabel.setFont(new Font("Arial rounded Mt Bold", 15));
    TextField healthPoints = new TextField();
    healthPoints.setFont(new Font("Arial rounded Mt Bold", 13));
    healthPoints.setPromptText(characterClass + "'s health points");

    Label defenseLabel = new Label("Defense: ");
    defenseLabel.setFont(new Font("Arial rounded Mt Bold", 15));
    TextField defense = new TextField();
    defense.setFont(new Font("Arial rounded Mt Bold", 13));
    defense.setPromptText(characterClass + "'s defense");

    Button createCharacterButton = new Button("Create " + characterClass);
    createCharacterButton.setFont(new Font("Arial rounded Mt Bold", 12));
    createCharacterButton.setPrefWidth(150);

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
          System.out.println(controller.getPlayerCharacters());
          name.setText("");
          healthPoints.setText("");
          defense.setText("");
          charactersNameClass.put(controller.getPlayerCharactersAlive(), "BlackMage");
        });
        break;

      case "Create Engineer":
        createCharacterButton.setOnAction(actionEvent -> {
          controller.createEngineer(
                  name.getText(),
                  Integer.parseInt(healthPoints.getText()),
                  Integer.parseInt(defense.getText())
          );
          System.out.println(controller.getPlayerCharacters());
          name.setText("");
          healthPoints.setText("");
          defense.setText("");
          charactersNameClass.put(controller.getPlayerCharactersAlive(), "Engineer");
        });
        break;

      case "Create Knight":
        createCharacterButton.setOnAction(actionEvent -> {
          controller.createKnight(
                  name.getText(),
                  Integer.parseInt(healthPoints.getText()),
                  Integer.parseInt(defense.getText())
          );
          System.out.println(controller.getPlayerCharacters());
          name.setText("");
          healthPoints.setText("");
          defense.setText("");
          charactersNameClass.put(controller.getPlayerCharactersAlive(), "Knight");
        });
        break;

      case "Create Thief":
        createCharacterButton.setOnAction(actionEvent -> {
          controller.createThief(
                  name.getText(),
                  Integer.parseInt(healthPoints.getText()),
                  Integer.parseInt(defense.getText())
          );
          System.out.println(controller.getPlayerCharacters());
          name.setText("");
          healthPoints.setText("");
          defense.setText("");
          charactersNameClass.put(controller.getPlayerCharactersAlive(), "Thief");
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
          System.out.println(controller.getPlayerCharacters());
          name.setText("");
          healthPoints.setText("");
          defense.setText("");
          charactersNameClass.put(controller.getPlayerCharactersAlive(), "WhiteMage");
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
    Label weaponLabel = new Label(weaponClass + "'s fields");
    weaponLabel.setFont(new Font("Arial rounded Mt Bold", 18));

    Label nameLabel = new Label("Name: ");
    nameLabel.setFont(new Font("Arial rounded Mt Bold", 15));
    TextField name = new TextField();
    name.setFont(new Font("Arial rounded Mt Bold", 13));
    name.setPromptText(weaponClass + "'s name");

    Label damageLabel = new Label("Damage: ");
    damageLabel.setFont(new Font("Arial rounded Mt Bold", 15));
    TextField damage = new TextField();
    damage.setFont(new Font("Arial rounded Mt Bold", 13));
    damage.setPromptText(weaponClass + "'s damage");

    Label weightLabel = new Label("Weight: ");
    weightLabel.setFont(new Font("Arial rounded Mt Bold", 15));
    TextField weight = new TextField();
    weight.setFont(new Font("Arial rounded Mt Bold", 13));
    weight.setPromptText(weaponClass + "'s weight");

    Button createWeaponButton = new Button("Create " + weaponClass);
    createWeaponButton.setFont(new Font("Arial rounded Mt Bold", 15));
    createWeaponButton.setPrefWidth(150);

    // Enlazar funcionalidad del botón
    switch (createWeaponButton.getText()) {
      case "Create Axe":
        createWeaponButton.setOnAction(actionEvent -> {
          controller.createAxe(
                  name.getText(),
                  Integer.parseInt(damage.getText()),
                  Integer.parseInt(weight.getText())
          );
          System.out.println(controller.isWeaponInInventory(name.getText()));
          name.setText("");
          damage.setText("");
          weight.setText("");
        });

      case "Create Bow":
        createWeaponButton.setOnAction(actionEvent -> {
          controller.createBow(
                  name.getText(),
                  Integer.parseInt(damage.getText()),
                  Integer.parseInt(weight.getText())
          );
          System.out.println(controller.isWeaponInInventory(name.getText()));
          name.setText("");
          damage.setText("");
          weight.setText("");
        });

      case "Create Knife":
        createWeaponButton.setOnAction(actionEvent -> {
          controller.createKnife(
                  name.getText(),
                  Integer.parseInt(damage.getText()),
                  Integer.parseInt(weight.getText())
          );
          System.out.println(controller.isWeaponInInventory(name.getText()));
          name.setText("");
          damage.setText("");
          weight.setText("");
        });

      case "Create Staff":
        createWeaponButton.setOnAction(actionEvent -> {
          controller.createStaff(
                  name.getText(),
                  Integer.parseInt(damage.getText()),
                  10,
                  Integer.parseInt(weight.getText())
          );
          System.out.println(controller.isWeaponInInventory(name.getText()));
          name.setText("");
          damage.setText("");
          weight.setText("");
        });

      case "Create Sword":
        createWeaponButton.setOnAction(actionEvent -> {
          controller.createSword(
                  name.getText(),
                  Integer.parseInt(damage.getText()),
                  Integer.parseInt(weight.getText())
          );
          System.out.println(controller.isWeaponInInventory(name.getText()));
          name.setText("");
          damage.setText("");
          weight.setText("");
        });
    }

    GridPane characterPane = new GridPane();
    characterPane.setHgap(5); //horizontal gap in pixels
    characterPane.setVgap(3); //vertical gap in pixels
    characterPane.addRow(0, weaponLabel);
    characterPane.addRow(1, nameLabel, name);
    characterPane.addRow(2, damageLabel, damage);
    characterPane.addRow(3, weightLabel, weight);
    characterPane.add(createWeaponButton, 1,4,1,1);

    return characterPane;
  }



  private @NotNull Scene equipCharacterScene() {
    VBox equipCharacter = characterVboxForEquipCharacterScene();
    // This sets the size of the Scene to be 1200px wide, 600px high
    return new Scene(equipCharacter, 1200, 600);
  }

  private VBox characterVboxForEquipCharacterScene() {
    VBox characterVbox = new VBox(15);

    Label characterLabel = new Label("Player Characters:");
    characterVbox.getChildren().add(characterLabel);

    VBox characterStats;

    for(int i=0; i<partySize; i++) {
      String className = charactersNameClass.get(i+1);
      String name = controller.getPlayerCharacterNameByIndex(i);
      int healthPoints = controller.getPlayerCharacterDefenseByIndex(i);
      int defense = controller.getPlayerCharacterDefenseByIndex(i);
      String weaponName = controller.getNameOffEquippedWeaponByIndex(i);
      characterStats = CharacterStatsForEquipCharacterScene(i+1, className, name, healthPoints, defense, weaponName);
      characterVbox.getChildren().add(characterStats);
    }
    return characterVbox;
  }

  private VBox CharacterStatsForEquipCharacterScene(int index,String className, String name, int healthPoints, int defense, String weaponName) {
    VBox characterStats = new VBox();
    Label characterNumber = new Label("Character " + index);
    Label characterClassName = new Label("Class: " + className);
    Label characterName = new Label("Name: " + name);
    Label characterHealthPoints = new Label("HealthPoints: " + healthPoints);
    Label characterDefense = new Label("Defense: " + defense);
    Label characterWeaponName = new Label("Equipped Weapon: " + weaponName);
    characterStats.getChildren().addAll(characterNumber, characterClassName, characterName, characterHealthPoints, characterDefense, characterWeaponName);
    return characterStats;
  }
}