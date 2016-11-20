package the.shape.is.right.controller;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import the.shape.is.right.GameProperties;
import the.shape.is.right.TheShapeIsRight;
import the.shape.is.right.Util;

public class ConfigController {

    //it seems these variables need to have public visibility in order to be assigned to by the FXMLLoader
    public ListView<ImageView> colorListView;
    public ListView<ImageView> shapeListView;
    public ChoiceBox<Integer> shapeNumberPicker;

    private Parent root;

    /**
     * Initializes the components of the window.
     * @param root The Parent containing all of our components
     */
    public void init(Parent root) {
        this.root = root;
        shapeNumberPicker.setItems(FXCollections.observableArrayList(3, 5, 7, 9, 11)); //Set of acceptable numbers of shapes
        shapeNumberPicker.setValue(5);

        ObservableList<ImageView> colorLs = FXCollections.observableArrayList();
        ObservableList<ImageView> shapeLs = FXCollections.observableArrayList();

        //Add each possible color and shape to their respective ListViews for user selection
        for (int i = 1; i <= 4; i++) {
            ImageView color = new ImageView("the/shape/is/right/resources/color_" + i + ".png");
            ImageView shape = new ImageView("the/shape/is/right/resources/shape_" + i + ".png");
            color.setFitWidth(32);
            color.setFitHeight(32);
            shape.setFitWidth(32);
            shape.setFitHeight(32);

            colorLs.add(color);
            shapeLs.add(shape);
        }

        colorListView.setItems(colorLs);
        colorListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        shapeListView.setItems(shapeLs);
        shapeListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    /**
     * Attempt to start the game
     * @param actionEvent disregarded
     */
    @FXML
    public void startGameButtonClick(ActionEvent actionEvent) {
        if (colorListView.getSelectionModel().getSelectedItems().isEmpty() ||   //Ensure that a color and shape are selected
                shapeListView.getSelectionModel().getSelectedItems().isEmpty()) {
            Util.showAlert(Alert.AlertType.ERROR, "Error", "Error: Invalid Configuration",
                    "Please select at least one color and at least one shape.");
            return;
        }

        //animate the root out of frame to the right
        Timeline tl = new Timeline();
        tl.getKeyFrames().add(new KeyFrame(new Duration(0), new KeyValue(root.translateXProperty(), 0)));
        tl.getKeyFrames().add(new KeyFrame(new Duration(500), new KeyValue(root.translateXProperty(), root.getLayoutBounds().getWidth())));
        tl.playFromStart();

        //after the animation, start the game
        tl.setOnFinished(e -> TheShapeIsRight.getInstance().startGame(
                new GameProperties(colorListView.getSelectionModel().getSelectedItems(),
                shapeListView.getSelectionModel().getSelectedItems(), shapeNumberPicker.getValue())));
    }
}
