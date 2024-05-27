import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import java.util.Map;
import java.util.Scanner;
import java.io.FileNotFoundException;
public class Main {
    public static void main(String[] args) {

        var eventRandomizer = new EventRandomizer(0.05);
        var eventSimulator = new EventSimulator(eventRandomizer);
        var colonyResources = new ColonyResources(380, 400, 400, 400);

        ArrayList<Object> buildings = new ArrayList<>();
        var buildingPlacer = new BuildingPlacer();

        try {
            Map<String, Map<String, String>> config = ConfigLoader.loadConfig("src/config.txt");

            buildings.add(new FusionReactor(config.get("FusionReactor"),0,0));


        } catch (IOException e) {
            e.printStackTrace();
        }




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

        //Position wynik = FindPath.BFS(new Position(0,0), new Position(5, 5), new Position(20, 20), 3);
        //System.out.println("wynik = "+wynik.toString());

        int startX = 0;
        int startY = 0;
        int numberOfEngineers = 10;


        int days = 100;
        //this is just for test
        while(days >= 0){

            //generates events
            eventSimulator.generateEvent(buildings);

            //adds buildings
            buildingPlacer.addBuildings(buildings, numberOfEngineers, colonyResources, startX, startY, map);

            //cycles all buildings
            for (Object building : buildings) {
                System.out.println("+++++++++++++++++++++++++++++");
                if (building instanceof Farm farm) {
                    System.out.println("This is a Farm.");
                    // Cast to Farm to access Farm-specific methods
                    farm.dayCycle(colonyResources);
                    farm.show();

                } else if (building instanceof SolarPanel solarPanel) {
                    System.out.println("This is a SolarPanel.");
                    // Cast to SolarPanel to access SolarPanel-specific methods
                    solarPanel.dayCycle(colonyResources);
                    solarPanel.show();
                } else if (building instanceof FusionReactor fusionReactor) {
                    System.out.println("This is a FusionReactor.");
                    // Cast to FusionReactor to access FusionReactor-specific methods
                    fusionReactor.dayCycle(colonyResources);
                    fusionReactor.show();
                } else if (building instanceof OxygenGenerator oxygenGenerator) {
                    System.out.println("This is an OxygenGenerator.");
                    // Cast to OxygenGenerator to access OxygenGenerator-specific methods
                    oxygenGenerator.dayCycle(colonyResources);
                    oxygenGenerator.show();
                } else if (building instanceof WaterPurifier waterPurifier) {
                    System.out.println("This is a WaterPurifier.");
                    // Cast to WaterPurifier to access WaterPurifier-specific methods
                    waterPurifier.dayCycle(colonyResources);
                    waterPurifier.show();
                }
            }

            System.out.println("====================Colony resources=======================");
            colonyResources.show();

            days--;
        }

    }
}
