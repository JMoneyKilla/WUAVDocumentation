package GUI.Controllers;

import GUI.Models.ProjectModel;
import GUI.Models.UserModel;
import be.Project;
import be.User;
import be.enums.UserType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateNewProjectViewController implements Initializable {

    @FXML
    private TextField txtFieldProjectName, txtFieldName, txtFieldAddress, txtFieldZipcode, txtFieldPhoneNumber, txtFieldEmail;
    @FXML
    private Label lblWarning;

    @FXML
    private ComboBox comboBox;
    ProjectModel projectModel = ProjectModel.getInstance();
    UserModel userModel = UserModel.getInstance();

    private boolean isEditTrue = false;
    private boolean isValid;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> choiceBoxOptions = FXCollections.observableArrayList("Corporate", "Private");
        comboBox.setItems(choiceBoxOptions);


     if(projectModel.getSelectedProject()!=null) isEditTrue = true;
     if(isEditTrue){
         if(projectModel.getSelectedProject().getCompanyType()==1)
             comboBox.getSelectionModel().select(0);
         else comboBox.getSelectionModel().select(1);
         txtFieldProjectName.setText(projectModel.getSelectedProject().getName());
         txtFieldAddress.setText(projectModel.getSelectedProject().getCompanyAddress());
         txtFieldName.setText(projectModel.getSelectedProject().getCustomerName());
         txtFieldEmail.setText(projectModel.getSelectedProject().getCustomerEmail());
         txtFieldPhoneNumber.setText(Integer.toString(projectModel.getSelectedProject().getPhoneNumber()));
         txtFieldZipcode.setText(Integer.toString(projectModel.getSelectedProject().getZipCode()));
     }
    }

    public void clickSave(ActionEvent actionEvent) {
        String invalidFields = "Invalid fields: ";

        //Checks for valid inputs, and notifies user of invalid fields

        if (!projectModel.isEmailValid(txtFieldEmail.getText())) {
            invalidFields += "Email, ";
            isValid = false;
        }
        if (!projectModel.isZipCodeValid(txtFieldZipcode.getText())) {
            invalidFields += "Zipcode, ";
            isValid = false;
        }
        if (!projectModel.isNumberValid(txtFieldPhoneNumber.getText())) {
            invalidFields += "Phone number, ";
            isValid = false;
        }
        if(comboBox.getSelectionModel().getSelectedItem()==null){
            invalidFields += "Unselected item in combo box, ";
            isValid = false;
        }
        if(!isValid){
            if(!projectModel.isProjectValid(txtFieldProjectName.getText(), txtFieldName.getText(), txtFieldAddress.getText(), txtFieldZipcode.getText(), txtFieldPhoneNumber.getText(), txtFieldEmail.getText()))
                lblWarning.setText("Please input text in all fields");
            else lblWarning.setText(invalidFields.substring(0, invalidFields.length()-2));
        }
        if(invalidFields.length()<17)
            isValid = true;
        if(isValid) {
            int companyType = switch ((String) comboBox.getSelectionModel().getSelectedItem()) {
                case "Corporate" -> 1;
                case "Private" -> 2;
                default -> 0;
            };

            /*If editing existing project, project is updated.
              If Creating new project, project is created.
             */

            lblWarning.setText("");
            if (isEditTrue) {
                projectModel.updateProject(new Project(
                        projectModel.getSelectedProject().getId(),
                        txtFieldProjectName.getText().trim(),
                        projectModel.getSelectedProject().getDateLastVisited(),
                        txtFieldName.getText().trim(),
                        txtFieldAddress.getText().trim(),
                        Integer.parseInt(txtFieldZipcode.getText().trim()),
                        companyType, Integer.parseInt(txtFieldPhoneNumber.getText().trim()),
                        txtFieldEmail.getText().trim()));
                projectModel.setSelectedProject(null);
                if(userModel.getLoggedInUser().getType() == UserType.PROJECT_MANAGER)
                    projectModel.fetchAllProjects();
                if(userModel.getLoggedInUser().getType() == UserType.TECHNICIAN)
                    projectModel.fetchAllUserProjects(userModel.getLoggedInUser().getId());
                Node n = (Node) actionEvent.getSource();
                Stage stage = (Stage) n.getScene().getWindow();
                stage.close();
            }
            if (!isEditTrue) {
                projectModel.createProject(new Project(txtFieldProjectName.getText().trim(), projectModel.getDateToday(), txtFieldName.getText().trim(), txtFieldAddress.getText().trim(), Integer.parseInt(txtFieldZipcode.getText().trim()), companyType,
                        Integer.parseInt(txtFieldPhoneNumber.getText().trim()), txtFieldEmail.getText().trim()));
                userModel.addUserToProject(userModel.getLoggedInUser());
                projectModel.fetchAllProjects();
                Node n = (Node) actionEvent.getSource();
                Stage stage = (Stage) n.getScene().getWindow();
                stage.close();
            }
        }
    }

    public void clickCancel(ActionEvent actionEvent) {
        projectModel.setSelectedProject(null);
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();

    }
}
