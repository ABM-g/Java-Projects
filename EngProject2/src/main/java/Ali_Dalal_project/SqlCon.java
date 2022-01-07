package Ali_Dalal_project;

import java.sql.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SqlCon {

    Connection conn = null;
    public static Connection ConnectDb(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/harbor","root","1234");
            return conn;
        } catch (Exception e) {
            return null;
        }

    }
    public static ObservableList<DataS> getDatausers(){
        Connection conn = ConnectDb();
        ObservableList<DataS> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from ports1");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                list.add(new DataS(rs.getInt("pId"),rs.getString("sId")
                        ,rs.getDouble("wight") ,rs.getDouble("fee"),rs.getDate("pDate"),rs.getString("pAt")));
            }
        } catch (Exception e) {
        }
        return list;
    }
    public static ObservableList<DataS> getDatausers(String str){
        Connection conn = ConnectDb();
        ObservableList<DataS> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement(str);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                list.add(new DataS(rs.getInt("pId"),rs.getString("sId")
                        , rs.getDouble("wight") ,rs.getDouble("fee"),rs.getDate("pDate"),rs.getString("pAt")));
            }
        } catch (Exception e) {
        }
        return list;
    }
    public static ResultSet getDatausersShips(){
        Connection conn = ConnectDb();
        ResultSet list = null;
        try {
            Statement sta = conn.createStatement();
            list = sta.executeQuery("SELECT * FROM harbor.ships;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public static ResultSet getDatausersParks(){
        Connection conn = ConnectDb();
        ResultSet list = null;
        try {
            Statement sta = conn.createStatement();
            list = sta.executeQuery("SELECT * FROM harbor.ports1;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public static ObservableList<ShipD> getDatausersOShip(){
        Connection conn = ConnectDb();
        ObservableList<ShipD> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from harbor.ships");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                list.add(new ShipD(rs.getString("sId"),rs.getString("sName")
                        ,rs.getString("cOwner") ,rs.getDouble("sSize")));
            }
        } catch (Exception e) {
        }
        return list;
    }
    public static ObservableList<ShipD> getDatausersOShip(String str){
        Connection conn = ConnectDb();
        ObservableList<ShipD> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement(str);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                list.add(new ShipD(rs.getString("sId"),rs.getString("sName")
                        ,rs.getString("cOwner") ,rs.getDouble("sSize")));
            }
        } catch (Exception e) {
        }
        return list;
    }
}
