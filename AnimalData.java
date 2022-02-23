import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayList;

public class AnimalData {
    private ArrayList<Animal> listOfAnimal;
    private HashMap<Animal, Integer> breedingAge = new HashMap<>();
    private HashMap<Animal, Integer> maxAge = new HashMap<>();
    private HashMap<Animal, Double> breedingProbability = new HashMap<>();
    private HashMap<Animal, Integer> maxLitterSize = new HashMap<>();
    private HashMap<Animal, Integer> foodValue = new HashMap<>();
    private HashMap<Animal, HashSet<Animal>> prey = new HashMap<>();

    public AnimalData() {
    }

    public void fillAnimalData(Object animalName, int breedingAge, int maxAge, double breedingProbability, int maxLitterSize, int foodValue, HashSet<Animal> prey) {
        this.breedingAge.put((Animal) animalName, breedingAge);
        this.maxAge.put((Animal) animalName,maxAge);
        this.breedingProbability.put((Animal) animalName,breedingProbability);
        this.maxLitterSize.put((Animal) animalName,maxLitterSize);
        this.foodValue.put((Animal) animalName,foodValue);
        this.prey.put((Animal) animalName,prey);
    }

    public void getBreedingAge(Animal nameOfAnimal){
    }
}
