import java.util.List;
import java.util.Random;

/**
 * A simple model of a fox.
 * Werewolves age, move, eat rabbits, and die.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29 (2)
 */
public class Werewolf extends Animal
{
    // Characteristics shared by all werewolves (class variables).

    private static String ANIMAL_NAME;
    //

    // The age to which a fox can live.
//    private final int MAX_AGE = data.getMaxAge(ANIMAL_NAME);
    // The likelihood of a werewolf breeding.
//    private final double BREEDING_PROBABILITY = data.getBreedingProbability(ANIMAL_NAME);
    // The maximum number of births.
//    private final int MAX_LITTER_SIZE = data.getMaxLitterSize(ANIMAL_NAME);
    // The food value of a single rabbit. In effect, this is the
    // number of steps a fox can go before it has to eat again.
    private final int JACKALOPE_FOOD_VALUE = data.getFoodValue("Jackalope");
    private final int UNICORN_FOOD_VALUE = data.getFoodValue("Unicorn");

    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();
    
    // Individual characteristics (instance fields).
    // The fox's age.
//    private int age;
    // The fox's food level, which is increased by eating rabbits.
//    private int health;

    /**
     * Create a werewolf. A werewolf can be created as a new born (age zero
     * and not hungry) or with a random age and food level.
     * 
     * @param randomAge If true, the werewolf will have random age and hunger level.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Werewolf(boolean randomAge, Field field, Location location, String animalName){

        super(field, location,animalName);
        ANIMAL_NAME = animalName;
        setHealthAndAge(randomAge);
    }
    
    /**
     * This is what the werewolf does most of the time: it hunts for
     * rabbits. In the process, it might breed, die of hunger,
     * or die of old age.
     * @param field The field currently occupied.
     * @param newWerewolves A list to return newly born werewolves.
     */
//    public void act(List<Animal> newWerewolves, boolean isDay)
//    {
//        incrementAge();
//        incrementHunger();
//        if(isAlive()) {
//            giveBirth(newWerewolves);
//            // Move towards a source of food if found.
//            Location newLocation = findFood();
//            if(newLocation == null) {
//                // No food found - try to move to a free location.
//                newLocation = getField().freeAdjacentLocation(getLocation());
//            }
//            // See if it was possible to move.
//            if(newLocation != null) {
//                setLocation(newLocation);
//            }
//            else {
//                // Overcrowding.
//                setDead();
//            }
//        }
//    }

//    /**
//     * Increase the age. This could result in the werewolf's death.
//     */
//    private void incrementAge()
//    {
//        age++;
//        if(age > MAX_AGE) {
//            setDead();
//        }
//    }
    
//    /**
//     * Make this werewolf more hungry. This could result in the werewolf's death.
//     */
//    private void incrementHunger()
//    {
//        health--;
//        if(health <= 0) {
//            setDead();
//        }
//    }
    
    /**
     * Look for rabbits adjacent to the current location.
     * Only the first live rabbit is eaten.
     * @return Where food was found, or null if it wasn't.
     */
    protected Location findFood()
    {
        if (!exceedMaxHealth()) {
            Field field = getField();
            List<Location> adjacent = field.adjacentLocations(getLocation());
            for (Location where : adjacent) {
                Object animal = field.getObjectAt(where);
//            for (Animal prey: data.getAnimalPrey(ANIMAL_NAME)){
                if (animal instanceof Jackalope jackalope && jackalope.isAlive()) {
                    System.out.println("W ate J");
                    jackalope.setDead();
                    health += JACKALOPE_FOOD_VALUE;
                    return where;
                }
                else if (animal instanceof Unicorn unicorn && unicorn.isAlive()) {
                    System.out.println("W ate U");
                    unicorn.setDead();
                    health += UNICORN_FOOD_VALUE;
                    return where;
                }
            }
        }
        return null;
    }
    /**
     * Check whether or not this werewolf is to give birth at this step.
     * New births will be made into free adjacent locations.
     * @param newAnimals A list to return newly born werewolves.
     */
    protected void giveBirth(List<Animal> newAnimals)
    {
        // New werewolves are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        int births = breed();
        for(int b = 0; b < births && !free.isEmpty(); b++) {
            Location loc = free.remove(0);
            Werewolf young = new Werewolf(false, field, loc, ANIMAL_NAME);
            newAnimals.add(young);
        }
    }
        
//    /**
//     * Generate a number representing the number of births,
//     * if it can breed.
//     * @return The number of births (may be zero).
//     */
//    private int breed()
//    {
//        int births = 0;
//        if(canBreed() && rand.nextDouble() <= BREEDING_PROBABILITY) {
//            births = rand.nextInt(MAX_LITTER_SIZE) + 1;
//        }
//        return births;
//    }

//    /**
//     * A werewolf can breed if it has reached the breeding age.
//     */
//    private boolean canBreed()
//    {
//        return age >= BREEDING_AGE;
//    }
}