import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayList;

public class AnimalData {
    private ArrayList<String> listOfAnimal;
    private HashMap<String, Integer> breedingAge = new HashMap<String, Integer>();
    private HashMap<String, Integer> maxAge = new HashMap<String, Integer>();
    private HashMap<String, Double> breedingProbability = new HashMap<String, Double>();
    private HashMap<String, Integer> maxLitterSize = new HashMap<String, Integer>();
    private HashMap<String, Integer> foodValue = new HashMap<String, Integer>();
    private HashMap<String, HashSet<String>> prey = new HashMap<String, HashSet<String>>();

    public AnimalData() {
    }

    public void fillAnimalData(String animalName, int breedingAge, int maxAge, double breedingProbability, int maxLitterSize, int foodValue, HashSet<String> prey) {
        this.breedingAge.put(animalName, breedingAge);
        this.maxAge.put(animalName, maxAge);
        this.breedingProbability.put(animalName, breedingProbability);
        this.maxLitterSize.put(animalName, maxLitterSize);
        this.foodValue.put(animalName, foodValue);
        this.prey.put(animalName, prey);
    }


    /**
     * Return breeding age
     */
    public int getBreedingAge(String nameOfAnimal) {
        return breedingAge.get(nameOfAnimal);
    }

    public int getMaxAge(String nameOfAnimal) {
        return maxAge.get(nameOfAnimal);
    }

    public double getBreedingProbability(String nameOfAnimal) {
        return breedingProbability.get(nameOfAnimal);
    }

    public int getMaxLitterSize(String nameOfAnimal) {
        return maxLitterSize.get(nameOfAnimal);
    }

    public int getFoodValue(String nameOfAnimal) {
        return foodValue.get(nameOfAnimal);
    }

    public HashSet<String> getPrey(String nameOfAnimal) {
        return prey.get(nameOfAnimal);
    }


    /**
     * Return the number of prey an animal has.
     */
    public int getNumberOfPrey(String nameOfAnimal) {
        return getPrey(nameOfAnimal).size();
    }

    /**
     * Returns total food value of the prey of an animal
     */

    public int getPreyValueTotal(String nameOfAnimal) {
        int x = 0;
        for (String prey : getPrey(nameOfAnimal)) {

            x = x + getFoodValue(prey);
        }
        return x;
    }

    /**
     * Return the average of food values of the prey of the animal
     */

    public int getAverageOfPreyValue(String a) {

        return getPreyValueTotal(a) / getNumberOfPrey(a);
    }


}






















































































