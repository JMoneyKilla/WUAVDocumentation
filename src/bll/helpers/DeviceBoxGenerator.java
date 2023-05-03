package bll.helpers;

import be.Device;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DeviceBoxGenerator {

    public HBox buildDeviceBox(Device device){
        HBox hBox = new HBox();
        VBox vBoxLeft = new VBox();
        VBox vBoxRight = new VBox();
        Label deviceName = new Label(device.getDeviceName());
        Label deviceId = new Label("Device ID: " + deviceName.getId());
        Label userId = new Label("Added by: " + device.getUserId());
        Label dateAdded = new Label("Date added: " + device.getDateAdded());

        TextArea description = new TextArea(device.getDescription());
        description.setWrapText(true);

        hBox.setPrefSize(725, 200);
        hBox.setStyle("-fx-background-color: #fafafa; -fx-border-color: #000000");

        vBoxLeft.setPrefSize(200, 200);
        vBoxLeft.getChildren().addAll(deviceName, deviceId, userId, dateAdded);

        vBoxRight.setPrefSize(525, 200);
        vBoxRight.getChildren().add(description);

        hBox.getChildren().addAll(vBoxLeft, vBoxRight);

        return hBox;
    }
}
