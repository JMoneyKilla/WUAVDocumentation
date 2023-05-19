package GUI.Controllers;

import GUI.Models.ProjectModel;
import GUI.Models.UserModel;
import be.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class AssignViewController implements Initializable {


    @FXML
    private TableView<User> tableView;

    @FXML
    private TableColumn<User, String> tableColumnTech;
    @FXML
    private Label lblAssign;

    UserModel userModel = UserModel.getInstance();
    ProjectModel projectModel = ProjectModel.getInstance();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableColumnTech.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableView.setItems(userModel.getMissingTechs());
        userModel.fetchAllMissingTechs(projectModel.getSelectedProject().getId());
    }

    public void clickSave(ActionEvent actionEvent) {
    }

    public void clickCancel(ActionEvent actionEvent) {
    }
}
