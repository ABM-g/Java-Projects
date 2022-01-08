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

public class selcteShipCon implements Initializable {
    Stage stage;
    Scene scene;
    Parent root;
    @FXML
    private TableColumn<ShipD, Double> tableS;
    @FXML
    private TableView<ShipD> tableV;
    @FXML
    private TextField searchtext;
    @FXML
    private TableColumn<ShipD, String> tableN;
    @FXML
    private TableColumn<ShipD, String> tableO;
    @FXML
    private TableColumn<ShipD, String> tableSid;
    @FXML
    private Button selectBtn;
    ObservableList<ShipD> listM;
    int index = -1;
    private static String selsid = "0";
    @FXML
    void exit(ActionEvent event) {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.show();
        stage.close();
    }
    @FXML
    public void UpdateTable(){
        tableSid.setCellValueFactory(new PropertyValueFactory<>("sid"));
        tableN.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableO.setCellValueFactory(new PropertyValueFactory<>("owner"));
        tableS.setCellValueFactory(new PropertyValueFactory<>("size"));
        listM = SqlCon.getDatausersOShip();
        selectBtn.setVisible(false);
        tableV.setItems(listM);
    }
    public void UpdateTable(String str){
        tableSid.setCellValueFactory(new PropertyValueFactory<>("sid"));
        tableN.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableO.setCellValueFactory(new PropertyValueFactory<>("owner"));
        tableS.setCellValueFactory(new PropertyValueFactory<>("size"));
        listM = SqlCon.getDatausersOShip("SELECT * FROM harbor.ships WHERE sName LIKE '" + str+"%'");
        selectBtn.setVisible(false);
        tableV.setItems(listM);
    }
    @FXML
    public void select(ActionEvent event) throws IOException {
        AddCon.selsidstage = selsid;
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.show();
        stage.close();
    }
    @FXML
    void getSelected (MouseEvent event){
        index = tableV.getSelectionModel().getSelectedIndex();
        if (index <= -1){
            return;
        }
        selsid = tableSid.getCellData(index);
        selectBtn.setVisible(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UpdateTable();
        searchtext.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String s2) {
                selectBtn.setVisible(false);
                index = -1;
                if (searchtext.getText()!="")
                    UpdateTable(searchtext.getText());
                else UpdateTable();
            }
        });
    }
}
