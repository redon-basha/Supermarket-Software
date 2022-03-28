import Model.*;
import Utils.Analytics_Utils;
import Utils.Bills_Utils;
import Utils.Items;
import Utils.Product;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


public class cashier {
    double total = 0;
    double discount = 0;
    double grand_total = 0;
    int number_of_items = 0;
    Date data_of_creation;
    Date data_for_analytics;

    public void start(Stage primaryStage, Users user_at_moment) {
        Analytics_Utils analytics_utils = new Analytics_Utils();
        Items itm = new Items();
        ArrayList<ItemS> ITEAM = itm.getItems();
        Bills_Utils bill_to_create = new Bills_Utils();
        SplitPane splitPane = new SplitPane();
        TableView tableView = new TableView();
        TableColumn<String, ItemS> column1 = new TableColumn<>("Product Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("item_name"));
        TableColumn<String, ItemS> column2 = new TableColumn<>("Barcode");
        column2.setCellValueFactory(new PropertyValueFactory<>("barcode"));
        TableColumn<String, ItemS> column3 = new TableColumn<>("  Category");
        column3.setCellValueFactory(new PropertyValueFactory<>("category"));
        TableColumn<String, ItemS> column4 = new TableColumn<>("Selling Price");
        column4.setCellValueFactory(new PropertyValueFactory<>("selling_price"));
        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.getColumns().add(column3);
        tableView.getColumns().add(column4);

        tableView.setItems(FXCollections.observableArrayList(itm.getItems()));


        column1.prefWidthProperty().bind(tableView.widthProperty().multiply(0.25));
        column2.prefWidthProperty().bind(tableView.widthProperty().multiply(0.25));
        column3.prefWidthProperty().bind(tableView.widthProperty().multiply(0.25));
        column4.prefWidthProperty().bind(tableView.widthProperty().multiply(0.25));
        column1.setResizable(false);
        column2.setResizable(false);
        column3.setResizable(false);
        column4.setResizable(false);
        StackPane st1 = new StackPane(tableView);
        GridPane gp = new GridPane();
        gp.setHgap(150);
        gp.setVgap(10);
        Label t = new Label("Add Coupon");
        Label ti = new Label("                  ");
        TextField t1 = new TextField();
        Button add = new Button("Add");
        add.setAlignment(Pos.CENTER_RIGHT);
        VBox xx = new VBox(t1, add);
        HBox t3 = new HBox(t, xx);
        xx.setAlignment(Pos.TOP_RIGHT);
        gp.setPadding(new Insets(10, 10, 10, 10));
        gp.addRow(0, t, t1);
        gp.addRow(1, ti, xx);


        gp.setAlignment(Pos.TOP_RIGHT);


        TableView tab = new TableView();
        TableColumn<String, ItemS> col1 = new TableColumn<>("Product Name");
        col1.setCellValueFactory(new PropertyValueFactory<>("item_name"));
        TableColumn<String, ItemS> col2 = new TableColumn<>("Barcode");
        col2.setCellValueFactory(new PropertyValueFactory<>("barcode"));
        TableColumn<String, ItemS> col3 = new TableColumn<>("  Category");
        col3.setCellValueFactory(new PropertyValueFactory<>("category"));
        TableColumn<String, ItemS> col4 = new TableColumn<>("Selling Price");
        col4.setCellValueFactory(new PropertyValueFactory<>("selling_price"));

        tab.getColumns().add(col1);
        tab.getColumns().add(col2);
        tab.getColumns().add(col3);
        tab.getColumns().add(col4);

        col1.prefWidthProperty().bind(tableView.widthProperty().multiply(0.18));
        col2.prefWidthProperty().bind(tableView.widthProperty().multiply(0.2));
        col3.prefWidthProperty().bind(tableView.widthProperty().multiply(0.17));
        col4.prefWidthProperty().bind(tableView.widthProperty().multiply(0.17));


        Label b = new Label("Subtotal: $ 00    ");
        Label b2 = new Label("Discount: $ 00    ");
        Label b4 = new Label("__________________");
        Label b3 = new Label("Grand Total: $ 00    ");
        Button back = new Button("Back");
        Button Submit = new Button("Submit");
        HBox xet = new HBox(back, Submit);
        VBox zet = new VBox(b, b2, b4, b3, xet);
        zet.setAlignment(Pos.CENTER_RIGHT);
        VBox box = new VBox(gp, tab, zet);
        xet.setMargin(Submit, new Insets(0, 5, 5, 280));


//        VBox rightControl = new VBox(bet,zet);
//        GridPane mainPane = new GridPane();
//        mainPane.addColumn(0, t4, tab, zet);
//        mainPane.setAlignment(Pos.CENTER_RIGHT);
        splitPane.getItems().addAll(st1, box);
        splitPane.setDividerPositions(0.58);
        splitPane.getStyleClass().add("split-pane");

        tableView.setRowFactory(tv -> {
            TableRow<ItemS> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    ItemS rowData = row.getItem();
                    total = total + rowData.getSelling_price();
                    if (rowData.getQuanty() < 1) {
                        Alert al = new Alert(Alert.AlertType.WARNING, "We dont have that quantity in th warehouse for iteam " + rowData.getItem_name(), ButtonType.OK);
                        al.show();
                        return;
                    }
                    rowData.setQuanty(rowData.getQuanty() - 1);
                    itm.updateIteams();
                    grand_total = total - discount;
                    number_of_items = number_of_items + 1;
                    b3.setText("Grand Total: $ " + grand_total);
                    b.setText("Subtotal:  $ " + total);
                    tab.getItems().add(new ItemS(rowData.getItem_name(), rowData.getBarcode(), rowData.getCategory(), rowData.getSelling_price(), rowData.getQuanty()));
                }
            });
            return row;
        });


