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


        ArrayList<Building> buildings = new ArrayList<>();
        ArrayList<Astronaut> astronauts = new ArrayList<>();




        var buildingPlacer = new BuildingPlacer();


        AMap map = new AMap(70, 50, 20);
        GlobalVariables.GridSizeX = map.getGridSizeX();
        GlobalVariables.GridSizeY = map.getGridSizeY();
        GlobalVariables.GridSize = map.getGridSize();
        ArrayList<Integer> chanceOfTypes = new ArrayList<>();
        chanceOfTypes.add(95);
        chanceOfTypes.add(3);
        chanceOfTypes.add(2);
        map.setMap(chanceOfTypes);

        int startX = 24;
        int startY = 24;
        int numberOfEngineers = 20;

        astronauts.add(new Collector(new Position(5,15),4,1,34, new Position(startX,startY)));


        astronauts.add(new Medic(new Position(3,5),40, 60, 2,3));
        astronauts.add(new Medic(new Position(34,10),40, 60, 2,4));
        astronauts.add(new Medic(new Position(12,23),40, 60, 2,5));
        astronauts.add(new Medic(new Position(42,49),40, 60, 2,6));
        astronauts.add(new Medic(new Position(16,32),40, 60, 2,7));

        astronauts.add(new Collector(new Position(57, 42), 3, 8, 100, new Position(startX,startY)));

        astronauts.get(6).setHealth(10);

        try {
            Map<String, Map<String, String>> config = ConfigLoader.loadConfig("src/config.txt");

            buildings.add(new FusionReactor(config.get("FusionReactor"),startX,startY));
            map.setFieldEmpty(startX,startY);



        } catch (IOException e) {
            e.printStackTrace();
        }

        SimulationFrame frame = new SimulationFrame(colonyResources, map, buildings, astronauts);
        var eventSimulator = new EventSimulator(eventRandomizer, frame.getSimulationPanelLeft());





        for (int i = 0; i<100; i++) {
            NewDay.nextDay(buildings, astronauts, map);

            frame.getSimulationPanelLeft().updateResources(colonyResources);

            eventSimulator.generateEvent(buildings);
            frame.repaintMap();
            //adds buildings
//            buildingPlacer.addBuildings(buildings, numberOfEngineers, colonyResources, startX, startY, map);
//            Astronaut astronaut = astronauts.get(0);
//            if (astronaut instanceof Collector) {
//                Collector collector = (Collector) astronaut;
//                collector.setPosition(new Position(10+i, 25+i));
//                collector.setHealth(collector.getHealth()-10);
//                if(collector.getHealth() <= 0){
//                    collector.kill();
//                }
//            }
            frame.repaintMap();

            //cycles all buildings
            for (Object building : buildings) {
                //System.out.println("+++++++++++++++++++++++++++++");
                if (building instanceof Farm farm) {
                    //System.out.println("This is a Farm.");
                    // Cast to Farm to access Farm-specific methods
                    farm.dayCycle(colonyResources);
                    farm.show();

                } else if (building instanceof SolarPanel solarPanel) {
                    //System.out.println("This is a SolarPanel.");
                    solarPanel.dayCycle(colonyResources);
                    solarPanel.show();
                } else if (building instanceof FusionReactor fusionReactor) {
                   // System.out.println("This is a FusionReactor.");
                    fusionReactor.dayCycle(colonyResources);
                    fusionReactor.show();
                } else if (building instanceof OxygenGenerator oxygenGenerator) {
                    //System.out.println("This is an OxygenGenerator.");
                    oxygenGenerator.dayCycle(colonyResources);
                    oxygenGenerator.show();
                } else if (building instanceof WaterPurifier waterPurifier) {
                    //System.out.println("This is a WaterPurifier.");
                    waterPurifier.dayCycle(colonyResources);
                    waterPurifier.show();
                }
            }
            colonyResources.setOxygen(colonyResources.getOxygen()-20);
            colonyResources.setWater(colonyResources.getWater()-20);
            colonyResources.setFood(colonyResources.getFood()-40);
            try {
                // Sleep for 1 second (1000 milliseconds)
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}


