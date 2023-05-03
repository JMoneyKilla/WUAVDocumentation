package bll.helpers;

import GUI.Controllers.MainViewController;
import be.Project;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.io.IOException;

public class ProjectPaneGenerator {
    public StackPane buildProjectPane(Project project) {
        StackPane stackPane = new StackPane();

        stackPane.setPrefSize(400, 132);
        stackPane.setOnMouseEntered(e -> {
            stackPane.setScaleX(1.05);
            stackPane.setScaleY(1.05);
        });

        stackPane.setOnMouseExited(e -> {
            stackPane.setScaleX(1.0);
            stackPane.setScaleY(1.0);
        });


        VBox vBox = new VBox();
        vBox.setPrefSize(stackPane.getPrefWidth(), 132);
        vBox.setId("vbox");

        Label eventName = new Label();
        eventName.setPrefSize(stackPane.getPrefWidth(), 16);
        eventName.setFont(Font.font(16));
        eventName.setStyle("-fx-text-fill: #0C2D48;");
        eventName.setText(project.getName());
        eventName.setTextAlignment(TextAlignment.CENTER);
        eventName.setPadding(new Insets(5, 5, 5, 150));
        eventName.setId("eventName");

        Label customerName = new Label();
        customerName.setPrefSize(stackPane.getPrefWidth(), 11);
        customerName.setFont(Font.font(13));
        customerName.setStyle("-fx-text-fill: #0C2D48;");
        customerName.setText("Customer: " + project.getCustomerName());
        customerName.setPadding(new Insets(5, 5, 5, 10));
        customerName.setId("customerName");

        Label date = new Label();
        date.setPrefSize(stackPane.getPrefWidth(), 11);
        date.setFont(Font.font(13));
        date.setStyle("-fx-text-fill: #0C2D48;");
        date.setText("Last visited: " + project.dateLastVisited());
        date.setPadding(new Insets(5, 5, 5, 10));
        date.setTextAlignment(TextAlignment.CENTER);
        date.setId("date");

        Label address = new Label();
        address.setPrefSize(stackPane.getPrefWidth(), 11);
        address.setFont(Font.font(13));
        address.setStyle("-fx-text-fill: #0C2D48;");
        address.setText("Address: " + project.getCompanyAddress());
        address.setPadding(new Insets(5, 5, 5, 10));
        address.setId("address");


        HBox hbox = new HBox();
        hbox.setPrefSize(stackPane.getPrefWidth(), 132);
        hbox.setPadding(new Insets(0, 0, 0, 3));
        hbox.setId("hbox");



        vBox.getChildren().add(eventName);
        vBox.getChildren().add(customerName);
        vBox.getChildren().add(address);
        vBox.getChildren().add(date);
        vBox.getChildren().add(hbox);

        stackPane.getChildren().add(vBox);
        stackPane.setStyle("-fx-background-radius: 10px; -fx-background-color: #c6c7c4;");
        stackPane.setCursor(Cursor.HAND);
        stackPane.setOnMousePressed(e -> {

            System.out.println("Clicked on project: " + project.getName());

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/GUI/Views/MainView.fxml"));
            try {
                Parent root = loader.load();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            MainViewController mainViewController = loader.getController();
            BorderPane borderPane = mainViewController.borderPane;

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GUI/Views/DocumentsView.fxml"));
            System.out.println(fxmlLoader);


            try {
                borderPane.getChildren().remove(borderPane.getCenter()); //remove existing fxml from center.
                borderPane.setCenter(fxmlLoader.load());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        });

        return stackPane;
    }


}
