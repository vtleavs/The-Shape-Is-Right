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
    private GameProperties gameProperties;
    private Random random = new Random();

    public void init(Parent root, GameProperties gameProperties) {
        this.gameProperties = gameProperties;

        Timeline tl = new Timeline();
        tl.getKeyFrames().add(new KeyFrame(new Duration(0), new KeyValue(root.translateXProperty(), -root.getLayoutBounds().getWidth())));
        tl.getKeyFrames().add(new KeyFrame(new Duration(500), new KeyValue(root.translateXProperty(), 0)));
        tl.playFromStart();
        root.translateXProperty().set(-root.getLayoutBounds().getWidth());

        root.setVisible(true);

        showRandomShapes();
    }

    private void showRandomShapes() {
        for (int i = 0; i < gameProperties.numShapes; i++) {
            int shapeRand = random.nextInt(gameProperties.shapes.size()) + 1;
            int colorRand = random.nextInt(gameProperties.colors.size()) + 1;

            ImageView shape = new ImageView("the/shape/is/right/resources/shape_" + shapeRand + ".png");
            ImageView color = new ImageView("the/shape/is/right/resources/color_" + colorRand + ".png");
            shape.setFitWidth(64);
            shape.setFitHeight(64);
            color.setFitWidth(64);
            color.setFitHeight(64);
            shape.setBlendMode(BlendMode.MULTIPLY);
            Group g = new Group(color, shape);
            shapeFlowPane.getChildren().add(g);
            FlowPane.setMargin(g, new Insets(0, 10, 0, 10));
        }
    }
}