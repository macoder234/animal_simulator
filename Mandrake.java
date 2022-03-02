import java.util.List;

public class Mandrake extends Plant {

    private String PLANT_NAME;


    protected Mandrake(boolean randomAge, Field field, Location location, String plantName) {
        super(field, location, plantName);
        PLANT_NAME = plantName;
        setAge(randomAge);
        growthRate = 1.5;
        currentFoodValue = data.getFoodValue(PLANT_NAME);
    }

    @Override
    protected Location findFood() {
        return null;
    }

    @Override
    protected void giveBirth(List<Animal> newPlant) {
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        int births = breed();
        for(int b = 0; b < births && !free.isEmpty(); b++) {
            Location loc = free.remove(0);
            Mandrake young = new Mandrake(false, field, loc, PLANT_NAME);
            newPlant.add(young);
        }
    }

}
