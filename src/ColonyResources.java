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

//    public void dailyUsage(Medic medicTab){
//        // Here function iterates over astronauts refiling thier backpack and substracting resources from magazine
//        int oxygenInMagazine = getOxygen();
//        int foodInMagazine = getFood();
//        int waterInMagazine = getWater();
//        for (Medic medic : medicTab) {
//        if(medic.getHealth() > 0) {
//            if (oxygenInMagazine - (100 - medic.getRemainingOxygen()) >= 0){
//                setOxygen(oxygenInMagazine - (100 - medic.getRemainingOxygen()));
//                medic.setRemainingOxygen(100);
//            }
//            if (foodInMagazine - (100 - medic.getRemainingFood()) >= 0){
//                setOxygen(foodInMagazine - (100 - medic.getRemainingFood()));
//                medic.setRemainingFood(100);
//            }
//            if (waterInMagazine - (100 - medic.getRemainingWater()) >= 0){
//                setOxygen(waterInMagazine - (100 - medic.getRemainingWater()));
//                medic.setRemainingWater(100);
//            }
//        }
//    }
//
//    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public int getOxygen() {
        return oxygen;
    }

    public void setOxygen(int oxygen) {
        this.oxygen = oxygen;
    }

    public int getElectricity() {
        return electricity;
    }

    public void setElectricity(int electricity) {
        this.electricity = electricity;
    }
}
