public class WaterPurifier extends Building{
    private int requiredElectricity;
    public WaterPurifier(int x, int y){
        super("water", x, y);
        setRequiredElectricity(10);
        setProducedAmount(100);
        setProductionTime(2);
        setChanceOfMalfunction(0.01 + getRandom().nextDouble()/100);
        setTimeToCompleteProduction(getProductionTime());
    }


}