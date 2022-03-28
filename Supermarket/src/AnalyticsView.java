import Model.Analytics;
import Model.Bills_Model;
import Model.ItemS;
import Model.Users;
import Utils.Analytics_Utils;
import Utils.Bills_Utils;
import Utils.RWUser;
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

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AnalyticsView {
    int nr_of_actual_bills = 0;
    double total_income = 0;

    public void start(Stage primaryStage, Users user_at_moment) {

        RWUser rw = new RWUser();
        ArrayList<Users> us = rw.getUsers();


        Analytics_Utils analytics_utils = new Analytics_Utils();
        ArrayList<Analytics> analitics_list = analytics_utils.getAnalytics();


        BorderPane pane1 = new BorderPane();
        TableView tableView = new TableView();
        TableColumn<String, Analytics> column1 = new TableColumn<>("Username");
        column1.setCellValueFactory(new PropertyValueFactory<>("username"));
        TableColumn<String, Analytics> column2 = new TableColumn<>("Debit");
        column2.setCellValueFactory(new PropertyValueFactory<>("debit"));

        TableColumn<String, Analytics> column3 = new TableColumn<>("Credit");
        column3.setCellValueFactory(new PropertyValueFactory<>("credit"));

        TableColumn<String, Analytics> column4 = new TableColumn<>("Voucher type");
        column4.setCellValueFactory(new PropertyValueFactory<>("voucher_name"));

        TableColumn<String, Analytics> column5 = new TableColumn<>("Date");
        column5.setCellValueFactory(new PropertyValueFactory<>("date_of_action"));


        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.getColumns().add(column3);
        tableView.getColumns().add(column4);
        tableView.getColumns().add(column5);


        column1.prefWidthProperty().bind(tableView.widthProperty().multiply(0.2));
        column2.prefWidthProperty().bind(tableView.widthProperty().multiply(0.19));
        column3.prefWidthProperty().bind(tableView.widthProperty().multiply(0.19));
        column4.prefWidthProperty().bind(tableView.widthProperty().multiply(0.19));
        column5.prefWidthProperty().bind(tableView.widthProperty().multiply(0.23));


        tableView.setItems(FXCollections.observableArrayList(analitics_list));
        Button button1 = new Button("Filter");
        Button button2 = new Button("Back");
        ComboBox combo1 = new ComboBox();
        combo1.getItems().addAll("Type", "Sales", "Purchase");
        combo1.getSelectionModel().selectFirst();
        ComboBox combo = new ComboBox();
        combo.getItems().add("User");
        combo.getSelectionModel().selectFirst();
        for (Users i : us) {
            combo.getItems().add(i.getUsename());
        }
        DatePicker From_date = new DatePicker();
        From_date.setPromptText(" From date ");
        DatePicker To_date = new DatePicker();
        To_date.setPromptText(" To date ");

         Label fitore=new Label("Balance");
        fitore.setFont(Font.font("Book Antiqua",
                FontWeight.BOLD, 14));
        for(Analytics i:analitics_list)
        {
            total_income=total_income+i.getDebit()-i.getCredit();
        }
        if(total_income>0) {
            fitore.setText("Balance: $ " + total_income);
            fitore.getStyleClass().add("error1");
        }
        else if(total_income==0) {
            fitore.setText("Balance: $ " + total_income);
            fitore.getStyleClass().add("text1");
        }
        else {
            fitore.setText("Balance: $ -" + total_income);
            fitore.getStyleClass().add("error2");
        }
        HBox hb10 = new HBox(combo, combo1, From_date, To_date);
        hb10.setSpacing(100);
        HBox hb1 = new HBox(button1, button2);
        hb1.setMargin(button1, new Insets(10, 10, 10, 10));
        hb1.setMargin(button2, new Insets(10, 650, 10, 10));
        HBox hb2 = new HBox(hb1,fitore);
        hb1.setMargin(fitore, new Insets(0, 30, 0, 0));


        pane1.setTop(hb10);
        pane1.setCenter(tableView);
        pane1.setBottom(hb2);
        button2.setOnAction(e -> {
            (new Menu3()).start(primaryStage, user_at_moment);
        });
        button1.setOnAction(e -> {
            String usenname_choosen = combo.getValue().toString();
            String type_choosen = combo1.getValue().toString();

            Date from = null;
            if(From_date.getValue() !=null){
                Instant from_date_instant = Instant.from(From_date.getValue().atStartOfDay(ZoneId.of("GMT")));
                from = java.sql.Date.from(from_date_instant);
            }

            Date to = null;
            if(To_date.getValue() != null){
                Instant to_date_instant = Instant.from(To_date.getValue().atStartOfDay(ZoneId.of("GMT")));
                to = java.sql.Date.from(to_date_instant);
            }

            ArrayList<Analytics> filtered_analytics = analitics_list;

            if(!usenname_choosen.equals("User")) {
                filtered_analytics = analytics_utils.getbyUsername(usenname_choosen, filtered_analytics);
            }

            if(!type_choosen.equals("Type")) {
                filtered_analytics = analytics_utils.getbyType(type_choosen, filtered_analytics);
            }

            if(from != null){
                filtered_analytics = analytics_utils.getFromDate(from, filtered_analytics);
            }

            if(to != null){
                filtered_analytics = analytics_utils.getToDate(to, filtered_analytics);
            }

            tableView.setItems(FXCollections.observableArrayList(filtered_analytics));

            Double total = 0.00;

            for(Analytics i: filtered_analytics){
                total = total + (i.debit - i.credit);
            }

        });


        Scene scene = new Scene(pane1, 913, 512);
        scene.getStylesheets().addAll(this.getClass().getResource("Effects.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}