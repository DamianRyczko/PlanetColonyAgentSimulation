import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        var eventRandomizer = new EventRandomizer(0.05);
        var eventSimulator = new EventSimulator(eventRandomizer);
        var colonyResources = new ColonyResources(380, 400, 400, 400);

        var buildingPlacer = new BuildingPlacer();

        ArrayList<WaterPurifier> waterPurifiers = new ArrayList<>();
        ArrayList<Farm>  farms= new ArrayList<>();
        ArrayList<OxygenGenerator> oxygenGenerators = new ArrayList<>();
        ArrayList<FusionReactor> fusionReactors = new ArrayList<>();
        ArrayList<SolarPanel> solarPanels = new ArrayList<>();

        //        waterPurifiers.add(new WaterPurifier(1,1));
//        farms.add(new Farm(1,2));
//        oxygenGenerators.add(new OxygenGenerator(1,3));
//        fusionReactors.add(new FusionReactor(1,4));
//        solarPanels.add(new SolarPanel(1,5));
// this is just for test
        String[][] grid = {
                {"empty", "water", "empty", "empty", "food", "empty", "empty", "oxygen", "empty", "electricity"},
                {"empty", "empty", "empty", "empty", "empty", "empty", "water", "empty", "empty", "empty"},
                {"electricity", "empty", "empty", "empty", "empty", "oxygen", "empty", "empty", "empty", "empty"},
                {"empty", "food", "empty", "empty", "empty", "empty", "empty", "empty", "empty", "water"},
                {"empty", "empty", "empty", "empty", "empty", "empty", "electricity", "empty", "empty", "empty"},
                {"empty", "empty", "oxygen", "empty", "empty", "food", "empty", "empty", "empty", "empty"},
                {"empty", "empty", "empty", "empty", "empty", "empty", "empty", "water", "empty", "empty"},
                {"empty", "empty", "empty", "electricity", "empty", "empty", "empty", "empty", "empty", "food"},
                {"empty", "empty", "empty", "empty", "oxygen", "empty", "empty", "empty", "empty", "empty"},
                {"food", "empty", "empty", "empty", "empty", "empty", "empty", "empty", "electricity", "empty"}
        };

        HashMap < Integer ,String> typeToName = new HashMap <>();
        typeToName.put(0, "empty");
        typeToName.put(1, "food");
        typeToName.put(2, "water");

        ArrayList<Integer> chanceOftypes =  new ArrayList<>();
        chanceOftypes.add(60);
        chanceOftypes.add(15);
        chanceOftypes.add(25);


        int gridSizex = 30;
        int gridSizey = 30;
        int chanceOfRich = 20;
        AMap map = new AMap(gridSizex, gridSizey, chanceOfRich);
        map.setMap(chanceOftypes);

        Position wynik = FindPath.BFS(new Position(0,0), new Position(5, 5), new Position(20, 20), 3);
        System.out.println("wynik = "+wynik.toString());

        int startX = 0;
        int startY = 0;
        int numberOfEngineers = 10;

        buildingPlacer.addBuildings(solarPanels, farms, waterPurifiers, oxygenGenerators, numberOfEngineers, colonyResources, startX, startY, grid);

        int days = 100;
        //this is just for test
        while(days >= 0){

            //generates events
            eventSimulator.generateEvent(solarPanels, farms, fusionReactors, waterPurifiers, oxygenGenerators);

            //adds buildings
            buildingPlacer.addBuildings(solarPanels, farms, waterPurifiers, oxygenGenerators, numberOfEngineers, colonyResources, startX, startY, grid);
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
