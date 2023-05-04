package GUI.Controllers;

import GUI.Models.ProjectModel;
import be.documents.IDocument;
import bll.helpers.DocumentBoxGenerator;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class DocumentsViewController implements Initializable {

    @FXML
    private MFXButton buttonAddDocument, buttonGetReport, buttonDevices, buttonDocuments, buttonBack;
    @FXML
    private VBox documentsBox;
    DocumentBoxGenerator docBoxGenerator = new DocumentBoxGenerator();
    ProjectModel projectModel = ProjectModel.getInstance();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("initialized");
     //  for (IDocument document: projectModel.getProjectDocuments()) {
     //       documentsBox.getChildren().add(docBoxGenerator.buildDocumentBox(document));
     //   }
    }

    public void clickBack(ActionEvent actionEvent) {
        //TODO
    }

    public void clickDocuments(ActionEvent actionEvent) {
        //TODO
    }

    public void clickDevices(ActionEvent actionEvent) {
        //TODO
    }

    public void clickGetReport(ActionEvent actionEvent) {
        //TODO
    }

    public void clickAddDocument(ActionEvent actionEvent) {
        //TODO
    }

    public void buttonBack(ActionEvent actionEvent) {
    }

    public void buttonDocuments(ActionEvent actionEvent) {
    }

    public void buttonDevices(ActionEvent actionEvent) {
    }

    public void buttonGetReport(ActionEvent actionEvent) {
    }

    public void buttonAddDocuments(ActionEvent actionEvent) {
    }
}
