package GUI.Controllers;

import GUI.Models.ProjectModel;
import GUI.Models.UserModel;
import be.Device;
import be.enums.DocumentType;
import be.documents.IDocument;
import be.enums.UserType;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.collections.ListChangeListener;
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
    private ListChangeListener<IDocument> currentListener;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateDocumentView();
        projectModel.refreshProjectDocuments();
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

    /**
     *  Adds listener to project documents. Iterates through project device list to generate
     *  stack panes to display different documents
     */
    public void populateDocumentView(){
        if (currentListener != null)
            ProjectModel.getInstance().getProjectDocuments().removeListener(currentListener);

        currentListener = c -> {
            int row = 0;
            documentPane.getChildren().clear();
            for (IDocument d : projectModel.getProjectDocuments()) {
                StackPane sp = generateDocumentPane(d);
                documentPane.getChildren().add(sp);
                AnchorPane.setTopAnchor(sp, 10 + row * 160.0);
                row++;
                documentPane.setMinHeight(250 + row * 160);
            }
        };
        ProjectModel.getInstance().getProjectDocuments().addListener(currentListener);

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
