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
        fillAnimalData("Werewolf", 9, 70, 0.06, 2, 20, werewolfPrey, false);
        fillAnimalData("Griffon", 9, 80, 0.06, 2, 20, griffonPrey, false);
        fillAnimalData("Cyclops", 10, 80, 0.03, 3, 50, cyclopsPrey, false);
        fillAnimalData("Mandrake",0, 60, 0.01, 4, 3, null, false);
        fillAnimalData("Hyacinth", 0, 60, 0.01, 4, 3, null, false);
    }

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

//    public HashSet<Animal> getAnimalPrey(String nameOfAnimal){
//        return animalPreyMap.get(nameOfAnimal);
//    }

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


    public Boolean getOppoGenderRequired(String animalName){
        return oppoGenderRequired.get(animalName);
    }


}
