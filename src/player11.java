import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import org.vu.contest.ContestEvaluation;
import org.vu.contest.ContestSubmission;

public class player11 implements ContestSubmission {
    
    private static int EVALUATIONS_LIMIT;
    private static int NUMBER_OF_PARENT_PAIRS = 30;
    private static int NUMBER_OF_CHILDREN = 12;
    private static IInitialPopulation initialPopulation = null;
    private static IParentSelection parentSelection = null;
    private static IRecombination recombination = null;
    private static IMutation mutation = null;
    private static ISurvivalSelection survivalSelection = null;
    
	public static Random rnd;
	public static ContestEvaluation evaluation;
	private static Population population = null;
	private static int evals;

	public static void main(String[] args){
	    
	}
	
	public player11() {
		rnd = new Random();
	}

	@Override
	public void run() {
	      // 10 dimension functions
        // all functions should be maximized
        // Search space [-5,5]^10
        // best fitness for all function is 10
        // which population size should we choose?
        // how many parent should make how many childs?
        // -->Keeping balance between crossover and selection. If selection is very
        // intense the population will converge very fast and there might not be
        // enough time for good mixing to occur between members of the
        // population.
        // how many parents should we use? (just the tow bests or the 3 or 4 best (alienated evolution))
        // parent A + parent B --> Crossover (random? 50%? Which fields?) --> Mutate (to which extend)
        // next gen = parents + childs or just childs (overlapping EA or simple non-overlapping GA)
        // see http://bt.pa.msu.edu/TM/BocaRaton2006/talks/poklonskiy.pdf for parameters
        // see http://en.wikipedia.org/wiki/Test_functions_for_optimization for possible testcases
	    
	    try{

	        //CREATE INITIAL POPULATION AND MEASURE FITNESS------------
	        population = initialPopulation.createInitialPopulation();
	        //----------------------------------------------------------
	        
	        
	        //RUN EVOLUTION ON INITIAL POPULATION-----------------------
	        while(evals < EVALUATIONS_LIMIT){
	            
	            //TODO: avoid that same parent is chosen in different parent pairs?
	            ArrayList<Individual> children = new ArrayList<Individual>();
	            parentSelection.prepareSelection(population);
	            for(int i = 0; i< NUMBER_OF_PARENT_PAIRS; i++){
	                Individual[] parents = parentSelection.selectParents(population);
	            
	                ArrayList<double[]> child_dnas = recombination.crossover(parents, NUMBER_OF_CHILDREN);
	                for(double[] child_dna: child_dnas){
	                    children.add(mutation.mutate(child_dna));
	                }
	            }
	            
	            survivalSelection.selectSurvivals(population,children);
	            
	            int generation = population.increaseGeneration();
	            System.out.println("Generation "+generation+" Result: "+population.getIndividuals().get(population.getIndividuals().size()-1));
	            
	            // Select parents
	            // Apply variation operators and get children
	            //  double child[] = ...
	            
	            // objective function ---> the close to 10, the better
	            // Double fitness = evaluation_.evaluate(child);
	            // Select survivors
	        }
	        
	        //------------------------------------------------------------
	        
	        // Run your algorithm here

	        // Getting data from evaluation problem (depends on the specific
	        // evaluation implementation)
	        // E.g. getting a vector of numbers
	        // Vector<Double> data =
	        // (Vector<Doulbe>)evaluation_.getData("trainingset1");

	        // Evaluating your results
	        // E.g. evaluating a series of true/false predictions
	        // boolean pred[] = ...
	        // Double score = (Double)evaluation_.evaluate(pred);
	    }catch(RuntimeException e){
	        System.out.println(e.getMessage());
	    }
	    
        return;
	}
	
	public static Individual createIndividual(double[] dna){
	    Double fitness = (Double) evaluation.evaluate(dna);
        evals++;
        if( fitness == null ){
            throw new RuntimeException("Maximum evaluations were reached.");
        }
        
        return new Individual(dna, fitness, population.getGeneration()+1);
	}

	@Override
	public void setEvaluation(ContestEvaluation evaluation) {
		// Set evaluation problem used in the run
		this.evaluation = evaluation;

		// Get evaluation properties
		Properties props = evaluation.getProperties();

        EVALUATIONS_LIMIT = Integer.parseInt(props.getProperty("Evaluations"));
		boolean IS_MULTIMODAL = Boolean.parseBoolean(props.getProperty("Multimodal"));
		boolean HAS_STRUCTURE = Boolean.parseBoolean(props.getProperty("GlobalStructure"));
		boolean IS_SEPERABLE = Boolean.parseBoolean(props.getProperty("Separable"));

		// Change settings(?)
		if (IS_MULTIMODAL) {
			// Do sth
		} else {
			// Do sth else
		}
		
		// Change settings(?)
        if (IS_SEPERABLE) {
            // Do sth
        } else {
            //solve using CMA Mutation
        }
        
        // Change settings(?)
        if (HAS_STRUCTURE) {
            // Do sth
        } else {
            //Do sth else
        }

        initialPopulation = new DefaultInitialPopulation();
        parentSelection = new RouletteWheelParentSelection();
        recombination = new BiPolarBlendCrossover();
        mutation = new UncorrelatedMutation();
        survivalSelection = new FitnessAndAgeBasedSurvivalSelection();
		
	}

	@Override
	public void setSeed(long seed) {
		// Set seed of algortihms random process
		rnd.setSeed(seed);
	}

}
