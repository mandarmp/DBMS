package PDBMS;



import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.util.Callback;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Tables {
    @FXML private ObservableList<ObservableList> data= FXCollections.observableArrayList();
    @FXML private TableView tableview;
    @FXML public Label title;
    @FXML public JFXButton fir;
    //@FXML private ComboBox tabs;
    //@FXML private TextField tup;

    private LoginManager lm;
    Stage st;
    String qu;

    public void initStatus(final LoginManager l,Stage s,String q){
        st=s;
        lm=l;
        qu=q;
        tableview.setVisible(false);
        fetch();
        /*ObservableList<String> data = FXCollections.observableArrayList();
        ResultSet rs=l.sql("SELECT Distinct TABLE_NAME FROM information_schema.TABLES;");

        try {
            while (rs.next()) {

                data.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tabs.setItems(null);
        tabs.setItems(data);
        tabs.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {
                tname=t1;

            }
        });*/

    }

    public void buildtable(){
        String SQL;

        // if(tup.getText().equals(null)||Integer.parseInt(tup.getText())==0)
        //   SQL="select top (10) * from "+tname;
        // else
        SQL=qu;
        ResultSet rs = LoginManager.sql(SQL);
        try{
            /**********************************
             * TABLE COLUMN ADDED DYNAMICALLY *
             **********************************/
            for(int i=0 ; i<rs.getMetaData().getColumnCount()-1; i++){
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                tableview.getColumns().addAll(col);
                //System.out.println("Column ["+i+"] ");
            }

            /********************************
             * Data added to ObservableList *
             ********************************/
            while(rs.next()){
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                //System.out.println("Row [1] added "+row );
                data.add(row);

            }

            //FINALLY ADDED TO TableView
            tableview.setItems(data);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

    @FXML
    private void back(){
        st.close();
        InfoRep.ext=false;
    }

    @FXML
    private void getfirs(){
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("InfoRep.fxml")
            );
            Parent root = loader.load();
            Scene scene = new Scene(root, 1016, 723);
            //lm.stage.setScene(scene);
            Stage stage = new Stage();
            stage.setTitle("Information Report");
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
            mover(root,stage);
            InfoRep controller =
                    loader.<InfoRep>getController();
            controller.initStatus(null,stage);
            controller.repfetch(1);


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
    private void fetch(){
        buildtable();
        tableview.autosize();
        tableview.setVisible(true);
    }


}
