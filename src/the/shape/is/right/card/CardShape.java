package the.shape.is.right.card;

import javafx.scene.Node;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CardShape extends CardImageGroup implements Comparable<CardShape> {

    private int shapeRand;
    private int colorRand;
    private boolean clickDisabled;

    public CardShape(Image shape, Image color, int shapeRand, int colorRand) {
        super((Node[]) getImageViews(color, shape));

        this.shapeRand = shapeRand;
        this.colorRand = colorRand;
    }

    private static ImageView[] getImageViews(Image shapeImage, Image colorImage) {
        ImageView shape = new ImageView(shapeImage);
        ImageView color = new ImageView(colorImage);
        shape.setFitWidth(150);
        shape.setFitHeight(150);
        color.setFitWidth(150);
        color.setFitHeight(150);
        shape.setBlendMode(BlendMode.MULTIPLY);

        return new ImageView[]{color, shape};
    }

    /**
     * Compares this with another CardShape, based solely on the color.
     *
     * @param o The other CardShape
     * @return the value of (this.colorRand - o.colorRand)
     */
    @Override
    public int compareTo(CardShape o) {
        return colorRand - o.colorRand;
    }

    public boolean shapeEquals(CardShape o){
        return this.shapeRand == o.shapeRand && this.colorRand == o.colorRand;
    }

    public void disableClicks(){
        clickDisabled = true;
    }

    public boolean isClickDisabled(){
        return clickDisabled;
    }

    @Override
    public CardShape clone(){
        CardShape cs = new CardShape(((ImageView) getChildren().get(1)).getImage(),
                ((ImageView) getChildren().get(0)).getImage(), shapeRand, colorRand);
        cs.clickDisabled = clickDisabled;
        return cs;
    }
}