//        SplitPane split = new SplitPane();
//        VBox left = new VBox(new Label("left"));
//        left.setStyle("-fx-background-color: cadetblue");
//        VBox right = new VBox(new Label("right"));
//        right.setStyle("-fx-background-color: darkorange");
//        VBox center = new VBox(new Label("center"));
//        center.setStyle("-fx-background-color: darkgreen");
//        split.getItems().addAll(left, center, right);
//
//        split.setDividerPosition(0, 1 / (double) 3);
//        split.setDividerPosition(1, 2 / (double) 3);
        add.setOnAction(e -> {

            discount = Double.parseDouble(t1.getText());
            if(discount<0){
                discount=-discount;
            }
            else{

                Alert al = new Alert(Alert.AlertType.WARNING, "Wrong coupon format", ButtonType.OK);
                al.show();
                return;

            }
            grand_total = total - discount;
            b3.setText("Grand Total: $ " + grand_total);
            b2.setText("Discount: $ - " + discount);
        });
        Submit.setOnAction(e -> {
            bill_to_create.addBills(new Bills_Model(user_at_moment.getFirstName() + " " + user_at_moment.getLastName(), user_at_moment.getUsename(), number_of_items, total, discount, grand_total, data_of_creation = new Date()));
            analytics_utils.addAnalytics(new Analytics(user_at_moment.getUsename(), grand_total, 0, "Sales", data_for_analytics = new Date()));
            Alert al = new Alert(Alert.AlertType.CONFIRMATION, "Bill is created", ButtonType.OK);
            al.show();
            (new cashier()).start(primaryStage, user_at_moment);
        });
        back.setOnAction(e -> {
            (new Menu3()).start(primaryStage, user_at_moment);
        });

        Scene scene = new Scene(splitPane, 913, 512);
        scene.getStylesheets().addAll(this.getClass().getResource("Effects.css").toExternalForm());

        primaryStage.setScene(scene);


        primaryStage.show();

    }
}
