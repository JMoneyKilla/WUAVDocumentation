package GUI.Controllers;

import GUI.Models.ProjectModel;
import GUI.Models.UserModel;
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
    private MFXButton buttonAddDocument;
    @FXML
    private VBox documentsBox;
    DocumentBoxGenerator docBoxGenerator = new DocumentBoxGenerator();
    ProjectModel projectModel = ProjectModel.getInstance();
    UserModel userModel = UserModel.getInstance();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("initialized");

       for (IDocument document: projectModel.getProjectDocuments()) {
            documentsBox.getChildren().add(docBoxGenerator.buildDocumentBox(document));
      }

     //  for (IDocument document: projectModel.getProjectDocuments()) {
     //       documentsBox.getChildren().add(docBoxGenerator.buildDocumentBox(document));
     //   }
        if(userModel.getLoggedInUser().getType()==3){
            buttonAddDocument.setVisible(false);
        }
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


}
