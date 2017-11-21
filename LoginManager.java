package PDBMS;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginManager {
    public String sid;
    public static Scene scene;
    public static Stage stage;
    public static Connection con=MSconnect.ConnectDB();
    public static String tp,nam;
    public static int ofid;
    private static double x = 0;
    private static double y = 0;
    public static Parent root = null;
    Image img = new Image(getClass().getResourceAsStream("back3.png"));
    public static ResultSet sql(String q)
    {
        try
        {
            PreparedStatement pst=con.prepareStatement(q);
            if(q.contains("select")||q.contains("Select")||q.contains("SELECT"))
                return pst.executeQuery();
            else
            {pst.execute();
                return null;}
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public LoginManager(Scene scene,Stage st) {
        this.scene = scene;
        stage=st;
    }

    public LoginManager(){}

    public void authenticated(String sessionID) {
        sid=sessionID;
        showMainView();

    }

    public void logout() {
        showLoginScreen();
    }

    public void showpublicview(){

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("PublicView.fxml")
            );
            Parent root = loader.load();
            Scene scene = new Scene(root, 1366, 720, Color.TRANSPARENT);
            //lm.stage.setScene(scene);
            Stage stage = new Stage();
            stage.setTitle("public view");

            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();
            stage.setWidth(bounds.getWidth());
            stage.setHeight(bounds.getHeight());
            stage.initStyle(StageStyle.TRANSPARENT);
           // stage.setMaximized(true);
            stage.setScene(scene);
            stage.show();
            mover1(root,stage);
            Publiccontroller controller =
                    loader.<Publiccontroller>getController();
            controller.initstatus(this,stage);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        /*
        //Publiccontroller p=new Publiccontroller(this);
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("PublicView.fxml")
            );
            root=loader.load();
            scene.setRoot(root);
            mover();

            Publiccontroller controller =
                    loader.<Publiccontroller>getController();
            controller.initstatus(this);
            //controller.loadss();
            //stage.sizeToScene();
        } catch (IOException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        Main.res();*/



    }

    double x1 = 0;
    double y1 = 0;
    public void mover1(Parent root,Stage stage){

        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                x1 = event.getSceneX();
                y1 = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - x1);
                stage.setY(event.getScreenY() - y1);
            }
        });

        ImagePattern pattern = new ImagePattern(img);


        scene.setFill(pattern);
    }




    public void showLoginScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("Login.fxml")
            );
        root=loader.load();
            scene.setRoot(root);
        mover();

            LoginController controller =
                    loader.<LoginController>getController();
            controller.initManager(this);
            //stage.sizeToScene();
        } catch (IOException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        Main.res();
    }

    public void showMainView() {
        //System.out.println("Main view func"+LoginManager.tp);
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("MainView.fxml")
            );
            root=loader.load();
            mover();
            scene.setRoot(root);
            MainView controller =
                    loader.<MainView>getController();
            controller.initSessionID(this);
            //stage.sizeToScene();
        } catch (IOException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        Main.res();
    }

    public void mover()
    {


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


        ImagePattern pattern = new ImagePattern(img);


        scene.setFill(pattern);
    }
}
