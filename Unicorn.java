import java.util.List;


/**
 * A simple model of a unicorn.
 * Unicorns age, move, breed, and die.
 *
 * @author Saathveekan Satheeshkumar, Manik Aggarwal, David J. Barnes and Michael Kölling
 * @version 2022.03.02 (2)
 */
public class Unicorn extends Herbivore {
    // The name of the animal.
    private static String ANIMAL_NAME;


    /**
     * Create a new unicorn. A unicorn may be created with age
     * zero (a new born) or with a random age.
     *
     * @param randomAge If true, the unicorn will have a random age.
     * @param field     The field currently occupied.
     * @param location  The location within the field.
     * @param animalName the name of animal
     */


    public Unicorn(boolean randomAge, Field field, Location location, String animalName) {
        super(field, location, animalName);
        ANIMAL_NAME = animalName;
        setAge(randomAge);
    }




    /**
     * Check whether or not this unicorn is to give birth at this step.
     * New births will be made into free adjacent locations.
     *
     * @param newAnimals A list to return newly born unicorns
     */
    protected void giveBirth(List<Animal> newAnimals) {
        // New unicorns are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        int births = breed();
        for (int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Unicorn young = new Unicorn(false, field, loc, ANIMAL_NAME);
            newAnimals.add(young);
        }
    }




}
