import java.awt.*;
import java.util.Random;

abstract class Astronaut {
    private int id;
    private int health;
    private Position position;
    private boolean alive = true;
    private boolean occupied;
    private int dailyDistance;


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
        if (health <= 0) {
            health = 0;
        }
        if (health > 100) {
            health = 100;
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
