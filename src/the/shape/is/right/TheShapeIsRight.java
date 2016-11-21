package the.shape.is.right;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import the.shape.is.right.controller.ConfigController;
import the.shape.is.right.controller.GameController;

import java.io.IOException;

/**
 * @author Ben, Brian
 */
public class TheShapeIsRight extends Application {

    private static TheShapeIsRight instance;
    private Stage stage;

    public static TheShapeIsRight getInstance() {
        return instance;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Starts the application, displaying the configuration window
     *
     * @param primaryStage The primary stage on which to display content
     * @throws IOException Thrown if the config fxml cannot be loaded
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        instance = this;

        stage = primaryStage;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("forms/config.fxml"));
        Parent configRoot = loader.load();

        ConfigController configController = loader.getController();

        primaryStage.setScene(new Scene(configRoot));

        configController.init(configRoot);

        stage.setMinWidth(1600);
        stage.setMinHeight(900);
        stage.setResizable(true);
        primaryStage.show();
    }

    /**
     * Switches to the game window
     *
     * @param gameProperties The properties (configuration) specified by the user in the configuration window
     */
    public void startGame(GameProperties gameProperties) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("forms/game.fxml"));
        try {
            Parent gameRoot = loader.load();
            GameController gameController = loader.getController();

            gameRoot.setVisible(false);

            Scene scene = new Scene(gameRoot);

            scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

            stage.setScene(scene);
//            stage.setWidth(1600);
//            stage.setHeight(900);

            gameController.init(gameRoot, gameProperties);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
