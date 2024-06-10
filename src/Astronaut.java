import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

abstract class Astronaut {
    private int id;
    private int health;
    private Position position;
    private boolean alive = true;
    private boolean occupied;
    private int dailyDistance;
    private boolean moveMade;
    private int moveDone;

    public Astronaut(int id, Position position, int dailyDistance) {

        this.id = id;
        health = 100;
        this.position = position;
        occupied = false;
        moveMade = false;
        this.dailyDistance = dailyDistance;
        moveDone = 0;
    }


    public int getId() {
        return id;
    }

    public int getHealth() {
        return health;
    }

    public Position getPosition() {
        return position;
    }

    public boolean isAlive() {
        return alive;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public int getDailyDistance() {
        return dailyDistance;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHealth(int health) {
        this.health = health;
        if (this.health <= 0) {
            this.health = 0;
            alive = false;
        }
        if (this.health > 100) {
            this.health = 100;
        }
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setDailyDistance(int dailyDistance) {
        this.dailyDistance = dailyDistance;
    }

    public void kill() {
        this.alive = false;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public boolean isMoveMade() {
        return moveMade;
    }

    public void setMoveMade(boolean moveMade) {
        this.moveMade = moveMade;
    }

    public int getMoveDone() {
        return moveDone;
    }

    public void setMoveDone(int moveDone) {
        this.moveDone = moveDone;
    }

    public void reSetMoveDone() {
        this.moveDone = 0;
    }

    public void moveTo (Position newPosition) {
        if (!isfree()){return;}
        if (newPosition == position){return;}
        int move = Position.manhattanDistance(position, newPosition);
        if (moveDone+move > dailyDistance) {
            this.position = FindPath.BFS(position,newPosition,GlobalVariables.GridSize,dailyDistance);
            this.moveMade = true;
        }
        else {
            moveDone += move;
            this.position = newPosition;
            this.moveMade = true;
        }

    }

    public boolean isfree(){
        if (moveMade){return false;}
        if (!alive){return false;}
        if (health <= 0){return false;}
        return true;
    }

    public boolean isfreeOrOccupide(){
        if (moveMade){return false;}
        if (occupied){return false;}
        if (!alive){return false;}
        if (health <= 0){return false;}
        return true;
    }



    void dailyTask(ArrayList<Building> buildings, ArrayList<Astronaut> astronauts){};

    public void astronautConsumption(int minimumOxygenConsumption, int maximumOxygenConsumption,
                                     int minimumFoodConsumption, int maximumFoodConsumption,
                                     int minimumWaterConsumption, int maximumWaterConsumption,
                                     ColonyResources colonyResources){
        Random random = new Random();
        colonyResources.subtractOxygen(minimumOxygenConsumption + random.nextInt(maximumOxygenConsumption - minimumOxygenConsumption));
        colonyResources.subtractFood(minimumFoodConsumption + random.nextInt(maximumFoodConsumption - minimumFoodConsumption));
        colonyResources.subtractWater(minimumWaterConsumption + random.nextInt(maximumWaterConsumption - minimumWaterConsumption));
    }

}
