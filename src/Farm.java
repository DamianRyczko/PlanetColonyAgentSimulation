import java.util.Map;

public class Farm extends Building{
    private int requiredWater;
    private boolean hasWater;

    public Farm(Map<String, String> config, int x, int y) {
        super(config, x, y);
    }

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
