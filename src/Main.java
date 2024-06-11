import java.io.IOException;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;



public class Main {

    public static void main(String[] args) {
        var colonyResources = new ColonyResources(400, 400-1, 400, 400);
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
        chanceOfTypes.add(94); // null
        chanceOfTypes.add(3); // food
        chanceOfTypes.add(3); // water
        map.setMap(chanceOfTypes);

        int startX = 24;
        int startY = 24;
        //int numberOfEngineers = 20;

        Random rand = new Random();


        for (int i = 0; i < 5;i++){
            astronauts.add(new Engineer(astronauts.size() + i, 100, new Position(rand.nextInt(gridX), rand.nextInt(gridY)),2));
        }

        for (int i = 0; i < 3;i++){
            astronauts.add(new Medic(new Position(rand.nextInt(gridX-1), rand.nextInt(gridY-1)),40, 60, 5,i));
        }
//        astronauts.add(new Engineer(9, 100, new Position(13, 21),10));
//        astronauts.add(new Engineer(10, 100, new Position(1, 21),10));
//        astronauts.add(new Engineer(11, 100, new Position(34, 13),10));
//        astronauts.add(new Engineer(12, 100, new Position(28, 56),10));


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
            if (NewDay.isItOver(astronauts)){
                System.out.println(" koniec ");
                break;
            }
            ArrayList<Engineer> freeEngineers = NewDay.nextDay(buildings, astronauts, colonyResources, map);



            frame.getSimulationPanelLeft().updateResources(colonyResources);

            eventSimulator.generateEvent(buildings,colonyResources);
            frame.repaintMap();
            //adds buildings
            buildingPlacer.addBuildings(buildings, colonyResources, startX, startY, map, freeEngineers);

            frame.repaintMap();
            System.out.println("budynki "+buildings.size()+" astronalci "+astronauts.size());
            //cycles all buildings
            for (Object building : buildings) {
                //System.out.println("+++++++++++++++++++++++++++++");
                if (building instanceof Farm farm) {
                    //System.out.println("This is a Farm.");
                    // Cast to Farm to access Farm-specific methods
                    farm.dayCycle(colonyResources);
                    //farm.show();

                } else if (building instanceof SolarPanel solarPanel) {
                    //System.out.println("This is a SolarPanel.");
                    solarPanel.dayCycle(colonyResources);
                    //solarPanel.show();
                } else if (building instanceof FusionReactor fusionReactor) {
                   // System.out.println("This is a FusionReactor.");
                    fusionReactor.dayCycle(colonyResources);
                    //fusionReactor.show();
                } else if (building instanceof OxygenGenerator oxygenGenerator) {
                    //System.out.println("This is an OxygenGenerator.");
                    oxygenGenerator.dayCycle(colonyResources);
                    //oxygenGenerator.show();
                } else if (building instanceof WaterPurifier waterPurifier) {
                    //System.out.println("This is a WaterPurifier.");
                    waterPurifier.dayCycle(colonyResources);
                    //waterPurifier.show();
                }
            }
//            colonyResources.setOxygen(colonyResources.getOxygen()-20);
//            colonyResources.setWater(colonyResources.getWater()-20);
//            colonyResources.setFood(colonyResources.getFood()-40);
            try {
                // Sleep for 1 second (1000 milliseconds)
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}


