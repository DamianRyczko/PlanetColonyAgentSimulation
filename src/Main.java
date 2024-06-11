import java.io.IOException;
import java.util.*;
public class Main {

    public static void main(String[] args) {
        var colonyResources = new ColonyResources(1000, 1000, 1000, 1000);
        var eventRandomizer = new EventRandomizer(0.05);
        ArrayList<Building> buildings = new ArrayList<>();
        ArrayList<Astronaut> astronauts = new ArrayList<>();
        var buildingPlacer = new BuildingPlacer();

        int gridX = 30;
        int gridY = 30;

        AMap map = new AMap(gridX, gridY, 20);
        GlobalVariables.GridSizeX = map.getGridSizeX();
        GlobalVariables.GridSizeY = map.getGridSizeY();
        GlobalVariables.GridSize = map.getGridSize();
        ArrayList<Integer> chanceOfTypes = new ArrayList<>();
        chanceOfTypes.add(80); // null
        chanceOfTypes.add(15); // food
        chanceOfTypes.add(5); // water
        map.setMap(chanceOfTypes);

        int startX = 24;
        int startY = 24;
        Random rand = new Random();

        for (int i = 0; i < 10;i++){
            astronauts.add(new Medic(new Position(rand.nextInt(gridX-1), rand.nextInt(gridY-1)),40, 60, 5,i));
        }

        for (int i = 0; i < 30;i++){
            astronauts.add(new Engineer(astronauts.size() + i, 100, new Position(rand.nextInt(gridX), rand.nextInt(gridY)),2));
        }

        try {
            Map<String, Map<String, String>> config = ConfigLoader.loadConfig("src/config.txt");

            buildings.add(new FusionReactor(config.get("FusionReactor"),startX,startY));
            map.setFieldEmpty(startX,startY);

        } catch (IOException e) {
            e.printStackTrace();
        }

        SimulationFrame frame = new SimulationFrame(colonyResources, map, buildings, astronauts);
        var eventSimulator = new EventSimulator(eventRandomizer, frame.getSimulationPanelLeft());

        for (int i = 0; i<10000; i++) {
            System.out.println(i+" dzien symulacji");
            ArrayList<Engineer> freeEngineers = NewDay.nextDay(buildings, astronauts, colonyResources, map);

            frame.getSimulationPanelLeft().updateResources(colonyResources);

            eventSimulator.generateEvent(buildings,colonyResources);
            frame.repaintMap();
            //adds buildings
            buildingPlacer.addBuildings(buildings, colonyResources, startX, startY, map, freeEngineers);

            frame.repaintMap();
            System.out.println("budynki "+buildings.size()+" astronalci "+astronauts.size());
            for (Object building : buildings) {
                if (building instanceof Farm farm) {
                    farm.dayCycle(colonyResources);
                } else if (building instanceof SolarPanel solarPanel) {
                    solarPanel.dayCycle(colonyResources);
                } else if (building instanceof FusionReactor fusionReactor) {
                    fusionReactor.dayCycle(colonyResources);
                } else if (building instanceof OxygenGenerator oxygenGenerator) {
                    oxygenGenerator.dayCycle(colonyResources);
                } else if (building instanceof WaterPurifier waterPurifier) {
                    waterPurifier.dayCycle(colonyResources);

                }
            }
;
            try {
                // Sleep for 1 second (1000 milliseconds)
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}


