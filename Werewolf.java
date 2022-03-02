import java.util.List;

/**
 * A simple model of a werewolf.
 * Werewolves age, move, and die.
 *
 * @author Saathveekan Satheeshkumar, Manik Aggarwal, David J. Barnes and Michael Kölling
 * @version 2022.03.02 (2)
 */
public class Werewolf extends Animal {

    // The name of the animal.
    private static String ANIMAL_NAME;

    // The food values of the werewolf's prey.
    private final int JACKALOPE_FOOD_VALUE = data.getFoodValue("Jackalope");
    private final int UNICORN_FOOD_VALUE = data.getFoodValue("Unicorn");


    /**
     * Create a werewolf. A werewolf can be created as a newborn (age zero
     * and not hungry) or with a random age and food level.
     *
     * @param randomAge If true, the werewolf will have random age and hunger level.
     * @param field     The field currently occupied.
     * @param location  The location within the field.*
     * @param animalName The name of the animal
     */
    public Werewolf(boolean randomAge, Field field, Location location, String animalName) {

        super(field, location, animalName);
        ANIMAL_NAME = animalName;
        setAge(randomAge);
    }

    /**
     * Look for werewolves adjacent to the current location.
     * Only the first live prey is eaten.
     *
     * @return Where food was found, or null if it wasn't.
     */
    protected Location findFood() {
        if (belowMaxHealth()) {
            Field field = getField();
            List<Location> adjacent = field.adjacentLocations(getLocation());
            for (Location where : adjacent) {
                Object animal = field.getObjectAt(where);
                if (animal instanceof Jackalope jackalope && jackalope.isAlive()) {
                    jackalope.setDead();
                    health += JACKALOPE_FOOD_VALUE;
                    return where;
                } else if (animal instanceof Unicorn unicorn && unicorn.isAlive()) {
                    unicorn.setDead();
                    health += UNICORN_FOOD_VALUE;
                    return where;
                }
            }
        }
        return null;
    }


    /**
     * Check whether this werewolf is to give birth at this step.
     * New births will be made into free adjacent locations.
     *
     * @param newAnimals A list to return newly born werewolves.
     */
    protected void giveBirth(List<Animal> newAnimals) {
        // New werewolves are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        int births = breed();
        for (int b = 0; b < births && !free.isEmpty(); b++) {
            Location loc = free.remove(0);
            Werewolf young = new Werewolf(false, field, loc, ANIMAL_NAME);
            newAnimals.add(young);
        }
    }

}