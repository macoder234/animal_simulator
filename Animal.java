import java.util.List;
import java.util.Random;
/**
 * A class representing shared characteristics of animals.
 *
 * @author Saathveekan Satheshkumar, Manik Aggarwal, David J. Barnes and Michael Kölling
 *  @version 2022.03.02 (2)
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
    // The age to which an animal can live.
    protected int MAX_AGE;
    // The likelihood of an animal breeding.
    protected double BREEDING_PROBABILITY;
    // The maximum number of births.
    protected int MAX_LITTER_SIZE;
    //The maximum health of an animal.
    protected int MAX_HEALTH;
    //current age
    protected int age;
    //current health
    protected int health = rand.nextInt(100);
    //for animals that have populations of both genders.
    protected Boolean isMale;
    // A shared random number generator to control breeding.
    protected static final Random rand = Randomizer.getRandom();

    /**
     * Create a new animal at location in field.
     * @param field The field currently occupied.
     * @param location The location within the field.
     * @param animalName The name of the animal
     */
    protected Animal(Field field, Location location, String animalName)
    {

        nameOfAnimal = animalName;
        alive = true;
        this.field = field;
        setLocation(location);
        fillAnimalFields();
        assignGender();

    }

    /**
     * Fill in the fields of animal from data in the AnimalData class
     *
     */
    private void fillAnimalFields()
    {
        BREEDING_AGE = data.getBreedingAge(nameOfAnimal);
        MAX_AGE = data.getMaxAge(nameOfAnimal);
        BREEDING_PROBABILITY = data.getBreedingProbability(nameOfAnimal);
        MAX_LITTER_SIZE = data.getMaxLitterSize(nameOfAnimal);
    }

    /**
     * This is what the animal does most of the time: it hunts for
     * food. In the process, it might breed, die of hunger,
     * or die of old age.
     //     * @param field The field currently occupied.
     * @param newAnimals A list to return newly born animals.
     */
    public void act(List<Animal> newAnimals, boolean dayOfTime, String currentWeather){

        if (dayOfTime) {
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
                } else {
                    // Overcrowding.
                    setDead();
                }
            }
        }
        //Hurricane weather gets rid of 70 percent of the population
        if (currentWeather.equals("Hurricane") && rand.nextDouble() <= 0.7) {
            System.out.println("Weather: hurricane");
            setDead();
        }
    }

    /**
     * Look for prey adjacent to the current location.
     * Only the first live prey is eaten.
     * @return Where food was found, or null if it wasn't.
     */
    protected abstract Location findFood();

    /**
     * Check whether or not this animal is to give birth at this step.
     * New births will be made into free adjacent locations.
     * @param newAnimals A list to return newly born aninmals.
     */
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
     *Check if species has 2 different genders.
     *  If so, assign a random gender to each animal of that species.
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

    /**
     *
     * @return boolean value for whether animal has exceeded max health
     */
    protected boolean exceedMaxHealth() {
        // Max health for animal is a multiple of the average of prey food value.
        MAX_HEALTH = data.getAverageOfPreyValue(nameOfAnimal) * 2;
        return MAX_HEALTH < health;
    }

    /**
     * Sets age for the newborns
     * @param if true, the age is set to random
     */
    protected void setAge(boolean randomAge) {
        age = 0;
        if (randomAge) {
            age = rand.nextInt(MAX_AGE);
        }
    }


}

