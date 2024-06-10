import java.util.ArrayList;

public class Collector extends Astronaut {
    private Position goal;
    private String resource;
    private int idResource;
    private final int carryingCapacity;
    private final Position basePosition;
    private int resourceInBackpack;

    public Position getGoal (){
        return goal;
    }

    public Collector(Position position, int dailyDistance, int id, int carryingCapacity, Position basePosition) {
        super(id, position, dailyDistance);
        this.carryingCapacity = carryingCapacity;
        this.basePosition = basePosition;
        this.goal = null;
    }

    public void collect (ArrayList<Building> buildings){
        if (isOccupied()){
            return;
        }
        findGoal(buildings);
    }

    public void findGoal(ArrayList<Building> buildings){
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
        //setOccupied(true);
    }

    void goalCollecting(ArrayList<Building> buildings, ColonyResources colonyResources){
        if (resourceInBackpack == 0) {
            if (super.getPosition() != goal) {
                setPosition(FindPath.BFS(getPosition(), goal, GlobalVariables.GridSize, getDailyDistance()));
            }
            else{
                for (Building building : buildings) {
                    if (building.getPostion() == goal){
                        resource = building.getProducedResource();
                        int remainingResource = building.getResourceWaitingForCollection()-carryingCapacity;
                        if (remainingResource < 0){
                            remainingResource = 0;
                        }
                        resourceInBackpack = building.getResourceWaitingForCollection()-remainingResource;
                        building.setResourceWaitingForCollection(remainingResource);
                    }
                }
            }
        }
        else{
            if (super.getPosition() != basePosition) {
                setPosition(FindPath.BFS(getPosition(), basePosition, GlobalVariables.GridSize, getDailyDistance()));
            }
            else{
                fillColonyResources(colonyResources);
                goal = null;
            }

        }
    }

    private void fillColonyResources(ColonyResources colonyResources){
        if (resourceInBackpack == 0) {
            return;
        }
        if (resource == "food") {
            colonyResources.setFood(colonyResources.getFood() + resourceInBackpack);
            reSetCollector();
            return;
        }
        if (resource == "water") {
            colonyResources.setWater(colonyResources.getWater() + resourceInBackpack);
            reSetCollector();
            return;
        }
        if (resource == "oxygen") {
            colonyResources.setOxygen(colonyResources.getOxygen() + resourceInBackpack);
            reSetCollector();
            return;
        }
        if (resource == "electricity") {
            colonyResources.setElectricity(colonyResources.getElectricity() + resourceInBackpack);
            reSetCollector();
            return;
        }
    }

    private void reSetCollector(){
        resourceInBackpack = 0;
        goal = basePosition;
        resource = "";
        idResource = 0;
        super.setOccupied(false);
    }


}
