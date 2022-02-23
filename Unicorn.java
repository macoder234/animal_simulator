import java.util.List;
import java.util.Random;

/**
 * A simple model of a unicorn.
 * unicorns age, move, breed, and die.
 *
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29 (2)
 */
public class Unicorn extends Animal
{
    // Characteristics shared by all unicorns (class variables).

    // The age at which a unicorn can start to breed.
    private static final int BREEDING_AGE = 5;
    // The age to which a unicorn can live.
    private static final int MAX_AGE = 40;
    // The likelihood of a unicorn breeding.
    private static final double BREEDING_PROBABILITY = 0.12;
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 4;
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();

    // Individual characteristics (instance fields).

    // The unicorn's age.
    private int age;

    /**
     * Create a new unicorn. A unicorn may be created with age
     * zero (a new born) or with a random age.
     *
     * @param randomAge If true, the unicorn will have a random age.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Unicorn(boolean randomAge, Field field, Location location)
    {
        super(field, location);
        age = 0;
        if(randomAge) {
            age = rand.nextInt(MAX_AGE);
        }
    }

    /**
     * This is what the unicorn does most of the time - it runs
     * around. Sometimes it will breed or die of old age.
     * @param newUnicorns A list to return newly born unicorns.
     */
    public void act(List<Animal> newUnicorns)
    {
        incrementAge();
        if(isAlive()) {
            giveBirth(newUnicorns);
            // Try to move into a free location.
            Location newLocation = getField().freeAdjacentLocation(getLocation());
            if(newLocation != null) {
                setLocation(newLocation);
            }
            else {
                // Overcrowding.
                setDead();
            }
        }
    }

    /**
     * Increase the age.
     * This could result in the unicorn's death.
     */
    private void incrementAge()
    {
        age++;
        if(age > MAX_AGE) {
            setDead();
        }
    }

    /**
     * Check whether or not this unicorn is to give birth at this step.
     * New births will be made into free adjacent locations.
     * @param newUnicorns A list to return newly born unicorns.
     */
    private void giveBirth(List<Animal> newUnicorns)
    {
        // New unicorns are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        int births = breed();
        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Unicorn young = new Unicorn(false, field, loc);
            newUnicorns.add(young);
        }
    }

    /**
     * Generate a number representing the number of births,
     * if it can breed.
     * @return The number of births (may be zero).
     */
    private int breed()
    {
        int births = 0;
        if(canBreed() && rand.nextDouble() <= BREEDING_PROBABILITY) {
            births = rand.nextInt(MAX_LITTER_SIZE) + 1;
        }
        return births;
    }

    /**
     * A unicorn can breed if it has reached the breeding age.
     * @return true if the unicorn can breed, false otherwise.
     */
    private boolean canBreed()
    {
        return age >= BREEDING_AGE;
    }
}