package PDBMS;

import com.jfoenix.controls.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainView {
    @FXML
    private TextField Session;
    public LoginManager l;
    @FXML private Button users;
    @FXML private Button traf;
    @FXML private Button veh;


    @FXML private JFXTextArea card1;
    @FXML private JFXTextArea card2;
    @FXML private JFXTextArea card3;
    @FXML private JFXTextArea card4;
    @FXML private JFXTextArea card5;
    @FXML private JFXTextArea card6;
    @FXML private JFXTextArea card7;



    @FXML private Pane c1;
    @FXML private Pane c2;
    @FXML private Pane c3;
    @FXML private Pane c4;
    @FXML private Pane c5;
    @FXML private Pane c6;
    @FXML private Pane c7;
    @FXML private Pane rpt;

    @FXML private HBox b1;
    @FXML private HBox b2;
    @FXML private HBox b3;
    @FXML private HBox b4;
    @FXML private HBox b5;
    @FXML private HBox b6;
    @FXML private HBox b7;
    @FXML private HBox b8;
    @FXML private HBox b9;
    @FXML private VBox v;

    @FXML private Label l1;
    @FXML private Label l2;
    @FXML private Label l3;
    @FXML private Label l4;
    @FXML private Label l5;
    @FXML private Label l6;
    @FXML private Label l7;
    @FXML private Label l8;
    @FXML private Label l9;

    @FXML private JFXTextField t0;
    @FXML private JFXTextField t1;
    @FXML private JFXTextField t2;
    @FXML private JFXTextField t3;
    @FXML private JFXTextField t4;
    @FXML private JFXTextField t5;
    @FXML private JFXTextField t6;
    @FXML private JFXTextField t7;
    @FXML private JFXTextField t8;
    @FXML private JFXTextField t9;


    @FXML private JFXButton srchbtn;
    @FXML private JFXButton offhm;
    @FXML private JFXButton paynow;
    @FXML private JFXTextField srch;
    @FXML private JFXTextField srch1;
    @FXML private JFXButton opn;

    @FXML private AnchorPane right;
    @FXML private AnchorPane sear;
    @FXML private AnchorPane officers;
    @FXML private AnchorPane graphs;
    @FXML private AnchorPane user;
    @FXML private AnchorPane an_content;

    @FXML private Label offname;
    @FXML private Label offtyp;
    @FXML private Label srchtitle;
    //search fields
    @FXML private JFXTextField name;
    @FXML private JFXTextField ofname;
    @FXML private JFXTextField tupls;
    @FXML private JFXTextField ofcode;
    @FXML private TextField stdt;
    @FXML private TextField endt;

    @FXML private JFXCheckBox ch1;
    @FXML private JFXCheckBox ch2;
    @FXML private JFXCheckBox ch3;
    @FXML private JFXCheckBox ch4;
    @FXML private JFXCheckBox ch5;
    @FXML private JFXCheckBox ch6;
    @FXML private JFXCheckBox ch7;

    @FXML private JFXComboBox offense;
    @FXML private JFXComboBox dist;
    @FXML private JFXComboBox casest;
    @FXML private JFXComboBox vicst;

    @FXML private JFXDatePicker st;
    @FXML private JFXDatePicker en;

    //user management
    @FXML private TextField tf0;
    @FXML private TextField tf1;
    @FXML private TextField tf2;
    @FXML private TextField tf3;
    @FXML private TextField tf01;

    //graphs
    @FXML private PieChart pie;

    @FXML private ComboBox graph;

    @FXML private StackedBarChart<String, Integer> bar;
    @FXML private CategoryAxis X;
    @FXML private NumberAxis Y;
    @FXML private AnchorPane ap;

    //officers
    @FXML private JFXTextField tc;
    @FXML private JFXTextField ac;
    @FXML private JFXTextField cc;
    @FXML private PieChart ofpie;

    private int ch=0;
    boolean ad=false,tf=false;

    public void initSessionID(final LoginManager lm){
        l=lm;
        right.setVisible(true);
        right.toFront();
        sear.setVisible(false);
        sear.toBack();
        InfoRep.fixpicker(st);
        InfoRep.fixpicker(en);
        stdt=st.getEditor();
        endt=en.getEditor();

        if(LoginManager.tp.equals("admin"))
        { ad=true;users.setVisible(true);offhm.setVisible(true);
            traf.setVisible(true);veh.setVisible(true);
        }


        else if(LoginManager.tp.equals("trafficofficer"))
        {traf.setVisible(true);veh.setVisible(true);
            users.setVisible(false);tf=true;}
        else
        {traf.setVisible(false);veh.setVisible(false);
            users.setVisible(false);}

        Session.setText("Session ID : "+lm.sid);
        offname.setText(LoginManager.nam.toUpperCase());
        offtyp.setText(LoginManager.tp.toUpperCase());

        ObservableList<String> data = FXCollections.observableArrayList();
        ResultSet rs=l.sql("Select Signal from Offense");

        try {
            while (rs.next()) {

                data.add(rs.getString("Signal"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        offense.setItems(null);
        offense.setItems(data);

        offense.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {
                ResultSet r=l.sql("Select Offense_ID from Offense where Signal='"+t1+"'");
                try {
                    r.next();
                    ofcode.setText(r.getString("Offense_ID"));
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        });



        dist.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {
                d=t1;
            }
        });

        ObservableList<String> vic = FXCollections.observableArrayList();
        vic.addAll("Fatal","Non-fatal");
        vicst.setItems(null);
        vicst.setItems(vic);

        vicst.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {
                vst=t1;
            }
        });

        ObservableList<String> cas = FXCollections.observableArrayList();
        cas.addAll("OPEN","CLOSED");
        casest.setItems(null);
        casest.setItems(cas);

        casest.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {
                cst=t1;
            }
        });

        paynow.setVisible(false);
        home();

    }


    String d,vst,cst;

    /*@FXML
    private void inforep(){
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("InfoRep.fxml")
            );
            l.root=loader.load();
            l.mover();
            l.scene.setRoot(l.root);
            InfoRep controller =
                    loader.<InfoRep>getController();
            controller.initStatus(l);

        } catch (IOException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        Main.res();
    }*/

    UserMan u;

    @FXML
    private void userman(){
        user.toFront();
        u=new UserMan();
        u.initStatus(l,tf0,tf1,tf2,tf3,tf01);
        Main.res();
    }

    @FXML
    private void u_crt(){
        u.create();
    }

    @FXML
    private void u_updt(){
        u.update();
    }

    @FXML
    private void u_del(){
        u.delete();
    }



    @FXML
    private void home(){
        an_content.toFront();
        right.toFront();
        right.setDisable(false);

        opn.setVisible(true);
        t0.setVisible(false);
        v.setVisible(false);
        ch=1;
        if(ad){
            cards(LoginManager.sql(("select TOP(7) officer_id as Report_id,Officer_fname as Rep_fname,officer_lname as Rep_lname,rnk as dat from officer ORDER BY officer_id")));
        }
        else if(tf)
        {
            opn.setVisible(false);
            cards(LoginManager.sql("select TOP (7) Violation_id as Report_id,fname as Rep_Fname,lname as Rep_Lname,dat from vehicle INNER JOIN ticket on vehicle.regno=ticket.regno where officer_id="+LoginManager.ofid+" ORDER BY dat DESC"));

        }
        else
            cards(LoginManager.sql("select TOP (7) Report_id,Rep_Fname,Rep_Lname,dat from Info_Rep where officer_id="+LoginManager.ofid+" and case_status = 'OPEN' ORDER BY dat DESC"));
       // InfoRep.ext=true;

    }

    @FXML
    private void ofhome(){
        officers.toFront();


    }

    Officerlogin of=new Officerlogin();
    LinkedList<Integer> ofid=new LinkedList<>();
    LinkedList<String> ofnam=new LinkedList<>();
    @FXML
    private void ofsrch(){

        ResultSet r;
        String nam=srch1.getText(),f,l;
        String n[]=nam.split(" ");
        if(n.length>2)
        {f=n[0];l=n[1];
            r=LoginManager.sql("select officer_id,officer_fname,officer_lname from officer where officer.officer_fname like '%"+f+"%' and officer.officer_lname like '%"+l+"%'");
        }
        else
        {f=n[0];
            r=LoginManager.sql("select officer_id,officer_fname,officer_lname from officer where officer.officer_fname like '%"+f+"%'");
        }



        try {
            while (r.next()){
                ofid.add(r.getInt(1));
                ofnam.add(r.getString(2)+" "+r.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        oi=0;
        getofficer(oi);


    }

    private void getofficer(int ofi){

        of.getofficer(ofid.get(ofi));
        srch1.setText(ofnam.get(ofi));
        tc.setText(""+of.total);
        ac.setText(""+of.active);
        cc.setText(""+of.closed);
        ofpie.getData().clear();
        ofpie.setLegendSide(Side.LEFT);
        ofpie.setLabelsVisible(false);
        ofpie.setVisible(true);
        ofpie.setData(of.setPIEchart());
    }

    int oi=0;

    @FXML
    private void ofnxt()
    {
        if(!(oi<=-2)){
            oi++;
            if(oi<ofid.size()-1)
                getofficer(oi);
        }else
            oi++;
    }

    @FXML
    private void ofprev(){

        if(oi>1)
            oi--;
        else
            oi=ofid.size()-1;
        if(oi>ofid.size())
            getofficer(oi);
        else
            oi--;

    }

    @FXML
    private void inf(){
        an_content.toFront();
        right.toFront();
        ch=2;
        opn.setVisible(true);
        cards(LoginManager.sql("select TOP (7) Report_id,Rep_Fname,Rep_Lname,dat from Info_Rep where case_status = 'OPEN' ORDER BY dat DESC"));
        //InfoRep.ext=true;
    }

    Graphs g;

    @FXML
    private void stat(){
        graphs.toFront();
        g=new Graphs();
        g.initStatus(l,pie,graph,bar,X,Y,ap);
        Main.res();
    }

    @FXML
    private void load(){
        g.load();
    }

    @FXML
    private void traffic(){
        an_content.toFront();
        right.toFront();
        ch=3;
        opn.setVisible(false);
        cards(LoginManager.sql("select TOP (7) Violation_id as Report_id,fname as Rep_Fname,lname as Rep_Lname,dat from vehicle INNER JOIN ticket on vehicle.regno=ticket.regno ORDER BY dat DESC"));
    }

    private boolean isPrimitive(String value){
        boolean status=true;
        if(value.length()<1)
            return false;
        for(int i = 0;i<value.length();i++){
            char c=value.charAt(i);
            if(Character.isDigit(c) || c=='.'){

            }else{
                status=false;
                break;
            }
        }
        return status;
    }


    @FXML
    private void srch(){

        switch (ch){
            case 1:{
                if(ad){
                    String n[]=srch.getText().split(" ");
                    if(isPrimitive(n[0])){
                        cards(LoginManager.sql("select officer_id as Report_id,officer_fname as Rep_Fname,officer_lname as Rep_Lname,rnk as dat from officer where officer_id="+n[0]));
                    }
                    //String n[]=srch.getText().split(" ");
                    else if(n.length>1)
                        cards(LoginManager.sql("select TOP (7) officer_id as Report_id,officer_fname as Rep_Fname,officer_lname as Rep_Lname,rnk as dat from officer where Rep_fname like '%"+n[0]+"%' and Rep_lname like '%"+n[1]+"%' ORDER BY dat DESC"));
                    else
                        cards(LoginManager.sql("select TOP (7) officer_id as Report_id,officer_fname as Rep_Fname,officer_lname as Rep_Lname,rnk as dat from officer where Rep_fname like '%"+srch.getText()+"%' ORDER BY dat DESC"));
                    break;

                }
                else if(tf){
                    String n[]=srch.getText().split(" ");
                    if(isPrimitive(n[0])){
                        cards(LoginManager.sql("select Violation_id as Report_id,fname as Rep_Fname,lname as Rep_Lname,dat from vehicle INNER JOIN ticket on vehicle.regno=ticket.regno where officer_id="+LoginManager.ofid+" and Violation_id="+n[0]));
                    }
                    //String n[]=srch.getText().split(" ");
                    else if(n.length>1)
                        cards(LoginManager.sql("select TOP (7) Violation_id as Report_id,fname as Rep_Fname,lname as Rep_Lname,dat from vehicle INNER JOIN ticket on vehicle.regno=ticket.regno where officer_id="+LoginManager.ofid+" and Rep_fname like '%"+n[0]+"%' and Rep_lname like '%"+n[1]+"%' ORDER BY dat DESC"));
                    else
                        cards(LoginManager.sql("select TOP (7) Violation_id as Report_id,fname as Rep_Fname,lname as Rep_Lname,dat from vehicle INNER JOIN ticket on vehicle.regno=ticket.regno where officer_id="+LoginManager.ofid+" and Rep_fname like '%"+srch.getText()+"%' ORDER BY dat DESC"));
                    break;
                }
                else{
                String n[]=srch.getText().split(" ");
                if(isPrimitive(n[0])){
                    cards(LoginManager.sql("select Report_id,Rep_Fname,Rep_Lname,dat from Info_Rep where officer_id="+LoginManager.ofid+" and case_status = 'OPEN' and Report_id="+n[0]));
                    //System.out.println("select Report_id,Rep_Fname,Rep_Lname,dat from Info_Rep where officer_id="+LoginManager.ofid+" and Report_id="+n[0]);
                }
                else if(n.length>1)
                    cards(LoginManager.sql("select TOP (7) Report_id,Rep_Fname,Rep_Lname,dat from Info_Rep where officer_id="+LoginManager.ofid+" and case_status = 'OPEN' and Rep_fname like '%"+n[0]+"%' and Rep_lname like '%"+n[1]+"%' ORDER BY dat DESC"));
                else
                    cards(LoginManager.sql("select TOP (7) Report_id,Rep_Fname,Rep_Lname,dat from Info_Rep where officer_id="+LoginManager.ofid+" and case_status = 'OPEN' and Rep_fname like '%"+srch.getText()+"%' ORDER BY dat DESC"));
                //InfoRep.ext=true;
                break;
            }}
            case 2:{
                String n[]=srch.getText().split(" ");
                if(isPrimitive(n[0])){
                    cards(LoginManager.sql("select Report_id,Rep_Fname,Rep_Lname,dat from Info_Rep where Report_id="+n[0]));
                    //System.out.println("select Report_id,Rep_Fname,Rep_Lname,dat from Info_Rep where Report_id="+n[0]);
                }
                else if(n.length>1)
                   cards(LoginManager.sql("select TOP (7) Report_id,Rep_Fname,Rep_Lname,dat from Info_Rep where Rep_fname like '%"+n[0]+"%' and Rep_lname like '%"+n[1]+"%' and case_status = 'OPEN' ORDER BY dat DESC"));
                else
                    cards(LoginManager.sql("select TOP (7) Report_id,Rep_Fname,Rep_Lname,dat from Info_Rep where Rep_fname like '%"+srch.getText()+"%' and case_status = 'OPEN'ORDER BY dat DESC"));
                //InfoRep.ext=true;
                break;
            }

            case 3:{
                String n[]=srch.getText().split(" ");
                if(isPrimitive(n[0])){
                    cards(LoginManager.sql("select Violation_id as Report_id,fname as Rep_Fname,lname as Rep_Lname,dat from vehicle INNER JOIN ticket on vehicle.regno=ticket.regno where Violation_id="+n[0]));
                }
                //String n[]=srch.getText().split(" ");
                else if(n.length>1)
                    cards(LoginManager.sql("select TOP (7) Violation_id as Report_id,fname as Rep_Fname,lname as Rep_Lname,dat from vehicle INNER JOIN ticket on vehicle.regno=ticket.regno where Rep_fname like '%"+n[0]+"%' and Rep_lname like '%"+n[1]+"%' ORDER BY dat DESC"));
                else
                    cards(LoginManager.sql("select TOP (7) Violation_id as Report_id,fname as Rep_Fname,lname as Rep_Lname,dat from vehicle INNER JOIN ticket on vehicle.regno=ticket.regno where Rep_fname like '%"+srch.getText()+"%' ORDER BY dat DESC"));
                break;
            }
        }

    }

    int p1,p2,p3,p4,p5,p6,p7;

    private LinkedList<Integer> cards(ResultSet r){
        c1.setVisible(false);
        c2.setVisible(false);
        c3.setVisible(false);
        c4.setVisible(false);
        c5.setVisible(false);
        c6.setVisible(false);
        c7.setVisible(false);

        int i=1;
        String D="Date : ";
        if(ad)
            D="Rank : ";

        LinkedList<Integer> reps=new LinkedList<Integer>();

        try {
            while(r.next()){
                reps.add(r.getInt("Report_id"));
                switch (i){
                    case 1:{
                        //System.out.println("Card 1");
                        card1.setText(r.getInt("Report_id")+" :\n"+r.getString("Rep_Fname")+" "+r.getString("Rep_Lname")+"\n"+D+r.getString("dat"));
                        p1=r.getInt("Report_id");
                        c1.setVisible(true);
                        //System.out.println("Card 1");
                        break;
                    }
                    case 2:{
                        card2.setText(r.getInt("Report_id")+" :\n"+r.getString("Rep_Fname")+" "+r.getString("Rep_Lname")+"\n"+D+r.getString("dat"));
                        p2=r.getInt("Report_id");
                        c2.setVisible(true);
                        break;
                    }
                    case 3:{
                        card3.setText(r.getInt("Report_id")+" :\n"+r.getString("Rep_Fname")+" "+r.getString("Rep_Lname")+"\n"+D+r.getString("dat"));
                        p3=r.getInt("Report_id");
                        c3.setVisible(true);
                        break;
                    }
                    case 4:{
                        card4.setText(r.getInt("Report_id")+" :\n"+r.getString("Rep_Fname")+" "+r.getString("Rep_Lname")+"\n"+D+r.getString("dat"));
                        p4=r.getInt("Report_id");
                        c4.setVisible(true);
                        break;
                    }
                    case 5:{
                        card5.setText(r.getInt("Report_id")+" :\n"+r.getString("Rep_Fname")+" "+r.getString("Rep_Lname")+"\n"+D+r.getString("dat"));
                        p5=r.getInt("Report_id");
                        c5.setVisible(true);
                        break;
                    }
                    case 6:{
                        card6.setText(r.getInt("Report_id")+" :\n"+r.getString("Rep_Fname")+" "+r.getString("Rep_Lname")+"\n"+D+r.getString("dat"));
                        p6=r.getInt("Report_id");
                        c6.setVisible(true);
                        break;
                    }
                    case 7:{
                        card7.setText(r.getInt("Report_id")+" :\n"+r.getString("Rep_Fname")+" "+r.getString("Rep_Lname")+"\n"+D+r.getString("dat"));
                        p7=r.getInt("Report_id");
                        c7.setVisible(true);
                        break;
                    }


                }
                i++;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reps;

    }

    private void repget(int p){
        paynow.setVisible(false);
        ResultSet cr1=LoginManager.sql("select * from victim INNER JOIN Info_Rep on victim.victim_id=Info_Rep.victim_id INNER JOIN offender ON offender.offender_id = Info_Rep.offender_id INNER JOIN offense ON offense.offense_id = Info_Rep.offense_id where Report_id="+p);
        try {
            while(cr1.next()) {
                l1.setText("Offender :");
                t1.setText(cr1.getString(25)+" "+cr1.getString(26));

                l2.setText("Victim :");
                t2.setText(cr1.getString(2)+" "+cr1.getString(3));

                l3.setText("Description :");
                t3.setText(cr1.getString("Descr"));

                l4.setText("Location :");
                t4.setText(cr1.getString("Location"));

                l5.setText("Offense :");
                t5.setText(cr1.getString("Signal"));

                l6.setText("Occurrence :");
                t6.setText(cr1.getString("Date_Occured"));

                l7.setText("Position :");
                t7.setText(cr1.getString("case_status"));

                l8.setText("Injuries :");
                t8.setText(cr1.getString("vic_status"));

                l9.setText("Report Type :");
                t9.setText(cr1.getString("Report_type"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    int payid=0;

    private void tickget(int p){
paynow.setVisible(true);
        ResultSet cr1=LoginManager.sql("select * from vehicle INNER JOIN ticket on vehicle.regno=ticket.regno where violation_id="+p);
        try {
            while(cr1.next()) {
                l1.setText("Vehicle Reg :");
                t1.setText(cr1.getString(1));

                l2.setText("City :");
                t2.setText(cr1.getString(3));



                l3.setText("Location :");
                t3.setText(cr1.getString("Location"));

                l4.setText("Description :");
                t4.setText(cr1.getString("Descr"));

                l5.setText("Drunk :");
                t5.setText(cr1.getString("alcohol"));

                l6.setText("Officer ID :");
                t6.setText(cr1.getString("officer_id"));

                l7.setText("Status :");
                t7.setText(cr1.getString("ticket_status"));
                if(t7.getText().equals("pending"))
                {paynow.setVisible(true);payid=p;
                l9.setText("Pending Amount :");
                t9.setText(cr1.getString("violation_amount"));
                }
                else
                {paynow.setVisible(false);l9.setVisible(false);t9.setVisible(false);}

                l8.setText("Vehicle :");
                t8.setText(cr1.getString(5)+" "+cr1.getString(6));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private void offget(int p){
        ResultSet cr1=LoginManager.sql("select * from officer where officer_id="+p);
        paynow.setVisible(false);
        try {
            while(cr1.next()) {
                l1.setText("Officer Name :");
                t1.setText(cr1.getString(2)+" "+cr1.getString(3));

                l2.setText("DOB :");
                t2.setText(cr1.getString(4));

                l3.setText("Gender :");
                t3.setText(cr1.getString(5));

                l4.setText("Address :");
                t4.setText(cr1.getString("adr"));

                l5.setText("Experience :");
                t5.setText(cr1.getString("expr"));

                l6.setText("Rank :");
                t6.setText(cr1.getString("rnk"));

                l7.setText("Department :");
                t7.setText(cr1.getString("dept_code"));

                l8.setText("Shift :");
                t8.setText(cr1.getString("shift_id"));



                l9.setVisible(false);
                t9.setVisible(false);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML private void paynow(){
        LoginManager.sql("update ticket set ticket_status='paid' where violation_id="+payid);
        paynow.setVisible(false);
        tickget(payid);

    }

    @FXML private void cbtn1(){
        t0.setVisible(true);
        t0.setText(""+p1);
        v.setVisible(true);
        if(ch==1){
            if(tf)
                tickget(p1);
            else if(ad)
                offget(p1);
            else
            repget(p1);
        }
        else if(ch==2)
            repget(p1);
        else
        {
            tickget(p1);
        }


    }

    @FXML private void cbtn2(){
        t0.setVisible(true);
        t0.setText(""+p2);
        v.setVisible(true);
        if(ch==1){
            if(tf)
                tickget(p2);
            else if(ad)
                offget(p2);
            else
            repget(p2);
        }
        else if(ch==2)
            repget(p1);
        else
        {
            tickget(p2);
        }
    }
    @FXML private void cbtn3(){
        t0.setVisible(true);
        t0.setText(""+p3);
        v.setVisible(true);
        if(ch==1){
            if(tf)
                tickget(p3);
            else if(ad)
                offget(p3);
            else
            repget(p3);
        }
        else if(ch==2)
            repget(p1);
        else
        {
            tickget(p3);
        }
    }
    @FXML private void cbtn4(){
        t0.setVisible(true);
        t0.setText(""+p4);
        v.setVisible(true);
        if(ch==1){
            if(tf)
                tickget(p4);
            else if(ad)
                offget(p4);
            else
            repget(p4);
        }
        else if(ch==2)
            repget(p1);
        else
        {
            tickget(p4);
        }
    }
    @FXML private void cbtn5(){
        t0.setVisible(true);
        t0.setText(""+p5);
        v.setVisible(true);
        if(ch==1){
            if(tf)
                tickget(p5);
            else if(ad)
                offget(p5);
            else
            repget(p5);
        }
        else if(ch==2)
            repget(p1);
        else
        {
            tickget(p5);
        }
    }
    @FXML private void cbtn6(){
        t0.setVisible(true);
        t0.setText(""+p6);
        v.setVisible(true);
        if(ch==1){
            if(tf)
                tickget(p6);
            else if(ad)
                offget(p6);
            else
            repget(p6);
        }
        else if(ch==2)
            repget(p1);
        else
        {
            tickget(p6);
        }
    }
    @FXML private void cbtn7(){
        t0.setVisible(true);
        t0.setText(""+p7);
        v.setVisible(true);
        if(ch==1){
            if(tf)
                tickget(p7);
            else if(ad)
                offget(p7);
            else
            repget(p7);
        }
        else if(ch==2)
            repget(p1);
        else
        {
            tickget(p7);
        }
    }

    public void loadrep(int repi){
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("InfoRep.fxml")
            );
            Parent root = loader.load();
            Scene scene = new Scene(root, 1016, 723);
            //lm.stage.setScene(scene);
            Stage stage = new Stage();
            stage.setTitle("Information Report");
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
            mover(root,stage);
            InfoRep controller =
                    loader.<InfoRep>getController();
            controller.initStatus(l,stage);
            controller.repfetch(repi);


        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }


    public void loadofi(int repi){
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("OfficerRW.fxml")
            );
            Parent root = loader.load();
            Scene scene = new Scene(root, 833, 707);
            //lm.stage.setScene(scene);
            Stage stage = new Stage();
            stage.setTitle("Officers");
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
            mover(root,stage);
            OfficerRW controller =
                    loader.<OfficerRW>getController();
            controller.initStatus(l,stage);
            controller.repfetch(repi);


        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML private void open(){
        switch (ch){
            case 1:{
                if(ad)
                    loadofi(Integer.parseInt(t0.getText()));

                else
                loadrep(Integer.parseInt(t0.getText()));
                break;
            }
            case 2:{
                loadrep(Integer.parseInt(t0.getText()));
                break;
            }

        }
    }

    double x = 0;
    double y = 0;

    public void mover(Parent root,Stage stage){

        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                x = event.getSceneX();
                y = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - x);
                stage.setY(event.getScreenY() - y);
            }
        });
    }

    int j=0;

    @FXML private void nxt(){
        switch (++j){
            case 1:
                cbtn1();
                break;
            case 2:
                cbtn2();
                break;
            case 3:
                cbtn3();
                break;
            case 4:
                cbtn4();
                break;
            case 5:
                cbtn5();
                break;
            case 6:
                cbtn6();
                break;
            case 7:
                cbtn7();
                break;
            default:
                j=0;
        }

    }
    @FXML private void prev(){

        switch (--j){
            case 1:
                cbtn7();
                break;
            case 2:
                cbtn1();
                break;
            case 3:
                cbtn2();
                break;
            case 4:
                cbtn3();
                break;
            case 5:
                cbtn4();
                break;
            case 6:
                cbtn5();
                break;
            case 7:
                cbtn6();
                break;
            default:
                j=8;
        }
    }

    @FXML
    private void fetch(){
        if(raw){
            String s,s0="",s1="",s2="",s3="",s4="",s5="",s6="",s7="",t="38";

            if(ch1.isSelected()){
                String nam=offname.getText(),f,l;
                String n[]=nam.split(" ");
                if(n.length>2)
                {f=n[0];l=n[1];
                    s1="and officer.officer_fname like '%"+f+"%' and officer.officer_lname like '%"+l+"%' ";
                }
                else
                {f=n[0];
                    s1="and officer.officer_fname like '%"+f+"%' ";
                }
            }

            if(ch2.isSelected()){
                if(Integer.parseInt(tupls.getText())>38)
                    t="38";
                else
                    t=tupls.getText();
            }

            if(ch5.isSelected()){
                s3="and Info_Rep.dat >= '"+st.getEditor().getText()+"' AND Info_Rep.dat <= '"+en.getEditor().getText()+"' ";
            }

            if(ch3.isSelected()){
                s4="and Info_Rep.offense_id='"+ofcode.getText()+"' ";
            }

            if(ch4.isSelected()){
                s5="and Info_Rep.District="+d+" ";
            }

            if(ch6.isSelected()){
                s6="and Info_Rep.case_status='"+cst+"' ";
            }

            if(ch7.isSelected()){
                s7="and Info_Rep.vic_status='"+vst+"' ";
            }

            String n[]=name.getText().split(" ");
            if(n.length>1)
                s0="where Info_Rep.Rep_fname like '%"+n[0]+"%' and Info_Rep.Rep_lname like '%"+n[1]+"%' ";
            else
                s0="where Info_Rep.Rep_fname like '%"+n[0]+"%' ";



            s="SELECT TOP ("+t+") * FROM victim INNER JOIN Info_Rep ON victim.victim_id = Info_Rep.victim_id INNER JOIN offender ON offender.offender_id = Info_Rep.offender_id INNER JOIN offense ON offense.offense_id = Info_Rep.offense_id INNER JOIN officer ON officer.officer_id = Info_Rep.officer_id "+s0+s1+s2+s3+s4+s5+s6+s7+" ORDER BY Info_Rep.dat DESC";

            System.out.println("\n"+s+"\n");
            getdata("Information Report and Officers Search",s);
        }
        else{
            String s,s0="",s1="",s2="",s3="",s4="",s5="";
            if(ch1.isSelected())
            {
                s1="and vehicle.regno='"+ofname.getText()+"' ";
            }
            if(ch2.isSelected()){
                s2="and vehicle.make like '%"+tupls.getText()+"%' ";
            }
            if(ch5.isSelected()){
                s3="and ticket.dat >= '"+st.getEditor().getText()+"' AND ticket.dat <= '"+en.getEditor().getText()+"' ";
            }
            if(ch4.isSelected()){
                s4="and vehicle.vehtype='"+d+"' ";
            }
            if(ch6.isSelected()){
                s5="and alcohol='yes'";
            }
            String n[]=name.getText().split(" ");
            if(n.length>1)
                s0="where vehicle.fname like '%"+n[0]+"%' and vehicle.lname like '%"+n[1]+"%' ";
                //cards(LoginManager.sql("select TOP (7) Violation_id as Report_id,fname as Rep_Fname,lname as Rep_Lname,dat from vehicle INNER JOIN ticket on vehicle.regno=ticket.regno where officer_id="+LoginManager.ofid+" and Rep_fname like '%"+n[0]+"%' and Rep_lname like '%"+n[1]+"%' ORDER BY dat DESC"));
            else
                s0="where vehicle.fname like '%"+n[0]+"%' ";
            //cards(LoginManager.sql("select TOP (7) Violation_id as Report_id,fname as Rep_Fname,lname as Rep_Lname,dat from vehicle INNER JOIN ticket on vehicle.regno=ticket.regno where officer_id="+LoginManager.ofid+" and Rep_fname like '%"+srch.getText()+"%' ORDER BY dat DESC"));


            s="select top (38) * from vehicle INNER JOIN ticket on vehicle.regno=ticket.regno "+s0+s1+s2+s3+s4+s5+" ORDER BY ticket.dat DESC";
            System.out.println("\n"+s+"\n");
            getdata("Vehicle Search",s);
        }

    }

    private void getdata(String t,String q){
        try {



            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("Tables.fxml")
            );
            Parent root = loader.load();
            Scene scene = new Scene(root, 1920, 1080);
            //lm.stage.setScene(scene);
            Stage stage = new Stage();
            stage.setTitle("Tables");
            stage.setScene(scene);
            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();
            stage.setWidth(bounds.getWidth());
            stage.setHeight(bounds.getHeight());
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setMaximized(true);
            stage.show();
            mover(root,stage);
            Tables controller =
                    loader.<Tables>getController();
            controller.title.setText(t);
            if(t.equals("Vehicle Search"))
                controller.fir.setVisible(false);
            else
            {controller.fir.setVisible(true);
                ResultSet r=LoginManager.sql(q);
                InfoRep.reps=new ArrayList<Integer>();
                while(r.next())
                {InfoRep.reps.add(r.getInt("Report_id"));
                    InfoRep.ext=true;
                }
            }
            controller.initStatus(l,stage,q);



        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void tables()
    {
        raw=true;
        an_content.toBack();
        officers.toBack();
        graphs.toBack();
        user.toBack();
        sear.toFront();
        sear.setVisible(true);
        right.setDisable(true);
        v.setVisible(false);
        offense.setVisible(true);
        ofname.setPromptText("Search by Officer Name");
        srchtitle.setText("Information Reports and Officers Search");
        ObservableList<String> dis = FXCollections.observableArrayList();
        dis.addAll("1","2","3","4","5","6","7","8");
        dist.setItems(null);
        dist.setItems(dis);
        dist.setPromptText("Select District");
        tupls.setPromptText("Number of Records");
        tupls.setText("");
        tupls.setEditable(true);
        casest.setVisible(true);
        ch6.setText("");
        vicst.setVisible(true);
        ch7.setVisible(true);
        ofcode.setVisible(true);
        ch3.setVisible(true);

    }

    Boolean raw=true;

    @FXML
    private void vehicle(){
        raw=false;
        an_content.toBack();
        officers.toBack();
        graphs.toBack();
        user.toBack();
        sear.toFront();
        sear.setVisible(true);
        //infsrch.toFront();
        right.setDisable(true);
        v.setVisible(false);
        offense.setVisible(false);
        ofname.setPromptText("Search By Vehicle Reg Number");
        srchtitle.setText("Vehicle Search");
        dist.setItems(null);
        ObservableList<String> dis = FXCollections.observableArrayList();
        ResultSet rs=l.sql("Select distinct vehtype from vehicle");

        try {
            while (rs.next()) {

                dis.add(rs.getString("vehtype"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dist.setPromptText("Select Vehicle Type");
        dist.setItems(dis);

        tupls.setPromptText("Manufacturer");
        tupls.setText("");
        tupls.setEditable(true);
        casest.setVisible(false);
        ch6.setText("Drink and Drive");
        vicst.setVisible(false);
        ch7.setVisible(false);
        ofcode.setVisible(false);
        ch3.setVisible(false);




    }

    /*try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("Tables.fxml")
            );
            l.root=loader.load();
            l.mover();
            l.scene.setRoot(l.root);
            Tables controller =
                    loader.<Tables>getController();
            controller.initStatus(l);

        } catch (IOException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        Main.res();*/
   /* @FXML
    private void officers()
    {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("OfficerView1.fxml")
            );
            l.root=loader.load();
            l.mover();
            l.scene.setRoot(l.root);
            OfficerView controller =
                    loader.<OfficerView>getController();
            controller.initStatus(l);

        } catch (IOException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        Main.res();
    }

    @FXML
    private void graph(){
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("Graphs1.fxml")
            );
            l.root=loader.load();
            l.mover();
            l.scene.setRoot(l.root);
            Graphs controller =
                    loader.<Graphs>getController();
            controller.initStatus(l);

        } catch (IOException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        Main.res();
    }

    @FXML
    private void regcheck(){
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("regcheck1.fxml")
            );
            l.root=loader.load();
            l.mover();
            l.scene.setRoot(l.root);
            regcheck controller =
                    loader.<regcheck>getController();
            controller.initStatus(l);

        } catch (IOException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        Main.res();
    }*/

    @FXML
    public void logout(){
        l.logout();
        Main.res();
    }
}
