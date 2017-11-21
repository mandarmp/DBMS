package PDBMS;


//Should be loaded when admin clicks department code on the form


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Deptview {
    @FXML private TextField deptcode;
    @FXML private TextField deptname;
    @FXML private TextArea deptdescrp;

    @FXML private Button back;
    Stage s;
    public void initStatus(final Stage l){
        s=l;
        ResultSet r=LoginManager.sql("select * from deptview where dept_code="+Integer.parseInt(deptcode.getText())+"");
        try {
            if (r.next()) {
                // deptcode.setText("" + r.getInt("dept_code"));
                deptname.setText(r.getString("dept_name"));
                deptdescrp.setText(r.getString("dept_descrp"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void getparam(String param)
    {
        deptcode.setText(param);
        deptcode.setEditable(false);
    }
   /* @FXML
    private void ret()
    {
        ResultSet r=LoginManager.sql("Select * from department where dept_code="+Integer.parseInt(deptcode.getText())+"");
        try {
            if (r.next()) {
                deptcode.setText("" + r.getInt("dept_code"));
                deptname.setText(r.getString("dept_name"));
                deptdescrp.setText(r.getString("dept_descrp"));
            }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    }*/
   @FXML
   private void GoBackButton(){
       // get a handle to the stage

       s.close();
   }


}
