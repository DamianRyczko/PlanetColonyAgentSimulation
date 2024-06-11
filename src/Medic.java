import java.util.ArrayList;
import java.util.Random;

public class Medic extends Astronaut {
    int healingPower;

    public Medic(Position position, int min_healing_power, int max_healing_power, int dailyDistance, int id) {
        super(id, position, dailyDistance);
        setRandomHealingPower(min_healing_power, max_healing_power);
    }


    public Medic(int id) {
        super(id, new Position(0,0), 1000 );
        setRandomHealingPower(50, 50);
    }

    void setHealingPower(int healingPower) {
        this.healingPower = healingPower;
    }

    void setRandomHealingPower(int min_healing_power, int max_healing_power) {
        Random rand = new Random();
        int mod = max_healing_power - min_healing_power;
        this.healingPower = rand.nextInt(mod) + min_healing_power;
    }

    void heal(Astronaut patient){
        if ((patient.getHealth() + healingPower) <= 100) {
            patient.setHealth(patient.getHealth() + healingPower);
        }
        else{
            patient.setHealth(100);
        }
    }

   // @Override
    public void dailyTask(ArrayList<Building> buildings, ArrayList<Astronaut> astronauts, AMap map){

    }
}
