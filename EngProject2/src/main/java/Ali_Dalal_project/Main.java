package Ali_Dalal_project;
/**
 * Blue Wave Harbor
 *
 * @author  Ali Bassam Mohsin | Dalal Hussain Lazim
 * @Stage third Stage
 * @version 1.0
 * @Done_At 2022-01-8
 * @Project Software Engineering Project
 */

//main class file.
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    //start method to start the main.fxml on start of the program.
    @Override
    public void start(Stage Stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        Scene s1 = new Scene(root);
        Stage.setTitle("Blue Wave Harbor");
        Stage.setWidth(1010);
        Stage.setHeight(720);
        Stage.setResizable(false);
        Stage.setScene(s1);
        Stage.show();
    }
    //main method to lunch the program.
    public static void main(String[] args) {
        launch(args);
    }
}