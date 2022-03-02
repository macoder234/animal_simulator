import java.util.List;
import java.util.Random;
/**
 * A class representing shared characteristics of animals.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29 (2)
 */
public abstract class Animal
{
    // Whether the animal is alive or not.
    private boolean alive;
    // The animal's field
    private Field field;
    // The animal's position in the field.
    private Location location;

    private String nameOfAnimal;

    protected AnimalData data = new AnimalData();

    // The age at which a fox can start to breed.
    protected int BREEDING_AGE;
    protected int MAX_AGE;
    protected double BREEDING_PROBABILITY;
    protected int MAX_LITTER_SIZE;
    protected int MAX_HEALTH;

    protected int age;
    protected int health;
    protected Boolean isMale;
    // A shared random number generator to control breeding.
    protected static final Random rand = Randomizer.getRandom();
    //A shared boolean value to represent day or night.
    protected static boolean isDay;

    /**
     * Create a new animal at location in field.
     * 
     * @param field The field currently occupied.
     * @param location The location within the field.
     */


    protected Animal(Field field, Location location, String animalName)
    {

        nameOfAnimal = animalName;
        alive = true;
        this.field = field;
        setLocation(location);
        fillAnimalData();
        assignGender();
  //      isDay = true;
    }

    private void fillAnimalData()
    {
        BREEDING_AGE = data.getBreedingAge(nameOfAnimal);
        MAX_AGE = data.getMaxAge(nameOfAnimal);
        BREEDING_PROBABILITY = data.getBreedingProbability(nameOfAnimal);
        MAX_LITTER_SIZE = data.getMaxLitterSize(nameOfAnimal);
    }

    /**
     * This is what the werewolf does most of the time: it hunts for
     * rabbits. In the process, it might breed, die of hunger,
     * or die of old age.
     //     * @param field The field currently occupied.
     * @param newAnimals A list to return newly born animals.
     */
    public void act(List<Animal> newAnimals, boolean dayOfTime){

            incrementAge();
            incrementHunger();
            if (isAlive()) {
                giveBirth(newAnimals);
                // Move towards a source of food if found.
                Location newLocation = findFood();
                if (newLocation == null) {
                    // No food found - try to move to a free location.
                    newLocation = getField().freeAdjacentLocation(getLocation());
                }
                // See if it was possible to move.
                if (newLocation != null) {
                    setLocation(newLocation);
                }
                else {
                    // Overcrowding.
                    setDead();
                }
//                System.out.println("day");
            }
    }

    protected abstract Location findFood();

    protected abstract void giveBirth(List<Animal> newAnimals);

    /**
     * Make this animal act - that is: make it do
     * whatever it wants/needs to do.
     * @param newAnimals A list to receive newly born animals.
     */

    /**
     * Check whether the animal is alive or not.
     * @return true if the animal is still alive.
     */
    protected boolean isAlive()
    {
        return alive;
    }

    /**
     * Generate a number representing the number of births,
     * if it can breed.
     * @return The number of births (may be zero).
     */
    protected int breed()
    {
        int births = 0;
        if(canBreed() && rand.nextDouble() <= BREEDING_PROBABILITY) {
            births = rand.nextInt(MAX_LITTER_SIZE) + 1;
        }
        return births;
    }

    /**
     * A werewolf can breed if it has reached the breeding age.
     */
    protected boolean canBreed()
    {
        return age >= BREEDING_AGE;
    }

    /**
     * Indicate that the animal is no longer alive.
     * It is removed from the field.
     */
    protected void setDead()
    {
        alive = false;
        if(location != null) {
            field.clear(location);
            location = null;
            field = null;
        }
    }

    /**
     * Increase the age. This could result in the werewolf's death.
     */
    protected void incrementAge()
    {
        age++;
        if(age > MAX_AGE) {
            setDead();
        }
    }

    protected void incrementHunger()
    {
        health--;
        if(health <= 0) {
            setDead();
        }
    }
    /**
     * Return the animal's location.
     * @return The animal's location.
     */
    protected Location getLocation()
    {
        return location;
    }
    
    /**
     * Place the animal at the new location in the given field.
     * @param newLocation The animal's new location.
     */
    protected void setLocation(Location newLocation)
    {
        if(location != null) {
            field.clear(location);
        }
        location = newLocation;
        field.place(this, newLocation);
    }
    
    /**
     * Return the animal's field.
     * @return The animal's field.
     */
    protected Field getField() {
        return field;
    }


    /**
     *Check if species has 2 different genders, if so, assign a random gender to each animal
     */
    protected void assignGender(){
        if (data.getOppoGenderRequired(nameOfAnimal))
        {
            isMale = rand.nextBoolean();
        }
        else {
            isMale = null;
        }
    }

    protected boolean exceedMaxHealth() {
        // Max health for animal is a multiple of the average of prey food value.
        MAX_HEALTH = data.getAverageOfPreyValue(nameOfAnimal) * 2;
        return MAX_HEALTH < health;
    }


    protected void setHealthAndAge(boolean randomAge) {
        int average = data.getAverageOfPreyValue(nameOfAnimal);
        if (randomAge) {
            age = rand.nextInt(MAX_AGE);
            health = rand.nextInt(100) ;
        }
        else {
            age = 0;
//           health = average;
            health = rand.nextInt(100);
        }
    }

}

