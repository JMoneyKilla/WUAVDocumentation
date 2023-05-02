package GUI.Controllers;

import GUI.Models.ProjectModel;
import be.Project;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProjectsViewController implements Initializable {
    @FXML
    private AnchorPane paneProject;
    ProjectModel projectModel = ProjectModel.getInstance();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadData();
    }
    public void loadData(){
        int row = 0;
        int col = 0;
        for (Project project : projectModel.getProjects()) {
            StackPane stackPane = generateEventPane(project);
            paneProject.getChildren().add(stackPane);
            paneProject.setStyle("-fx-background-color: #fafafa; -fx-border-color: #000000");
            AnchorPane.setTopAnchor(stackPane, 25 + row * 160.0);
            AnchorPane.setLeftAnchor(stackPane, 50 + col * 205.0);
            col++;
            if (col == 2) {
                col = 0;
                row++;
            }
        }
    }
    /**
     * Generates a stackpane which will be used as visual tile for an event that the user can interact with
     * @param project
     * @return
     */
    public StackPane generateEventPane(Project project) {
        StackPane stackPane = new StackPane();

        stackPane.setPrefSize(175, 130);
        stackPane.setOnMouseEntered(e -> {
            stackPane.setScaleX(1.1);
            stackPane.setScaleY(1.1);
        });

        stackPane.setOnMouseExited(e -> {
            stackPane.setScaleX(1.0);
            stackPane.setScaleY(1.0);
        });


        VBox vBox = new VBox();
        vBox.setPrefSize(175, 130);
        vBox.setId("vbox");

        Label eventName = new Label();
        eventName.setPrefSize(175, 16);
        eventName.setFont(Font.font(16));
        eventName.setStyle("-fx-text-fill: #0C2D48;");
        eventName.setText(project.getName());
        eventName.setPadding(new Insets(5, 5, 5, 5));
        eventName.setId("eventName");


        HBox hbox = new HBox();
        hbox.setPrefSize(175, 25);
        hbox.setPadding(new Insets(0, 0, 0, 3));
        hbox.setId("hbox");

        MFXButton button1 = new MFXButton();
        button1.setPrefSize(83, 25);
        button1.setStyle("-fx-font-family: arial;\n" +
                "    -fx-font-size: 13px;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-border-radius: 5px;\n" +
                "    -fx-border-color: #000000;\n" +
                "    -fx-background-color: #145DA0;");
        button1.setPadding(new Insets(0, 0, 0, 5));
        button1.setText("View");
        button1.setId("viewButton");
        MFXButton button2 = new MFXButton();
        button2.setStyle("-fx-font-family: arial;\n" +
                "    -fx-font-size: 13px;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-border-radius: 5px;\n" +
                "    -fx-border-color: #000000;\n" +
                "    -fx-background-color: #145DA0;");
        button2.setText("Edit");
        button2.setId("editButton");
        button2.setPrefSize(83, 25);

        vBox.getChildren().add(eventName);
        hbox.getChildren().add(button1);
        hbox.getChildren().add(new Label(" "));
        hbox.getChildren().add(button2);
        vBox.getChildren().add(hbox);


        stackPane.getChildren().add(vBox);
        stackPane.setStyle("-fx-background-radius: 10px; -fx-background-color: #ffffff;");

        return stackPane;
    }

}
