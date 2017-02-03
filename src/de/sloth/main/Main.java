package de.sloth.main;

import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.controllHandler.InventoryControllHandler;
import de.sloth.controllHandler.SimpleControllHandler;
import de.sloth.generators.GameSystemGenerator;
import de.sloth.hmi.MainMenuLayer;
import de.sloth.hmi.PlayerInformationLayer;
import de.sloth.hmi.WinGameLayer;
import de.sloth.system.game.core.GameCore;
import de.sloth.system.game.core.GameEvent;
import de.sloth.hmi.LooseGameLayer;
import de.sloth.hmi.GameHMI;
import de.sloth.hmi.GeneralGameInformation;
import de.sloth.hmi.InventoryGameLayer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		int screenWidth = (int) Screen.getPrimary().getBounds().getWidth();
		int screenHeight = (int) Screen.getPrimary().getBounds().getHeight(); 

		int spriteHeight = 32;
		int spriteWidth = 32;
		IEntityManagement entityManager = new EntityManager();
		GameHMI gameHmi = new GameHMI(screenWidth, screenHeight);
		
		Scene scene = new Scene(gameHmi);
		primaryStage.setScene(scene);
		ConcurrentLinkedQueue<GameEvent> eventQueue = new ConcurrentLinkedQueue<GameEvent>();
		PlayerInformationLayer pil = new PlayerInformationLayer("playerInfo", eventQueue);
		LooseGameLayer wlgl = new LooseGameLayer("wl", eventQueue);
		WinGameLayer wlg = new WinGameLayer("wlg", eventQueue);
		InventoryGameLayer igl = new InventoryGameLayer("igl", eventQueue);
		GeneralGameInformation ggi = new GeneralGameInformation("ggi", eventQueue);
		MainMenuLayer mml = new MainMenuLayer("main", eventQueue);
		
		gameHmi.registerGameInterfaceLayer(ggi);
		gameHmi.registerGameInterfaceLayer(pil);
		gameHmi.registerGameInterfaceLayer(wlgl);
		gameHmi.registerGameInterfaceLayer(wlg);
		gameHmi.registerGameInterfaceLayer(igl);
		gameHmi.registerGameInterfaceLayer(mml);
		
		SimpleControllHandler stdControll = new SimpleControllHandler(entityManager, eventQueue, spriteWidth, spriteHeight);
		InventoryControllHandler iControll = new InventoryControllHandler(entityManager, eventQueue);
		pil.setOnKeyPressed(stdControll);
		mml.requestFocus();
		igl.setOnKeyPressed(iControll);
		GameCore core = new GameCore();
		((GeneralGameInformation) gameHmi.getGameInterfaceLayer("ggi")).getLabel().textProperty().bindBidirectional(core.getFpsProperty(), new StringConverter<Number>() {
			@Override
			public Number fromString(String arg0) {
				return Integer.parseInt(arg0);
			}
			@Override
			public String toString(Number arg0) {
				return arg0.toString();
			}
		});
		
		core.registerSystem(GameSystemGenerator.getInstance().generateSystemActivationSystem(entityManager, core, eventQueue));
		core.registerSystem(GameSystemGenerator.getInstance().generateEndConditionSystem(entityManager, eventQueue));
		core.registerSystem(GameSystemGenerator.getInstance().generateMoveSystem(entityManager, eventQueue));
		core.registerSystem(GameSystemGenerator.getInstance().generateCollisionSystem(entityManager, eventQueue));
		core.registerSystem(GameSystemGenerator.getInstance().generateBattleSystem(entityManager, eventQueue));
		core.registerSystem(GameSystemGenerator.getInstance().generateLootSystem(entityManager, eventQueue));
		core.registerSystem(GameSystemGenerator.getInstance().generateHMIMenuSystem(entityManager, gameHmi, eventQueue));
		core.registerSystem(GameSystemGenerator.getInstance().generateInventorySystem(entityManager, eventQueue));
		core.registerSystem(GameSystemGenerator.getInstance().generateBGMSystem(entityManager, eventQueue));
		core.registerSystem(GameSystemGenerator.getInstance().generateStartGameSystem(entityManager, eventQueue));
		core.registerSystem(GameSystemGenerator.getInstance().generateRenderSystem(entityManager, gameHmi, eventQueue));
		core.registerSystem(GameSystemGenerator.getInstance().generateHMIInventorySystem(entityManager, gameHmi, eventQueue));
		core.start();
		primaryStage.setFullScreen(true);
		primaryStage.setFullScreenExitHint("");
		primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
		primaryStage.setAlwaysOnTop(true);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}