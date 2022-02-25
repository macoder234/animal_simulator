import java.util.List;
import java.util.Random;

public class Cyclops extends Animal
{

    private AnimalData data = new AnimalData();
    // Characteristics shared by all foxes (class variables).
    private static String ANIMAL_NAME = "Cyclops";
    // The age at which a fox can start to breed.
    private final int BREEDING_AGE = data.getBreedingAge(ANIMAL_NAME);
    // The age to which a fox can live.
    private final int MAX_AGE = data.getMaxAge(ANIMAL_NAME);
    // The likelihood of a fox breeding.
    private final double BREEDING_PROBABILITY = data.getBreedingProbability(ANIMAL_NAME);
    // The maximum number of births.
    private final int MAX_LITTER_SIZE = data.getMaxLitterSize(ANIMAL_NAME);
    // The food value of a single jackalope. In effect, this is the
    // number of steps a werewolf can go before it has to eat again.
    private final int JACKALOPE_FOOD_VALUE = data.getFoodValue("Jackalope");
    // The food value of a single pegasus. In effect, this is the
    // number of steps a werewolf can go before it has to eat again.
    private final int PEGASUS_FOOD_VALUE = data.getFoodValue("Pegasus");
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();
    // Individual characteristics (instance fields).
    // The fox's age.
    private int age;
    // The fox's food level, which is increased by eating rabbits.
    private int health;

    /**
     * Create a fox. A fox can be created as a new born (age zero
     * and not hungry) or with a random age and food level.
     *
     * @param randomAge If true, the fox will have random age and hunger level.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Cyclops(boolean randomAge, Field field, Location location)
    {
        super(field, location);
        if(randomAge) {
            age = rand.nextInt(MAX_AGE);
            health = rand.nextInt(JACKALOPE_FOOD_VALUE);
        }
        else {
            age = 0;
            health = JACKALOPE_FOOD_VALUE;
        }
    }

    /**
     * This is what the fox does most of the time: it hunts for
     * rabbits. In the process, it might breed, die of hunger,
     * or die of old age.
     //     * @param field The field currently occupied.
     * @param newCyclopes A list to return newly born foxes.
     */
    public void act(List<Animal> newCyclopes)
    {
        incrementAge();
        incrementHunger();
        if(isAlive()) {
            giveBirth(newCyclopes);
            // Move towards a source of food if found.
            Location newLocation = findFood();
            if(newLocation == null) {
                // No food found - try to move to a free location.
                newLocation = getField().freeAdjacentLocation(getLocation());
            }
            // See if it was possible to move.
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
     * Increase the age. This could result in the fox's death.
     */
    private void incrementAge()
    {
        age++;
        if(age > MAX_AGE) {
            setDead();
        }
    }

    /**
     * Make this fox more hungry. This could result in the fox's death.
     */
    private void incrementHunger()
    {
        health--;
        if(health <= 0) {
            setDead();
        }
    }

    /**
     * Look for rabbits adjacent to the current location.
     * Only the first live rabbit is eaten.
     * @return Where food was found, or null if it wasn't.
     */
    private Location findFood()
    {
        Field field = getField();
        List<Location> adjacent = field.adjacentLocations(getLocation());
        for (Location where : adjacent) {
            Object animal = field.getObjectAt(where);
            if (animal instanceof Jackalope jackalope && jackalope.isAlive()) {
                jackalope.setDead();
                health = RABBIT_FOOD_VALUE;
                return where;
            }
        }
        return null;
    }

    /**
     * Check whether or not this fox is to give birth at this step.
     * New births will be made into free adjacent locations.
     * @param newCyclopes A list to return newly born foxes.
     */
    private void giveBirth(List<Animal> newCyclopes)
    {
        // New foxes are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        int births = breed();
        for(int b = 0; b < births && !free.isEmpty(); b++) {
            Location loc = free.remove(0);
            Cyclops young = new Cyclops(false, field, loc);
            newCyclopes.add(young);
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
     * A fox can breed if it has reached the breeding age.
     */
    private boolean canBreed()
    {
        return age >= BREEDING_AGE;
    }

}



