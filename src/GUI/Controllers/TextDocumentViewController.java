package GUI.Controllers;

import GUI.Models.ProjectModel;
import GUI.Models.UserModel;
import be.Device;
import be.documents.IDocument;
import be.enums.UserType;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class TextDocumentViewController implements Initializable {
    @FXML
    private MFXButton buttonDelete;
    @FXML
    private Label lblDocName, lblCreatedBy, lblDateAdded, lblRefNum, lblDocId;

    @FXML
    private TextArea textAreaDescription;

    IDocument document;
    Device device;

    public void setDocument(IDocument document){
        this.document = document;
    }
    public void setDevice(Device device){
        this.device = device;
    }

    /**
     * Sets information of document to be displayed
     * @param document
     */
    public void setDocumentLabels(IDocument document) {
        lblDocName.setText(document.getDocumentName());
        lblCreatedBy.setText("Created By: " + UserModel.getInstance().getUserName(document.getUserId()));
        lblDateAdded.setText("Date Added: " + document.getDateAdded());
        lblRefNum.setText("Reference Number: " + document.getRefNumber());
        lblDocId.setText("Document ID: " + document.getDocumentId());

        textAreaDescription.setWrapText(true);
        textAreaDescription.setText(document.getDescription());
        textAreaDescription.setEditable(false);
    }

    /**
     * Sets information of device to be displayed
     * @param device
     */
    public void setDeviceLabels(Device device){
        lblDocName.setText(device.getDeviceName());
        lblCreatedBy.setText("Created By: " + UserModel.getInstance().getUserName(device.getUserId()));
        lblDateAdded.setText("Date Added: " + device.getDateAdded());
        lblRefNum.setVisible(false);
        lblDocId.setText("Device ID: " + device.getDeviceId());

        textAreaDescription.setWrapText(true);
        textAreaDescription.setText(device.getDescription());
        textAreaDescription.setEditable(false);
    }

    public void clickDelete(ActionEvent actionEvent) {
        if(document != null) {
            ProjectModel.getInstance().deleteDocument(document);
            ProjectModel.getInstance().refreshProjectDocuments();
        }
        if(device != null){
            ProjectModel.getInstance().deleteDevice(device);
            ProjectModel.getInstance().refreshProjectDevices();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(UserModel.getInstance().getLoggedInUser().getType() == UserType.SALES_PERSON){
            buttonDelete.setDisable(true);
            buttonDelete.setVisible(false);
        }
    }
}
