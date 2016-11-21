package the.shape.is.right.card;

import javafx.scene.Group;
import javafx.scene.Node;

import java.util.UUID;

public abstract class CardImageGroup extends Group {

    private boolean inTray = false;

    CardImageGroup(Node... nodes) {
        super(nodes);
        setId(UUID.randomUUID().toString());
    }

    public boolean isInTray() {
        return inTray;
    }

    public void setInTray(boolean inTray) {
        this.inTray = inTray;
    }

    @Override
    public boolean equals(Object other){
        return other instanceof CardImageGroup && ((CardImageGroup) other).getId().equals(getId());
    }

}
