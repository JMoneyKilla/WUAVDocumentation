package GUI.Controllers;

import GUI.Models.ProjectModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DeleteConfirmationController implements Initializable {

    @FXML
    private TextField txtFieldDelete;
    @FXML
    private Label labelWarning;

    ProjectModel projectModel = ProjectModel.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(projectModel.getSelectedProject());
        System.out.println(projectModel.getMultipleIds());
    }

    public void clickDelete(ActionEvent actionEvent) {
        if(!txtFieldDelete.getText().equals("DELETE")){
            labelWarning.setText("Please input DELETE in all caps");
        }
        else{
            if(projectModel.getSelectedProject()!=null){
                projectModel.deleteProjectFromUserProject(projectModel.getSelectedProject());
                projectModel.deleteProject(projectModel.getSelectedProject());
                projectModel.setSelectedProject(null);
                projectModel.setMultipleIds(null);
                projectModel.fetchAllOldProjects();
                Node n = (Node) actionEvent.getSource();
                Stage stage = (Stage) n.getScene().getWindow();
                stage.close();
            }
            else if(projectModel.getMultipleIds()!=null){
                projectModel.deleteMultipleProjectsInUserProject();
                projectModel.deleteMultipleProjects();
                projectModel.setSelectedProject(null);
                projectModel.setMultipleIds(null);
                projectModel.fetchAllOldProjects();
                Node n = (Node) actionEvent.getSource();
                Stage stage = (Stage) n.getScene().getWindow();
                stage.close();
            }
        }
    }

    public void clickCancel(ActionEvent actionEvent) {
        projectModel.setMultipleIds(null);
        projectModel.setSelectedProject(null);
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }
}
