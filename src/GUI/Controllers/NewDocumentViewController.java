package GUI.Controllers;

import GUI.Models.DocumentModel;
import GUI.Models.ProjectModel;
import GUI.Models.UserModel;
import be.documents.DocumentBuilder;
import bll.FacadeManager;
import bll.InputManager;
import bll.validator.DocumentValidator;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NewDocumentViewController implements Initializable {
    @FXML
    private Label labelAddFile;
    @FXML
    private TextField textFieldDocumentName, textFieldFilePath, textFieldRefNum;
    @FXML
    private ChoiceBox choiceBoxDocumentType;
    @FXML
    private TextArea textAreaDescription;
    @FXML
    private MFXButton buttonChooseFile, buttonAddDocument, buttonCancel;
    ProjectModel projectModel = ProjectModel.getInstance();
    UserModel userModel = UserModel.getInstance();
    DocumentModel documentModel = new DocumentModel();
    DocumentBuilder documentBuilder = new DocumentBuilder();
    DocumentValidator documentValidator = new DocumentValidator();
    InputManager inputManager = new InputManager();


    public void clickAddDocument(ActionEvent actionEvent) {
        String docName = textFieldDocumentName.getText();
        String docDescription = textAreaDescription.getText();
        String docFilePath = textFieldFilePath.getText();
        int projectId = projectModel.getSelectedProject().getId();
        int userId = userModel.getLoggedInUser().getId();

        try{
            int refNum = Integer.parseInt(textFieldRefNum.getText());
        } catch (NumberFormatException e){
            System.out.println("ref num is not an int");
            return;
        }
        int documentType = switch ((String) choiceBoxDocumentType.getSelectionModel().getSelectedItem()) {
            case "Diagram" -> 1;
            case "Picture" -> 2;
            case "Text" -> 3;
            default -> 0;
        };
        String dateAdded = inputManager.getDateToday();
        documentBuilder.documentName(docName).description(docDescription).absolutePath(new File(docFilePath)).
                projectId(projectId).userId(userId).documentType(documentType).dateAdded(dateAdded);
        if((documentType == 1 || documentType == 2) && documentValidator.isDiagramOrPictureDocValid(docName, docDescription, docFilePath)){
                documentModel.createDocument(documentBuilder.build(documentType));
                projectModel.refreshProjectDocuments();
                Stage stage = (Stage) buttonAddDocument.getScene().getWindow();
                stage.close();
        }
        if(documentType == 3 && documentValidator.isTextDocValid(docName, docDescription)){
            documentModel.createDocument(documentBuilder.build(documentType));
            projectModel.refreshProjectDocuments();
            Stage stage = (Stage) buttonAddDocument.getScene().getWindow();
            stage.close();
        }
    }

    public void clickCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) buttonCancel.getScene().getWindow();
        stage.close();
    }

    public void clickChooseFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose image");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            //Looks for any instances of ":" and creates a string from the last instance to the end, not including ":"
            String filePath = selectedFile.toURI().toString();
            int indexLastColon = 0;
            for (int i = 0; i < filePath.length(); i++) {
                String charString = String.valueOf(filePath.charAt(i));
                if(charString.equals(":"))
                    indexLastColon = i;
            }
            filePath = filePath.substring(indexLastColon +1, filePath.length());
            textFieldFilePath.setText(filePath);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> items = FXCollections.observableArrayList("Diagram", "Picture", "Text");
        buttonChooseFile.setVisible(false);
        textFieldFilePath.setEditable(false);
        textFieldFilePath.setVisible(false);
        labelAddFile.setVisible(false);
        choiceBoxDocumentType.setItems(items);
        choiceBoxDocumentType.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.equals("Picture")){
                buttonChooseFile.setVisible(true);
                textFieldFilePath.setVisible(true);
                labelAddFile.setVisible(true);
            }
            if(newValue.equals("Diagram")){
                labelAddFile.setText("Create Diagram");
                labelAddFile.setVisible(true);
                buttonChooseFile.setText("New Diagram...");
                buttonChooseFile.setVisible(true);
                textFieldFilePath.setVisible(true);
                buttonChooseFile.setOnAction(e ->{
                    Parent root;
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Views/DiagramProgramView.fxml"));
                    try {
                        root = loader.load();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                    textFieldFilePath.setText("savedImages/diagram.png");
                });
            }
            if(newValue.equals("Text")){
                buttonChooseFile.setVisible(false);
                textFieldFilePath.setVisible(false);
                labelAddFile.setVisible(false);
            }
        });
    }
}
