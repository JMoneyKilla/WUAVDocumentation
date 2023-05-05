package GUI.Controllers;

import GUI.Models.ProjectModel;
import GUI.Models.UserModel;
import be.Device;
import be.User;
import bll.helpers.DeviceBoxGenerator;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class DevicesViewController implements Initializable {

    @FXML
    private MFXButton buttonBack, buttonDocuments, buttonDevices, buttonAddDevice, buttonGetReport;
    @FXML
    private VBox devicesBox;
    DeviceBoxGenerator deviceBoxGenerator = new DeviceBoxGenerator();
    ProjectModel projectModel = ProjectModel.getInstance();
    UserModel userModel = UserModel.getInstance();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Device device : projectModel.getProjectDevices()) {
            devicesBox.getChildren().add(deviceBoxGenerator.buildDeviceBox(device));
        }
        if(userModel.getLoggedInUser().getType()==3){
            buttonAddDevice.setVisible(false);
        }
    }
    public void clickAddDevice(ActionEvent actionEvent) {
        //TODO
    }


}
