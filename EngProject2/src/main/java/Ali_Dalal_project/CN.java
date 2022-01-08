package Ali_Dalal_project;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//this is CN class is the main page class controller.

public class CN  {
    //some essential declarations.
    static Stage stage;
    private Scene scene;
    private Parent root;
    //these methods for switching to other fxml files each connect to a button in the main fxml.
    @FXML
    void switchToAdd(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("add.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void switchToEdit(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("edit.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void switchToSView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("editShip.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
