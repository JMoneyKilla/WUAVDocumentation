package GUI.Controllers;

import GUI.Models.ProjectModel;
import GUI.Models.UserModel;
import be.Project;
import be.enums.UserType;
import io.github.palexdev.materialfx.controls.MFXToggleButton;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProjectsViewController implements Initializable {

    @FXML
    private MFXToggleButton toggleButton;
    @FXML
    private ScrollPane paneScroll;
    @FXML
    private AnchorPane paneProject, paneMainProject;

    @FXML
    private VBox vBoxProjects;
    ProjectModel projectModel = ProjectModel.getInstance();
    UserModel userModel = UserModel.getInstance();
    private BooleanProperty toggleView = new SimpleBooleanProperty();

    private boolean isListViewTrue = false;

    private ListChangeListener<Project> currentListener;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(projectModel.getProjects());


        if (userModel.getLoggedInUser().getType() == UserType.TECHNICIAN) {
            try {
                loadUserProjectData();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            projectModel.fetchAllUserProjects(userModel.getLoggedInUser().getId());
        } else {
            try {
                loadData();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            projectModel.fetchAllProjects();
        }
        toggleView.addListener((obs, oldVal, newVal) -> {
            if (newVal == true) {
                isListViewTrue = true;
                changeViewList();
            } else {
                try {
                    isListViewTrue = false;
                    changeViewGrid();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    /**
     * Loads data (projects) onto stackpanes. CurrentListener is added to listen when getProjects() changes. (When using filter)
     * Checks first if currentListener is not null, then removes it.
     * Else everytime the method would be called multiple listeners will be added and the method will throw errors.
     * @throws IOException
     */

    public void loadData() throws IOException {
            if (currentListener != null)
                ProjectModel.getInstance().getProjects().removeListener(currentListener);

            currentListener = c -> {
                if(!isListViewTrue) {
                    paneProject.getChildren().clear();
                    int row = 0;
                    int col = 0;
                    for (Project project : projectModel.getProjects()) {
                        StackPane stackPane;
                        try {
                            stackPane = generateStackPane(project);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        paneProject.getChildren().add(stackPane);
                        paneProject.setStyle("-fx-background-color: #fafafa; -fx-border-color: #000000");
                        AnchorPane.setTopAnchor(stackPane, 10 + row * 145.0);
                        AnchorPane.setLeftAnchor(stackPane, 20 + col * 420.0);
                        col++;
                        if (col == 2) {
                            col = 0;
                            row++;
                            paneProject.setMinHeight(row * 220);
                        }
                    }
                }
            };
            ProjectModel.getInstance().getProjects().addListener(currentListener);
        }



    public void loadUserProjectData() throws IOException {
        if (currentListener != null)
            ProjectModel.getInstance().getUserProjects(userModel.getLoggedInUser().getId()).removeListener(currentListener);

        currentListener = c -> {
            if(!isListViewTrue) {
            paneProject.getChildren().clear();
                int row = 0;
                int col = 0;
        for (Project project : projectModel.getUserProjects(userModel.getLoggedInUser().getId())) {
            StackPane stackPane;
            try {
                stackPane = generateStackPane(project);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            paneProject.getChildren().add(stackPane);
            paneProject.setStyle("-fx-background-color: #fafafa; -fx-border-color: #000000");
            AnchorPane.setTopAnchor(stackPane, 10 + row * 145.0);
            AnchorPane.setLeftAnchor(stackPane, 20 + col * 420.0);
            col++;
            if (col == 2) {
                col = 0;
                row++;
            }
          }
        }
       };
        ProjectModel.getInstance().getUserProjects(userModel.getLoggedInUser().getId()).addListener(currentListener);
    }


    /**
     * Generates a stackpane which will be used as visual tile for an event that the user can interact with
     *
     * @param project
     * @return
     */
    public StackPane generateStackPane(Project project) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Views/ProjectTemplateView.fxml"));
        StackPane sp = loader.load();
        ProjectTemplateController controller = loader.getController();
        controller.setLabels(project);

        sp.setOnMousePressed(e -> {
            ProjectModel projectModel = ProjectModel.getInstance();
            projectModel.setSelectedProject(project);
            projectModel.setIsProjectSelected(true);
        });


        return sp;
    }


    public void toggleView (ActionEvent actionEvent){
        toggleView.set(!toggleView.get());
        System.out.println(isListViewTrue);
    }

    private void changeViewList () {
        ObservableList observableList;
        if(userModel.getLoggedInUser().getType() == UserType.TECHNICIAN)
            observableList = projectModel.getUserProjects(userModel.getLoggedInUser().getId());

        else observableList = projectModel.getProjects();

        TableView tableView = new TableView(observableList);
        TableColumn<Project, String> nameColumn = new TableColumn<>("Project Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Project, String> customerColumn = new TableColumn<>("Customer");
        customerColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        TableColumn<Project, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("companyAddress"));
        TableColumn<Project, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateLastVisited"));
        TableColumn<Project, String> zipColumn = new TableColumn<>("Zip code");
        zipColumn.setCellValueFactory(new PropertyValueFactory<>("zipCode"));

        tableView.getColumns().addAll(nameColumn, customerColumn, addressColumn, dateColumn, zipColumn);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setPrefSize(paneProject.getPrefWidth(), paneProject.getPrefHeight());
        paneProject.getChildren().add(tableView);
        tableView.getStylesheets().add(
                getClass().getResource("/GUI/Views/MainStyleSheet.css").toExternalForm());
        tableView.setOnMouseClicked(event ->{
            if(event.getClickCount() == 2){
                Project selectedProject = (Project) tableView.getSelectionModel().getSelectedItem();
            if(selectedProject!=null){
                projectModel.setSelectedProject(selectedProject);
                projectModel.setIsProjectSelected(true);}
                }
            });
    }

        private void changeViewGrid() throws IOException {
        if(userModel.getLoggedInUser().getType() == UserType.TECHNICIAN){
            paneProject.getChildren().clear();
            loadUserProjectData();
            projectModel.fetchAllUserProjects(userModel.getLoggedInUser().getId());
        }
        else{
            paneProject.getChildren().clear();
            loadData();
            projectModel.fetchAllProjects();
        }
    }
}
