package Components;

import Database.DBConnection;

import java.sql.Connection;


public class PointSystem {
    private int players = DBConnection.getAmtPlayer();
    private static int oldPoints = DBConnection.getPoints();

    //1,2,3 get points, and everybody who guess correct.
    // input is round result, then the DB is updated with new totScore
    public static void setPointsGuesser(int userID){
        int amtCorrect = DBConnection.getAmtCorrect();
        int points;

        if(amtCorrect==1){
            System.out.println("første riktig");
            points = 150;
        }else if(amtCorrect==2){
            System.out.println("andre riktig");
            points = 100;
        }else if (amtCorrect==3){
            System.out.println("tredje riktig");
            points=75;
        } else{
            points = 50;
        }
        DBConnection.updatePoints(points);
    }


    public static void setPointsDrawer(){
            int amtCorrect = DBConnection.getAmtCorrect();
            int newPoints = (50 * amtCorrect);
            DBConnection.updatePoints(newPoints);
    }
}
