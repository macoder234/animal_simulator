import java.util.Random;

/**
 * A model for the weather.
 *
 *  @author Saathveekan Satheshkumar and Manik Aggarwal
 *  @version 2022.03.02 (2)
 */
public class Weather {

    // The probability for each weather event
    private double SUNNY_PROBABILITY = 0.4;
    private double RAINY_PROBABILITY = 0.4;
    private double HURRICANE_PROBABILITY = 0.01;

    // True if there is daylight.
    private boolean isDay;

    public Weather() {
    }

    /**
     * Randomly sets the weather for each step (day and night).
     * @return the new weather to be set.
     */
    public String setWeather() {
        Random rand = Randomizer.getRandom();
        if (isDay && rand.nextDouble() <= SUNNY_PROBABILITY) {
            return "Sunny";
        } else if (rand.nextDouble() <= RAINY_PROBABILITY) {
            return "Rainy";
        } else if (rand.nextDouble() <= HURRICANE_PROBABILITY) {
            return "Hurricane";
        }
        return "Cloudy";
    }

    /**
     * Changes the time of day it is.
     * @param isDay boolean which is true when there is daylight.
     */
    public void timeOfDay(boolean isDay) {
        this.isDay = isDay;
    }

}
