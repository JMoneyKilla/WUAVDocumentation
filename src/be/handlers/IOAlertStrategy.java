package be.handlers;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.stage.StageStyle;

public class IOAlertStrategy implements AlertBoxStrategy{
    @Override
    public void showGenericAlert(Exception e) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
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
        alert.showAndWait();
    }

    @Override
    public void showCustomAlert(String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
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
        alert.showAndWait();
    }
}
