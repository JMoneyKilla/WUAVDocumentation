package GUI.Controllers;

import GUI.Models.ProjectModel;
import GUI.Models.UserModel;
import be.Project;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProjectTemplateController implements Initializable {

    @FXML
    private Label lblProjectName, lblCustomerName, lblDate, lblAddress, lblZipCode;
    @FXML
    private Button btnAssign, btnEdit, btnDelete;

    UserModel userModel = UserModel.getInstance();
    ProjectModel projectModel = ProjectModel.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(userModel.getLoggedInUser().getType()!=1){
            btnAssign.setVisible(false);
            btnDelete.setVisible(false);
            btnEdit.setVisible(false);
        }
    }

    public void setLabels(Project project){
        lblProjectName.setText("Project name: "+project.getName());
        lblCustomerName.setText("Customer name: "+project.getCustomerName());
        lblDate.setText("Date: "+project.getDateLastVisited());
        lblAddress.setText("Address: "+project.getCompanyAddress());
        lblZipCode.setText("Zip code: "+project.getZipCode());
        btnAssign.setOnAction(e->{

        });

        btnDelete.setOnAction(e->{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to remove " + project.getName() + "?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
            projectModel.deleteProjectFromUserProject(project);
            projectModel.deleteProject(project);
            projectModel.fetchAllProjects();}
            else alert.close();
        });

        btnEdit.setOnAction(e->{
                projectModel.setSelectedProject(project);
                Node n = (Node) e.getSource();
                Window stage = n.getScene().getWindow();
                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getClassLoader().getResource("GUI/Views/CreateNewProjectView.fxml"));
                    Stage editProject = new Stage();
                    editProject.setScene(new Scene(root));
                    editProject.setTitle("Edit Project");
                    editProject.initModality(Modality.WINDOW_MODAL);
                    editProject.centerOnScreen();
                    editProject.initOwner(stage);
                    editProject.show();


                } catch (IOException ex) {
                    ex.printStackTrace();
                }
        });
    }
}
