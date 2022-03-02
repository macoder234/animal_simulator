import java.util.List;

/**
 * Lays out the basic functions for a plant.
 *
 *  @author Saathveekan Satheshkumar, Manik Aggarwale
 *  @version 2022.03.02 (2)
 */



public class Plant extends Animal {

    //a growth multiplier
    protected double growthRate;
    //the current food value of the plant due to the growth rate
    protected double currentFoodValue;
    // the max food value for a plant
    protected int maxTotalFoodValue = 50;

    /**
     * Create a new plant at a given location
     * @param field The field currently occupied
     *
     */

    protected Plant(Field field, Location location, String plantName) {
        super(field, location, plantName);
    }

    public void act(List<Animal> newPlant, boolean dayOfTime, String currentWeather) {
        incrementAge();
        if (isAlive()) {
            giveBirth(newPlant);

            // if it is day, then the current food value is multiplied by the growth rate.
            if (dayOfTime && currentFoodValue < maxTotalFoodValue / growthRate) {
                currentFoodValue = currentFoodValue * growthRate;
            }
        }
    }

    @Override
    protected Location findFood() {
        // Plants don't need to find food.
        return null;
    }

    @Override
    protected void giveBirth(List<Animal> newPlant) {
        // Done in subclasses.
    }

    /**
     * @return returns the current food value.
     */
    protected double getCurrentFoodValue() {
        return currentFoodValue;
    }


}
