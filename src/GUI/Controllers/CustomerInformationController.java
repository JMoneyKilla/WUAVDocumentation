package GUI.Controllers;

import GUI.Models.ProjectModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerInformationController implements Initializable {

    @FXML
    private Label lblName, lblAddress, lblZipCode, lblEmail, lblPhoneNumber;

    ProjectModel projectModel = ProjectModel.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblName.setText(projectModel.getSelectedProject().getCustomerName());
        lblAddress.setText(projectModel.getSelectedProject().getCompanyAddress());
        lblZipCode.setText(Integer.toString(projectModel.getSelectedProject().getZipCode()));
        lblEmail.setText(projectModel.getSelectedProject().getCustomerEmail());
        lblPhoneNumber.setText(Integer.toString(projectModel.getSelectedProject().getPhoneNumber()));
    }
}
