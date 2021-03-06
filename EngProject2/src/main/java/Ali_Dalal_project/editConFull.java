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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
//editCon class is the controller class for viewFShip.fxml.
public class editConFull implements Initializable {
    //some essential declarations.
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TableColumn<DataS, Integer> tableP;
    @FXML
    private TableColumn<DataS, String> tablePAt;
    @FXML
    private TableColumn<DataS, Double> tableW;
    @FXML
    private TableView<DataS> tableV;
    @FXML
    private TableColumn<DataS, Double> tableF;
    @FXML
    private TableColumn<DataS, String> tableDate;
    @FXML
    private TextField searchtext;
    @FXML
    private Button delBtn;
    @FXML
    private Label namePrombitL;
    @FXML
    private Label ownerPrombitL;
    @FXML
    private Label sidPrombitL;
    @FXML
    private Label sizePrombitL;
    ObservableList<DataS> listM;
    private int index = -1;
    private static int selpid=0;
    private String selsid = editShipCon.getSelsid();
    //these methods for switching to other fxml files each connect to a button in the viewFShip fxml.
    @FXML
    void switchToMain(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("main.fxml"));
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
    //this method to exit the programme.
    @FXML
    void exit(ActionEvent event) {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.show();
        stage.close();
    }
    //this method delete the selected index of the table from the database.
    @FXML
    void delete(ActionEvent event){
        if(index!=-1) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("CONFIRM!");
            alert.setHeaderText("Are you sure you want to delete this port record?");
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.YES) {
                    Connection conn = SqlCon.ConnectDb();
                    String sql = "delete from ports1 where pId = " + selpid;
                    PreparedStatement pst;
                    try {
                        pst = conn.prepareStatement(sql);
                        pst.execute();
                        UpdateTable();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
    //these methods update the table of the fxml file with the data of the database.
    @FXML
    public void UpdateTable(){
        tableP.setCellValueFactory(new PropertyValueFactory<>("pid"));
        tablePAt.setCellValueFactory(new PropertyValueFactory<>("pat"));
        tableW.setCellValueFactory(new PropertyValueFactory<>("wight"));
        tableF.setCellValueFactory(new PropertyValueFactory<>("fee"));
        tableDate.setCellValueFactory(new PropertyValueFactory<>("date12"));
        listM = SqlCon.getDatausers("SELECT * FROM harbor.ports1 where sId = " + selsid);
        delBtn.setVisible(false);
        tableV.setItems(listM);
    }
    public void UpdateTable(String str){
        tableP.setCellValueFactory(new PropertyValueFactory<>("pid"));
        tablePAt.setCellValueFactory(new PropertyValueFactory<>("pat"));
        tableW.setCellValueFactory(new PropertyValueFactory<>("wight"));
        tableF.setCellValueFactory(new PropertyValueFactory<>("fee"));
        tableDate.setCellValueFactory(new PropertyValueFactory<>("date12"));
        listM = SqlCon.getDatausers("SELECT * FROM harbor.ports1 where pId = " + str +" and sId = "+selsid);
        delBtn.setVisible(false);
        tableV.setItems(listM);
    }
    //method to get the index of the selected row in the table.
    @FXML
    void getSelected (MouseEvent event){
        index = tableV.getSelectionModel().getSelectedIndex();
        if (index <= -1){
            return;
        }
        selpid = tableP.getCellData(index);
        delBtn.setVisible(true);
    }
    //initialize method start when the fxml get loaded.
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ResultSet re = SqlCon.getDatausersShips();
        try {
            while (re.next()) {
                if (re.getString("sId").equals(selsid)) {
                    sidPrombitL.setText(re.getString("sId"));
                    namePrombitL.setText(re.getString("sName"));
                    ownerPrombitL.setText(re.getString("cOwner"));
                    sizePrombitL.setText(Double.toString(re.getDouble("sSize")));
                    break;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        UpdateTable();
        searchtext.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String s2) {
                delBtn.setVisible(false);
                index = -1;
                if (searchtext.getText()!="")
                    UpdateTable(searchtext.getText());
                else UpdateTable();
            }
        });
    }
}
