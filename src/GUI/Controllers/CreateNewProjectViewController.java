package GUI.Controllers;

import GUI.Models.ProjectModel;
import GUI.Models.UserModel;
import be.Project;
import be.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateNewProjectViewController implements Initializable {

    @FXML
    private TextField txtFieldProjectName, txtFieldName, txtFieldAddress, txtFieldZipcode;
    @FXML
    private Label lblWarning;
    ProjectModel projectModel = ProjectModel.getInstance();
    UserModel userModel = UserModel.getInstance();

    private boolean isEditTrue = false;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
     if(projectModel.getSelectedProject()!=null) isEditTrue = true;
     if(isEditTrue){
         txtFieldProjectName.setText(projectModel.getSelectedProject().getName());
         txtFieldAddress.setText(projectModel.getSelectedProject().getCompanyAddress());
         txtFieldName.setText(projectModel.getSelectedProject().getCustomerName());
         System.out.println(isEditTrue);
     }
    }

    public void clickSave(ActionEvent actionEvent) {
        if(isEditTrue){
            projectModel.updateProject(new Project(
                    projectModel.getSelectedProject().getId(),
                    txtFieldProjectName.getText(),
                    projectModel.getSelectedProject().getDateLastVisited(),
                    txtFieldName.getText(),
                    txtFieldAddress.getText(),
                    Integer.parseInt(txtFieldZipcode.getText()),
                    projectModel.getSelectedProject().getCompanyType()));
            //projectModel.refreshUserProjects();
            projectModel.setSelectedProject(null);
            projectModel.fetchAllProjects();

            Node n = (Node) actionEvent.getSource();
            Stage stage = (Stage) n.getScene().getWindow();
            stage.close();
        }
        if(!isEditTrue && projectModel.isProjectValid(txtFieldProjectName.getText(), txtFieldName.getText(), txtFieldAddress.getText(),txtFieldZipcode.getText())){
            projectModel.createProject(new Project(txtFieldProjectName.getText(), projectModel.getDateToday(), txtFieldName.getText(), txtFieldAddress.getText(),Integer.parseInt(txtFieldZipcode.getText()), 1));
            //projectModel.refreshUserProjects();
            userModel.addUserToProject(userModel.getLoggedInUser());
            projectModel.fetchAllProjects();
            Node n = (Node) actionEvent.getSource();
            Stage stage = (Stage) n.getScene().getWindow();
            stage.close();
        }
        else lblWarning.setText("Please input text in all fields");
    }

    public void clickCancel(ActionEvent actionEvent) {
        projectModel.setSelectedProject(null);
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }
}
