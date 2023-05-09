package bll.helpers;

import GUI.Models.ProjectModel;
import GUI.Models.UserModel;
import be.documents.IDocument;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DocumentBoxGenerator {
    UserModel userModel = UserModel.getInstance();
    public HBox buildDocumentBox(IDocument document){
        int docType = document.getDocumentType();
        HBox hBox = null;
        if(docType == 1 ||docType == 2)
            return diagramPictureDocBox(document);
        if(docType == 3)
            return textDocBox(document);
        return hBox;
    }

    public HBox diagramPictureDocBox(IDocument document){
            HBox hbox = new HBox();
            hbox.setPrefSize(1000, 300);

            VBox leftVbox = new VBox();
            leftVbox.setPrefSize(300, 300);

            ImageView imageView = new ImageView(document.getAbsolutePath());
            imageView.setFitWidth(200);
            imageView.setFitHeight(200);

            Label createdByLabel = new Label("Created by: " + userModel.getUserName(document.getUserId()));
            Label dateAddedLabel = new Label("Date added: " + document.getDateAdded());
            Label documentIdLabel = new Label("Document ID: " + document.getDocumentId());

            leftVbox.getChildren().addAll(imageView, createdByLabel, dateAddedLabel, documentIdLabel);

            VBox rightVbox = new VBox();
            rightVbox.setPrefSize(700, 300);

            TextArea descriptionTextArea = new TextArea(document.getDescription());
            descriptionTextArea.setEditable(false);
            descriptionTextArea.setWrapText(true);

            rightVbox.getChildren().add(descriptionTextArea);

            hbox.getChildren().addAll(leftVbox, rightVbox);

            return hbox;
    }

    public HBox textDocBox(IDocument document){
        HBox hbox = new HBox();
        hbox.setPrefSize(1000, 150);

        VBox leftVbox = new VBox();
        leftVbox.setPrefSize(300, 150);

        Label createdByLabel = new Label("Created by: " + userModel.getUserName(document.getUserId()));
        Label dateAddedLabel = new Label("Date added: " + document.getDateAdded());
        Label documentIdLabel = new Label("Document ID: " + document.getDocumentId());

        leftVbox.getChildren().addAll(createdByLabel, dateAddedLabel, documentIdLabel);

        VBox rightVbox = new VBox();
        rightVbox.setPrefSize(700, 150);

        TextArea descriptionTextArea = new TextArea(document.getDescription());
        descriptionTextArea.setEditable(false);
        descriptionTextArea.setWrapText(true);

        rightVbox.getChildren().add(descriptionTextArea);

        hbox.getChildren().addAll(leftVbox, rightVbox);

        return hbox;
    }
}
