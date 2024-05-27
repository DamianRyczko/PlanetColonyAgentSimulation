public class Position {
    private int x;
    private int y;

    // Konstruktor
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Gettery
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // Settery
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    // Metoda toString do reprezentacji tekstowej
    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    // Metoda equals do porównywania obiektów
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (x != position.x) return false;
        return y == position.y;
    }

    // Metoda hashCode
    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
