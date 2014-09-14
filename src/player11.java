import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import org.vu.contest.ContestEvaluation;
import org.vu.contest.ContestSubmission;

public class player11 implements ContestSubmission {
	Random rnd;
	ContestEvaluation evaluation;
	private int EVALUATIONS_LIMIT;
	private static int MAX_POPULATION_SIZE = 100;
	private static int INITIAL_POPULATION_SIZE = 10;
	private static int MAX_GENERATIONS = Integer.MAX_VALUE;
	private static int NUMBER_OF_ELITE = 4;
	private static int NUMBER_OF_PARENTS = 2;
	private static int NUMBER_OF_CHILDREN = 12;
	private static double MUTATION_RATE = 1.3;
	private static double MUTATION_PROBABILITY = 1;
	private static double CROSSOVER_RATE = 0.5;
	private static double SEARCH_SPACE_MIN = -5.0;
	private static double SEARCH_SPACE_MAX = 5.0;
	private static double FITNESS_MAX = 10;
	private static boolean OVERLAPPING_GENERATIONS = true;
	protected static double sigma_lower_boundary = 0;
	protected Population population = null;

	public static void main(String[] args){
	    
	}
	
	public player11() {
		rnd = new Random();
	}

	@Override
	public void run() {
	    //CREATE INITIAL POPULATION AND MEASURE FITNESS------------
        int generations = 0;
        int evals = 0;

		while(population.getIndividuals().size() < INITIAL_POPULATION_SIZE){
		    double[] randomDna = new double[10];
		    for(int i = 0; i<10; i++){
		        //bounds are [-5.0,5.0[
		        randomDna[i] = rnd.nextDouble()*10.0 - 5.0;
		    }
		    Double fitness = (Double) evaluation.evaluate(randomDna);
		    evals++;
            Individual individual = new Individual(randomDna,fitness);
            population.getIndividuals().add(individual);
   		}
		//----------------------------------------------------------
		
		//RUN EVOLUTION ON INITIAL POPULATION-----------------------
        while(generations < MAX_GENERATIONS){
            // get the elite
            Collections.sort(population.getIndividuals());
            CopyOnWriteArrayList<Individual> elite = new CopyOnWriteArrayList<Individual>(population.getIndividuals().subList((population.getIndividuals().size()-NUMBER_OF_ELITE) < 0 ? 0 : (population.getIndividuals().size()-NUMBER_OF_ELITE), population.getIndividuals().size()));
            
            //remove current population.getIndividuals() if overlapping is off
            if(!OVERLAPPING_GENERATIONS){
                population.getIndividuals().clear();
            }
            
            
            //start crossing
            while(elite.size() > 0){
                //get a random set of elite parents
                CopyOnWriteArrayList<Individual> parents = new CopyOnWriteArrayList<Individual>();
                while(elite.size() > 0 && parents.size() < NUMBER_OF_PARENTS){
                    parents.add(elite.remove(rnd.nextInt(elite.size())));
                }
                
                //check if number of parents was reached
                if(parents.size() < NUMBER_OF_PARENTS){
                    System.out.println("Number of parents could not be reached.");
                }
                
                //create children
                for(int i = 0; i<NUMBER_OF_CHILDREN; i++){
                    double[] childDna = new double[10];
                    //choose dna segments randomly from parents
                    for(int j = 0; j<10; j++){
                        double gene = parents.get(rnd.nextInt(parents.size())).getDna()[j];
                        //apply mutation to gene
                        if( rnd.nextDouble() < MUTATION_PROBABILITY ){
                            //apply random mutation, the mutation rate defines how much the gene is allowed to change
                            gene = gene + (rnd.nextDouble()*2.0-1.0)*(MUTATION_RATE*gene);
                            if( gene < SEARCH_SPACE_MIN ){
                                gene = SEARCH_SPACE_MIN;
                            }
                            if( gene > SEARCH_SPACE_MAX){
                                gene = SEARCH_SPACE_MAX;
                            }
                        }
                        childDna[j] = gene;
                    }
                    
                    //calcuate fitness and add to population.getIndividuals()
                    Double fitness = (Double) evaluation.evaluate(childDna);
                    evals++;
                    if( fitness == null ){
                        finalMessage("Maximum evaluations were reached.");
                        return;
                    }
                    Individual child = new Individual(childDna, fitness);
                    
                    //if max population.getIndividuals() size is reached, remove unfittest individual from population.getIndividuals()
                    if( population.getIndividuals().size() >= MAX_POPULATION_SIZE ){
                        Collections.sort(population.getIndividuals());
                        population.getIndividuals().remove(0);
                    }
                    
                    //add child to population
                    population.getIndividuals().add(child);

                }
            }
            
            Collections.sort(population.getIndividuals());
            System.out.println("Generation "+generations+" Result: "+population.getIndividuals().get(population.getIndividuals().size()-1));
            generations ++;
            
            // Select parents
            // Apply variation operators and get children
            //  double child[] = ...
            
            // objective function ---> the close to 10, the better
            // Double fitness = evaluation_.evaluate(child);
            // Select survivors
        }
        
        finalMessage("Maximum number of generations was reached. Evaluations: "+evals);
        return;
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
	}
	
	public void finalMessage(String message){
	    System.out.println(message);
        Collections.sort(population.getIndividuals());
        System.out.println("Final best fitness was: "+population.getIndividuals().get(population.getIndividuals().size()-1).getFitness());
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
		
		//error
		if(INITIAL_POPULATION_SIZE < NUMBER_OF_ELITE){
		    return;
		}
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
        
	}

	@Override
	public void setSeed(long seed) {
		// Set seed of algortihms random process
		rnd.setSeed(seed);
	}
	
	
    
    

}
