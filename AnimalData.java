import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayList;

public class AnimalData {
    private ArrayList<Animal> listOfAnimal;
    private HashMap<String, Integer> breedingAge = new HashMap<String, Integer>();
    private HashMap<String, Integer> maxAge = new HashMap<String, Integer>();
    private HashMap<String, Double> breedingProbability = new HashMap<String, Double>();
    private HashMap<String, Integer> maxLitterSize = new HashMap<String, Integer>();
    private HashMap<String, Integer> foodValue = new HashMap<String, Integer>();
    private HashMap<String, HashSet<Animal>> prey = new HashMap<String, HashSet<Animal>>();

    public AnimalData() {
    }

    public void fillAnimalData(String animalName, int breedingAge, int maxAge, double breedingProbability, int maxLitterSize, int foodValue, HashSet<Animal> prey) {
        this.breedingAge.put(animalName, breedingAge);
        this.maxAge.put(animalName,maxAge);
        this.breedingProbability.put(animalName,breedingProbability);
        this.maxLitterSize.put(animalName,maxLitterSize);
        this.foodValue.put(animalName,foodValue);
        this.prey.put(animalName,prey);
    }

    public void getBreedingAge(Animal nameOfAnimal){
    }
}
