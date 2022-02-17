import java.util.List;
import java.util.Random;

/**
 * A simple model of a jackalope.
 * jackalopes age, move, breed, and die.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29 (2)
 */
public class Jackalope extends Animal
{
    // Characteristics shared by all jackalopes (class variables).

    // The age at which a jackalope can start to breed.
    private static final int BREEDING_AGE = 5;
    // The age to which a jackalope can live.
    private static final int MAX_AGE = 40;
    // The likelihood of a jackalope breeding.
    private static final double BREEDING_PROBABILITY = 0.12;
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 4;
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();
    
    // Individual characteristics (instance fields).
    
    // The jackalope's age.
    private int age;

    /**
     * Create a new jackalope. A jackalope may be created with age
     * zero (a new born) or with a random age.
     * 
     * @param randomAge If true, the jackalope will have a random age.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Jackalope(boolean randomAge, Field field, Location location)
    {
        super(field, location);
        age = 0;
        if(randomAge) {
            age = rand.nextInt(MAX_AGE);
        }
    }
    
    /**
     * This is what the jackalope does most of the time - it runs 
     * around. Sometimes it will breed or die of old age.
     * @param newjackalopes A list to return newly born jackalopes.
     */
    public void act(List<Animal> newjackalopes)
    {
        incrementAge();
        if(isAlive()) {
            giveBirth(newjackalopes);            
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
     * This could result in the jackalope's death.
     */
    private void incrementAge()
    {
        age++;
        if(age > MAX_AGE) {
            setDead();
        }
    }
    
    /**
     * Check whether or not this jackalope is to give birth at this step.
     * New births will be made into free adjacent locations.
     * @param newjackalopes A list to return newly born jackalopes.
     */
    private void giveBirth(List<Animal> newjackalopes)
    {
        // New jackalopes are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        int births = breed();
        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Jackalope young = new Jackalope(false, field, loc);
            newjackalopes.add(young);
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
     * A jackalope can breed if it has reached the breeding age.
     * @return true if the jackalope can breed, false otherwise.
     */
    private boolean canBreed()
    {
        return age >= BREEDING_AGE;
    }
}
