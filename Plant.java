import java.util.List;

public class Plant extends Animal {

    //if not double, change to int
    protected double growthRate;
    //the current food value of the plant due to the growth rate
    protected double currentFoodValue;

    protected static int maxTotalFoodValue;


    protected Plant(Field field, Location location, String plantName) {
        super(field, location, plantName);
        maxTotalFoodValue = 50;
    }

    protected void setAge(boolean randomAge) {
        age = 0;
        if (randomAge) {
            age = rand.nextInt(MAX_AGE);
        }
    }


    public void act(List<Animal> newPlant, boolean dayOfTime, String currentWeather) {
        incrementAge();
        if (isAlive()) {
            giveBirth(newPlant);

            // if it is day, then the current food value is multiplied by the growth rate.
            if (dayOfTime && currentFoodValue < maxTotalFoodValue / growthRate) {
                currentFoodValue = currentFoodValue * growthRate;
            }
//            // Move towards a source of food if found.
//            Location newLocation = findFood();
//            if (newLocation == null) {
//                // No food found - try to move to a free location.
//                newLocation = getField().freeAdjacentLocation(getLocation());
//            }
//            // See if it was possible to move.
//            if (newLocation != null) {
//                setLocation(newLocation);
//            }
//            else {
//                // Overcrowding.
//                setDead();
//            }
//                System.out.println("day");
        }
    }

    @Override
    protected Location findFood() {
        return null;
    }

    @Override
    protected void giveBirth(List<Animal> newPlant) {
    }

    protected double getCurrentFoodValue() {
        return currentFoodValue;
    }


}
