package PDBMS;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Side;

import javafx.scene.chart.*;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.chart.PieChart.Data;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Graphs {
    //Crimes Reported in Years
    //Crimes Commited by Age Groups
    //Crimes Located in Districts PieChart
    //Crimes Commited by Gender Pie

    @FXML private PieChart pie;
    @FXML private ComboBox graph;
    public String gr,t1,t2,t3,t4,t5,t6,t7,t8;
    @FXML private StackedBarChart<String, Integer> bar;
    @FXML private CategoryAxis X;
    @FXML private NumberAxis Y;
    @FXML private AnchorPane ap;
    XYChart.Series<String, Integer> series;
    //XYChart.Series<String, Integer> series1;

    //Button loa;
            LoginManager lm;
    //final Label caption=new Label("");

    public void initStatus(final LoginManager l,PieChart p,ComboBox g,StackedBarChart b,CategoryAxis x,NumberAxis y,AnchorPane a)
    {
        pie=p;
        graph=g;
        bar=b;
        X=x;
        Y=y;
        ap=a;
        lm=l;
        ap.setVisible(false);
        ObservableList<String> data = FXCollections.observableArrayList();
        bar.setVisible(false);//age frps
        //years
        pie.setVisible(false);//district
        //third.setVisible(false);//gender
        t1="Crimes Reported in Years";
        data.add(t1);
        t2="Crimes Committed by Age Groups";
        data.add(t2);
        t3="Crimes Located in Districts";
        data.add(t3);
        t4="Crimes Committed by Gender";
        data.add(t4);
        t5="Tickets Issued in Years";
        data.add(t5);
        t6="Drunk alcohol Violators out of Total";
        data.add(t6);
        t7="Drunk alcohol Violators in Years";
        data.add(t7);
        t8="Crimes Reported in 2017 by Months";
        data.add(t8);
        graph.setItems(null);
        graph.setItems(data);

        graph.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String r) {
                gr=r;
                bar.setVisible(false);
                pie.setVisible(false);
                bar.getData().clear();
                bar.layout();
                pie.getData().clear();
                ap.setVisible(false);

            }
        });
       /* caption.setTextFill(Color.DARKORANGE);
        caption.setStyle("-fx-font: 24 arial;");
        for (final PieChart.Data data : sec.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                    e -> {
                        caption.setTranslateX(e.getSceneX());
                        caption.setTranslateY(e.getSceneY());
                        caption.setText(String.valueOf(data.getPieValue()) + "%");
                    });
        }*/
    }

    public void clean(){
        bar.setVisible(false);
        pie.setVisible(false);
        bar.getData().clear();
        bar.layout();
        pie.getData().clear();
        ap.setVisible(false);
    }

    @FXML
    public void load(){
        ap.setVisible(true);
        ExecutorService service = Executors.newFixedThreadPool(8);
        CountDownLatch latch= new CountDownLatch(1);

        try {

            if(gr.equals(t1))
            {
                bar.getData().clear();
                bar.layout();
                series=new XYChart.Series<>();
                service.submit(new Runnable() {
                    @Override
                    public void run() {
                        //prepare();
                        ResultSet r=LoginManager.sql("select year(dat),count(Report_id) from Info_Rep group by year(dat) order by year(dat);");
                        try {
                            while (r.next())
                            {
                                series.getData().add(new XYChart.Data<>(Integer.toString(r.getInt(1)),r.getInt(2)));


                            }

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        latch.countDown();
                    }
                });

                bar.setTitle(t1);
                latch.await();
                bar.getData().addAll(series);
                bar.setVisible(true);
                X.setLabel("Years");
                Y.setLabel("Number of Crimes");
            }
            else if(gr.equals(t2)){
                bar.getData().clear();
                bar.layout();
                series=new XYChart.Series<>();
                service.submit(new Runnable() {
                    @Override
                    public void run() {
                        prepare1();
                        latch.countDown();
                    }
                });

                bar.setTitle(t2);
                latch.await();
                bar.getData().addAll(series);
                bar.setVisible(true);
                X.setLabel("Age Groups");
                Y.setLabel("Number of Crimes");
            }
            else if(gr.equals(t3)){
                pie.getData().clear();
                pie.setTitle(t3);
                pie.setData(getChartData());
                pie.setLegendSide(Side.LEFT);
                pie.setLabelsVisible(false);
                pie.setVisible(true);
            }
            else if(gr.equals(t4)){
                pie.getData().clear();
                pie.setTitle(t4);
                pie.setData(getGenderData());
                pie.setLegendSide(Side.LEFT);
                pie.setLabelsVisible(false);
                pie.setVisible(true);
            }
            else if(gr.equals(t5)){
                bar.getData().clear();
                bar.layout();
                series=new XYChart.Series<>();
                service.submit(new Runnable() {
                    @Override
                    public void run() {
                        //prepare2();
                        ResultSet r=LoginManager.sql("select year(dat),count(violation_id) from ticket group by year(dat) order by year(dat);");
                        try {
                            while (r.next())
                            {
                                series.getData().add(new XYChart.Data<>(Integer.toString(r.getInt(1)),r.getInt(2)));


                            }

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        latch.countDown();
                    }
                });

                bar.setTitle(t5);
                latch.await();
                bar.getData().addAll(series);
                bar.setVisible(true);
                X.setLabel("Years");
                Y.setLabel("Number of Tickets");
            }
            else if(gr.equals(t6)){
                pie.getData().clear();
                pie.setTitle(t6);
                pie.setData(getGender1Data());
                pie.setLegendSide(Side.LEFT);
                pie.setLabelsVisible(false);
                pie.setVisible(true);
            }
            else if(gr.equals(t7)){
                bar.getData().clear();
                bar.layout();
                series=new XYChart.Series<>();
                service.submit(new Runnable() {
                    @Override
                    public void run() {
                        //prepare3();
                        boolean b=true;
                        ResultSet r=LoginManager.sql("select year(dat),count(violation_id) from ticket where alcohol='yes' group by year(dat) order by year(dat);");
                        try {
                            while (r.next())
                            {
                                if(b)
                                    b=false;
                                else
                                series.getData().add(new XYChart.Data<>(Integer.toString(r.getInt(1)),r.getInt(2)));


                            }

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        latch.countDown();
                    }
                });

                bar.setTitle(t7);
                latch.await();
                bar.getData().addAll(series);
                bar.setVisible(true);
                X.setLabel("Years");
                Y.setLabel("Number of Drunk Violators");
            }
            else if(gr.equals(t8)){
                bar.getData().clear();
                bar.layout();
                series=new XYChart.Series<>();
                service.submit(new Runnable() {
                    @Override
                    public void run() {
                        //prepare4();
                        ResultSet r=LoginManager.sql("select datename(month, dat),count(Report_id) from Info_Rep where year(dat)=2017 group by datename(month, dat);");
                        try {
                            while (r.next())
                            {
                                series.getData().add(new XYChart.Data<>(r.getString(1),r.getInt(2)));


                            }

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        latch.countDown();
                    }
                });

                bar.setTitle(t8);
                latch.await();
                bar.getData().addAll(series);
                bar.setVisible(true);
                X.setLabel("Months");
                Y.setLabel("Number of Crimes Reported");
            }
            else
                System.out.println("Invalid choice");


        } catch (Exception e){e.printStackTrace();}


    }

    @FXML
    private void back(){
        lm.showMainView();
    }

    private void prepare1(){
        int count;
        Map<String, Integer> n = new HashMap<String, Integer>();
        String sql;
        n.put("10 - 20",null);
        n.put("20 - 30",null);
        n.put("30 - 40",null);
        n.put("40 - 50",null);
        n.put("50 - 60",null);
        n.put("60 - 70",null);
        n.put("70 - 80",null);
        n.put("80 - 90",null);
        n.put("90 - 100",0);
        sql="select Age,count(Age) from Offender GROUP BY Age;";
        ResultSet r=LoginManager.sql(sql);

        try {
            while (r.next()) {
                if(r.getInt(1)<20){
                    String a="10 - 20";
                    Integer freq = n.get(a);
                    n.put(a, (freq == null) ? r.getInt(2) : freq + r.getInt(2));}

                if(r.getInt(1)>20 && r.getInt(1)<30){
                    String a="20 - 30";
                    Integer freq = n.get(a);
                    n.put(a, (freq == null) ? r.getInt(2) : freq + r.getInt(2));}

                if(r.getInt(1)>30 && r.getInt(1)<40){
                    String a="30 - 40";
                    Integer freq = n.get(a);
                    n.put(a, (freq == null) ? r.getInt(2) : freq + r.getInt(2));}

                if(r.getInt(1)>40 && r.getInt(1)<50){
                    String a="40 - 50";
                    Integer freq = n.get(a);
                    n.put(a, (freq == null) ? r.getInt(2) : freq + r.getInt(2));}

                if(r.getInt(1)>50 && r.getInt(1)<60){
                    String a="50 - 60";
                    Integer freq = n.get(a);
                    n.put(a, (freq == null) ? r.getInt(2) : freq + r.getInt(2));}

                if(r.getInt(1)>60 && r.getInt(1)<70){
                    String a="60 - 70";
                    Integer freq = n.get(a);
                    n.put(a, (freq == null) ? r.getInt(2) : freq + r.getInt(2));}

                if(r.getInt(1)>70 && r.getInt(1)<80){
                    String a="70 - 80";
                    Integer freq = n.get(a);
                    n.put(a, (freq == null) ? r.getInt(2) : freq + r.getInt(2));}

                if(r.getInt(1)>80 && r.getInt(1)<90){
                    String a="80 - 90";
                    Integer freq = n.get(a);
                    n.put(a, (freq == null) ? r.getInt(2) : freq + r.getInt(2));}

                if(r.getInt(1)>90 && r.getInt(1)<100){
                    String a="90 - 100";
                    Integer freq = n.get(a);
                    n.put(a, (freq == null) ? r.getInt(2) : freq + r.getInt(2));}


            }}catch (SQLException e){e.printStackTrace();}

        count=0;

        for (Map.Entry<String, Integer> entry : n.entrySet()) {
            String tmpString = entry.getKey();
            Integer tmpValue = entry.getValue();

            XYChart.Data<String, Integer> d = new XYChart.Data<>(tmpString, tmpValue);
            series.getData().add(d);
            count++;
            if (count == n.size()) {
                break;
            }
        }
    }

    private ObservableList<Data> getChartData() {
        ObservableList<Data> answer = FXCollections.observableArrayList();

        String sql="select District,count(Report_id) from Info_Rep GROUP BY District;";
        ResultSet r=LoginManager.sql(sql);
        try {
            while (r.next())
            {
                answer.add(new Data("District "+r.getInt(1),r.getInt(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return answer;
    }

    private ObservableList<Data> getGenderData() {
        ObservableList<Data> answer = FXCollections.observableArrayList();

        String sql="select Gender,count(Offender_id) from Offender GROUP BY Gender;";
        ResultSet r=LoginManager.sql(sql);
        try {
            while (r.next())
            {
                answer.add(new Data(r.getString(1),r.getInt(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return answer;
    }

    private ObservableList<Data> getGender1Data() {
        ObservableList<Data> answer = FXCollections.observableArrayList();

        String sql="select alcohol,count(violation_id) from ticket GROUP BY alcohol;";
        ResultSet r=LoginManager.sql(sql);
        try {
            while (r.next())
            {
                answer.add(new Data(r.getString(1),r.getInt(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return answer;
    }
}
