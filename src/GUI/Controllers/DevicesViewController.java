package GUI.Controllers;

import GUI.Models.ProjectModel;
import be.Device;
import be.Project;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DevicesViewController implements Initializable {

    @FXML
    private AnchorPane devicePane;
    @FXML
    private MFXButton buttonBack, buttonDocuments, buttonDevices, buttonAddDevice, buttonGetReport;
    @FXML
    private VBox devicesBox;
    ProjectModel projectModel = ProjectModel.getInstance();
    private ListChangeListener<Device> currentListener;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateDeviceView();
        projectModel.refreshProjectDevices();
    }
    public void clickAddDevice(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Views/NewDeviceView.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);

        Stage popupStage = new Stage();
        popupStage.setScene(scene);
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.showAndWait();
    }

    /**
     * Adds listener to project devices. Iterates through project device list to generate
     * stack panes to display different devices
     */
    public void populateDeviceView(){
        if (currentListener != null)
            ProjectModel.getInstance().getProjectDevices().removeListener(currentListener);

        currentListener = c -> {
            int row = 0;
        devicePane.getChildren().clear();
        for (Device d: projectModel.getProjectDevices()) {
            StackPane sp = generateDevicePane(d);
            devicePane.getChildren().add(sp);
            AnchorPane.setTopAnchor(sp, 10 + row * 160.0);
            row++;
            devicePane.setMinHeight(250 + row*160);
        }
        };
        ProjectModel.getInstance().getProjectDevices().addListener(currentListener);
    }

    /**
     * Creates stack pane from given device to display device for user
     * @param device
     * @return StackPane
     */
    public StackPane generateDevicePane(Device device){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Views/TextDocumentView.fxml"));
        StackPane sp = null;
        try {
            sp = loader.load();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        TextDocumentViewController controller = loader.getController();
        controller.setDeviceLabels(device);
        controller.setDevice(device);

        return sp;
    }

}
