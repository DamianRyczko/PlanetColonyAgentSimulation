import java.util.Random;

public class Engineer extends Astronaut{

    public Engineer (int id, int health, Position position, int dailyDistance){
        super(id,position,dailyDistance);
    }

    public void repair(Building building){
        if (building.getIsDamaged()){
            building.setIsDamaged(false);
            changeHealth();
        }
        if (building instanceof SolarPanel){
            if (((SolarPanel) building).getDirty()){
                ((SolarPanel) building).setIsDirty(false);
                ((SolarPanel) building).setPanelEffectiveness(1);
                changeHealth();
            }
        }
    }

    public void changeHealth (){
        int chanceOfGettingHurt = 10;
        int minimalWound = 10;
        int maximalWound = 20;
        Random rand = new Random();
        if (rand.nextInt(100) < chanceOfGettingHurt){
            setHealth(getHealth() - (rand.nextInt(maximalWound - minimalWound) + minimalWound));
        }
    }

    public void goToRepair(Building building){
        if (super.getPosition() == building.getPostion()){
            repair(building);
        }
        else{
            moveTo(building.getPostion());
        }
    }

}
