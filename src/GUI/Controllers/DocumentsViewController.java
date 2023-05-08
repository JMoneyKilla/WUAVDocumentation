package GUI.Controllers;

import GUI.Models.ProjectModel;
import GUI.Models.UserModel;
import be.documents.IDocument;
import bll.helpers.DocumentBoxGenerator;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
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
        projectModel.refreshProjectDocuments();
        System.out.println(projectModel.getProjectDocuments().get(0).getDocumentType());
        for (IDocument document: projectModel.getProjectDocuments()) {
            HBox hBox = docBoxGenerator.buildDocumentBox(document);
            documentsBox.getChildren().add(hBox);
        }
    }


    public void clickBack(ActionEvent actionEvent) {
        //TODO
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Views/NewDocumentView.fxml"));
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
