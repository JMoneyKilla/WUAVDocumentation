package be.handlers;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.stage.StageStyle;

import java.util.Optional;

public class IOAlertStrategy implements AlertBoxStrategy{
    @Override
    public Optional<ButtonType> showGenericAlert(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(e.getMessage());
        alert.getDialogPane().getChildren()
                .stream()
                .filter(node -> node instanceof Label)
                .forEach(node -> ((Label)node)
                        .setMinHeight(Region.USE_PREF_SIZE));
        alert.setResizable(false);
        alert.getDialogPane().setMaxWidth(350);
        alert.initStyle(StageStyle.UNDECORATED);
        return alert.showAndWait();
    }

    @Override
    public Optional<ButtonType> showCustomAlert(String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.getDialogPane().getChildren()
                .stream()
                .filter(node -> node instanceof Label)
                .forEach(node -> ((Label)node)
                        .setMinHeight(Region.USE_PREF_SIZE));
        alert.setResizable(false);
        alert.getDialogPane().setMaxWidth(350);
        alert.initStyle(StageStyle.UNDECORATED);
        return alert.showAndWait();
    }
}
