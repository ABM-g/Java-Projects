package Ali_Dalal_project;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
//addShipCon class is the controller class for addShip.fxml and other fxml files.
public class addShipCon implements Initializable {
    //some essential declarations.
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField idShip;
    @FXML
    private TextField nameShip;
    @FXML
    private TextField oShip;
    @FXML
    private TextField sShip;
    @FXML
    private Label wOnlyNum;
    @FXML
    private Button addBtn;
    //these methods for switching to other fxml files each connect to a button in the addShip fxml.
    @FXML
    void switchToAdd(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("add.fxml"));
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
    @FXML
    void switchToEditView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("editView.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    //this method to exit the programme.
    @FXML
    void exit(ActionEvent event) {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.show();
        stage.close();
    }
    //the method to add ship record.
    @FXML
    void addShip(ActionEvent event) throws IOException {
        boolean f = false;
        MysqlConnect sql = new MysqlConnect();
        sql.connect();
        try {
            Statement sta = sql.connect().createStatement();
            ResultSet re = sta.executeQuery("SELECT * FROM harbor.ships;");
            while (re.next()){
                if(re.getString("sId").equals(idShip.getText())){
                    f=true;
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            sql.disconnect();
        }
        if(f){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert!");
            alert.setHeaderText("This ship Id already exist in the database!");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    try {
                        root = FXMLLoader.load(getClass().getResource("add.fxml"));
                        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        else {
            ShipD s1 = new ShipD(idShip.getText(),nameShip.getText(),oShip.getText(),Double.valueOf(sShip.getText()));
            s1.toSQL();
            root = FXMLLoader.load(getClass().getResource("add.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
    @FXML
    void addShip2(ActionEvent event) throws IOException {
        boolean f = false;
        MysqlConnect sql = new MysqlConnect();
        sql.connect();
        try {
            Statement sta = sql.connect().createStatement();
            ResultSet re = sta.executeQuery("SELECT * FROM harbor.ships;");
            while (re.next()){
                if(re.getString("sId").equals(idShip.getText())){
                    f=true;
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            sql.disconnect();
        }
        if(f){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert!");
            alert.setHeaderText("This ship Id already exist in the database!");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    try {
                        root = FXMLLoader.load(getClass().getResource("editShip.fxml"));
                        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        else {
            ShipD s1 = new ShipD(idShip.getText(),nameShip.getText(),oShip.getText(),Double.valueOf(sShip.getText()));
            s1.toSQL();
            root = FXMLLoader.load(getClass().getResource("editShip.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
    @FXML
    void addShip3(ActionEvent event) throws IOException {
        boolean f = false;
        MysqlConnect sql = new MysqlConnect();
        sql.connect();
        try {
            Statement sta = sql.connect().createStatement();
            ResultSet re = sta.executeQuery("SELECT * FROM harbor.ships;");
            while (re.next()){
                if(re.getString("sId").equals(idShip.getText())){
                    f=true;
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            sql.disconnect();
        }
        if(f){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert!");
            alert.setHeaderText("This ship Id already exist in the database!");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    try {
                        root = FXMLLoader.load(getClass().getResource("editView.fxml"));
                        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        else {
            ShipD s1 = new ShipD(idShip.getText(),nameShip.getText(),oShip.getText(),Double.valueOf(sShip.getText()));
            s1.toSQL();
            root = FXMLLoader.load(getClass().getResource("editView.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
    //method to chick if the string is numbers.
    public boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    //initialize method start when the fxml get loaded.
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sShip.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String s2) {
                if(isNumeric(s2)){
                    wOnlyNum.setVisible(false);
                    addBtn.setDisable(false);
                }else {
                    wOnlyNum.setVisible(true);
                    addBtn.setDisable(true);
                }
                if(s2==""){
                    wOnlyNum.setVisible(false);
                    addBtn.setDisable(true);
                }
            }
        });
    }
}
