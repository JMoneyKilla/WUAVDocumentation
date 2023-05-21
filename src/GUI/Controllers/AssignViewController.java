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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AssignViewController implements Initializable {


    @FXML
    private TableView<User> tableView, tableViewAssigned;
    @FXML
    private TableColumn<User, String> tableColumnTech, tableColumnAssignedTech;
    @FXML
    private Label lblInform;

    UserModel userModel = UserModel.getInstance();
    ProjectModel projectModel = ProjectModel.getInstance();



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableColumnTech.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableView.setItems(userModel.getMissingTechs());
        userModel.fetchAllMissingTechs(projectModel.getSelectedProject().getId());
        tableColumnAssignedTech.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableViewAssigned.setItems(userModel.getAssignedTechs());
        userModel.fetchAssignedTechs(projectModel.getSelectedProject().getId());
    }

    public void clickAdd(ActionEvent actionEvent) {
        User selectedUser = tableView.getSelectionModel().getSelectedItem();
        if(selectedUser!=null){
        userModel.addUserToSpecificProject(selectedUser, projectModel.getSelectedProject());
        userModel.fetchAllMissingTechs(projectModel.getSelectedProject().getId());
        userModel.fetchAssignedTechs(projectModel.getSelectedProject().getId());
        lblInform.setText(selectedUser.getName()+" has successfully been added to "+projectModel.getSelectedProject().getName());
        }
    }

    public void clickRemove(ActionEvent actionEvent) {
        User selectedUser = tableViewAssigned.getSelectionModel().getSelectedItem();
        if(selectedUser!=null){
            userModel.deleteUserFromProject(selectedUser, projectModel.getSelectedProject());
            userModel.fetchAllMissingTechs(projectModel.getSelectedProject().getId());
            userModel.fetchAssignedTechs(projectModel.getSelectedProject().getId());
            lblInform.setText(selectedUser.getName()+" has successfully been deleted from "+projectModel.getSelectedProject().getName());
        }
    }

    public void clickClose(ActionEvent actionEvent) {
        projectModel.setSelectedProject(null);
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }

    public void clickAnchorPane(MouseEvent mouseEvent) {
        tableView.getSelectionModel().clearSelection();
    }
}
