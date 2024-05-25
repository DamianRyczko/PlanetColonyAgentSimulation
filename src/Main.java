import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        var eventRandomizer = new EventRandomizer(0.05);
        var eventSimulator = new EventSimulator(eventRandomizer);
        var colonyResources = new ColonyResources(400, 400, 500, 300);

        ArrayList<WaterPurifier> waterPurifiers = new ArrayList<>();
        ArrayList<Farm>  farms= new ArrayList<>();
        ArrayList<OxygenGenerator> oxygenGenerators = new ArrayList<>();
        ArrayList<FusionReactor> fusionReactors = new ArrayList<>();
        ArrayList<SolarPanel> solarPanels = new ArrayList<>();

        waterPurifiers.add(new WaterPurifier(1,1));
        farms.add(new Farm(1,2));
        oxygenGenerators.add(new OxygenGenerator(1,3));
        fusionReactors.add(new FusionReactor(1,4));
        solarPanels.add(new SolarPanel(1,5));

        int days = 10;
        //to taki tescik
        while(days >= 0){

            //generates events
            eventSimulator.generateEvent(solarPanels, farms, fusionReactors, waterPurifiers, oxygenGenerators);

            //cycles all buildings
            System.out.println("====================Water purifiers=======================");
            for (WaterPurifier waterPurifier : waterPurifiers) {
                waterPurifier.dayCycle(colonyResources);
                System.out.println("--------------------");
                System.out.println(waterPurifier.getIsDamaged());
                System.out.println(waterPurifier.getResourceWaitingForCollection());
            }
            System.out.println("====================Farms=======================");
            for (Farm farm : farms) {
                farm.dayCycle(colonyResources);
                System.out.println("--------------------");
                System.out.println(farm.getIsDamaged());
                System.out.println(farm.getResourceWaitingForCollection());
                System.out.println(farm.getTimeToCompleteProduction());
            }
            System.out.println("====================Oxygen generators=======================");
            for (OxygenGenerator oxygenGenerator : oxygenGenerators) {
                oxygenGenerator.dayCycle(colonyResources);
                System.out.println("--------------------");
                System.out.println(oxygenGenerator.getIsDamaged());
                System.out.println(oxygenGenerator.getResourceWaitingForCollection());
            }
            System.out.println("====================Fusion reactors=======================");
            for (FusionReactor fusionReactor : fusionReactors) {
                fusionReactor.dayCycle(colonyResources);
                System.out.println("--------------------");
                System.out.println(fusionReactor.getIsDamaged());
                System.out.println(fusionReactor.getResourceWaitingForCollection());
            }
            System.out.println("====================Solar panels=======================");
            for (SolarPanel solarPanel : solarPanels) {
                solarPanel.dayCycle(colonyResources);
                System.out.println("--------------------");
                System.out.println(solarPanel.getIsDamaged());
                System.out.println(solarPanel.getResourceWaitingForCollection());
            }
            System.out.println("====================Colony resources=======================");
            System.out.println(colonyResources.getFood());
            System.out.println(colonyResources.getOxygen());
            System.out.println(colonyResources.getWater());
            System.out.println(colonyResources.getElectricity());

            days--;
        }

    }
}
