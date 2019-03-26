package Scenes;

import Database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.management.remote.SubjectDelegationPermission;

public class Help{
    private static GridPane grid;
    private static final double WIDTH = 400, HEIGHT = 500;

    public static void start() {
        Stage stage = new Stage();
        stage.setTitle("Help - User Manual");

        grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);

        //Label label = new Label();

        String message = (" Quick start:\n" +
                "\n" +
                "1. The first thing you need to do is to register a new user. You do this by clicking the “register new user” button. Then you fill out the textboxes with your personal information. When you have registered your user, you will receive an email verifying your new user.\n" +
                "2. Now you will be taken back to the log-in-page and can log in with your new username and password.\n" +
                "3. You are now at the main menu. Here you can choose between quitting, entering the “my page” or starting a new game.\n" +
                "4. When selecting the “Join Game” button, you will be sent to the drawing canvas which is your gaming arena. Here you can play with your friends when they arrive at the drawing canvas as well.\n" +
                "5. When you are playing you will find yourself in one of two states. Ether you are the drawer, or you are the guesser. The drawer can use the drawing tools to draw the given word, while the rest of the players guess what the word is, by writing the guesses in the live-chat.\n" +
                "6. If you guess correct word, both you and the drawer will receive point.\n" +
                "\n" +
                "Registration\n" +
                "When selecting the “register new user” button you enter the registration page. Here you need to register a username. The username can be any word or number that is not already registered or longer than 20 characters. Then you need to register your email and password. When the registration is completed you will receive an email that verifies your new account.\n" +
                "With you new username and password you can log in to the game. You do this at the log in page, which is the first page you enter when starting the application.\n" +
                "Main menu\n" +
                "At the main menu you have five buttons. The log out button takes you back to the page you came from. The quit button gives you an option to quit the whole application. A pop-up box will appear and ask if you are sure you want to quit.\n" +
                "The options button is to be find at every scene in the application. In the options you can customize the application to your desire. You can change the background color and the font size.\n" +
                "In “My Page” you can se your username and email. Here you also have the choice to change your password. Your avatar is also displayed at the page, and you can toggle thru the different avatars and change if desired.\n" +
                "Game\n" +
                "The meaning of the game SQUIGGLE is to draw and guess word. The game chooses words and who will draw and guess. The drawer is the only person who knows the word, while the rest of the players only can see how many letters the word contains. The drawer has several tools to his possession. The drawer can choose between four different sizes of the drawing-brush and an eraser. You can also draw in any color you want.\n" +
                "The players that are guessing will se what the drawer are drawing live. When the players want to guess the word, they simply write their suggestion in the live-chat at the right side of the canvas. If the guess is false, the guess will appear as a normal chat. If the guess is correct the player will get a confirmation that the guess was correct. The other players will receive the same message and therefor be able to continue guessing without knowing the correct word.\n" +
                "When the round ends, the game is over and the scores will be received. The player that guessed the correct word the fastest, win the most points. The drawer will also win points on the basis of the number of players that managed to guess the correct word.");
        ObservableList<String> items = FXCollections.observableArrayList(message);
        ListView<String> text = new ListView<>(items);
        text.setPrefHeight(450);
        text.setPrefWidth(380);


        grid.add(text,0, 0);

        Button backButton = new Button("Back");
        backButton.setPrefWidth(100);
        backButton.setOnAction(e -> {});

        grid.add(backButton, 0, 1);
        GridPane.setHalignment(backButton, HPos.CENTER);
        GridPane.setMargin(backButton, new Insets(20, 0, 20, 0));

        Scene scene = new Scene(grid);

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setHeight(HEIGHT);
        stage.setWidth(WIDTH);
        stage.setScene(scene);
        stage.show();
    }
}