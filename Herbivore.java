import java.util.List;

public class Herbivore extends Animal{

    private final int HYACINTH_FOOD_VALUE = data.getFoodValue("Hyacinth");
    private final int MANDRAKE_FOOD_VALUE = data.getFoodValue("Mandrake");

    protected Herbivore(Field field, Location location, String animalName){
        super(field, location, animalName);
    }

    @Override
    protected Location findFood() {
        if (!exceedMaxHealth()) {
            Field field = getField();
            List<Location> adjacent = field.adjacentLocations(getLocation());
            for (Location where : adjacent) {
                Object animal = field.getObjectAt(where);
//            for (Animal prey: data.getAnimalPrey(ANIMAL_NAME)){
                if (animal instanceof Hyacinth hyacinth && hyacinth.isAlive()) {
                    hyacinth.setDead();
                    health += HYACINTH_FOOD_VALUE;
                    return where;
                }
                else if (animal instanceof Mandrake mandrake && mandrake.isAlive()) {
                    mandrake.setDead();
                    health += MANDRAKE_FOOD_VALUE;
                    return where;
                }
            }
        }
        return null;
    }

    @Override
    protected void giveBirth(List<Animal> newAnimals) {

    }
}
