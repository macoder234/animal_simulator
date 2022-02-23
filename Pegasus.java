import java.util.List;
import java.util.Random;

public class Pegasus extends Animal {

    // Characteristics shared by all pegasi (class variables).

    // The age at which a pegasus can start to breed.
    private static final int BREEDING_AGE = 5;
    // The age to which a pegasus can live.
    private static final int MAX_AGE = 40;
    // The likelihood of a pegasus breeding.
    private static final double BREEDING_PROBABILITY = 0.12;
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 4;
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();

    // Individual characteristics (instance fields).

    // The pegasus's age.
    private int age;

    /**
     * Create a new pegasus. A pegasus may be created with age
     * zero (a new born) or with a random age.
     *
     * @param randomAge If true, the pegasus will have a random age.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Pegasus(boolean randomAge, Field field, Location location)
    {
        super(field, location);
        age = 0;
        if(randomAge) {
            age = rand.nextInt(MAX_AGE);
        }
    }

    /**
     * This is what the pegasus does most of the time - it runs 
     * around. Sometimes it will breed or die of old age.
     * @param newPegasi A list to return newly born pegasi.
     */
    public void act(List<Animal> newPegasi)
    {
        incrementAge();
        if(isAlive()) {
            giveBirth(newPegasi);
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
     * This could result in the pegasus's death.
     */
    private void incrementAge()
    {
        age++;
        if(age > MAX_AGE) {
            setDead();
        }
    }

    /**
     * Check whether or not this pegasus is to give birth at this step.
     * New births will be made into free adjacent locations.
     * @param newPegasi A list to return newly born pegasi.
     */
    private void giveBirth(List<Animal> newPegasi)
    {
        // New pegasi are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        int births = breed();
        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Pegasus young = new Pegasus(false, field, loc);
            newPegasi.add(young);
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
     * A pegasus can breed if it has reached the breeding age.
     * @return true if the pegasus can breed, false otherwise.
     */
    private boolean canBreed()
    {
        return age >= BREEDING_AGE;
    }
}