import java.util.List;

/**
 * A simple model of a jackalope.
 * jackalopes age, move, breed, and die.
 *
 * @author Saathveekan Satheshkumar, Manik Aggarwal, David J. Barnes and Michael Kölling
 * @version 2022.03.02 (2)
 */
public class Jackalope extends Herbivore
{

    // The name of the animal.
    private static String ANIMAL_NAME;

    /**
     * Create a new jackalope. A jackalope may be created with age
     * zero (a newborn) or with a random age.
     *
     * @param randomAge If true, the pegasus will have a random age.
     * @param field The field currently occupied.
     * @param location The location within the field.
     * @param animalName the name of animal
     */
    public Jackalope(boolean randomAge, Field field, Location location, String animalName)
    {
        super(field, location, animalName);
        ANIMAL_NAME = animalName;
        setAge(randomAge);

    }

    /**
     * Check whether this jackalope is to give birth at this step.
     * New births will be made into free adjacent locations.
     * Jackalopes only breed with opposite genders
     * @param newAnimals A list to return newly born jackalopes.
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
     * Check adjacent locations for jackalopes of opposite genders
     * @return returns true if a jackalope of opposite gender
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
        }
        return true;
    }


    /**
     * Checks if the gender parameter is opposite to the current animal's
     * gender.
     * @param animalsGender another animal's gender. true if male, false if female
     * @return Returns true if gender is opposite
     */
    private boolean checkOppoGender(boolean animalsGender) {
        return animalsGender != isMale;
    }

    /**
     * Gets the gender of the animal.
     * @return the gender.
     */
    private boolean getGender() {
        return isMale;
    }

}

