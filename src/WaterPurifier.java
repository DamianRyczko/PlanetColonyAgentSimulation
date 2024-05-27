import java.util.Map;

public class WaterPurifier extends Building{
    public WaterPurifier(Map<String, String> config, int x, int y) {
        super(config, x, y);
    }


//    public WaterPurifier(int x, int y){
//        super("water", x, y);
//        setRequiredElectricity(10);
//        setProducedAmount(100);
//        setProductionTime(2);
//        setChanceOfMalfunction(0.01 + getRandom().nextDouble()/100);
//        setTimeToCompleteProduction(getProductionTime());
//    }


}