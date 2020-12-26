package com.github.salix07.finalreality.gui;

import com.github.salix07.finalreality.controller.GameController;
import com.github.salix07.finalreality.model.character.player.IPlayerCharacter;
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
  private Scene mainMenuScene, playerCreatorScene, weaponCreatorScene, equipCharacterScene, combatScene, changeWeaponScene, gameOverScene;
  int partySize, enemyPartySize;
  String turnCharacterName;
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
    Scene scene = setControllerScene();
    primaryStage.setScene(scene);
    primaryStage.setTitle("Final Reality");
    primaryStage.setResizable(false);
    primaryStage.show();
    playSound();
  }


  private static void playSound() {
    String audioFilePath = "mediaFiles/piercingLight.wav";
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
      if(enemyPartySize > 10) {
        enemyPartySize = 10;
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
    controllerScenePane.setPadding(new Insets(10, 10, 10, 100)); //margins around the whole grid (top/right/bottom/left)
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
      if(controller.getPlayerCharactersAlive() == partySize) {
        try {
          equipCharacterScene = equipCharacterScene();
        } catch (FileNotFoundException e) {
          e.printStackTrace();
        }
        window.setScene(equipCharacterScene);
      }
    });

    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(final long now) {
        playerCreatorLabel.setText("You must create " + (partySize - controller.getPlayerCharactersAlive()) + " PlayerCharacters more");
        if((partySize - controller.getPlayerCharactersAlive()) == 0) {
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
        characterRemainingLabel.setText("You have created " + controller.getPlayerCharactersAlive() + "/" + partySize + " PlayerCharacters");
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
    healthPoints.setPromptText(characterClass + "'s health");

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
          name.setText("");
          healthPoints.setText("");
          defense.setText("");
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
          name.setText("");
          damage.setText("");
          weight.setText("");
        });
        break;

      case "Create Bow":
        createWeaponButton.setOnAction(actionEvent -> {
          controller.createBow(
                  name.getText(),
                  Integer.parseInt(damage.getText()),
                  Integer.parseInt(weight.getText())
          );
          name.setText("");
          damage.setText("");
          weight.setText("");
        });
        break;

      case "Create Knife":
        createWeaponButton.setOnAction(actionEvent -> {
          controller.createKnife(
                  name.getText(),
                  Integer.parseInt(damage.getText()),
                  Integer.parseInt(weight.getText())
          );
          name.setText("");
          damage.setText("");
          weight.setText("");
        });
        break;

      case "Create Staff":
        createWeaponButton.setOnAction(actionEvent -> {
          controller.createStaff(
                  name.getText(),
                  Integer.parseInt(damage.getText()),
                  10,
                  Integer.parseInt(weight.getText())
          );
          name.setText("");
          damage.setText("");
          weight.setText("");
        });
        break;

      case "Create Sword":
        createWeaponButton.setOnAction(actionEvent -> {
          controller.createSword(
                  name.getText(),
                  Integer.parseInt(damage.getText()),
                  Integer.parseInt(weight.getText())
          );
          name.setText("");
          damage.setText("");
          weight.setText("");
        });
        break;
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



  private @NotNull Scene equipCharacterScene() throws FileNotFoundException {
    Image backGroundImage = new Image(new FileInputStream("mediaFiles/summonersRiftMenu.png"));
    ImageView backGround = new ImageView(backGroundImage);
    backGround.fitWidthProperty().bind(window.widthProperty());
    backGround.isPreserveRatio();
    backGround.setViewOrder(0.5);
    backGround.setOpacity(0.5);
    backGround.setMouseTransparent(true);

    Label characterTitle = new Label("Player Characters:");
    characterTitle.setFont(new Font("Arial rounded Mt Bold", 20));
    FlowPane characterFlowPane = characterFlowPaneForEquipCharacterScene();
    characterFlowPane.setAlignment(Pos.TOP_CENTER);
    VBox characterVbox = new VBox();
    characterVbox.setSpacing(10);
    characterVbox.getChildren().addAll(characterTitle, characterFlowPane);
    characterVbox.setAlignment(Pos.TOP_CENTER);

    Label equipTitleLabel = new Label("You must equip all your\n" + "characters to start the game");
    equipTitleLabel.setFont(new Font("Arial rounded Mt Bold", 25));

    Label characterLabel = new Label("Enter character's name");
    characterLabel.setFont(new Font("Arial rounded Mt Bold", 12));
    Label weaponLabel = new Label("Enter weapon's name");
    weaponLabel.setFont(new Font("Arial rounded Mt Bold", 12));
    HBox equipInstruction = new HBox();
    equipInstruction.setSpacing(75);
    equipInstruction.getChildren().addAll(characterLabel, weaponLabel);

    TextField characterText = new TextField();
    characterText.setFont(new Font("Arial rounded Mt Bold", 13));
    characterText.setPromptText("Character's name");
    characterText.setPrefWidth(150);
    Label equipLabel = new Label("equip");
    equipLabel.setFont(new Font("Arial rounded Mt Bold", 10));
    TextField weaponText = new TextField();
    weaponText.setFont(new Font("Arial rounded Mt Bold", 15));
    weaponText.setPromptText("Weapon's name");
    weaponText.setPrefWidth(150);
    Button equipButton = new Button("Equip");
    equipButton.setFont(new Font("Arial rounded Mt Bold", 15));
    equipButton.setPrefWidth(75);
    equipButton.setOnAction(event -> {
      int characterPosition = controller.getCharactersPositionByName().get(characterText.getText()) - 1;
      String weaponName = weaponText.getText();
      controller.tryToEquipPlayerCharacter(controller.getPlayerCharacter(characterPosition), controller.selectWeaponFromInventory(weaponName));
      characterText.setText("");
      weaponText.setText("");
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

    Button mainMenuButton = new Button("Go back to main menu");
    mainMenuButton.setFont(new Font("Arial rounded Mt Bold", 12));
    mainMenuButton.setPrefWidth(150);
    mainMenuButton.setOnAction(event ->
            window.setScene(mainMenuScene));

    Label startGameLabel = new Label("Now you can start the game!");
    startGameLabel.setFont(new Font("Arial rounded Mt Bold", 20));
    startGameLabel.setVisible(false);

    Button startGameButton = new Button("Start Game");
    startGameButton.setFont(new Font("Arial rounded Mt Bold", 15));
    startGameButton.setPrefWidth(150);
    startGameButton.setVisible(false);
    startGameButton.setOnAction(event -> {
      int partyHealthPoints = controller.getPartyHealthPoints();
      int partyDefense = controller.getPartyDefense();
      int partyAttackDamage = controller.getPartyAttackDamage();
      int partyWeight = controller.getPartyWeight();
      controller.createRandomEnemies(partyHealthPoints,partyDefense,partyAttackDamage,partyWeight);
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
        if(controller.partyIsEquipped()) {
          startGameLabel.setVisible(true);
          startGameButton.setVisible(true);
        }
      }
    };
    timer.start();

    GridPane equipGridPane = new GridPane();
    equipGridPane.setVgap(10); //vertical gap in pixels
    equipGridPane.add(equipTitleLabel,0,20,1,1);
    equipGridPane.add(equipLayout,0,24,1,1);
    equipGridPane.add(mainMenuButton,0,26,1,1);
    equipGridPane.add(startGameVbox,0,30,1,1);

    Label weaponsTitle = new Label("Weapons:");
    weaponsTitle.setFont(new Font("Arial rounded Mt Bold", 20));
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
      characterStats = characterStatsForEquipCharacterScene(i+1, className, name, healthPoints, defense, weaponName);
      characterFlowPane.getChildren().add(characterStats);
    }
    return characterFlowPane;
  }

  private VBox characterStatsForEquipCharacterScene(int position, String className, String name, int healthPoints, int defense, String weaponName) {
    VBox characterStats = new VBox();
    Label characterNumber = new Label("Character " + position);
    characterNumber.setFont(new Font("Arial rounded Mt Bold", 12));
    Label characterClassName = new Label("Class: " + className);
    characterClassName.setFont(new Font("Arial rounded Mt Bold", 12));
    Label characterName = new Label("Name: " + name);
    characterName.setFont(new Font("Arial rounded Mt Bold", 12));
    Label characterHealthPoints = new Label("HealthPoints: " + healthPoints);
    characterHealthPoints.setFont(new Font("Arial rounded Mt Bold", 12));
    Label characterDefense = new Label("Defense: " + defense);
    characterDefense.setFont(new Font("Arial rounded Mt Bold", 12));
    Label characterWeaponName = new Label("Equipped Weapon: " + weaponName);
    characterWeaponName.setFont(new Font("Arial rounded Mt Bold", 12));
    characterStats.getChildren().addAll(characterNumber, characterClassName, characterName, characterHealthPoints, characterDefense, characterWeaponName);
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
      weaponStats = weaponStatsForEquipCharacterScene(i+1, className, name, damage, weight);
      weaponFlowPane.getChildren().add(weaponStats);
    }
    return weaponFlowPane;
  }

  private VBox weaponStatsForEquipCharacterScene(int position, String className, String name, int damage, int weight) {
    VBox weaponStats = new VBox();
    Label weaponNumber = new Label("Weapon " + position);
    weaponNumber.setFont(new Font("Arial rounded Mt Bold", 12));
    Label weaponClassName = new Label("Class: " + className);
    weaponClassName.setFont(new Font("Arial rounded Mt Bold", 12));
    Label weaponName = new Label("Name: " + name);
    weaponName.setFont(new Font("Arial rounded Mt Bold", 12));
    Label weaponDamage = new Label("Damage: " + damage);
    weaponDamage.setFont(new Font("Arial rounded Mt Bold", 12));
    Label weaponWeight = new Label("Weight: " + weight);
    weaponWeight.setFont(new Font("Arial rounded Mt Bold", 12));
    weaponStats.getChildren().addAll(weaponNumber, weaponClassName, weaponName, weaponDamage, weaponWeight);
    return weaponStats;
  }


  private Scene combatScene() throws FileNotFoundException {
    Image backGroundImage = new Image(new FileInputStream("mediaFiles/summonersRiftMenu.png"));
    ImageView backGround = new ImageView(backGroundImage);
    backGround.fitWidthProperty().bind(window.widthProperty());
    backGround.isPreserveRatio();
    backGround.setViewOrder(0.5);
    backGround.setOpacity(0.5);
    backGround.setMouseTransparent(true);

    Label characterTitle = new Label("Player Characters:");
    characterTitle.setFont(new Font("Arial rounded Mt Bold", 20));
    FlowPane characterFlowPane = characterFlowPaneCombatScene();
    characterFlowPane.setAlignment(Pos.TOP_CENTER);
    VBox characterVbox = new VBox();
    characterVbox.setSpacing(10);
    characterVbox.getChildren().addAll(characterTitle, characterFlowPane);
    characterVbox.setAlignment(Pos.TOP_CENTER);

    Label turnLabel = new Label();
    turnLabel.setFont(new Font("Arial rounded Mt Bold", 30));
    VBox turnVbox = new VBox();
    turnVbox.setAlignment(Pos.CENTER);
    turnVbox.getChildren().add(turnLabel);

    Label whatWillDoLabel = new Label();
    whatWillDoLabel.setFont(new Font("Arial rounded Mt Bold", 25));

    Label attackText = new Label("Attack Enemy");
    attackText.setFont(new Font("Arial rounded Mt Bold", 22));

    Label targetText = new Label("Enter target's name");
    targetText.setFont(new Font("Arial rounded Mt Bold", 15));

    TextField attackField = new TextField();
    attackField.setFont(new Font("Arial rounded Mt Bold", 15));
    attackField.setPromptText("Target's name");
    attackField.setPrefWidth(150);

    Button attackButton = new Button("Attack");
    attackButton.setFont(new Font("Arial rounded Mt Bold", 15));
    attackButton.setPrefWidth(85);
    attackButton.setOnAction(event -> {
      int targetPosition = controller.getEnemyPositionByName().get(attackField.getText()) - 1;
      controller.tryToAttackCharacter(controller.getActiveICharacter(), controller.getEnemy(targetPosition));
      attackField.setText("");
      if(controller.isGameOverPhase()) { // Is Game Over Phase
        gameOverScene = gameOverScene();
        window.setScene(gameOverScene);
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

    HBox attackHbox = new HBox();
    attackHbox.setSpacing(30); //horizontal gap in pixels
    attackHbox.getChildren().addAll(attackField, attackButton);

    VBox attackInstruction = new VBox();
    attackInstruction.getChildren().addAll(targetText, attackHbox);

    VBox attackOption = new VBox();
    attackOption.getChildren().addAll(attackText, attackInstruction);


    Label changeWeaponText = new Label("Change weapon");
    changeWeaponText.setFont(new Font("Arial rounded Mt Bold", 19));

    Button changeWeaponButton = new Button("Go to Inventory");
    changeWeaponButton.setFont(new Font("Arial rounded Mt Bold", 15));
    changeWeaponButton.setPrefWidth(150);
    changeWeaponButton.setVisible(true);
    changeWeaponButton.setOnAction(actionEvent -> {
      System.out.println(controller.isPlayerSelectingActionPhase());
      System.out.println(controller.getCurrentPhaseName());
      System.out.println(controller.getActiveICharacter().getName());
      if(controller.isPlayerSelectingActionPhase() && controller.isPlayerCharacterTurn(controller.getActiveICharacter())) {
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
    combatHud.setSpacing(30);
    combatHud.getChildren().addAll(turnVbox, optionsHud);

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
          else { // Enemy's turn
            if (controller.isGameOverPhase()) { // Is Game Over Phase
              gameOverScene = gameOverScene();
              window.setScene(gameOverScene);
            }
            else { // Is Not Game Over Phase
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

    Label enemiesTitle = new Label("Enemies:");
    enemiesTitle.setFont(new Font("Arial rounded Mt Bold", 20));
    FlowPane enemiesFlowPane = enemiesFlowPaneForCombatScene();
    enemiesFlowPane.setAlignment(Pos.TOP_CENTER);
    VBox enemiesVbox = new VBox();
    enemiesVbox.setSpacing(10);
    enemiesVbox.getChildren().addAll(enemiesTitle, enemiesFlowPane);
    enemiesVbox.setAlignment(Pos.TOP_CENTER);

    HBox combatHbox = new HBox();
    combatHbox.setSpacing(30); //horizontal gap in pixels
    combatHbox.setLayoutX(100);
    combatHbox.getChildren().addAll(characterVbox, combatHud, enemiesVbox);

    StackPane equipCharacterStackPane = new StackPane();
    equipCharacterStackPane.getChildren().addAll(backGround, combatHbox);
    // This sets the size of the Scene to be 1200px wide, 600px high
    return new Scene(equipCharacterStackPane, 1200, 600);
  }

  private FlowPane characterFlowPaneCombatScene() {
    FlowPane characterFlowPane = new FlowPane();
    characterFlowPane.setHgap(15);
    characterFlowPane.setVgap(15);

    ArrayList<Integer> playerCharactersAliveIndex = controller.getPlayerCharactersAliveIndex();
    VBox characterStats;

    for(int i : playerCharactersAliveIndex) {
      String className = controller.getCharacterClassByIndex(i);
      String name = controller.getPlayerCharacterNameByIndex(i);
      int healthPoints = controller.getPlayerCharacterDefenseByIndex(i);
      int defense = controller.getPlayerCharacterDefenseByIndex(i);
      String weaponName = controller.getNameOffEquippedWeaponByIndex(i);
      int attackDamage = controller.getPlayerCharacterAttackByIndex(i);
      int weight = controller.getWeightOffEquippedWeaponByIndex(i);
      characterStats = characterStatsForCombatScene(i+1, className, name, healthPoints, defense, weaponName,attackDamage,weight);
      characterFlowPane.getChildren().add(characterStats);
    }
    return characterFlowPane;
  }

  private VBox characterStatsForCombatScene(int position, String className, String name, int healthPoints, int defense, String weaponName, int attackDamage, int weight) {
    VBox characterStats = new VBox();
    Label characterNumber = new Label("Character " + position);
    characterNumber.setFont(new Font("Arial rounded Mt Bold", 12));
    Label characterClassName = new Label("Class: " + className);
    characterClassName.setFont(new Font("Arial rounded Mt Bold", 12));
    Label characterName = new Label("Name: " + name);
    characterName.setFont(new Font("Arial rounded Mt Bold", 12));
    Label characterHealthPoints = new Label("HealthPoints: " + healthPoints);
    characterHealthPoints.setFont(new Font("Arial rounded Mt Bold", 12));
    Label characterDefense = new Label("Defense: " + defense);
    characterDefense.setFont(new Font("Arial rounded Mt Bold", 12));
    Label characterWeaponName = new Label("Equipped Weapon: " + weaponName);
    characterWeaponName.setFont(new Font("Arial rounded Mt Bold", 12));
    Label characterAttackDamage = new Label("AttackDamage: " + attackDamage);
    characterAttackDamage.setFont(new Font("Arial rounded Mt Bold", 12));
    Label characterWeight = new Label("Weight: " + weight);
    characterWeight.setFont(new Font("Arial rounded Mt Bold", 12));
    characterStats.getChildren().addAll(characterNumber, characterClassName, characterName, characterHealthPoints, characterDefense, characterWeaponName, characterAttackDamage, characterWeight);
    return characterStats;
  }

  private FlowPane enemiesFlowPaneForCombatScene() {
    FlowPane enemiesFlowPane = new FlowPane();
    enemiesFlowPane.setHgap(15);
    enemiesFlowPane.setVgap(15);

    ArrayList<Integer> enemiesAliveIndex = controller.getEnemiesAliveIndex();
    VBox enemiesStats;

    for(int i : enemiesAliveIndex) {
      String className = controller.getEnemyClassByIndex(i);
      String name = controller.getEnemyNameByIndex(i);
      int healthPoints = controller.getEnemyHealthByIndex(i);
      int defense = controller.getEnemyDefenseByIndex(i);
      int attackDamage = controller.getEnemyAttackByIndex(i);
      int weight = controller.getEnemyWeightByIndex(i);
      enemiesStats = enemyStatsForCombatScene(i+1, className, name, healthPoints, defense, attackDamage, weight);
      enemiesFlowPane.getChildren().add(enemiesStats);
    }
    return enemiesFlowPane;
  }

  private VBox enemyStatsForCombatScene(int position, String className, String name, int healthPoints, int defense, int attackDamage, int weight) {
    VBox enemyStats = new VBox();
    Label enemyNumber = new Label("Enemy " + position);
    enemyNumber.setFont(new Font("Arial rounded Mt Bold", 12));
    Label enemyClassName = new Label("Class: " + className);
    enemyClassName.setFont(new Font("Arial rounded Mt Bold", 12));
    Label enemyName = new Label("Name: " + name);
    enemyName.setFont(new Font("Arial rounded Mt Bold", 12));
    Label enemyHealthPoints = new Label("HealthPoints: " + healthPoints);
    enemyHealthPoints.setFont(new Font("Arial rounded Mt Bold", 12));
    Label enemyDefense = new Label("Defense: " + defense);
    enemyDefense.setFont(new Font("Arial rounded Mt Bold", 12));
    Label enemyAttackDamage = new Label("AttackDamage: " + attackDamage);
    enemyAttackDamage.setFont(new Font("Arial rounded Mt Bold", 12));
    Label enemyWeight = new Label("Weight: " + weight);
    enemyWeight.setFont(new Font("Arial rounded Mt Bold", 12));
    enemyStats.getChildren().addAll(enemyNumber, enemyClassName, enemyName, enemyHealthPoints, enemyDefense, enemyAttackDamage, enemyWeight);
    return enemyStats;
  }

  private Scene changeWeaponScene() throws FileNotFoundException {
    Image backGroundImage = new Image(new FileInputStream("mediaFiles/summonersRiftMenu.png"));
    ImageView backGround = new ImageView(backGroundImage);
    backGround.fitWidthProperty().bind(window.widthProperty());
    backGround.isPreserveRatio();
    backGround.setViewOrder(0.5);
    backGround.setOpacity(0.5);
    backGround.setMouseTransparent(true);

    Label characterTitle = new Label("Active Player Character:");
    characterTitle.setFont(new Font("Arial rounded Mt Bold", 20));
    FlowPane characterFlowPane = characterFlowPaneForChangeWeaponScene();
    characterFlowPane.setAlignment(Pos.TOP_CENTER);
    VBox characterVbox = new VBox();
    characterVbox.setSpacing(10);
    characterVbox.getChildren().addAll(characterTitle, characterFlowPane);
    characterVbox.setAlignment(Pos.TOP_CENTER);

    Label equipTitleLabel = new Label("Enter the name off the\n" + "weapon that you want to equip");
    equipTitleLabel.setFont(new Font("Arial rounded Mt Bold", 23));

    Label weaponLabel = new Label("Enter weapon's name");
    weaponLabel.setFont(new Font("Arial rounded Mt Bold", 12));

    TextField weaponText = new TextField();
    weaponText.setFont(new Font("Arial rounded Mt Bold", 15));
    weaponText.setPromptText("Weapon's name");
    weaponText.setPrefWidth(150);
    Button equipButton = new Button("Equip");
    equipButton.setFont(new Font("Arial rounded Mt Bold", 15));
    equipButton.setPrefWidth(75);
    equipButton.setOnAction(event -> {
      String weaponName = weaponText.getText();
      System.out.println(weaponName);
      controller.tryToEquipPlayerCharacter((IPlayerCharacter)controller.getActiveICharacter(), controller.selectWeaponFromInventory(weaponName));
      weaponText.setText("");
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

    Button combatButton = new Button("Go back to Combat");
    combatButton.setFont(new Font("Arial rounded Mt Bold", 12));
    combatButton.setPrefWidth(150);
    combatButton.setOnAction(event -> {
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

    Label weaponsTitle = new Label("Your weapons:");
    weaponsTitle.setFont(new Font("Arial rounded Mt Bold", 20));
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

    String className = controller.getCharacterClassFrom(controller.getActiveICharacter());
    String name = controller.getNameFrom(controller.getActiveICharacter());
    int healthPoints = controller.getHealthPointsFrom(controller.getActiveICharacter());
    int defense = controller.getDefenseFrom(controller.getActiveICharacter());
    String weaponName = controller.getNameOffEquippedWeaponFrom((IPlayerCharacter) controller.getActiveICharacter());
    int attackDamage = controller.getAttackDamageFrom(controller.getActiveICharacter());
    int weight = controller.getWeightOffEquippedWeaponFrom((IPlayerCharacter) controller.getActiveICharacter());
    characterStats = characterStatsForChangeWeaponScene(className, name, healthPoints, defense, weaponName, attackDamage, weight);
    characterFlowPane.getChildren().add(characterStats);
    return characterFlowPane;
  }

  private VBox characterStatsForChangeWeaponScene(String className, String name, int healthPoints, int defense, String weaponName, int attackDamage, int weight) {
    VBox characterStats = new VBox();
    Label characterClassName = new Label("Class: " + className);
    characterClassName.setFont(new Font("Arial rounded Mt Bold", 12));
    Label characterName = new Label("Name: " + name);
    characterName.setFont(new Font("Arial rounded Mt Bold", 12));
    Label characterHealthPoints = new Label("HealthPoints: " + healthPoints);
    characterHealthPoints.setFont(new Font("Arial rounded Mt Bold", 12));
    Label characterDefense = new Label("Defense: " + defense);
    characterDefense.setFont(new Font("Arial rounded Mt Bold", 12));
    Label characterWeaponName = new Label("Equipped Weapon: " + weaponName);
    characterWeaponName.setFont(new Font("Arial rounded Mt Bold", 12));
    Label characterAttackDamage = new Label("AttackDamage: " + attackDamage);
    characterAttackDamage.setFont(new Font("Arial rounded Mt Bold", 12));
    Label characterWeight = new Label("Weight: " + weight);
    characterWeight.setFont(new Font("Arial rounded Mt Bold", 12));
    characterStats.getChildren().addAll(characterClassName, characterName, characterHealthPoints, characterDefense, characterWeaponName, characterAttackDamage, characterWeight);
    return characterStats;
  }

  private Scene gameOverScene() {
    Label gameOverLabel = new Label("Game Over");
    StackPane gameOverPane = new StackPane();
    gameOverPane.getChildren().add(gameOverLabel);
    // This sets the size of the Scene to be 1200px wide, 600px high
    return new Scene(gameOverPane, 1200, 600);
  }
}