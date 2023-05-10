package GUI.Controllers;

import GUI.Models.ProjectModel;
import be.Project;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ReminderViewController implements Initializable {

    ProjectModel projectModel = ProjectModel.getInstance();
    @FXML
    private Label lblInform, lblWarning;

    @FXML
    private TableView tableViewProjects;

    @FXML
    private TableColumn tableColumnProjectName, tableColumnDate;

    public void clickDelete(ActionEvent actionEvent) {
        if(tableViewProjects.getSelectionModel().getSelectedItem()!=null) {
            projectModel.deleteProject((Project) tableViewProjects.getSelectionModel().getSelectedItem());
        }
        else lblWarning.setText("Please select what project to delete");
    }

    public void clickCancel(ActionEvent actionEvent) {
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableColumnProjectName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumnDate.setCellValueFactory(new PropertyValueFactory<>("dateLastVisited"));
        tableViewProjects.setItems(projectModel.getOldProjects());
        if(projectModel.getOldProjects().size()>1){
            lblInform.setText("These projects are 48 months old. Do you want to delete them?");
        }
        else lblInform.setText("This project is 48 months old. Do you want to delete it?");
    }
}
