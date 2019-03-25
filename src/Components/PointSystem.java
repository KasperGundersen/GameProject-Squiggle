package Components;

import Database.DBConnection;

import java.sql.Connection;


public class PointSystem {
    private int players = DBConnection.getAmtPlayer();
    private static int oldPoints = DBConnection.getPoints();

    //1,2,3 get points, and everybody who guess correct.
    // input is round result, then the DB is updated with new totScore
    public static void setPointsGuesser(){
        int amtCorrect = DBConnection.getAmtCorrect();
        int newPoints;

        if(amtCorrect==0){
            newPoints = 150;
        }else if(amtCorrect==1){
            newPoints = 100;
        }else if (amtCorrect==2){
            newPoints=75;
        } else{
            newPoints = 50;
        }
        DBConnection.updatePoints(newPoints);
    }

    public static void setPointsDrawer(){
        int amtCorrect = DBConnection.getAmtCorrect();
        int newPoints = amtCorrect*50;
        DBConnection.updatePoints(newPoints);
    }
}
