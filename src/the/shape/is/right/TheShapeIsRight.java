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
 *
 * @author Ben, Brian
 */
public class TheShapeIsRight extends Application 
{

    private static TheShapeIsRight instance;
    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        instance = this;

        stage = primaryStage;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("forms/config.fxml"));
        Parent configRoot = loader.load();

        ConfigController configController = loader.getController();

        configController.init(configRoot);

        primaryStage.setScene(new Scene(configRoot));
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.setWidth(600);
        primaryStage.setHeight(400);
    }

    public static TheShapeIsRight getInstance() {
        return instance;
    }

    public void startGame(GameProperties gameProperties) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("forms/game.fxml"));
        try {
            Parent gameRoot = loader.load();
            GameController gameController = loader.getController();

            stage.setScene(new Scene(gameRoot));
            stage.setWidth(600);
            stage.setHeight(400);

            gameController.init(gameRoot, gameProperties);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        launch(args);
    }
    
}
