package Ali_Dalal_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage Stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        //Image icon = new Image("Ali_Dalal_project/icon.png");
        Scene s1 = new Scene(root);
        Stage.setTitle("Blue Wave Harbor");
        Stage.setWidth(1010);
        Stage.setHeight(720);
        Stage.setResizable(false);
        Stage.setScene(s1);
        //Stage.getIcons().add(icon);
        Stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}