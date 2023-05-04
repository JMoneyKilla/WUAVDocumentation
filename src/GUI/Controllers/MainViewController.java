package GUI.Controllers;

import GUI.Models.ProjectModel;
import GUI.Models.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.EventListener;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {
    ProjectsViewController projectsViewController = null;
    @FXML
    private BorderPane borderPane;
    @FXML
    private Label lableUserName;
    @FXML
    private CheckBox toggleAddress, toggleProjectName, toggleCompanyName, toggleCustomerName;
    UserModel userModel = UserModel.getInstance();
    ProjectModel projectModel = ProjectModel.getInstance();



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        projectModel.isProjectSelectedProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue==true){
                    try {
                        documentsSwitch();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
            }
            if(newValue==false){
                try {
                    projectsSwitch();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        try {
            projectsSwitch();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //lableUserName.setText(""+userModel.getLoggedInUser().getName());
    }
    public void projectsSwitch() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GUI/Views/ProjectsView.fxml"));
        projectsViewController = fxmlLoader.getController();
        System.out.println("ProjectsView loaded");
        borderPane.getChildren().remove(borderPane.getCenter()); //remove existing fxml from center.
        try {
            borderPane.setCenter(fxmlLoader.load());
            System.out.println(borderPane.getCenter());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void documentsSwitch() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GUI/Views/DocumentsView.fxml"));
        System.out.println("DocumentsView loaded");
        borderPane.getChildren().remove(borderPane.getCenter()); //remove existing fxml from center.
        try {
            borderPane.setCenter(fxmlLoader.load());
            System.out.println(borderPane.getCenter());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void devicesSwitch() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GUI/Views/DevicesView.fxml"));
        System.out.println("DevicesView loaded");
        borderPane.getChildren().remove(borderPane.getCenter()); //remove existing fxml from center.
        try {
            borderPane.setCenter(fxmlLoader.load());
            System.out.println(borderPane.getCenter());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void clickCreateNewProject(ActionEvent actionEvent) {
                Node n = (Node) actionEvent.getSource();
                Window stage = n.getScene().getWindow();
                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getClassLoader().getResource("GUI/Views/CreateNewProjectView.fxml"));
                    Stage createNewUserView = new Stage();
                    createNewUserView.setScene(new Scene(root));
                    createNewUserView.setTitle("New Project");
                    createNewUserView.initModality(Modality.WINDOW_MODAL);
                    createNewUserView.centerOnScreen();
                    createNewUserView.initOwner(stage);
                    createNewUserView.show();


                } catch (IOException e) {
                    e.printStackTrace();
                }}

    public BorderPane getBorderPane(){
        return borderPane;
    }
}

