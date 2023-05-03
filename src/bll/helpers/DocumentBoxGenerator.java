package bll.helpers;

import be.documents.IDocument;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DocumentBoxGenerator {

    public HBox buildDocumentBox(IDocument document){
        HBox hBox = switch (document.getDocumentType()) {
            case 1, 2 -> diagramPictureDocBox(document);
            case 3 -> textDocBox(document);
            default -> null;
        };
        return hBox;
    }

    public HBox diagramPictureDocBox(IDocument document){
        HBox hbox = new HBox();
        VBox vBoxLeft = new VBox();
        VBox vBoxRight = new VBox();
        ImageView imageView = new ImageView(document.getAbsolutePath());
        TextArea description = new TextArea(document.getDescription());
        Label documentName = new Label(document.getDocumentName());
        Label dateAdded = new Label("Date added: " + document.getDateAdded());
        Label createdBy = new Label("Created by:" + document.getUserId());
        Label refNumb = new Label("Reference number: " + document.getRefNumber());
        Label documentId = new Label("Document ID: " + document.getDocumentId());

        hbox.setPrefSize(725, 200);
        hbox.setStyle("-fx-background-color: #fafafa; -fx-border-color: #000000");

        vBoxLeft.setPrefSize(200, 200);
        vBoxLeft.getChildren().addAll(documentName, imageView, refNumb);

        vBoxRight.setPrefSize(525, 200);
        vBoxRight.getChildren().addAll(description, createdBy, dateAdded, documentId);

        hbox.getChildren().addAll(vBoxLeft, vBoxRight);

        return hbox;

    }

    public HBox textDocBox(IDocument document){
        HBox hbox = new HBox();
        VBox vBox = new VBox();
        TextArea description = new TextArea(document.getDescription());
        Label documentName = new Label(document.getDocumentName());
        Label dateAdded = new Label("Date added: " + document.getDateAdded());
        Label createdBy = new Label("Created by:" + document.getUserId());
        Label documentId = new Label("Document ID: " + document.getDocumentId());

        hbox.setPrefSize(370, 100);
        hbox.setStyle("-fx-background-color: #fafafa; -fx-border-color: #000000");

        vBox.setPrefSize(100, 100);
        vBox.getChildren().addAll(documentName, description, createdBy, dateAdded, documentId);

        hbox.getChildren().add(vBox);

        return hbox;

    }
}
