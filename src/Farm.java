import java.util.Map;

public class Farm extends Building{
    private int requiredWater;
    private boolean hasWater;

    public Farm(Map<String, String> config, int x, int y) {
        super(config, x, y);
    }


//    public Farm(int x, int y) {
//        super("food", x, y);
//        this.requiredWater = 10;
//        setRequiredElectricity(20);
//        setRequiredWater(20);
//        setHasWater(true);
//        setChanceOfMalfunction(0);
//        setProducedAmount(100);
//        setProductionTime(2);
//        setChanceOfMalfunction(0.01 + getRandom().nextDouble()/100);
//        setTimeToCompleteProduction(getProductionTime());
//    }

    @Override
    public void dayCycle(ColonyResources colonyResources){
        if (getRandom().nextDouble() < getChanceOfMalfunction()){
            setIsDamaged(true);
        }
        setHasEnergy(false);
        setHasWater(false);
        if(colonyResources.getElectricity()- getRequiredElectricity() >= 0) {
            colonyResources.setElectricity(colonyResources.getElectricity() - getRequiredElectricity());
            setHasEnergy(true);
        }
        if(colonyResources.getWater()-getRequieredWater()>0){
            colonyResources.setWater(colonyResources.getWater()- getRequieredWater());
            setHasWater(true);
        }
        if(!getIsDamaged() && getHasEnergy() && getHasWater()){
            setTimeToCompleteProduction(getTimeToCompleteProduction()-1);
            if(getTimeToCompleteProduction() == 0){
                setResourceWaitingForCollection(getResourceWaitingForCollection()+getProducedAmount());
                setTimeToCompleteProduction(getProductionTime());
            }
        }
    }

}
