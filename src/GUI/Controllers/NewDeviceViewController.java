package GUI.Controllers;

import GUI.Models.ProjectModel;
import GUI.Models.UserModel;
import be.Device;
import bll.FacadeManager;
import bll.InputManager;
import bll.validator.DocumentValidator;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class NewDeviceViewController {
    @FXML
    private TextField textFieldDeviceName;
    @FXML
    private TextArea textAreDescription;
    @FXML
    private MFXButton buttonAddDevice, buttonCancel;
    FacadeManager facadeManager = new FacadeManager();
    ProjectModel projectModel = ProjectModel.getInstance();
    UserModel userModel = UserModel.getInstance();
    InputManager inputManager = new InputManager();
    public void clickAddDevice(ActionEvent actionEvent) {
        String deviceName = textFieldDeviceName.getText();
        String description = textAreDescription.getText();
        int projectId = projectModel.getSelectedProject().getId();
        int userId = userModel.getLoggedInUser().getId();
        String dateAdded = inputManager.getDateToday();
        if(!deviceName.isBlank() && !deviceName.isEmpty() && !description.isEmpty() && !deviceName.isBlank()){
            Device device = new Device(0, projectId, userId, deviceName, description, dateAdded);
            try {
                facadeManager.createDevice(device);
                projectModel.refreshProjectDevices();
                projectModel.setAddedDevice(true);
                Stage stage = (Stage) buttonAddDevice.getScene().getWindow();
                stage.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void clickCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) buttonCancel.getScene().getWindow();
        stage.close();
    }
}
