import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Color;

/**
 * A simple predator-prey simulator, based on a rectangular field
 * containing various mythological animals.
 *
 * @author Saathveekan Satheshkumar, Manik Aggarwal, David J. Barnes and Michael Kölling
 *  @version 2022.03.02 (2)
 */
public class Simulator
{
    // Constants representing configuration information for the simulation.
    // The default width for the grid.
    private static final int DEFAULT_WIDTH = 120;
    // The default depth of the grid.
    private static final int DEFAULT_DEPTH = 80;
    // The probability that a werewolf will be created in any given grid position.
    private static final double WEREWOLF_CREATION_PROBABILITY = 0.03;
    // The probability that a jackalope will be created in any given grid position.
    private static final double JACKALOPE_CREATION_PROBABILITY = 0.2;
    // The probability that a griffon will be created in any given grid position.
    private static final double GRIFFON_CREATION_PROBABILITY = 0.03;
    // The probability that a pegasus will be created in any given grid position.
    private static final double PEGASUS_CREATION_PROBABILITY = 0.08;
    // The probability that a unicorn will be created in any given grid position.
    private static final double UNICORN_CREATION_PROBABILITY = 0.05;
    // The probability that a cyclops will be created in any given grid position.
    private static final double CYCLOPS_CREATION_PROBABILITY = 0.03;
    // The probability that a mandrake will be created in any given grid position.
    private static final double MANDRAKE_CREATION_PROBABILITY = 0.3;
    // The probability that a hyacinth will be created in any given grid position.
    private static final double HYACINTH_CREATION_PROBABILITY = 0.3;


    // List of animals in the field.
    private List<Animal> animals;
    // The current state of the field.
    private Field field;
    // The current time of day.
    private boolean isDay;
    //The current weather event.
    private String currentWeather;
    // The current step of the simulation.
    private int step;
    // A graphical view of the simulation.
    private SimulatorView view;
    // Simulates the weather.
    private Weather weather = new Weather();

    /**
     * Construct a simulation field with default size.
     */
    public Simulator()
    {
        this(DEFAULT_DEPTH, DEFAULT_WIDTH);
    }
    
    /**
     * Create a simulation field with the given size.
     * @param depth Depth of the field. Must be greater than zero.
     * @param width Width of the field. Must be greater than zero.
     */
    public Simulator(int depth, int width)
    {
        if(width <= 0 || depth <= 0) {
            System.out.println("The dimensions must be greater than zero.");
            System.out.println("Using default values.");
            depth = DEFAULT_DEPTH;
            width = DEFAULT_WIDTH;
        }
        
        animals = new ArrayList<>();
        field = new Field(depth, width);

        // Create a view of the state of each location in the field.
        view = new SimulatorView(depth, width);
        view.setColor(Hyacinth.class, new Color(0, 150, 2));
        view.setColor(Mandrake.class, new Color(0, 73, 1));
        view.setColor(Jackalope.class, new Color(79, 73, 204));
        view.setColor(Werewolf.class, new Color(94, 94, 94, 255));
        view.setColor(Griffon.class, new Color(255,204,0));
        view.setColor(Unicorn.class, new Color(167, 4, 248));
        view.setColor(Pegasus.class, new Color(0,0,0));
        view.setColor(Cyclops.class, new Color(253, 0, 0));

        // Setup a valid starting point.
        reset();
    }

    /**
     * Run the simulation from its current state for a reasonably long period,
     * (4000 steps).
     */
    public void runLongSimulation(){
        simulate(4000);
    }
    
    /**
     * Run the simulation from its current state for the given number of steps.
     * Stop before the given number of steps if it ceases to be viable.
     * @param numSteps The number of steps to run for.
     */
    public void simulate(int numSteps)
    {
        for(int tally = 1; tally <= numSteps && view.isViable(field); tally++) {
            simulateOneStep();
            delay(1);   // uncomment this to run m
        }
    }
    
    /**
     * Run the simulation from its current state for a single step.
     * Iterate over the whole field updating the state of each
     * animal.
     */
    public void simulateOneStep()
    {
        step++;
        // Changes the time of day.
        changesDay();
        // Updates the time of day for the weather class.
        weather.timeOfDay(isDay);
        // Sets a new weather for each step.
        currentWeather = weather.setWeather();

        // Provide space for newborn animals.
        List<Animal> newAnimals = new ArrayList<>();
        // Let all animals act.
        for(Iterator<Animal> it = animals.iterator(); it.hasNext(); ) {
            Animal animal = it.next();
            animal.act(newAnimals, isDay, currentWeather);
            if(! animal.isAlive()){
                it.remove();
            }
        }

        // Add the newly born animals to the main lists.
        animals.addAll(newAnimals);

        view.showStatus(currentWeather, step, field, isDayOrNight());
    }
        
    /**
     * Reset the simulation to a starting position.
     */
    public void reset()
    {
        step = 0;
        isDay = true;
        animals.clear();
        populate();
        
        // Show the starting state in the view.
        view.showStatus(currentWeather ,step, field, isDayOrNight());
    }
    
    /**
     * Randomly populate the field with foxes and rabbits.
     */
    private void populate()
    {
        Random rand = Randomizer.getRandom();
        field.clear();
        for(int row = 0; row < field.getDepth(); row++) {
            for(int col = 0; col < field.getWidth(); col++) {

                if(rand.nextDouble() <= WEREWOLF_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Werewolf werewolf = new Werewolf(true, field, location, "Werewolf");
                    animals.add(werewolf);
                }
                else if(rand.nextDouble() <= JACKALOPE_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Jackalope Jackalope = new Jackalope(true, field, location, "Jackalope");
                    animals.add(Jackalope);
                }
                else if(rand.nextDouble() <= GRIFFON_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Griffon griffon = new Griffon(true, field, location, "Griffon");
                    animals.add(griffon);
                }
                else if(rand.nextDouble() <= PEGASUS_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Pegasus pegasus = new Pegasus(true, field, location, "Pegasus");
                    animals.add(pegasus);
                }
                else if(rand.nextDouble() <= CYCLOPS_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Cyclops cyclops = new Cyclops(true, field, location, "Cyclops");
                    animals.add(cyclops);
                }
                else if(rand.nextDouble() <= UNICORN_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Unicorn unicorn = new Unicorn(true, field, location, "Unicorn");
                    animals.add(unicorn);
                }
                else if(rand.nextDouble() <= MANDRAKE_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Mandrake mandrake = new Mandrake(true , field, location, "Mandrake");
                    animals.add(mandrake);
                }
                else if(rand.nextDouble() <= HYACINTH_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Hyacinth hyacinth = new Hyacinth(true, field, location, "Hyacinth");
                    animals.add(hyacinth);
                }
                // else leave the location empty.
            }
        }
    }
    
    /**
     * Pause for a given time.
     * @param millisec  The time to pause for, in milliseconds
     */
    private void delay(int millisec)
    {
        try {
            Thread.sleep(millisec);
        }
        catch (InterruptedException ie) {
            // wake up
        }
    }

    /**
     * Changes the time of day.
     */
    protected void changesDay(){
        isDay = !isDay;

    }

    /**
     * For the GUI, returns the corresponding time of day as a
     * String type.
     * @return the time of day as a String type.
     */
    private String isDayOrNight()
    {
        if (isDay) {
            return "Day";
        }
        else {
            return "Night";
        }
    }

    public static void main(String[] args) {
        Simulator sim = new Simulator();
        sim.runLongSimulation();

    }

}
