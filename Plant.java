import java.util.List;

/**
 * Lays out the basic functions for a plant.
 *
 * @author Saathveekan Satheshkumar, Manik Aggarwal
 * @version 2022.03.02 (2)
 */


public class Plant extends Animal {

    //a growth multiplier
    protected double growthRate;
    //the current food value of the plant due to the growth rate
    protected double currentFoodValue;
    // the max food value for a plant
    protected int maxTotalFoodValue = 50;

    /**
     * Create a new plant at a given location.
     *
     * @param field     The field currently occu
     * @param location  The location within the field.
     * @param plantName The name of the animal
     */

    protected Plant(Field field, Location location, String plantName) {
        super(field, location, plantName);
    }


    /**
     * This is what the plant does most of the time: it gives birth and increases its food value during the day.
     * In the process, it may die of old age.
     *
     * @param newPlant       A list to return newly born animals.
     * @param dayOfTime      if true, then it is day. if false, then night
     * @param currentWeather the current weather
     */


    public void act(List<Animal> newPlant, boolean dayOfTime, String currentWeather) {
        incrementAge();
        if (isAlive()) {
            giveBirth(newPlant);

            // if it is sunny, then the current food value is increased at even faster rate ( 2 times the growth rate).

            if (currentWeather.equals("Sunny") && currentFoodValue < maxTotalFoodValue / growthRate * 2) {
                currentFoodValue = currentFoodValue * growthRate * 2;
            }
            //If it is rainy, then the current food value is increased at an even faster rate (1.5 times the growth rate).
            else if (currentWeather.equals("Rainy") && currentFoodValue < maxTotalFoodValue / growthRate * 1.5) {
                currentFoodValue = currentFoodValue * growthRate * 1.5;
            }


            // if it is day, then the current food value is multiplied by the growth rate.
            else if (dayOfTime && currentFoodValue < maxTotalFoodValue / growthRate) {
                currentFoodValue = currentFoodValue * growthRate;
            }


        }
    }

    /**
     * Plants don't need to find
     */
    @Override
    protected Location findFood() {

        return null;
    }

    /**
     * Done in subclasses.
     */
    @Override
    protected void giveBirth(List<Animal> newPlant) {

    }

    /**
     * @return returns the current food value.
     */
    protected double getCurrentFoodValue() {
        return currentFoodValue;
    }


}
