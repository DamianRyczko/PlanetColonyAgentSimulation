import java.util.ArrayList;

public class Collector extends Astronaut {
    private Position goal;
    private String resource;
    private int idResource;
    private int carryingCapacity;

    public Collector(Position position, int dailyDistance, int id, int carryingCapacity) {
        setHealth(100);
        setPosition(position);
        setDailyDistance(dailyDistance);
        setId(id);
        setOccupied(false);
        this.carryingCapacity = carryingCapacity;
    }

    public void collect (ArrayList<Building> buildings){
        if (isOccupied()){
            return;
        }
        findGoal(buildings);

    }

    private void findGoal(ArrayList<Building> buildings){

    }


}
