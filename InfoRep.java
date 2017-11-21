package PDBMS;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;


public class InfoRep {
    @FXML
    private TextArea status;
    @FXML private TextField tf0;
    @FXML private TextField tf1;
    @FXML private TextField tf2;
    @FXML private TextField tf3;
    @FXML private TextField tf4;
    @FXML private TextField tf5;
    @FXML private TextField tf6;
    @FXML private TextField tf7;
    @FXML private TextField tf8;
    @FXML private TextField tf9;
    @FXML private TextField tf10;
    @FXML private TextField tf11;
    @FXML private TextField tf12;
    @FXML private TextField tf13;
    @FXML private TextField tf14;
    @FXML private TextField tf15;
    @FXML private TextField tf16;
    @FXML private TextField tf17;
    @FXML private TextField tf18;
    @FXML private TextField tf19;
    @FXML private TextField tf20;
    @FXML private ComboBox offense;
    @FXML private DatePicker rep;
    @FXML private DatePicker occ;
    @FXML private TextField oc;
    @FXML private TextField re;
    @FXML private RadioButton Ar_T;
    @FXML private RadioButton Ar_F;
    @FXML private RadioButton F;
    @FXML private RadioButton NF;
    @FXML private Button ret;
    @FXML private Button ins;

    LoginManager lm;
    Stage st;

    public static ArrayList<Integer> reps;

    public static Boolean ext=false;

