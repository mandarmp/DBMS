package PDBMS;

import com.jfoenix.controls.JFXButton;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Publiccontroller {



    LoginManager lm;

    @FXML
    private TextField chief;
    @FXML
    private TextField  count;
    @FXML
    private TextField  actc;
    @FXML
    private TextField  trafcnt;
    @FXML
    private TextField pend;

    @FXML
    private AnchorPane ap;

    @FXML
    private JFXButton ld;

    @FXML
    private PieChart pie;

    ComboBox b=new ComboBox();

    @FXML private StackedBarChart<String, Integer> bar;
    @FXML private CategoryAxis X;
    @FXML private NumberAxis Y;

    Timeline five=new Timeline(new KeyFrame(Duration.seconds(5), new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            ld.fire();
        }
    }));

    Stage s;

    public void initstatus(LoginManager l,Stage ss) {
s=ss;
        lm = l;
        ResultSet r, s, t, u, v;
        try {
            r = LoginManager.sql("select count(officer_id) from officer where rnk like '%traffic%'");
            if (r.next())
                trafcnt.setText(r.getString(1));
            s = LoginManager.sql("select count(officer_id) from officer");
            if (s.next()) {
                int cnt = (Integer.parseInt(s.getString(1)) - Integer.parseInt(trafcnt.getText()));
                count.setText("" + cnt);
            }
            t = LoginManager.sql("select count(case_status) from Info_Rep where case_status='OPEN'");
            if (t.next())
                actc.setText(t.getString(1));
            u = LoginManager.sql("select count(t.violation_id ) from ticket t where t.ticket_status='pending'");
            if (u.next())
                pend.setText(u.getString(1));

            v = LoginManager.sql("select officer_fname,officer_lname from officer where officer.rnk='SUPERINTENDENT OF POLICE'");
            if (v.next()) {
                String str = v.getString("officer_fname") + " " + v.getString("officer_lname");
                chief.setText(str);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        five.setCycleCount(Timeline.INDEFINITE);

    }

    /*
    public class Reminder {
        Timer timer;

        public Reminder(int seconds) {
            timer = new Timer();
            timer.schedule(new RemindTask(), seconds * 1000);
        }

        class RemindTask extends TimerTask {
            public void run() {
                System.out.println("Time's up!");
                latch.countDown();
                timer.cancel(); //Terminate the timer thread

            }
        }
    }

        class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            System.out.println("Timer task started at:"+new Date());
            completeTask();
            System.out.println("Timer task finished at:"+new Date());
        }

        private void completeTask() {
            try {



            } catch (Exception e) {
                e.printStackTrace();
            }
        }}
        CountDownLatch latch= new CountDownLatch(1);*/

    int j=0;
    boolean first=true;

        @FXML
        public void loadss(){

            if(first)
            {
                five.play();
                first=false;
            }

        /*TimerTask timerTask = new MyTimerTask();
        running timer task as daemon thread
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(timerTask, 0, 10*1000);*/
        Graphs g = new Graphs();
        g.initStatus(lm, pie, b, bar, X, Y, ap);

        switch (j)
        {
            case 0:{
                g.gr=g.t1;
                g.clean();
                g.load();
                break;
            }
            case 1:{
                g.gr=g.t2;
                g.clean();
                g.load();

                break;
            }
            case 2:{
                g.gr=g.t3;
                g.clean();
                g.load();

                break;
            }
            case 3:{
                g.gr=g.t4;
                g.clean();
                g.load();

                break;
            }
            case 4:{
                g.gr=g.t5;
                g.clean();
                g.load();


                break;
            }
            case 5:{
                g.gr=g.t6;
                g.clean();
                g.load();

                break;
            }
            case 6:{g.gr=g.t7;
                g.clean();
                g.load();

                break;
            }
            case 7:{
                g.gr=g.t8;
                g.clean();
                g.load();

                break;
            }

        }
            if(j<7)
            j++;
            else
                j=0;
       // ExecutorService service = Executors.newFixedThreadPool(8);
        /*Task<Void> task = new Task<Void>() {

            @Override protected Void call() throws Exception {

                    Platform.runLater(new Runnable() {
                        @Override public void run() {



                        }
                    });

                return null;
            }
        };

        //service.submit(task);
        new Thread(task).start();*/

    }

    @FXML
    private void back(){
            s.close();
            first=true;
        //lm.showLoginScreen();
    }


}
