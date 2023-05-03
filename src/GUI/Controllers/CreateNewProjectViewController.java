package GUI.Controllers;

import GUI.Models.ProjectModel;
import be.Project;
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
    private TextField txtFieldProjectName, txtFieldName, txtFieldAddress;
    @FXML
    private Label lblWarning;
    ProjectModel projectModel = ProjectModel.getInstance();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    public void clickSave(ActionEvent actionEvent) {
        if(projectModel.isProjectValid(txtFieldProjectName.getText(), txtFieldName.getText(), txtFieldAddress.getText())){
            projectModel.createProject(new Project(txtFieldProjectName.getText(), projectModel.getDateToday(), txtFieldName.getText(), txtFieldAddress.getText(), 1));
            Node n = (Node) actionEvent.getSource();
            Stage stage = (Stage) n.getScene().getWindow();
            stage.close();
        }
        else lblWarning.setText("Please input text in all fields");
    }

    public void clickCancel(ActionEvent actionEvent) {
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }
}
