import java.util.ArrayList;

public class Collector extends Astronaut {
    private Position goal;
    private String resource;
    private int idResource;
    private final int carryingCapacity;
    private final Position basePosition;

    public Collector(Position position, int dailyDistance, int id, int carryingCapacity, Position basePosition) {
        super(id, position, dailyDistance);
        this.carryingCapacity = carryingCapacity;
        this.basePosition = basePosition;
    }

    public void collect (ArrayList<Building> buildings){
        if (isOccupied()){
            return;
        }
        findGoal(buildings);
    }

    private void findGoal(ArrayList<Building> buildings){
        if (buildings.isEmpty()){
            return;
        }
        int maxResourceWaitingForCollection = 0;
        Position positionToCollect = new Position(0,0);
        for (Building building : buildings) {
            if (maxResourceWaitingForCollection < building.getResourceWaitingForCollection()){
                maxResourceWaitingForCollection = building.getResourceWaitingForCollection();
                positionToCollect = building.getPostion();
            }
        }
        goal = positionToCollect;
        setOccupied(true);
    }

    void goalCollecting(AMap map){
        setPosition(FindPath.BFS(getPosition(),goal,map.getGridSize(),getDailyDistance()));
    }

    //@Override
    void dailyTask(ArrayList<Building> buildings, ArrayList<Astronaut> astronauts, AMap map){
        if (isOccupied()){
            goalCollecting(map);
        }
        else{
            findGoal(buildings);
        }
    }


}
