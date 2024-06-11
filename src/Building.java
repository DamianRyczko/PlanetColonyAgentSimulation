import java.util.Map;
import java.util.Random;
abstract class Building{
    private String producedResource;
    private int producedAmount;
    private int productionTime;
    private int timeToCompleteProduction;
    private double chanceOfMalfunction;
    private int resourceWaitingForCollection;
    private Random random = new Random();
    private Position postion;
    private boolean isDamaged;
    private boolean hasEnergy;
    private int requiredElectricity;
    private int requieredWater;
    private boolean hasWater;

    public Building(Map<String, String> config, int x, int y) {
        this.postion = new Position(x, y);
        setProducedResource(config.get("producedResource"));
        setProducedAmount(Integer.parseInt(config.get("producedAmount")));
        setProductionTime(Integer.parseInt(config.get("productionTime")));
        setChanceOfMalfunction(Double.parseDouble(config.get("chanceOfMalfunction"))+ getRandom().nextDouble()/100);
        setRequiredElectricity(Integer.parseInt(config.get("requiredElectricity")));
        setRequieredWater(Integer.parseInt(config.get("requiredWater")));
        setIsDamaged(false);
        setHasEnergy(true);
        setHasWater(true);
        setTimeToCompleteProduction(Integer.parseInt(config.get("productionTime")));
    }
    public void dayCycle(ColonyResources colonyResources){
        if (getRandom().nextDouble() < getChanceOfMalfunction()){
            setIsDamaged(true);
        }
        setHasEnergy(false);
        if(colonyResources.getElectricity()- getRequiredElectricity() >= 0) {
            colonyResources.setElectricity(colonyResources.getElectricity() - getRequiredElectricity());
            setHasEnergy(true);
        }
        if(!getIsDamaged() && getHasEnergy()){
            setTimeToCompleteProduction(getTimeToCompleteProduction()-1);
            if(getTimeToCompleteProduction() == 0){
                setResourceWaitingForCollection(getResourceWaitingForCollection()+getProducedAmount());
                setTimeToCompleteProduction(getProductionTime());
            }
        }
    }
    public void show(){
        System.out.println("ProducedResource: "+getProducedResource());
        System.out.println("HasWater: "+getHasWater());
        System.out.println("HasEnergy: "+getHasEnergy());
        System.out.println("IsDamaged: "+getIsDamaged());
        System.out.println("ProducedAmount: "+getProducedAmount());
        System.out.println("ProductionTime: "+getProductionTime());
        System.out.println("ChanceOfMalfunction: "+getChanceOfMalfunction());
        System.out.println("X: "+getPostion().getX());
        System.out.println("Y: "+getPostion().getY());
        System.out.println("ResourceWaitingForCollection: "+getResourceWaitingForCollection());
        System.out.println("TimeToCompleteProduction: "+getTimeToCompleteProduction());
        System.out.println("RequieredWater: "+getRequieredWater());
        System.out.println("RequiredElectricity: "+getRequiredElectricity());
    }


    public String getProducedResource() {
        return producedResource;
    }

    public void setProducedResource(String producedResource) {
        this.producedResource = producedResource;
    }

    public int getProducedAmount() {
        return producedAmount;
    }

    public void setProducedAmount(int producedAmount) {
        this.producedAmount = producedAmount;
    }

    public int getProductionTime() {
        return productionTime;
    }

    public void setProductionTime(int productionTime) {
        this.productionTime = productionTime;
    }

    public double getChanceOfMalfunction() {
        return chanceOfMalfunction;
    }

    public void setChanceOfMalfunction(double chanceOfMalfunction) {
        this.chanceOfMalfunction = chanceOfMalfunction;
    }

    public int getTimeToCompleteProduction() {
        return timeToCompleteProduction;
    }

    public void setTimeToCompleteProduction(int timeToCompleteProduction) {
        this.timeToCompleteProduction = timeToCompleteProduction;
    }

    public int getResourceWaitingForCollection() {
        return resourceWaitingForCollection;
    }

    public void setResourceWaitingForCollection(int resourceWaitingForCollection) {
        this.resourceWaitingForCollection = resourceWaitingForCollection;
    }

    public boolean getIsDamaged() {
        return isDamaged;
    }

    public void setIsDamaged(boolean damaged) {
        isDamaged = damaged;
    }

    public boolean getHasEnergy() {
        return hasEnergy;
    }

    public void setHasEnergy(boolean hasEnergy) {
        this.hasEnergy = hasEnergy;
    }

    public Random getRandom() {
        return random;
    }

    public int getRequiredElectricity() {
        return requiredElectricity;
    }

    public void setRequiredElectricity(int requiredElectricity) {
        this.requiredElectricity = requiredElectricity;
    }

    public int getRequieredWater() {
        return requieredWater;
    }

    public void setRequieredWater(int requieredWater) {
        this.requieredWater = requieredWater;
    }

    public boolean getHasWater() {
        return hasWater;
    }

    public void setHasWater(boolean hasWater) {
        this.hasWater = hasWater;
    }

    public Position getPostion() {
        return postion;
    }

}
