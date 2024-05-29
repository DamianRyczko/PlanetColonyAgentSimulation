import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import java.util.Map;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        var colonyResources = new ColonyResources(380, 400, 400, 400);
        var eventRandomizer = new EventRandomizer(0.05);
        var eventSimulator = new EventSimulator(eventRandomizer);

        ArrayList<Object> buildings = new ArrayList<>();
        var buildingPlacer = new BuildingPlacer();

        try {
            Map<String, Map<String, String>> config = ConfigLoader.loadConfig("src/config.txt");

            buildings.add(new FusionReactor(config.get("FusionReactor"),0,0));


        } catch (IOException e) {
            e.printStackTrace();
        }

        AMap map = new AMap(50, 50, 20);
        ArrayList<Integer> chanceOfTypes = new ArrayList<>();
        chanceOfTypes.add(90);
        chanceOfTypes.add(5);
        chanceOfTypes.add(5);
        map.setMap(chanceOfTypes);

        SimulationFrame frame = new SimulationFrame(colonyResources, map, buildings);

        int startX = 0;
        int startY = 0;
        int numberOfEngineers = 10;

        for (int i = 0; i<100; i++) {
            try {
                // Sleep for 1 second (1000 milliseconds)
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            frame.getSimulationPanelLeft().updateResources(colonyResources);

            eventSimulator.generateEvent(buildings);
            //adds buildings
            buildingPlacer.addBuildings(buildings, numberOfEngineers, colonyResources, startX, startY, map);
            frame.repaintMap();

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

        }
    }
}


