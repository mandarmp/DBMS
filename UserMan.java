package PDBMS;


import javafx.fxml.FXML;

import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMan {
    @FXML private TextField tf0;
    @FXML private TextField tf1;
    @FXML private TextField tf2;
    @FXML private TextField tf3;
    private TextField tf01;

    LoginManager lm;

    public void initStatus(final LoginManager l,TextField a,TextField b,TextField c,TextField d,TextField e) {

        lm = l;
        tf0=a;
        tf1=b;
        tf2=c;
        tf3=d;
        tf01=e;
        tf3.setText("User Management");
    }

    @FXML
    public void create(){
        ResultSet r;
        r=LoginManager.sql("select * from users where UserName='"+tf0.getText()+"'");
        try {
            if(r.next())
            {
                tf3.setText("User Already Exists! Try Update User if you want to change Password or User Name");
            }
            else
            {
                if(tf1.getText().equals(tf2.getText()))
                {
                    r=LoginManager.sql("Insert into users values('"+tf0.getText()+"','"+tf1.getText()+"','officer',"+tf01.getText()+");");
                    tf3.setText("Created Successfully!");
                }
                else
                    tf3.setText("Error! Passwords Don't match");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void update(){
        ResultSet r;
        r=LoginManager.sql("select * from users where UserName='"+tf0.getText()+"'");
        try {
            if(r.next())
            {
                if(tf1.getText().equals(tf2.getText())){
                    r=LoginManager.sql("update users set Password='"+tf1.getText()+"' where UserName='"+tf0.getText()+"';");
                    tf3.setText("Updated Successfully!");
                }
                else
                    tf3.setText("Error! Passwords Don't match");
            }
            else
            {
                tf3.setText("User Does not Exist! Try Create User if you want to Create new User");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void delete(){
        ResultSet r;
        r=LoginManager.sql("select * from users where UserName='"+tf0.getText()+"'");
        try {
            if(r.next())
            {
                if(tf1.getText().equals(tf2.getText())){
                    r=LoginManager.sql("delete from users where UserName='"+tf0.getText()+"';");
                    tf3.setText("Deleted Successfully!");
                }
                else
                    tf3.setText("Error! Passwords Don't match");
            }
            else
            {
                tf3.setText("User Does not Exist! Try Create User if you want to Create new User");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void back(){
        lm.showMainView();
    }
}
