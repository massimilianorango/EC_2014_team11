import java.util.ArrayList;


public class RecombinationSingleArtihmetic extends IRecombination{
    
    public int K;
    public double ALPHA;
    
    public RecombinationSingleArtihmetic(){
        this(0.5);
    }
    
    public RecombinationSingleArtihmetic(double ALPHA_RECOMBINATION){
        this.ALPHA = ALPHA_RECOMBINATION;
    }
    
    public void setK(int K){
        this.K = K;
    }
    
    public int getK(){
        return K;
    }

    @Override
    public ArrayList<Individual> crossover(Individual[] parents, int number_of_childs) {
        ArrayList<Individual> individuals = new ArrayList<Individual>();
        
        while(individuals.size() < number_of_childs){
            double[] child_dna = crossOperator(parents[0].getDna(),parents[1].getDna());
            Individual individual = new Individual(child_dna);
            individual.setSigma_mutation_step_sizes(sigmaCrossover(parents));
            individuals.add(individual);
        }
        
        return individuals;
    }

    public double[] crossOperator(double[] a, double[] b) {
        double[] child_dna = new double[10];

        K = player11.getRnd().nextInt(10);
        child_dna[K] = (ALPHA*a[K])+((1-ALPHA)*+b[K]);
        
        return child_dna;
    }


}
