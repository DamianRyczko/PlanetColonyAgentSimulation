import java.util.Random;

public class Medic extends Astronaut {
    int healingPower;

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
