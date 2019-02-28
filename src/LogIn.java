import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class LogIn{

    private GridPane gp;
    private Scene sc;

    public LogIn(double width, double height) {
        gp = createLogInFormPane();
        addUIControls(gp);
        sc = new Scene(gp, width, height);
    }

    public Scene getSc() {
        return sc;
    }

    private GridPane createLogInFormPane() {
        // Instantiate a new Grid Pane
        GridPane gridPane = new GridPane();

        // Position the pane at the center of the screen, both vertically and horizontally
        gridPane.setAlignment(Pos.CENTER);

        // Set a padding of 20px on each side
        gridPane.setPadding(new Insets(40, 40, 40, 40));

        // Set the horizontal gap between columns
        gridPane.setHgap(10);

        // Set the vertical gap between rows
        gridPane.setVgap(10);


        // Add Column Constraints

        // columnOneConstraints will be applied to all the nodes placed in column one.
        ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.RIGHT);

        // columnTwoConstraints will be applied to all the nodes placed in column two.
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(200,200, Double.MAX_VALUE);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

        return gridPane;
    }
    private void addUIControls(GridPane gridPane) {

        double prefHeight = 40;
        // Add Header
        Label headerLabel = new Label("Login");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));

        // Add Name Label
        Label nameLabel = new Label("Username: ");
        gridPane.add(nameLabel, 0,1);

        // Add Name Text Field
        TextField nameField = new TextField();
        nameField.setPrefHeight(prefHeight);
        nameField.setPromptText("xXPussySlayerXx");
        gridPane.add(nameField, 1,1);

        // Add Password Label
        Label passwordLabel = new Label("Password: ");
        gridPane.add(passwordLabel, 0, 3);

        // Add Password Field
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefHeight(prefHeight);
        passwordField.setPromptText("password");
        gridPane.add(passwordField, 1, 3);

        // Add Login Button
        Button logInButton = new Button("Login");
        logInButton.setPrefHeight(prefHeight);
        logInButton.setDefaultButton(true);
        logInButton.setPrefWidth(100);
        gridPane.add(logInButton, 0, 4, 2, 1);
        GridPane.setHalignment(logInButton, HPos.CENTER);
        GridPane.setMargin(logInButton, new Insets(20, 0,20,0));

        // Add Registration Button
        Button regButton = new Button("Register new user");
        SignUp su = new SignUp(Main.getWidth(), Main.getHeight());
        regButton.setOnAction(e -> Main.setScene2(su.getSc()));

        regButton.setPrefHeight(prefHeight);
        regButton.setDefaultButton(true);
        regButton.setPrefWidth(300);
        gridPane.add(regButton, 0, 5, 2, 1);
        GridPane.setHalignment(regButton, HPos.CENTER);
        GridPane.setMargin(regButton, new Insets(20, 0, 20, 0));

        // Add option button
        Button optionButton = new Button("Options");
        gridPane.add(optionButton, 4, 14);
        //optionButton.setPadding(new Insets(20, 20, 20, 800));
    }
}