import java.util.List;

/**
 * A simple model of a mandrake.
 *
 *  @author Saathveekan Satheshkumar and Manik Aggarwal
 *  @version 2022.03.02 (2)
 */

public class Mandrake extends Plant {

    private String PLANT_NAME;

    /**
     * Create a new mandrake. A mandrake may be created with age
     * zero (a newborn) or with a random age.
     *
     * @param field The field currently occupied.
     * @param location The location within the field.
     * @param plantName The name of the plant.
     */
    protected Mandrake(boolean randomAge, Field field, Location location, String plantName) {
        super(field, location, plantName);
        PLANT_NAME = plantName;
        setAge(randomAge);
        growthRate = 1.5;
        currentFoodValue = data.getFoodValue(PLANT_NAME);
    }

    /**
     * @return Mandrake does not need to find food.
     */
    @Override
    protected Location findFood() {
        return null;
    }

    /**
     * Check whether this mandrake is to give birth at this step.
     * New births will be made into free adjacent locations.
     * @param newPlant A list to return newly born mandrakes.
     */
    @Override
    protected void giveBirth(List<Animal> newPlant) {
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        int births = breed();
        for(int b = 0; b < births && !free.isEmpty(); b++) {
            Location loc = free.remove(0);
            Mandrake young = new Mandrake(false, field, loc, PLANT_NAME);
            newPlant.add(young);
        }
    }

}
