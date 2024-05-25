import java.util.Random;
abstract class Building {
    private String producedResource;
    private int producedAmount;
    private int productionTime;
    private int timeToCompleteProduction;
    private double chanceOfMalfunction;
    private int resourceWaitingForCollection;
    private Random random;
    private boolean isDamaged;
    private boolean hasEnergy;
    private int requiredElectricity;
    private int x;

    private int y;
    public Building(String producedResource, int x, int y) {
        this.producedResource = producedResource;
        this.x = x;
        this.y = y;
        this.random = new Random();
        setIsDamaged(false);
        setHasEnergy(true);
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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
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
}
