import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;
import java.util.Random;

import org.vu.contest.ContestEvaluation;
import org.vu.contest.ContestSubmission;

public class player11 implements ContestSubmission {
    
    public static int EVALUATIONS_LIMIT = Integer.MAX_VALUE;
    public static int NUMBER_OF_PARENT_PAIRS = 1;
    public static int NUMBER_OF_CHILDREN = 2;
    public static int MAX_POPULATION_SIZE = 50;
    private static IFitnessCalculation fitnessCalculation = null;
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
	            
	                ArrayList<Individual> unmutated_children = recombination.crossover(parents, NUMBER_OF_CHILDREN);
	                for(Individual child: unmutated_children){
	                    mutation.mutate(child);
	                    evaluateChild(child);
	                    children.add(child);
	                }
	                
	            }
	            
	            survivalSelection.selectSurvivals(population,children);
	            
	            int generation = population.increaseGeneration();
	            System.out.println("Generation "+generation+" Result: "+getBestIndividual());
	            
	        }
	        
	        //------------------------------------------------------------
	    }catch(RuntimeException e){
	        e.printStackTrace();
	        System.out.println(e.getMessage());
	    }
	    
        return;
	}
	
	public Individual getBestIndividual(){
	    @SuppressWarnings("unchecked")
        ArrayList<Individual> individuals = (ArrayList<Individual>) population.getIndividuals().clone();
	    Collections.sort(individuals);
	    if( individuals.size() > 0 ){
	        return individuals.get(individuals.size()-1);
	    }else{
	        return null;
	    }
	}
	
	public static void evaluateInitialIndividual(Individual ind){
	    evaluateIndividual(ind, 0);
	}
	
	private static void evaluateChild(Individual ind){
	    evaluateIndividual(ind, population.getGeneration()+1);
	}
	    
    private static void evaluateIndividual(Individual ind, int generation) {
        Double fitness = (Double) evaluation.evaluate(ind.getDna());
        //Double fitness = (Double) fitnessCalculation.calculateFitness(result);
        evals++;
        if( fitness == null ){
            throw new RuntimeException("Maximum evaluations were reached.");
        }

        ind.setFitness(fitness);
        ind.setGeneration(generation);
    }
	
	/**
     * 
     * @param intervals probability list like [0.1][0.15][0.21][0.32]..[1]
     * @return returns list index of the first value which is higher than the given one, returns
     */
	public static int getProbabilityBasedRandomIndex(double[] intervals){
	    Double random = player11.rnd.nextDouble();
        for(int i = 0; i<intervals.length; i++){
            if( intervals[i] > random ){
                // found parent in range e.g if random=0.2 -> [0.1][0.15][x][0.32]..[1]
                return i;
            }
        }
        
        throw new RuntimeException("Random number i=[0-1[ was not inside the given interval.");
	}

	@Override
	public void setEvaluation(ContestEvaluation evaluation) {
		// Set evaluation problem used in the run
	    player11.evaluation = evaluation;

		// Get evaluation properties
		Properties props = evaluation.getProperties();

        EVALUATIONS_LIMIT = Integer.parseInt(props.getProperty("Evaluations"));
		boolean IS_MULTIMODAL = Boolean.parseBoolean(props.getProperty("Multimodal"));
		boolean HAS_STRUCTURE = Boolean.parseBoolean(props.getProperty("GlobalStructure"));
		boolean IS_SEPERABLE = Boolean.parseBoolean(props.getProperty("Separable"));

        initialPopulation = new InitialPopulationSimple();
        mutation = new MutationSimple();
        recombination = new RecombinationAverage();

		// Change settings(?)
		if (!IS_MULTIMODAL) {
	        fitnessCalculation = new FitnessNonZeroLogarithmic();
	        parentSelection = new ParentSelectionRouletteWheel();
	        survivalSelection = new SurvivalSelectionRouletteWheelFitnessAndAgeBased();
		} else {
	        fitnessCalculation = new FitnessSimple();
			parentSelection = new ParentSelectionSimple();
			survivalSelection = new SurvivalSelectionSimple();
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

        recombination.setMutation(mutation);
        mutation.setRecombination(recombination);
        initialPopulation.setMutation(mutation);
        parentSelection.setFitnessCalculation(fitnessCalculation);
        survivalSelection.setFitnessCalculation(fitnessCalculation);
		
	}

	@Override
	public void setSeed(long seed) {
		// Set seed of algortihms random process
		rnd.setSeed(seed);
	}

}
