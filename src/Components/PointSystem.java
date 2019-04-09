package Components;

import Database.DBConnection;

import java.sql.Connection;

/**
 * Class containing the different methods which involves the point system
 */
public class PointSystem {
    private int players = DBConnection.getAmtPlayer();
    private static int oldPoints = DBConnection.getPoints();
    private static int amtCorrect;


    //1,2,3 get points, and everybody who guess correct.
    // input is round result, then the DB is updated with new totScore
    /**
     * Gives points to the player who guessing
     */
    public static void setPointsGuesser(){
        int amtCorrect = DBConnection.getAmtCorrect();
        int points;
        if (amtCorrect == 0) { //Temp fix because setPointsGuesser runs twice
            return;
        }

        if(amtCorrect==1){
            points = 150;
        }else if(amtCorrect==2){
            points = 100;
        }else if (amtCorrect==3){
            points=75;
        } else{
            points = 50;
        }
        DBConnection.updatePoints(points);
    }

    /**
     * Gives points to the players who is drawing
     */
    public static void setPointsDrawer(){
            int amtCorrect = DBConnection.getAmtCorrect();
            int newPoints = (50 * amtCorrect);
            DBConnection.updatePoints(newPoints);
    }
}
