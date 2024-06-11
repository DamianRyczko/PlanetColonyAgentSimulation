import java.util.Random;

public class Field extends AMap {
    private int terrainType;
    private final boolean Rich;
    private boolean empty;
    private int chanceOfRich;

    public Field(int terrainType, int chanceOfRich) {

        this.terrainType = terrainType;
        Random random = new Random();
        if (random.nextInt(100) <= chanceOfRich) {
            this.Rich = true;
        }
        else{
            this.Rich = false;
        }
        this.empty = true;

    }

    public boolean isRich() {
        return Rich;
    }

    public boolean isEmpty() {
        return empty;
    }

    public int getTerrainType() {
        return terrainType;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public void setTerrainType(int terrainType) {
        this.terrainType = terrainType;
    }
}
