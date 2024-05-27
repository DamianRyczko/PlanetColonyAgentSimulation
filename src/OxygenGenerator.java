import java.util.Map;

public class OxygenGenerator extends Building{
    public OxygenGenerator(Map<String, String> config, int x, int y) {
        super(config, x, y);
    }


//    public OxygenGenerator(int x, int y) {
//        super("oxygen", x, y);
//        setRequiredElectricity(15);
//        setProducedAmount(100);
//        setProductionTime(2);
//        setChanceOfMalfunction(0.01 + getRandom().nextDouble()/100);
//        setTimeToCompleteProduction(getProductionTime());
//    }
}
