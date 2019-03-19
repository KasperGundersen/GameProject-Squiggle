package Components;

public class Player {
    private String username;
    private int userID;
    private int avatarID;
    private double points;

    public Player(String username, int userID, int avatarID, double points){
        this.username = username;
        this.userID = userID;
        this.avatarID = avatarID;
        this.points = points;
    }

    public String getUsername(){
        return username;
    }

    public int getUserID(){
        return userID;
    }
    public int getAvatarID(){
        return avatarID;
    }
    public double getPoints(){
        return points;
    }
}
