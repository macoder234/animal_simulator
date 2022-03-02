import java.util.Random;

public class Weather {

    private double SUNNY_PROBABILITY = 0.4;
    private double RAINY_PROBABILITY = 0.4;
    private double HURRICANE_PROBABILITY = 0.1;

    public Weather() {
    }

    private String setWeather() {
        Random rand = Randomizer.getRandom();
        if (rand.nextDouble() <= SUNNY_PROBABILITY) {
            return "Sunny";
        } else if (rand.nextDouble() <= RAINY_PROBABILITY) {
            return "Rainy";
        } else if (rand.nextDouble() <= HURRICANE_PROBABILITY) {
            return "Hurricane";
        }
        return "Cloudy";
    }

    public String getNewWeather() {
        return setWeather();
    }

}
