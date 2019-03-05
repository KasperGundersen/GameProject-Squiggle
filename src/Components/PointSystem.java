package Components;

import java.util.ArrayList;

public class PointSystem {
    private int players;
    public ArrayList<Integer> points;

    public PointSystem(int players){
        this.points = new ArrayList<Integer>(players);
    }
}
