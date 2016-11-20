package the.shape.is.right.controller;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.util.Duration;
import the.shape.is.right.GameProperties;

import java.util.Random;

public class GameController {

    public FlowPane shapeFlowPane;
    public FlowPane trayFlowPane;
    private GameProperties gameProperties;
    private Random random = new Random();

    /**
     * Initialize the game window with the specified Parent and game properties
     * @param root the Parent our components are in
     * @param gameProperties The properties selected by the user
     */
    public void init(Parent root, GameProperties gameProperties) {
        this.gameProperties = gameProperties;

        //animate the window into frame from the left
        Timeline tl = new Timeline();
        tl.getKeyFrames().add(new KeyFrame(new Duration(0), new KeyValue(root.translateXProperty(),
                -root.getLayoutBounds().getWidth())));
        tl.getKeyFrames().add(new KeyFrame(new Duration(500), new KeyValue(root.translateXProperty(), 0)));
        tl.playFromStart();
        root.translateXProperty().set(-root.getLayoutBounds().getWidth());

        root.setVisible(true);

        showRandomShapes();
    }

    /**
     * Shows a random selection of N shapes given the user's allowed shapes and colors.
     * N is the configured number of shapes.
     */
    private void showRandomShapes() {
        for (int i = 0; i < gameProperties.numShapes; i++) {
            int shapeRand = random.nextInt(gameProperties.shapes.size()) + 1;
            int colorRand = random.nextInt(gameProperties.colors.size()) + 1;

            ImageView shape = new ImageView("the/shape/is/right/resources/playing-card-back.png");
            shape.setFitWidth(150);
            shape.setFitHeight(150);
            //shape.setBlendMode(BlendMode.MULTIPLY);
            trayFlowPane.getChildren().add(shape);
           // FlowPane.setMargin(g, new Insets(0, 10, 0, 10));
        }
        
        for (int i = 0; i < gameProperties.numShapes; i++) {
            int shapeRand = random.nextInt(gameProperties.shapes.size()) + 1;
            int colorRand = random.nextInt(gameProperties.colors.size()) + 1;

            ImageView shape = new ImageView("the/shape/is/right/resources/shape_" + shapeRand + ".png");
            ImageView color = new ImageView("the/shape/is/right/resources/color_" + colorRand + ".png");
            shape.setFitWidth(150);
            shape.setFitHeight(150);
            color.setFitWidth(150);
            color.setFitHeight(150);
            shape.setBlendMode(BlendMode.MULTIPLY);
            Group g = new Group(color, shape);
            shapeFlowPane.getChildren().add(g);
           // FlowPane.setMargin(g, new Insets(0, 10, 0, 10));
        }
    }
}