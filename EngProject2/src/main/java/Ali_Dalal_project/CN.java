package Ali_Dalal_project;

import java.io.IOException;
import java.sql.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CN  {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private VBox vp;
    @FXML
    private TextField idShip;
    @FXML
    private TextField nameShip;
    @FXML
    private TextField oShip;
    @FXML
    private TextField sShip;
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
    @FXML
    void switchToEditView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("editView.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void exit(ActionEvent event) {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.show();
        stage.close();
    }
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
}
