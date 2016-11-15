package the.shape.is.right;

import javafx.collections.ObservableList;
import javafx.scene.image.ImageView;

public class GameProperties {

    public final ObservableList<ImageView> colors, shapes;
    public final int numShapes;

    public GameProperties(ObservableList<ImageView> colors, ObservableList<ImageView> shapes, int numShapes){
        this.colors = colors;
        this.shapes = shapes;
        this.numShapes = numShapes;
    }

}
