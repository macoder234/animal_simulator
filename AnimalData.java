import java.util.HashSet;
import java.util.HashMap;

/**
 * Stores the data for all the animals.
 *
 * @author Saathveekan Satheshkumar, Manik Aggarwal, David J. Barnes and Michael Kölling
 * @version 2022.03.02 (2)
 */
public class AnimalData {

    // Creates the hashmaps to store all the data for each animal.
    private HashSet<String> listOfAnimal = new HashSet<>();
    private HashMap<String, Integer> breedingAge = new HashMap<>();
    private HashMap<String, Integer> maxAge = new HashMap<>();
    private HashMap<String, Double> breedingProbability = new HashMap<>();
    private HashMap<String, Integer> maxLitterSize = new HashMap<>();
    private HashMap<String, Integer> foodValue = new HashMap<>();
    private HashMap<String, Boolean> oppoGenderRequired = new HashMap<>();
    private HashMap<String, HashSet<String>> prey = new HashMap<>();


    /**
     *  Fills data into each of the corresponding hashmaps.
     */
    public AnimalData() {
        // Fills in data for all the animals
        HashSet<String> werewolfPrey = new HashSet<>();
        werewolfPrey.add("Jackalope");
        werewolfPrey.add("Unicorn");

        HashSet<String> griffonPrey = new HashSet<>();
        griffonPrey.add("Jackalope");
        griffonPrey.add("Pegasus");

        HashSet<String> cyclopsPrey = new HashSet<>();
        cyclopsPrey.add("Werewolf");
        cyclopsPrey.add("Griffon");

        HashSet<String> jackalopePrey = new HashSet<>();
        jackalopePrey.add("Hyacinth");
        jackalopePrey.add("Mandrake");

        HashSet<String> unicornPrey = new HashSet<>();
        unicornPrey.add("Hyacinth");
        unicornPrey.add("Mandrake");

        HashSet<String> pegasusPrey = new HashSet<>();
        pegasusPrey.add("Hyacinth");
        pegasusPrey.add("Mandrake");


        fillAnimalData("Jackalope", 7, 100, 0., 5, 4, jackalopePrey, true);
        fillAnimalData("Unicorn", 7, 100, 0.1, 3, 12, unicornPrey, false);
        fillAnimalData("Pegasus", 7, 100, 0.1, 3, 12, pegasusPrey, true);
        fillAnimalData("Werewolf", 9, 70, 0.03, 2, 20, werewolfPrey, false);
        fillAnimalData("Griffon", 9, 80, 0.06, 2, 20, griffonPrey, false);
        fillAnimalData("Cyclops", 10, 80, 0.06, 3, 50, cyclopsPrey, false);
        fillAnimalData("Mandrake", 0, 60, 0.01, 4, 3, null, false);
        fillAnimalData("Hyacinth", 0, 60, 0.01, 4, 3, null, false);
    }

    /**
     * Places each value into the hashmap.
     * @param animalName The name of the animal
     * @param breedingAge The age at which an animal can start to breed.
     * @param maxAge The age to which an animal can live.
     * @param breedingProbability The likelihood of an animal breeding.
     * @param maxLitterSize The maximum number of births.
     * @param foodValue The food value.
     * @param prey A list of all the prey that the animal can eat.
     * @param oppGenderRequired If the animal requires the opposite gender to reproduce.
     */
    public void fillAnimalData(String animalName, int breedingAge, int maxAge, double breedingProbability, int maxLitterSize, int foodValue, HashSet<String> prey, boolean oppGenderRequired) {
        this.listOfAnimal.add(animalName);
        this.breedingAge.put(animalName, breedingAge);
        this.maxAge.put(animalName, maxAge);
        this.breedingProbability.put(animalName, breedingProbability);
        this.maxLitterSize.put(animalName, maxLitterSize);
        this.foodValue.put(animalName, foodValue);
        this.prey.put(animalName, prey);
        this.oppoGenderRequired.put(animalName, oppGenderRequired);

    }


    /**
     * Return age at which an animal can start to breed.
     * @param nameOfAnimal name of the animal
     * @return Returns the breeding age,
     */
    public int getBreedingAge(String nameOfAnimal) {
        return breedingAge.get(nameOfAnimal);
    }

    /**
     * Gets the maximum age to which an animal can live.
     * @param nameOfAnimal name of the animal.
     * @return max age.
     */
    public int getMaxAge(String nameOfAnimal) {
        return maxAge.get(nameOfAnimal);
    }

    /**
     * Gets the likelihood of an animal breeding.
     * @param nameOfAnimal name of animal.
     * @return breeding probability.
     */
    public double getBreedingProbability(String nameOfAnimal) {
        return breedingProbability.get(nameOfAnimal);
    }

    /**
     * Gets the maximum number of births.
     * @param nameOfAnimal name of animal
     * @return max litter size
     */
    public int getMaxLitterSize(String nameOfAnimal) {
        return maxLitterSize.get(nameOfAnimal);
    }

    /**
     * Gets the food value.
     * @param nameOfAnimal name of animal
     * @return food value
     */
    public int getFoodValue(String nameOfAnimal) {
        return foodValue.get(nameOfAnimal);
    }

    /**
     * Gets the list of prey that the animal can eat.
     * @param nameOfAnimal name of animal.
     * @return list of prey.
     */
    public HashSet<String> getPrey(String nameOfAnimal) {
        return prey.get(nameOfAnimal);
    }


    /**
     * Gets the number of prey that the animal can eat.
     * @param nameOfAnimal name of animal.
     * @return the number of prey.
     */
    public int getNumberOfPrey(String nameOfAnimal) {
        return getPrey(nameOfAnimal).size();
    }


    /**
     * Calculates the total food value for the list of prey.
     * @param nameOfAnimal name of animal.
     * @return total prey food value.
     */
    public int getPreyValueTotal(String nameOfAnimal) {
        int x = 0;
        for (String prey : getPrey(nameOfAnimal)) {

            x = x + getFoodValue(prey);
        }
        return x;
    }


    /**
     * Calculates the average food value for the list of prey.
     * @param a name of animal.
     * @return average prey food value.
     */
    public int getAverageOfPreyValue(String a) {

        return getPreyValueTotal(a) / getNumberOfPrey(a);
    }

    /**
     * Gets whether the opposite gender is required.
     * @param animalName name of animal
     * @return true if opposite gender is required.
     */
    public Boolean getOppoGenderRequired(String animalName) {
        return oppoGenderRequired.get(animalName);
    }


}
