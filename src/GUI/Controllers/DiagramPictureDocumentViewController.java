package GUI.Controllers;

import GUI.Models.ProjectModel;
import GUI.Models.UserModel;
import be.documents.IDocument;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class DiagramPictureDocumentViewController{
    @FXML
    private MFXButton buttonDelete;
    @FXML
    private ImageView imgViewDoc;
    @FXML
    private Label lblDocName, lblCreatedBy, lblDateAdded, lblRefNum, lblDocId;
    @FXML
    private TextArea textAreaDescription;
    ProjectModel projectModel = ProjectModel.getInstance();
    IDocument document;

    public void setDocument(IDocument document){
        this.document = document;
    }

    public void setDocumentLabels(IDocument document) {
        imgViewDoc.setImage(new Image(document.getImageFile().getAbsolutePath()));

        lblDocName.setText(document.getDocumentName());
        lblCreatedBy.setText("Created By: " + UserModel.getInstance().getUserName(document.getUserId()));
        lblDateAdded.setText("Date Added: " + document.getDateAdded());
        lblRefNum.setText("Reference Number: " + document.getRefNumber());
        lblDocId.setText("Document ID: " + document.getDocumentId());

        textAreaDescription.setWrapText(true);
        textAreaDescription.setText(document.getDescription());
        textAreaDescription.setEditable(false);
    }

    public void clickDelete(ActionEvent actionEvent) {
        projectModel.deleteDocument(document);
        projectModel.setAddedDocument(true);
    }
}
