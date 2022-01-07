package Ali_Dalal_project;

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
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddCon implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField ITBox;
    @FXML
    private Label Ronglap1;
    @FXML
    private Button addBtn;
    @FXML
    private CheckBox cCBox;
    @FXML
    private Label donelap;
    @FXML
    private TextField fTBox;
    @FXML
    private VBox infoprombit;
    @FXML
    private Label namePrombitL;
    @FXML
    private Label ownerPrombitL;
    @FXML
    private CheckBox pCBox;
    @FXML
    private CheckBox puCBox;
    @FXML
    private Button showPBtn;
    @FXML
    private Label sidPrombitL;
    @FXML
    private Label sizePrombitL;
    @FXML
    private Label theFee;
    @FXML
    private VBox vp;
    @FXML
    private TextField wTBox;
    @FXML
    private RadioButton rPL1;
    @FXML
    private RadioButton rPL2;
    @FXML
    private RadioButton rPL3;
    @FXML
    private RadioButton rPL4;
    @FXML
    private RadioButton rPL5;
    @FXML
    private RadioButton rPL6;
    @FXML
    private RadioButton rPL7;
    @FXML
    private RadioButton rPL8;
    @FXML
    private RadioButton rPL9;
    @FXML
    private RadioButton rPR1;
    @FXML
    private RadioButton rPR2;
    @FXML
    private RadioButton rPR3;
    @FXML
    private RadioButton rPR4;
    @FXML
    private RadioButton rPR5;
    @FXML
    private RadioButton rPR6;
    @FXML
    private RadioButton rPR7;
    @FXML
    private RadioButton rPR8;
    @FXML
    private RadioButton rPR9;
    @FXML
    private Label wOnlyNum;
    @FXML
    private Label fOnlyNum;
    private final ToggleGroup group = new ToggleGroup();
    private String selcRadio="PL1";
    private boolean chSid=false,chW=false,chfee=true;
    @FXML
    void add(ActionEvent event) {
            theFee.setVisible(false);
            donelap.setVisible(false);
            Ronglap1.setVisible(false);
            double afee;
            if(fTBox.getText()=="") {
            afee = 0.0;
            }else afee = Double.valueOf(fTBox.getText());
            DataS d1 = new DataS(ITBox.getText(),Double.valueOf(wTBox.getText()),
                    afee,cCBox.isSelected(),puCBox.isSelected(),pCBox.isSelected(),selcRadio);
            d1.printInConsole();
            if(!donelap.isVisible())
                donelap.setVisible(true);
            theFee.setText(("The final fee is : "+d1.getFee()+"$"));
            theFee.setVisible(true);
    }
    @FXML
    void exit(ActionEvent event) {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.show();
        stage.close();
    }
    @FXML
    void switchToAddShip(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("addShip.fxml"));
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
     void switchToMain(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("main.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void hidePrombit(ActionEvent event) {
        infoprombit.setVisible(false);
    }
    @FXML
    void showPrombit(ActionEvent event) {
        ResultSet re = SqlCon.getDatausersShips();
        try {
        while (re.next()) {
            infoprombit.setVisible(true);
            if (re.getString("sId").equals(ITBox.getText())) {
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
    }
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
    public void btnEn(){
    if (chSid&&chW&&chfee)
        addBtn.setDisable(false);
    else addBtn.setDisable(true);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ITBox.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String s2) {
                try {
                    boolean f = false;
                    ResultSet re = SqlCon.getDatausersShips();
                    while (re.next()) {
                        if (re.getString("sId").equals(s2)) {
                            f = true;
                            break;
                        }
                    }
                    infoprombit.setVisible(false);
                    if (f) {
                        showPBtn.setVisible(true);
                        chSid=true;
                        btnEn();
                        Ronglap1.setVisible(false);
                    } else {
                        showPBtn.setVisible(false);
                        chSid=false;
                        btnEn();
                        Ronglap1.setVisible(true);
                    }
                    if(s2.length()==0){
                        showPBtn.setVisible(false);
                        Ronglap1.setVisible(false);
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        });
        wTBox.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String s2) {
                if(isNumeric(s2)){
                    wOnlyNum.setVisible(false);
                    chW=true;
                    btnEn();
                }else {
                    wOnlyNum.setVisible(true);
                    chW=false;
                    btnEn();
                }
                if(s2=="")
                    wOnlyNum.setVisible(false);
            }
        });
        fTBox.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String s2) {
                if(isNumeric(s2)||s2==""){
                    fOnlyNum.setVisible(false);
                    chfee=true;
                    btnEn();
                }else {
                    fOnlyNum.setVisible(true);
                    chfee=false;
                    btnEn();
                }
            }
        });
        rPL1.setToggleGroup(group);
        rPL2.setToggleGroup(group);
        rPL3.setToggleGroup(group);
        rPL4.setToggleGroup(group);
        rPL5.setToggleGroup(group);
        rPL6.setToggleGroup(group);
        rPL7.setToggleGroup(group);
        rPL8.setToggleGroup(group);
        rPL9.setToggleGroup(group);
        rPR1.setToggleGroup(group);
        rPR2.setToggleGroup(group);
        rPR3.setToggleGroup(group);
        rPR4.setToggleGroup(group);
        rPR5.setToggleGroup(group);
        rPR6.setToggleGroup(group);
        rPR7.setToggleGroup(group);
        rPR8.setToggleGroup(group);
        rPR9.setToggleGroup(group);
        group.selectedToggleProperty().addListener((observable, oldVal, newVal) -> {
            selcRadio = newVal.toString();
            selcRadio = selcRadio.substring(selcRadio.indexOf("'")+1,selcRadio.length()-1);
        });
        group.selectToggle(rPL1);
    }

}
