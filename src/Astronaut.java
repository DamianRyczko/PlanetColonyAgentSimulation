import java.util.Random;

abstract class Astronaut {
    private int id;
    private int health;
    private int remainingOxygen;
    private int remainingFood;
    private int remainingWater;
    private int x;
    private int y;
    private boolean alive;


    public int getId() {
        return id;
    }

    public int getHealth() {
        return health;
    }

    public int getRemainingOxygen() {
        return remainingOxygen;
    }

    public int getRemainingFood() {
        return remainingFood;
    }

    public int getRemainingWater() {
        return remainingWater;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isAlive() {
        return alive;
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

    public void setRemainingOxygen(int remainingOxygen) {
        this.remainingOxygen = remainingOxygen;
    }

    public void setRemainingWater(int remainingWater) {
        this.remainingWater = remainingWater;
    }

    public void setRemainingFood(int remainingFood) {
        this.remainingFood = remainingFood;
    }

    public void kill() {
        this.alive = false;
    }

    public void consumeResorces(int minimumOxygenConsumtion, int maximumOxygenConsumtion,
                                int minimumFoodConsumtion, int maximumFoodConsumtion,
                                int minimumWaterConsumtion, int maximumWaterConsumtion){
        Random random = new Random();
        remainingOxygen -= minimumOxygenConsumtion + random.nextInt(maximumOxygenConsumtion - minimumOxygenConsumtion);
        remainingFood -= minimumFoodConsumtion + random.nextInt(maximumFoodConsumtion - minimumFoodConsumtion);
        remainingWater -= minimumWaterConsumtion + random.nextInt(maximumWaterConsumtion - minimumWaterConsumtion);
        if (remainingOxygen <= 0 || remainingFood <= 0 || remainingWater <= 0) {
            kill();
        }
    }

}
