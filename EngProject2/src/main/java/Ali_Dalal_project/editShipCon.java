package Ali_Dalal_project;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

public class editShipCon implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TableColumn<ShipD, Double> tableS;
    @FXML
    private TableView<ShipD> tableV;
    @FXML
    private TextField searchtext;
    @FXML
    private Button delBtn;
    @FXML
    private Button vFull;
    @FXML
    private TableColumn<ShipD, String> tableN;
    @FXML
    private TableColumn<ShipD, String> tableO;
    @FXML
    private TableColumn<ShipD, String> tableSid;
    ObservableList<ShipD> listM;
    int index = -1;
    private static String selsid = "0";
    @FXML
    void switchToAdd(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("addShipFromView.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void switchToMain(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("main.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void switchToFull(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("viewFShip.fxml"));
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
    void delete(ActionEvent event){
        if(index!=-1) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("CONFIRM!");
            alert.setHeaderText("Are you sure you want to delete this ship record?");
            alert.setContentText("ALL THE PARK RECORDS FOR THIS SHIP WILL BE LOST!");
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.YES) {
                    Connection conn = SqlCon.ConnectDb();
                    String sql = "delete from harbor.ports1 where sId = " + selsid;
                    PreparedStatement pst;
                    try {
                        pst = conn.prepareStatement(sql);
                        pst.execute();
                        UpdateTable();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    sql = "delete from harbor.ships where sId = " + selsid;
                    PreparedStatement pst1;
                    try {
                        pst1 = conn.prepareStatement(sql);
                        pst1.execute();
                        UpdateTable();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
    @FXML
    public void UpdateTable(){
        tableSid.setCellValueFactory(new PropertyValueFactory<>("sid"));
        tableN.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableO.setCellValueFactory(new PropertyValueFactory<>("owner"));
        tableS.setCellValueFactory(new PropertyValueFactory<>("size"));
        listM = SqlCon.getDatausersOShip();
        delBtn.setVisible(false);
        vFull.setVisible(false);
        tableV.setItems(listM);
    }
    public void UpdateTable(String str){
        tableSid.setCellValueFactory(new PropertyValueFactory<>("sid"));
        tableN.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableO.setCellValueFactory(new PropertyValueFactory<>("owner"));
        tableS.setCellValueFactory(new PropertyValueFactory<>("size"));
        listM = SqlCon.getDatausersOShip("SELECT * FROM harbor.ships WHERE sId LIKE '" + str+"%'");
        delBtn.setVisible(false);
        vFull.setVisible(false);
        tableV.setItems(listM);
    }
    @FXML
    void getSelected (MouseEvent event){
        index = tableV.getSelectionModel().getSelectedIndex();
        if (index <= -1){
            return;
        }
        selsid = tableSid.getCellData(index);
        delBtn.setVisible(true);
        vFull.setVisible(true);
    }
    public static String getSelsid(){
        return selsid;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UpdateTable();
        searchtext.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String s2) {
                delBtn.setVisible(false);
                vFull.setVisible(false);
                index = -1;
                if (searchtext.getText()!="")
                    UpdateTable(searchtext.getText());
                else UpdateTable();
            }
        });
    }
}
