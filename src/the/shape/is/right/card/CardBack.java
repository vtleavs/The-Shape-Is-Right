package the.shape.is.right.card;

import javafx.scene.image.ImageView;

public class CardBack extends CardImageGroup {

    public CardBack(){
        super(buildImageView());
    }

    private static ImageView buildImageView() {
        ImageView shape = new ImageView("the/shape/is/right/resources/playing-card-back.png");
        shape.setFitWidth(150);
        shape.setFitHeight(150);
        return shape;
    }

}
