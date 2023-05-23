package GUI.Controllers;

import GUI.Models.ProjectModel;
import GUI.Models.UserModel;
import be.documents.IDocument;
import bll.helpers.DocumentBoxGenerator;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
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
    private AnchorPane documentPane;
    ProjectModel projectModel = ProjectModel.getInstance();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        projectModel.addedDocumentProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue) {
                populateDocumentView();
                projectModel.setAddedDocument(false);
            }
        });
        populateDocumentView();
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
        if(document.getDocumentType() == 1 || document.getDocumentType() == 2){
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
