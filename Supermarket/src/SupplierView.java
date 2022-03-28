import Model.Analytics;
import Model.ItemS;
import Model.ProducT;
import Model.Users;
import com.sun.javafx.util.Utils;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import Utils.Supplier;
import Utils.Product;
import Utils.Items;

import java.util.ArrayList;
import java.util.Date;
import Utils.Analytics_Utils;
public class SupplierView {



    String selectedSupplier = null;
    String selectbarcode = null;
   Date data_for_analytics;

    public void start(Stage primaryStage, Users user_at_moment) {
        Items items = new Items();
        Analytics_Utils analytics_utils=new Analytics_Utils();
        ArrayList<ItemS> ITEAM = items.getItems();
        Product product = new Product();
        SplitPane pane1 = new SplitPane();
        Product prduct = new Product();
        TableView tableView = new TableView();
        TableColumn<String, String> column1 = new TableColumn<>("Supplier");
        column1.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue()));
        tableView.getColumns().add(column1);

        Supplier supplier = new Supplier();
        ObservableList<String> supplier_list = FXCollections.observableArrayList(supplier.getSuppliers());
        tableView.setItems(supplier_list);
        column1.prefWidthProperty().bind(tableView.widthProperty().multiply(1));


        TableView tab = new TableView();
        TableColumn<String, ProducT> col1 = new TableColumn<>("Barcode");
        col1.setCellValueFactory(new PropertyValueFactory<>("barcode"));


        TableColumn<String, ProducT> col2 = new TableColumn<>("Product Name");
        col2.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<String, ProducT> col3 = new TableColumn<>("  Category");
        col3.setCellValueFactory(new PropertyValueFactory<>("category"));
        TableColumn<String, ProducT> col4 = new TableColumn<>("        Price");
        col4.setCellValueFactory(new PropertyValueFactory<>("price"));
        TableColumn<String, ProducT> col5 = new TableColumn<>("   Quantity");
        col5.setCellValueFactory(new PropertyValueFactory<>("quanty"));
        tab.getColumns().add(col1);
        tab.getColumns().add(col2);
        tab.getColumns().add(col3);
        tab.getColumns().add(col4);
        tab.getColumns().add(col5);
        tab.setItems(FXCollections.observableArrayList(prduct.getBarcode()));

        col1.prefWidthProperty().bind(tableView.widthProperty().multiply(0.816));
        col2.prefWidthProperty().bind(tableView.widthProperty().multiply(0.816));
        col3.prefWidthProperty().bind(tableView.widthProperty().multiply(0.816));
        col4.prefWidthProperty().bind(tableView.widthProperty().multiply(0.816));
        col5.prefWidthProperty().bind(tableView.widthProperty().multiply(0.816));
        Button b1 = new Button("Add Supplier");
        Button b2 = new Button("Add Product");
        Button b3 = new Button("Back");
        HBox hb1 = new HBox(b1, b2, b3);
        hb1.setSpacing(20);
        hb1.setAlignment(Pos.BASELINE_RIGHT);
        VBox vb1 = new VBox(tab, hb1);
        vb1.setMargin(hb1, new Insets(70, 20, 10, 10));

        pane1.getItems().addAll(tableView, vb1);
        pane1.setDividerPositions(0.2);

        tableView.setRowFactory(tv -> {
            TableRow<String> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    selectedSupplier = row.getItem();

                    Product p = new Product();
                    ArrayList<ProducT> _p = p.getProductsBySupplier(selectedSupplier);

                    tab.setItems(FXCollections.observableArrayList(p.getProductsBySupplier(selectedSupplier)));

                }
            });
            return row;
        });

        b2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Label lbl_barcode = new Label("Barcode");
                Label lbl_name = new Label("Product Name");
                Label lbl_category = new Label("Category");
                Label lbl_price = new Label("Price for unit");
                TextField input_barcode = new TextField();
                TextField input_name = new TextField();
                TextField input_category = new TextField();
                TextField input_price = new TextField();
                Label lbl_quanty = new Label("Quantity");
                TextField input_quanty = new TextField();
                Button add_product = new Button("Add Product");
                VBox vb1 = new VBox(lbl_barcode, input_barcode);
                VBox vb2 = new VBox(lbl_price,input_price);
                HBox hb1 = new HBox(vb1, vb2);
                HBox hb2 = new HBox(lbl_quanty);
                HBox hb3 = new HBox(input_quanty);
                VBox vb5 = new VBox(hb2,hb3);

                VBox vb3 = new VBox(hb1,vb5,add_product);
                hb1.setMargin(vb1, new Insets(20, 10, 10, 35));
                hb1.setMargin(vb2, new Insets(20, 10, 10, 35));
                vb3.setMargin(vb5, new Insets(5, 10, 10, 130));
                vb3.setMargin(add_product, new Insets(10, 10, 10, 160));
