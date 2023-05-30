package GUI.Controllers;

import GUI.Models.ProjectModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DeleteConfirmationController{

    @FXML
    private TextField txtFieldDelete;
    @FXML
    private Label labelWarning;

    ProjectModel projectModel = ProjectModel.getInstance();


    //Deletes either one selected project or all projects if user selected all.
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
                projectModel.fetchAllProjects();
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
                projectModel.fetchAllProjects();
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
