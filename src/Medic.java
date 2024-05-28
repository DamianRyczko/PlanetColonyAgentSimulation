import java.util.Random;

public class Medic extends Astronaut {
    int healingPower;

    public Medic(Position position, int min_healing_power, int max_healing_power, int dailyDistance, int id) {
        setHealth(100);
        setPosition(position);
        setRandomHealingPower(min_healing_power, max_healing_power);
        setDailyDistance(dailyDistance);
        setId(id);
        setOccupied(false);
    }


    public Medic() {
        setHealth(100);
        setPosition(new Position(0,0));
        setRandomHealingPower(50, 50);
        setDailyDistance(1000);
        setOccupied(false);
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
}
