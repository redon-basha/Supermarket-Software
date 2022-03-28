import Model.Admin;
import Model.Cashier;
import Model.Economist;
import Model.Users;
import Utils.RWUser;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Date;
import java.time.*;
import java.util.ArrayList;
import java.util.Calendar;

public class AddUser {

    String selectedUsername = null;

    public void start(Stage Stagi,Users user_at_moment) {
        SplitPane splitPane = new SplitPane();

        RWUser rw = new RWUser();
        ArrayList<Users> us = rw.getUsers();

        TableView tableView = new TableView();
        TableColumn<String, Users> column1 = new TableColumn<>("Username");
        column1.setCellValueFactory(new PropertyValueFactory<>("usename"));

        tableView.getColumns().add(column1);

        tableView.setItems(FXCollections.observableArrayList(rw.getUsers()));


        column1.prefWidthProperty().bind(tableView.widthProperty().multiply(1));
        GridPane gridPane = new GridPane();
        StackPane st1 = new StackPane(tableView);
        Label aa = new Label("First Name");
        Label bb = new Label("Last Name: ");
        Label cc = new Label("Phone");
        Label vv = new Label("Username");
        Label zz = new Label("Password");
        Label pp = new Label("Birthday");
        Label tt = new Label("Salary");
        Label xx = new Label("Access level");
        TextField a1 = new TextField();
        TextField b1 = new TextField();
        TextField v1 = new TextField();
        TextField c1 = new TextField();
        TextField z1 = new TextField();
        DatePicker bdayField = new DatePicker();
        TextField t1 = new TextField();
        TextField x1 = new TextField();
        ComboBox combo = new ComboBox();

        combo.getItems().addAll(
                "Choose department",
                "Admin",
                "Economist",
                "Cashier"
        );
        combo.getSelectionModel().selectFirst();


        Button cancel = new Button("Add User");
        Button add = new Button("Save User");
        Button del = new Button("Delete User");
        Button back = new Button("Back");
        Button adu = new Button("Add User");
        adu.setVisible(false);
        back.setAlignment(Pos.BASELINE_RIGHT);
        add.setVisible(false);
        StackPane pane1 = new StackPane(cancel, add);
        pane1.setAlignment(Pos.CENTER);

        HBox hb4 = new HBox(pane1, del);
        hb4.setSpacing(20);
        HBox hb5 = new HBox(adu, back);
        hb5.setSpacing(20);
        VBox vb1 = new VBox(aa, a1, bb, b1, cc, c1, tt, t1);
        VBox vb2 = new VBox(vv, v1, zz, z1, pp, bdayField, xx, combo);
        HBox hb1 = new HBox(vb1, vb2);
        VBox vb3 = new VBox(hb1, hb4, hb5);
        hb1.setMargin(vb1, new Insets(10, 20, 20, 20));
        hb1.setMargin(vb2, new Insets(10, 20, 20, 20));
        vb3.setMargin(hb4, new Insets(20, 20, 20, 20));
        vb3.setMargin(hb5, new Insets(200, 20, 20, 570));

        splitPane.setDividerPositions(0.2);

        vb3.setAlignment(Pos.TOP_CENTER);
        splitPane.getItems().addAll(st1, vb3);

        tableView.setRowFactory(tv -> {
            TableRow<Admin> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {

                    Users rowData = row.getItem();

                    // a1.setEditable(false);

                    a1.setText(rowData.firstName);
                    b1.setText(rowData.lastName);
                    v1.setText(rowData.usename);
                    c1.setText(rowData.phone);
                    z1.setText(rowData.password);
                    t1.setText(rowData.salary);
                    combo.setValue(rowData.acces_level);
                    combo.setEditable(false);

                    ZoneId defaultZoneId = ZoneId.systemDefault();
                    Instant instant = rowData.birthday.toInstant();
                    LocalDate localDate = instant.atZone(defaultZoneId).toLocalDate();
                    bdayField.setValue(localDate);

                    selectedUsername = rowData.usename;
                    add.setVisible(true);
                    cancel.setVisible(false);
                    adu.setVisible(true);

                }
            });
            return row;
        });

        add.setOnAction(e -> {
            RWUser rwu = new RWUser();

            Users x = rwu.getUsername1(selectedUsername);

            if (combo.getValue().equals("Select Department")) {
                Alert al = new Alert(Alert.AlertType.WARNING, "Access level does not exist", ButtonType.OK);
                al.show();
                return;
            }
            try {
                Double.parseDouble(c1.getText());
            } catch (Exception pjone11) {
                Alert al = new Alert(Alert.AlertType.WARNING, "Wrong phone format", ButtonType.OK);
                al.show();
                return;
            }
            try {
                Double.parseDouble(t1.getText());
            } catch (Exception pjone12) {
                Alert al = new Alert(Alert.AlertType.WARNING, "Wrong salary format", ButtonType.OK);
                al.show();
                return;
            }
            x.firstName = a1.getText();
            x.lastName = b1.getText();
            x.usename = v1.getText();
            x.password = z1.getText();
            x.phone = c1.getText();
            x.acces_level = combo.getValue().toString();
            x.salary = t1.getText();


            Instant instant = Instant.from(bdayField.getValue().atStartOfDay(ZoneId.of("GMT")));
            x.birthday = Date.from(instant);

            rwu.update();
            a1.clear();
            b1.clear();
            c1.clear();
            t1.clear();
            z1.clear();
            v1.clear();
            combo.getSelectionModel().selectFirst();
            bdayField.setValue(null);
            tableView.getSelectionModel().select(null);
            add.setVisible(false);
            cancel.setVisible(true);
            (new AddUser()).start(Stagi,user_at_moment);
        });
        cancel.setOnAction(e -> {
            RWUser rwu = new RWUser();
            try {
                Double.parseDouble(c1.getText());
            } catch (Exception pjone11) {
                Alert al = new Alert(Alert.AlertType.WARNING, "Wrong phone format", ButtonType.OK);
                al.show();
                return;
            }
            try {
                Double.parseDouble(t1.getText());
            } catch (Exception pjone12) {
                Alert al = new Alert(Alert.AlertType.WARNING, "Wrong salary format", ButtonType.OK);
                al.show();
                return;
            }

            if (combo.getValue().equals("Economist"))
                rwu.add(new Economist(a1.getText(), b1.getText(), t1.getText(), c1.getText(), v1.getText(), z1.getText(), new java.util.Date(), combo.getValue().toString()));
            else if (combo.getValue().equals("Admin"))
                rwu.add(new Admin(a1.getText(), b1.getText(), t1.getText(), c1.getText(), v1.getText(), z1.getText(), new java.util.Date(),  combo.getValue().toString()));
            else if (combo.getValue().equals("Cashier"))
                rwu.add(new Cashier(a1.getText(), b1.getText(), t1.getText(), c1.getText(), v1.getText(), z1.getText(), new java.util.Date(),  combo.getValue().toString()));
            else {
                Alert al = new Alert(Alert.AlertType.WARNING, "Acces level dos not exist", ButtonType.OK);
                al.show();
            }


            rwu.update();
            a1.clear();
            b1.clear();
            c1.clear();
            t1.clear();
            z1.clear();
            v1.clear();
            combo.getSelectionModel().selectFirst();
            bdayField.setValue(null);
            tableView.getSelectionModel().select(null);
            add.setVisible(false);
            cancel.setVisible(true);
            (new AddUser()).start(Stagi,user_at_moment);
        });

        del.setOnAction(e -> {
            RWUser rwu = new RWUser();
            Users xxx = rwu.getUsername1(v1.getText());
            rwu.delete(xxx);


            // rwu.update();
            a1.clear();
            b1.clear();
            c1.clear();
            t1.clear();
            z1.clear();
            v1.clear();
            combo.getSelectionModel().selectFirst();
            bdayField.setValue(null);
            tableView.getSelectionModel().select(null);
            add.setVisible(false);
            cancel.setVisible(true);
            (new AddUser()).start(Stagi,user_at_moment);
        });
        back.setOnAction(e -> {
            (new Menu3()).start(Stagi,user_at_moment);
        });
        adu.setOnAction(e -> {


            a1.clear();
            b1.clear();
            c1.clear();
            t1.clear();
            z1.clear();
            v1.clear();
            combo.getSelectionModel().selectFirst();
            bdayField.setValue(null);
            tableView.getSelectionModel().select(null);
            add.setVisible(false);
            cancel.setVisible(true);
            (new AddUser()).start(Stagi,user_at_moment);
            adu.setVisible(false);
        });
        Scene vivi = new Scene(splitPane, 913, 512);
        Stagi.setScene(vivi);
        Stagi.show();
    }
}
