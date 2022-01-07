package Ali_Dalal_project;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class DataS{
    private int pid;
    private String sid,date12,pat;
    private double wight,fee=0.0,aFee;
    private boolean crane,puller,pilot;


    DataS(String sid, double wight, double aFee, boolean crane, boolean puller, boolean pilot,String pat){
        this.sid = sid;
        this.wight=wight;
        this.aFee=aFee;
        this.crane=crane;
        this.puller=puller;
        this.pilot=pilot;
        this.pat =pat;
        calFee();
    }

    DataS(int pid, String sid, double wight, double fee, Date date,String pat){
        this.pid = pid;
        this.sid = sid;
        this.wight=wight;
        this.fee=fee;
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy/MM/dd");
        date12 = df2.format(date);
        this.pat=pat;
    }
    public String getSid() {
        return sid;
    }
    public void setSid(String sid) {
        this.sid = sid;
    }
    public double getWight() {
        return wight;
    }
    public void setWight(double wight) {
        this.wight = wight;
    }
    public double getFee() {
        return fee;
    }
    public void setFee(double fee) {
        this.fee = fee;
    }
    public double getaFee() {
        return aFee;
    }
    public void setaFee(double aFee) {
        this.aFee = aFee;
    }
    public boolean isCrane() {
        return crane;
    }
    public void setCrane(boolean crane) {
        this.crane = crane;
    }
    public boolean isPuller() {
        return puller;
    }
    public void setPuller(boolean puller) {
        this.puller = puller;
    }
    public boolean isPilot() {
        return pilot;
    }
    public void setPilot(boolean pilot) {
        this.pilot = pilot;
    }
    public int getPid() {
        return pid;
    }
    public void setPid(int pid) {
        this.pid = pid;
    }
    public String getDate12() {
        return date12;
    }
    public void setDate12(String date12) {
        this.date12 = date12;
    }
    public String getPat() {
        return pat;
    }
    public void setPat(String pat) {
        this.pat = pat;
    }
    public void calFee(){
        fee=(wight*0.18237)+aFee;
        if(pilot)
            fee+=350;
        if(crane)
            fee+=920;
        if(puller)
            fee+=200;

        toSQL();
    }
    public void toSQL (){
        String str = "insert into harbor.ports1(sId,pAt,wight,aFee,pDate,pilot,crane,puller,fee) values ('"+ sid +"','"+ pat +"',"+ wight +","+aFee+",now(),"+pilot+","+crane+","+puller+","+fee+");";
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

    public void printInConsole(){
        System.out.println("---info---\nsId = "+ sid +"\npAt = "+ pat +"\nweight = "+
                wight+" T\naFee = "+ aFee+"$\ncrane = "+crane+"\npuller = "+
                puller+"\npilot = "+pilot+"\nFee = "+fee+"$");
    }
}