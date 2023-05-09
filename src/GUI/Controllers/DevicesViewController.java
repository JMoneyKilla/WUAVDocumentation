package GUI.Controllers;

import GUI.Models.ProjectModel;
import GUI.Models.UserModel;
import be.Device;
import be.User;
import be.documents.IDocument;
import bll.helpers.DeviceBoxGenerator;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
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
        try{
            for (Device device : projectModel.getProjectDevices()) {
                devicesBox.getChildren().add(deviceBoxGenerator.buildDeviceBox(device));
            }
        }catch (IndexOutOfBoundsException e){
            System.out.println("No documents");
        }
        if(userModel.getLoggedInUser().getType()==3){
            buttonAddDevice.setVisible(false);
        }
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


}
