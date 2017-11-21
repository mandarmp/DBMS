package PDBMS;

//Should be loaded when admin clicks shift on the form


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Shiftview {

    @FXML
    private TextField shiftid;
    @FXML private TextField strt;
    @FXML private TextField end;
    @FXML private TextArea shifttdescrp;
    @FXML private Button back;
    Stage s;
    public void initStatus(final Stage l){
        s=l;
        ResultSet r;
        r=LoginManager.sql("select * from shiftview where shift_id= "+ Integer.parseInt(shiftid.getText()));
        try {
            if(r.next())
            {   //ins.setVisible(false);
                strt.setText(r.getString("start_time"));
                end.setText(r.getString("end_time"));
                shifttdescrp.setText(r.getString("shift_descrp"));
            }
            //  else

            // ins.setVisible(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getparam(String param)
    {
        shiftid.setText(param);
        shiftid.setEditable(false);
    }

    @FXML
    private void GoBackButton(){

        s.close();
    }

}
