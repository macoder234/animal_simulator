import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Color;

/**
 * A simple predator-prey simulator, based on a rectangular field
 * containing various mythological animals.
 * 
 * @author Manik Aggarwal, Saathveekan Satheshkumar, David J. Barnes and Michael Kölling
 * @version 2016.02.29 (2)
 */
public class Simulator
{
    // Constants representing configuration information for the simulation.
    // The default width for the grid.
    private static final int DEFAULT_WIDTH = 120;
    // The default depth of the grid.
    private static final int DEFAULT_DEPTH = 80;
    // The probability that a fox will be created in any given grid position.
    private static final double WEREWOLF_CREATION_PROBABILITY = 0.02;
    // The probability that a rabbit will be created in any given grid position.
    private static final double JACKALOPE_CREATION_PROBABILITY = 0.08;
    // The probability that a rabbit will be created in any given grid position.
    private static final double GRIFFON_CREATION_PROBABILITY = 0.01;
    // The probability that a griffon will be created in any given grid position.
    private static final double PEGASUS_CREATION_PROBABILITY = 0.004;
    // The probability that a pegasus will be created in any given grid position.
    private static final double UNICORN_CREATION_PROBABILITY = 0.004;
    // The probability that a unicorn will be created in any given grid position.
    private static final double CYCLOPS_CREATION_PROBABILITY = 0.004;
    // The probability that a cyclops will be created in any given grid position.


    // List of animals in the field.
    private List<Animal> animals;
    // The current state of the field.
    private Field field;
    // The current step of the simulation.
    private int step;
    // A graphical view of the simulation.
    private SimulatorView view;
    // The data for all the animals.
    private AnimalData data;
    
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
        view.setColor(Jackalope.class, new Color(150, 75, 0, 255));
        view.setColor(Werewolf.class, new Color(94, 94, 94, 255));
        view.setColor(Griffon.class, new Color(255,204,0));
        view.setColor(Unicorn.class, new Color(167, 4, 248));
        view.setColor(Pegasus.class, new Color(0,0,0));
        view.setColor(Cyclops.class, new Color(253, 0, 0));

        // Fills in data for all the animals
        data = new AnimalData();
        data.fillAnimalData(, 5,  );




        // Setup a valid starting point.
        reset();
    }
    
    /**
     * Run the simulation from its current state for a reasonably long period,
     * (4000 steps).
     */
    public void runLongSimulation()
    {
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
            delay(100);   // uncomment this to run more slowly
        }
    }
    
    /**
     * Run the simulation from its current state for a single step.
     * Iterate over the whole field updating the state of each
     * fox and rabbit.
     */
    public void simulateOneStep()
    {
        step++;

        // Provide space for newborn animals.
        List<Animal> newAnimals = new ArrayList<>();        
        // Let all rabbits act.
        for(Iterator<Animal> it = animals.iterator(); it.hasNext(); ) {
            Animal animal = it.next();
            animal.act(newAnimals);
            if(! animal.isAlive()) {
                it.remove();
            }
        }
               
        // Add the newly born foxes and rabbits to the main lists.
        animals.addAll(newAnimals);

        view.showStatus(step, field);
    }
        
    /**
     * Reset the simulation to a starting position.
     */
    public void reset()
    {
        step = 0;
        animals.clear();
        populate();
        
        // Show the starting state in the view.
        view.showStatus(step, field);
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
                    Werewolf werewolf = new Werewolf(true, field, location);
                    animals.add(werewolf);
                }
                else if(rand.nextDouble() <= JACKALOPE_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Jackalope Jackalope = new Jackalope(true, field, location);
                    animals.add(Jackalope);
                }
                else if(rand.nextDouble() <= GRIFFON_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Griffon griffon = new Griffon(true, field, location);
                    animals.add(griffon);
                }
                else if(rand.nextDouble() <= PEGASUS_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Pegasus pegasus = new Pegasus(true, field, location);
                    animals.add(pegasus);
                }
                else if(rand.nextDouble() <= CYCLOPS_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Cyclops cyclops = new Cyclops(true, field, location);
                    animals.add(cyclops);
                }
                else if(rand.nextDouble() <= UNICORN_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Unicorn unicorn = new Unicorn(true, field, location);
                    animals.add(unicorn);
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

    public static void main(String[] args) {
        Simulator sim = new Simulator();
        sim.runLongSimulation();
    }
}
