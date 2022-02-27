import java.util.HashSet;
import java.util.HashMap;

public class AnimalData {
    private HashSet<String> listOfAnimal = new HashSet<>();
    private HashMap<String, Integer> breedingAge = new HashMap<>();
    private HashMap<String, Integer> maxAge = new HashMap<>();
    private HashMap<String, Double> breedingProbability = new HashMap<>();
    private HashMap<String, Integer> maxLitterSize = new HashMap<>();
    private HashMap<String, Integer> foodValue = new HashMap<>();
    private HashMap<String, Boolean> oppoGenderRequired = new HashMap<>();
    private HashMap<String, HashSet<String>> prey = new HashMap<>();
//    private HashMap<String, HashSet<Animal>> animalPrey = new HashMap<>();


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

//        HashSet<Animal> werewolfAnimalPrey = new HashSet<>();
//        Jackalope jackalope = new Jackalope();
//        werewolfAnimalPrey.add(jackalope);


        HashMap<String, Boolean>
        fillAnimalData("Jackalope", 5, 40, 0.12, 4, 9, null);
        fillAnimalData("Unicorn", 5, 40, 0.01, 2, 14, null);
        fillAnimalData("Pegasus", 5, 40, 0.12, 4, 9, null);
        fillAnimalData("Werewolf", 15, 150, 0.08, 2, 15, werewolfPrey);
        fillAnimalData("Griffon", 5, 40, 0.12, 4, 9, griffonPrey);
        fillAnimalData("Cyclops", 5, 40, 0.12, 4, 9, cyclopsPrey);
    }

    public void fillAnimalData(String animalName, int breedingAge, int maxAge, double breedingProbability, int maxLitterSize, int foodValue, HashSet<String> prey) {
        this.listOfAnimal.add(animalName);
        this.breedingAge.put(animalName, breedingAge);
        this.maxAge.put(animalName, maxAge);
        this.breedingProbability.put(animalName, breedingProbability);
        this.maxLitterSize.put(animalName, maxLitterSize);
        this.foodValue.put(animalName, foodValue);
        this.prey.put(animalName, prey);
        this.oppoGenderRequired.put(animalName, );
        //
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
