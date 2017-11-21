package PDBMS;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Officerlogin
{


    public int active,closed,total,offid;
    ResultSet r,s;

    //search function hoping it is common search by name.

    public void getofficer(int v)
    {
        offid=v;
        r=LoginManager.sql("select count(case_status) from Info_Rep where case_status='OPEN' and officer_id="+offid+"");
        s=LoginManager.sql("select count(case_status) from Info_Rep where case_status='CLOSED' and officer_id="+offid+"");
        try {
            if(r.next())
            active=Integer.parseInt(r.getString(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(s.next())
            closed=Integer.parseInt(s.getString(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        total=(active+closed);

        // set the textfields int the fxml somehow using this above computed acive closed and total
    }
    public int getactive()
    {
        return  active;
    }
    public int getclosed()
    {
        return closed;
    }

    public int gettotal()
    {
        return  total;
    }


    public ObservableList<PieChart.Data> setPIEchart() {
        ObservableList<PieChart.Data> answer = FXCollections.observableArrayList();


                answer.add(new PieChart.Data("OPEN",active));
                answer.add(new PieChart.Data("CLOSED",closed));


        return answer;
    }
    /*
    public PieChart setPIEchart()
    {
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("OPEN", active),
                        new PieChart.Data("CLOSED",closed));
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Case status");
        return chart;
        //int the just add the chart through object of this class
        //something like this ((Group) scene.getRoot()).getChildren().add(obj.setPIEchart);
    }*/

}


