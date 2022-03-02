import java.util.List;

/**
 * A simple model of a cyclops.
 * Cyclopes age, move, and die.
 *
 * @author Saathveekan Satheshkumar, Manik Aggarwal, David J. Barnes and Michael Kölling
 * @version 2022.03.02 (2)
 */
public class Cyclops extends Animal {
    // The name of the animal.
    private static String ANIMAL_NAME;

    // The food values of the griffon's prey.
    private final int WEREWOLF_FOOD_VALUE = data.getFoodValue("Werewolf");
    private final int GRIFFON_FOOD_VALUE = data.getFoodValue("Griffon");

    /**
     * Create a cyclops. A cyclops can be created as a newborn (age zero
     * and not hungry) or with a random age and food level.
     *
     * @param randomAge If true, the cyclops will have random age and hunger level.
     * @param field     The field currently occupied.
     * @param location  The location within the field.
     * @param animalName The name of the animal
     */
    public Cyclops(boolean randomAge, Field field, Location location, String animalName) {

        super(field, location, animalName);
        ANIMAL_NAME = animalName;
        int average = data.getAverageOfPreyValue(ANIMAL_NAME);
        setAge(randomAge);
    }

    /**
     * Looks for prey in adjacent locations to the location.
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
                if (animal instanceof Werewolf werewolf && werewolf.isAlive()) {
                    werewolf.setDead();
                    health += WEREWOLF_FOOD_VALUE;
                    return where;
                } else if (animal instanceof Griffon griffon && griffon.isAlive()) {
                    griffon.setDead();
                    health += GRIFFON_FOOD_VALUE;
                    return where;
                }
            }
        }
        return null;
    }

    /**
     * Check whether this cyclops is to give birth at this step.
     * New births will be made into free adjacent locations.
     *
     * @param newAnimals A list to return newly born cyclopes.
     */
    protected void giveBirth(List<Animal> newAnimals) {
        // New cyclopes are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        int births = breed();
        for (int b = 0; b < births && !free.isEmpty(); b++) {
            Location loc = free.remove(0);
            Cyclops young = new Cyclops(false, field, loc, ANIMAL_NAME);
            newAnimals.add(young);
        }
    }

}



