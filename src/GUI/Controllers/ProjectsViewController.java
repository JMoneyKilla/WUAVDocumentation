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

        /*StackPane stackPane = new StackPane();

        stackPane.setPrefSize(400, 132);
        stackPane.setOnMouseEntered(e -> {
            stackPane.setScaleX(1.05);
            stackPane.setScaleY(1.05);
        });

        stackPane.setOnMouseExited(e -> {
            stackPane.setScaleX(1.0);
            stackPane.setScaleY(1.0);
        });


        VBox vBox = new VBox();
        vBox.setPrefSize(stackPane.getPrefWidth(), 132);
        vBox.setId("vbox");

        Label projectName = new Label();
        projectName.setPrefSize(stackPane.getPrefWidth(), 16);
        projectName.setFont(Font.font(16));
        projectName.setStyle("-fx-text-fill: #0C2D48;");
        projectName.setFont(Font.font("Segoe UI Semibold"));
        projectName.setText(project.getName());
        projectName.setTextAlignment(TextAlignment.CENTER);
        projectName.setPadding(new Insets(5, 5, 5, 150));
        projectName.setId("projectName");

        Label customerName = new Label();
        customerName.setPrefSize(stackPane.getPrefWidth(), 11);
        customerName.setFont(Font.font(13));
        customerName.setFont(Font.font("Segoe UI Semibold"));
        customerName.setStyle("-fx-text-fill: #0C2D48;");
        customerName.setText("Customer: " + project.getCustomerName());
        customerName.setPadding(new Insets(5, 5, 5, 10));
        customerName.setId("customerName");
        Label date = new Label();
        date.setPrefSize(stackPane.getPrefWidth(), 11);
        date.setFont(Font.font(13));
        date.setStyle("-fx-text-fill: #0C2D48;");
        date.setFont(Font.font("Segoe UI Semibold"));
        date.setText("Last visited: " + project.getDateLastVisited());
        date.setPadding(new Insets(5, 5, 5, 10));
        date.setTextAlignment(TextAlignment.CENTER);
        date.setId("date");

        Label address = new Label();
        address.setPrefSize(stackPane.getPrefWidth(), 11);
        address.setFont(Font.font("Segoe UI Semibold"));
        address.setFont(Font.font(13));
        address.setStyle("-fx-text-fill: #0C2D48;");
        address.setText("Address: " + project.getCompanyAddress());
        address.setPadding(new Insets(5, 5, 5, 10));
        address.setId("address");


        HBox hbox = new HBox();
        hbox.setPrefSize(stackPane.getPrefWidth(), 80);
        hbox.setPadding(new Insets(0, 0, 0, 3));
        hbox.setId("hbox");

        Button AssignButton = new Button();
        AssignButton.setStyle("-fx-font-family: arial;\n" +
                "    -fx-font-size: 10px;\n" +
                "    -fx-text-fill: #0C2D48;\n" +
                "    -fx-background-color: #A2999E;");
        AssignButton.setText("Assign");
        AssignButton.setId("AssignButton");
        AssignButton.setPrefSize(60, 20);
        AssignButton.setTranslateY(-10);

        Button editButton = new Button();
        editButton.setStyle("-fx-font-family: arial;\n" +
                "    -fx-font-size: 10px;\n" +
                "    -fx-text-fill: #0C2D48;\n" +
                "    -fx-background-color: #A2999E;");
        editButton.setText("Edit...");
        editButton.setId("editButton");
        editButton.setPrefSize(60, 20);
        editButton.setTranslateY(-10);
        editButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                projectModel.setSelectedProject(project);
                Node n = (Node) actionEvent.getSource();
                Window stage = n.getScene().getWindow();
                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getClassLoader().getResource("GUI/Views/CreateNewProjectView.fxml"));
                    Stage createNewUserView = new Stage();
                    createNewUserView.setScene(new Scene(root));
                    createNewUserView.setTitle("New Project");
                    createNewUserView.initModality(Modality.WINDOW_MODAL);
                    createNewUserView.centerOnScreen();
                    createNewUserView.initOwner(stage);
                    createNewUserView.show();


                } catch (IOException e) {
                    e.printStackTrace();
                }}
    });

        Button deleteButton = new Button();
        deleteButton.setStyle("-fx-font-family: arial;\n" +
                "    -fx-font-size: 10px;\n" +
                "    -fx-text-fill: #0C2D48;\n" +
                "    -fx-background-color: #A2999E;");
        deleteButton.setText("Delete");
        deleteButton.setId("deleteButton");
        deleteButton.setPrefSize(60, 20);
        deleteButton.setTranslateY(-10);
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to remove " + project.getName() + "?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                    projectModel.deleteProjectFromUserProject(project);
                    projectModel.deleteProject(project);
                    projectModel.refreshUserProjects();
                }
            }
        });

        vBox.getChildren().add(projectName);
        vBox.getChildren().add(customerName);
        vBox.getChildren().add(address);
        vBox.getChildren().add(date);
        vBox.getChildren().add(hbox);
        hbox.getChildren().add(new Label("                                                         "));
        if(userModel.getLoggedInUser().getType()==1){
        hbox.getChildren().add(AssignButton);
        hbox.getChildren().add(new Label(" "));
        hbox.getChildren().add(deleteButton);
        }
        if(userModel.getLoggedInUser().getType()!=3){
            hbox.getChildren().add(new Label(" "));
            hbox.getChildren().add(editButton);
        }


        stackPane.getChildren().add(vBox);
        stackPane.setStyle("-fx-background-radius: 10px; -fx-background-color: #c6c7c4;");
        stackPane.setCursor(Cursor.HAND);
        stackPane.setOnMousePressed(e -> {
            ProjectModel projectModel = ProjectModel.getInstance();
            projectModel.setSelectedProject(project);
            projectModel.setIsProjectSelected(true);
        });

        return stackPane;*/



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
            loadUserProjectData();
        }
        else{
            paneProject.getChildren().clear();
            loadData();
            projectModel.fetchAllProjects();
        }
    }
}
