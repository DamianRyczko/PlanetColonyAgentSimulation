import java.util.Map;

public class SolarPanel extends Building{
    private double panelEffectiveness; // 1 for 100% Effectiveness

    private boolean isDirty;

    public SolarPanel(Map<String, String> config, int x, int y) {
        super(config, x, y);
        setPanelEffectiveness(1);
        setIsDirty(false);
    }


//    public SolarPanel(int x, int y) {
//        super("energy", x, y);
//        setPanelEffectiveness(1);
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
        if(!getIsDamaged()){
            setTimeToCompleteProduction(getTimeToCompleteProduction()-1);
            if(getTimeToCompleteProduction() == 0){
                setResourceWaitingForCollection(getResourceWaitingForCollection()+(int)(getProducedAmount()*getPanelEffectiveness()));
                setTimeToCompleteProduction(getProductionTime());
            }
        }
        if(getPanelEffectiveness()-0.05 > 0){
            setPanelEffectiveness(getPanelEffectiveness()-0.05);
        }
        else{
            setPanelEffectiveness(0.0);
        }
        if (panelEffectiveness < 0.5){
            setIsDirty(true);
        }
    }

    public double getPanelEffectiveness() {
        return panelEffectiveness;
    }

    public void setPanelEffectiveness(double panelEffectiveness) {
        this.panelEffectiveness = panelEffectiveness;
    }

    public boolean getDirty() {
        return isDirty;
    }

    public void setIsDirty(boolean dirty) {
        isDirty = dirty;
    }

}
