import java.util.List;

/**
 * Lays out the basic functions for a herbivore.
 *
 *  @author Saathveekan Satheshkumar and Manik Aggarwal
 *  @version 2022.03.02 (2)
 */

public class Herbivore extends Animal {

    /**
     * Create a new herbivore at a given location.
     *
     * @param field The animal's field.
     * @param location The location with the field.
     * @param animalName The name of the animal
     */
    protected Herbivore(Field field, Location location, String animalName) {
        super(field, location, animalName);
    }

    /**
     * Finds plants that are near the herbivore.
     * @return The location of the plant that the herbivore moves to.
     */
    @Override
    protected Location findFood() {
        // Checks to see if max health has been reached.
        if (belowMaxHealth()) {
            Field field = getField();
            List<Location> adjacent = field.adjacentLocations(getLocation());
            for (Location where : adjacent) {
                Object animal = field.getObjectAt(where);
                if (animal instanceof Hyacinth hyacinth && hyacinth.isAlive()) {
                    health += hyacinth.getCurrentFoodValue();
                    hyacinth.setDead();
                    return where;
                } else if (animal instanceof Mandrake mandrake && mandrake.isAlive()) {
                    health += mandrake.getCurrentFoodValue();
                    mandrake.setDead();
                    return where;
                }
            }
        }
        return null;
    }


    /**
     * Done in subclasses
     */
    @Override
    protected void giveBirth (List < Animal > newAnimals) {
    }
}
