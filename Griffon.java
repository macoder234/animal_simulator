import java.util.List;

/**
 * A simple model of a griffon.
 * Griffons age, move, and die.
 *
 * @author Saathveekan Satheeshkumar, Manik Aggarwal, David J. Barnes and Michael Kölling
 * @version 2022.03.02 (2)
 */
public class Griffon extends Animal {

    // The name of the animal.
    private static String ANIMAL_NAME;

    // The food values of the griffon's prey.
    private final int JACKALOPE_FOOD_VALUE = data.getFoodValue("Jackalope");
    private final int PEGASUS_FOOD_VALUE = data.getFoodValue("Pegasus");

    /**
     * Create a griffon. A griffon can be created as a new born (age zero
     * and not hungry) or with a random age and food level.
     *
     * @param randomAge If true, the griffon will have random age and hunger level.
     * @param field     The field currently occupied.
     * @param location  The location within the field.
     * @param animalName The name of the animal
     */
    public Griffon(boolean randomAge, Field field, Location location, String animalName) {
        super(field, location, animalName);
        ANIMAL_NAME = animalName;
        int average = data.getAverageOfPreyValue(ANIMAL_NAME);
        setAge(randomAge);
    }

    /**
     * Look for prey adjacent to the current location.
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
                    System.out.println("G ate J");
                    jackalope.setDead();
                    health += JACKALOPE_FOOD_VALUE;
                    return where;
                } else if (animal instanceof Pegasus pegasus && pegasus.isAlive()) {
                    System.out.println("G ate P");
                    pegasus.setDead();
                    health += PEGASUS_FOOD_VALUE;
                    return where;
                }
            }
        }
        return null;
    }

    /**
     * Check whether these griffons are to give birth at this step.
     * New births will be made into free adjacent locations.
     *
     * @param newAnimals A list to return newly born griffons.
     */
    protected void giveBirth(List<Animal> newAnimals) {
        // New griffons are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        int births = breed();
        for (int b = 0; b < births && !free.isEmpty(); b++) {
            Location loc = free.remove(0);
            Griffon young = new Griffon(false, field, loc, ANIMAL_NAME);
            newAnimals.add(young);
        }
    }

}