    public void initStatus(final LoginManager l,final Stage s){
        st=s;
        lm=l;
        status.setText("INFORMATION REPORTS");
        oc=occ.getEditor();
        re=rep.getEditor();
        Ar_T.fire();
        Ar_F.disarm();
        //set datepicker format
        fixpicker(rep);
        fixpicker(occ);

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
                    tf15.setText(r.getString("Offense_ID"));
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public static void fixpicker(DatePicker datePicker){
        datePicker.setConverter(new StringConverter<LocalDate>() {
            String pattern = "yyyy-MM-dd";
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

            {
                datePicker.setPromptText(pattern.toLowerCase());
            }

            @Override public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });
    }

    public void repfetch(int rep)
    {
        tf0.setText(""+rep);
        if(ext)
        {
            ri=rj=0;

        }
        retrieve();

    }

    int ri,rj;

    @FXML
    private void back(){
        st.close();
        if(ext)
        {
            ext=false;
        }
    }

    private boolean rt=false,nxt=false,bck=false;
    private int vi,of,rid;


    @FXML
    private void retrieve(){
        ResultSet r=null;
        if(!ext){
        rid=Integer.parseInt(tf0.getText());
        if(nxt)
            rid=Integer.parseInt(tf0.getText())+1;
        else if(bck)
            rid=Integer.parseInt(tf0.getText())-1;
        if(rid<1)
            rid=1;}
            else
        {
            rid=reps.get(ri);
        }


        if(rid>0)
        {r=lm.sql("Select * from Info_Rep I,Victim V,Offender F,Offense O where Report_ID="+rid+" and V.Victim_ID=I.Victim_ID and F.Offender_ID=I.Offender_ID and O.Offense_ID=I.Offense_ID;");
        tf0.setText(""+rid);
        bck=nxt=false;
        }



        try {
            if(r.next())
            {
                rt=true;
                of=r.getInt(6);
                vi=r.getInt(7);
                status.setText("Report Found at Report ID "+r.getString(1));
                tf1.setText(r.getString(25));
                tf2.setText(r.getString(26));
                tf3.setText(r.getString(28));
                tf4.setText(r.getString(27));
                tf5.setText(r.getString(20));
                tf6.setText(r.getString(21));
                tf7.setText(r.getString(23));
                tf8.setText(r.getString(22));
                tf9.setText(r.getString(2));
                tf10.setText(r.getString(3));
                tf11.setText(r.getString(5));
                tf12.setText(r.getString(4));
                tf13.setText(r.getString("Descr"));
                tf14.setText(r.getString("Location"));//+" District : "+r.getString("District"));
                tf15.setText(r.getString("Offense_ID"));
                offense.setValue(r.getString("Signal"));
                tf16.setText(r.getString("case_status"));
                tf17.setText(r.getString("Report_type"));
                tf18.setText(r.getString("officer_id"));
                oc.setText(r.getString("Date_Occured"));
                re.setText(r.getString("Date_Reported"));
                if(r.getString("vic_status").equals("Non-fatal"))
                    NF.fire();
                else
                    F.fire();

            }
            else
            {status.setText("Report not Found");clear();}
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
            r = lm.sql("insert into Offender(Fname,Lname,Age,Gender) values('" + tf1.getText() + "','" + tf2.getText() + "'," + Integer.parseInt(tf3.getText()) + ",'" + tf4.getText() + "');");
            r = lm.sql("insert into Victim(Fname,Lname,Age,Gender) values('" + tf5.getText() + "','" + tf6.getText() + "'," + Integer.parseInt(tf7.getText()) + ",'" + tf8.getText() + "');");

            int oid = 0, vid = 0;
            r = lm.sql("select MAX(Victim_ID) from Victim");
            try {
                if (r.next())
                    vid = r.getInt(1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            r = lm.sql("select MAX(Offender_ID) from Offender");
            try {
                if (r.next())
                    oid = r.getInt(1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String vic;
            if (F.isArmed())
                vic = "FATAL";
            else
                vic = "NON-FATAL";

            r = lm.sql("insert into Info_Rep(Rep_Fname,Rep_Lname,Rep_Age,Rep_Gender,Offender_ID,Victim_ID,Offense_ID,District,Location,Date_Reported,Date_Occured,case_status,vic_status,Report_type,Descr,officer_id,dat) values('" + tf9.getText() + "','" + tf10.getText() + "'," + Integer.parseInt(tf11.getText()) + ",'" + tf12.getText() + "'," + oid + "," + vid + ",'" + tf15.getText() + "'," + ThreadLocalRandom.current().nextInt(1, 8 + 1) + ",'" + tf14.getText() + "','" + re.getText() + "','" + oc.getText() + "','" + tf16.getText() + "','" + vic + "','" + tf17.getText() + "','" + tf13.getText() + "',"+tf18.getText()+",'" + re.getText() + "');");
            status.setText("Report Inserted Successfully at Report ID : " + vid);
        }
        else
        {
            String vic;
            if (F.isArmed())
                vic = "FATAL";
            else
                vic = "NON-FATAL";
            ResultSet r;
            r=lm.sql("update Victim set Fname='"+ tf5.getText() + "', Lname = '"+tf6.getText() + "',Age =" + Integer.parseInt(tf7.getText()) + ",Gender = '" + tf8.getText() + "' where Victim_id="+vi+";");
            r=lm.sql("update Offender set Fname='"+ tf1.getText() + "', Lname = '"+tf2.getText() + "',Age =" + Integer.parseInt(tf3.getText()) + ",Gender = '" + tf4.getText() + "' where Offender_id="+of+";");
            r=lm.sql("update Info_Rep set Rep_Fname='"+ tf9.getText() + "', Rep_Lname = '"+tf10.getText() + "',Rep_Age =" + Integer.parseInt(tf11.getText()) + ",Rep_Gender = '" + tf12.getText() + "',Location ='"+tf14.getText() + "',Date_Reported='"+re.getText() + "',Date_Occured='" + oc.getText() + "',case_status='" + tf16.getText() + "',vic_status='" + vic + "',Report_type='" + tf17.getText() + "',Descr='" + tf13.getText() + "', officer_id="+tf18.getText()+", dat='"+re.getText()+"' where Report_id="+Integer.parseInt(tf0.getText())+";");
            status.setText("Report Updated at Report_ID : "+tf0.getText());
            //retrieve();
        }
    }

    @FXML
    private void disable(){
        tf0.setEditable(false);
        ins.setVisible(true);
        ret.setVisible(false);
    }

    @FXML
    private void next(){
       if(!ext){
        nxt=true;
        bck=false;
        retrieve();}
        else
       {
           if(ri<reps.size())
           ri++;

           retrieve();
       }
    }

    @FXML
    private void previous(){
        if(!ext){bck=true;
        nxt=false;
        retrieve();}
        else
        {
            if(ri>0)
                ri--;

            retrieve();
        }
    }


    @FXML
    private void clear(){
        tf0.clear();
        tf1.clear();
        tf2.clear();
        tf3.clear();
        tf4.clear();
        tf5.clear();
        tf6.clear();
        tf7.clear();
        tf8.clear();
        tf9.clear();
        tf10.clear();
        tf11.clear();
        tf12.clear();
        tf13.clear();
        tf14.clear();
        tf15.clear();
        tf16.clear();
        tf17.clear();
        tf18.clear();
//        tf19.clear();
        tf20.clear();
        re.clear();
        oc.clear();
        //status.clear();
        rt=false;
    }
    @FXML
    private void enabl(){
        tf0.setEditable(true);
        ins.setVisible(false);
        ret.setVisible(true);
        clear();
    }
}
