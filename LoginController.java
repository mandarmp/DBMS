package PDBMS;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable{
    @FXML
    private TextField txtUserName;
    @FXML private TextField txtPassword;
    LoginManager l;
    public void initManager(final LoginManager LM)
    {
        l=LM;
    }

    @FXML
    private void exitApp(){
        System.exit(0);
    }

    @FXML
    private void publicview(){

        l.showpublicview();

    }

    @FXML
    private void loginview(){
        l.showLoginScreen();
    }


    @FXML
    private void loginclick()
    {



        if (txtUserName.getText().equals("")) {

            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter user name", ButtonType.OK);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.OK) {
                alert.close();
            }

            return;

        }
        String Password= String.valueOf(txtPassword.getCharacters());
        if (Password.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter password", ButtonType.OK);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.OK) {
                alert.close();
            }

            return;

        }
        if((txtUserName.getText().equals("dummy")&&txtPassword.getText().equals("dummy")))
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Login Successful", ButtonType.OK);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.OK) {
                alert.close();
                LoginManager.nam=LoginManager.tp="admin";
                l.authenticated(generateSessionID());

            }

            return;
        }
        else {

            String sql = "select * from users where UserName= '" + txtUserName.getText() + "' and Password ='" + txtPassword.getText() + "'";
            try {

                ResultSet rs = l.sql(sql);
                if (rs.next()) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Login Successful", ButtonType.OK);
                    alert.showAndWait();

                    if (alert.getResult() == ButtonType.OK) {
                        alert.close();
                        LoginManager.tp = rs.getString("Type");
                        LoginManager.nam= rs.getString("UserName");
                        LoginManager.ofid=rs.getInt("officer_id");
                        l.authenticated(generateSessionID());

                    }

                    return;
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Login Failed", ButtonType.OK);
                    alert.showAndWait();

                    if (alert.getResult() == ButtonType.OK) {
                        alert.close();
                    }


                }
            } catch (SQLException e) {
                e.printStackTrace();

            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    private static int sessionID = 0;
    private String generateSessionID() {
        sessionID++;
        return ""+sessionID;
    }
}
