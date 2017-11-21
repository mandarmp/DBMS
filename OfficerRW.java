package PDBMS;

//This is form the admin to view and insert police officer records.

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OfficerRW {
    private LoginManager lm;
    Stage st;
    @FXML private TextField offid;
    @FXML private TextField off_fname;
    @FXML private TextField off_lname;
    @FXML private TextField status;
    @FXML private TextField dob;
    @FXML private TextField gender;
    @FXML private TextField exp;
    @FXML private TextField rankcode;
    @FXML private TextField deptcode;
    @FXML private TextField shiftid;
    @FXML private TextArea address;
    @FXML private Button insert;
    @FXML private Button  search;
    @FXML private Button delete;

    @FXML private ComboBox dptc;
    @FXML private ComboBox sft;

    public void initStatus(final LoginManager l,Stage s){
        lm=l;
        st=s;

        deptcode.setVisible(true);
        shiftid.setVisible(true);
        //dptc.setVisible(false);
        //sft.setVisible(false);
        ObservableList<String> data = FXCollections.observableArrayList();
        ResultSet rs=l.sql("Select dept_name from deptview");
        try {
            while (rs.next()) {

                data.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dptc.setItems(null);
        dptc.setItems(data);

        dptc.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {
                ResultSet r=l.sql("Select dept_code from deptview where dept_name='"+t1+"'");
                try {
                    r.next();
                    deptcode.setText(r.getString(1));
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        });
        //for shidtid
        ObservableList<String> data2 = FXCollections.observableArrayList();
        ResultSet rs2=l.sql("Select shift_descrp from shiftview");
        try {
            while (rs2.next()) {

                data2.add(rs2.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sft.setItems(null);
        sft.setItems(data2);

        sft.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {
                ResultSet r=l.sql("Select shift_id from shiftview where shift_descrp='"+t1+"'");
                try {
                    r.next();
                    shiftid.setText(r.getString(1));
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @FXML
    private void back(){
        st.close();
    }

    public void repfetch(int rep)
    {
        offid.setText(""+rep);
        retrieve();
    }

    @FXML public void onclickdept()
    {


        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("deptview.fxml")
            );
            Parent root = loader.load();
            Scene scene = new Scene(root, 833, 707);
            // lm.stage.setScene(scene);
            Stage stage = new Stage();
            stage.setTitle("Department Details");
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
            mover(root,stage);
            Deptview controller =
                    loader.<Deptview>getController();
            controller.getparam(deptcode.getText());
            controller.initStatus(stage);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

/*
    @FXML private void srch() {
        ResultSet r = LoginManager.sql("select * from officer where officer_id=" + Integer.parseInt(offid.getText()) + " ");
        try {
            if (r.next()) {
                status.setText("Report found");
                insert.setVisible(false);
                delete.setVisible(true);

                off_fname.setText(r.getString("officer_fname"));
                off_lname.setText(r.getString("officer_lname"));
                dob.setText(r.getString("dob"));
                gender.setText(r.getString("gender"));
                exp.setText(r.getString("expr"));
                rankcode.setText(r.getString("rnk"));
                deptcode.setText(r.getString("dept_code"));
                shiftid.setText(r.getString("shift_id"));
                address.setText(r.getString("adr"));
            } else {

                status.setText("Record does not exist: enter new one");
                delete.setVisible(false);
                insert.setVisible(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML private void ins()
    {
        ResultSet r;
        r=LoginManager.sql("insert into officer(officer_id,officer_fname,officer_lname,dob,gender,expr,rnk,adr,dept_code,shift_id) values ("+Integer.parseInt(offid.getText())+",'"+off_fname.getText()+"','"+off_lname.getText()+"','"+dob.getText()+"','"+gender.getText()+"',"+Integer.parseInt(exp.getText())+",'"+address.getText()+"',"+Integer.parseInt(deptcode.getText())+","+Integer.parseInt(shiftid.getText())+")");
        String dptcode=deptcode.getText();
        String shftid=shiftid.getText();

    }

    @FXML private void del()
    {
        ResultSet r;
        r=LoginManager.sql("delete * from officer where officer_id=" + Integer.parseInt(offid.getText()) + " ");
    }*/

    @FXML public void onclickshift()
    {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("shiftview.fxml")
            );
            Parent root = loader.load();
            Scene scene = new Scene(root, 833, 707);
            //lm.stage.setScene(scene);
            Stage stage = new Stage();
            stage.setTitle("Shift Details");
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
            mover(root,stage);
            Shiftview controller =
                    loader.<Shiftview>getController();
            controller.getparam(shiftid.getText());
            controller.initStatus(stage);

        } catch (IOException ex) {
            ex.printStackTrace();
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


    private boolean rt=false,nxt=false,bck=false;
    private int rid;


    @FXML
    private void retrieve(){
        ResultSet r=null;
        rid=Integer.parseInt(offid.getText());
        if(nxt)
            rid=Integer.parseInt(offid.getText())+1;
        else if(bck)
            rid=Integer.parseInt(offid.getText())-1;
        if(rid<1)
            rid=1;

        if(rid>0)
        {r=lm.sql("Select * from officer where officer_id="+rid);
            offid.setText(""+rid);
            bck=nxt=false;
        }




        try {
            if(r.next())
            {
                rt=true;

                status.setText("Officer Found");
                off_fname.setText(r.getString("officer_fname"));
                off_lname.setText(r.getString("officer_lname"));
                dob.setText(r.getString("dob"));
                gender.setText(r.getString("gender"));
                exp.setText(r.getString("expr"));
                rankcode.setText(r.getString("rnk"));
                deptcode.setText(r.getString("dept_code"));
                shiftid.setText(r.getString("shift_id"));
                address.setText(r.getString("adr"));

            }
            else
            {status.setText("Officer not Found");clear();}
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @FXML
    private void insert()
    {Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Insert or Update Information Report");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            alert.close();
        } else {
            alert.close();
            return;
        }
        if(!rt) {
            ResultSet r;


            r=LoginManager.sql("insert into officer(officer_fname,officer_lname,dob,gender,expr,rnk,adr,dept_code,shift_id) values ('"+off_fname.getText()+"','"+off_lname.getText()+"','"+dob.getText()+"','"+gender.getText()+"',"+Integer.parseInt(exp.getText())+",'"+rankcode.getText()+"','"+address.getText()+"',"+Integer.parseInt(deptcode.getText())+","+Integer.parseInt(shiftid.getText())+")");

            status.setText("Officer inserted");
        }
        else
        {

            ResultSet r;

            r=lm.sql("update officer set officer_Fname='"+ off_fname.getText() + "', officer_Lname = '"+off_lname.getText() + "',dob ='"+dob.getText()+"',gender = '" + gender.getText() + "',expr ="+exp.getText()+ ",rnk='"+rankcode.getText()+"', adr='"+address.getText() + "',dept_code=" + deptcode.getText() + ",shift_id=" + shiftid.getText() + " where officer_id="+offid.getText()+";");
            status.setText("Officer Updated");
            //retrieve();
        }
    }

    @FXML
    private void disable(){
        offid.setEditable(false);
        insert.setVisible(true);
        search.setVisible(false);
    }

    @FXML
    private void enabl(){
        offid.setEditable(true);
        insert.setVisible(false);
        search.setVisible(true);
        clear();
    }

    @FXML
    private void next(){
        nxt=true;
        bck=false;
        retrieve();
    }

    @FXML
    private void previous(){
        bck=true;
        nxt=false;
        retrieve();
    }


    @FXML
    private void clear(){
        offid.clear();
        off_fname.clear();
        off_lname.clear();
        dob.clear();
        gender.clear();
        address.clear();
        exp.clear();
        rankcode.clear();
        deptcode.clear();
        shiftid.clear();

        rt=false;
    }

}








