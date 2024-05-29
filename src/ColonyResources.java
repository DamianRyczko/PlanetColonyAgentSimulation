public class ColonyResources {
    private int food;
    private int water;
    private int oxygen;
    private int electricity;

    public ColonyResources(int food, int water, int oxygen, int electricity) {
        this.food = food;
        this.water = water;
        this.oxygen = oxygen;
        this.electricity = electricity;
    }
    void show(){
        System.out.println("Food: "+getFood());
        System.out.println("Water: "+getWater());
        System.out.println("Electricity: "+getElectricity());
        System.out.println("Oxygen: "+getOxygen());
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public void subtractFood(int food) {
        this.food -= food;
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public void subtractWater(int water) {
        this.water -= water;
    }

    public int getOxygen() {
        return oxygen;
    }

    public void setOxygen(int oxygen) {
        this.oxygen = oxygen;
    }

    public void subtractOxygen(int oxygen) {
        this.oxygen -= oxygen;
    }

    public int getElectricity() {
        return electricity;
    }

    public void setElectricity(int electricity) {
        this.electricity = electricity;
    }

    public void subtractElectricity(int electricity) {
        this.electricity -= electricity;
    }
}
