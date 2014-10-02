import java.util.ArrayList;
import java.util.Collections;

/**
 * Implements a general Evolution Strategy algorithm (Chapter 4, book).
 * 
 * @author max
 *
 */
public class AlgorithmESAdvanced extends AbstractEA {
    protected Population population;

    private int MU; // number of individuals in each generation (parent population, p.81 book)
    private int LAMBDA; // number of offspring to be generated in each generation
    protected RecombinationDiscreteAndIntermediate recombination; // how to recombine parents

    public AlgorithmESAdvanced(){
        this(15);
    }
    
    public AlgorithmESAdvanced(int MU) {
        this(MU,100, new MutationUncorrelated());
    }
    
    public AlgorithmESAdvanced(int MU, int LAMBDA) {
        this(MU,LAMBDA, new MutationUncorrelated());
    }
    
    public AlgorithmESAdvanced(int MU, int LAMBDA, double ALPHA) {
        this(MU,LAMBDA, new MutationUncorrelated(ALPHA));
    }
    
    public AlgorithmESAdvanced(int MU, int LAMBDA, double ALPHA, double EPSILON) {
        this(MU,LAMBDA, new MutationUncorrelated(ALPHA, EPSILON));
    }
    
    public AlgorithmESAdvanced(int MU, int LAMBDA, IMutation mutation){
        this.MU = MU;
        this.LAMBDA = LAMBDA;
        initialPopulation = new InitialPopulationSimple(MU);
        parentSelection = new ParentSelectionTournament();
        recombination = new RecombinationDiscreteAndIntermediate();
        this.mutation = mutation;
        survivalSelection = new SurvivalSelectionMuCommaLambda(MU);
    }

    @Override
    public void run() {
        int parallel_algos = 4;
        int evaluation_limit_per_algo = evaluationsLimit/(parallel_algos+10);
        Population[] populations = new Population[parallel_algos];
        for( int i = 0; i<parallel_algos; i++ ){
            Population population = initialPopulation.createInitialPopulation();
            populations[i] = runEA(population, evaluation_limit_per_algo);
        }
        
        ArrayList<Individual> all_best = new ArrayList<Individual>();
        for(Population population : populations){
            all_best.addAll(population.getIndividuals());
        }
        
        Collections.sort(all_best);
        Population best_population = new Population();
        best_population.addIndividuals(all_best.subList(all_best.size()-MU, all_best.size()));
       
        runEA(best_population, evaluation_limit_per_algo*10);
    }
    
    public Population runEA(Population population, int evaluationsLimit){
        recombination.setPopulation(population.getIndividuals());
        
        for (Individual ind : population.getIndividuals()) {
            evaluateInitialIndividual(ind);
        }
        while (getEvals() < evaluationsLimit) {
            ArrayList<Individual> children = new ArrayList<Individual>();
            for (int i = 0; i < LAMBDA; i++) { // TODO: fix: should be only LAMBDA
                Individual[] couple = parentSelection.selectParents(population, 2);
                Individual child = recombination.crossover(couple, 1).get(0);
                mutation.mutate(child);
                evaluateIndividual(child, population.getGeneration()+1);
                children.add(child);
            }
            survivalSelection.selectSurvivals(population, children);
            population.increaseGeneration();
            System.out.println("Generation " + population.getGeneration() + " Result: "
                    + population.getBestIndividual());
        }
        
        resetEvals();
        
        return population;
    }

}
