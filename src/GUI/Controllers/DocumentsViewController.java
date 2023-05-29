package GUI.Controllers;

import GUI.Models.ProjectModel;
import GUI.Models.UserModel;
import be.enums.DocumentType;
import be.documents.IDocument;
import be.enums.UserType;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DocumentsViewController implements Initializable {

    @FXML
    private AnchorPane documentPane;
    @FXML
    MFXButton buttonAddDocument;
    ProjectModel projectModel = ProjectModel.getInstance();
    UserModel userModel = UserModel.getInstance();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        projectModel.addedDocumentProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue) {
                populateDocumentView();
                projectModel.setAddedDocument(false);
            }
        });
        populateDocumentView();
        if(userModel.getLoggedInUser().getType()== UserType.SALES_PERSON){
            buttonAddDocument.setVisible(false);
        }
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

    public void populateDocumentView(){
        int row = 0;
        documentPane.getChildren().clear();
        for (IDocument d: projectModel.getProjectDocuments()) {
            StackPane sp = generateDocumentPane(d);
            documentPane.getChildren().add(sp);
            AnchorPane.setTopAnchor(sp, 10 + row * 160.0);
            row++;
            documentPane.setMinHeight(250 + row*160);
        }
    }

    public StackPane generateDocumentPane(IDocument document) {
        if(document.getDocumentType() == DocumentType.DIAGRAM_DOC || document.getDocumentType() == DocumentType.PICTURE_DOC){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Views/DiagramPictureDocumentView.fxml"));
            StackPane sp = null;
            try {
                sp = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            DiagramPictureDocumentViewController controller = loader.getController();
            controller.setDocumentLabels(document);
            controller.setDocument(document);

            return sp;
        }
        else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Views/TextDocumentView.fxml"));
            StackPane sp = null;
            try {
                sp = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            TextDocumentViewController controller = loader.getController();
            controller.setDocumentLabels(document);
            controller.setDocument(document);

            return sp;
        }
    }


}
