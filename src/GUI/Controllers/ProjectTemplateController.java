package GUI.Controllers;

import GUI.Models.ProjectModel;
import GUI.Models.UserModel;
import be.Project;

import be.enums.UserType;

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
        if(userModel.getLoggedInUser().getType() == UserType.SALES_PERSON){
            btnAssign.setVisible(false);
            btnDelete.setVisible(false);
            btnEdit.setVisible(false);
        }
        if(userModel.getLoggedInUser().getType() == UserType.TECHNICIAN){
            btnAssign.setVisible(false);
            btnDelete.setVisible(false);
        }
    }

    public void setLabels(Project project){
        lblProjectName.setText("Project name: "+project.getName());
        lblCustomerName.setText("Customer name: "+project.getCustomerName());
        lblDate.setText("Date: "+project.getDateLastVisited());
        lblAddress.setText("Address: "+project.getCompanyAddress());
        lblZipCode.setText("Zip code: "+project.getZipCode());
        btnAssign.setOnAction(e->{
            projectModel.setSelectedProject(project);
            Node n = (Node) e.getSource();
            Window stage = n.getScene().getWindow();
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getClassLoader().getResource("GUI/Views/AssignView.fxml"));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            Stage assignView = new Stage();
                assignView.setScene(new Scene(root));
                assignView.initModality(Modality.WINDOW_MODAL);
                assignView.centerOnScreen();
                assignView.initOwner(stage);
                assignView.show();
        });

        btnDelete.setOnAction(e->{
            projectModel.setSelectedProject(project);
            Node n = (Node) e.getSource();
            Window stage = n.getScene().getWindow();
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getClassLoader().getResource("GUI/Views/DeleteConfirmation.fxml"));
                Stage deleteConfirmation = new Stage();
                deleteConfirmation.setScene(new Scene(root));
                deleteConfirmation.setTitle("Delete Project");
                deleteConfirmation.initModality(Modality.WINDOW_MODAL);
                deleteConfirmation.centerOnScreen();
                deleteConfirmation.initOwner(stage);
                deleteConfirmation.show();


            } catch (IOException ex) {
                ex.printStackTrace();
            }
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
