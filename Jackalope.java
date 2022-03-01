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

    private AnimalData data = new AnimalData();
    // Characteristics shared by all jackalopes (class variables).
    private static String ANIMAL_NAME;
    // The age at which a jackalope can start to breed.
//    private final int BREEDING_AGE = data.getBreedingAge(ANIMAL_NAME);
//    // The age to which a jackalope can live.
//    private final int MAX_AGE = data.getMaxAge(ANIMAL_NAME);
//    // The likelihood of a jackalope breeding.
//    private final double BREEDING_PROBABILITY = data.getBreedingProbability(ANIMAL_NAME);
//    // The maximum number of births.
//    private final int MAX_LITTER_SIZE = data.getMaxLitterSize(ANIMAL_NAME);
    // A shared random number generator to control breeding.


    private final int HYACINTH_FOOD_VALUE = data.getFoodValue("Hyacinth");
    private final int MANDRAKE_FOOD_VALUE = data.getFoodValue("Mandrake");

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
    public Jackalope(boolean randomAge, Field field, Location location, String animalName)
    {
        super(field, location, animalName);
        ANIMAL_NAME = animalName;
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

//    protected Location findFood() {
//        if (!exceedMaxHealth()) {
//            Field field = getField();
//            List<Location> adjacent = field.adjacentLocations(getLocation());
//            for (Location where : adjacent) {
//                Object plant = field.getObjectAt(where);
////            for (Animal prey: data.getAnimalPrey(ANIMAL_NAME)){
//                if (plant instanceof Hyacinth hyacinth && hyacinth.isAlive()) {
//                    hyacinth.setDead();
//                    health += HYACINTH_FOOD_VALUE;
//                    return where;
//                } else if (plant instanceof Mandrake mandrake && mandrake.isAlive()) {
//                    mandrake.setDead();
//                    health += MANDRAKE_FOOD_VALUE;
//                    return where;
//                }
//            }
//        }
//        return null;
//    }


    /**
     * Increase the age.
     * This could result in the jackalope's death.
     */
//    private void incrementAge()
//    {
//        age++;
//        if(age > MAX_AGE) {
//            setDead();
//         }}

    
    /**
     * Check whether or not this jackalope is to give birth at this step.
     * New births will be made into free adjacent locations.
     * @param newjackalopes A list to return newly born jackalopes.
     */
    protected void giveBirth(List<Animal> newAnimals)
    {
        // New jackalopes are born into adjacent locations.
        // Get a list of adjacent free locations.
        if (oppGenderPresent()) {
            Field field = getField();
            List<Location> free = field.getFreeAdjacentLocations(getLocation());
            int births = breed();
            for (int b = 0; b < births && free.size() > 0; b++) {
                Location loc = free.remove(0);
                Jackalope young = new Jackalope(false, field, loc, ANIMAL_NAME);

                newAnimals.add(young);
            }
        }
   }


    /**
     * Check adjacent locations and return true if locations contain
     * animals of opposite genders.
     * @return returns if there is an opposite gender near
     */
    private boolean oppGenderPresent() {

        if (data.getOppoGenderRequired(ANIMAL_NAME)) {
            Field field = getField();
            List<Location> adjacent = field.adjacentLocations(getLocation());
            for (Location where : adjacent) {
                Object animal = field.getObjectAt(where);
                return animal instanceof Jackalope jackalope
                        && jackalope.isAlive()
                        && jackalope.canBreed()
                        && checkOppoGender((jackalope.getGender()));
            }
        }return true;
    }


    /*
    @param animalsGender. If true the animal is a male, if false then female
     */
    private boolean checkOppoGender(boolean animalsGender) {
        return animalsGender != isMale;
    }

    private boolean getGender() {
        return isMale;
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

    /**
     * A jackalope can breed if it has reached the breeding age.
     * @return true if the jackalope can breed, false otherwise.
     */
//    private boolean canBreed()
//    {
//        return age >= BREEDING_AGE;
//    }
}
