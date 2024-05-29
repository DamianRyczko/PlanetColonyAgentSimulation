import java.util.*;

public class AMap {
    private final int gridSizeX;
    private final int gridSizeY;
    private Field[][] grid;
    private final int chanceOfRich;
    HashMap<Integer, String> typeToName;


    public AMap(int gridSizeX, int gridSizeY, int chanceOfRich) {
        this.gridSizeX = gridSizeX;
        this.gridSizeY = gridSizeY;
        this.chanceOfRich = chanceOfRich;
        grid = new Field[gridSizeX][gridSizeY];
    }

    public void setMap(ArrayList<Integer> chanceOftypes){
        for (int x = 0; x < gridSizeX; x++) {
            for (int y = 0; y < gridSizeY; y++) {
                Random rand = new Random();;
                int chanceSum = chanceOftypes.get(0);
                int randomNum = rand.nextInt(100);
                int i = 0;
                while (randomNum > chanceSum) {
                    i++;
                    chanceSum += chanceOftypes.get(i);
                }
                System.out.println(i);
                grid[x][y] = new Field(i ,chanceOfRich);
            }
        }
    }

    public int getGridSizeX() {
        return gridSizeX;
    }

    public int getGridSizeY() {
        return gridSizeY;
    }

    public Field[][] getGrid() {
        return grid;
    }

    public int getChanceOfRich() {
        return chanceOfRich;
    }

    public Field getField (int x, int y) {
        return grid[x][y];
    }

    public int getFieldType (int x, int y) {
        return grid[x][y].getTerrainType();
    }

    public boolean getFieldRich (int x, int y) {
        return grid[x][y].isRich();
    }

    public boolean getFieldEmpty (int x, int y) {
        return grid[x][y].isEmpty();
    }

    public void setFieldEmpty (int x, int y) {
        grid[x][y].setEmpty(false);
    }

}
