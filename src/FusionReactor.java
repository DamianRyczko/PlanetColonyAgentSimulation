import java.util.Map;

public class FusionReactor extends Building{
    public FusionReactor(Map<String, String> config, int x, int y) {
        super(config, x, y);
    }


//    public FusionReactor(int x, int y) {
//        super("energy", x, y);
//        setRequiredElectricity(0);
//        setProducedAmount(100);
//        setProductionTime(2);
//        setChanceOfMalfunction(0.01 + getRandom().nextDouble()/100);
//        setTimeToCompleteProduction(getProductionTime());
//    }
}
