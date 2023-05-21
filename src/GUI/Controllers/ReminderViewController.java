package GUI.Controllers;

import GUI.Models.ProjectModel;
import be.Project;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ReminderViewController implements Initializable {

    ProjectModel projectModel = ProjectModel.getInstance();
    @FXML
    private Label lblInform, lblWarning;

    @FXML
    private TableView tableViewProjects;

    @FXML
    private TableColumn tableColumnProjectName, tableColumnDate;

    private boolean isAllSelected = false;

    public void clickDelete(ActionEvent actionEvent) {
        if (isAllSelected) {
            List<Project> projects = tableViewProjects.getSelectionModel().getSelectedItems();
            List<Integer> ids = new ArrayList<>();

            for (Project p : projects) {
                ids.add(p.getId());
            }
            projectModel.setMultipleIds(ids);
            Node n = (Node) actionEvent.getSource();
            Window stage = n.getScene().getWindow();
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getClassLoader().getResource("GUI/Views/DeleteConfirmation.fxml"));
                Stage deleteConfirmation = new Stage();
                deleteConfirmation.setScene(new Scene(root));
                deleteConfirmation.setTitle("Delete Projects");
                deleteConfirmation.initModality(Modality.WINDOW_MODAL);
                deleteConfirmation.centerOnScreen();
                deleteConfirmation.initOwner(stage);
                deleteConfirmation.show();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Project selectedProject = (Project) tableViewProjects.getSelectionModel().getSelectedItem();
        if(!isAllSelected && tableViewProjects.getSelectionModel().getSelectedItem()!= null) {
            projectModel.setSelectedProject(selectedProject);
            Node n = (Node) actionEvent.getSource();
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


            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(tableViewProjects.getSelectionModel().getSelectedItem()==null)
            lblWarning.setText("Please select what project to delete");
    }


    public void clickCancel(ActionEvent actionEvent) {
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
        projectModel.fetchAllProjects();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableViewProjects.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableViewProjects.getSelectionModel().getSelectedItems().addListener(new ListChangeListener() {
            @Override
            public void onChanged(ListChangeListener.Change change) {
                if (tableViewProjects.getSelectionModel().getSelectedItems().size() > 1) {
                    isAllSelected = true;
                } else {
                    isAllSelected = false;
                }
            }
        });

        tableColumnProjectName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumnDate.setCellValueFactory(new PropertyValueFactory<>("dateLastVisited"));
        tableViewProjects.setItems(projectModel.getOldProjects());
        if (projectModel.getOldProjects().size() > 1) {
            lblInform.setText("These projects are 48 months old. Do you want to delete them?");
        } else lblInform.setText("This project is 48 months old. Do you want to delete it?");
    }

    public void clickSelectAll(ActionEvent actionEvent) {
        if (!isAllSelected) {
            isAllSelected = true;
            tableViewProjects.getSelectionModel().selectAll();
        } else {
            isAllSelected = false;
            tableViewProjects.getSelectionModel().clearSelection();
        }
    }
}

