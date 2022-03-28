import Model.*;
import Utils.Bills_Utils;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import com.sun.prism.paint.Color;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Bills  {
int nr_of_actual_bills=0;
double total_income=0;
    public void start(Stage primaryStage, Users user_at_moment) {
        Bills_Utils bills_to_show=new Bills_Utils();
        ArrayList<Bills_Model> biills = bills_to_show.getBills();
        nr_of_actual_bills=biills.size();
        for(Bills_Model i:biills) {
            total_income += i.getGrand_total();

        }




        BorderPane pane1 =new BorderPane();
        TableView tableView = new TableView();
        TableColumn<String, Bills_Model> column1 = new TableColumn<>("Employee");
        column1.setCellValueFactory(new PropertyValueFactory<>("full_name"));
        TableColumn<String, Bills_Model> column2 = new TableColumn<>("Username");
        column2.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<String, Bills_Model> column3 = new TableColumn<>("Nr of items");
        column3.setCellValueFactory(new PropertyValueFactory<>("total_items"));

        TableColumn<String, Bills_Model> column4 = new TableColumn<>("Subtotal");
        column4.setCellValueFactory(new PropertyValueFactory<>("subtotal"));

        TableColumn<String, Bills_Model> column5 = new TableColumn<>("Discount");
        column5.setCellValueFactory(new PropertyValueFactory<>("discount"));

        TableColumn<String, Bills_Model> column6 = new TableColumn<>("Grand total");
        column6.setCellValueFactory(new PropertyValueFactory<>("grand_total"));

        TableColumn<String, Bills_Model> column7 = new TableColumn<>("Date created");
        column7.setCellValueFactory(new PropertyValueFactory<>("date"));





        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.getColumns().add(column3);
        tableView.getColumns().add(column4);
        tableView.getColumns().add(column5);
        tableView.getColumns().add(column6);
        tableView.getColumns().add(column7);



        column1.prefWidthProperty().bind(tableView.widthProperty().multiply(0.2));
        column2.prefWidthProperty().bind(tableView.widthProperty().multiply(0.15));
        column3.prefWidthProperty().bind(tableView.widthProperty().multiply(0.1));
        column4.prefWidthProperty().bind(tableView.widthProperty().multiply(0.1));
        column5.prefWidthProperty().bind(tableView.widthProperty().multiply(0.1));
        column6.prefWidthProperty().bind(tableView.widthProperty().multiply(0.1));
        column7.prefWidthProperty().bind(tableView.widthProperty().multiply(0.25));

        tableView.setItems(FXCollections.observableArrayList(bills_to_show.getBills()));
        ArrayList<Bills_Model> funi33 = biills;
        if(user_at_moment instanceof Cashier){
            funi33 = bills_to_show.getThat(user_at_moment.getUsename(), funi33);

            tableView.setItems(FXCollections.observableArrayList(funi33));
            total_income=0;
            nr_of_actual_bills=0;
            nr_of_actual_bills=funi33.size();
            for(Bills_Model i:biills) {
                total_income += i.getGrand_total();

            }

        }

        Button button2=new Button("Back");

        Label text1=new Label("Total bills: "+nr_of_actual_bills);
        Label text2=new Label("Total: "+total_income);
        VBox vb1=new VBox(text1,text2);
        vb1.setAlignment(Pos.BASELINE_RIGHT);


        HBox hb1=new HBox(button2);
        hb1.setMargin(button2, new Insets(10, 700, 10, 10));
        hb1.setAlignment(Pos.CENTER);
        HBox hb2=new HBox(hb1,vb1);
        hb2.setAlignment(Pos.CENTER);


        pane1.setCenter(tableView);
        pane1.setBottom(hb2);
        button2.setOnAction(e -> {
            (new Menu3()).start(primaryStage,user_at_moment);
        });


        Scene scene = new Scene(pane1,913,512);
        scene.getStylesheets().addAll(this.getClass().getResource("Effects.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