String zett = null;


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

                        Items items = new Items();
                        if (selectedSupplier == null) {
                            Alert al = new Alert(Alert.AlertType.WARNING, "Select a Supplier", ButtonType.OK);
                            al.show();
                            secondStage.close();
                            return;
                        }
                        try {
                            Double.parseDouble(input_price.getText());
                        } catch (Exception pjone11) {
                            Alert al = new Alert(Alert.AlertType.WARNING, "Wrong price format", ButtonType.OK);
                            al.show();
                            return;
                        }
                        try {
                            Integer.parseInt(input_quanty.getText());
                        } catch (Exception pjone11) {
                            Alert al = new Alert(Alert.AlertType.WARNING, "Wrong quantity format", ButtonType.OK);
                            al.show();
                            return;
                        }
//
                        for (ItemS i: ITEAM )
                            if(i.getBarcode().equals(input_barcode.getText()))
                            {
                                product.add(new ProducT(i.getBarcode(), i.getItem_name(), i.getCategory(), Double.parseDouble(input_price.getText()), selectedSupplier,Integer.parseInt(input_quanty.getText())));
                                analytics_utils.addAnalytics(new Analytics(user_at_moment.getUsename(),0,Double.parseDouble(input_price.getText())*Integer.parseInt(input_quanty.getText()),"Purchase",data_for_analytics=new Date()));
                                secondStage.close();


                                selectbarcode = i.getBarcode();
                                ItemS x = items.getItemsbybarcode(selectbarcode);
                                x.quanty =  Integer.parseInt(input_quanty.getText())+i.getQuanty();
                                items.updateIteams();
                                secondStage.close();
                                (new SupplierView()).start(primaryStage,user_at_moment);
                                return;
                            }
                        Alert all = new Alert(Alert.AlertType.WARNING, "Barcode was not found", ButtonType.OK);
                        all.show();
                        secondStage.close();
                        return;
                    }

                });





            }
        });

        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Label secondLabel = new Label("New Supplier");
                TextField text = new TextField();
                Button add = new Button("Add");
                HBox hb1 = new HBox(secondLabel, text);
                hb1.setSpacing(20);

                VBox vb1 = new VBox(hb1, add);
                vb1.setMargin(hb1, new Insets(20, 10, 10, 10));
                vb1.setMargin(add, new Insets(10, 10, 10, 145));

                StackPane secondaryLayout = new StackPane();
                secondaryLayout.getChildren().add(vb1);

                Scene secondScene = new Scene(secondaryLayout, 300, 100);

                Stage secondStage = new Stage();
                secondStage.setTitle("Add Suplier");
                secondStage.setScene(secondScene);
                add.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Supplier sup = new Supplier();
                        sup.add(text.getText());

                        sup.readUsers();
                        ObservableList<String> supplier_list = FXCollections.observableArrayList(supplier.getSuppliers());
                        tableView.setItems(supplier_list);
                        (new SupplierView()).start(primaryStage,user_at_moment);
                        Alert al = new Alert(Alert.AlertType.CONFIRMATION, "Supplier is added", ButtonType.OK);
                        al.show();
                        secondStage.close();
                    }
                });

                //Set position of second window, related to primary window.
                secondStage.setX(primaryStage.getX() + 250);
                secondStage.setY(primaryStage.getY() + 100);

                secondStage.show();
            }
        });
        b3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                (new Menu3()).start(primaryStage,user_at_moment);
            }
        });
        Scene scen = new Scene(pane1, 913, 512);
        primaryStage.setScene(scen);
        primaryStage.show();
    }
}
