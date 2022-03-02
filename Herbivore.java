import java.util.List;

/**
 * Lays out the basic functions for a herbivore.
 *
 *  @author Saathveekan Satheshkumar, Manik Aggarwal, David J. Barnes and Michael Kölling
 *  @version 2022.03.02 (2)
 */

public class Herbivore extends Animal {

    /**
     * Sends all animals information to the superclass.
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
        if (!exceedMaxHealth()) {
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

    @Override
    protected void giveBirth (List < Animal > newAnimals) {
    }
}
