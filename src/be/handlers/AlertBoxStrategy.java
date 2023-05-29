package be.handlers;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.stage.StageStyle;

import java.util.Optional;

public interface AlertBoxStrategy {
    Optional<ButtonType> showGenericAlert(Exception e);
    Optional<ButtonType> showCustomAlert(String content);


}
