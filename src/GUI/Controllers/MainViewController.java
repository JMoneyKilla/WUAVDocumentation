package GUI.Controllers;

import GUI.Models.ProjectModel;
import GUI.Models.UserModel;
import be.Project;
import bll.InputManager;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.beans.Observable;
import javafx.beans.property.ListProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.EventListener;
import java.util.List;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {
    @FXML
    private MFXTextField textSearch;
    @FXML
    private VBox sideBar;
    ProjectsViewController projectsViewController = null;
    @FXML
    private BorderPane borderPane;
    @FXML
    private Label lableUserName;
    @FXML
    private CheckBox toggleAddress, toggleProjectName, toggleCompanyName, toggleCustomerName;
    UserModel userModel = UserModel.getInstance();

    @FXML
    private Button btnCreateNewProject;

    ProjectModel projectModel = ProjectModel.getInstance();
    InputManager inputManager = new InputManager();

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        textSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                List<Project> filteredList = inputManager.searchProjectName(textSearch.getText());
                //loadData(filteredList);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        });

        if(!projectModel.getOldProjects().isEmpty()) {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Views/ReminderView.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // Create the scene and set it on a new stage
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Delete old projects?");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setResizable(false);
            stage.setAlwaysOnTop(true);
            stage.setScene(scene);
            stage.show();
        }


        //listens for the user to click on a project so it can switch to the documents
        borderPane.getChildren().remove(sideBar);
        projectModel.isProjectSelectedProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue==true){
                    try {
                        documentsSwitch();
                        borderPane.setLeft(sideBar);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
            }
        });

        try {
            projectsSwitch();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        lableUserName.setText(""+userModel.getLoggedInUser().getName());

        if(userModel.getLoggedInUser().getType()!= 1){
            btnCreateNewProject.setVisible(false);
        }
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

    public void clickBack(ActionEvent actionEvent) {
        borderPane.getChildren().remove(sideBar);
        projectModel.setIsProjectSelected(false);
        projectModel.setSelectedProject(null);
        try {
            projectsSwitch();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void clickDocuments(ActionEvent actionEvent) {
        if(borderPane.getLeft()!=sideBar){
            borderPane.setLeft(sideBar);
        }
        try {
            documentsSwitch();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void clickDevices(ActionEvent actionEvent) {
        if(borderPane.getLeft()!=sideBar){
            borderPane.setLeft(sideBar);
        }
        try {
            devicesSwitch();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void clickGetReport(ActionEvent actionEvent) {
    }
}

