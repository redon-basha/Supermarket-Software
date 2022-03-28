import Model.*;
import Utils.Items;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Menu3  {


    public void start(Stage Stageo, Users user_at_moment) {
        Items it=new Items();
        ArrayList<ItemS> ITEAM = it.getItems();
        Label WARNING=new Label("Warning there are items in Warehouse that have quantity less than 5");
        WARNING.setFont(Font.font("Book Antiqua",
                FontWeight.BOLD, 13));
        WARNING.setVisible(false);
        WARNING.getStyleClass().add("text2");
        MenuBar menuBar = new MenuBar();
        Label logoutLab = new Label("Log Out");

        Menu menu1 = new Menu("Welcome User: " + user_at_moment.getFirstName() + " " + user_at_moment.getLastName());
        MenuItem menuItem1 = new MenuItem("  POS");
        menuItem1.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                (new cashier()).start(Stageo, user_at_moment);
            }
        });
        MenuItem menuItem2 = new MenuItem("  Bills");
        menuItem2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                (new Bills()).start(Stageo, user_at_moment);
            }
        });
        MenuItem menuItem3 = new MenuItem("  Supplier");
        menuItem3.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                (new SupplierView()).start(Stageo, user_at_moment);
            }
        });
        MenuItem menuItem4 = new MenuItem("  Warehouse");
        menuItem4.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                (new Warehouse()).start(Stageo, user_at_moment);
            }
        });
        MenuItem menuItem5 = new MenuItem("  Users");
        menuItem5.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                (new AddUser()).start(Stageo, user_at_moment);
            }
        });

        Menu menu2 = new Menu("                                                                                                                                                                                                   ");
        Menu menu3 = new Menu("", logoutLab);
        menu3.setOnAction(e -> {
            Stageo.close();
        });
        menu2.setDisable(true);
        menu1.setDisable(false);
        if (user_at_moment instanceof Admin) {
            for (ItemS i:ITEAM)
            {
                if(i.getQuanty()<5){WARNING.setVisible(true);}

            }
            menu1.getItems().add(menuItem1);
            menu1.getItems().add(menuItem2);
            menu1.getItems().add(menuItem3);
            menu1.getItems().add(menuItem4);
            menu1.getItems().add(menuItem5);
            Button button1 = new Button("Warehouse");
            Button button2 = new Button("Bills");
            Button button3 = new Button("Supliers");
            Button button4 = new Button("Pos");
            Button button5 = new Button("Users");
            Button button6 = new Button("Analytics");
            HBox hb1 = new HBox(button1, button2, button3);
            HBox hb2 = new HBox(button6, button4, button5);
            hb1.setSpacing(10);
            hb2.setSpacing(10);

            VBox vb1 = new VBox(hb1, hb2,WARNING);
            hb1.setMargin(button1, new Insets(310, 50, 10, 60));
            hb1.setMargin(button2, new Insets(310, 50, 10, 10));
            hb1.setMargin(button3, new Insets(310, 50, 10, 10));
            hb1.setMargin(button4, new Insets(10, 50, 10, 10));
            hb1.setMargin(button5, new Insets(10, 40, 10, 20));
            hb1.setMargin(button6, new Insets(10, 60, 10, 150));
            vb1.setMargin(WARNING, new Insets(60, 0, 10, 30));
            button1.getStyleClass().add("buttona");
            button2.getStyleClass().add("buttona");
            button3.getStyleClass().add("buttona");
            button4.getStyleClass().add("buttona");
            button5.getStyleClass().add("buttona");
            button6.getStyleClass().add("buttona");
            for (ItemS i:ITEAM)
            {
                if(i.getQuanty()<5){WARNING.setVisible(true);}

            }

            BorderPane pane2 = new BorderPane();
            pane2.setCenter(vb1);
            menuBar.getMenus().addAll(menu1, menu2, menu3);
            menuBar.prefWidthProperty().bind(Stageo.widthProperty());
            pane2.setTop(menuBar);
            pane2.setStyle("-fx-background-image: url('image/kot1.jpg');"
                    + "-fx-background-repeat: stretch;");
            Scene scene_e_pare = new Scene(pane2, 913, 512);
            scene_e_pare.getStylesheets().addAll(this.getClass().getResource("Effects.css").toExternalForm());

        button1.setOnAction(e -> {
            (new Warehouse()).start(Stageo, user_at_moment);
        });
        button2.setOnAction(e -> {
            (new Bills()).start(Stageo, user_at_moment);
        });
        button3.setOnAction(e -> {
            (new SupplierView()).start(Stageo, user_at_moment);
        });
        button4.setOnAction(e -> {
            (new cashier()).start(Stageo, user_at_moment);
        });
        button5.setOnAction(e -> {
            (new AddUser()).start(Stageo, user_at_moment);
        });
            button6.setOnAction(e -> {
                (new AnalyticsView()).start(Stageo, user_at_moment);
            });

        logoutLab.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                (new login()).start(Stageo);

            }
        });
        Stageo.setScene(scene_e_pare);
    }
        if(user_at_moment instanceof Economist){
            for (ItemS i:ITEAM)
                  {
                      if(i.getQuanty()<5){WARNING.setVisible(true);}

            }
            menu1.getItems().add(menuItem4);

            menu1.getItems().add(menuItem2);

            menu1.getItems().add(menuItem3);

            Button button1=new Button("Warehouse");
            Button button2=new Button("Bills");
            Button button3=new Button("Supliers");
            HBox hb1=new HBox(button1,button2,button3);
            VBox vb1=new VBox(hb1,WARNING);
            vb1.setMargin(WARNING, new Insets(220, 70, 10, 30));
            hb1.setSpacing(10);
            hb1.setAlignment(Pos.CENTER);
            hb1.setMargin(button1, new Insets(200, 70, 10, 240));
            hb1.setMargin(button2, new Insets(200, 80, 10, 10));
            hb1.setMargin(button3, new Insets(200, 70, 10, 10));
            button1.getStyleClass().add("buttone");
            button2.getStyleClass().add("buttone");
            button3.getStyleClass().add("buttone");
            BorderPane pane2 =new BorderPane();
            pane2.setCenter(vb1);
            menuBar.getMenus().addAll(menu1,menu2,menu3);
            menuBar.prefWidthProperty().bind(Stageo.widthProperty());
            pane2.setTop(menuBar);
            pane2.setStyle("-fx-background-image: url('image/eco.jpg');"
                    + "-fx-background-repeat: stretch;");
            Scene scene_e_dyte = new Scene(pane2, 913, 512);
            scene_e_dyte.getStylesheets().addAll(this.getClass().getResource("Effects.css").toExternalForm());

            button1.setOnAction(e -> {
                (new Warehouse()).start(Stageo, user_at_moment);
            });
            button2.setOnAction(e -> {
                (new Bills()).start(Stageo, user_at_moment);
            });
            button3.setOnAction(e -> {
                (new SupplierView()).start(Stageo, user_at_moment);
            });

            logoutLab.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {

                    (new login()).start(Stageo);

                }
            });
            Stageo.setScene(scene_e_dyte);




        }
        if(user_at_moment instanceof Cashier){
            menu1.getItems().add(menuItem1);

            menu1.getItems().add(menuItem2);


            Button button1=new Button("POS");
            button1.getStyleClass().add("buttonc");
            Button button2=new Button("Bills");
            button2.getStyleClass().add("buttonc");
            HBox hb1=new HBox(button1,button2);
            hb1.setSpacing(150);
            hb1.setAlignment(Pos.CENTER);

            BorderPane pane2 =new BorderPane();
            pane2.setCenter(hb1);
            menuBar.getMenus().addAll(menu1,menu2,menu3);
            menuBar.prefWidthProperty().bind(Stageo.widthProperty());
            pane2.setTop(menuBar);
            pane2.setStyle("-fx-background-image: url('image/3299936.jpg');"
                    + "-fx-background-repeat: stretch;");
            Scene scene_e_trete = new Scene(pane2, 913, 512);
            scene_e_trete.getStylesheets().addAll(this.getClass().getResource("Effects.css").toExternalForm());
            logoutLab.setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent e) {

                    (new login()).start(Stageo);

                }
            });
            Stageo.setScene(scene_e_trete);
            button1.setOnAction(e -> {
                (new cashier()).start(Stageo, user_at_moment);
            });
            button2.setOnAction(e -> {
                (new Bills()).start(Stageo, user_at_moment);
            });
        }
        Stageo.show();

    }
}
