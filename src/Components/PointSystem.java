package Components;

import Database.DBConnection;

import java.sql.Connection;


public class PointSystem {
    private int players = DBConnection.getAmtPlayer();
    private static int oldPoints = DBConnection.getPoints();
    private static int amtCorrect;

    //1,2,3 get points, and everybody who guess correct.
    // input is round result, then the DB is updated with new totScore
    public static void setPointsGuesser(int userID){
        amtCorrect = DBConnection.getAmtCorrect();
        int points;

        if(amtCorrect==1){
            points = 150;
        }else if(amtCorrect==2){
            points = 100;
        }else if (amtCorrect==3){
            points=75;
        } else{
            points = 50;
        }
        DBConnection.updatePoints(points, userID);
    }


    public static void setPointsDrawer(int userID){
            int amtCorrect = DBConnection.getAmtCorrect();
            int points = DBConnection.getPoints();
            int newPoints = points + (amtCorrect*50);
            DBConnection.updatePoints(newPoints, userID);
    }
}
