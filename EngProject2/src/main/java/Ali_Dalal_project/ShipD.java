package Ali_Dalal_project;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ShipD {
    private String sid,owner,name;
    private double size;

    ShipD(String sid, String name, String owner, double size){
        this.name=name;
        this.sid=sid;
        this.owner=owner;
        this.size=size;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public void toSQL(){
        String str = "insert into harbor.ships(sId,sName,cOwner,sSize) values ('"+ sid +"','"+ name +"','"+owner+"',"+size+");";
        System.out.println(str);
        MysqlConnect sql = new MysqlConnect();
        sql.connect();
        try {
            PreparedStatement statement = sql.connect().prepareStatement(str);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            sql.disconnect();
        }
    }
}
