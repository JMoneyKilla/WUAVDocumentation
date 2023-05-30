package GUI.Controllers;

import be.diagram.LineSymbol;
import be.handlers.AlertBoxStrategy;
import be.handlers.IOAlertStrategy;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.utils.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DiagramProgramViewController implements Initializable {

    @FXML
    private HBox controlBox, powerBox, cabelBox, screenBox, projectorBox, speakerBox;
    @FXML
    private MFXButton buttonSave, buttonCancel;
    @FXML
    private ImageView symbolScreen, symbolControl, symbolPower, symbolSpeaker, symbolProjector;
    @FXML
    private Pane mainPane;
    private Image selectedImage;
    @FXML
    private Line cabel;
    private double startX;
    private double startY;
    boolean newSymbol = false;
    boolean isDrawing = false;



    public void handleImageClick(MouseEvent event) {
        ImageView clickedImageView = (ImageView) event.getSource();
        selectedImage = clickedImageView.getImage();
        newSymbol = true;
        isDrawing = false;
    }

    public void clickPlaceSymbol(MouseEvent event) {
        if(newSymbol){
            ImageView symbol = new ImageView(selectedImage);
            symbol.setTranslateX(event.getSceneX());
            symbol.setTranslateY(event.getSceneY());
            symbol.setScaleX(1.3);
            symbol.setScaleY(1.3);
            makeDraggable(symbol);
            symbol.setCursor(Cursor.HAND);
            mainPane.getChildren().add(symbol);
            newSymbol = false;
        }
        if(isDrawing){
            Line newCabel = new LineSymbol(event.getSceneX(), event.getSceneY(), event.getSceneX() + 20, event.getSceneY());
            newCabel.setStrokeWidth(5);
            newCabel.setCursor(Cursor.HAND);
            mainPane.getChildren().add(newCabel);
            isDrawing = false;
        }
    }

    /**
     * Makes Nodes click and draggable when creating diagram
     * @param node
     */
    public void makeDraggable(Node node){
        // a = b - c
        // a + c = b
        // c = b - a
        node.setOnMousePressed(e -> {
            //calc offset
            startX = e.getSceneX() - node.getTranslateX();
            startY = e.getSceneY() - node.getTranslateY();
            node.toFront();
        });
        node.setOnMouseDragged(e -> {
            double newX = e.getSceneX() - startX;
            double newY = e.getSceneY() - startY;
            if (newX >= 0 && newX <= mainPane.getWidth() - 50 &&
                    newY >= 0 && newY <= mainPane.getHeight() - 30) {
                //set
                node.setTranslateX(e.getSceneX() - startX);
                node.setTranslateY(e.getSceneY() - startY);
            }

        });
    }

    /**
     * Captures the main pane where diagram is drawn and saves it as a png image
     */
    private void takeAndSaveScreenshot() {
        // Capture the screenshot of the Pane
        WritableImage image = mainPane.snapshot(new SnapshotParameters(), null);

        // Create the folder if it doesn't exist
        File folder = new File("savedImages");
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // Create a file for saving the screenshot
        File file = new File(folder, "diagram.png");

        try {
            // Write the image to the file
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        } catch (IOException e) {
            AlertBoxStrategy alertBoxStrategy = new IOAlertStrategy();
            alertBoxStrategy.showCustomAlert("Error saving diagram screenshot. Please restart program and try again.");
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image screen = new Image("resource/screenSymbol.png");
        Image control = new Image("resource/controlCenterSymbol.png");
        Image power = new Image("resource/powerSymbol.png");
        Image speaker = new Image("resource/speakerSymbol.png");
        Image projector = new Image("resource/projectorSymbol.png");

        symbolScreen.setImage(screen);
        symbolScreen.setCursor(Cursor.HAND);

        symbolControl.setImage(control);
        symbolControl.setCursor(Cursor.HAND);

        symbolPower.setImage(power);
        symbolPower.setCursor(Cursor.HAND);

        symbolSpeaker.setImage(speaker);
        symbolSpeaker.setCursor(Cursor.HAND);

        symbolProjector.setImage(projector);
        symbolProjector.setCursor(Cursor.HAND);

        cabel.setCursor(Cursor.HAND);
    }

    public void lineClicked(MouseEvent event) {
        newSymbol = false;
        isDrawing = true;
    }

    public void clickSave(ActionEvent actionEvent) {
        takeAndSaveScreenshot();
        Stage stage = (Stage) buttonSave.getScene().getWindow();
        stage.close();
    }

    public void clickCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) buttonCancel.getScene().getWindow();
        stage.close();
    }
}
