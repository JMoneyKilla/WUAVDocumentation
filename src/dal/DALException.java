package dal;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;



public class DALException extends RuntimeException {

    public DALException(String message) {
        super(message);
        handleException(message);

    }
    public static void handleException(String message) {

        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.CLOSE);
        alert.showAndWait();

    }
}
