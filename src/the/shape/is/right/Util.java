package the.shape.is.right;

import javafx.scene.control.Alert;

public class Util {

    /**
     * Shows an {@link Alert} dialog to the user.
     * @param type The type of the alert
     * @param title The title (shown on the top bar of the window)
     * @param header The header (shown above the context in the window)
     * @param context The context (the main body of the alert)
     */
    public static void showAlert(Alert.AlertType type, String title, String header, String context) {
        Alert a = new Alert(type);
        a.setTitle(title);
        a.setHeaderText(header);
        a.setContentText(context);
        a.showAndWait();
    }

}
