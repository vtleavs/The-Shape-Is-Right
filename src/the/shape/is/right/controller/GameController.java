package the.shape.is.right.controller;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.util.Duration;
import the.shape.is.right.GameProperties;
import the.shape.is.right.Util;
import the.shape.is.right.card.CardBack;
import the.shape.is.right.card.CardImageGroup;
import the.shape.is.right.card.CardShape;

import java.util.*;

public class GameController {

    /* Controls */
    public FlowPane shapeFlowPane;
    public FlowPane trayFlowPane;
    public Button submitGuessButton;
    public Label pointsLabel;

    /* Fields */
    private GameProperties gameProperties;
    private Random random = new Random();
    private List<CardShape> correctShapeOrdering = new ArrayList<>();
    private int editingIndex = 0;
    private int revealedIndex = -1;
    private int points = 0;


    /**
     * Initialize the game window with the specified Parent and game properties
     *
     * @param root           the Parent our components are in
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

        insertRandomShapes();

        submitGuessButton.setDisable(true);
    }

    /**
     * Shows a random selection of N shapes given the user's allowed shapes and colors.
     * N is the configured number of shapes.
     */
    private void insertRandomShapes() {
        for (int i = 0; i < gameProperties.numShapes; i++) {
            trayFlowPane.getChildren().add(new CardBack());
        }

        for (int i = 0; i < gameProperties.numShapes; i++) {
            int shapeRand = random.nextInt(gameProperties.shapes.size());
            int colorRand = random.nextInt(gameProperties.colors.size());

            CardShape s = new CardShape(gameProperties.shapes.get(shapeRand).getImage(),
                    gameProperties.colors.get(colorRand).getImage(), shapeRand, colorRand);

            setCardShapeOnClicked(s);

            correctShapeOrdering.add(s);
        }


        //apparently getChildren.sort() doesn't work, so here we have this silly workaround:
        ObservableList<Node> workingCollection = FXCollections.observableArrayList(correctShapeOrdering);
        Collections.sort(workingCollection, null);
        shapeFlowPane.getChildren().setAll(workingCollection);
        shapeFlowPane.getChildren().forEach(s -> {
            Timeline t = new Timeline(
                    new KeyFrame(new Duration(200), new KeyValue(s.rotateProperty(), 0)),
                    new KeyFrame(new Duration(1000), new KeyValue(s.rotateProperty(), 360))
            );
            t.playFromStart();
        });
    }

    private void removeCardShape(ObservableList<Node> o, CardShape cs) {
        Node n;
        for (Iterator<Node> it = o.listIterator(); it.hasNext(); ) {
            n = it.next();
            if (n.getId().equals(cs.getId())) {
                it.remove();
            }
        }
    }

    private void resetEditingIndex() {
        for (int i = revealedIndex + 1; i < trayFlowPane.getChildren().size(); i++) {
            CardImageGroup cig = (CardImageGroup) trayFlowPane.getChildren().get(i);

            if (cig instanceof CardBack) {
                editingIndex = i;
                submitGuessButton.setDisable(true);
                return;
            }
        }

        submitGuessButton.setDisable(false);
    }

    public void submitGuessButtonClicked(MouseEvent mouseEvent) {
        revealedIndex++;

        CardShape correctShape = correctShapeOrdering.get(revealedIndex);
        CardShape current = (CardShape) trayFlowPane.getChildren().get(revealedIndex);

        //If they guessed correctly
        if (current.shapeEquals(correctShape)) {
            points++;
            pointsLabel.setText("Points: " + points);
            current.disableClicks();
            Util.showAlert(Alert.AlertType.INFORMATION, "Guess Results", "You guessed correctly!", null);
        } else { //otherwise
            for (int i = revealedIndex + 1; i < trayFlowPane.getChildren().size(); i++) {

                //if this is the shape that belongs on the revealed index
                if (((CardShape) trayFlowPane.getChildren().get(i)).shapeEquals(correctShape)) {
                    shapeFlowPane.getChildren().add(setCardShapeOnClicked(current.clone()));
                    trayFlowPane.getChildren().set(i, new CardBack());
                    break;
                }
            }

            trayFlowPane.getChildren().set(revealedIndex, setCardShapeOnClicked(correctShape.clone()));
            ((CardShape) trayFlowPane.getChildren().get(revealedIndex)).disableClicks();

            resetEditingIndex();

            Util.showAlert(Alert.AlertType.WARNING, "Guess Results", "You guessed incorrectly!", null);
        }

        if (revealedIndex >= gameProperties.numShapes - 2) { //all have been revealed
            Util.showAlert(Alert.AlertType.INFORMATION, "Game Over", "Game Over!", "You got " + (points == 1 ? (1 + "point!") : (points + " points!")));
        }
    }

    private CardShape setCardShapeOnClicked(CardShape cs) {
        cs.setOnMouseClicked(e -> {

            if (cs.isClickDisabled()) {
                return;
            }

            if (cs.isInTray()) {
                trayFlowPane.getChildren().set(trayFlowPane.getChildren().indexOf(cs), new CardBack());
                shapeFlowPane.getChildren().add(cs);
                cs.setInTray(false);
            } else {
                trayFlowPane.getChildren().set(editingIndex, cs);
                removeCardShape(shapeFlowPane.getChildren(), cs);
                cs.setInTray(true);
            }

            resetEditingIndex();
        });

        return cs;
    }

    private void animateCard(Node n, double fromX, double fromY, double toX, double toY, javafx.event.EventHandler<javafx.event.ActionEvent> doAfter) {
        Timeline t = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(n.translateXProperty(), fromX),
                        new KeyValue(n.translateYProperty(), fromY)),
                new KeyFrame(new Duration(1000),
                        new KeyValue(n.translateXProperty(), toX),
                        new KeyValue(n.translateYProperty(), toY)));

        t.playFromStart();
        if (doAfter != null) {
            t.setOnFinished(doAfter);
        }
    }
}