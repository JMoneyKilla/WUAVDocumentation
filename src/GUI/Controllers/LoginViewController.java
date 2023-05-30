package GUI.Controllers;

import GUI.Models.UserModel;
import be.User;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginViewController implements Initializable {

    @FXML
    private Label lblWarning;

    @FXML
    private MFXTextField txtFieldUsername;

    @FXML
    private MFXPasswordField txtFieldPassword;
    @FXML
    private Button enterButton;

    UserModel userModel = UserModel.getInstance();

    //Logs in user and switches view
    public void clickEnter(ActionEvent actionEvent) {
        String email = txtFieldUsername.getText().trim();
        String password = txtFieldPassword.getText();
        User user;
        if(userModel.validateLogin(email, password)) {
            int userId = userModel.getUserIdFromEmail(email);
            user = userModel.getUserFromLoginById(userId);
            userModel.setLoggedInUser(user);
            Parent root;
            try { FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Views/MainView.fxml"));
                root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Node n = (Node) actionEvent.getSource();
            Stage stage2 = (Stage) n.getScene().getWindow();
            stage2.close();
        }
        else lblWarning.setText("Incorrect login");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtFieldUsername.setText("tinatran@live.dk");
        txtFieldPassword.setText("hej123123");

  }
}
