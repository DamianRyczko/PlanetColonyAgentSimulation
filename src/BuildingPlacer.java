import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BuildingPlacer {
    private final int daysToCompleteBuilding = 20;
    private String mostNeededResource;
    private boolean currentlyBuilding;
    private int timeToCompletion;
    private int buildingX;
    private int buildingY;

    public int[] findClosestResource(int startX, int startY, String resourceType, String[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startX, startY});
        visited[startX][startY] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            if (grid[x][y].equals(resourceType)) {
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

    public void addBuildings(ArrayList<SolarPanel> solarPanels,
                             ArrayList<Farm> farms,
                             ArrayList<WaterPurifier> waterPurifiers,
                             ArrayList<OxygenGenerator> oxygenGenerators,
                             int numberOfEngineers,
                             ColonyResources colonyResources,
                             int startX,
                             int startY,
                             String[][] grid
    ) {
        if (!isCurrentlyBuilding()) {
            int water = colonyResources.getWater();
            int food = colonyResources.getFood();
            int electricity = colonyResources.getElectricity();
            int oxygen = colonyResources.getOxygen();

            setMostNeededResource("water"); // Default to water
            int smallest = water;

            if (food < smallest) {
                smallest = food;
                setMostNeededResource("food");
            }
            if (electricity < smallest) {
                smallest = electricity;
                setMostNeededResource("electricity");
            }
            if (oxygen < smallest) {
                setMostNeededResource("oxygen");
            }

            setTimeToCompletion(getDaysToCompleteBuilding() / numberOfEngineers);

            int[] coordinates = findClosestResource(startX, startY, mostNeededResource, grid);
            if (coordinates != null) {
                System.out.println("Building " + mostNeededResource + " at coordinates: (" + coordinates[0] + ", " + coordinates[1] + ")");
                setCurrentlyBuilding(true);
                setBuildingX(coordinates[0]);
                setBuildingY(coordinates[1]);
            } else {
                System.out.println("No available resource found for " + mostNeededResource);
            }
        }
        setTimeToCompletion(getTimeToCompletion()-1);
        if (getTimeToCompletion() == 0){
            switch(getMostNeededResource()){
                case "water":
                    waterPurifiers.add(new WaterPurifier(getBuildingX(),getBuildingY()));
                    setCurrentlyBuilding(false);
                    break;
                case "food":
                    farms.add(new Farm(getBuildingX(),getBuildingY()));
                    setCurrentlyBuilding(false);
                    break;
                case "electricity":
                    solarPanels.add(new SolarPanel(getBuildingX(),getBuildingY()));
                    setCurrentlyBuilding(false);
                    break;
                case "oxygen":
                    oxygenGenerators.add(new OxygenGenerator(getBuildingX(),getBuildingY()));
                    setCurrentlyBuilding(false);
                    break;
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

    public String getMostNeededResource() {
        return mostNeededResource;
    }

    public void setMostNeededResource(String mostNeededResource) {
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
