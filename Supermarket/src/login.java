import Model.Admin;
import Model.Cashier;
import Model.Economist;
import Model.Users;
import Utils.RWUser;
import javafx.application.Application;
import javafx.scene.control.*;
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

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


public class login extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        RWUser r = new RWUser();
        Label userLabel = new Label("Username: ");
        userLabel.setFont(Font.font("Book Antiqua",
                FontWeight.BOLD, 16));
        userLabel.getStyleClass().add("text1");
        TextField userField = new TextField();
        userField.setPromptText("Enter Username");

        Label passLabel = new Label("Password: ");
        passLabel.setFont(Font.font("Book Antiqua",
                FontWeight.BOLD, 16));
        passLabel.getStyleClass().add("text1");
        PasswordField passField = new PasswordField();
        passField.setPromptText("Enter Password");

        Text err = new Text("Username and Password do not match!");
        err.setStroke(Color.RED);
        err.getStyleClass().add("error");
        err.setVisible(false);

        Button login = new Button("Login");
        login.getStyleClass().add("login-but");
        Button cancel = new Button("Cancel");
        cancel.getStyleClass().addAll("cancel","login-but");

        GridPane gp = new GridPane();
        gp.setHgap(5);
        gp.setVgap(20);
        gp.setPadding(new Insets(7,7,7,7));
        gp.addRow(0, userLabel, userField);
        gp.addRow(1, passLabel, passField);
        gp.setAlignment(Pos.CENTER);

        HBox hb = new HBox(login, cancel);
        hb.setPadding(new Insets(5, 5, 5, 5));
        hb.setMargin(login, new Insets(0, 10, 0, 0));
        hb.setAlignment(Pos.CENTER);

        HBox hb_err = new HBox(err);
        hb_err.setAlignment(Pos.CENTER);

        GridPane mainPane = new GridPane();


        mainPane.addColumn(0, gp, hb, hb_err);
        mainPane.setVgap(10);

       mainPane.setAlignment(Pos.CENTER_RIGHT);
        login.setOnAction(e -> {
            RWUser rwu=new RWUser();
            String user=userField.getText();
            String pass=passField.getText();
            Users usr=rwu.checkLogin(user, pass);
            if(usr instanceof Admin) {
                (new Menu3()).start(primaryStage,usr);
            } else if(usr instanceof Economist) {
                (new Menu3()).start(primaryStage,usr);
            } else if(usr instanceof Cashier) {
                (new Menu3()).start(primaryStage,usr);
            } else {
                Alert al=new Alert(Alert.AlertType.WARNING, "Username and Password is incorrect", ButtonType.OK);
                al.show();
            }


    });
        cancel.setOnAction(e -> {
            primaryStage.close();
        });


        mainPane.setStyle("-fx-background-image: url('image/he1.jpg');"
                + "-fx-background-repeat: stretch;");

        Scene sc=new Scene(mainPane,913,512);
        sc.getStylesheets().addAll(this.getClass().getResource("Effects.css").toExternalForm());
         primaryStage.resizableProperty().setValue(Boolean.FALSE);
        primaryStage.setScene(sc);
        primaryStage.show();

    }
}
