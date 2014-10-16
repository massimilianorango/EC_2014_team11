import java.util.ArrayList;
import java.util.Collections;


public class AlgorithmIsland extends AbstractEA {

    private final int MU; // number of individuals in each generation (parent population, p.81 book)
    private final int LAMBDA; // number of offspring to be generated in each generation
    private int islands;
    private int epochLength;
    private ArrayList<Population> populations = new ArrayList<Population>();
    private int exchangeIndCount;
    
    public AlgorithmIsland(int mu, int lambda, int islands, int epochLength, int exchangeIndCount ) {
        this.MU = mu;
        this.LAMBDA = lambda;
        this.islands = islands;
        this.epochLength = epochLength;
        this.exchangeIndCount = exchangeIndCount;
        this.initialPopulation = new InitialPopulationSimple(MU);
        this.parentSelection = new ParentSelectionTournament(2);
        this.recombination = new RecombinationDiscreteAndIntermediate();
        this.mutation = new MutationUncorrelated();
        this.survivalSelection = new SurvivalSelectionMuPlusLambda(new SelectionTournamentIsland(20,mu));
    }

    @Override
    public void run() {
        for(int i = 0; i< islands; i++){
            populations.add(new InitialPopulationSimple(MU).createInitialPopulation(/*((i+1)/islands)*10*/));
        }

        for( Population population : populations){
            for (Individual ind : population.getIndividuals()) {
                evaluateInitialIndividual(ind);
            }
        }
        
        int genCount = 1;
        while (getEvals() < evaluationsLimit) {
            if( genCount % epochLength == 0){
                exchangeIndividuals();
            }
            for( Population population : populations){
                ArrayList<Individual> children = new ArrayList<Individual>();
                for (int i = 0; i < LAMBDA; i++) {
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
                
                for(Individual ind:population.getIndividuals()){
                    System.out.println(ind.toString());
                }
            }
            genCount++;

        }
    }

    private void exchangeIndividuals() {
        ArrayList<ArrayList<Individual>> exchanges = new ArrayList<ArrayList<Individual>>();
        for(int i = 0; i<populations.size(); i++){
            
            ArrayList<Individual> exchangeA = new ArrayList<Individual>();
            Collections.sort(populations.get(i).getIndividuals());
            for( int k = 0; k<exchangeIndCount; k++){
                Individual echange = populations.get(i).getIndividuals().get(populations.get(i).getIndividuals().size()-1-k);
                exchangeA.add(echange);
            }
            
            exchanges.add(exchangeA);
            
        }
        
        for( int i = 0; i< populations.size(); i++){
            ArrayList<Individual> exchangeA;
            if(i==0){
                exchangeA = exchanges.get(exchanges.size()-1);
            }else{
                exchangeA = exchanges.get(i-1);
            }
            
            for( int k = 0; k<exchangeIndCount; k++){
                populations.get(i).getIndividuals().remove(i);
                populations.get(i).getIndividuals().add(exchangeA.get(k));
            }
            
        }
    }
    
    @Override
    public void resetEvals() {
        // TODO Auto-generated method stub
        super.resetEvals();
        populations = new ArrayList<Population>();
    }

}
