import Model.ItemS;
import Model.ProducT;
import Model.Users;
import Utils.Items;
import Utils.Product;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Warehouse {


    public void start(Stage primaryStage, Users user_at_moment) {

        Items itm = new Items();
        ArrayList<ItemS> ITEAM = itm.getItems();

        BorderPane pane1 = new BorderPane();
        TableView tableView = new TableView();

        TableColumn<String, ItemS> column1 = new TableColumn<>("Product Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("item_name"));
        TableColumn<String, ItemS> column2 = new TableColumn<>("Barcode");
        column2.setCellValueFactory(new PropertyValueFactory<>("barcode"));
        TableColumn<String, ItemS> column3 = new TableColumn<>("  Category");
        column3.setCellValueFactory(new PropertyValueFactory<>("category"));
        TableColumn<String, ItemS> column4 = new TableColumn<>("Selling Price");
        column4.setCellValueFactory(new PropertyValueFactory<>("selling_price"));
        TableColumn<String, ItemS> column5 = new TableColumn<>("     Quanty");
        column5.setCellValueFactory(new PropertyValueFactory<>("quanty"));

        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.getColumns().add(column3);
        tableView.getColumns().add(column4);
        tableView.getColumns().add(column5);

        // tableView.getItems().add(itm.getItems());
        tableView.setItems(FXCollections.observableArrayList(itm.getItems()));

        column1.prefWidthProperty().bind(tableView.widthProperty().multiply(0.25));
        column2.prefWidthProperty().bind(tableView.widthProperty().multiply(0.25));
        column3.prefWidthProperty().bind(tableView.widthProperty().multiply(0.25));
        column4.prefWidthProperty().bind(tableView.widthProperty().multiply(0.125));
        column5.prefWidthProperty().bind(tableView.widthProperty().multiply(0.125));

        Button button1 = new Button("Add Product");
        Button button2 = new Button("Add Categoty");
        Button button3 = new Button(" Back ");
        Label text1 = new Label("Total bils: 1");
        Label text2 = new Label("Total: 23$");
        VBox vb1 = new VBox(text1, text2);
        vb1.setAlignment(Pos.BASELINE_RIGHT);


        HBox hb1 = new HBox(button1, button3);
        hb1.setMargin(button1, new Insets(10, 20, 10, 600));
        hb1.setMargin(button2, new Insets(10, 20, 10, 10));
        hb1.setMargin(button3, new Insets(10, 20, 10, 10));
        hb1.setAlignment(Pos.CENTER);
        HBox hb2 = new HBox(hb1, vb1);
        hb2.setAlignment(Pos.CENTER_RIGHT);


        pane1.setCenter(tableView);
        pane1.setBottom(hb1);
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Label lbl_barcode = new Label("Product Name");
                Label lbl_name = new Label("Barcode");
                Label lbl_category = new Label("Category");
                Label lbl_price = new Label("Selling Price");
                TextField input_barcode = new TextField();
                TextField input_name = new TextField();
                TextField input_category = new TextField();
                TextField input_price = new TextField();
                Label lbl_quanty = new Label("Quantity");
                TextField input_quanty = new TextField();
                Button add_product = new Button("Add Product");
                VBox vb1 = new VBox(lbl_barcode, input_barcode, lbl_name, input_name);
                VBox vb2 = new VBox(lbl_category, input_category, lbl_price, input_price);
                HBox hb1 = new HBox(vb1, vb2);
                HBox hb2 = new HBox(lbl_quanty);
                HBox hb3 = new HBox(input_quanty);
                VBox vb5 = new VBox(hb2, hb3);

                VBox vb3 = new VBox(hb1, add_product);
                hb1.setMargin(vb1, new Insets(10, 10, 10, 35));
                hb1.setMargin(vb2, new Insets(10, 10, 10, 35));
                vb3.setMargin(vb5, new Insets(5, 10, 10, 130));
                vb3.setMargin(add_product, new Insets(10, 10, 10, 170));


                // product.add(new ProducT(input_barcode.getText(), input_name.getText(), input_category.getText(),((double price=Double.parseDouble(input_price.getText());
                StackPane secondaryLayout = new StackPane();
                secondaryLayout.getChildren().add(vb3);

                Scene secondScene = new Scene(secondaryLayout, 400, 200);

                Stage secondStage = new Stage();
                secondStage.setTitle("Second Stage");
                secondStage.setScene(secondScene);

                //Set position of second window, related to primary window.
                secondStage.setX(primaryStage.getX() + 250);
                secondStage.setY(primaryStage.getY() + 100);

                secondStage.show();
                add_product.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        try {
                            Double.parseDouble(input_price.getText());
                        } catch (Exception pjone11) {
                            Alert al = new Alert(Alert.AlertType.WARNING, "Wrong price format", ButtonType.OK);
                            al.show();
                            return;
                        }
                        for (ItemS i : ITEAM) {
                            if (i.getBarcode().equals(input_barcode.getText())) {
                                Alert all = new Alert(Alert.AlertType.WARNING, "Barcode already exist", ButtonType.OK);
                                all.show();
                                return;
                            }

                        }
                        itm.addItems(new ItemS(input_barcode.getText(), input_name.getText(), input_category.getText(), Double.parseDouble(input_price.getText()), 0));
                        (new Warehouse()).start(primaryStage, user_at_moment);
                        secondStage.close();
                    }
                });

            }
        });
        button3.setOnAction(e -> {
            (new Menu3()).start(primaryStage, user_at_moment);
        });
        Scene scene = new Scene(pane1, 913, 512);
        scene.getStylesheets().addAll(this.getClass().getResource("Effects.css").toExternalForm());


        primaryStage.setScene(scene);
        primaryStage.show();
    }
}



