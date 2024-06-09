import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BuildingPlacer {
    private final int daysToCompleteBuilding = 20;
    private int mostNeededResource;
    private boolean currentlyBuilding;
    private int timeToCompletion;
    private int buildingX;
    private int buildingY;

    public int[] findClosestResource(int startX, int startY, int resourceType, AMap map) {
        int rows = map.getGridSizeX();
        int cols = map.getGridSizeY();
        boolean[][] visited = new boolean[rows][cols];
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startX, startY});
        visited[startX][startY] = true;

        if (resourceType == 3 || resourceType == 4){
            resourceType = 0;
        }

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            if (map.getFieldType(x, y) == resourceType && map.getFieldEmpty(x, y)) {
                map.setFieldEmpty(x,y);
                return new int[]{x, y};
            }

            for (int[] direction : directions) {
                int newX = x + direction[0];
                int newY = y + direction[1];

                if (newX >= 0 && newY >= 0 && newX < rows && newY < cols && !visited[newX][newY]) {
                    queue.add(new int[]{newX, newY});
                    visited[newX][newY] = true;
                }
            }
        }

        return null; // Resource not found
    }

    public void addBuildings( ArrayList<Building> buildings,
                             int numberOfEngineers,
                             ColonyResources colonyResources,
                             int startX,
                             int startY,
                             AMap map
    ) {
        if (!isCurrentlyBuilding()) {
            int water = colonyResources.getWater();
            int food = colonyResources.getFood();
            int electricity = colonyResources.getElectricity();
            int oxygen = colonyResources.getOxygen();

            // 1 for food
            // 2 for water
            // 3 for electricityS
            // 4 for oxygen
            setMostNeededResource(1); // Default to food
            int smallest = food;

            if (water < smallest) {
                smallest = water;
                setMostNeededResource(2);
            }
            if (electricity < smallest) {
                smallest = electricity;
                setMostNeededResource(3);
            }
            if (oxygen < smallest) {
                setMostNeededResource(4);
            }

            setTimeToCompletion(getDaysToCompleteBuilding());

            int[] coordinates = findClosestResource(startX, startY, mostNeededResource, map);
            if (coordinates != null) {
                System.out.println("Building " + mostNeededResource + " at coordinates: (" + coordinates[0] + ", " + coordinates[1] + ")");
                setCurrentlyBuilding(true);
                setBuildingX(coordinates[0]);
                setBuildingY(coordinates[1]);
            } else {
                System.out.println("No available resource found for " + mostNeededResource);
            }
        }
        setTimeToCompletion(getTimeToCompletion()-numberOfEngineers);

        if (getTimeToCompletion() == 0){
            try {
                Map<String, Map<String, String>> config = ConfigLoader.loadConfig("src/config.txt");

                switch(getMostNeededResource()){
                    case 1:
                        buildings.add(new Farm(config.get("Farm"),getBuildingX(),getBuildingY()));
                        System.out.println("===========================================");
                        System.out.println("Placed a FARM");
                        System.out.println("============================================");
                        setCurrentlyBuilding(false);
                        break;
                    case 2:
                        buildings.add(new WaterPurifier(config.get("WaterPurifier"),getBuildingX(),getBuildingY()));
                        setCurrentlyBuilding(false);
                        System.out.println("===========================================");
                        System.out.println("Placed a WaterPurifer");
                        System.out.println("============================================");
                        break;
                    case 3:
                        buildings.add(new SolarPanel(config.get("SolarPanel"),getBuildingX(),getBuildingY()));
                        setCurrentlyBuilding(false);
                        System.out.println("===========================================");
                        System.out.println("Placed a SolarPanel");
                        System.out.println("============================================");
                        break;
                    case 4:
                        buildings.add(new OxygenGenerator(config.get("OxygenGenerator"),getBuildingX(),getBuildingY()));
                        setCurrentlyBuilding(false);
                        System.out.println("===========================================");
                        System.out.println("Placed a OxygenGenerator");
                        System.out.println("============================================");
                        break;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public int getDaysToCompleteBuilding() {
        return daysToCompleteBuilding;
    }

    public int getTimeToCompletion() {
        return timeToCompletion;
    }

    public void setTimeToCompletion(int timeToCompletion) {
        this.timeToCompletion = timeToCompletion;
    }

    public int getMostNeededResource() {
        return mostNeededResource;
    }

    public void setMostNeededResource(int mostNeededResource) {
        this.mostNeededResource = mostNeededResource;
    }

    public boolean isCurrentlyBuilding() {
        return currentlyBuilding;
    }

    public void setCurrentlyBuilding(boolean currentlyBuilding) {
        this.currentlyBuilding = currentlyBuilding;
    }

    public int getBuildingX() {
        return buildingX;
    }

    public void setBuildingX(int buildingX) {
        this.buildingX = buildingX;
    }

    public int getBuildingY() {
        return buildingY;
    }

    public void setBuildingY(int buildingY) {
        this.buildingY = buildingY;
    }
}
