public class Collector extends Astronaut {
    private Position goal;
    private String resource;
    private int idResource;

    public Collector(Position position, int dailyDistance, int id) {
        setHealth(100);
        setPosition(position);
        setDailyDistance(dailyDistance);
        setId(id);
        setOccupied(false);
    }

    public void collect (){
        if (isOccupied()){
            return;
        }
        findGoal();

    }

     private void findGoal(){

     }


}
