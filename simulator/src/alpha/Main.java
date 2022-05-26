/* ******************************************************
 * Project alpha - Composants logiciels 2015.
 * Copyright (C) 2015 <Binh-Minh.Bui-Xuan@ens-lyon.org>.
 * GPL version>=3 <http://www.gnu.org/licenses/>.
 * $Id: alpha/Main.java 2015-03-11 buixuan.
 * ******************************************************/
package alpha;

import tools.HardCodedParameters;
import tools.User;
import specifications.data.DataService;
import specifications.engine.EngineService;
import specifications.viewer.ViewerService;
import data.Data;
import engine.Engine;
import userInterface.Viewer;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Main extends Application {
	// ---HARD-CODED-PARAMETERS---//
	private static String fileName = HardCodedParameters.defaultParamFileName;

	// ---VARIABLES---//
	private static DataService data;
	private static EngineService engine;
	private static ViewerService viewer;
	private static AnimationTimer timer;

	// ---EXECUTABLE---//
	public static void main(String[] args) {
		data = new Data();
		engine = new Engine();
		viewer = new Viewer();

		((Engine) engine).bindDataService(data);
		((Viewer) viewer).bindReadService(data);

		data.init();
		engine.init();
		viewer.init();

		launch(args);
	}

	@Override
	public void start(Stage stage) {
		// Bouton play
		Image play = new Image("file:src/images/play.png");
		ImageView playView = new ImageView(play);
		playView.setFitHeight(30);
		playView.setPreserveRatio(true);	
		Button buttonPlay = new Button();
		buttonPlay.setText(" P L A Y");
		buttonPlay.setStyle("-fx-font: 20 Helvetica;");
		buttonPlay.setLayoutX(225);
		buttonPlay.setLayoutY(400);
		buttonPlay.setPrefSize(180, 70);
		buttonPlay.setGraphic(playView);
		buttonPlay.setOpacity(0.75);
		
		buttonPlay.setOnAction(new EventHandler<ActionEvent>() {
			// Deuxième interface pour jouer
			@Override
			public void handle(ActionEvent event) {
				stage.close();
				
				final Scene secondScene = new Scene(((Viewer) viewer).getPanel());
				Stage secondStage = new Stage();
				secondStage.setTitle("Space Shooter");
				secondStage.getIcons().add(new Image("file:src/images/icon.jpeg"));
				secondStage.setScene(secondScene);
				Image background = new Image("file:src/images/background.jpeg");
				secondScene.setFill(new ImagePattern(background));
				
				secondScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
					@Override
					public void handle(KeyEvent event) {
						if (event.getCode() == KeyCode.LEFT)
							engine.setHeroesCommand(User.COMMAND.LEFT);
						if (event.getCode() == KeyCode.RIGHT)
							engine.setHeroesCommand(User.COMMAND.RIGHT);
						if (event.getCode() == KeyCode.UP)
							engine.setHeroesCommand(User.COMMAND.UP);
						if (event.getCode() == KeyCode.DOWN)
							engine.setHeroesCommand(User.COMMAND.DOWN);
						if (event.getCode() == KeyCode.SPACE)
							engine.setHeroesCommand(User.COMMAND.SPACE);
						event.consume();
					}
				});
				secondScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
					@Override
					public void handle(KeyEvent event) {
						if (event.getCode() == KeyCode.LEFT)
							engine.releaseHeroesCommand(User.COMMAND.LEFT);
						if (event.getCode() == KeyCode.RIGHT)
							engine.releaseHeroesCommand(User.COMMAND.RIGHT);
						if (event.getCode() == KeyCode.UP)
							engine.releaseHeroesCommand(User.COMMAND.UP);
						if (event.getCode() == KeyCode.DOWN)
							engine.releaseHeroesCommand(User.COMMAND.DOWN);
						if (event.getCode() == KeyCode.SPACE)
							engine.releaseHeroesCommand(User.COMMAND.SPACE);
						event.consume();
					}
				});
				secondScene.widthProperty().addListener(new ChangeListener<Number>() {
					@Override
					public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth,
							Number newSceneWidth) {
						viewer.setMainWindowWidth(newSceneWidth.doubleValue());
					}
				});
				secondScene.heightProperty().addListener(new ChangeListener<Number>() {
					@Override
					public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight,
							Number newSceneHeight) {
						viewer.setMainWindowHeight(newSceneHeight.doubleValue());
					}
				});
				
				secondStage.setScene(secondScene);
				secondStage.setWidth(HardCodedParameters.defaultWidth);
				secondStage.setHeight(HardCodedParameters.defaultHeight);
				secondStage.setOnShown(new EventHandler<WindowEvent>() {
					@Override
					public void handle(WindowEvent event) {
						engine.start();
					}
				});
				secondStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
					@Override
					public void handle(WindowEvent event) {
						engine.stop();
					}
				});
				secondStage.show();

				timer = new AnimationTimer() {
					@Override
					public void handle(long l) {
						secondScene.setRoot(((Viewer) viewer).getPanel());
						switch (data.getSoundEffect()) {
						case PhantomDestroyed:
							new MediaPlayer(new Media(getHostServices().getDocumentBase() + "src/sound/waterdrip.mp3")).play();
							break;
						case HeroesGotHit:
							new MediaPlayer(new Media(getHostServices().getDocumentBase() + "src/sound/waterdrip.mp3")).play();
							break;
						case TouchIndestructible:
							new MediaPlayer(new Media(getHostServices().getDocumentBase() + "src/sound/explosion.wav")).play();
							break;
						default:
							break;
						}
					}
				};
				timer.start();

				secondStage.show();
			}
		});

		// Bouton exit
		Image exit = new Image("file:src/images/exit.png");
		ImageView exitView = new ImageView(exit);
		exitView.setFitHeight(25);
		exitView.setPreserveRatio(true);	
		Button buttonExit = new Button();
		buttonExit.setText(" E X I T");
		buttonExit.setStyle("-fx-font: 20 Helvetica;");
		buttonExit.setLayoutX(650);
		buttonExit.setLayoutY(400);
		buttonExit.setPrefSize(180, 70);
		buttonExit.setGraphic(exitView);
		buttonExit.setOpacity(0.75);
		
		buttonExit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Platform.exit();
			}
		});
		
		// Interface principale (accueil)
		Pane root = new Pane();
		root.getChildren().add(buttonPlay);
		root.getChildren().add(buttonExit);

		Scene scene = new Scene(root, 1047, 608);
		stage.setScene(scene);
		
		stage.setTitle("Welcome - Space Shooter");
		stage.getIcons().add(new Image("file:src/images/icon.jpeg"));
		
		Image startBackground = new Image("file:src/images/startBackground.png");
        BackgroundImage backgroundImg = new BackgroundImage(startBackground, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImg);
        root.setBackground(background);
		stage.show();
	
	}

	// ---ARGUMENTS---//
	private static void readArguments(String[] args) {
		if (args.length > 0 && args[0].charAt(0) != '-') {
			System.err.println("Syntax error: use option -h for help.");
			return;
		}
		for (int i = 0; i < args.length; i++) {
			if (args[i].charAt(0) == '-') {
				if (args[i + 1].charAt(0) == '-') {
					System.err.println("Option " + args[i] + " expects an argument but received none.");
					return;
				}
				switch (args[i]) {
				case "-inFile":
					fileName = args[i + 1];
					break;
				case "-h":
					System.out.println("Options:");
					System.out.println(
							" -inFile FILENAME: (UNUSED AT THE MOMENT) set file name for input parameters. Default name is"
									+ HardCodedParameters.defaultParamFileName + ".");
					break;
				default:
					System.err.println("Unknown option " + args[i] + ".");
					return;
				}
				i++;
			}
		}
	}
}
