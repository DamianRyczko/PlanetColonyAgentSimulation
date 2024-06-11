import java.util.Random;
public class EventRandomizer extends Main implements EventInterface {
    private Random random;
    private int daysSinceLastEvent;
    private double chanceOfRandomEvent; // 0 for 0% and 1 for 100%

    public EventRandomizer(double chanceOfRandomEvent) {
        this.random = new Random();
        this.chanceOfRandomEvent = chanceOfRandomEvent;
        setDaysSinceLastEvent(0);
    }
    @Override
    public int drawEvent(int numberOfEvents) {
        if(getRandom().nextDouble() < getChanceOfRandomEvent() + (getDaysSinceLastEvent() * 0.02)) {
            setDaysSinceLastEvent(0);
            return random.nextInt(numberOfEvents)+1;
        }
        setDaysSinceLastEvent(getDaysSinceLastEvent()+1);
        return 0;
    }

    public Random getRandom() {
        return random;
    }

    public int getDaysSinceLastEvent() {
        return daysSinceLastEvent;
    }

    public double getChanceOfRandomEvent() {
        return chanceOfRandomEvent;
    }

    public void setDaysSinceLastEvent(int daysSinceLastEvent) {
        this.daysSinceLastEvent = daysSinceLastEvent;
    }


}

