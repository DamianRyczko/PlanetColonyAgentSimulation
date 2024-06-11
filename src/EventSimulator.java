import java.util.Random;
import java.util.ArrayList;

public class EventSimulator {
    private EventInterface randomEvent;
    private Random random;
    private SimulationPanelLeft simulationPanelLeft;

    public EventSimulator(EventInterface randomEvent, SimulationPanelLeft simulationPanelLeft) {
        this.randomEvent = randomEvent;
        this.random = new Random();
        this.simulationPanelLeft = simulationPanelLeft;
    }

    public void generateEvent(ArrayList<Building> buildings, ColonyResources colonyResources) {
        int event = getRandomEvent().drawEvent(3);
        switch (event) {
            case 0:
                simulationPanelLeft.appendEventMessage("No event occurred");
                break;
            case 1:
                simulationPanelLeft.appendEventMessage("Event 'Dust storm'");
                for (Object building : buildings) {
                    if (building instanceof SolarPanel solarPanel) {
                        solarPanel.setPanelEffectiveness(0);
                        solarPanel.setIsDirty(true);
                    }
                }
                break;
            case 2:
                simulationPanelLeft.appendEventMessage("Event 'Meteor rain'");
                double chanceOfDamage = 0.2;
                for (Object building : buildings) {
                    if (building instanceof Farm farm) {
                        if (random.nextDouble() < chanceOfDamage) {
                            farm.setIsDamaged(true);
                        }
                    } else if (building instanceof SolarPanel solarPanel) {
                        if (random.nextDouble() < chanceOfDamage) {
                            solarPanel.setIsDamaged(true);
                        }
                    } else if (building instanceof FusionReactor fusionReactor) {
                        if (random.nextDouble() < chanceOfDamage) {
                            fusionReactor.setIsDamaged(true);
                        }
                    } else if (building instanceof OxygenGenerator oxygenGenerator) {
                        if (random.nextDouble() < chanceOfDamage) {
                            oxygenGenerator.setIsDamaged(true);
                        }
                    } else if (building instanceof WaterPurifier waterPurifier) {
                        if (random.nextDouble() < chanceOfDamage) {
                            waterPurifier.setIsDamaged(true);
                        }
                    }
                }
                break;
            case 3:
                simulationPanelLeft.appendEventMessage("Event 'Famine'");
                colonyResources.setFood((int) (colonyResources.getFood()*7/100));
                break;
        }
    }

    public EventInterface getRandomEvent() {
        return randomEvent;
    }
}
