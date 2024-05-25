import java.util.Random;
import java.util.ArrayList;
public class EventSimulator {
    private EventInterface randomEvent;
    private Random random;
    public EventSimulator(EventInterface randomEvent) {
        this.randomEvent = randomEvent;
        this.random = new Random();
    }
    public void generateEvent(ArrayList<SolarPanel> solarPanels,
                              ArrayList<Farm> farms,
                              ArrayList<FusionReactor> fusionReactors,
                              ArrayList<WaterPurifier> waterPurifiers,
                              ArrayList<OxygenGenerator> oxygenGenerators
    ) {
        int event = getRandomEvent().drawEvent(3);
        switch (event) {
            case 0:
                System.out.println("No event occurred");
                break;
            case 1:
                System.out.println("Event 'Dust storm'");
                for (SolarPanel solarPanel : solarPanels) {
                    solarPanel.setPanelEffectiveness(0);
                }
                break;
            case 2:
                System.out.println("Event 'Meteor rain'");
                double chanceOfDamage = 0.2;
                for (SolarPanel solarPanel : solarPanels) {
                    if (random.nextDouble() < chanceOfDamage) {
                        solarPanel.setIsDamaged(true);
                    }
                }
                for (Farm farm : farms) {
                    if (random.nextDouble() < chanceOfDamage) {
                        farm.setIsDamaged(true);
                    }
                }
                for (FusionReactor fusionReactor : fusionReactors) {
                    if (random.nextDouble() < chanceOfDamage) {
                        fusionReactor.setIsDamaged(true);
                    }
                }
                for (WaterPurifier waterPurifier : waterPurifiers) {
                    if (random.nextDouble() < chanceOfDamage) {
                        waterPurifier.setIsDamaged(true);
                    }
                }
                for (OxygenGenerator oxygenGenerator : oxygenGenerators) {
                    if (random.nextDouble() < chanceOfDamage) {
                        oxygenGenerator.setIsDamaged(true);
                    }
                }
                break;
            case 3:
                System.out.println("Event 'Famine'");
                for (Farm farm : farms) {
                    farm.setResourceWaitingForCollection(0);
                }
                break;
        }
    }
    public EventInterface getRandomEvent() {
        return randomEvent;
    }
}
